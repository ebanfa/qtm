/**
 * 
 */
package com.nathanclaire.alantra.datasource.etl.transformation;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.datasource.model.DataInput;

/**
 * @author Edward Banfa 
 *
 */
public interface DataProcessorProducer {
	
	/**
	 * @param dataInput
	 */
	public DataProcessor getDataProcessor(DataInput dataInput) throws ApplicationException ;
}
