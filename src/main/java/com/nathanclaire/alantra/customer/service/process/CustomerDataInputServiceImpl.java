/**
 * 
 */
package com.nathanclaire.alantra.customer.service.process;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.model.BaseEntity;
import com.nathanclaire.alantra.base.request.BaseRequest;
import com.nathanclaire.alantra.base.service.process.BaseProcessService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtils;
import com.nathanclaire.alantra.base.util.StringUtil;
import com.nathanclaire.alantra.businessdata.service.entity.CurrencyService;
import com.nathanclaire.alantra.customer.model.Account;
import com.nathanclaire.alantra.customer.model.Customer;
import com.nathanclaire.alantra.customer.model.CustomerAccount;
import com.nathanclaire.alantra.customer.model.CustomerClassification;
import com.nathanclaire.alantra.customer.model.CustomerType;
import com.nathanclaire.alantra.customer.request.AccountRequest;
import com.nathanclaire.alantra.customer.request.CustomerAccountRequest;
import com.nathanclaire.alantra.customer.request.CustomerRequest;
import com.nathanclaire.alantra.customer.service.entity.AccountService;
import com.nathanclaire.alantra.customer.service.entity.AccountTypeService;
import com.nathanclaire.alantra.customer.service.entity.CustomerAccountService;
import com.nathanclaire.alantra.customer.service.entity.CustomerClassificationService;
import com.nathanclaire.alantra.customer.service.entity.CustomerService;
import com.nathanclaire.alantra.customer.service.entity.CustomerTypeService;
import com.nathanclaire.alantra.datasource.etl.TableData;

/**
 * @author Edward Banfa 
 *
 */
@Stateless
public class CustomerDataInputServiceImpl extends BaseProcessService implements CustomerDataInputService {
	
	@Inject	AccountService accountService;
	@Inject	CustomerService customerService;
	@Inject	CurrencyService currencyService;
	@Inject	CustomerTypeService customerTypeService;
	@Inject AccountTypeService accountTypeService;
	@Inject	CustomerAccountService customerAccountService;
	@Inject	CustomerClassificationService customerclassficationService;
	
	private Logger logger = LoggerFactory.getLogger(CustomerDataInputServiceImpl.class);

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.process.EntityDataInputService#processDataInput(com.nathanclaire.alantra.base.request.BaseRequest)
	 */
	@Override
	public BaseEntity processDataInput(BaseRequest primaryEntityRequest, BaseRequest secEntityRequest, TableData tableData)	throws ApplicationException {
		PropertyUtils.initializeBaseFields(primaryEntityRequest);
		Customer customer = this.createCustomer((CustomerRequest) primaryEntityRequest);
		this.createAccount(customer, (AccountRequest) secEntityRequest);
		return customer;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.process.CustomerDataInputService#createCustomer(com.nathanclaire.alantra.customer.request.CustomerRequest)
	 */
	private Customer createCustomer(CustomerRequest customerRequest) throws ApplicationException 
	{
		logger.debug("Creating customer from data input request: {}", customerRequest);
		Customer customer = customerService.findByCode(customerRequest.getCode());
		if(customer != null)
			return customer;
		customerRequest.setPin(generateCustomerPINCode());
		customerRequest.setCustomerTypeId(this.getCustomerType(CustomerTypeService.PROFESSIONAL).getId());
		customerRequest.setCustomerClassificationId(getCustomerClass(CustomerClassificationService.DEFAULT_CUST_CLASS_CODE).getId());
		return customerService.create(customerRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.process.CustomerDataInputService#createCustomerAccount(com.nathanclaire.alantra.customer.request.CustomerAccountRequest)
	 */
	private Account createAccount(Customer customer, AccountRequest accountRequest)
			throws ApplicationException 
	{
		if(customer == null) throw new ApplicationException(CUSTOMER_NOT_FOUND);
		if(accountRequest == null) throw new ApplicationException(INVALID_ACCOUNT_REQUEST);
		Account account = accountService.findByCode(accountRequest.getAccountNo());
		if(account != null)
			return account;
		PropertyUtils.initializeBaseFields(accountRequest);
		accountRequest.setName(customer.getName());
		accountRequest.setCurrencyId(null);
		accountRequest.setAccountTypeId(null);
		accountRequest.setIsJointFg('Y');
		accountRequest.setCode(accountRequest.getAccountNo());
		accountRequest.setAccountTypeId(accountTypeService.findByCode(AccountTypeService.OD_CURRENT_ACCOUNT).getId());
		accountRequest.setCurrencyId(currencyService.findByCode(CurrencyService.DEFAULT_CURRENCY_CODE).getId());
		//customerAccountRequest.setCode(customerAccountRequest..getAccountNo());
		//accountRequest.setCustomerId(customer.getId());
		account = accountService.create(accountRequest);
		if(account != null)
			this.createCustomerAccount(customer, account);
		return account;
	}
	
	private CustomerAccount createCustomerAccount(Customer customer, Account account)
			throws ApplicationException 
	{
		if(customer == null) throw new ApplicationException(CUSTOMER_NOT_FOUND);
		CustomerAccountRequest customerAccountRequest = new CustomerAccountRequest(); 
		
		PropertyUtils.initializeBaseFields(customerAccountRequest);
		customerAccountRequest.setName(customer.getName());
		customerAccountRequest.setCode(account.getAccountNo());
		customerAccountRequest.setCustomerId(customer.getId());
		customerAccountRequest.setAccountId(account.getId());
		CustomerAccount customerAccount = customerAccountService.findByCode(account.getAccountNo());
		if(customerAccount == null)
			customerAccountService.create(customerAccountRequest);
		return customerAccount;
	}
	
	/**
	 * @param customerClassCode
	 * @return
	 * @throws ApplicationException
	 */
	private CustomerClassification getCustomerClass(String customerClassCode) throws ApplicationException {
		CustomerClassification customerClassification = customerclassficationService.findByCode(customerClassCode);
		if(customerClassification == null) throw new ApplicationException(CUSTOMER_CLASS_NOT_FOUND);
		return customerClassification;
	}

	/**
	 * @param customerType
	 * @return
	 * @throws ApplicationException
	 */
	private CustomerType getCustomerType(String customerTypeCode) throws ApplicationException {
		logger.debug("Looking up customer type code: {}", customerTypeCode);
		CustomerType customerType = customerTypeService.findByCode(customerTypeCode);
		if(customerType == null) throw new ApplicationException(CUSTOMER_TYPE_NOT_FOUND);
		return customerType;
	}

	/**
	 * @return
	 */
	private String generateCustomerPINCode() {
		return StringUtil.getRandomString(4);
	}
	
}
