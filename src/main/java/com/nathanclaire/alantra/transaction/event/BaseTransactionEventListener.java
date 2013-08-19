/**
 * 
 */
package com.nathanclaire.alantra.transaction.event;

import javax.inject.Inject;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.customer.model.Customer;
import com.nathanclaire.alantra.transaction.model.ServiceTransaction;
import com.nathanclaire.alantra.transaction.model.ServiceTransactionType;
import com.nathanclaire.alantra.transaction.service.entity.ServiceTransactionService;

/**
 * @author Edward Banfa 
 *
 */
public class BaseTransactionEventListener {

	@Inject ServiceTransactionService transactionService;	
	public static final String INVALID_TRANSACTION_TYPE = 
			"BaseTransactionEventListener.INVALID_TRANSACTION_TYPE";
	public static final String TRANSACTION_DOES_NOT_EXIST = 
			"BaseTransactionEventListener.TRANSACTION_DOES_NOT_EXIST";
	public static final String INVALID_TRANSACTION_TYPE_NOT_SPECIFIED =
			"BaseTransactionEventListener.INVALID_TRANSACTION_TYPE_NOT_SPECIFIED";

	/**
	 * @param event
	 * @return
	 * @throws ApplicationException
	 */
	protected ServiceTransaction getTransaction(BaseTransactionEvent event)
			throws ApplicationException {
		ServiceTransaction transaction = transactionService.findById(event.getTransactionId());
		validateTransaction(transaction, event.getTransactionTypeCode());
		return transaction;
	}
	
	/**
	 * @param adviceId
	 * @param transactionId
	 * @param event
	 * @return
	 */
	protected TransactionEvent initializeEvent(Integer adviceId, Integer transactionId, TransactionEvent event)
	{
		TransactionEvent transactioEvent = new TransactionEvent(adviceId, transactionId);
		transactioEvent.setJobId(event.getJobId());
		transactioEvent.setChannelId(event.getChannelId());
		transactioEvent.setTransactionId(event.getTransactionId());
		transactioEvent.setJobSummaryId(event.getJobSummaryId());
		return transactioEvent;
	}

	/**
	 * @param transaction
	 * @throws ApplicationException
	 */
	protected void validateTransaction(ServiceTransaction transaction, String transactionTypecCode)
			throws ApplicationException {
		if(transaction == null)
			throw new ApplicationException(TRANSACTION_DOES_NOT_EXIST);
		// 2. Validate that the transaction is really a cheque transaction
		ServiceTransactionType transactionType = transaction.getServiceTransactionType();
		if(transactionType == null)
			throw new ApplicationException(INVALID_TRANSACTION_TYPE_NOT_SPECIFIED);
		if(!transactionType.getCode().equals(transactionTypecCode))
			// and throw application exception if the validation fails
			throw new ApplicationException(INVALID_TRANSACTION_TYPE);
	}


	protected Customer getCustomer(ServiceTransaction transaction) {
		Customer customer = transaction.getCustomerAccount().getCustomer();
		return customer;
	}
	

}
