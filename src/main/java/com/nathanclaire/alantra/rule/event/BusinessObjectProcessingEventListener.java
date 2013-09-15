/**
 * 
 */
package com.nathanclaire.alantra.rule.event;

import javax.enterprise.event.Observes;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.rule.annotation.BusinessObjectProcessingEvent;

/**
 * @author Edward Banfa
 *
 */
public interface BusinessObjectProcessingEventListener {
	
	public void processBusinessObjectCreactionEvent(
			@Observes @BusinessObjectProcessingEvent BusinessObjectEvent event) throws ApplicationException;

}
