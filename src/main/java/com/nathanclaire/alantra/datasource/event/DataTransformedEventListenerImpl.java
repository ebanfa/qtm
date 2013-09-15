/**
 * 
 */
package com.nathanclaire.alantra.datasource.event;

import javax.ejb.Stateless;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.service.process.BaseProcessService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.ErrorCodes;
import com.nathanclaire.alantra.base.util.ExceptionUtil;
import com.nathanclaire.alantra.datasource.annotation.DataTransformedEvent;
import com.nathanclaire.alantra.datasource.service.process.DataInputService;
import com.nathanclaire.alantra.datasource.service.process.ETLService;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class DataTransformedEventListenerImpl extends BaseProcessService implements DataTransformedEventListener 
{
	@Inject ETLService etlService;
	@Inject DataInputService dataInputService;
	private Logger logger = LoggerFactory.getLogger(getClass());

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.event.DataExtractedEventListener#processDataExtractedEvent(com.nathanclaire.alantra.datasource.event.DataInputEvent)
	 */
	@Override
	public void processDatatTransformedEvent(@Observes @DataTransformedEvent DataInputEvent event) throws ApplicationException {
		logger.debug("Processing data transformed event for " +
				"input job: {}, with data: {}", event.getInputJobCode(), event.getTableData());
		try {
			etlService.loadData(
					dataInputService.getDataInputJob(event.getInputJobId()), event.getTableData());
		} catch (Exception e) {
			ExceptionUtil.processException(e, ErrorCodes.DTEL_EVENT_PROCESSING_ERROR_CD);
		}
	}

}
