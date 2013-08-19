/**
 * 
 */
package com.nathanclaire.alantra.transaction.service.entity;


import com.nathanclaire.alantra.transaction.model.CustCatTxnTypeConfirmationOptions;
import com.nathanclaire.alantra.transaction.model.ServiceTransactionType;
import com.nathanclaire.alantra.transaction.request.CustCatTxnTypeConfirmationOptionsRequest;
import com.nathanclaire.alantra.transaction.response.CustCatTxnTypeConfirmationOptionsResponse;
import com.nathanclaire.alantra.base.service.entity.BaseEntityService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.customer.model.CustomerCategory;

/**
 * @author Edward Banfa
 *
 */
public interface CustCatTxnTypeConfirmationOptionsService extends BaseEntityService<CustCatTxnTypeConfirmationOptions, CustCatTxnTypeConfirmationOptionsResponse, CustCatTxnTypeConfirmationOptionsRequest>
{

	public CustCatTxnTypeConfirmationOptions findCustomerCategoryOption(
			CustomerCategory customerCategory,
			ServiceTransactionType serviceTransactionType) throws ApplicationException;

}
