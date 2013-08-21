/**
 * 
 */
package com.nathanclaire.alantra.messaging.event;

import javax.enterprise.event.Observes;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.messaging.annotation.MessageCreatedEvent;


/**
 * @author Edward Banfa
 *
 */
public interface MessageCreatedEventListener {
	
	public void processMessageCreatedEvent(@Observes @MessageCreatedEvent MessagingEvent event) throws ApplicationException;

}
