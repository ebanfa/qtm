/**
 * 
 */
package com.nathanclaire.alantra.customer.event;

import javax.enterprise.event.Observes;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.customer.annotation.IndividualCustomerCreatedEvent;

/**
 * @author Edward Banfa
 *
 */
public interface IndividualCustomerCreatedEventListener {

	public void processIndividualCustomerCreatedEvent(@Observes @IndividualCustomerCreatedEvent CustomerEvent event)
			throws ApplicationException;
	

}
