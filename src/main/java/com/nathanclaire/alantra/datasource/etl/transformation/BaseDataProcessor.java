/**
 * 
 */
package com.nathanclaire.alantra.datasource.etl.transformation;

import java.util.Set;

import javax.inject.Inject;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.EntityUtil;
import com.nathanclaire.alantra.datasource.etl.util.RowDataLite;
import com.nathanclaire.alantra.datasource.etl.util.TableData;
import com.nathanclaire.alantra.datasource.model.DataField;
import com.nathanclaire.alantra.datasource.model.DataInputJob;
import com.nathanclaire.alantra.datasource.service.process.DataService;

/**
 * @author Edward Banfa 
 *
 */
public abstract class BaseDataProcessor {
	
	@Inject DataService dataService;
		
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.DataProcessor#processData(com.nathanclaire.alantra.datasource.etl.TableDataLite)
	 */
	public TableData processData(TableData data, Set<DataField> fields) throws ApplicationException {
		for(RowDataLite currentRow: data.getRows())
			data = processTableDataRow(currentRow, data, fields);
		return data;
	}
	

	public TableData processData(DataInputJob inputJob, TableData data) throws ApplicationException {
		EntityUtil.returnOrThrowIfParamsArrayContainsNull(new Object[]{inputJob, data});
		return processData(data, dataService.getDataStructure(inputJob).getDataFields());
	}
	
	protected abstract TableData processTableDataRow(RowDataLite currentRow, TableData data, Set<DataField> fields) throws ApplicationException ;

}
