/**
 * 
 */
package com.nathanclaire.alantra.notification.event;

import javax.enterprise.event.Observes;

import com.nathanclaire.alantra.notification.annotation.event.IVRNotificationCreatedEvent;

/**
 * @author Edward Banfa
 *
 */
public interface IVRNotificationEventCreatedListener {
	
	public void processIVRNotificationEventCreatedEvent(@Observes @IVRNotificationCreatedEvent NotificationEvent event);

}
