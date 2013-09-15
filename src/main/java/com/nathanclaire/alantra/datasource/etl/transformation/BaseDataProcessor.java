/**
 * 
 */
package com.nathanclaire.alantra.datasource.etl.transformation;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.EntityUtil;
import com.nathanclaire.alantra.datasource.etl.util.RowData;
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
	private Logger logger = LoggerFactory.getLogger(getClass());
		
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.DataProcessor#processData(com.nathanclaire.alantra.datasource.etl.TableDataLite)
	 */
	public TableData processData(TableData data, Set<DataField> fields) throws ApplicationException {
		logger.debug("Processing {}", data);
		List<RowData> processedRows = new ArrayList<RowData>();
		for(RowData currentRow: data.getRows()){
			RowData processedRow = 
					processTableDataRow(currentRow, data, fields);
			processedRow.setTableData(data);
			processedRows.add(processedRow);
		}
		data.setRows(processedRows);
		return data;
	}
	

	public TableData processData(DataInputJob inputJob, TableData data) throws ApplicationException {
		EntityUtil.returnOrThrowIfParamsArrayContainsNull(new Object[]{inputJob, data});
		return processData(data, dataService.getDataStructure(inputJob).getDataFields());
	}
	
	protected abstract RowData processTableDataRow(RowData currentRow, TableData data, Set<DataField> fields) throws ApplicationException ;

}
