/**
 * 
 */
package com.nathanclaire.alantra.notification.event;

import javax.inject.Inject;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.customer.service.entity.CustomerService;
import com.nathanclaire.alantra.customer.service.process.CustomerContactService;
import com.nathanclaire.alantra.messaging.util.MessageLite;
import com.nathanclaire.alantra.security.service.entity.SystemUserService;
import com.nathanclaire.alantra.security.service.process.SystemUserContactService;

/**
 * @author Edward Banfa
 *
 */
public class BaseNotificationEventListener {
	
	@Inject SystemUserService userService;
	@Inject CustomerService customerService;

	@Inject CustomerContactService customerContactService;
	@Inject SystemUserContactService systemUserContactService;
	
	protected String getUserMobileNo(Integer userId) throws ApplicationException {
		return systemUserContactService.getUserMobileNo(userService.findById(userId));
	}

	protected String getCustomerMobileNo(Integer customerId) throws ApplicationException {
		return customerContactService.getPrimaryMobileNo(customerService.findById(customerId));
	}
	
	protected String getUserEmailAddress(Integer userId) throws ApplicationException {
		return systemUserContactService.getUserEmailAddress(userService.findById(userId));
	}

	protected String getCustomerEmailAddress(Integer customerId) throws ApplicationException {
		return customerContactService.getPrimaryEmailAddress(customerService.findById(customerId));
	}
	
	
	protected MessageLite initializeMessageLite(String recipient, String sender, String subject, String text)
	{
		return new MessageLite(recipient, sender, subject, text, false);
	}

}
