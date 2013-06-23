/**
 * 
 */
package com.nathanclaire.alantra.customer.service.process;

import java.util.List;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.customer.model.Account;
import com.nathanclaire.alantra.customer.model.Customer;

/**
 * @author Edward Banfa 
 *
 */
public interface CustomerProcessingService {

	public static final String CUSTOMER_DEFAULT_ACCOUNT_DOES_NOT_EXIST =
			"CustomerProcessingService.CUSTOMER_DEFAULT_ACCOUNT_DOES_NOT_EXIST";
	public static final String CUSTOMER_NOT_FOUND = "CustomerProcessingService.CUSTOMER_NOT_FOUND";
	public static final String CUSTOMER_ACCOUNT_DOES_NOT_EXIST = "CustomerProcessingService.CUSTOMER_ACCOUNT_DOES_NOT_EXIST";
	
	public Customer findSingleCustomerBySourceAddress(String sourceAddress) throws ApplicationException;
	
	public Customer getCustomerById(Integer customerId) throws ApplicationException;

	public boolean verifyCustomerContact(Customer customer, String sourceAddress) throws ApplicationException;

	public Account getAccount(String accountNo) throws ApplicationException;

	public Customer getFirstCustomerWithAccount(String accountNo) throws ApplicationException;

	public Account getDefaultCustomerAccount(Customer customer, List<Account> accounts) throws ApplicationException;
}
