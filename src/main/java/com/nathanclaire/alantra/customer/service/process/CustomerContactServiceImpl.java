/**
 * 
 */
package com.nathanclaire.alantra.customer.service.process;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import com.nathanclaire.alantra.base.service.process.BaseProcessService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.ErrorCodes;
import com.nathanclaire.alantra.base.util.StringUtil;
import com.nathanclaire.alantra.customer.model.Customer;
import com.nathanclaire.alantra.customer.service.entity.CustomerService;

/**
 * @author Edward Banfa
 *
 */
public class CustomerContactServiceImpl extends BaseProcessService implements
		CustomerContactService {
	
	@Inject CustomerService customerEntityService;

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.process.CustomerContactService#getPrimaryMobileNo(com.nathanclaire.alantra.customer.model.Customer)
	 */
	@Override
	public String getPrimaryMobileNo(Customer customer) throws ApplicationException {
		if (StringUtil.isValidString(customer.getPrimaryMobile()))
			return customer.getPrimaryMobile();
		else 
			throw new ApplicationException("ENTITY_INSTANCE_NOT_FOUND", "Primary Moblie Number");
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.process.CustomerContactService#getSecondaryMobileNo(com.nathanclaire.alantra.customer.model.Customer)
	 */
	@Override
	public String getSecondaryMobileNo(Customer customer) throws ApplicationException {
		if (StringUtil.isValidString(customer.getSecondaryMobile()))
			return customer.getSecondaryMobile();
		else 
			throw new ApplicationException("ENTITY_INSTANCE_NOT_FOUND", "Secondary Moblie Number");
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.process.CustomerContactService#getPrimaryEmailAddress(com.nathanclaire.alantra.customer.model.Customer)
	 */
	@Override
	public String getPrimaryEmailAddress(Customer customer)	throws ApplicationException {
		if (StringUtil.isValidString(customer.getPrimaryEmail()))
			return customer.getPrimaryEmail();
		else 
			throw new ApplicationException("ENTITY_INSTANCE_NOT_FOUND", "Primary Email Address");
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.process.CustomerContactService#getSecondaryEmailAddress(com.nathanclaire.alantra.customer.model.Customer)
	 */
	@Override
	public String getSecondaryEmailAddress(Customer customer) throws ApplicationException {
		if (StringUtil.isValidString(customer.getSecondaryEmail()))
			return customer.getSecondaryEmail();
		else 
			throw new ApplicationException("ENTITY_INSTANCE_NOT_FOUND", "Secondary Email Address");
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.process.CustomerContactService#findCustomerByContact(java.lang.String)
	 */
	@Override
	public Customer findCustomerByContact(String contact) throws ApplicationException {
		Customer customer = this.findCustomerByPhone(contact);
		if(customer == null)
			customer = this.findCustomerByEmail(contact);
		if(customer != null)
			return customer;
		throw new ApplicationException(ErrorCodes.CCS_CUSTOMER_CONTACT_ERROR_CD, ErrorCodes.CCS_CUST_HAS_NO_CONTACT_ERROR_MSG);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.process.CustomerContactService#findCustomerByEmail(java.lang.String)
	 */
	@Override
	public Customer findCustomerByEmail(String sourceAddress) throws ApplicationException {
		Map<String, String> criteria = new HashMap<String, String>();
		criteria.put("primaryEmail", sourceAddress);
		List<Customer> customers = customerEntityService.findByCriteria(criteria);
		// Search the primary and secondary email.
		// This should be optimized into a single query call
		if(!customers.isEmpty())
			return customers.get(0);
		else{
			criteria.clear();
			criteria.put("secondaryEmail", sourceAddress);
			customers = customerEntityService.findByCriteria(criteria);
			if(!customers.isEmpty())
				return customers.get(0);
			else
				throw new ApplicationException(ErrorCodes.BPS_ENTITY_INSTANCE_NOT_FOUND_ERROR_CD, "Customer::email");
		}
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.process.CustomerContactService#findCustomerByPhone(java.lang.String)
	 */
	@Override
	public Customer findCustomerByPhone(String sourceAddress) throws ApplicationException {
		Map<String, String> criteria = new HashMap<String, String>();
		criteria.put("primaryMobile", sourceAddress);
		List<Customer> customers = customerEntityService.findByCriteria(criteria);
		// Search the primary and secondary email.
		// This should be optimized into a single query call
		if(!customers.isEmpty())
			return customers.get(0);
		else{
			criteria.clear();
			criteria.put("secondaryMobile", sourceAddress);
			customers = customerEntityService.findByCriteria(criteria);
			if(!customers.isEmpty())
				return customers.get(0);
			else
				throw new ApplicationException(ErrorCodes.BPS_ENTITY_INSTANCE_NOT_FOUND_ERROR_CD, "Customer::mobile");
		}
	}

}
