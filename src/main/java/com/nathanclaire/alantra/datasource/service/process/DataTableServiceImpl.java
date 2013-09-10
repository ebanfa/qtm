/**
 * 
 */
package com.nathanclaire.alantra.datasource.service.process;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.service.process.BaseProcessService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.EntityUtil;
import com.nathanclaire.alantra.base.util.ErrorCodes;
import com.nathanclaire.alantra.base.util.ExceptionUtil;
import com.nathanclaire.alantra.base.util.PropertyUtil;
import com.nathanclaire.alantra.base.util.StringUtil;
import com.nathanclaire.alantra.datasource.model.DataCell;
import com.nathanclaire.alantra.datasource.model.DataField;
import com.nathanclaire.alantra.datasource.model.DataInputJob;
import com.nathanclaire.alantra.datasource.model.DataRow;
import com.nathanclaire.alantra.datasource.model.DataTable;
import com.nathanclaire.alantra.datasource.request.CellDataRequest;
import com.nathanclaire.alantra.datasource.request.RowDataRequest;
import com.nathanclaire.alantra.datasource.request.TableDataRequest;
import com.nathanclaire.alantra.datasource.service.entity.CellDataService;
import com.nathanclaire.alantra.datasource.service.entity.RowDataService;

/**
 * @author Edward Banfa
 *
 */
public class DataTableServiceImpl extends BaseProcessService implements	DataTableService 
{
	@Inject DataService dataService;
	@Inject RowDataService rowDataService;
	@Inject CellDataService cellDataService;
	@Inject DataInputService dataInputService;
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Inject com.nathanclaire.alantra.datasource.service.entity.TableDataService dataTableEntityService;

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.process.DataTableService#getDataTable(java.lang.String)
	 */
	@Override
	public DataTable getDataTable(String tableCode) throws ApplicationException {
		return (DataTable) EntityUtil.returnOrThrowIfNull(
				dataTableEntityService.findByCode(tableCode), ErrorCodes.BPS_ENTITY_INSTANCE_NOT_FOUND_ERROR_CD, "DataTable");
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.process.DataTableService#createDataTable(com.nathanclaire.alantra.datasource.model.DataInputJob, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Character)
	 */
	@Override
	public DataTable createDataTable(DataInputJob inputJob,
			String primEntityNm, Integer primEntityRd,
			Integer primEntityCreated, Integer primEntityRejected,
			Integer totalEntityRd, Integer totalEntityCreated,
			Integer totalEntityRejected, Character importStatusFg)
			throws ApplicationException {
		// Debug and validate the provided parameters
		logger.debug("Creating advice: inputJob: {}, primEntityNm: {}, primEntityRd: {}, primEntityCreated: {}, " +
				"primEntityRejected: {}, totalEntityRd: {}, totalEntityCreated: {} totalEntityRejected: {}, importStatusFg: {}", 
				inputJob, primEntityNm, primEntityRd, primEntityCreated, primEntityRejected, totalEntityRd, 
				totalEntityCreated, totalEntityRejected, importStatusFg);
		
		EntityUtil.returnOrThrowIfParamsArrayContainsNull( new Object[] {inputJob, primEntityNm, primEntityRd, 
				primEntityCreated, primEntityRejected, totalEntityRd, totalEntityCreated, totalEntityRejected, importStatusFg});
		try {
			String primaryEntityName = dataService.getPrimaryEntityCode(inputJob.getDataInput().getData());
			String dataTableName = 
					inputJob.getName().concat(StringUtil.UNDERSCORE).concat(EntityUtil.generateDefaultEntityCode());
			
			TableDataRequest tableDataRequest = new TableDataRequest(inputJob.getId(), dataTableName, 
					EntityUtil.generateDefaultEntityCode(), primaryEntityName, primEntityRd, primEntityCreated, 
					primEntityRejected, totalEntityRd, totalEntityCreated, totalEntityRejected, importStatusFg);
			
			PropertyUtil.initializeBaseFields(tableDataRequest);
			return dataTableEntityService.create(tableDataRequest);
		} catch (Exception e) {
			ExceptionUtil.processException(e, ErrorCodes.BPS_ENTITY_CREATION_ERROR_CD);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.process.DataTableService#createDataRow(com.nathanclaire.alantra.datasource.model.DataTable, java.lang.String, java.lang.String, java.lang.Character, java.lang.Character)
	 */
	@Override
	public DataRow createDataRow(DataTable dataTable, String name, String code, 
			Character isHeaderFg, Character importStatusFg)	throws ApplicationException {
		// Debug and validate the provided parameters
		logger.debug("Creating data row: dataTable: {}, name: {}, code: {}, isHeaderFg: {}, " +
				"importStatusFg: {}", dataTable, name, code, isHeaderFg, importStatusFg);
		
		EntityUtil.returnOrThrowIfParamsArrayContainsNull( new Object[] {dataTable, name, code, isHeaderFg, importStatusFg});
		
		try {
			RowDataRequest rowDataRequest = new RowDataRequest(dataTable.getId(), name, code, isHeaderFg, importStatusFg);
			PropertyUtil.initializeBaseFields(rowDataRequest);
			return rowDataService.create(rowDataRequest);
		} catch (Exception e) {
			ExceptionUtil.processException(e, ErrorCodes.BPS_ENTITY_CREATION_ERROR_CD);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.process.DataTableService#createDataCell(com.nathanclaire.alantra.datasource.model.DataRow, java.lang.String, java.lang.String, java.lang.Character)
	 */
	@Override
	public DataCell createDataCell(DataRow dataRow, DataField dataField, String name, 
			String code, Character importStatusFg, String data) throws ApplicationException {
		// Debug and validate the provided parameters
		logger.debug("Creating data cell: dataRow: {}, name: {}, code: {}, importStatusFg: {}", dataRow, name, code, importStatusFg);
		
		EntityUtil.returnOrThrowIfParamsArrayContainsNull( new Object[] {dataRow, name, code, importStatusFg});
		
		try {
			CellDataRequest cellDataRequest = 
					new CellDataRequest(dataRow.getId(), dataField.getId(), name, code, importStatusFg, data);
			PropertyUtil.initializeBaseFields(cellDataRequest);
			return cellDataService.create(cellDataRequest);
		} catch (Exception e) {
			ExceptionUtil.processException(e, ErrorCodes.BPS_ENTITY_CREATION_ERROR_CD);
		}
		return null;
	}

}
