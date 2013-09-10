/**
 * 
 */
package com.nathanclaire.alantra.datasource.event;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.service.process.BaseProcessService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.ErrorCodes;
import com.nathanclaire.alantra.base.util.ExceptionUtil;
import com.nathanclaire.alantra.datasource.annotation.DataLoadedEvent;
import com.nathanclaire.alantra.datasource.service.process.DataInputService;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class DataLoadedEventListenerImpl extends BaseProcessService implements	DataLoadedEventListener 
{
	@Inject DataInputService dataInputService;
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Inject @DataLoadedEvent Event<DataInputEvent> dataLoadedEvent;

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.event.DataExtractedEventListener#processDataExtractedEvent(com.nathanclaire.alantra.datasource.event.DataInputEvent)
	 */
	@Override
	public void processDataLoadedEvent(@Observes @DataLoadedEvent DataInputEvent event)	throws ApplicationException  {
		logger.debug("Processing data loaded event for input job {}", event.getInputJobCode());
		try {
			dataLoadedEvent.fire(new DataInputEvent(event.getInputJobId(), event.getTableData()));
		} catch (Exception e) {
			ExceptionUtil.processException(e, ErrorCodes.DTEL_EVENT_PROCESSING_ERROR_CD);
		}
	}
}
