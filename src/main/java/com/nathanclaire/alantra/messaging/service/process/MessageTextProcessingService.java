/**
 * 
 */
package com.nathanclaire.alantra.messaging.service.process;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.messaging.model.MessageApplication;
import com.nathanclaire.alantra.messaging.model.MessageApplicationAction;
import com.nathanclaire.alantra.messaging.request.MessageRequest;

/**
 * @author Edward Banfa 
 *
 */
public interface MessageTextProcessingService {

	public static final String READ_MSG_APPL_FAILED = 
			"MessageTextProcessingService.READ_MSG_APPL_FAILED";
	public static final String PARSE_REGEX_PATTERN = "([a-zA-Z]*)([\\s]*)([a-zA-Z]*)([\\s]*)([a-zA-Z]*)([\\s]*)([a-zA-Z]*)([\\s]*)([.]*)";
	public static final String MESSAGE_APPL_NOT_FOUND = "MessageTextProcessingService.MESSAGE_APPL_NOT_FOUND";
	public static final String GET_MESSSAGE_TEXT_FAILED = "MessageTextProcessingService.GET_MESSSAGE_TEXT_FAILED";
	public static final String READ_MSG_APPL_ACTION_FAILED = "MessageTextProcessingService.READ_MSG_APPL_ACTION_FAILED";
	public static final String MESSAGE_APPL_ACTION_NOT_FOUND = "MessageTextProcessingService.MESSAGE_APPL_ACTION_NOT_FOUND";

	public String getMessageText(MessageRequest messageRequest) throws ApplicationException;
	
	public MessageApplication getMessageApplication(String messageText) throws ApplicationException;
	
	public MessageApplicationAction getMessageApplicationAction(String messageText) throws ApplicationException;
	
	public String[] getMessageApplicationActionOptions(String messageText) throws ApplicationException;
	
	public String extractMessageApplicationString(String messageText)throws ApplicationException;

	public String extractMessageApplicationActionString(String messageText) throws ApplicationException;
	
	public String extractAdviceReferenceString(String messageText) throws ApplicationException;
}
