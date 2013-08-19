/**
 * 
 */
package com.nathanclaire.alantra.notification.event;

import javax.enterprise.event.Observes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.notification.annotation.event.SMSNotificationCreatedEvent;

/**
 * @author Edward Banfa 
 *
 */
public class SMSNotificationEventCreatedListenerImpl implements
		SMSNotificationEventCreatedListener {

	private Logger logger = LoggerFactory.getLogger(getClass());

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.notification.event.SMSNotificationEventCreatedListener#processNotificationCreated(com.nathanclaire.alantra.notification.event.NotificationEvent)
	 */
	@Override
	public void processNotificationCreated(@Observes @SMSNotificationCreatedEvent NotificationEvent event) throws ApplicationException 
	{
		try 
		{

		} catch (Exception e) {
			logger.error(e.getMessage());
		}		
	}

}
