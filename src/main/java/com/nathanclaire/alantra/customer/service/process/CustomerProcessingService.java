/**
 * 
 */
package com.nathanclaire.alantra.customer.service.process;

import java.util.List;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.customer.model.Account;
import com.nathanclaire.alantra.customer.model.Customer;
import com.nathanclaire.alantra.messaging.model.MessageType;

/**
 * @author Edward Banfa 
 *
 */
public interface CustomerProcessingService {
	
	public static final String PRIM_PHONE_CRITERIA = "primaryMobile";
	public static final String SEC_PHONE_CRITERIA = "secondaryMobile";
	public static final String PRIM_EMAIL_CRITERIA = "primaryEmail";
	public static final String SEC_EMAIL_CRITERIA = "secondaryEmail";

	public static final String CUSTOMER_DEFAULT_ACCOUNT_DOES_NOT_EXIST =
			"CustomerProcessingService.CUSTOMER_DEFAULT_ACCOUNT_DOES_NOT_EXIST";

	public static final String CUST_NOT_FOUND_INVALID_MESSAGE_TYPE_SPECIFIED = 
			"CustomerProcessingService.CUST_NOT_FOUND_INVALID_MESSAGE_TYPE_SPECIFIED";
	
	public static final String CONFIG_ERROR_CUST_NOT_FOUND_INVALID_MESSAGE_CAT_SPECIFIED = 
			"CustomerProcessingService.CONFIG_ERROR_CUST_NOT_FOUND_INVALID_MESSAGE_CAT_SPECIFIED";
	
	public static final String CUSTOMER_NOT_FOUND = "CustomerProcessingService.CUSTOMER_NOT_FOUND";
	public static final String CUSTOMER_ACCOUNT_DOES_NOT_EXIST = "CustomerProcessingService.CUSTOMER_ACCOUNT_DOES_NOT_EXIST";
	
	
	
	public Customer findSingleCustomerBySourceAddress(MessageType messageType, String sourceAddress) throws ApplicationException;
	
	public Customer getCustomerById(Integer customerId) throws ApplicationException;

	public boolean verifyCustomerContact(Customer customer, String sourceAddress) throws ApplicationException;

	public Account getAccount(String accountNo) throws ApplicationException;

	public Customer getFirstCustomerWithAccount(String accountNo) throws ApplicationException;

	public Account getDefaultCustomerAccount(Customer customer, List<Account> accounts) throws ApplicationException;

	public Account getDefaultCustomerAccount(Customer customer) throws ApplicationException;
}
