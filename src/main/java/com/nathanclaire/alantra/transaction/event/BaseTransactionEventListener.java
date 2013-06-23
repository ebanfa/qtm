/**
 * 
 */
package com.nathanclaire.alantra.transaction.event;

import javax.enterprise.event.Event;
import javax.inject.Inject;

import com.nathanclaire.alantra.advice.model.Advice;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.transaction.annotation.TransactionAlreadyExistEvent;
import com.nathanclaire.alantra.transaction.annotation.TransactionMatchedEvent;
import com.nathanclaire.alantra.transaction.annotation.TransactionNotMatchedEvent;
import com.nathanclaire.alantra.transaction.model.ServiceTransaction;
import com.nathanclaire.alantra.transaction.model.ServiceTransactionType;
import com.nathanclaire.alantra.transaction.service.entity.ServiceTransactionService;
import com.nathanclaire.alantra.transaction.service.entity.ServiceTransactionStatusService;
import com.nathanclaire.alantra.transaction.service.process.TransactionMatchingService;

/**
 * @author Edward Banfa 
 *
 */
public class BaseTransactionEventListener {

	@Inject ServiceTransactionService transactionService;
	@Inject TransactionMatchingService transactionMatchingService;
	@Inject ServiceTransactionStatusService serviceTransactionStatusService;
	@Inject @TransactionMatchedEvent Event<TransactionMatchingEvent> transactionMatchedEvent;
	@Inject @TransactionNotMatchedEvent Event<TransactionMatchingEvent> transactionNotMatchingEvent;
	@Inject @TransactionAlreadyExistEvent Event<TransactionMatchingEvent> transactionAlreadyExistEvent;
	
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
	 * @param transaction
	 * @throws ApplicationException
	 */
	protected Advice matchTransaction(ServiceTransaction transaction, TransactionCreationEvent event)
			throws ApplicationException {
		Advice advice = transactionMatchingService.match(transaction);
		// 4. If matching succeeds then update the status of the transaction to match successful
		if(advice != null){
			transaction.setServiceTransactionStatus(
					serviceTransactionStatusService.findByCode(ServiceTransactionStatusService.MATCHED));
			transactionMatchedEvent.fire(initializeEvent(advice.getId(), transaction.getId(), event));
		}
		// 5. If matching fails update the status to matched failed and fire match failed event
		else{
			transaction.setServiceTransactionStatus(
					serviceTransactionStatusService.findByCode(ServiceTransactionStatusService.NOT_MATCHED));
			transactionNotMatchingEvent.fire(initializeEvent(null, transaction.getId(), event));
		}
		return advice;
	}
	
	/**
	 * @param adviceId
	 * @param transactionId
	 * @param event
	 * @return
	 */
	protected TransactionMatchingEvent initializeEvent(Integer adviceId, Integer transactionId, TransactionCreationEvent event)
	{
		TransactionMatchingEvent transactionMatchingEvent = new TransactionMatchingEvent(adviceId, transactionId);
		transactionMatchingEvent.setJobId(event.getJobId());
		transactionMatchingEvent.setChannelId(event.getChannelId());
		transactionMatchingEvent.setTransactionId(event.getTransactionId());
		transactionMatchingEvent.setJobSummaryId(event.getJobSummaryId());
		return transactionMatchingEvent;
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

}
