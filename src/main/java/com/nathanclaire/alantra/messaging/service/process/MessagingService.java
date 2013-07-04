/**
 * 
 */
package com.nathanclaire.alantra.messaging.service.process;

import java.util.List;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.datasource.model.DataChannel;
import com.nathanclaire.alantra.messaging.util.MessageLite;

/**
 * @author Edward Banfa 
 *
 */
public interface MessagingService {

	public List<MessageLite> getAllMessages(List<DataChannel> channels) throws ApplicationException;
	
	public void sendMessage(DataChannel channel, MessageLite message) throws ApplicationException;

}
