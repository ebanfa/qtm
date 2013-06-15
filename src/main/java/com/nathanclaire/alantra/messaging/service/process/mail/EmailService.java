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
public interface EmailService {

	public void SendEmail(EmailLite message)throws ApplicationException;
	
	public List<EmailLite> getMessages()throws ApplicationException;
}
