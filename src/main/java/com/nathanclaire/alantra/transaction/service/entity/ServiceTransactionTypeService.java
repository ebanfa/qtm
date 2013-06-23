/**
 * 
 */
package com.nathanclaire.alantra.transaction.service.entity;

import com.nathanclaire.alantra.base.service.entity.BaseEntityService;
import com.nathanclaire.alantra.transaction.model.ServiceTransactionType;
import com.nathanclaire.alantra.transaction.request.ServiceTransactionTypeRequest;
import com.nathanclaire.alantra.transaction.response.ServiceTransactionTypeResponse;

/**
 * @author Edward Banfa
 *
 */
public interface ServiceTransactionTypeService extends BaseEntityService<ServiceTransactionType, ServiceTransactionTypeResponse, ServiceTransactionTypeRequest>
{

	public static final String ATM_WITHDRAWAL = "ATM_WITHDRAWAL";
	public static final String CHEQUE_WITHDRAWAL = "CHQ_WITHDRAWAL";
	
}
