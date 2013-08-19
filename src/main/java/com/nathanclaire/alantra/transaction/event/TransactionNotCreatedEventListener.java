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
public interface TransactionNotCreatedEventListener {

	public void processTransactionNotCreatedEvent(@Observes @TransactionNotCreatedEvent TransactionEvent event);
}
