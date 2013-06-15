/**
 * 
 */
package com.nathanclaire.alantra.messaging.service.process.mail;

import java.util.List;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.messaging.util.EmailLite;

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
	public List<EmailLite> getMessages(String mailServer, String username, String password) throws ApplicationException;

}
