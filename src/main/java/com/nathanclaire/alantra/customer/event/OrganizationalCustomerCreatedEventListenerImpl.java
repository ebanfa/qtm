/**
 * 
 */
package com.nathanclaire.alantra.customer.event;

import javax.enterprise.event.Observes;

import com.nathanclaire.alantra.base.service.process.BaseProcessService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.customer.annotation.OrganizationalCustomerCreatedEvent;

/**
 * @author Edward Banfa
 *
 */
public class OrganizationalCustomerCreatedEventListenerImpl extends
		BaseProcessService implements
		OrganizationalCustomerCreatedEventListener {

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.event.OrganizationalCustomerCreatedEventListener#processOrganizationalCustomerCreatedEvent(com.nathanclaire.alantra.customer.event.CustomerEvent)
	 */
	@Override
	public void processOrganizationalCustomerCreatedEvent(@Observes @OrganizationalCustomerCreatedEvent CustomerEvent event)
			throws ApplicationException {
		// TODO Auto-generated method stub
		
	}

}
