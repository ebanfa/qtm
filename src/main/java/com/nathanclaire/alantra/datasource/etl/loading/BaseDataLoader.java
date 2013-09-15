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
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.EntityUtil;
import com.nathanclaire.alantra.base.util.ExceptionUtil;
import com.nathanclaire.alantra.datasource.etl.util.RowData;
import com.nathanclaire.alantra.datasource.etl.util.TableData;
import com.nathanclaire.alantra.datasource.model.DataField;
import com.nathanclaire.alantra.datasource.model.DataInputJob;
import com.nathanclaire.alantra.datasource.service.entity.DataInputJobService;
import com.nathanclaire.alantra.datasource.service.process.DataService;

/**
 * Parent class of all {@link DataLoader}s.
 * 
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
		EntityUtil.returnOrThrowIfParamsArrayContainsNull(
				new Object[]{inputJob, tableData}, "BaseDataLoader.loadData(DataInputJob inputJob, TableData tableData)");
		return loadData(tableData, dataService.getDataStructure(inputJob).getDataFields());
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.DataLoader#loadData(com.nathanclaire.alantra.datasource.etl.TableDataLite)
	 */
	public TableData loadData(TableData tableData, Set<DataField> fields) throws ApplicationException {
		EntityUtil.returnOrThrowIfParamsArrayContainsNull(
				new Object[]{fields, tableData}, "BaseDataLoader.loadData(TableData tableData, Set<DataField> fields)");
		ApplicationEntity entity = entityService.findByName(tableData.getPrimEntityName());
		tableData.setBusinessObjectClassName(entity.getEntityClassNm());
		// Load each individual row
		for(RowData rowData: tableData.getRows())
			// Was getting a null pointer exception
			// TODO: This needs to be fixed up
			if(rowData != null)
			tableData = loadDataRow(rowData, fields);
		return tableData;
	}

	protected TableData loadDataRow(RowData rowData, Set<DataField> fields) 
	{
		logger.debug("Loading row {}", rowData);
		if(!rowData.isErrors())
		{
			try {
				logger.debug("Loading row {}", rowData);
				return loadTableDataRow(rowData, fields);
			} catch (ApplicationException e) {
				ExceptionUtil.logException(e);
			}
		}
		else
			logger.debug("Ignoring row {}",rowData);
		return rowData.getTableData();
	}	

	/**
	 * This method is called to load a single {@link RowData}.
	 * 
	 * Subclasses will provide an implementation that is specific
	 * to the manner they load data into the system.
	 * 
	 * @param rowData
	 * @param field
	 * @return
	 * @throws ApplicationException
	 */
	protected abstract TableData loadTableDataRow(RowData rowData, Set<DataField> field) throws ApplicationException;
}
