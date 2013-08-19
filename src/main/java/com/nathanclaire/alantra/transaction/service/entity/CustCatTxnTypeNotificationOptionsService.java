/**
 * 
 */
package com.nathanclaire.alantra.transaction.service.entity;


import com.nathanclaire.alantra.transaction.model.CustCatTxnTypeNotificationOptions;
import com.nathanclaire.alantra.transaction.model.ServiceTransactionType;
import com.nathanclaire.alantra.transaction.request.CustCatTxnTypeNotificationOptionsRequest;
import com.nathanclaire.alantra.transaction.response.CustCatTxnTypeNotificationOptionsResponse;
import com.nathanclaire.alantra.base.service.entity.BaseEntityService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.customer.model.CustomerCategory;
import com.nathanclaire.alantra.customer.model.CustomerType;

/**
 * @author Edward Banfa
 *
 */
public interface CustCatTxnTypeNotificationOptionsService extends BaseEntityService<CustCatTxnTypeNotificationOptions, CustCatTxnTypeNotificationOptionsResponse, CustCatTxnTypeNotificationOptionsRequest>
{

	CustCatTxnTypeNotificationOptions findCustomerCategoryOption(CustomerCategory customerCategory,
			ServiceTransactionType serviceTransactionType) throws ApplicationException;

}
