/**
 * 
 */
package com.nathanclaire.alantra.notification.event;

import javax.enterprise.event.Observes;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.notification.annotation.SystemUserNotificationCreatedEvent;;

/**
 * @author Edward Banfa 
 *
 */
public interface SystemUserNotificationEventListener {
	

	public void processNotificationCreated(@Observes @SystemUserNotificationCreatedEvent NotificationEvent event)
			throws ApplicationException;

}
