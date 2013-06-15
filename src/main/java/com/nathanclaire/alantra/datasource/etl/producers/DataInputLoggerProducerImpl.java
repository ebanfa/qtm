/**
 * 
 */
package com.nathanclaire.alantra.datasource.etl.producers;

import javax.ejb.Stateless;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.datasource.etl.DataInputLogger;
import com.nathanclaire.alantra.datasource.model.DataInputJob;

/**
 * @author Edward Banfa 
 *
 */
@Stateless
public class DataInputLoggerProducerImpl implements DataInputLoggerProducer {

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.producers.DataInputLoggerProducer#getDataInputLogger(com.nathanclaire.alantra.datasource.model.DataInputJob)
	 */
	@Override
	public DataInputLogger getDataInputLogger(DataInputJob inputJob)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

}
