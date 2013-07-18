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
import com.nathanclaire.alantra.base.util.StringUtil;
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
import com.nathanclaire.alantra.messaging.model.MessageApplicationAction;
import com.nathanclaire.alantra.messaging.model.MessageAttachment;
import com.nathanclaire.alantra.messaging.model.MessageType;
import com.nathanclaire.alantra.messaging.request.MessageAttachmentRequest;
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
	@Inject MessagingModuleService messagingModuleService;
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
		logger.debug("Processing data input for message with message code: {}", messageRequest.getCode());
		Message message = messagingModuleService.findMessage(messageRequest.getCode());
		if(message != null) 
			return rejectMessage(message, secEntityRequest, tableData);
		// 2. Check for attachments
		MessageAttachmentRequest attachments = null;
		if(secEntityRequest != null) attachments = (MessageAttachmentRequest) secEntityRequest;
		// 3. Process customer message
		if(messagingModuleService.isCustomerMessage(messageRequest)) 
			return processCustomerMessage(messageRequest, attachments, tableData);
		// 4. Process user message
		else if(messagingModuleService.isUserMessage(messageRequest))
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
			MessageAttachmentRequest attachments, TableData tableData) throws ApplicationException 
	{
		logger.debug("Processing customer message {} ", messageRequest.getCode());
		// Data channel will be used to find out what type of message we have;
		DataChannel dataChannel = getDataImportService(tableData.getSourceServiceCode());
		Customer customer = messagingModuleService.findCustomerFromMessageRequest(dataChannel, messageRequest);
		if(customer == null) 
			return processUnregisteredCustomerMessage(messageRequest, attachments, tableData, dataChannel);
		else 
			return processRegisteredCustomerMessage(customer, messageRequest, attachments, tableData, dataChannel);
	}

	/**
	 * @param messageRequest
	 * @param attachments
	 * @param tableData
	 * @return
	 * @throws ApplicationException
	 */
	private BaseEntity processUserMessage(MessageRequest messageRequest, 
			MessageAttachmentRequest attachments, TableData tableData) throws ApplicationException 
	{
		logger.debug("Processing user message {} ", messageRequest.getCode());
		// Data channel will be used to find out what type of message we have;
		DataChannel dataChannel = getDataImportService(tableData.getSourceServiceCode());
		SystemUser user = messagingModuleService.findSystemUserFromMessageRequest(dataChannel, messageRequest);
		if(user == null) 
			return processUnregisteredUserMessage(messageRequest, attachments, tableData, dataChannel);
		else 
			return processRegisteredUserMessage(user, messageRequest, attachments, tableData, dataChannel);
	}

	/**
	 * @param messageRequest
	 * @param attachments
	 * @param tableData
	 * @return
	 * @throws ApplicationException
	 */
	private BaseEntity processUnknownMessageType(MessageRequest messageRequest,
			MessageAttachmentRequest attachments, TableData tableData) throws ApplicationException 
	{
		logger.debug("Processing unknown message {} ", messageRequest.getCode());
		// Data channel will be used to find out what type of message we have;
		DataChannel dataChannel = getDataImportService(tableData.getSourceServiceCode());
		initializeMessageRequest(MessageStatusService.UNCLASSIFIED_MESSAGE_RECEIVED, messageRequest, dataChannel);
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
			MessageAttachmentRequest attachments, TableData tableData, DataChannel dataChannel) throws ApplicationException  
	{
		initializeMessageRequest(MessageStatusService.CUSTOMER_MESSAGE_RECEIVED, messageRequest, dataChannel);
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
			MessageAttachmentRequest attachments, TableData tableData, DataChannel dataChannel)  throws ApplicationException {
		initializeMessageRequest(MessageStatusService.CUSTOMER_NOT_REGISTERED, messageRequest, dataChannel);
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
			MessageAttachmentRequest attachments, TableData tableData, DataChannel dataChannel) throws ApplicationException {
		initializeMessageRequest(MessageStatusService.SYSTEM_USER_NOT_REGISTERED, messageRequest, dataChannel);
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
			MessageAttachmentRequest attachments, TableData tableData, DataChannel dataChannel) throws ApplicationException {
		initializeMessageRequest(MessageStatusService.SYSTEM_USER_MESSAGE_RECEIVED, messageRequest, dataChannel);
		return acceptMessage(MessageEvent.SENDER_TY_USER, null, null, messageRequest, attachments, tableData);
	}

	/**
	 * @param tableData
	 * @param messageRequest
	 * @throws ApplicationException
	 */
	private void initializeMessageRequest(String messageStatusCode, MessageRequest messageRequest, 
			DataChannel dataChannel) throws ApplicationException 
	{
		messageRequest.setDataChannelId(dataChannel.getId());
		messageRequest.setMessageStatusId(messagingModuleService.getMessageStatus(messageStatusCode).getId());
		messageRequest.setMessageTypeId(messagingModuleService.getMessageType(dataChannel).getId());
		messageRequest.setMessageApplicationId(messagingModuleService.getMessageApplication(messageRequest).getId());
		messageRequest.setMessageClassificationId(messagingModuleService.getMessageClassification(dataChannel).getId());
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
			MessageAttachmentRequest attachmentRequest, TableData tableData) throws ApplicationException 
	{
		logger.debug("Accepting {} message for customer with id {} and" +
				" tabledata if size {}", senderType, customerId, tableData.getRows().size());
		Message message = messagingModuleService.createMessage(messageRequest);
		MessageType messageType = message.getMessageType();
		// Update job statistics related information
		flagDataInputAccepted(tableData);		

		MessageEvent event = new MessageEvent(tableData.getJobId(),
				message.getId(), messageType.getCode(), customerId, userId, null, null, false);
		event.setMessageCode(message.getCode());
		event.setDataChannelId(message.getDataChannel().getId());
		event.setMessageText(messagingModuleService.getMessageText(messageRequest));
		event.setSourceAddress(message.getMessageFrom());
		event.setMessageApplicationCode(message.getMessageApplication().getCode());
		// Get the message application action for this message
		MessageApplicationAction action = messagingModuleService.getMessageApplicationAction(messageRequest);
		if(action == null)
			throw new ApplicationException(CANNOT_PARSE_APPLICATION_ACTION_FOR_MSG);
		event.setMessageApplicationActionCode(action.getCode());
		// Do we have an attachment
		if(attachmentRequest != null) {
			MessageAttachment attachment = 
					messagingModuleService.createAttachment(message, attachmentRequest);
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
	private Message rejectMessage(Message message, BaseRequest attachmentRequest, TableData tableData) 
			throws ApplicationException 
	{
		// Update job statistics related information
		logger.debug("Rejecting message with code {}", message.getCode());
		flagDataInputRejected(tableData);
		// Create the message event
		MessageEvent event = new MessageEvent();
		event.setMessageId(message.getId());
		// We set the file name of the attachment in order to be able to delete it in the event listener
		if(attachmentRequest != null) 
		{
			MessageAttachmentRequest attachments = (MessageAttachmentRequest) attachmentRequest;
			event.setAttachmentFileName(attachments.getDataUrl());
		}
		existingMessageReceivedEvent.fire(event);
		return message;
	}
}
