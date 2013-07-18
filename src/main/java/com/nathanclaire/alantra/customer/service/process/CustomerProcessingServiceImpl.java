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
import com.nathanclaire.alantra.base.util.StringUtil;
import com.nathanclaire.alantra.customer.model.Account;
import com.nathanclaire.alantra.customer.model.Customer;
import com.nathanclaire.alantra.customer.model.CustomerAccount;
import com.nathanclaire.alantra.customer.service.entity.AccountService;
import com.nathanclaire.alantra.customer.service.entity.CustomerService;
import com.nathanclaire.alantra.messaging.model.MessageCategory;
import com.nathanclaire.alantra.messaging.model.MessageType;
import com.nathanclaire.alantra.messaging.service.entity.MessageCategoryService;

/**
 * @author Edward Banfa 
 *
 */
@Stateless
public class CustomerProcessingServiceImpl extends BaseProcessService implements CustomerProcessingService {

	@Inject AccountService accountService;
	@Inject CustomerService customerService;
	private Logger logger = LoggerFactory.getLogger(CustomerProcessingServiceImpl.class);
	
	
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.process.CustomerProcessingService#findSingleCustomerBySourceAddress(java.lang.String)
	 */
	@Override
	public Customer findSingleCustomerBySourceAddress(MessageType messageType, String sourceAddress) throws ApplicationException {
		if(messageType == null)
			throw new ApplicationException(CUST_NOT_FOUND_INVALID_MESSAGE_TYPE_SPECIFIED);
		logger.debug("Find single customer with source address {} of type {}", sourceAddress, messageType.getCode());
		MessageCategory messageCategory = messageType.getMessageCategory();
		if(messageCategory == null)
			throw new ApplicationException(CONFIG_ERROR_CUST_NOT_FOUND_INVALID_MESSAGE_CAT_SPECIFIED);
		logger.debug("Using message category {} to narrow search", messageCategory.getCode());
		// Depending on the message category we will either be finding by email
		Customer customer = null;
		if(messageCategory.getCode().equals(MessageCategoryService.EMAIL))
			customer = findCustomerByEmail(sourceAddress);
		// or by phone
		if(messageCategory.getCode().equals(MessageCategoryService.SMS))
			customer = findCustomerByPhone(sourceAddress);
		if(customer != null)
			logger.debug("Found customer {} with source address {} found", customer.getName(), sourceAddress);
		else
			logger.debug("Customer with source address {} not found", sourceAddress);
		return customer;
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
		logger.debug("Verifying customer contact {} for customer {}", sourceAddress, customer);
		if(StringUtil.isValidString(StringUtil.match(customer.getPrimaryMobile(), sourceAddress, false)))
			return true;
		if(StringUtil.isValidString(StringUtil.match(customer.getSecondaryMobile(), sourceAddress, false)))
			return true;
		if(StringUtil.isValidString(StringUtil.match(customer.getPrimaryEmail(), sourceAddress, false)))
			return true;
		if(StringUtil.isValidString(StringUtil.match(customer.getSecondaryEmail(), sourceAddress, false)))
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
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.process.CustomerProcessingService#getDefaultCustomerAccount(com.nathanclaire.alantra.customer.model.Customer, java.util.List)
	 */
	@Override
	public Account getDefaultCustomerAccount(Customer customer, List<Account> accounts)
			throws ApplicationException {
		if(accounts.isEmpty())
			return getDefaultCustomerAccount(customer);
		return accounts.get(0);
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.process.CustomerProcessingService#getDefaultCustomerAccount(com.nathanclaire.alantra.customer.model.Customer)
	 */
	@Override
	public Account getDefaultCustomerAccount(Customer customer) throws ApplicationException {
		Set<CustomerAccount> accountMappings = customer.getCustomerAccounts();
		if(accountMappings.isEmpty())
			throw new ApplicationException(CUSTOMER_DEFAULT_ACCOUNT_DOES_NOT_EXIST);
		Account account = null;
		for(CustomerAccount mapping : accountMappings)
			account = mapping.getAccount();
		return account;
	}

	/**
	 * @param sourceAddress
	 * @return
	 * @throws ApplicationException
	 */
	@Override
	public  Customer findCustomerByPhone(String sourceAddress) throws ApplicationException 
	{
		Map<String, String> criteria = new HashMap<String, String>();
		// First we search by primary phone number
		criteria.put(PRIM_PHONE_CRITERIA, sourceAddress);
		List<Customer> results = customerService.findByCriteria(criteria);
		if(!results.isEmpty())
			return results.get(0);
		// If the search came up empty we try to search by the secondary phone number
		criteria.clear();
		criteria.put(SEC_PHONE_CRITERIA, sourceAddress);
		results = customerService.findByCriteria(criteria);
		if(!results.isEmpty())
			return results.get(0);
		// The search is hopeless so return null
		return null;
	}

	/**
	 * @param sourceAddress
	 * @return
	 * @throws ApplicationException
	 */
	@Override
	public Customer findCustomerByEmail(String sourceAddress) throws ApplicationException 
	{
		Map<String, String> criteria = new HashMap<String, String>();
		// First we search by primary phone number
		criteria.put(PRIM_EMAIL_CRITERIA, sourceAddress);
		List<Customer> results = customerService.findByCriteria(criteria);
		if(!results.isEmpty())
			return results.get(0);
		// If the search came up empty we try to search by the secondary phone number
		criteria.clear();
		criteria.put(SEC_EMAIL_CRITERIA, sourceAddress);
		results = customerService.findByCriteria(criteria);
		if(!results.isEmpty())
			return results.get(0);
		// The search is hopeless so return null
		return null;
	}

}
