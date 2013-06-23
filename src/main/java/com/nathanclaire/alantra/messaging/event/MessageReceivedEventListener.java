/**
 * 
 */
package com.nathanclaire.alantra.messaging.event;

import javax.enterprise.event.Observes;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.messaging.annotation.CustomerMessageReceivedEvent;
import com.nathanclaire.alantra.messaging.annotation.ExistingMessageReceivedEvent;
import com.nathanclaire.alantra.messaging.annotation.UnclassifiedMessageReceivedEvent;
import com.nathanclaire.alantra.messaging.annotation.UnregisteredCustomerMessageReceivedEvent;
import com.nathanclaire.alantra.messaging.annotation.UnregisteredUserMessageReceivedEvent;
import com.nathanclaire.alantra.messaging.annotation.UserMessageReceivedEvent;

/**
 * @author Edward Banfa 
 *
 */
public interface MessageReceivedEventListener {
	
	public void processUserMessageReceivedEvent(
			@Observes @UserMessageReceivedEvent MessageEvent event) throws ApplicationException;
	
	public void processExistingMessageReceivedEven(
			@Observes @ExistingMessageReceivedEvent MessageEvent event) throws ApplicationException;

	public void processCustomerMessageReceivedEvent(
			@Observes @CustomerMessageReceivedEvent MessageEvent event) throws ApplicationException;
	
	public void processUnclassifiedMessageReceivedEvent(
			@Observes @UnclassifiedMessageReceivedEvent MessageEvent event) throws ApplicationException;
	
	public void processUnregisteredUserMessageReceivedEvent(
			@Observes @UnregisteredUserMessageReceivedEvent MessageEvent event) throws ApplicationException;
	
	public void processUnregisteredCustomerMessageReceivedEvent(
			@Observes @UnregisteredCustomerMessageReceivedEvent MessageEvent event) throws ApplicationException;
}
