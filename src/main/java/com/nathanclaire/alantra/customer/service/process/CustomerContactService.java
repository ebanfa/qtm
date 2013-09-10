/**
 * 
 */
package com.nathanclaire.alantra.customer.service.process;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.customer.model.Customer;

/**
 * @author Edward Banfa
 *
 */
public interface CustomerContactService {
	
	public Customer findCustomerByContact(String contact) throws ApplicationException;

	public Customer findCustomerByEmail(String sourceAddress) throws ApplicationException;
	
	public Customer findCustomerByPhone(String sourceAddress) throws ApplicationException;
	
	public String getPrimaryMobileNo(Customer customer) throws ApplicationException;
	
	public String getSecondaryMobileNo(Customer customer) throws ApplicationException;
	
	public String getPrimaryEmailAddress(Customer customer) throws ApplicationException;

	public String getSecondaryEmailAddress(Customer customer) throws ApplicationException;

}
