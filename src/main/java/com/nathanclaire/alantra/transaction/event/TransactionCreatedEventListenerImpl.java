/**
 * 
 */
package com.nathanclaire.alantra.transaction.event;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.advice.model.Advice;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.StringUtil;
import com.nathanclaire.alantra.transaction.annotation.CashDepositTransactionCreatedEvent;
import com.nathanclaire.alantra.transaction.annotation.CashWithdrawalTransactionCreatedEvent;
import com.nathanclaire.alantra.transaction.annotation.ChequeDepositOnCustomerTransactionCreatedEvent;
import com.nathanclaire.alantra.transaction.annotation.ChequeDepositOnNonCustomerTransactionCreatedEvent;
import com.nathanclaire.alantra.transaction.annotation.ChequeWithdrawalTransactionCreatedEvent;
import com.nathanclaire.alantra.transaction.annotation.FundsTransferTransactionCreatedEvent;
import com.nathanclaire.alantra.transaction.annotation.TransactionCreatedEvent;
import com.nathanclaire.alantra.transaction.annotation.TransactionMatchedEvent;
import com.nathanclaire.alantra.transaction.annotation.TransactionNotMatchedEvent;
import com.nathanclaire.alantra.transaction.model.ServiceTransaction;
import com.nathanclaire.alantra.transaction.model.ServiceTransactionType;
import com.nathanclaire.alantra.transaction.service.entity.ServiceTransactionStatusService;
import com.nathanclaire.alantra.transaction.service.entity.ServiceTransactionTypeService;
import com.nathanclaire.alantra.transaction.service.process.TransactionMatchingService;

/**
 * @author Edward Banfa 
 *
 */
@Stateless
public class TransactionCreatedEventListenerImpl extends BaseTransactionEventListener implements
		TransactionCreatedEventListener {

	@Inject TransactionMatchingService transactionMatchingService;
	@Inject ServiceTransactionStatusService serviceTransactionStatusService;
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Inject @TransactionMatchedEvent Event<TransactionEvent> transactionMatchedEvent;
	@Inject @TransactionNotMatchedEvent Event<TransactionEvent> transactionNotMatchedEvent;
	@Inject @CashDepositTransactionCreatedEvent Event<TransactionEvent> cashDepositTransactionCreatedEvent;
	@Inject @CashWithdrawalTransactionCreatedEvent Event<TransactionEvent> cashWithdrawalTransactionCreatedEvent;
	@Inject @ChequeDepositOnCustomerTransactionCreatedEvent Event<TransactionEvent> chequeDepositOnCustomerTransactionCreatedEvent;
	@Inject @ChequeDepositOnNonCustomerTransactionCreatedEvent Event<TransactionEvent> chequeDepositOnNonCustomerTransactionCreatedEvent;
	@Inject @ChequeWithdrawalTransactionCreatedEvent Event<TransactionEvent> chequeWithdrawalTransactionCreatedEvent;
	@Inject @FundsTransferTransactionCreatedEvent Event<TransactionEvent> fundsTransferTransactionCreatedEvent;

	/**
 	 * If transaction supports matching, then call matcher, and then fire an event for the
 	 * transaction type.
	 * @param event
	 * @throws ApplicationException
	 */
	@Override
	public void processTransactionCreatedEvent(@Observes @TransactionCreatedEvent TransactionEvent event) 
			throws ApplicationException {
		try {
			ServiceTransaction transaction = getTransaction(event);
			ServiceTransactionType transactionType = transaction.getServiceTransactionType();
			if(StringUtil.flagToBoolean(transactionType.getMatchFg()))
				matchTransaction(event, transaction);
			this.processTransactionType(transaction);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	/**
 	 * If matching succeeds then update the status of the transaction to match successful,
	 * and fire match successful event.
	 * If matching fails update the status to matched failed and fire match failed event
	 * @param event
	 * @param transaction
	 * @throws ApplicationException
	 */
	private void matchTransaction(TransactionEvent event,
			ServiceTransaction transaction) throws ApplicationException {
		Advice advice = transactionMatchingService.match(transaction);
		if(advice != null){
			transaction.setServiceTransactionStatus(
					serviceTransactionStatusService.findByCode(ServiceTransactionStatusService.MATCHED));
			transactionMatchedEvent.fire(initializeEvent(advice.getId(), transaction.getId(), event));
		}
		else{
			transaction.setServiceTransactionStatus(
					serviceTransactionStatusService.findByCode(ServiceTransactionStatusService.NOT_MATCHED));
			transactionNotMatchedEvent.fire(initializeEvent(null, transaction.getId(), event));
		}
	}
	
	/**
	 * 
	 * @param transaction
	 */
	private void processTransactionType(ServiceTransaction transaction)
	{

		ServiceTransactionType transactionType = transaction.getServiceTransactionType();
		if(transactionType.getCode().equals(ServiceTransactionTypeService.CASH_DEPOSIT))
			cashDepositTransactionCreatedEvent.fire(new TransactionEvent());
		else if(transactionType.getCode().equals(ServiceTransactionTypeService.CASH_WITHDRAWAL))
			cashWithdrawalTransactionCreatedEvent.fire(new TransactionEvent());
		else if(transactionType.getCode().equals(ServiceTransactionTypeService.CHEQUE_DEPOSIT_ON_CUSTOMER))
			chequeDepositOnCustomerTransactionCreatedEvent.fire(new TransactionEvent());
		else if(transactionType.getCode().equals(ServiceTransactionTypeService.CHEQUE_DEPOSIT_ON_NON_CUSTOMERS))
			chequeDepositOnNonCustomerTransactionCreatedEvent.fire(new TransactionEvent());
		else if(transactionType.getCode().equals(ServiceTransactionTypeService.CHEQUE_WITHDRAWAL))
			chequeWithdrawalTransactionCreatedEvent.fire(new TransactionEvent());
		else if(transactionType.getCode().equals(ServiceTransactionTypeService.FUNDS_TRANSFER))
			fundsTransferTransactionCreatedEvent.fire(new TransactionEvent());
		else;
	}
	
}
