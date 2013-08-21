/**
 * 
 */
package com.nathanclaire.alantra.messaging.event;

import javax.ejb.Stateless;
import javax.enterprise.event.Observes;

import com.nathanclaire.alantra.base.service.process.BaseProcessService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.messaging.annotation.InboundMessageCreatedEvent;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class InboundMessageCreatedEventListenerImpl extends BaseProcessService
		implements InboundMessageCreatedEventListener {

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.event.InboundMessageCreatedEventListener#processInboundMessageCreatedEvent(com.nathanclaire.alantra.messaging.event.MessagingEvent)
	 */
	@Override
	public void processInboundMessageCreatedEvent(@Observes @InboundMessageCreatedEvent MessagingEvent event)
			throws ApplicationException {
		// TODO Auto-generated method stub
		
	}

}
