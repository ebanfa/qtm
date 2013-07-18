/**
 * 
 */
package com.nathanclaire.alantra.messaging.service.process;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.service.process.BaseProcessService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.datasource.model.DataChannel;
import com.nathanclaire.alantra.messaging.annotation.SMTPPOP3Messenger;
import com.nathanclaire.alantra.messaging.messenger.Messenger;
import com.nathanclaire.alantra.messaging.model.MessageType;
import com.nathanclaire.alantra.messaging.service.entity.MessageTypeService;
import com.nathanclaire.alantra.messaging.util.MessageLite;

/**
 * Convert this to use plugins to implement
 * a common set of features for the different 
 * message types
 * @author Edward Banfa 
 *
 */
@Stateless
public class MessagingServiceImpl extends BaseProcessService implements	MessagingService {
	@Inject ClassificationService classificationService;
	@Inject @SMTPPOP3Messenger Messenger sMTPPOP3Messenger;
	private Logger logger = LoggerFactory.getLogger(MessagingServiceImpl.class);
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.process.MessagingService#getAllMessages(java.util.List)
	 */
	@Override
	public List<MessageLite> getAllMessages(List<DataChannel> channels) throws ApplicationException {
		List<MessageLite> allMessages = new ArrayList<MessageLite>();
		for(DataChannel channel : channels)
		{
			// The channel type should correspond to a message type
			MessageType messageType = classificationService.getMessageType(channel);
			// If message type is SMTP_POP3 then use the SMTP_POP3 messenger
			if(messageType.getCode().equals(MessageTypeService.SMTP_POP3_MSG))
				allMessages.addAll(sMTPPOP3Messenger.getMessages(channel));
		}
		return allMessages;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.process.MessagingService#sendMessage(com.nathanclaire.alantra.datasource.model.DataChannel, com.nathanclaire.alantra.messaging.util.MessageLite)
	 */
	@Override
	public void sendMessage(DataChannel channel, MessageLite message) throws ApplicationException {
		logger.debug("Sending message to {} on channel {}", message.getMessageTo(), channel);
		// The channel type should correspond to a message type
		MessageType messageType = classificationService.getMessageType(channel);
		// If message type is SMTP_POP3 then use the SMTP_POP3 messenger
		if(messageType.getCode().equals(MessageTypeService.SMTP_POP3_MSG))
			sMTPPOP3Messenger.sendMessage(channel, message);
	}

}
