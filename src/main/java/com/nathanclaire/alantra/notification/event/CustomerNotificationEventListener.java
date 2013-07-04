/**
 * 
 */
package com.nathanclaire.alantra.notification.event;

import javax.enterprise.event.Observes;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.notification.annotation.CustomerNotificationCreatedEvent;

/**
 * @author Edward Banfa 
 *
 */
public interface CustomerNotificationEventListener {
	
	public void processNotificationCreated(@Observes @CustomerNotificationCreatedEvent NotificationEvent event)
			throws ApplicationException;

}
