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

	public static final String ENTITY_FIELD_ERROR = "DataLoader.ENTITY_FIELD_ERROR";
	public static final String DATA_LOADING_ERROR = "DataLoader.DATA_LOADING_ERROR";
	public static final String DATA_INPUT_SERVICE_NOT_FOUND = "DataLoader.DATA_INPUT_SERVICE_NOT_FOUND";
	public static final String REQ_FIELD_VALUE_NOT_PROVIDED = "DataLoader.REQ_FIELD_VALUE_NOT_PROVIDED";
	public static final String TARGET_ENTITY_FIELD_NOT_SPECIFIED = "DataLoader.TARGET_ENTITY_FIELD_NOT_SPECIFIED";
	
	public TableDataLite loadData(TableDataLite tableDataLite, Set<DataField> fields) throws ApplicationException;
}
