/**
 * 
 */
package com.nathanclaire.alantra.datasource.etl.producers;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.nathanclaire.alantra.advice.service.process.AdviceRequestMessageDataInputService;
import com.nathanclaire.alantra.base.service.process.EntityDataInputService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.customer.service.process.CustomerDataInputService;
import com.nathanclaire.alantra.messaging.service.process.MessageDataInputService;
import com.nathanclaire.alantra.transaction.service.process.TransactionDataInputService;

/**
 * @author Edward Banfa 
 *
 */
@Stateless
public class EntityDataInputServiceProducerImpl implements
		EntityDataInputServiceProducer {

	@Inject MessageDataInputService messageDataInputService;
	@Inject CustomerDataInputService customerDataInputService;
	@Inject TransactionDataInputService transactionDataInputService;
	@Inject AdviceRequestMessageDataInputService adviceRequestMessageDataInputService;;
	
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
		mapping.put(EntityDataInputServiceProducer.MESSAGE_ENTITY, messageDataInputService);
		mapping.put(EntityDataInputServiceProducer.CUSTOMER_ENTITY, customerDataInputService);
		mapping.put(EntityDataInputServiceProducer.TRANSACTION_ENTITY, transactionDataInputService);
		mapping.put(EntityDataInputServiceProducer.ADVICE_ENTITY, adviceRequestMessageDataInputService);

		return mapping;
	}
	

}
