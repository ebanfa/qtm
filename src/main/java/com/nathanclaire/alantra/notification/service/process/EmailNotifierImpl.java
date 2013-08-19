/**
 * 
 */
package com.nathanclaire.alantra.notification.service.process;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.customer.model.Customer;
import com.nathanclaire.alantra.notification.annotation.EmailNotifier;
import com.nathanclaire.alantra.notification.model.NotificationType;
import com.nathanclaire.alantra.security.model.SystemUser;

/**
 * @author Edward Banfa
 *
 */
@EmailNotifier
public class EmailNotifierImpl implements Notifier {

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.notification.service.process.Notifier#notifyCustomer(com.nathanclaire.alantra.customer.model.Customer, com.nathanclaire.alantra.notification.model.NotificationType)
	 */
	@Override
	public void notifyCustomer(Customer customer, NotificationType notificationType) throws ApplicationException {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.notification.service.process.Notifier#notifyUser(com.nathanclaire.alantra.security.model.SystemUser, com.nathanclaire.alantra.notification.model.NotificationType)
	 */
	@Override
	public void notifyUser(SystemUser user, NotificationType notificationType) throws ApplicationException {
		// TODO Auto-generated method stub
		
	}

}
