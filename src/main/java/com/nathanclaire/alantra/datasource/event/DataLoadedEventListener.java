/**
 * 
 */
package com.nathanclaire.alantra.datasource.event;

import javax.enterprise.event.Observes;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.datasource.annotation.DataLoadedEvent;

/**
 * @author Edward Banfa
 * 
 */
public interface DataLoadedEventListener {

	public void processDataLoadedEvent(@Observes @DataLoadedEvent DataInputEvent event)
			throws ApplicationException;

}
