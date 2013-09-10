/**
 * 
 */
package com.nathanclaire.alantra.datasource.etl.loading;

import java.util.Set;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.application.model.ApplicationEntity;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.request.BaseRequest;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.EntityUtil;
import com.nathanclaire.alantra.base.util.ExceptionUtil;
import com.nathanclaire.alantra.datasource.etl.util.RowDataLite;
import com.nathanclaire.alantra.datasource.etl.util.TableData;
import com.nathanclaire.alantra.datasource.model.DataField;
import com.nathanclaire.alantra.datasource.model.DataInputJob;
import com.nathanclaire.alantra.datasource.service.entity.DataInputJobService;
import com.nathanclaire.alantra.datasource.service.process.DataService;

/**
 * @author Edward Banfa 
 *
 */
public abstract class BaseDataLoader {
	

	@Inject DataService dataService;
	@Inject DataInputJobService inputJobService;
	@Inject ApplicationEntityService entityService;
	private Logger logger = LoggerFactory.getLogger(getClass());
	

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.DataLoader#loadData(com.nathanclaire.alantra.datasource.model.DataInputJob, com.nathanclaire.alantra.datasource.etl.TableData)
	 */
	public TableData loadData(DataInputJob inputJob, TableData tableData) throws ApplicationException {
		EntityUtil.returnOrThrowIfParamsArrayContainsNull(new Object[]{inputJob, tableData});
		return loadData(tableData, dataService.getDataStructure(inputJob).getDataFields());
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.DataLoader#loadData(com.nathanclaire.alantra.datasource.etl.TableDataLite)
	 */
	public TableData loadData(TableData tableData, Set<DataField> fields) throws ApplicationException {
		// Get the request class for the primary and secondary entities 
		Class<? extends BaseRequest> primEntityRequestClass = getEntityRequestClass(tableData.getPrimEntityName());
		Class<? extends BaseRequest> secEntityRequestClass = getEntityRequestClass(tableData.getSecEntityName());
		// Load each individual row
		int rowNumber = 0;
		for(RowDataLite row: tableData.getRows()){
			rowNumber++;
			tableData = loadDataRow(tableData, row, rowNumber, fields, primEntityRequestClass, secEntityRequestClass);
		}
		return tableData;
	}

	private TableData loadDataRow(TableData tableData, RowDataLite row, int rowNumber, Set<DataField> fields, 
			Class<? extends BaseRequest> primEntityRequestClass, Class<? extends BaseRequest> secEntityRequestClass) 
	{
		if(!row.isErrors())
		{
			logger.debug("Loading row {}. [P]Entity {}, [S]Entity {}", 
					rowNumber, tableData.getPrimEntityName(), tableData.getSecEntityName());
			try {
				return loadTableDataRow(tableData, fields, row, primEntityRequestClass, secEntityRequestClass);
			} catch (ApplicationException e) {
				ExceptionUtil.logException(e);
			}
		}
		else
			logger.debug("Ignoring row {}. [P]Entity: {}, [S]Entity {}", 
					rowNumber, tableData.getPrimEntityName(), tableData.getSecEntityName());
		return tableData;
	}
	

	private Class<? extends BaseRequest> getEntityRequestClass(String entityCode) throws ApplicationException 
	{
		EntityUtil.returnOrThrowIfParamsArrayContainsNull(new Object[]{entityCode});
		ApplicationEntity entity = entityService.findByName(entityCode);
		return (entity != null) ? EntityUtil.getEntityRequestClass(entity) : null;
	}
	

	/**
	 * @param tableData
	 * @param currentRow
	 * @param secEntityRequestClass 
	 * @param primEntityRequestClass 
	 * @return
	 * @throws ApplicationException
	 */
	protected abstract TableData loadTableDataRow(TableData tableData, Set<DataField> field, 
			RowDataLite currentRow,	Class<? extends BaseRequest> primEntityRequestClass,
			Class<? extends BaseRequest> secEntityRequestClass) throws ApplicationException;
}
