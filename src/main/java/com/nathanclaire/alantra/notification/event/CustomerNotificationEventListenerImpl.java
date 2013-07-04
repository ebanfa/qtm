/**
 * 
 */
package com.nathanclaire.alantra.notification.event;

import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.customer.model.Customer;
import com.nathanclaire.alantra.customer.service.process.CustomerChannelProcessingService;
import com.nathanclaire.alantra.customer.service.process.CustomerProcessingService;
import com.nathanclaire.alantra.datasource.model.DataChannel;
import com.nathanclaire.alantra.messaging.model.MessageApplicationAction;
import com.nathanclaire.alantra.messaging.model.MessageType;
import com.nathanclaire.alantra.messaging.service.process.MessagingModuleService;
import com.nathanclaire.alantra.notification.annotation.CustomerNotificationCreatedEvent;
import com.nathanclaire.alantra.notification.model.NotificationType;
import com.nathanclaire.alantra.notification.service.process.MessageMappingProcessingService;
import com.nathanclaire.alantra.notification.service.process.NotificationService;

/**
 * @author Edward Banfa 
 *
 */
@Stateless
public class CustomerNotificationEventListenerImpl implements
		CustomerNotificationEventListener {

	@Inject MessagingModuleService messagingModuleService;
	@Inject NotificationService notificationService;
	@Inject CustomerProcessingService customerProcessingService;
	@Inject CustomerChannelProcessingService channelProcessingService;
	@Inject MessageMappingProcessingService messageMappingProcessingService;
	private Logger logger = LoggerFactory.getLogger(CustomerNotificationEventListenerImpl.class);

	/* (non-Javadoc)
	 *  
	 * @see com.nathanclaire.alantra.notification.event.CustomerNotificationEventListener#processNotificationCreated(com.nathanclaire.alantra.notification.event.NotificationEvent)
	 */
	@Override
	public void processNotificationCreated(@Observes @CustomerNotificationCreatedEvent NotificationEvent event)
			throws ApplicationException {
		// 1. Get the customer
		// 2. Get the channels for the customer and message type
		// 3. Call message service to send message to customer
		try {
			Customer customer = customerProcessingService.getCustomerById(event.getUserId());
			logger.debug("Processing notification creation event for customer {}", customer.getName());
			List<DataChannel> channels = channelProcessingService.getCustomerChannels(customer);
			//NotificationType notificationType = notificationService.getNotificationType(event.getNotificationTypeCode());
			
			//MessageApplicationAction messageApplicationAction = messageMappingProcessingService.getMessageType(notificationType);
			messagingModuleService.sendCustomerMessage(customer, channels, event.getHeaderText(), event.getBodyText());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

}
