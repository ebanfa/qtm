/**
 * 
 */
package com.nathanclaire.alantra.transaction.service.process;

import com.nathanclaire.alantra.advice.model.Advice;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.transaction.model.ServiceTransaction;

/**
 * @author Edward Banfa 
 *
 */
public interface TransactionMatchingService {

	public static final String CUSTOMER_NOT_FOUND = "TransactionMatchingService.CUSTOMER_NOT_FOUND";
	public static final String CUSTOMER_ACCOUNT_NOT_FOUND = "TransactionMatchingService.CUSTOMER_ACCOUNT_NOT_FOUND";
	public static final String TRANSACTION_CURRENCY_NOT_FOUND = "TransactionMatchingService.TRANSACTION_CURRENCY_NOT_FOUND";
	public static final String CONFIG_ERROR_ADVICE_STATUS_NOT_FOUND = "TransactionMatchingService.CONFIG_ERROR_ADVICE_STATUS_NOT_FOUND";
	
	
	/**
	 * @param transaction
	 * @return
	 * @throws ApplicationException
	 */
	public Advice match(ServiceTransaction transaction) throws ApplicationException;

}
