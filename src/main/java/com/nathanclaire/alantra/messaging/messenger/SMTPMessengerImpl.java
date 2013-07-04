/**
 * 
 */
package com.nathanclaire.alantra.messaging.messenger;

import java.util.List;

import javax.ejb.Stateless;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.datasource.model.DataChannel;
import com.nathanclaire.alantra.messaging.annotation.SMTPMessenger;
import com.nathanclaire.alantra.messaging.util.MessageLite;

/**
 * @author Edward Banfa 
 *
 */
@Stateless
@SMTPMessenger
public class SMTPMessengerImpl implements Messenger {

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
		// TODO Auto-generated method stub

	}

}
