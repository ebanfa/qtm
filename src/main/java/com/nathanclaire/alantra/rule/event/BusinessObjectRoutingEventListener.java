/**
 * 
 */
package com.nathanclaire.alantra.rule.event;

import javax.enterprise.event.Observes;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.rule.annotation.BusinessObjectRoutingEvent;

/**
 * @author Edward Banfa
 *
 */
public interface BusinessObjectRoutingEventListener {

	public void processBusinessObjectCreactionEvent(
			@Observes @BusinessObjectRoutingEvent BusinessObjectEvent event) throws ApplicationException;

}
