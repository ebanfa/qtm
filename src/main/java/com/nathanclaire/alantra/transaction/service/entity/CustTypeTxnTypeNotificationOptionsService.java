/**
 * 
 */
package com.nathanclaire.alantra.transaction.service.entity;


import com.nathanclaire.alantra.transaction.model.CustTypeTxnTypeNotificationOptions;
import com.nathanclaire.alantra.transaction.model.ServiceTransactionType;
import com.nathanclaire.alantra.transaction.request.CustTypeTxnTypeNotificationOptionsRequest;
import com.nathanclaire.alantra.transaction.response.CustTypeTxnTypeNotificationOptionsResponse;
import com.nathanclaire.alantra.base.service.entity.BaseEntityService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.customer.model.CustomerType;

/**
 * @author Edward Banfa
 *
 */
public interface CustTypeTxnTypeNotificationOptionsService extends BaseEntityService<CustTypeTxnTypeNotificationOptions, CustTypeTxnTypeNotificationOptionsResponse, CustTypeTxnTypeNotificationOptionsRequest>
{

	CustTypeTxnTypeNotificationOptions findCustomerTypeOption(
			CustomerType customerType,
			ServiceTransactionType serviceTransactionType) throws ApplicationException;

}
