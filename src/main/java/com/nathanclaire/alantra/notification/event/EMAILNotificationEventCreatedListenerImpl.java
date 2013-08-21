/**
 * 
 */
package com.nathanclaire.alantra.notification.event;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.datasource.model.DataChannel;
import com.nathanclaire.alantra.datasource.service.entity.DataChannelService;
import com.nathanclaire.alantra.messaging.messenger.MessengerService;
import com.nathanclaire.alantra.messaging.messenger.MessengerServiceLocator;
import com.nathanclaire.alantra.messaging.util.MessageLite;
import com.nathanclaire.alantra.notification.annotation.event.EMAILNotificationEventCreated;

/**
 * @author Edward Banfa
 *
 */
public class EMAILNotificationEventCreatedListenerImpl extends BaseNotificationEventListener implements
		EMAILNotificationEventCreatedListener {
	
	@Inject DataChannelService channelService;
	@Inject MessengerServiceLocator serviceLocator;
	private static final String APPL_NAME = "EMAIL Notifier";
	private Logger logger = LoggerFactory.getLogger(getClass());

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.notification.event.EMAILNotificationEventCreatedListener#processEMAILNotificationEventCreatedEvent(com.nathanclaire.alantra.notification.event.NotificationEvent)
	 */
	@Override
	public void processEMAILNotificationEventCreated(@Observes @EMAILNotificationEventCreated NotificationEvent event) {
		try 
		{
			String emailAddress = null;
			DataChannel channel = channelService.findById(event.getChannelId());
			MessengerService messengerService = serviceLocator.findMessengerService(channel);
			// The mobile number will either that of a customer or that of a user
			// depending on the recipient type
			if(event.getRecipientType().equals(NotificationEvent.CUST_RECIPIENT))
				emailAddress = getCustomerEmailAddress(event.getCustomerId());
			else
				emailAddress = getUserEmailAddress(event.getUserId());
			// Initialize the message and send using the messenger
			MessageLite message = initializeMessageLite(emailAddress, APPL_NAME, event.getHeaderText(), event.getBodyText());
			messengerService.sendMessage(channel, message);
		} catch (Exception e) {
			logger.error("Error processing email notification created event. {}", e.getMessage());
		}		
		
	}

}
