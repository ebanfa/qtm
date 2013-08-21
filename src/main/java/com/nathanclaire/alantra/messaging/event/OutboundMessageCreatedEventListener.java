/**
 * 
 */
package com.nathanclaire.alantra.messaging.event;

import javax.enterprise.event.Observes;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.messaging.annotation.OutboundMessageCreatedEvent;

/**
 * @author Edward Banfa
 *
 */
public interface OutboundMessageCreatedEventListener {
	
	public void processOutboundMessageCreatedEvent(@Observes @OutboundMessageCreatedEvent MessagingEvent event) throws ApplicationException;

}
