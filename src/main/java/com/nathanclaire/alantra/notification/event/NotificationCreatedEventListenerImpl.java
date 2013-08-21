/**
 * 
 */
package com.nathanclaire.alantra.notification.event;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.datasource.service.entity.DataChannelCategoryService;
import com.nathanclaire.alantra.notification.annotation.event.EMAILNotificationEventCreated;
import com.nathanclaire.alantra.notification.annotation.event.IVRNotificationCreatedEvent;
import com.nathanclaire.alantra.notification.annotation.event.NotificationCreatedEvent;
import com.nathanclaire.alantra.notification.annotation.event.SMSNotificationEventCreated;

/**
 * @author Edward Banfa 
 *
 */
@Stateless
public class NotificationCreatedEventListenerImpl implements
		NotificationCreatedEventListener {

	private Logger logger = LoggerFactory.getLogger(getClass());
	@Inject @SMSNotificationEventCreated Event<NotificationEvent> sMSNotificationCreatedEvent;
	@Inject @IVRNotificationCreatedEvent Event<NotificationEvent> iVRNotificationCreatedEvent;
	@Inject @EMAILNotificationEventCreated Event<NotificationEvent> eMAILNotificationCreatedEvent;


	/* (non-Javadoc)
	 *  
	 * @see com.nathanclaire.alantra.notification.event.CustomerNotificationEventListener#processNotificationCreated(com.nathanclaire.alantra.notification.event.NotificationEvent)
	 */
	@Override
	public void processNotificationCreated(@Observes @NotificationCreatedEvent NotificationEvent event)
			throws ApplicationException {
		try 
		{
			if(event.getChannelCatCode().equals(DataChannelCategoryService.SMS_DATA_CHANNEL)){
				sMSNotificationCreatedEvent.fire(new NotificationEvent());
			}
			else if(event.getChannelCatCode().equals(DataChannelCategoryService.EMAIL_DATA_CHANNEL)){
				eMAILNotificationCreatedEvent.fire(new NotificationEvent());
			}
			else if(event.getChannelCatCode().equals(DataChannelCategoryService.IVR_DATA_CHANNEL)){
				iVRNotificationCreatedEvent.fire(new NotificationEvent());
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

}
