/**
 * 
 */
package com.nathanclaire.alantra.transaction.event;

import javax.ejb.Stateless;
import javax.enterprise.event.Observes;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.transaction.annotation.ATMTransactionCreatedEvent;
import com.nathanclaire.alantra.transaction.annotation.ChequeTransactionCreatedEvent;
import com.nathanclaire.alantra.transaction.annotation.TransactionAlreadyExistEvent;

/**
 * @author Edward Banfa 
 *
 */
@Stateless
public class TransactionCreationEventListenerImpl extends BaseTransactionEventListener implements
		TransactionCreationEventListener {

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.transaction.event.TransactionCreationEventListener#processATMTransactionCreationEvent(com.nathanclaire.alantra.transaction.annotation.TransactionCreationEvent)
	 */
	@Override
	public void processATMTransactionCreationEvent(@Observes @ATMTransactionCreatedEvent TransactionCreationEvent event) 
			throws ApplicationException {
		try {
			// 1. Get the transaction and all the transaction matching service
			matchTransaction(getTransaction(event), event);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.transaction.event.TransactionCreationEventListener#processChequeTransactionCreationEvent(com.nathanclaire.alantra.transaction.annotation.TransactionCreationEvent)
	 */
	@Override
	public void processChequeTransactionCreationEvent(	@Observes @ChequeTransactionCreatedEvent TransactionCreationEvent event) 
			throws ApplicationException{
		try {
			// 1. Get the transaction and all the transaction matching service
			matchTransaction(getTransaction(event), event);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.transaction.event.TransactionCreationEventListener#processTransactionAlreadyExistEvent(com.nathanclaire.alantra.transaction.annotation.TransactionCreationEvent)
	 */
	@Override
	public void processTransactionAlreadyExistEvent(@Observes @TransactionAlreadyExistEvent TransactionCreationEvent event) 
			throws ApplicationException {
	}

}
