/**
 * 
 */
package com.nathanclaire.alantra.transaction.service.process;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.customer.model.Customer;
import com.nathanclaire.alantra.customer.model.CustomerAccount;
import com.nathanclaire.alantra.notification.service.process.NotifierService;
import com.nathanclaire.alantra.transaction.model.ServiceTransaction;
import com.nathanclaire.alantra.transaction.model.TxnAwaitingConfirmation;
import com.nathanclaire.alantra.transaction.service.entity.TxnAwaitingConfirmationService;

/**
 * @author Edward Banfa
 *
 */
@Startup
@Singleton
public class TransactionConfirmationServiceImpl 
		implements TransactionConfirmationService {

	@Resource(name = "java:jboss/datasources/alantraDS")
	javax.sql.DataSource ds; 

	@Inject NotifierService notificationsService;
	@Inject TxnAwaitingConfirmationService txnAwaitingConfirmationService;
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * 
	 */
	@Override
	@PostConstruct
	@Schedule(minute="*/3", hour="*")
	public void processTransactionsAwaitingConfirmation() 
	{
		try 
		{
			List<TxnAwaitingConfirmation> transactionsAwaitingConfirmation = 
					txnAwaitingConfirmationService.getTransactionsAwaitingConfirmation();
			for(TxnAwaitingConfirmation transactionAwaitingConfirmation : transactionsAwaitingConfirmation)
			{
					notificationsService.sendCustomerNotification(getCustomer(transactionAwaitingConfirmation), 
							transactionAwaitingConfirmation.getNotificationType(), transactionAwaitingConfirmation.getChannelTypeCode());
			}
		} catch (ApplicationException e) {
			logger.error("Error processing transaction awaiting confirmation", e.getMessage());
		}
	}
	
	private Customer getCustomer(TxnAwaitingConfirmation transactionAwaitingNotification) 
			throws ApplicationException 
	{
		ServiceTransaction transaction = transactionAwaitingNotification.getServiceTransaction();
		CustomerAccount account = transaction.getCustomerAccount();
		return account.getCustomer();
	}
}
