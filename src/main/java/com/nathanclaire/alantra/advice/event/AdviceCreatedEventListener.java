/**
 * 
 */
package com.nathanclaire.alantra.advice.event;

import javax.enterprise.event.Observes;

import com.nathanclaire.alantra.advice.annotation.AdviceCreatedEvent;
import com.nathanclaire.alantra.base.util.ApplicationException;

/**
 * @author Edward Banfa
 *
 */
public interface AdviceCreatedEventListener {
	
	public void processAdviceCreatedEvent(@Observes @AdviceCreatedEvent AdviceEvent event) throws ApplicationException;

}
