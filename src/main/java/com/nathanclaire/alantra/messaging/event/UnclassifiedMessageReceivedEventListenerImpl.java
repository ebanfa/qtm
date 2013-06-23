/**
 * 
 */
package com.nathanclaire.alantra.messaging.event;

import javax.ejb.Stateless;
import javax.enterprise.event.Observes;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.messaging.annotation.UnclassifiedMessageReceivedEvent;

/**
 * @author Edward Banfa 
 *
 */
@Stateless
public class UnclassifiedMessageReceivedEventListenerImpl implements
		UnclassifiedMessageReceivedEventListener {

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.event.UnclassifiedMessageReceivedEventListener#processUnclassifiedMessageReceivedEvent(com.nathanclaire.alantra.messaging.event.MessageReceivedEvent)
	 */
	@Override
	public void processUnclassifiedMessageReceivedEvent(@Observes @UnclassifiedMessageReceivedEvent MessageEvent event)
			throws ApplicationException {
	}

}
