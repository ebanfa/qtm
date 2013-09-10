/**
 * 
 */
package com.nathanclaire.alantra.transaction.event;

import javax.enterprise.event.Observes;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.transaction.annotation.TransactionCreatedEvent;

/**
 * @author Edward Banfa 
 *
 */
public interface TransactionCreatedEventListener {

	/**
	 * @param event
	 */
	public void processTransactionCreatedEvent(@Observes @TransactionCreatedEvent TransactionEvent event)
			throws ApplicationException;
}
