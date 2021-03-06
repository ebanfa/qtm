/**
 * 
 */
package com.nathanclaire.alantra.datasource.etl.loading;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.datasource.annotation.etl.EntityDataLoader;
import com.nathanclaire.alantra.datasource.model.DataInput;

/**
 * @author Edward Banfa 
 *
 */
@Stateless
public class DataLoaderProducerImpl implements DataLoaderProducer {

	@Inject @EntityDataLoader DataLoader dataLoader;
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.producers.DataLoaderProducer#getDataLoader(com.nathanclaire.alantra.datasource.model.DataInput)
	 */
	@Override
	public DataLoader getDataLoader(DataInput dataInput) throws ApplicationException {
		return dataLoader;
	}
	
}
