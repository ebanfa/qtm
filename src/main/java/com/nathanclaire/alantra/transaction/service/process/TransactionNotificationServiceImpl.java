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
import com.nathanclaire.alantra.transaction.model.TxnAwaitingNotification;
import com.nathanclaire.alantra.transaction.service.entity.TxnAwaitingNotificationService;

/**
 * @author Edward Banfa
 */
@Startup
@Singleton
public class TransactionNotificationServiceImpl implements
		TransactionNotificationService {
	
	@Resource(name = "java:jboss/datasources/alantraDS")
	javax.sql.DataSource ds; 
	
	@Inject NotifierService notificationsService;
	@Inject TxnAwaitingNotificationService txnAwaitingNotificationService;
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.transaction.service.process.TransactionNotificationService#processTransactionNotifications()
	 */
	@Override
	@PostConstruct
	@Schedule(minute="*/2", hour="*")
	public void processTransactionsAwaitingNotification() 
	{		
		try 
		{
			List<TxnAwaitingNotification> transactionsAwaitingNotification = 
					txnAwaitingNotificationService.getTransactionsAwaitingNotification();
			for(TxnAwaitingNotification transactionAwaitingNotification : transactionsAwaitingNotification)
			{
					notificationsService.sendCustomerNotification(getCustomer(transactionAwaitingNotification), 
							transactionAwaitingNotification.getNotificationType(), transactionAwaitingNotification.getChannelTypeCode());
			}
		} catch (ApplicationException e) {
			logger.error("Error processing transaction awaiting notification", e.getMessage());
		}
	}

	private Customer getCustomer(TxnAwaitingNotification transactionAwaitingNotification) 
			throws ApplicationException 
	{
		ServiceTransaction transaction = transactionAwaitingNotification.getServiceTransaction();
		CustomerAccount account = transaction.getCustomerAccount();
		return account.getCustomer();
	}

}
