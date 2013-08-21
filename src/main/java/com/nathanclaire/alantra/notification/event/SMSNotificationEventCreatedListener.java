/**
 * 
 */
package com.nathanclaire.alantra.notification.event;

import javax.enterprise.event.Observes;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.notification.annotation.event.SMSNotificationEventCreated;

/**
 * @author Edward Banfa 
 *
 */
public interface SMSNotificationEventCreatedListener {
	

	public void processSMSNotificationEventCreated(@Observes @SMSNotificationEventCreated NotificationEvent event)
			throws ApplicationException;

}
