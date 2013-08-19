/**
 * 
 */
package com.nathanclaire.alantra.transaction.service.entity;


import com.nathanclaire.alantra.transaction.model.CustTxnTypeNotificationOptions;
import com.nathanclaire.alantra.transaction.model.ServiceTransactionType;
import com.nathanclaire.alantra.transaction.request.CustTxnTypeNotificationOptionsRequest;
import com.nathanclaire.alantra.transaction.response.CustTxnTypeNotificationOptionsResponse;
import com.nathanclaire.alantra.base.service.entity.BaseEntityService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.customer.model.Customer;

/**
 * @author Edward Banfa
 *
 */
public interface CustTxnTypeNotificationOptionsService extends BaseEntityService<CustTxnTypeNotificationOptions, CustTxnTypeNotificationOptionsResponse, CustTxnTypeNotificationOptionsRequest>
{

	CustTxnTypeNotificationOptions findCustomerOption(Customer customer,
			ServiceTransactionType serviceTransactionType) throws ApplicationException;

}
