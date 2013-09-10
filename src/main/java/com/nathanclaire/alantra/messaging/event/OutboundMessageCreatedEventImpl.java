/**
 * 
 */
package com.nathanclaire.alantra.messaging.event;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.service.process.BaseProcessService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.datasource.service.entity.DataChannelEntityService;
import com.nathanclaire.alantra.messaging.annotation.OutboundMessageCreatedEvent;
import com.nathanclaire.alantra.messaging.messenger.MessengerService;
import com.nathanclaire.alantra.messaging.messenger.MessengerServiceLocator;
import com.nathanclaire.alantra.messaging.model.Message;
import com.nathanclaire.alantra.messaging.service.entity.MessageService;
import com.nathanclaire.alantra.messaging.util.MessageLite;

/**
 * @author Edward Banfa
 *
 */
public class OutboundMessageCreatedEventImpl extends BaseProcessService
		implements OutboundMessageCreatedEventListener {

	@Inject MessageService messageService;
	@Inject DataChannelEntityService channelService;
	@Inject MessengerServiceLocator serviceLocator;
	private Logger logger = LoggerFactory.getLogger(getClass());

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.event.OutboundMessageCreatedEventListener#processOutboundMessageCreatedEvent(com.nathanclaire.alantra.messaging.event.MessagingEvent)
	 */
	@Override
	public void processOutboundMessageCreatedEvent(@Observes @OutboundMessageCreatedEvent MessagingEvent event) throws ApplicationException 
	{
		try {
			Boolean containsAttachments = false;
			Message message = messageService.findById(event.getMsgId());
			if(message.getMessageAttachments().isEmpty())
				containsAttachments = true;
			
			MessengerService messengerService = 
					serviceLocator.findMessengerService(message.getDataChannel());
					
			messengerService.sendMessage(message.getDataChannel(), 
					new MessageLite(message.getMessageTo(), message.getMessageFrom(), 
							message.getMessageSubject(), message.getMessageTxt(), containsAttachments));
		} 
		catch (Exception e) {
			logger.error("Error processing outbound message. {}", e.getMessage());
		}
	}
}
