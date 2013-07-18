/**
 * 
 */
package com.nathanclaire.alantra.notification.event;

import java.util.List;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.customer.service.process.CustomerChannelProcessingService;
import com.nathanclaire.alantra.customer.service.process.CustomerProcessingService;
import com.nathanclaire.alantra.datasource.model.DataChannel;
import com.nathanclaire.alantra.messaging.service.process.MessagingModuleService;
import com.nathanclaire.alantra.notification.annotation.SystemUserNotificationCreatedEvent;
import com.nathanclaire.alantra.notification.service.process.MessageMappingProcessingService;
import com.nathanclaire.alantra.notification.service.process.NotificationService;
import com.nathanclaire.alantra.security.model.SystemUser;
import com.nathanclaire.alantra.security.service.process.SecurityModuleService;

/**
 * @author Edward Banfa 
 *
 */
public class SystemUserNotificationEventListenerImpl implements
		SystemUserNotificationEventListener {

	@Inject NotificationService notificationService;
	@Inject SecurityModuleService securityModuleService;
	@Inject MessagingModuleService messagingModuleService;
	@Inject MessageMappingProcessingService messageMappingProcessingService;
	private Logger logger = LoggerFactory.getLogger(SystemUserNotificationEventListenerImpl.class);

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.notification.event.SystemUserNotificationEventListener#processNotificationCreated(com.nathanclaire.alantra.notification.event.NotificationEvent)
	 */
	@Override
	public void processNotificationCreated(
			@Observes @SystemUserNotificationCreatedEvent NotificationEvent event)
			throws ApplicationException {
		try {
			SystemUser user = securityModuleService.getUserById(event.getUserId());
			logger.debug("Processing notification creation event for user {}", user);
			List<DataChannel> channels = securityModuleService.getSystemUserOutboundChannels(user);
			logger.debug("Found {} outbound communication channels for user {}", channels.size(), user);
			messagingModuleService.sendUserMessage(user, channels, event.getHeaderText(), event.getBodyText());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}		
	}

}
