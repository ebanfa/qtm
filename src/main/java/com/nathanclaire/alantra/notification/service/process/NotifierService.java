/**
 * 
 */
package com.nathanclaire.alantra.notification.service.process;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.customer.model.Customer;
import com.nathanclaire.alantra.notification.model.NotificationType;
import com.nathanclaire.alantra.security.model.SystemUser;

/**
 * @author Edward Banfa
 *
 */
public interface NotifierService {
	
	public static final String NOTIFICATION_CHANNEL_TYPE_SMS = "SMS";
	public static final String NOTIFICATION_CHANNEL_TYPE_IVR = "IVR";
	public static final String NOTIFICATION_CHANNEL_TYPE_ALL = "ALL";
	public static final String NOTIFICATION_CHANNEL_TYPE_EMAIL = "EMAIL";
	
	public void sendCustomerNotification(Customer customer, 
			NotificationType notificationType, String channelType) throws ApplicationException;
	
	public void sendUserNotification(SystemUser user, 
			NotificationType notificationType, String channelType) throws ApplicationException;
}
