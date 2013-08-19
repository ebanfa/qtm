/**
 * 
 */
package com.nathanclaire.alantra.notification.service.process;

import java.util.Map;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.customer.model.Customer;
import com.nathanclaire.alantra.datasource.model.DataChannel;
import com.nathanclaire.alantra.notification.model.NotificationType;
import com.nathanclaire.alantra.security.model.SystemUser;

/**
 * @author Edward Banfa 
 *
 */
public interface NotificationService {

	public void createCustomerNotification(NotificationType notificationType, Customer customer, 
			DataChannel channel, Map<String, String> templateTageValues) throws ApplicationException;
	
	public void createUserNotification(NotificationType notificationType, SystemUser user, 
			DataChannel channel, Map<String, String> templateTageValues) throws ApplicationException;
}
