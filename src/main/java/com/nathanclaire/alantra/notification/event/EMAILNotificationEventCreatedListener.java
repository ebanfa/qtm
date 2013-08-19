/**
 * 
 */
package com.nathanclaire.alantra.notification.event;

import javax.enterprise.event.Observes;

import com.nathanclaire.alantra.notification.annotation.event.EMAILNotificationCreatedEvent;

/**
 * @author Edward Banfa
 *
 */
public interface EMAILNotificationEventCreatedListener {
	
	public void processEMAILNotificationEventCreatedEvent(@Observes @EMAILNotificationCreatedEvent NotificationEvent event);

}
