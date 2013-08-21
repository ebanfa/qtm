/**
 * 
 */
package com.nathanclaire.alantra.notification.service.process;

import java.util.Map;

import javax.enterprise.event.Event;
import javax.inject.Inject;

import com.nathanclaire.alantra.base.service.process.BaseProcessService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtils;
import com.nathanclaire.alantra.customer.model.Customer;
import com.nathanclaire.alantra.datasource.model.DataChannel;
import com.nathanclaire.alantra.datasource.model.DataChannelCategory;
import com.nathanclaire.alantra.notification.annotation.event.NotificationCreatedEvent;
import com.nathanclaire.alantra.notification.event.NotificationEvent;
import com.nathanclaire.alantra.notification.model.CustomerNotification;
import com.nathanclaire.alantra.notification.model.NotificationType;
import com.nathanclaire.alantra.notification.model.Template;
import com.nathanclaire.alantra.notification.request.CustomerNotificationRequest;
import com.nathanclaire.alantra.notification.service.entity.CustomerNotificationService;
import com.nathanclaire.alantra.notification.service.entity.NotificationTypeService;
import com.nathanclaire.alantra.notification.service.entity.SystemUserNotificationService;
import com.nathanclaire.alantra.notification.util.FilledTemplate;
import com.nathanclaire.alantra.security.model.SystemUser;

/**
 * @author Edward Banfa 
 * **** ALWAYS CHECK IF THE GIVEN TEMPLATE TAG VALUES ARE NULL
 *      AND FOR CASES WHERE THE NOTIFCATION TYPE DOES NOT HAVE TEMPLATE
 *      TAGS.
 */
public class BaseNotifier extends BaseProcessService{
	
	private static final String CREATE_CUST_NOTIFICATION_ERROR = null;
	@Inject NotificationTypeService notificationTypeService;
	@Inject TemplatingService templateProcessingService;
	@Inject SystemUserNotificationService userNotificationService;
	@Inject CustomerNotificationService customerNotificationService;
	@Inject @NotificationCreatedEvent Event<NotificationEvent> customerNotificationCreatedEvent;

	/**/
	public void createCustomerNotification(NotificationType notificationType,
			Customer customer, DataChannel channel,	Map<String, String> templateTagValues) throws ApplicationException {
		try {
			CustomerNotificationRequest notificationRequest = new CustomerNotificationRequest();
			PropertyUtils.initializeBaseFields(notificationRequest);
			// 1. Get the notification type
			notificationRequest.setCustomerId(customer.getId());
			notificationRequest.setNotificationTypeId(notificationType.getId());
			notificationRequest.setCode(getCurrentTimeInMilliSeconds().toString());
			notificationRequest.setName(notificationType.getName());
			// Get and fill the template
			Template template = notificationType.getTemplate();
			FilledTemplate filledTemplate = templateProcessingService.fillTemplate(template, templateTagValues);
			// Create notification and fire the event to signal we have a new customer notification
			CustomerNotification notification = customerNotificationService.create(notificationRequest);
			NotificationEvent event = initializeCustomerNotificationEvent(
					notification, customer, channel, filledTemplate);
			customerNotificationCreatedEvent.fire(event);
		} catch (Exception e) {
			throw new ApplicationException(CREATE_CUST_NOTIFICATION_ERROR, e.getMessage());
		}
		
	}

	private NotificationEvent initializeCustomerNotificationEvent(
			CustomerNotification notification, Customer customer,
			DataChannel channel, FilledTemplate filledTemplate) {
		NotificationEvent event = new NotificationEvent();
		event.setBodyText(filledTemplate.getBody());
		event.setHeaderText(filledTemplate.getHeader());
		event.setChannelId(channel.getId());
		event.setNotificationId(notification.getId());
		event.setNotificationTypeCode(notification.getNotificationType().getCode());
		event.setCustomerId(customer.getId());
		event.setChannelCatCode(getDataChannelCategory(channel).getCode());
		event.setRecipientType(NotificationEvent.CUST_RECIPIENT);
		return event;
	}

	/**/
	public void createUserNotification(NotificationType notificationType,
			SystemUser user, DataChannel channel,
			Map<String, String> templateTageValues) throws ApplicationException {
		// TODO Auto-generated method stub
		
	}

	

}
