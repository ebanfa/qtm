/**
 * 
 */
package com.nathanclaire.alantra.messaging.service.process;

import com.nathanclaire.alantra.base.model.BaseEntity;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.customer.model.Customer;
import com.nathanclaire.alantra.datasource.model.DataChannel;
import com.nathanclaire.alantra.messaging.model.Message;
import com.nathanclaire.alantra.messaging.model.MessageAttachements;
import com.nathanclaire.alantra.messaging.model.MessageClassification;
import com.nathanclaire.alantra.messaging.model.MessageType;
import com.nathanclaire.alantra.messaging.request.MessageAttachementsRequest;
import com.nathanclaire.alantra.messaging.request.MessageRequest;
import com.nathanclaire.alantra.security.model.SystemUser;

/**
 * @author Edward Banfa 
 *
 */
public interface MessageProcessingService {

	public static final String MESSAGE_NOT_FOUND = "MessageProcessingService.MESSAGE_NOT_FOUND";
	public static final String INVALID_MESSAGE_PROVIDED = "MessageProcessingService.INVALID_MESSAGE_PROVIDED";
	public static final String MESSAGE_STATUS_NOT_FOUND = "MessageProcessingService.MESSAGE_STATUS_NOT_FOUND";
	public static final String COULD_NOT_CREATED_MESSAGE = "MessageProcessingService.COULD_NOT_CREATED_MESSAGE";
	public static final String MESSAGE_CLASSIFICATION_NOT_FOUND = "MessageProcessingService.MESSAGE_CLASSIFICATION_NOT_FOUND";
	public static final String COULD_NOT_FIND_MESSAGE_ATTACHMENT = "MessageProcessingService.COULD_NOT_FIND_MESSAGE_ATTACHMENT";
	public static final String INVALID_ATTACHMENT_REQUEST_PROVIDED = "MessageProcessingService.INVALID_ATTACHMENT_REQUEST_PROVIDED";
	public static final String COULD_NOT_CREATED_MESSAGE_ATTACHMENT = "MessageProcessingService.COULD_NOT_CREATED_MESSAGE_ATTACHMENT";

	public Message getMessage(Integer messageId) throws ApplicationException;
	
	public Message findMessage(String messageCode) throws ApplicationException;

	public Message create(MessageRequest messageRequest) throws ApplicationException;
	
	public String extractAdviceReference(String messageText) throws ApplicationException;

	public BaseEntity getMessageStatus(String messageStatusCode) throws ApplicationException;
	
	public boolean isUserMessage(MessageRequest messageRequest) throws ApplicationException;
	
	public MessageAttachements getAttachment(Integer attachmentId) throws ApplicationException;
	
	public MessageType getMessageType(MessageRequest messageRequest) throws ApplicationException;
	
	public boolean isCustomerMessage(MessageRequest messageRequest) throws ApplicationException;

	public String extractMessageTypeTagfromMessage(String messageText)throws ApplicationException;

	public Customer findCustomerFromMessage(MessageRequest messageRequest) throws ApplicationException;

	public SystemUser findSystemUserFromMessage(MessageRequest messageRequest) throws ApplicationException;
	
	public MessageClassification getMessageClassification(DataChannel dataChannel) throws ApplicationException;
	
	public MessageAttachements createAttachment(Message message , MessageAttachementsRequest attachmentRequest) throws ApplicationException;

}
