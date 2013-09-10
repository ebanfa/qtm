/**
 * 
 */
package com.nathanclaire.alantra.customer.service.process;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.customer.model.Customer;
import com.nathanclaire.alantra.customer.model.CustomerCategory;
import com.nathanclaire.alantra.customer.model.CustomerClassification;
import com.nathanclaire.alantra.customer.model.CustomerType;

/**
 * @author Edward Banfa
 * 
 */
public interface CustomerService {

	public String generateCustomerPin() throws ApplicationException;
	
	public Customer getCustomer(Integer customerId) throws ApplicationException;

	public Customer getCustomer(String custCode) throws ApplicationException;
	
	public CustomerCategory getCustomerCategory(String categoryCode) throws ApplicationException;
	
	public CustomerClassification getCustomerClassification(String customerClassificationCode) throws ApplicationException;
	
	public CustomerType getCustomerType(String customerTypeCode) throws ApplicationException;

	public Customer createCustomer(CustomerType customerType,
			CustomerClassification classfication, String name, String pinNo,
			String email, String mobileNo) throws ApplicationException;



}
