/**
 * 
 */
package com.nathanclaire.alantra.advice.event;

import javax.ejb.Stateless;
import javax.enterprise.event.Observes;

import com.nathanclaire.alantra.advice.annotation.AdviceCreatedEvent;
import com.nathanclaire.alantra.base.service.process.BaseProcessService;
import com.nathanclaire.alantra.base.util.ApplicationException;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class AdviceCreatedEventListenerImpl extends BaseProcessService
		implements AdviceCreatedEventListener {

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.event.AdviceCreatedEventListener#processAdviceCreatedEvent(com.nathanclaire.alantra.advice.event.AdviceEvent)
	 */
	@Override

	public void processAdviceCreatedEvent(@Observes @AdviceCreatedEvent AdviceEvent event)
			throws ApplicationException {
		// TODO Auto-generated method stub
		
	}

}
