/**
 * 
 */
package com.nathanclaire.alantra.datasource.etl.transformation;

import java.util.Set;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.datasource.etl.util.TableData;
import com.nathanclaire.alantra.datasource.model.DataField;
import com.nathanclaire.alantra.datasource.model.DataInputJob;

/**
 * @author Edward Banfa 
 *
 */
public interface DataProcessor {

	public static final String DATA_TRANSFORMER_NOT_FOUND = "DataProcessor.DATA_TRANSFORMER_NOT_FOUND";
	
	public TableData processData(TableData data, Set<DataField> fields) throws ApplicationException;

	public TableData processData(DataInputJob inputJob, TableData data) throws ApplicationException;

}
