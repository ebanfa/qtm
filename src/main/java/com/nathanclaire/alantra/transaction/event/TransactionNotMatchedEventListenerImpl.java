/**
 * 
 */
package com.nathanclaire.alantra.transaction.event;

import java.math.BigDecimal;

import javax.ejb.Stateless;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtils;
import com.nathanclaire.alantra.customer.model.Customer;
import com.nathanclaire.alantra.transaction.annotation.TransactionNotMatchedEvent;
import com.nathanclaire.alantra.transaction.model.ServiceTransaction;
import com.nathanclaire.alantra.transaction.request.TxnAwaitingConfirmationRequest;
import com.nathanclaire.alantra.transaction.service.entity.ServiceTransactionService;
import com.nathanclaire.alantra.transaction.service.entity.TxnAwaitingConfirmationService;
import com.nathanclaire.alantra.transaction.service.entity.TxnConfirmationStatusService;
import com.nathanclaire.alantra.transaction.service.process.CustomerTxnNotificationConfigurationService;

/**
 * @author edward
 *
 */
@Stateless
public class TransactionNotMatchedEventListenerImpl extends BaseTransactionEventListener implements
		TransactionNotMatchedEventListener {

	private static final String SMS_CONFIRMATION = "SMS_CONFIRMATION";
	private static final String IVR_CONFIRMATION = "IVR_CONFIRMATION";
	private static final String EMAIL_CONFIRMATION = "EMAIL_CONFIRMATION";
	
	@Inject ServiceTransactionService transactionService;
	@Inject TxnConfirmationStatusService confirmationStatusService;
	@Inject TxnAwaitingConfirmationService txnAwaitingConfirmationService;
	@Inject CustomerTxnNotificationConfigurationService customerTxnNotificationConfigurationService;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * 	Check the SMS, EMAIL and IVR confirmation configuration for the customer 
	 *	If transaction amount is => than SMS_AMOUNT then schedule SMS confirmation.
	 *	If transaction amount is => than EMAIL_AMOUNT then schedule EMAIL confirmation.
	 *  If transaction amount is => than IVR_AMOUNT then schedule IVR confirmation.
	 */
	@Override
	public void processTransactionNotMatchedEvent(@Observes @TransactionNotMatchedEvent TransactionEvent event)
	{
		try {
			ServiceTransaction transaction = getTransaction(event);
			Customer customer = getCustomer(transaction);
			
			BigDecimal sMSConfirmationAmount = 
					customerTxnNotificationConfigurationService.getSMSConfirmationAmount(customer, transaction.getServiceTransactionType());
			if(transaction.getAmount().compareTo(sMSConfirmationAmount) >= 0)
				this.createTxnAwaitingConfirmation(transaction, SMS_CONFIRMATION);
			
			BigDecimal emailConfirmationAmount = 
					customerTxnNotificationConfigurationService.getEMAILConfirmationAmount(customer, transaction.getServiceTransactionType());
			if(transaction.getAmount().compareTo(emailConfirmationAmount) >= 0)
				this.createTxnAwaitingConfirmation(transaction, EMAIL_CONFIRMATION);
			
			BigDecimal iVRConfirmationAmount = 
					customerTxnNotificationConfigurationService.getIVRConfirmationAmount(customer, transaction.getServiceTransactionType());
			if(transaction.getAmount().compareTo(iVRConfirmationAmount) >= 0)
				this.createTxnAwaitingConfirmation(transaction, IVR_CONFIRMATION);
		} 
		catch (Exception e) {
			logger.error("Error processing transaction matched event. {}", e.getMessage());
		}
		
	}
	
	/**
	 * @param transaction
	 * @param type
	 * @throws ApplicationException
	 */
	private void createTxnAwaitingConfirmation(ServiceTransaction transaction, String type) throws ApplicationException {
		TxnAwaitingConfirmationRequest awaitingConfirmationRequest = new 
				TxnAwaitingConfirmationRequest();
		PropertyUtils.initializeBaseFields(awaitingConfirmationRequest);
		awaitingConfirmationRequest.setServiceTransactionId(transaction.getId());
		awaitingConfirmationRequest.setNotificationTypeId(0);
		awaitingConfirmationRequest.setCode(transaction.getCode());
		awaitingConfirmationRequest.setChannelTypeCode(type);
		awaitingConfirmationRequest.setTxnConfirmationStatusId(
				confirmationStatusService.findByCode(TxnConfirmationStatusService.UNCONFIRMED).getId());
		txnAwaitingConfirmationService.create(awaitingConfirmationRequest);
	}

}
