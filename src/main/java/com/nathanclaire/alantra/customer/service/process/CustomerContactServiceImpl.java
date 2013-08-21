/**
 * 
 */
package com.nathanclaire.alantra.customer.service.process;

import com.nathanclaire.alantra.base.service.process.BaseProcessService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.StringUtil;
import com.nathanclaire.alantra.customer.model.Customer;

/**
 * @author Edward Banfa
 *
 */
public class CustomerContactServiceImpl extends BaseProcessService implements
		CustomerContactService {

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

}
