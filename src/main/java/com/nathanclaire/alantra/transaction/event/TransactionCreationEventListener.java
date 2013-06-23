/**
 * 
 */
package com.nathanclaire.alantra.transaction.event;

import javax.enterprise.event.Observes;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.transaction.annotation.ATMTransactionCreatedEvent;
import com.nathanclaire.alantra.transaction.annotation.ChequeTransactionCreatedEvent;
import com.nathanclaire.alantra.transaction.annotation.TransactionAlreadyExistEvent;

/**
 * @author Edward Banfa 
 *
 */
public interface TransactionCreationEventListener {

	/**
	 * @param event
	 */
	public void processATMTransactionCreationEvent(@Observes @ATMTransactionCreatedEvent TransactionCreationEvent event)
			throws ApplicationException;
	
	/**
	 * @param event
	 */
	public void processChequeTransactionCreationEvent(@Observes @ChequeTransactionCreatedEvent TransactionCreationEvent event)
			throws ApplicationException;
	
	/**
	 * @param event
	 */
	public void processTransactionAlreadyExistEvent(@Observes @TransactionAlreadyExistEvent TransactionCreationEvent event)
			throws ApplicationException;
}
