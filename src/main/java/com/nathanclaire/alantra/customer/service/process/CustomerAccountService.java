/**
 * 
 */
package com.nathanclaire.alantra.customer.service.process;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.businessdata.model.Currency;
import com.nathanclaire.alantra.customer.model.AccountType;
import com.nathanclaire.alantra.customer.model.Customer;
import com.nathanclaire.alantra.customer.model.CustomerAccount;

/**
 * @author Edward Banfa
 *
 */
public interface CustomerAccountService {

	public AccountType getAccountType(String accountTypeCode) throws ApplicationException;
	
	public CustomerAccount getCustomerAccount(String accountCode) throws ApplicationException;
	
	public CustomerAccount getCustomerAccount(Customer customer, String accountNo) throws ApplicationException;

	public CustomerAccount getDefaultCustomerAccount(Customer customer) throws ApplicationException;
	
	public CustomerAccount createCustomerAccount(Customer customer, AccountType accountType, Currency currency, 
			String accountName, String accountNo, Character isJointFg, Character isDefaultFg) throws ApplicationException;
}
