/**
 * 
 */
package com.nathanclaire.alantra.notification.service.process;

import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.customer.model.Customer;
import com.nathanclaire.alantra.customer.service.process.CustomerCommsChannelService;
import com.nathanclaire.alantra.datasource.model.DataChannel;
import com.nathanclaire.alantra.notification.annotation.EmailNotifier;
import com.nathanclaire.alantra.notification.model.NotificationType;
import com.nathanclaire.alantra.security.model.SystemUser;

/**
 * @author Edward Banfa
 *
 */
@Stateless
@EmailNotifier
public class EmailNotifierImpl extends BaseNotifier implements Notifier {
	
	@Inject CustomerCommsChannelService customerCommsChannelService;

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.notification.service.process.Notifier#notifyCustomer(com.nathanclaire.alantra.customer.model.Customer, com.nathanclaire.alantra.notification.model.NotificationType)
	 */
	@Override
	public void notifyCustomer(Customer customer, NotificationType notificationType, 
			Map<String, String> templateTagValues) throws ApplicationException {
		DataChannel channel = customerCommsChannelService.getDefaultCustomerSMSChannel(customer);
		this.createCustomerNotification(notificationType, customer, channel, templateTagValues);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.notification.service.process.Notifier#notifyUser(com.nathanclaire.alantra.security.model.SystemUser, com.nathanclaire.alantra.notification.model.NotificationType)
	 */
	@Override
	public void notifyUser(SystemUser user, NotificationType notificationType, 
			Map<String, String> templateTagValues) throws ApplicationException {
		// TODO Auto-generated method stub
		
	}

}
