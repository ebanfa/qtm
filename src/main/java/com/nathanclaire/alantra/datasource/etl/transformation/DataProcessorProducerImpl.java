/**
 * 
 */
package com.nathanclaire.alantra.datasource.etl.transformation;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.datasource.annotation.etl.FieldDataTransformerProcessor;
import com.nathanclaire.alantra.datasource.model.DataInput;

/**
 * @author Edward Banfa 
 *
 */
@Stateless
public class DataProcessorProducerImpl implements DataProcessorProducer {

	@Inject @FieldDataTransformerProcessor DataProcessor dataProcessor;
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.producers.DataProcessorProducer#getDataProcessor(com.nathanclaire.alantra.datasource.model.DataInput)
	 */
	@Override
	public DataProcessor getDataProcessor(DataInput dataInput) throws ApplicationException {
		return dataProcessor;
	}
}
