/**
 * 
 */
package com.nathanclaire.alantra.messaging.event;

import javax.enterprise.event.Observes;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.messaging.annotation.InboundMessageCreatedEvent;

/**
 * @author Edward Banfa
 *
 */
public interface InboundMessageCreatedEventListener {
	
	public void processInboundMessageCreatedEvent(@Observes @InboundMessageCreatedEvent MessagingEvent event) throws ApplicationException;

}
