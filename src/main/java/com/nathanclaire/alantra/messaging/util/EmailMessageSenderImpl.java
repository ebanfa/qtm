/**
 * 
 */
package com.nathanclaire.alantra.messaging.util;

import javax.ejb.Stateless;

import com.nathanclaire.alantra.base.service.process.BaseProcessService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.datasource.model.DataChannel;
import com.nathanclaire.alantra.messaging.annotation.EmailMessageSender;

/**
 * @author Edward Banfa 
 *
 */
@Stateless
@EmailMessageSender
public class EmailMessageSenderImpl extends BaseProcessService implements MessageSender {

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.util.MessageSender#sendMessage(com.nathanclaire.alantra.datasource.model.DataChannel, com.nathanclaire.alantra.messaging.util.MessageLite)
	 */
	@Override
	public void sendMessage(DataChannel channel, MessageLite message) throws ApplicationException {
	}

}
