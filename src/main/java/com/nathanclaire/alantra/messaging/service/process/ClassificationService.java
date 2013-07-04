/**
 * 
 */
package com.nathanclaire.alantra.messaging.service.process;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.datasource.model.DataChannel;
import com.nathanclaire.alantra.messaging.model.MessageClassification;
import com.nathanclaire.alantra.messaging.model.MessageStatus;
import com.nathanclaire.alantra.messaging.model.MessageType;
import com.nathanclaire.alantra.messaging.request.MessageRequest;

/**
 * @author Edward Banfa 
 *
 */
public interface ClassificationService {

	public static final String MESSAGE_TYPE_NOT_FOUND = "ClassificationService.MESSAGE_TYPE_NOT_FOUND";
	
	public static final String MESSAGE_STATUS_NOT_FOUND = "ClassificationService.MESSAGE_STATUS_NOT_FOUND";
	
	public static final String MESSAGE_CLASSIFICATION_NOT_FOUND = "ClassificationService.MESSAGE_CLASSIFICATION_NOT_FOUND";

	public boolean isCustomerMessage(MessageRequest messageRequest) throws ApplicationException;
	
	public boolean isUserMessage(MessageRequest messageRequest) throws ApplicationException;
	
	public MessageStatus getMessageStatus(String messageStatusCode) throws ApplicationException;
	
	public MessageClassification getMessageClassification(DataChannel dataChannel) throws ApplicationException;
	
	public MessageType getMessageType(DataChannel channel) throws ApplicationException;
	

}
