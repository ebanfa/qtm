/**
 * 
 */
package com.nathanclaire.alantra.datasource.etl.transformation;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.datasource.model.DataField;

/**
 * @author Edward Banfa 
 *
 */
public interface DataTransformerProducer {
	
	public DataTransformer getDataTransformer(String transformerCode) throws ApplicationException;

}
