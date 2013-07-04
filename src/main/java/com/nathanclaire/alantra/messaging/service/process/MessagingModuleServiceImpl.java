/**
 * 
 */
package com.nathanclaire.alantra.messaging.service.process;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.service.process.BaseProcessService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.customer.model.Customer;
import com.nathanclaire.alantra.customer.service.process.CustomerProcessingService;
import com.nathanclaire.alantra.datasource.model.DataChannel;
import com.nathanclaire.alantra.messaging.model.Message;
import com.nathanclaire.alantra.messaging.model.MessageApplication;
import com.nathanclaire.alantra.messaging.model.MessageApplicationAction;
import com.nathanclaire.alantra.messaging.model.MessageAttachment;
import com.nathanclaire.alantra.messaging.model.MessageClassification;
import com.nathanclaire.alantra.messaging.model.MessageStatus;
import com.nathanclaire.alantra.messaging.model.MessageType;
import com.nathanclaire.alantra.messaging.request.MessageAttachmentRequest;
import com.nathanclaire.alantra.messaging.request.MessageRequest;
import com.nathanclaire.alantra.security.model.SystemUser;
import com.nathanclaire.alantra.security.service.process.SecurityService;

/**
 * @author Edward Banfa 
 *
 */
@Stateless
public class MessagingModuleServiceImpl extends BaseProcessService implements MessagingModuleService {

	@Inject MessageProcessService messageProcessService;
	@Inject SecurityService securityService;
	@Inject AttachmentService attachmentService;
	@Inject ClassificationService classificationService;
	@Inject CustomerMessagingService customerMessagingService;
	@Inject CustomerProcessingService customerProcessingService;
	@Inject MessageTextProcessingService messageTextProcessingService;
	
	private Logger logger = LoggerFactory.getLogger(MessagingModuleServiceImpl.class);

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.process.MessagingModuleService#findMessage(java.lang.String)
	 */
	@Override
	public Message findMessage(String messageCode) throws ApplicationException {
		return messageProcessService.findMessage(messageCode);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.process.MessagingModuleService#findMessage(java.lang.String)
	 */
	@Override
	public Message getMessage(Integer messageId) throws ApplicationException {
		return messageProcessService.getMessage(messageId);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.process.MessagingModuleService#getCustomerFromMessage(com.nathanclaire.alantra.messaging.request.MessageRequest)
	 */
	@Override
	public Customer findCustomerFromMessageRequest(DataChannel dataChannel, MessageRequest messageRequest) 
			throws ApplicationException {
		MessageType messageType = getMessageType(dataChannel);
		// Customers can share source addresses
		return customerProcessingService.findSingleCustomerBySourceAddress(messageType, messageRequest.getMessageFrom());
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.process.MessagingModuleService#getSystemUserFromMessage(com.nathanclaire.alantra.messaging.request.MessageRequest)
	 */
	@Override
	public SystemUser findSystemUserFromMessageRequest(DataChannel dataChannel, MessageRequest messageRequest 
			) throws ApplicationException {
		// Users cannot share source addresses
		return securityService.findUserBySourceAddress(messageRequest.getMessageFrom());
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.process.MessagingModuleService#isCustomerMessage(com.nathanclaire.alantra.messaging.request.MessageRequest)
	 */
	@Override
	public boolean isCustomerMessage(MessageRequest messageRequest) throws ApplicationException {
		return classificationService.isCustomerMessage(messageRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.process.MessagingModuleService#isUserMessage(com.nathanclaire.alantra.messaging.request.MessageRequest)
	 */
	@Override
	public boolean isUserMessage(MessageRequest messageRequest) throws ApplicationException {
		return classificationService.isUserMessage(messageRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.process.MessagingModuleService#create(com.nathanclaire.alantra.messaging.request.MessageRequest)
	 */
	@Override
	public Message createMessage(MessageRequest messageRequest) throws ApplicationException {
		return messageProcessService.createMessage(messageRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.process.MessagingModuleService#getMessageStatus(java.lang.String)
	 */
	@Override
	public MessageStatus getMessageStatus(String messageStatusCode) throws ApplicationException {
		return classificationService.getMessageStatus(messageStatusCode);
	}
	
	@Override
	public MessageClassification getMessageClassification(DataChannel dataChannel) throws ApplicationException {
		return classificationService.getMessageClassification(dataChannel);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.process.MessagingModuleService#getMessageText(com.nathanclaire.alantra.messaging.request.MessageRequest)
	 */
	@Override
	public String getMessageText(MessageRequest messageRequest) throws ApplicationException {
		return messageTextProcessingService.getMessageText(messageRequest);
	}

	@Override
	public String getAdviceRefNoInMessageText(String messageText) throws ApplicationException {
		return messageTextProcessingService.extractAdviceReferenceString(messageText);
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.process.MessagingModuleService#createAttachment(com.nathanclaire.alantra.messaging.model.Message, com.nathanclaire.alantra.messaging.request.MessageAttachmentRequest)
	 */
	@Override
	public MessageAttachment createAttachment(Message message, MessageAttachmentRequest attachmentRequest) 
			throws ApplicationException {
		return attachmentService.createAttachment(message, attachmentRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.process.MessagingModuleService#getAttachment(java.lang.Integer)
	 */
	@Override
	public MessageAttachment getAttachment(Integer attachmentId)	throws ApplicationException {
		return attachmentService.getAttachment(attachmentId);
	}

	@Override
	public void sendCustomerMessage(Customer customer, List<DataChannel> channels, 
			String subjectText, String messageText) throws ApplicationException {
		customerMessagingService.sendMessageToCustomer(customer, channels, subjectText, messageText);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.process.MessagingModuleService#sendUserMessage(com.nathanclaire.alantra.security.model.SystemUser, java.util.List, com.nathanclaire.alantra.messaging.model.MessageType, java.lang.String, java.lang.String)
	 */
	@Override
	public void sendUserMessage(SystemUser user, List<DataChannel> channels,
			MessageType messageType, String subjectText, String messageText) throws ApplicationException {
		// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.process.MessagingModuleService#getMessageType(com.nathanclaire.alantra.datasource.model.DataChannel)
	 */
	@Override
	public MessageType getMessageType(DataChannel dataChannel) throws ApplicationException {
		return classificationService.getMessageType(dataChannel);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.process.MessagingModuleService#getMessageApplication(com.nathanclaire.alantra.messaging.request.MessageRequest)
	 */
	@Override
	public MessageApplication getMessageApplication(MessageRequest messageRequest) throws ApplicationException {
		String messageText = messageTextProcessingService.getMessageText(messageRequest);
		return messageTextProcessingService.getMessageApplication(messageText);
	}

	@Override
	public MessageApplicationAction getMessageApplicationAction(MessageRequest messageRequest) throws ApplicationException {
		String messageText = messageTextProcessingService.getMessageText(messageRequest);
		return messageTextProcessingService.getMessageApplicationAction(messageText);
	}
	
}
