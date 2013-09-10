/**
 * 
 */
package com.nathanclaire.alantra.customer.event;

import javax.enterprise.event.Observes;

import com.nathanclaire.alantra.base.service.process.BaseProcessService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.customer.annotation.CustomerNotCreatedEvent;

/**
 * @author Edward Banfa
 *
 */
public class CustomerNotCreatedEventListenerImpl extends BaseProcessService
		implements CustomerNotCreatedEventListener {

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.event.CustomerNotCreatedEventListener#processCustomerNotCreatedEvent(com.nathanclaire.alantra.customer.event.CustomerEvent)
	 */
	@Override
	public void processCustomerNotCreatedEvent(@Observes @CustomerNotCreatedEvent CustomerEvent event)
			throws ApplicationException {
		// TODO Auto-generated method stub
		
	}

}
