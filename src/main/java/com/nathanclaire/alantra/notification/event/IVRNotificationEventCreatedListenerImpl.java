/**
 * 
 */
package com.nathanclaire.alantra.notification.event;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.customer.model.Customer;
import com.nathanclaire.alantra.customer.service.entity.CustomerService;
import com.nathanclaire.alantra.datasource.model.DataChannel;
import com.nathanclaire.alantra.datasource.service.entity.DataChannelEntityService;
import com.nathanclaire.alantra.messaging.messenger.MessengerService;
import com.nathanclaire.alantra.messaging.messenger.MessengerServiceLocator;
import com.nathanclaire.alantra.messaging.model.MessageType;
import com.nathanclaire.alantra.messaging.service.entity.MessageTypeService;
import com.nathanclaire.alantra.messaging.service.process.MessagingService;
import com.nathanclaire.alantra.messaging.util.MessageLite;
import com.nathanclaire.alantra.notification.annotation.event.IVRNotificationCreatedEvent;
import com.nathanclaire.alantra.security.model.SystemUser;
import com.nathanclaire.alantra.security.service.entity.SystemUserService;

/**
 * @author Edward Banfa
 *
 */
public class IVRNotificationEventCreatedListenerImpl extends BaseNotificationEventListener implements
		IVRNotificationEventCreatedListener {

	@Inject SystemUserService userService;
	@Inject CustomerService customerService;
	@Inject MessagingService messagingService;
	@Inject DataChannelEntityService channelService;
	private Logger logger = LoggerFactory.getLogger(getClass());

	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.notification.event.IVRNotificationEventCreatedListener#processIVRNotificationEventCreated(com.nathanclaire.alantra.notification.event.NotificationEvent)
	 */
	@Override   
	public void processIVRNotificationEventCreated(@Observes @IVRNotificationCreatedEvent NotificationEvent event) throws ApplicationException 
	{
		try 
		{
			DataChannel channel = channelService.findById(event.getChannelId());
			
			if(event.getRecipientType().equals(NotificationEvent.CUST_RECIPIENT)) {
				Customer customer = customerService.findById(event.getCustomerId());
				messagingService.createOutboundCustMsg(customer, channel, event.getHeaderText(), event.getBodyText());
			}
			else {
				SystemUser user = userService.findById(event.getCustomerId());
				messagingService.createOutboundUserMsg(user, channel, event.getHeaderText(), event.getBodyText());
			}
		} catch (Exception e) {
			logger.error("Error processing IVR notification created event. {}", e.getMessage());
		}	
	}

}
