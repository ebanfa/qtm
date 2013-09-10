/**
 * 
 */
package com.nathanclaire.alantra.datasource.event;

import javax.enterprise.event.Observes;

import com.nathanclaire.alantra.base.service.process.BaseProcessService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.datasource.annotation.DataInputJobStartedEvent;

/**
 * @author Edward Banfa
 *
 */
public class DataInputJobStartedEventListenerImpl extends BaseProcessService
		implements DataInputJobStartedEventListener {

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.event.DataInputJobStartedEventListener#processDataInputJobStartedEvent(com.nathanclaire.alantra.datasource.event.DataInputEvent)
	 */
	@Override
	public void processDataInputJobStartedEvent(@Observes @DataInputJobStartedEvent DataInputEvent inputEvent)
			throws ApplicationException {
		// TODO Should update the currently running jobs table
		
	}

}
