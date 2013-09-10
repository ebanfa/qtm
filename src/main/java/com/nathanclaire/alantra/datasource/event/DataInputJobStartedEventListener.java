/**
 * 
 */
package com.nathanclaire.alantra.datasource.event;

import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.datasource.annotation.DataInputJobStartedEvent;

/**
 * @author Edward Banfa
 * 
 */
public interface DataInputJobStartedEventListener {

	public void processDataInputJobStartedEvent(@Observes @DataInputJobStartedEvent DataInputEvent inputEvent)
			throws ApplicationException;

}
