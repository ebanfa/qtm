/**
 * 
 */
package com.nathanclaire.alantra.messaging.event;

import javax.enterprise.event.Observes;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.messaging.annotation.AdviceRequestReceivedEvent;

/**
 * @author Edward Banfa 
 *
 */
public interface AdviceRequestReceivedEventListener {
	
	public void processAdviceRequestReceivedEvent(@Observes @AdviceRequestReceivedEvent MessageEvent event) 
			throws ApplicationException;
}
