/**
 * 
 */
package com.nathanclaire.alantra.datasource.etl.service;

import com.nathanclaire.alantra.base.util.ApplicationException;

/**
 * @author Edward Banfa 
 *
 */
public interface EntityDataInputServiceProducer {
	
	public static final String MESSAGE_ENTITY = "Message";
	public static final String CUSTOMER_ENTITY = "Customer";
	public static final String ADVICE_ENTITY = "AdviceRequestMessage";
	public static final String TRANSACTION_ENTITY = "ServiceTransaction";
	
	public EntityDataInputService getEntityDataInputService(String entityName) throws ApplicationException;

}
