/**
 * 
 */
package com.nathanclaire.alantra.messaging.service.process;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.messaging.model.Message;
import com.nathanclaire.alantra.messaging.request.MessageRequest;

/**
 * @author Edward Banfa 
 *
 */
public interface MessageProcessService {
	
	public static final String MESSAGE_NOT_FOUND = "MessageProcessService.MESSAGE_NOT_FOUND";
	
	public static final String COULD_NOT_CREATED_MESSAGE = "MessageProcessService.COULD_NOT_CREATED_MESSAGE";
	
	public Message findMessage(String messageCode) throws ApplicationException;
	
	public Message getMessage(Integer messageId) throws ApplicationException;

	public Message createMessage(MessageRequest messageRequest) throws ApplicationException;

}
