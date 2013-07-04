/**
 * 
 */
package com.nathanclaire.alantra.messaging.messenger;

import java.util.List;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.datasource.model.DataChannel;
import com.nathanclaire.alantra.messaging.util.MessageLite;

/**
 * @author Edward Banfa 
 *
 */
public interface Messenger {
	

	public static final String INVALID_SENDER_ADDRESS = "MessageReader.INVALID_SENDER_ADDRESS";
	
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
