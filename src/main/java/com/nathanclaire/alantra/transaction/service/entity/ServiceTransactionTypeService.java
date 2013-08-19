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

	public static final String CASH_DEPOSIT = "CASH_DEPOSIT";
	public static final String FUNDS_TRANSFER = "FUNDS_TRANSFER";
	public static final String CASH_WITHDRAWAL = "CASH_WITHDRAWAL";
	public static final String CHEQUE_WITHDRAWAL = "CHEQUE_WITHDRAWAL";
	public static final String CHEQUE_DEPOSIT_ON_CUSTOMER = "CHEQUE_DEPOSIT_ON_CUSTOMER";
	public static final String CHEQUE_DEPOSIT_ON_NON_CUSTOMERS = "CHEQUE_DEPOSIT_ON_NON_CUSTOMERS";
	
}
