/**
 * 
 */
package com.nathanclaire.alantra.messaging.service.process.mail;

import java.util.List;

import javax.ejb.Stateless;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.messaging.util.EmailLite;
import com.nathanclaire.alantra.messaging.util.EmailReader;

/**
 * @author Edward Banfa 
 *
 */
@Stateless
public class MailServiceImpl implements MailService {

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.process.MailService#getMessages(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public List<EmailLite> getMessages(String mailServer, String username,	String password) throws ApplicationException{
		return EmailReader.getMessages(mailServer, username, password);
	}

}
