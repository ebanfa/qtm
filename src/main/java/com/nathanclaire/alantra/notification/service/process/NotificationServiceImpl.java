/**
 * 
 */
package com.nathanclaire.alantra.notification.service.process;

import java.util.List;
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
import com.nathanclaire.alantra.datasource.model.DataChannel;
import com.nathanclaire.alantra.notification.annotation.event.NotificationCreatedEvent;
import com.nathanclaire.alantra.notification.annotation.event.SMSNotificationCreatedEvent;
import com.nathanclaire.alantra.notification.event.NotificationEvent;
import com.nathanclaire.alantra.notification.model.CustomerNotification;
import com.nathanclaire.alantra.notification.model.NotificationType;
import com.nathanclaire.alantra.notification.model.SystemUserNotification;
import com.nathanclaire.alantra.notification.model.Template;
import com.nathanclaire.alantra.notification.request.CustomerNotificationRequest;
import com.nathanclaire.alantra.notification.request.SystemUserNotificationRequest;
import com.nathanclaire.alantra.notification.service.entity.CustomerNotificationService;
import com.nathanclaire.alantra.notification.service.entity.NotificationTypeService;
import com.nathanclaire.alantra.notification.service.entity.SystemUserNotificationService;
import com.nathanclaire.alantra.notification.service.entity.TemplateTypeTagService;
import com.nathanclaire.alantra.notification.util.FilledTemplate;
import com.nathanclaire.alantra.security.model.SystemUser;
import com.nathanclaire.alantra.security.service.process.SecurityModuleService;

/**
 * @author Edward Banfa 
 * **** ALWAYS CHECK IF THE GIVEN TEMPLATE TAG VALUES ARE NULL
 *      AND FOR CASES WHERE THE NOTIFCATION TYPE DOES NOT HAVE TEMPLATE
 *      TAGS.
 */
@Stateless
public class NotificationServiceImpl extends BaseProcessService implements NotificationService {

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.notification.service.process.NotificationService#createCustomerNotification(com.nathanclaire.alantra.notification.model.NotificationType, com.nathanclaire.alantra.customer.model.Customer, com.nathanclaire.alantra.datasource.model.DataChannel, java.util.Map)
	 */
	@Override
	public void createCustomerNotification(NotificationType notificationType,
			Customer customer, DataChannel channel,
			Map<String, String> templateTageValues) throws ApplicationException {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.notification.service.process.NotificationService#createUserNotification(com.nathanclaire.alantra.notification.model.NotificationType, com.nathanclaire.alantra.security.model.SystemUser, com.nathanclaire.alantra.datasource.model.DataChannel, java.util.Map)
	 */
	@Override
	public void createUserNotification(NotificationType notificationType,
			SystemUser user, DataChannel channel,
			Map<String, String> templateTageValues) throws ApplicationException {
		// TODO Auto-generated method stub
		
	}

	

}
