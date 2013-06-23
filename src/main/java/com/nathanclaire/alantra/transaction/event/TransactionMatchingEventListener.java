/**
 * 
 */
package com.nathanclaire.alantra.transaction.event;

import javax.enterprise.event.Observes;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.transaction.annotation.TransactionMatchedEvent;
import com.nathanclaire.alantra.transaction.annotation.TransactionNotMatchedEvent;

/**
 * @author Edward Banfa 
 *
 */
public interface TransactionMatchingEventListener {

	/**
	 * @param event
	 */
	public void processTransactionMatchedEvent(@Observes @TransactionMatchedEvent TransactionMatchingEvent event)
			throws ApplicationException;
	
	/**
	 * @param event
	 */
	public void processTransactionNotMatchedEvent(@Observes @TransactionNotMatchedEvent TransactionMatchingEvent event)
			throws ApplicationException;
}
