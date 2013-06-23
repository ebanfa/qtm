/**
 * 
 */
package com.nathanclaire.alantra.messaging.service.process;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.model.BaseEntity;
import com.nathanclaire.alantra.base.request.BaseRequest;
import com.nathanclaire.alantra.base.service.process.BaseProcessService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtils;
import com.nathanclaire.alantra.customer.model.Customer;
import com.nathanclaire.alantra.datasource.etl.TableData;
import com.nathanclaire.alantra.datasource.model.DataChannel;
import com.nathanclaire.alantra.messaging.annotation.CustomerMessageReceivedEvent;
import com.nathanclaire.alantra.messaging.annotation.ExistingMessageReceivedEvent;
import com.nathanclaire.alantra.messaging.annotation.UnclassifiedMessageReceivedEvent;
import com.nathanclaire.alantra.messaging.annotation.UnregisteredCustomerMessageReceivedEvent;
import com.nathanclaire.alantra.messaging.annotation.UnregisteredUserMessageReceivedEvent;
import com.nathanclaire.alantra.messaging.annotation.UserMessageReceivedEvent;
import com.nathanclaire.alantra.messaging.event.MessageEvent;
import com.nathanclaire.alantra.messaging.model.Message;
import com.nathanclaire.alantra.messaging.model.MessageAttachements;
import com.nathanclaire.alantra.messaging.model.MessageType;
import com.nathanclaire.alantra.messaging.request.MessageAttachementsRequest;
import com.nathanclaire.alantra.messaging.request.MessageRequest;
import com.nathanclaire.alantra.messaging.service.entity.MessageStatusService;
import com.nathanclaire.alantra.security.model.SystemUser;

/**
 * @author Edward Banfa 
 *
 */
@Stateless
public class MessageDataInputServiceImpl extends BaseProcessService implements MessageDataInputService 
{
	@Inject MessageProcessingService messageProcessingService;
	@Inject @UserMessageReceivedEvent Event<MessageEvent> userMessageReceivedEvent;
	@Inject @ExistingMessageReceivedEvent Event<MessageEvent> existingMessageReceivedEvent;
	@Inject @CustomerMessageReceivedEvent Event<MessageEvent> customerMessageReceivedEvent;
	@Inject @UnclassifiedMessageReceivedEvent Event<MessageEvent> unclassifiedMessageReceivedEvent;
	@Inject @UnregisteredUserMessageReceivedEvent Event<MessageEvent> unregisteredUserMessageReceivedEvent;
	@Inject @UnregisteredCustomerMessageReceivedEvent Event<MessageEvent> unregisteredCustomerMessageReceivedEvent;
	
	private Logger logger = LoggerFactory.getLogger(MessageDataInputServiceImpl.class);

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.process.EntityDataInputService#processDataInput(com.nathanclaire.alantra.base.request.BaseRequest, com.nathanclaire.alantra.base.request.BaseRequest)
	 */
	@Override
	public BaseEntity processDataInput(BaseRequest primaryEntityRequest, BaseRequest secEntityRequest, TableData tableData) 
			throws ApplicationException {

		logger.debug("Processing data input request for primary entity {}, " +
				"with secondary entity {} with table data of size {}", primaryEntityRequest, secEntityRequest, tableData.getRows().size());
		if(primaryEntityRequest == null) throw new ApplicationException(INVALID_PRIM_ENTITY_SPECIFIED);
		PropertyUtils.initializeBaseFields(primaryEntityRequest);
		// 1. Cast to message request and check if the message already exists
		MessageRequest messageRequest = (MessageRequest) primaryEntityRequest;
		Message message = messageProcessingService.findMessage(messageRequest.getCode());
		if(message != null) rejectMessage(message, secEntityRequest, tableData);
		// 2. Check for attachments
		MessageAttachementsRequest attachments = null;
		if(secEntityRequest != null) attachments = (MessageAttachementsRequest) secEntityRequest;
		// 3. Process customer message
		if(messageProcessingService.isCustomerMessage(messageRequest)) 
			return processCustomerMessage(messageRequest, attachments, tableData);
		// 4. Process user message
		else if(messageProcessingService.isUserMessage(messageRequest))
			return processUserMessage(messageRequest, attachments, tableData);
		else
			return processUnknownMessageType(messageRequest, attachments, tableData);
	}

	/**
	 * @param messageRequest
	 * @param attachments
	 * @param tableData
	 * @return
	 * @throws ApplicationException
	 */
	private BaseEntity processCustomerMessage(MessageRequest messageRequest, 
			MessageAttachementsRequest attachments, TableData tableData) throws ApplicationException 
	{
		Customer customer = messageProcessingService.findCustomerFromMessage(messageRequest);
		if(customer == null) 
			return processUnregisteredCustomerMessage(messageRequest, attachments, tableData);
		else 
			return processRegisteredCustomerMessage(customer, messageRequest, attachments, tableData);
	}

	/**
	 * @param messageRequest
	 * @param attachments
	 * @param tableData
	 * @return
	 * @throws ApplicationException
	 */
	private BaseEntity processUserMessage(MessageRequest messageRequest, 
			MessageAttachementsRequest attachments, TableData tableData) throws ApplicationException 
	{
		SystemUser user = messageProcessingService.findSystemUserFromMessage(messageRequest);
		if(user == null) 
			return processUnregisteredUserMessage(messageRequest, attachments, tableData);
		else 
			return processRegisteredUserMessage(user, messageRequest, attachments, tableData);
	}

	/**
	 * @param messageRequest
	 * @param attachments
	 * @param tableData
	 * @return
	 * @throws ApplicationException
	 */
	private BaseEntity processUnknownMessageType(MessageRequest messageRequest,
			MessageAttachementsRequest attachments, TableData tableData) throws ApplicationException 
	{
		initializeMessageRequest(MessageStatusService.UNCLASSIFIED_MESSAGE_RECEIVED, messageRequest, tableData);
		return acceptMessage(MessageEvent.SENDER_TY_UNCLASSIFIED, null, null, messageRequest, attachments, tableData);
	}
	
	/**
	 * @param customer
	 * @param messageRequest
	 * @param attachments
	 * @param tableData
	 * @return
	 * @throws ApplicationException
	 */
	private BaseEntity processRegisteredCustomerMessage(Customer customer, 	MessageRequest messageRequest,
			MessageAttachementsRequest attachments, TableData tableData) throws ApplicationException  
	{
		initializeMessageRequest(MessageStatusService.CUSTOMER_MESSAGE_RECEIVED, messageRequest, tableData);
		return acceptMessage(MessageEvent.SENDER_TY_CUSTOMER, customer.getId(), null, messageRequest, attachments, tableData);
	}

	/**
	 * @param messageRequest
	 * @param attachments
	 * @param tableData
	 * @return
	 * @throws ApplicationException
	 */
	private BaseEntity processUnregisteredCustomerMessage(MessageRequest messageRequest,
			MessageAttachementsRequest attachments, TableData tableData)  throws ApplicationException {
		initializeMessageRequest(MessageStatusService.CUSTOMER_NOT_REGISTERED, messageRequest, tableData);
		return acceptMessage(MessageEvent.SENDER_TY_CUSTOMER, null, null, messageRequest, attachments, tableData);
	}

	/**
	 * @param messageRequest
	 * @param attachments
	 * @param tableData
	 * @return
	 * @throws ApplicationException
	 */
	private BaseEntity processUnregisteredUserMessage(MessageRequest messageRequest,
			MessageAttachementsRequest attachments, TableData tableData) throws ApplicationException {
		initializeMessageRequest(MessageStatusService.SYSTEM_USER_NOT_REGISTERED, messageRequest, tableData);
		return acceptMessage(MessageEvent.SENDER_TY_USER, null, null, messageRequest, attachments, tableData);
	}

	/**
	 * @param user
	 * @param messageRequest
	 * @param attachments
	 * @param tableData
	 * @return
	 * @throws ApplicationException
	 */
	private BaseEntity processRegisteredUserMessage(SystemUser user, MessageRequest messageRequest,
			MessageAttachementsRequest attachments, TableData tableData) throws ApplicationException {
		initializeMessageRequest(MessageStatusService.SYSTEM_USER_MESSAGE_RECEIVED, messageRequest, tableData);
		return acceptMessage(MessageEvent.SENDER_TY_USER, null, null, messageRequest, attachments, tableData);
	}

	/**
	 * @param tableData
	 * @param messageRequest
	 * @throws ApplicationException
	 */
	private void initializeMessageRequest(String messageStatusCode, MessageRequest messageRequest, 
			TableData tableData) throws ApplicationException {
		// Get message type, message class and message status;
		DataChannel dataChannel = getDataImportService(tableData.getSourceServiceCode());
		messageRequest.setDataChannelId(dataChannel.getId());
		messageRequest.setMessageStatusId(messageProcessingService.getMessageStatus(messageStatusCode).getId());
		messageRequest.setMessageTypeId(messageProcessingService.getMessageType(messageRequest).getId());
		messageRequest.setMessageClassificationId(messageProcessingService.getMessageClassification(dataChannel).getId());
	}

	/**
	 * @param senderTyCustomer 
	 * @param secEntityRequest
	 * @param tableData
	 * @param messageRequest
	 * @param attachmentFileName
	 * @param attachmentMimeType
	 * @return
	 * @throws ApplicationException
	 */
	private Message acceptMessage(String senderType, Integer customerId, Integer userId, MessageRequest messageRequest, 
			MessageAttachementsRequest attachmentRequest, TableData tableData) throws ApplicationException 
	{
		Message message = messageProcessingService.create(messageRequest);
		MessageType messageType = message.getMessageType();
		// Update job statistics related information
		flagDataInputAccepted(tableData);		

		MessageEvent event = new MessageEvent(tableData.getJobId(),
				message.getId(), messageType.getCode(), customerId, userId, null, null, false);
		// Do we have an attachment
		if(attachmentRequest != null) {
			MessageAttachements attachment = 
					messageProcessingService.createAttachment(message, attachmentRequest);
			event.setContainsAttachment(true);
			event.setAttachmentId(attachment.getId());
			event.setAttachmentFileType(attachmentRequest.getDataTy());
			event.setAttachmentFileName(attachmentRequest.getDataUrl());
		}
		// Fire appropriate event
		if(senderType.equals(MessageEvent.SENDER_TY_CUSTOMER))
		{
			if(customerId != null)
				customerMessageReceivedEvent.fire(event);
			else
				unregisteredCustomerMessageReceivedEvent.fire(event);
		}
		if(senderType.equals(MessageEvent.SENDER_TY_USER))
		{
			if(userId != null)
				userMessageReceivedEvent.fire(event);
			else
				unregisteredUserMessageReceivedEvent.fire(event);
		}
		if(senderType.equals(MessageEvent.SENDER_TY_UNCLASSIFIED))
			unclassifiedMessageReceivedEvent.fire(event);
		return message;
	}

	/**
	 * @param attachmentRequest
	 * @param tableData
	 * @param message
	 * @throws ApplicationException
	 */
	private void rejectMessage(Message message, BaseRequest attachmentRequest, TableData tableData) 
			throws ApplicationException 
	{
		// Update job statistics related information
		flagDataInputRejected(tableData);
		// Create the message event
		MessageEvent event = new MessageEvent();
		event.setMessageId(message.getId());
		// We set the file name of the attachment in order to be able to delete it in the event listener
		if(attachmentRequest != null) 
		{
			MessageAttachementsRequest attachments = (MessageAttachementsRequest) attachmentRequest;
			event.setAttachmentFileName(attachments.getDataUrl());
		}
		existingMessageReceivedEvent.fire(event);
	}
}
