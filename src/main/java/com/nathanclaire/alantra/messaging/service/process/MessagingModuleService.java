/**
 * 
 */
package com.nathanclaire.alantra.messaging.service.process;

import java.util.List;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.customer.model.Customer;
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

/**
 * @author Edward Banfa 
 *
 */
public interface MessagingModuleService {

	
	public Message findMessage(String messageCode) throws ApplicationException;
	
	public Message getMessage(Integer messageId) throws ApplicationException;

	public Message createMessage(MessageRequest messageRequest) throws ApplicationException;

	public Customer findCustomerFromMessageRequest(DataChannel dataChannel, MessageRequest messageRequest) throws ApplicationException;

	public SystemUser findSystemUserFromMessageRequest(DataChannel dataChannel, MessageRequest messageRequest) throws ApplicationException;

	public MessageStatus getMessageStatus(String messageStatusCode) throws ApplicationException;
	
	public MessageClassification getMessageClassification(DataChannel dataChannel) throws ApplicationException;
	
	public boolean isCustomerMessage(MessageRequest messageRequest) throws ApplicationException;
	
	public boolean isUserMessage(MessageRequest messageRequest) throws ApplicationException;

	public String getMessageText(MessageRequest messageRequest) throws ApplicationException;

	public String getAdviceRefNoInMessageText(String messageText) throws ApplicationException;
	
	public MessageAttachment getAttachment(Integer attachmentId) throws ApplicationException;
	
	public MessageAttachment createAttachment(Message message , MessageAttachmentRequest attachmentRequest) 
			throws ApplicationException;

	public void sendCustomerMessage(Customer customer, List<DataChannel> channels, 
			String subjectText, String messageText) throws ApplicationException;

	public void sendUserMessage(SystemUser user, List<DataChannel> channels, 
			MessageType messageType, String subjectText, String messageText) throws ApplicationException;

	public MessageType getMessageType(DataChannel dataChannel) throws ApplicationException;

	public MessageApplication getMessageApplication(MessageRequest messageRequest) throws ApplicationException;

	public MessageApplicationAction getMessageApplicationAction(MessageRequest messageRequest) throws ApplicationException;
	

}
