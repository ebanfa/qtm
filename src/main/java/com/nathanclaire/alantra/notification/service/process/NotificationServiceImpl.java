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
 * **** ALWAYS CHECK IF THE GIVEN TEMPLATE TAG VALUES ARE NULL
 *      AND FOR CASES WHERE THE NOTIFCATION TYPE DOES NOT HAVE TEMPLATE
 *      TAGS.
 */
public class NotificationServiceImpl implements NotificationService {

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.notification.service.process.NotificationService#notifyCustomer(com.nathanclaire.alantra.customer.model.Customer, com.nathanclaire.alantra.notification.model.NotificationType, java.util.Map)
	 */
	@Override
	public void notifyCustomer(Customer customer, String notificationType,
			Map<String, String> templateTagValues) throws ApplicationException {
		
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.notification.service.process.NotificationService#notifyUser(com.nathanclaire.alantra.security.model.SystemUser, com.nathanclaire.alantra.notification.model.NotificationType, java.util.Map)
	 */
	@Override
	public void notifyUser(SystemUser user, String notificationType,
			Map<String, String> templateTagValues) throws ApplicationException {
		
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.notification.service.process.NotificationService#notifyAdmin(java.lang.String, java.util.Map)
	 */
	@Override
	public void notifyAdmin(String notificationType, Map<String, String> templateTagValues) throws ApplicationException {
		// TODO Auto-generated method stub
		
	}

	

}
