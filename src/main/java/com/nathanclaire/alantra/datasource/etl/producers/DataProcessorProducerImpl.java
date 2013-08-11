/**
 * 
 */
package com.nathanclaire.alantra.datasource.etl.producers;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.datasource.etl.DataProcessor;
import com.nathanclaire.alantra.datasource.etl.DataProcessorProducer;
import com.nathanclaire.alantra.datasource.model.DataInput;

/**
 * @author Edward Banfa 
 *
 */
@Stateless
public class DataProcessorProducerImpl implements DataProcessorProducer {

	@Inject DataProcessor dataProcessor;
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.producers.DataProcessorProducer#getDataProcessor(com.nathanclaire.alantra.datasource.model.DataInput)
	 */
	@Override
	public DataProcessor getDataProcessor(DataInput dataInput) throws ApplicationException {
		return dataProcessor;
	}
}
