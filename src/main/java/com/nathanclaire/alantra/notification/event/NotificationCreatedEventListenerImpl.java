/**
 * 
 */
package com.nathanclaire.alantra.notification.event;

import javax.ejb.Stateless;
import javax.enterprise.event.Observes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.notification.annotation.event.NotificationCreatedEvent;

/**
 * @author Edward Banfa 
 *
 */
@Stateless
public class NotificationCreatedEventListenerImpl implements
		NotificationCreatedEventListener {

	private Logger logger = LoggerFactory.getLogger(getClass());

	/* (non-Javadoc)
	 *  
	 * @see com.nathanclaire.alantra.notification.event.CustomerNotificationEventListener#processNotificationCreated(com.nathanclaire.alantra.notification.event.NotificationEvent)
	 */
	@Override
	public void processNotificationCreated(@Observes @NotificationCreatedEvent NotificationEvent event)
			throws ApplicationException {
		try 
		{
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

}
