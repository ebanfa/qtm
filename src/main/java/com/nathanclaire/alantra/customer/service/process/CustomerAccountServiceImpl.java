/**
 * 
 */
package com.nathanclaire.alantra.customer.service.process;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.service.process.BaseProcessService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.EntityUtil;
import com.nathanclaire.alantra.base.util.ErrorCodes;
import com.nathanclaire.alantra.base.util.ExceptionUtil;
import com.nathanclaire.alantra.base.util.PropertyUtil;
import com.nathanclaire.alantra.base.util.StringUtil;
import com.nathanclaire.alantra.businessdata.model.Currency;
import com.nathanclaire.alantra.customer.model.Account;
import com.nathanclaire.alantra.customer.model.AccountType;
import com.nathanclaire.alantra.customer.model.Customer;
import com.nathanclaire.alantra.customer.model.CustomerAccount;
import com.nathanclaire.alantra.customer.request.AccountRequest;
import com.nathanclaire.alantra.customer.request.CustomerAccountRequest;
import com.nathanclaire.alantra.customer.service.entity.AccountService;
import com.nathanclaire.alantra.customer.service.entity.AccountTypeService;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class CustomerAccountServiceImpl extends BaseProcessService implements
		CustomerAccountService {
	
	@Inject AccountService accountService;
	@Inject AccountTypeService accountTypeService;
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Inject com.nathanclaire.alantra.customer.service.entity.CustomerAccountService customerAccountService;

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.process.CustomerAccountService#getAccountType(java.lang.String)
	 */
	@Override
	public AccountType getAccountType(String accountTypeCode) throws ApplicationException {
		return (AccountType) EntityUtil.returnOrThrowIfNull(
				accountTypeService.findByCode(accountTypeCode), ErrorCodes.BPS_ENTITY_INSTANCE_NOT_FOUND_ERROR_CD, "AccountType");
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.process.CustomerAccountService#getCustomerAccount(java.lang.String)
	 */
	@Override
	public CustomerAccount getCustomerAccount(String accountCode) throws ApplicationException {
		return (CustomerAccount) EntityUtil.returnOrThrowIfNull(
				accountTypeService.findByCode(accountCode), ErrorCodes.BPS_ENTITY_INSTANCE_NOT_FOUND_ERROR_CD, "CustomerAccount");
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.process.CustomerAccountService#getCustomerAccount(com.nathanclaire.alantra.customer.model.Customer, java.lang.String)
	 */
	@Override
	public CustomerAccount getCustomerAccount(Customer customer, String accountNo) throws ApplicationException {
		Map<String, String> criteria = new HashMap<String, String>();
		criteria.put("customer.code", customer.getCode());
		criteria.put("accountNo", accountNo);
		List<CustomerAccount> accounts = customerAccountService.findByCriteria(criteria);
		if(accounts.isEmpty())
			throw new ApplicationException(ErrorCodes.CAS_ACCOUNT_ERROR_CD, ErrorCodes.CAS_CUST_HAS_NO_ACCT_ERROR_MSG);
		return accounts.get(0);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.process.CustomerAccountService#getDefaultCustomerAccount(com.nathanclaire.alantra.customer.model.Customer)
	 */
	@Override
	public CustomerAccount getDefaultCustomerAccount(Customer customer) throws ApplicationException {
		Set<CustomerAccount> accounts = customer.getCustomerAccounts();
		if(accounts.isEmpty())
			throw new ApplicationException(ErrorCodes.CAS_ACCOUNT_ERROR_CD, ErrorCodes.CAS_CUST_HAS_NO_ACCT_ERROR_MSG);
		for(CustomerAccount account : accounts)
			if(StringUtil.flagToBoolean(account.getIsDefaultFg()))
				return account;
		throw new ApplicationException(ErrorCodes.CAS_ACCOUNT_ERROR_CD, ErrorCodes.CAS_CUST_HAS_NO_DEFUALT_ACCT_ERROR_MSG);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.process.CustomerAccountService#createCustomerAccount(com.nathanclaire.alantra.customer.model.Customer, com.nathanclaire.alantra.customer.model.AccountType, java.lang.String, java.lang.String, java.lang.Character, java.lang.Character)
	 */
	@Override
	public CustomerAccount createCustomerAccount(Customer customer, AccountType accountType, Currency currency, 
			String accountName, String accountNo, Character isJointFg, Character isDefaultFg) throws ApplicationException {
		// Debug and validate the provided parameters
		logger.debug("Creating advice: Customer: {}, accountType: {}, accountName: {}, accountNo: {}, " +
				"isJointFg: {}, isDefaultFg: {} ", customer, accountType, accountName, accountNo, isJointFg, isDefaultFg);
		EntityUtil.returnOrThrowIfParamsArrayContainsNull( 
				new Object[] {customer, accountType, accountName, accountNo, isJointFg, isDefaultFg});
		try {
			// Create the main account entity instance
			AccountRequest accountRequest = 
					new AccountRequest(accountType.getId(), currency.getId(), accountName, accountNo, isJointFg);
			PropertyUtil.initializeBaseFields(accountRequest);
			accountRequest.setCode(accountNo);
			Account account = accountService.create(accountRequest);
			// Create the customer account entity mapping instance
			CustomerAccountRequest customerAccountRequest = 
					new CustomerAccountRequest(customer.getId(), account.getId(), accountName, isDefaultFg);
			PropertyUtil.initializeBaseFields(customerAccountRequest);
			customerAccountRequest.setCode(accountNo);
			customerAccountService.create(customerAccountRequest);
		}
		catch (Exception e) {
			ExceptionUtil.processException(e, ErrorCodes.BPS_ENTITY_CREATION_ERROR_CD);
		}
		return null;
	}

}
