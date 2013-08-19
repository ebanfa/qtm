/**
 * 
 */
package com.nathanclaire.alantra.transaction.service.entity;


import com.nathanclaire.alantra.transaction.model.CustTxnTypeConfirmationOptions;
import com.nathanclaire.alantra.transaction.model.ServiceTransactionType;
import com.nathanclaire.alantra.transaction.request.CustTxnTypeConfirmationOptionsRequest;
import com.nathanclaire.alantra.transaction.response.CustTxnTypeConfirmationOptionsResponse;
import com.nathanclaire.alantra.base.service.entity.BaseEntityService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.customer.model.Customer;

/**
 * @author Edward Banfa
 *
 */
public interface CustTxnTypeConfirmationOptionsService extends BaseEntityService<CustTxnTypeConfirmationOptions, CustTxnTypeConfirmationOptionsResponse, CustTxnTypeConfirmationOptionsRequest>
{

	public CustTxnTypeConfirmationOptions findCustomerOption(Customer customer,
			ServiceTransactionType serviceTransactionType) throws ApplicationException;

}
