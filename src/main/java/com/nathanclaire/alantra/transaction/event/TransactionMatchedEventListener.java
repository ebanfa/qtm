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
public interface TransactionMatchedEventListener {

	/**
	 * @param event
	 */
	public void processTransactionMatchedEvent(@Observes @TransactionMatchedEvent TransactionEvent event)
			throws ApplicationException;
}
