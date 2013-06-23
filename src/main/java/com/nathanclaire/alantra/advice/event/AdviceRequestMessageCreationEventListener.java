/**
 * 
 */
package com.nathanclaire.alantra.advice.event;

import javax.enterprise.event.Observes;

import com.nathanclaire.alantra.advice.annotation.AdviceRequestMessageCreationFailedEvent;
import com.nathanclaire.alantra.advice.annotation.AdviceRequestMessageCreatedEvent;
import com.nathanclaire.alantra.base.util.ApplicationException;

/**
 * @author Edward Banfa 
 *
 */
public interface AdviceRequestMessageCreationEventListener {

	/**
	 * @param event
	 */
	public void processAdviceRequestCreatedEvent(@Observes @AdviceRequestMessageCreatedEvent AdviceRequestMessageCreationEvent event)
			throws ApplicationException ;
	
	/**
	 * @param event
	 */
	public void processAdviceRequestCreateFailedEvent(@Observes 
			@AdviceRequestMessageCreationFailedEvent AdviceRequestMessageCreationEvent event) throws ApplicationException ;
}
