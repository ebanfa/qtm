/**
 * 
 */
package com.nathanclaire.alantra.transaction.event;

import javax.enterprise.event.Observes;

import com.nathanclaire.alantra.transaction.annotation.TransactionNotCreatedEvent;

/**
 * @author edward
 *
 */
public class TransactionNotCreatedEventListenerImpl implements
		TransactionNotCreatedEventListener {

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.transaction.event.TransactionNotCreatedEventListener#processTransactionNotCreatedEvent(com.nathanclaire.alantra.transaction.event.TransactionEvent)
	 */
	@Override
	public void processTransactionNotCreatedEvent(@Observes @TransactionNotCreatedEvent TransactionEvent event) {
		// TODO Auto-generated method stub
		
	}


}
