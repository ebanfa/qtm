/**
 * 
 */
package com.nathanclaire.alantra.notification.event;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.datasource.model.DataChannel;
import com.nathanclaire.alantra.datasource.service.entity.DataChannelService;
import com.nathanclaire.alantra.messaging.messenger.MessengerService;
import com.nathanclaire.alantra.messaging.messenger.MessengerServiceLocator;
import com.nathanclaire.alantra.messaging.util.MessageLite;
import com.nathanclaire.alantra.notification.annotation.event.IVRNotificationCreatedEvent;

/**
 * @author Edward Banfa
 *
 */
public class IVRNotificationEventCreatedListenerImpl extends BaseNotificationEventListener implements
		IVRNotificationEventCreatedListener {
	private static final String APPL_NAME = "IVR Notifier";
	@Inject DataChannelService channelService;
	@Inject MessengerServiceLocator serviceLocator;
	private Logger logger = LoggerFactory.getLogger(getClass());

	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.notification.event.IVRNotificationEventCreatedListener#processIVRNotificationEventCreated(com.nathanclaire.alantra.notification.event.NotificationEvent)
	 */
	@Override   
	public void processIVRNotificationEventCreated(@Observes @IVRNotificationCreatedEvent NotificationEvent event) throws ApplicationException 
	{
		try 
		{
			String mobileNo = null;
			DataChannel channel = channelService.findById(event.getChannelId());
			MessengerService messengerService = serviceLocator.findMessengerService(channel);
			// The mobile number will either that of a customer or that of a user
			// depending on the recipient type
			if(event.getRecipientType().equals(NotificationEvent.CUST_RECIPIENT))
				mobileNo = getCustomerMobileNo(event.getCustomerId());
			else
				mobileNo = getUserMobileNo(event.getUserId());
			// Initialize the message and send using the messenger
			MessageLite message = initializeMessageLite(mobileNo, APPL_NAME, event.getHeaderText(), event.getBodyText());
			messengerService.sendMessage(channel, message);
		} catch (Exception e) {
			logger.error("Error processing IVR notification created event. {}", e.getMessage());
		}		
	}

}
