/**
 * 
 */
package com.nathanclaire.alantra.datasource.etl.producers;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.datasource.etl.DataTransformer;
import com.nathanclaire.alantra.datasource.model.DataField;

/**
 * @author Edward Banfa 
 *
 */
public interface DataTransformerProducer {
	
	public DataTransformer getDataTransformer(DataField field) throws ApplicationException;

}