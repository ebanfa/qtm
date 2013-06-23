/**
 * 
 */
package com.nathanclaire.alantra.messaging.service.process;

import java.util.List;

import javax.ejb.Stateless;

import com.nathanclaire.alantra.base.service.process.BaseProcessService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.datasource.model.DataChannel;
import com.nathanclaire.alantra.messaging.util.MessageLite;

/**
 * @author Edward Banfa 
 *
 */
@Stateless
public class MessagingServiceImpl extends BaseProcessService implements	MessagingService {

	@Override
	public List<MessageLite> getAllSMSMessages(List<DataChannel> smsChannels)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MessageLite> getAllEmailMessages(List<DataChannel> emailChannels)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MessageLite> getAllMessages(List<DataChannel> channels)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void sendMessage(DataChannel channel, MessageLite message)
			throws ApplicationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendSMSMessage(DataChannel channel, MessageLite message)
			throws ApplicationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendEmailMessage(DataChannel channel, MessageLite message)
			throws ApplicationException {
		// TODO Auto-generated method stub
		
	}

}
