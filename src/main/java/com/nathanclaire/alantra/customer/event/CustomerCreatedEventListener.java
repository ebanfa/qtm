/**
 * 
 */
package com.nathanclaire.alantra.customer.event;

import javax.enterprise.event.Observes;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.customer.annotation.CustomerCreatedEvent;

/**
 * @author Edward Banfa
 * 
 */
public interface CustomerCreatedEventListener {

	public void processCustomerCreatedEvent(@Observes @CustomerCreatedEvent CustomerEvent event)
			throws ApplicationException;

}
