/**
 * 
 */
package com.nathanclaire.alantra.datasource.etl.service;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.nathanclaire.alantra.advice.annotation.AdviceDataInputService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.customer.annotation.CustomerDataInputService;
import com.nathanclaire.alantra.messaging.annotation.MessageDataInputService;
import com.nathanclaire.alantra.transaction.annotation.TransactionDataInputService;

/**
 * @author Edward Banfa 
 *
 */
@Stateless
public class EntityDataInputServiceProducerImpl implements
		EntityDataInputServiceProducer {

	@Inject @AdviceDataInputService EntityDataInputService adviceDataInputService;;
	@Inject @MessageDataInputService EntityDataInputService messageDataInputService;
	@Inject @CustomerDataInputService EntityDataInputService customerDataInputService;
	@Inject @TransactionDataInputService EntityDataInputService transactionDataInputService;
	
	@Override
	public EntityDataInputService getEntityDataInputService(String entityName)	throws ApplicationException {
		Map<String, EntityDataInputService> dataInputServicesMapping = getEntityDataInputServiceMapping();
		if(dataInputServicesMapping.containsKey(entityName))
			return dataInputServicesMapping.get(entityName);
		return null;
	}
	/**
	 * @return
	 */
	private Map<String, EntityDataInputService> getEntityDataInputServiceMapping()
	{
		Map<String, EntityDataInputService> mapping = new HashMap<String, EntityDataInputService>();
		mapping.put(EntityDataInputServiceProducer.ADVICE_ENTITY, adviceDataInputService);
		mapping.put(EntityDataInputServiceProducer.MESSAGE_ENTITY, messageDataInputService);
		mapping.put(EntityDataInputServiceProducer.CUSTOMER_ENTITY, customerDataInputService);
		mapping.put(EntityDataInputServiceProducer.TRANSACTION_ENTITY, transactionDataInputService);
		return mapping;
	}
	

}
