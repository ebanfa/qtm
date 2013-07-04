/**
 * 
 */
package com.nathanclaire.alantra.messaging.messenger;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.datasource.model.DataChannel;
import com.nathanclaire.alantra.messaging.annotation.POP3Messenger;
import com.nathanclaire.alantra.messaging.annotation.SMTPMessenger;
import com.nathanclaire.alantra.messaging.annotation.SMTPPOP3Messenger;
import com.nathanclaire.alantra.messaging.util.MessageLite;

/**
 * @author Edward Banfa 
 *
 */
@Stateless
@SMTPPOP3Messenger
public class SMTPPOP3MessengerImpl implements Messenger {
	
	@Inject @POP3Messenger Messenger pop3Messenger;
	@Inject @SMTPMessenger Messenger smtpMessenger;

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.process.MailService#getMessages(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public List<MessageLite> getMessages(DataChannel channel) throws ApplicationException{
		return pop3Messenger.getMessages(channel);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.messenger.Messenger#sendMessage(java.lang.String, java.lang.String, java.lang.String, com.nathanclaire.alantra.messaging.util.MessageLite)
	 */
	@Override
	public void sendMessage(DataChannel channel, MessageLite message) throws ApplicationException {
		smtpMessenger.sendMessage(channel, message);
	}
}
