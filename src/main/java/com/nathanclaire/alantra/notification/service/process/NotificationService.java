/**
 * 
 */
package com.nathanclaire.alantra.notification.service.process;

import java.util.Map;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.customer.model.Customer;
import com.nathanclaire.alantra.security.model.SystemUser;

/**
 * @author Edward Banfa 
 *
 */
public interface NotificationService {
	
	/**
	 * @param customer
	 * @param notificationType
	 * @param templateTagValues
	 * @throws ApplicationException
	 */
	public void notifyCustomer(Customer customer, String notificationType, 
			Map<String, String> templateTagValues) throws ApplicationException;
	
	/**
	 * @param user
	 * @param notificationType
	 * @param templateTagValues
	 * @throws ApplicationException
	 */
	public void notifyUser(SystemUser user, String notificationType, 
			Map<String, String> templateTagValues) throws ApplicationException;
	
	/**
	 * @param notificationType
	 * @param templateTagValues
	 * @throws ApplicationException
	 */
	public void notifyAdmin(String notificationType, Map<String, String> templateTagValues) throws ApplicationException;


}
