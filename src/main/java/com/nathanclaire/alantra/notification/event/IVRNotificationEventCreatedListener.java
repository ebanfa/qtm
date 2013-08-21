/**
 * 
 */
package com.nathanclaire.alantra.notification.event;

import javax.enterprise.event.Observes;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.notification.annotation.event.IVRNotificationCreatedEvent;

/**
 * @author Edward Banfa
 *
 */
public interface IVRNotificationEventCreatedListener {
	
	public void processIVRNotificationEventCreated(@Observes @IVRNotificationCreatedEvent NotificationEvent event) throws ApplicationException;

}
