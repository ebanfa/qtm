/**
 * 
 */
package com.nathanclaire.alantra.datasource.event;

import javax.enterprise.event.Observes;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.datasource.annotation.DataExtractedEvent;

/**
 * @author Edward Banfa
 * 
 */
public interface DataExtractedEventListener {

	public void processDataExtractedEvent(@Observes @DataExtractedEvent DataInputEvent event)
			throws ApplicationException;
}
