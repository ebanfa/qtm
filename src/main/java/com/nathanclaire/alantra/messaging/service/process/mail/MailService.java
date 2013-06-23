/**
 * 
 */
package com.nathanclaire.alantra.messaging.service.process.mail;

import java.util.List;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.datasource.model.DataChannel;
import com.nathanclaire.alantra.messaging.util.MessageLite;

/**
 * @author Edward Banfa 
 *
 */
public interface MailService {
	
	/**
	 * @param mailServer
	 * @param username
	 * @param password
	 * @return
	 * @throws ApplicationException
	 */
	public List<MessageLite> getMessages(DataChannel channel) throws ApplicationException;
	
	public void sendMessage(DataChannel channel, MessageLite message) throws ApplicationException;

}
