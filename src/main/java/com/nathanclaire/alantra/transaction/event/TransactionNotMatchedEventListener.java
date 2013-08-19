/**
 * 
 */
package com.nathanclaire.alantra.transaction.event;

import javax.enterprise.event.Observes;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.transaction.annotation.TransactionNotMatchedEvent;

/**
 * @author edward
 *
 */
public interface TransactionNotMatchedEventListener {

	
	/**
	 * @param event
	 */
	public void processTransactionNotMatchedEvent(@Observes @TransactionNotMatchedEvent TransactionEvent event)
			throws ApplicationException;

}
