/**
 * 
 */
package com.nathanclaire.alantra.datasource.etl.extraction;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.datasource.model.DataInput;

/**
 * @author Edward Banfa 
 *
 */
public interface DataExtractorProducer {
	
	/**
	 * @param dataInput
	 */
	public DataExtractor getDataExtractor(DataInput dataInput) throws ApplicationException ;

}
