/**
 * 
 */
package com.nathanclaire.alantra.channel.service.process;

import java.math.BigDecimal;
import java.util.Date;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.channel.model.ServiceTransaction;

/**
 * @author Edward Banfa 
 *
 */
public interface TransactionProcessorService {

	public static final String TRANSACTION_ALREADY_EXISTS = "TransactionProcessorService.TRANSACTION_ALREADY_EXISTS";
	public static final String ACCOUNT_NO_NOT_PROVIDED = "TransactionProcessorService.ACCOUNT_NO_NOT_PROVIDED";
	public static final String CHEQUE_NO_NOT_PROVIDED = "TransactionProcessorService.CHEQUE_NO_NOT_PROVIDED";
	
	/**
	 * @param serviceId
	 * @param transactionTypeCode
	 * @param accountNo
	 * @param chequeNo
	 * @param amount
	 * @param crncyCode
	 * @param txnDate
	 * @return
	 * @throws ApplicationException 
	 */
	public ServiceTransaction processChequeTransactionRequest(Integer serviceId, 
			String transactionTypeCode, String accountNo, String chequeNo, BigDecimal amount, String crncyCode, Date txnDate) throws ApplicationException;
}
