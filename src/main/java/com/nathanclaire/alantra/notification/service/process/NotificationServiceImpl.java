/**
 * 
 */
package com.nathanclaire.alantra.notification.service.process;

import java.util.Map;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.service.process.BaseProcessService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtils;
import com.nathanclaire.alantra.customer.model.Customer;
import com.nathanclaire.alantra.notification.annotation.CustomerNotificationCreatedEvent;
import com.nathanclaire.alantra.notification.event.NotificationEvent;
import com.nathanclaire.alantra.notification.model.CustomerNotification;
import com.nathanclaire.alantra.notification.model.NotificationType;
import com.nathanclaire.alantra.notification.model.Template;
import com.nathanclaire.alantra.notification.request.CustomerNotificationRequest;
import com.nathanclaire.alantra.notification.service.entity.CustomerNotificationService;
import com.nathanclaire.alantra.notification.service.entity.NotificationTypeService;
import com.nathanclaire.alantra.notification.util.FilledTemplate;
import com.nathanclaire.alantra.security.model.SystemUser;

/**
 * @author Edward Banfa 
 * **** ALWAYS CHECK IF THE GIVEN TEMPLATE TAG VALUES ARE NULL
 *      AND FOR CASES WHERE THE NOTIFCATION TYPE DOES NOT HAVE TEMPLATE
 *      TAGS.
 */
@Stateless
public class NotificationServiceImpl extends BaseProcessService implements NotificationService {

	@Inject NotificationTypeService notificationTypeService;
	@Inject TemplateProcessingService templateProcessingService;
	@Inject CustomerNotificationService customerNotificationService;
	@Inject @CustomerNotificationCreatedEvent Event<NotificationEvent> customerNotificationCreatedEvent;
	private Logger logger = LoggerFactory.getLogger(NotificationServiceImpl.class);
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.notification.service.process.NotificationService#notifyCustomer(com.nathanclaire.alantra.customer.model.Customer, com.nathanclaire.alantra.notification.model.NotificationType, java.util.Map)
	 */
	@Override
	public void notifyCustomer(Customer customer, String notificationTypeCode,	 Map<String, String> templateTagValues) 
			throws ApplicationException {
		try {
			CustomerNotificationRequest notificationRequest = new CustomerNotificationRequest();
			PropertyUtils.initializeBaseFields(notificationRequest);
			// 1. Get the notification type
			NotificationType notificationType = getNotificationType(notificationTypeCode);
			notificationRequest.setCustomerId(customer.getId());
			notificationRequest.setNotificationTypeId(notificationType.getId());
			notificationRequest.setCode(getCurrentTimeInMilliSeconds().toString());
			notificationRequest.setName(notificationType.getName());
			// Get and fill the template
			Template template = notificationType.getTemplate();
			FilledTemplate filledTemplate = templateProcessingService.fillTemplate(template, templateTagValues);
			// Create notification and fire the event to signal we have a new customer notification
			CustomerNotification notification = customerNotificationService.create(notificationRequest);
			customerNotificationCreatedEvent.fire(new NotificationEvent(notificationType.getCode(), 
					notification.getId(), customer.getId(), filledTemplate.getHeader(), filledTemplate.getBody()));
		} catch (Exception e) {
			throw new ApplicationException(CREATE_CUST_NOTIFICATION_ERROR, e.getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.notification.service.process.NotificationService#notifyUser(com.nathanclaire.alantra.security.model.SystemUser, com.nathanclaire.alantra.notification.model.NotificationType, java.util.Map)
	 */
	@Override
	public void notifyUser(SystemUser user, String notificationType,
			Map<String, String> templateTagValues) throws ApplicationException {
		// TODO Use the above method as a template when it has been tested
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.notification.service.process.NotificationService#notifyAdmin(java.lang.String, java.util.Map)
	 */
	@Override
	public void notifyAdmin(String notificationType, Map<String, String> templateTagValues) throws ApplicationException {
		// TODO Auto-generated method stub
		logger.debug("Sending notification {} to admin user. Using template tag values {}", notificationType, templateTagValues);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.notification.service.process.NotificationService#getNotificationType(java.lang.String)
	 */
	@Override
	public NotificationType getNotificationType(String notificationTypeCode) throws ApplicationException {
		// TODO Auto-generated method stub
		NotificationType notificationType = notificationTypeService.findByCode(notificationTypeCode);
		if(notificationType == null)
			throw new ApplicationException(NOTIFICATION_TYPE_NOT_FIND);
		return notificationType;
	}

	

}
