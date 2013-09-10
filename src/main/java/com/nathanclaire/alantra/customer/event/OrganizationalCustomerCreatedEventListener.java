/**
 * 
 */
package com.nathanclaire.alantra.customer.event;

import javax.enterprise.event.Observes;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.customer.annotation.OrganizationalCustomerCreatedEvent;

/**
 * @author Edward Banfa
 *
 */
public interface OrganizationalCustomerCreatedEventListener {
	
	public void processOrganizationalCustomerCreatedEvent(@Observes @OrganizationalCustomerCreatedEvent CustomerEvent event)
			throws ApplicationException;

}
