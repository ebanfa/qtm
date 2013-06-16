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
public interface MessageProcessingService {

	public static final String MESSAGE_RECEIVED_STATUS_NOT_FOUND = "MessageProcessingService.MESSAGE_RECEIVED_STATUS_NOT_FOUND";
	public static final String MESSAGE_CLASSIFICATION_NOT_FOUND = "MessageProcessingService.MESSAGE_CLASSIFICATION_NOT_FOUND";

	/**
	 * @return
	 */
	public MessageStatus getReceivedMessageStatus() throws ApplicationException;

	/**
	 * @param dataChannel
	 * @return
	 */
	public MessageClassification getMessageClassification(DataChannel dataChannel) throws ApplicationException;
	
	/**
	 * @param messageRequest
	 * @return
	 */
	public MessageType getMessageType(MessageRequest messageRequest) throws ApplicationException;
}
