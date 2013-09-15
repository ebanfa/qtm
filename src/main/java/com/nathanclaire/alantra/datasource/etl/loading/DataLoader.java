/**
 * 
 */
package com.nathanclaire.alantra.datasource.etl.loading;

import java.util.Set;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.datasource.etl.util.TableData;
import com.nathanclaire.alantra.datasource.model.DataField;
import com.nathanclaire.alantra.datasource.model.DataInputJob;

/**
 * The primary interface for classes that can load 
 * {@link TableData}s into the system.
 * 
 * TODO: I think the table data should contain
 * all the data required to load it. Should there be 
 * any need to pass in the {@link DataInputJob} ?
 * 
 * @author Edward Banfa 
 *
 */
public interface DataLoader {

	public static final String ENTITY_FIELD_ERROR = "DataLoader.ENTITY_FIELD_ERROR";
	public static final String DATA_LOADING_ERROR = "DataLoader.DATA_LOADING_ERROR";
	public static final String DATA_INPUT_SERVICE_NOT_FOUND = "DataLoader.DATA_INPUT_SERVICE_NOT_FOUND";
	public static final String REQ_FIELD_VALUE_NOT_PROVIDED = "DataLoader.REQ_FIELD_VALUE_NOT_PROVIDED";
	public static final String TARGET_ENTITY_FIELD_NOT_SPECIFIED = "DataLoader.TARGET_ENTITY_FIELD_NOT_SPECIFIED";
	
	public TableData loadData(TableData tableData, Set<DataField> fields) throws ApplicationException;

	public TableData loadData(DataInputJob inputJob, TableData tableData) throws ApplicationException;
}
