/**
 * 
 */
package com.nathanclaire.alantra.channel.service.process;

import com.nathanclaire.alantra.base.service.process.EntityDataInputService;

/**
 * @author Edward Banfa 
 *
 */
public interface TransactionDataInputService extends EntityDataInputService {

	public static final String CHEQUE_NO_NOT_PROVIDED = "TransactionDataInputService.CHEQUE_NO_NOT_PROVIDED";
	public static final String ACCOUNT_NO_NOT_PROVIDED = "TransactionDataInputService.ACCOUNT_NO_NOT_PROVIDED";
	public static final String TRANSACTION_ALREADY_EXISTS = "TransactionDataInputService.TRANSACTION_ALREADY_EXISTS";
	public static final String TRANSACTION_TYPE_NOT_FOUND = "TransactionDataInputService.TRANSACTION_TYPE_NOT_FOUND";
	public static final String ACCOUNT_DOES_NOT_NOT_EXIST = "TransactionDataInputService.ACCOUNT_DOES_NOT_NOT_EXIST";
	public static final String TRANSACTION_STATUS_NOT_FOUND = "TransactionDataInputService.TRANSACTION_STATUS_NOT_FOUND";
	public static final String TRANSACTION_CURRENCY_NOT_FOUND = "TransactionDataInputService.TRANSACTION_CURRENCY_NOT_FOUND";
	public static final String TRANSACTION_INPUT_SERVICE_NOT_FOUND = "TransactionDataInputService.TRANSACTION_INPUT_SERVICE_NOT_FOUND";

}
