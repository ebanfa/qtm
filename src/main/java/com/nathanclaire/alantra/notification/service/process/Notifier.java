/**
 * 
 */
package com.nathanclaire.alantra.notification.service.process;

import java.util.Map;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.customer.model.Customer;
import com.nathanclaire.alantra.notification.model.NotificationType;
import com.nathanclaire.alantra.security.model.SystemUser;

/**
 * @author Edward Banfa
 *
 */
public interface Notifier {
	
	public void notifyCustomer(Customer customer, NotificationType notificationType, Map<String, String> templateTagValues) throws ApplicationException;

	public void notifyUser(SystemUser user, NotificationType notificationType, Map<String, String> templateTagValues) throws ApplicationException;

}
