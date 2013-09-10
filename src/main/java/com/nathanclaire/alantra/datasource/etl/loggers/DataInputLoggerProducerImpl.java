/**
 * 
 */
package com.nathanclaire.alantra.datasource.etl.loggers;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.datasource.model.DataInputJob;

/**
 * @author Edward Banfa 
 *
 */
@Stateless
public class DataInputLoggerProducerImpl implements DataInputLoggerProducer {

	@Inject DataInputLogger dataInputLogger;
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.producers.DataInputLoggerProducer#getDataInputLogger(com.nathanclaire.alantra.datasource.model.DataInputJob)
	 */
	@Override
	public DataInputLogger getDataInputLogger(DataInputJob inputJob) throws ApplicationException {
		return dataInputLogger;
	}

}
