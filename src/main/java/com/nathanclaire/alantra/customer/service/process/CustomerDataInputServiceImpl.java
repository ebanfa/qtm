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
import com.nathanclaire.alantra.customer.model.Customer;
import com.nathanclaire.alantra.customer.model.CustomerAccount;
import com.nathanclaire.alantra.customer.model.CustomerClassification;
import com.nathanclaire.alantra.customer.model.CustomerType;
import com.nathanclaire.alantra.customer.request.CustomerAccountRequest;
import com.nathanclaire.alantra.customer.request.CustomerRequest;
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
	
	@Inject	CustomerService customerService;
	@Inject	CustomerTypeService customerTypeService;
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
		this.createCustomerAccount(customer, (CustomerAccountRequest) secEntityRequest);
		return customer;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.process.CustomerDataInputService#createCustomer(com.nathanclaire.alantra.customer.request.CustomerRequest)
	 */
	private Customer createCustomer(CustomerRequest customerRequest) throws ApplicationException 
	{
		Customer customer = customerService.findByCode(customerRequest.getCode());
		if(customer != null)
			return customer;
		customerRequest.setPin(generateCustomerPINCode());
		customerRequest.setCustomerTypeId(this.getCustomerType(CustomerTypeService.INDIVIDUAL_CUST_TYPE_CODE).getId());
		customerRequest.setCustomerClassificationId(getCustomerClass(CustomerClassificationService.DEFAULT_CUST_CLASS_CODE).getId());
		return customerService.create(customerRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.process.CustomerDataInputService#createCustomerAccount(com.nathanclaire.alantra.customer.request.CustomerAccountRequest)
	 */
	private CustomerAccount createCustomerAccount(Customer customer, CustomerAccountRequest customerAccountRequest)
			throws ApplicationException 
	{
		if(customer == null) throw new ApplicationException(CUSTOMER_NOT_FOUND);
		if(customerAccountRequest == null) throw new ApplicationException(INVALID_ACCOUNT_REQUEST);
		CustomerAccount account = customerAccountService.findByCode(customerAccountRequest.getAccountNo());
		if(account != null)
			return account;
		PropertyUtils.initializeBaseFields(customerAccountRequest);
		customerAccountRequest.setName(customer.getName());
		customerAccountRequest.setCode(customerAccountRequest.getAccountNo());
		customerAccountRequest.setCustomerId(customer.getId());
		return customerAccountService.create(customerAccountRequest);
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
		CustomerType customerType = customerTypeService.findByCode(customerTypeCode);
		if(customerType == null) throw new ApplicationException(CUSTOMER_TYPE_NOT_FOUND);
		return customerType;
	}

	/**
	 * @return
	 */
	private String generateCustomerPINCode() {
		return "0000";
	}
	
}
