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

	public List<MessageLite> getAllSMSMessages(List<DataChannel> smsChannels) throws ApplicationException;
	
	public List<MessageLite> getAllEmailMessages(List<DataChannel> emailChannels) throws ApplicationException;

	public List<MessageLite> getAllMessages(List<DataChannel> channels) throws ApplicationException;
	
	public void sendMessage(DataChannel channel, MessageLite message) throws ApplicationException;
	
	public void sendSMSMessage(DataChannel channel, MessageLite message) throws ApplicationException;
	
	public void sendEmailMessage(DataChannel channel, MessageLite message) throws ApplicationException;

}
