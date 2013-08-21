/**
 * 
 */
package com.nathanclaire.alantra.messaging.event;

import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import com.nathanclaire.alantra.base.service.process.BaseProcessService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.messaging.annotation.InboundMessageCreatedEvent;
import com.nathanclaire.alantra.messaging.annotation.MessageCreatedEvent;
import com.nathanclaire.alantra.messaging.annotation.OutboundMessageCreatedEvent;
import com.nathanclaire.alantra.messaging.service.process.MessagingService;

/**
 * @author Edward Banfa
 *
 */
public class MessageCreatedEventListenerImpl extends BaseProcessService	implements MessageCreatedEventListener 
{
	@Inject @InboundMessageCreatedEvent Event<MessagingEvent>  inboundMessageCreatedEvent;
	@Inject @OutboundMessageCreatedEvent Event<MessagingEvent>  outboundMessageCreatedEvent;

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.event.MessageCreatedEventListener#processMessageCreatedEvent(com.nathanclaire.alantra.messaging.event.MessagingEvent)
	 */
	@Override
	public void processMessageCreatedEvent(@Observes @MessageCreatedEvent MessagingEvent event)	throws ApplicationException 
	{
		if(event.getInOutFg().equals(MessagingService.MSG_OUT_FG))
			outboundMessageCreatedEvent.fire(
					new MessagingEvent(event.getMsgId(), event.getCustId(), 
							event.getUserId(), event.getInOutFg(), event.getCustMsg(), event.getUserMsg()));
		else if (event.getInOutFg().equals(MessagingService.MSG_IN_FG))
			inboundMessageCreatedEvent.fire(
					new MessagingEvent(event.getMsgId(), event.getCustId(), 
							event.getUserId(), event.getInOutFg(), event.getCustMsg(), event.getUserMsg()));
	}
	

}
