/**
 * 
 */
package com.nathanclaire.alantra.transaction.service.entity;


import com.nathanclaire.alantra.transaction.model.CustTypeTxnTypeConfirmationOptions;
import com.nathanclaire.alantra.transaction.model.ServiceTransactionType;
import com.nathanclaire.alantra.transaction.request.CustTypeTxnTypeConfirmationOptionsRequest;
import com.nathanclaire.alantra.transaction.response.CustTypeTxnTypeConfirmationOptionsResponse;
import com.nathanclaire.alantra.base.service.entity.BaseEntityService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.customer.model.CustomerType;

/**
 * @author Edward Banfa
 *
 */
public interface CustTypeTxnTypeConfirmationOptionsService extends BaseEntityService<CustTypeTxnTypeConfirmationOptions, CustTypeTxnTypeConfirmationOptionsResponse, CustTypeTxnTypeConfirmationOptionsRequest>
{

	public CustTypeTxnTypeConfirmationOptions findCustomerTypeOption(
			CustomerType customerType,
			ServiceTransactionType serviceTransactionType) throws ApplicationException;

}
