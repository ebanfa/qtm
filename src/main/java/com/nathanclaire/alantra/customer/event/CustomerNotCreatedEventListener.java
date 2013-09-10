/**
 * 
 */
package com.nathanclaire.alantra.customer.event;

import javax.enterprise.event.Observes;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.customer.annotation.CustomerNotCreatedEvent;

/**
 * @author Edward Banfa
 *
 */
public interface CustomerNotCreatedEventListener {

	public void processCustomerNotCreatedEvent(@Observes @CustomerNotCreatedEvent CustomerEvent event)
			throws ApplicationException;

}
