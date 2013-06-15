/**
 * 
 */
package com.nathanclaire.alantra.datasource.etl;

import java.util.Set;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.datasource.model.DataField;

/**
 * @author Edward Banfa 
 *
 */
public interface DataLoader {

	public static final String DATA_INPUT_SERVICE_NOT_FOUND = "DataLoader.DATA_INPUT_SERVICE_NOT_FOUND";
	
	public TableData loadData(TableData tableData, Set<DataField> fields) throws ApplicationException;
}
