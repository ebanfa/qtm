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
public class EMAILNotificationEventCreatedListenerImpl implements
		EMAILNotificationEventCreatedListener {

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.notification.event.EMAILNotificationEventCreatedListener#processEMAILNotificationEventCreatedEvent(com.nathanclaire.alantra.notification.event.NotificationEvent)
	 */
	@Override
	public void processEMAILNotificationEventCreatedEvent(
			@Observes @EMAILNotificationCreatedEvent NotificationEvent event) {
		// TODO Auto-generated method stub
		
	}

}
