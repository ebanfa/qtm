/**
 * 
 */
package com.nathanclaire.alantra.customer.service.process;

import com.nathanclaire.alantra.base.service.process.EntityDataInputService;


/**
 * @author Edward Banfa 
 *
 */
public interface CustomerDataInputService extends EntityDataInputService {

	public static final String CUSTOMER_NOT_CREATED = "CustomerDataInputService.CUSTOMER_NOT_CREATED";
	public static final String CUSTOMER_NOT_FOUND = "CustomerDataInputService.CUSTOMER_NOT_FOUND";
	public static final String INVALID_ACCOUNT_REQUEST = "CustomerDataInputService.INVALID_ACCOUNT_REQUEST";
	public static final String CUSTOMER_TYPE_NOT_FOUND = "CustomerDataInputService.CUSTOMER_TYPE_NOT_FOUND";
	public static final String CUSTOMER_CLASS_NOT_FOUND = "CustomerDataInputService.CUSTOMER_CLASS_NOT_FOUND";
	public static final String CUSTOMER_ACCOUNT_NOT_CREATED = "CustomerDataInputService.CUSTOMER_ACCOUNT_NOT_CREATED";
	
}
