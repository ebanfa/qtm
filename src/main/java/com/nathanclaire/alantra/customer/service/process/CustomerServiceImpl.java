/**
 * 
 */
package com.nathanclaire.alantra.customer.service.process;

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
import com.nathanclaire.alantra.customer.model.Customer;
import com.nathanclaire.alantra.customer.model.CustomerCategory;
import com.nathanclaire.alantra.customer.model.CustomerClassification;
import com.nathanclaire.alantra.customer.model.CustomerType;
import com.nathanclaire.alantra.customer.request.CustomerRequest;
import com.nathanclaire.alantra.customer.service.entity.CustomerCategoryService;
import com.nathanclaire.alantra.customer.service.entity.CustomerClassificationService;
import com.nathanclaire.alantra.customer.service.entity.CustomerTypeService;

/**
 * @author Edward Banfa 
 *
 */
@Stateless
public class CustomerServiceImpl extends BaseProcessService implements CustomerService {

	@Inject CustomerTypeService customerTypeService;
	@Inject CustomerCategoryService customerCategoryService;
	@Inject CustomerClassificationService classificationService;
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Inject com.nathanclaire.alantra.customer.service.entity.CustomerService customerEntityService;
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.process.CustomerService#generateCustomerPin()
	 */
	@Override
	public String generateCustomerPin() throws ApplicationException {
		return StringUtil.getRandomString(4);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.process.CustomerService#getCustomer(java.lang.Integer)
	 */
	@Override
	public Customer getCustomer(Integer customerId) throws ApplicationException {
		return (Customer) EntityUtil.returnOrThrowIfNull(
				customerEntityService.findById(customerId), ErrorCodes.BPS_ENTITY_INSTANCE_NOT_FOUND_ERROR_CD, "Customer");
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.process.CustomerService#getCustomer(java.lang.String)
	 */
	@Override
	public Customer getCustomer(String custCode) throws ApplicationException {
		return (Customer) EntityUtil.returnOrThrowIfNull(
				customerEntityService.findByCode(custCode), ErrorCodes.BPS_ENTITY_INSTANCE_NOT_FOUND_ERROR_CD, "Customer");
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.process.CustomerService#getCustomerCategory(java.lang.String)
	 */
	@Override
	public CustomerCategory getCustomerCategory(String categoryCode) throws ApplicationException {
		return (CustomerCategory) EntityUtil.returnOrThrowIfNull(
				customerCategoryService.findByCode(categoryCode), ErrorCodes.BPS_ENTITY_INSTANCE_NOT_FOUND_ERROR_CD, "CustomerCategory");
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.process.CustomerService#getCustomerClassification(java.lang.String)
	 */
	@Override
	public CustomerClassification getCustomerClassification(String customerClassificationCode) throws ApplicationException {
		return (CustomerClassification) EntityUtil.returnOrThrowIfNull(
				classificationService.findByCode(customerClassificationCode), 
				ErrorCodes.BPS_ENTITY_INSTANCE_NOT_FOUND_ERROR_CD, "CustomerClassification");
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.process.CustomerService#getCustomerType(java.lang.String)
	 */
	@Override
	public CustomerType getCustomerType(String customerTypeCode) throws ApplicationException {
		return (CustomerType) EntityUtil.returnOrThrowIfNull(
				customerTypeService.findByCode(customerTypeCode), ErrorCodes.BPS_ENTITY_INSTANCE_NOT_FOUND_ERROR_CD, "CustomerType");
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.process.CustomerService#createCustomer(com.nathanclaire.alantra.customer.model.CustomerType, com.nathanclaire.alantra.customer.model.CustomerClassification, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Customer createCustomer(CustomerType customerType, CustomerClassification classfication, 
			String name, String pinNo, String email, String mobileNo) throws ApplicationException {
		// Debug and validate the provided parameters
		logger.debug("Creating customer: Type: {}, classification: {}, " +
				"name: {}, email: {}, mobileNo: {} ", customerType, classfication, name, pinNo, email, mobileNo);
		
		EntityUtil.returnOrThrowIfParamsArrayContainsNull( 
				new Object[] {customerType, classfication, name, pinNo, email, mobileNo});
		try 
		{
			CustomerRequest customerRequest = new CustomerRequest(
					customerType.getId(), classfication.getId(), EntityUtil.generateDefaultEntityCode(), name, pinNo, email, mobileNo);
			PropertyUtil.initializeBaseFields(customerRequest);
			return customerEntityService.create(customerRequest);
		}
		catch (Exception e) {
			ExceptionUtil.processException(e, ErrorCodes.BPS_ENTITY_CREATION_ERROR_CD);
		}
		return null;
	}
}
