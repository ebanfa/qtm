/**
 * 
 */
package com.nathanclaire.alantra.datasource.etl.processors;

import java.util.Set;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.datasource.etl.DataProcessor;
import com.nathanclaire.alantra.datasource.etl.RowDataLite;
import com.nathanclaire.alantra.datasource.etl.TableDataLite;
import com.nathanclaire.alantra.datasource.model.DataField;

/**
 * @author Edward Banfa 
 *
 */
public abstract class BaseDataProcessor implements DataProcessor{
		
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.DataProcessor#processData(com.nathanclaire.alantra.datasource.etl.TableDataLite)
	 */
	public TableDataLite processData(TableDataLite data, Set<DataField> fields) throws ApplicationException {
		for(RowDataLite currentRow: data.getRows())
		{
			data = processTableDataRow(currentRow, data, fields);
		}
		return data;
	}
	
	protected abstract TableDataLite processTableDataRow(RowDataLite currentRow, TableDataLite data, Set<DataField> fields) throws ApplicationException ;

}
