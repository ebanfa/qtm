/**
 * 
 */
package com.nathanclaire.alantra.notification.event;

import javax.enterprise.event.Observes;

import com.nathanclaire.alantra.notification.annotation.event.EMAILNotificationEventCreated;

/**
 * @author Edward Banfa
 *
 */
public interface EMAILNotificationEventCreatedListener {
	
	public void processEMAILNotificationEventCreated(@Observes @EMAILNotificationEventCreated NotificationEvent event);

}
