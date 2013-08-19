/**
 * 
 */
package com.nathanclaire.alantra.transaction.event;

import javax.enterprise.event.Observes;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.transaction.annotation.TransactionCreatedEvent;
import com.nathanclaire.alantra.transaction.annotation.ChequeTransactionCreatedEvent;
import com.nathanclaire.alantra.transaction.annotation.TransactionNotCreatedEvent;

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
