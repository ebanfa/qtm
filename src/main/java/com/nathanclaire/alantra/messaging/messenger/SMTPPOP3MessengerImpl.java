/**
 * 
 */
package com.nathanclaire.alantra.messaging.messenger;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.datasource.model.DataChannel;
import com.nathanclaire.alantra.messaging.annotation.messenger.POP3Messenger;
import com.nathanclaire.alantra.messaging.annotation.messenger.SMTPMessenger;
import com.nathanclaire.alantra.messaging.annotation.messenger.SMTPPOP3Messenger;
import com.nathanclaire.alantra.messaging.util.MessageLite;

/**
 * @author Edward Banfa 
 *
 */
@Stateless
@SMTPPOP3Messenger
public class SMTPPOP3MessengerImpl implements MessengerService {
	
	@Inject @POP3Messenger MessengerService pop3Messenger;
	@Inject @SMTPMessenger MessengerService smtpMessenger;

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
