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
import com.nathanclaire.alantra.transaction.annotation.TransactionMatchedEvent;
import com.nathanclaire.alantra.transaction.model.ServiceTransaction;
import com.nathanclaire.alantra.transaction.request.TxnAwaitingNotificationRequest;
import com.nathanclaire.alantra.transaction.service.entity.ServiceTransactionService;
import com.nathanclaire.alantra.transaction.service.entity.TxnAwaitingNotificationService;
import com.nathanclaire.alantra.transaction.service.entity.TxnNotificationStatusService;
import com.nathanclaire.alantra.transaction.service.process.CustomerTxnNotificationConfigurationService;

/**
 * @author Edward Banfa 
 *
 */
@Stateless
public class TransactionMatchedEventListenerImpl extends BaseTransactionEventListener implements TransactionMatchedEventListener {

	private static final String SMS_NOTIFICATION = "SMS_NOTIFICATION";
	private static final String IVR_NOTIFICATION = "IVR_NOTIFICATION";
	private static final String EMAIL_NOTIFICATION = "EMAIL_NOTIFICATION";
	
	@Inject ServiceTransactionService transactionService;
	@Inject TxnNotificationStatusService notificationStatusService;
	@Inject TxnAwaitingNotificationService txnAwaitingNotificationService;
	@Inject CustomerTxnNotificationConfigurationService customerTxnNotificationConfigurationService;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * 	Check the SMS, EMAIL and IVR notification configuration for the customer 
	 *	If transaction amount is => than SMS_AMOUNT then schedule SMS notification.
	 *	If transaction amount is => than EMAIL_AMOUNT then schedule EMAIL notification.
	 *  If transaction amount is => than IVR_AMOUNT then schedule IVR notification.
	 */
	@Override
	public void processTransactionMatchedEvent(@Observes @TransactionMatchedEvent TransactionEvent event) throws ApplicationException 
	{
		try {
			ServiceTransaction transaction = getTransaction(event);
			Customer customer = getCustomer(transaction);
			
			BigDecimal sMSNotificationAmount = 
					customerTxnNotificationConfigurationService.getSMSNotificationAmount(customer, transaction.getServiceTransactionType());
			if(transaction.getAmount().compareTo(sMSNotificationAmount) >= 0)
				this.createTxnAwaitingNotification(transaction, SMS_NOTIFICATION);
			
			BigDecimal emailNotificationAmount = 
					customerTxnNotificationConfigurationService.getEMAILNotificationAmount(customer, transaction.getServiceTransactionType());
			if(transaction.getAmount().compareTo(emailNotificationAmount) >= 0)
				this.createTxnAwaitingNotification(transaction, EMAIL_NOTIFICATION);
			
			BigDecimal iVRNotificationAmount = 
					customerTxnNotificationConfigurationService.getIVRNotificationAmount(customer, transaction.getServiceTransactionType());
			if(transaction.getAmount().compareTo(iVRNotificationAmount) >= 0)
				this.createTxnAwaitingNotification(transaction, IVR_NOTIFICATION);
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
	private void createTxnAwaitingNotification(ServiceTransaction transaction, String type) throws ApplicationException {
		TxnAwaitingNotificationRequest awaitingNotificationRequest = new 
				TxnAwaitingNotificationRequest();
		PropertyUtils.initializeBaseFields(awaitingNotificationRequest);
		awaitingNotificationRequest.setServiceTransactionId(transaction.getId());
		awaitingNotificationRequest.setCode(transaction.getCode());
		awaitingNotificationRequest.setNotificationTypeId(0);
		awaitingNotificationRequest.setChannelTypeCode(type);
		awaitingNotificationRequest.setTxnNotificationStatusId(
				notificationStatusService.findByCode(TxnNotificationStatusService.NOT_SENT).getId());
		txnAwaitingNotificationService.create(awaitingNotificationRequest);
	}
}
