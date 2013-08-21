/**
 * 
 */
package com.nathanclaire.alantra.messaging.messenger;

import java.util.List;

import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.datasource.model.DataChannel;
import com.nathanclaire.alantra.messaging.annotation.messenger.SMSHTTPMessenger;
import com.nathanclaire.alantra.messaging.util.MessageLite;

/**
 * @author edward
 *
 */
@Stateless
@SMSHTTPMessenger
public class SMSHTTPMessengerImpl implements MessengerService {
	private Logger logger = LoggerFactory.getLogger(SMSHTTPMessengerImpl.class);

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.messenger.Messenger#getMessages(com.nathanclaire.alantra.datasource.model.DataChannel)
	 */
	@Override
	public List<MessageLite> getMessages(DataChannel channel)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.messenger.Messenger#sendMessage(com.nathanclaire.alantra.datasource.model.DataChannel, com.nathanclaire.alantra.messaging.util.MessageLite)
	 */
	@Override
	public void sendMessage(DataChannel channel, MessageLite message)
			throws ApplicationException {
		logger.debug("Sending message to {} from {} on channel {}", message.getMessageTo(), message.getMessageFrom(), channel.getName());

	}

}
