/**
 * 
 */
package com.nathanclaire.alantra.messaging.service.process;

import java.util.List;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.datasource.model.DataChannel;
import com.nathanclaire.alantra.security.model.SystemUser;

/**
 * @author Edward Banfa 
 *
 */
public interface SystemUserMessagingService {

	public static final String INVALID_USER_TO_SEND_MESSAGE_TO = "SystemUserMessagingService.INVALID_USER_TO_SEND_MESSAGE_TO";
	public static final String ERROR_SENDING_MESSAGE_TO_USER = "SystemUserMessagingService.ERROR_SENDING_MESSAGE_TO_USER";
	
	public void sendMessageToUser(SystemUser user, List<DataChannel> channels, 
			String subjectText, String messageText) throws ApplicationException;

}
