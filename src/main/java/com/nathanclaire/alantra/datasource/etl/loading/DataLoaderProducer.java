/**
 * 
 */
package com.nathanclaire.alantra.datasource.etl.loading;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.datasource.model.DataInput;

/**
 * @author Edward Banfa 
 *
 */
public interface DataLoaderProducer {

	/**
	 * @param dataInput
	 */
	public DataLoader getDataLoader(DataInput dataInput) throws ApplicationException ;
}
