/**
 * 
 */
package com.nathanclaire.alantra.notification.event;

import javax.enterprise.event.Observes;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.notification.annotation.event.NotificationCreatedEvent;

/**
 * @author Edward Banfa 
 *
 */
public interface NotificationCreatedEventListener {
	
	public void processNotificationCreated(@Observes @NotificationCreatedEvent NotificationEvent event)
			throws ApplicationException;

}
