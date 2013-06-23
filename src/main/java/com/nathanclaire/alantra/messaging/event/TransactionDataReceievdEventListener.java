/**
 * 
 */
package com.nathanclaire.alantra.messaging.event;

import javax.enterprise.event.Observes;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.messaging.annotation.TransactionDataReceievdEvent;

/**
 * @author Edward Banfa 
 *
 */
public interface TransactionDataReceievdEventListener {
	
	public void processTransactionDataReceivedEvent(@Observes @TransactionDataReceievdEvent MessageEvent event) 
			throws ApplicationException;
}
