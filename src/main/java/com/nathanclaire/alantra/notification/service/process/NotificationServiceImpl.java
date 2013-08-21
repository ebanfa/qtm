/**
 * 
 */
package com.nathanclaire.alantra.notification.service.process;

import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.customer.model.Customer;
import com.nathanclaire.alantra.notification.annotation.EmailNotifier;
import com.nathanclaire.alantra.notification.annotation.IVRNotifier;
import com.nathanclaire.alantra.notification.annotation.SMSNotifier;
import com.nathanclaire.alantra.notification.model.NotificationType;
import com.nathanclaire.alantra.security.model.SystemUser;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class NotificationServiceImpl implements NotificationService{
	
	@Inject @SMSNotifier Notifier sMSNotifier;
	@Inject @IVRNotifier Notifier iVRNotifier;
	@Inject @EmailNotifier Notifier emailNotifier;
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.notification.service.process.NotificationsService#sendCustomerNotification(com.nathanclaire.alantra.customer.model.Customer, com.nathanclaire.alantra.notification.model.NotificationType, java.lang.String)
	 */
	@Override
	public void sendCustomerNotification(Customer customer,
			NotificationType notificationType, String channelType, Map<String, String> templateTageValues) 
	{
		try 
		{
			if(channelType.equals(NotificationService.NOTIFICATION_CHANNEL_TYPE_SMS))
				sMSNotifier.notifyCustomer(customer, notificationType, templateTageValues);
			else if(channelType.equals(NotificationService.NOTIFICATION_CHANNEL_TYPE_EMAIL))
				emailNotifier.notifyCustomer(customer, notificationType, templateTageValues);
			else if(channelType.equals(NotificationService.NOTIFICATION_CHANNEL_TYPE_IVR))
				iVRNotifier.notifyCustomer(customer, notificationType, templateTageValues);
			else if(channelType.equals(NotificationService.NOTIFICATION_CHANNEL_TYPE_ALL))
			{
				sMSNotifier.notifyCustomer(customer, notificationType, templateTageValues);
				iVRNotifier.notifyCustomer(customer, notificationType, templateTageValues);
				emailNotifier.notifyCustomer(customer, notificationType, templateTageValues);
			}
		} catch (ApplicationException e) {
			logger.error("Error sending {} customer notification. {}", channelType, e.getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.notification.service.process.NotificationsService#sendUserNotification(com.nathanclaire.alantra.customer.model.Customer, com.nathanclaire.alantra.notification.model.NotificationType, java.lang.String)
	 */
	@Override
	public void sendUserNotification(SystemUser user, NotificationType notificationType, String channelType,
			Map<String, String> templateTageValues) 
	{
		try 
		{
			if(channelType.equals(NotificationService.NOTIFICATION_CHANNEL_TYPE_SMS))
				sMSNotifier.notifyUser(user, notificationType, templateTageValues);
			else if(channelType.equals(NotificationService.NOTIFICATION_CHANNEL_TYPE_EMAIL))
				emailNotifier.notifyUser(user, notificationType, templateTageValues);
			else if(channelType.equals(NotificationService.NOTIFICATION_CHANNEL_TYPE_IVR))
				iVRNotifier.notifyUser(user, notificationType, templateTageValues);
			else if(channelType.equals(NotificationService.NOTIFICATION_CHANNEL_TYPE_ALL))
			{
				sMSNotifier.notifyUser(user, notificationType, templateTageValues);
				iVRNotifier.notifyUser(user, notificationType, templateTageValues);
				emailNotifier.notifyUser(user, notificationType, templateTageValues);
			}
		} catch (ApplicationException e) {
			logger.error("Error sending {} user notification. {}", channelType, e.getMessage());
		}
	}

}
