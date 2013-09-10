/**
 * 
 */
package com.nathanclaire.alantra.customer.event;

import javax.enterprise.event.Observes;

import com.nathanclaire.alantra.base.service.process.BaseProcessService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.customer.annotation.IndividualCustomerCreatedEvent;

/**
 * @author Edward Banfa
 *
 */
public class IndividualCustomerCreatedEventListenerImpl extends
		BaseProcessService implements IndividualCustomerCreatedEventListener {

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.event.IndividualCustomerCreatedEventListener#processIndividualCustomerCreatedEvent(com.nathanclaire.alantra.customer.event.CustomerEvent)
	 */
	@Override
	public void processIndividualCustomerCreatedEvent(@Observes @IndividualCustomerCreatedEvent CustomerEvent event)
			throws ApplicationException {
		// TODO Auto-generated method stub
		
	}

}
