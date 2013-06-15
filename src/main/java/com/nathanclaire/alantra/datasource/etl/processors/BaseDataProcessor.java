/**
 * 
 */
package com.nathanclaire.alantra.datasource.etl.processors;

import java.util.Set;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.datasource.etl.DataProcessor;
import com.nathanclaire.alantra.datasource.etl.RowData;
import com.nathanclaire.alantra.datasource.etl.TableData;
import com.nathanclaire.alantra.datasource.model.DataField;

/**
 * @author Edward Banfa 
 *
 */
public abstract class BaseDataProcessor implements DataProcessor{
		
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.DataProcessor#processData(com.nathanclaire.alantra.datasource.etl.TableData)
	 */
	public TableData processData(TableData data, Set<DataField> fields) throws ApplicationException {
		for(RowData currentRow: data.getRows())
		{
			data = processTableDataRow(currentRow, data, fields);
		}
		return data;
	}
	
	protected abstract TableData processTableDataRow(RowData currentRow, TableData data, Set<DataField> fields) throws ApplicationException ;

}
