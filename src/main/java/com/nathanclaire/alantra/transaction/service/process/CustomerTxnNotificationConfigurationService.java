/**
 * 
 */
package com.nathanclaire.alantra.transaction.service.process;

import java.math.BigDecimal;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.customer.model.Customer;
import com.nathanclaire.alantra.transaction.model.ServiceTransactionType;

/**
 * @author edward
 *
 */
public interface CustomerTxnNotificationConfigurationService {
	
	
	public BigDecimal getSMSNotificationAmount(Customer customer, ServiceTransactionType serviceTransactionType) 
			throws ApplicationException;
	
	public BigDecimal getEMAILNotificationAmount(Customer customer, ServiceTransactionType serviceTransactionType)
			throws ApplicationException;
	
	public BigDecimal getIVRNotificationAmount(Customer customer, ServiceTransactionType serviceTransactionType)
			throws ApplicationException;

	public BigDecimal getSMSConfirmationAmount(Customer customer, ServiceTransactionType serviceTransactionType)
			throws ApplicationException;
	
	public BigDecimal getEMAILConfirmationAmount(Customer customer, ServiceTransactionType serviceTransactionType)
			throws ApplicationException;
	
	public BigDecimal getIVRConfirmationAmount(Customer customer, ServiceTransactionType serviceTransactionType)
			throws ApplicationException;
}
