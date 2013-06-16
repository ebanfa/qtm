/**
 * 
 */
package com.nathanclaire.alantra.messaging.service.process.mail;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.messaging.util.EmailMessageReader;
import com.nathanclaire.alantra.messaging.util.MessageLite;
import com.nathanclaire.alantra.messaging.util.MessageReader;

/**
 * @author Edward Banfa 
 *
 */
@Stateless
public class MailServiceImpl implements MailService {
	
	@Inject @EmailMessageReader MessageReader messageReader;

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.process.MailService#getMessages(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public List<MessageLite> getMessages(String mailServer, String username,	String password) throws ApplicationException{
		return messageReader.getMessages(mailServer, username, password);
	}

}
