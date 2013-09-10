/**
 * 
 */
package com.nathanclaire.alantra.advice.event;

import javax.enterprise.event.Observes;

import com.nathanclaire.alantra.advice.annotation.AdviceNotCreatedEvent;
import com.nathanclaire.alantra.base.util.ApplicationException;

/**
 * @author Edward Banfa
 *
 */
public interface AdviceNotCreatedEventListener {

	public void processAdviceNotCreatedEvent(@Observes @AdviceNotCreatedEvent AdviceEvent event) throws ApplicationException;

}
