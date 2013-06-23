/**
 * 
 */
package com.nathanclaire.alantra.messaging.service.process.mail;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.datasource.model.DataChannel;
import com.nathanclaire.alantra.messaging.annotation.EmailMessageReader;
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
	public List<MessageLite> getMessages(DataChannel channel) throws ApplicationException{
		return messageReader.getMessages(channel.getIpAddr(), channel.getUsername(), channel.getPassword());
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.process.mail.MailService#sendMessage(java.lang.String, java.lang.String, java.lang.String, com.nathanclaire.alantra.messaging.util.MessageLite)
	 */
	@Override
	public void sendMessage(DataChannel channel, MessageLite message) throws ApplicationException {
		// TODO Auto-generated method stub
		
	}

}
