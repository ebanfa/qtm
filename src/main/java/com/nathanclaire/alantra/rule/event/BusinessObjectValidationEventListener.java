/**
 * 
 */
package com.nathanclaire.alantra.rule.event;

import javax.enterprise.event.Observes;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.businessobject.data.BusinessObjectData;
import com.nathanclaire.alantra.rule.annotation.BusinessObjectValidationEvent;
import com.nathanclaire.alantra.rule.service.process.TransactionRuleValidationService;

/**
 * Listens to {@link BusinessObjectData} validation events.
 * These events are normally fired by the 
 * {@link TransactionRuleValidationService}.
 * 
 * @author Edward Banfa
 *
 */
public interface BusinessObjectValidationEventListener {
	

	public void processBusinessObjectCreationEvent(
			@Observes @BusinessObjectValidationEvent BusinessObjectEvent event) throws ApplicationException;


}
