/**
 * 
 */
package com.nathanclaire.alantra.customer.event;

import javax.enterprise.event.Observes;

import com.nathanclaire.alantra.base.service.process.BaseProcessService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.customer.annotation.CustomerCreatedEvent;

/**
 * @author Edward Banfa
 *
 */
public class CustomerCreatedEventListenerImpl extends BaseProcessService
		implements CustomerCreatedEventListener {

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.event.CustomerCreatedEventListener#processCustomerCreatedEvent(com.nathanclaire.alantra.customer.event.CustomerEvent)
	 */
	@Override
	public void processCustomerCreatedEvent(@Observes @CustomerCreatedEvent CustomerEvent event)
			throws ApplicationException {
		// TODO Auto-generated method stub
		
	}

}
