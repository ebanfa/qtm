/**
 * 
 */
package com.nathanclaire.alantra.datasource.event;

import javax.enterprise.event.Observes;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.datasource.annotation.DataTransformedEvent;

/**
 * @author Edward Banfa
 * 
 */
public interface DataTransformedEventListener {

	public void processDatatTransformedEvent(@Observes @DataTransformedEvent DataInputEvent event)
			throws ApplicationException;

}
