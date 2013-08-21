/**
 * 
 */
package com.nathanclaire.alantra.customer.service.entity;

import java.util.List;

import com.nathanclaire.alantra.base.service.entity.BaseEntityService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.customer.model.Customer;
import com.nathanclaire.alantra.customer.request.CustomerRequest;
import com.nathanclaire.alantra.customer.response.CustomerResponse;

/**
 * @author Edward Banfa
 *
 */
public interface CustomerService extends BaseEntityService<Customer, CustomerResponse, CustomerRequest>
{

	List<Customer> findByIds(List<Integer> listOfCustomerIds) throws ApplicationException;

	List<Customer> findByType(String type) throws ApplicationException;

	List<Customer> findByCategory(String type) throws ApplicationException;

	List<Customer> findByClassification(String type) throws ApplicationException;

	public Customer findCustomerByPhone(String mobileNo) throws ApplicationException;

	public Customer findCustomerByEmail(String emailAddress) throws ApplicationException;
	
}
