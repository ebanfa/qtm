/**
 * 
 */
package com.nathanclaire.alantra.customer.service.process;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.nathanclaire.alantra.base.service.process.BaseProcessService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.StringUtil;
import com.nathanclaire.alantra.customer.model.Account;
import com.nathanclaire.alantra.customer.model.Customer;
import com.nathanclaire.alantra.customer.model.CustomerAccount;
import com.nathanclaire.alantra.customer.service.entity.AccountService;
import com.nathanclaire.alantra.customer.service.entity.CustomerService;

/**
 * @author Edward Banfa 
 *
 */
@Stateless
public class CustomerProcessingServiceImpl extends BaseProcessService implements CustomerProcessingService {

	@Inject AccountService accountService;
	@Inject CustomerService customerService;
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.process.CustomerProcessingService#findSingleCustomerBySourceAddress(java.lang.String)
	 */
	@Override
	public Customer findSingleCustomerBySourceAddress(String sourceAddress) throws ApplicationException {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.process.CustomerProcessingService#getCustomerById(java.lang.Integer)
	 */
	@Override
	public Customer getCustomerById(Integer customerId) throws ApplicationException {
		Customer customer = customerService.findById(customerId);
		if(customer == null)
			throw new ApplicationException(CUSTOMER_NOT_FOUND);
		return customer;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.process.CustomerProcessingService#verifyCustomerContact(com.nathanclaire.alantra.customer.model.Customer, java.lang.String)
	 */
	@Override
	public boolean verifyCustomerContact(Customer customer, String sourceAddress)	throws ApplicationException 
	{
		if(StringUtil.isValidString(StringUtil.match(customer.getMobile(), sourceAddress, false)))
			return true;
		if(StringUtil.isValidString(StringUtil.match(customer.getEmail(), sourceAddress, false)))
			return true;
		return false;
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.process.CustomerProcessingService#getAccount(java.lang.String)
	 */
	@Override
	public Account getAccount(String accountNo) throws ApplicationException {
		Account account = accountService.findByCode(accountNo);
		if(account == null) 
			throw new ApplicationException(CUSTOMER_ACCOUNT_DOES_NOT_EXIST);
		return account;
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.process.CustomerProcessingService#getFirstCustomerWithAccount(java.lang.String)
	 */
	@Override
	public Customer getFirstCustomerWithAccount(String accountNo) throws ApplicationException
	{
		Account account = getAccount(accountNo);
		Customer customer = null;
		for(CustomerAccount accountMapping : account.getCustomerAccounts())
		{
			customer = accountMapping.getCustomer();
		}
		if(customer == null)
			throw new ApplicationException(CUSTOMER_NOT_FOUND);
		return customer;
	}
	/**
	 * @param customer 
	 * @param accounts
	 * @return
	 * @throws ApplicationException
	 */
	@Override
	public Account getDefaultCustomerAccount(Customer customer, List<Account> accounts)
			throws ApplicationException {
		if(accounts.isEmpty())
			throw new ApplicationException(CUSTOMER_DEFAULT_ACCOUNT_DOES_NOT_EXIST);
		Account account = accounts.get(0);
		return account;
	}
}
