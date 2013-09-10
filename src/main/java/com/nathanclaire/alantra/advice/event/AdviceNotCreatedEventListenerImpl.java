/**
 * 
 */
package com.nathanclaire.alantra.advice.event;

import javax.ejb.Stateless;
import javax.enterprise.event.Observes;

import com.nathanclaire.alantra.advice.annotation.AdviceNotCreatedEvent;
import com.nathanclaire.alantra.base.service.process.BaseProcessService;
import com.nathanclaire.alantra.base.util.ApplicationException;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class AdviceNotCreatedEventListenerImpl extends BaseProcessService
		implements AdviceNotCreatedEventListener {

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.event.AdviceNotCreatedEventListener#processAdviceNotCreatedEvent(com.nathanclaire.alantra.advice.event.AdviceEvent)
	 */
	@Override
	public void processAdviceNotCreatedEvent(@Observes @AdviceNotCreatedEvent AdviceEvent event)
			throws ApplicationException {
		// TODO Auto-generated method stub
		
	}

}
