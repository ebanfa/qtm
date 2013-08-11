/**
 * 
 */
package com.nathanclaire.alantra.datasource.etl.loggers;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.nathanclaire.alantra.base.service.entity.BaseEntityService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.DateUtil;
import com.nathanclaire.alantra.base.util.PropertyUtils;
import com.nathanclaire.alantra.datasource.etl.CellDataLite;
import com.nathanclaire.alantra.datasource.etl.DataInputLogger;
import com.nathanclaire.alantra.datasource.etl.RowDataLite;
import com.nathanclaire.alantra.datasource.etl.TableDataLite;
import com.nathanclaire.alantra.datasource.model.DataInputJob;
import com.nathanclaire.alantra.datasource.model.RowData;
import com.nathanclaire.alantra.datasource.model.TableData;
import com.nathanclaire.alantra.datasource.request.CellDataRequest;
import com.nathanclaire.alantra.datasource.request.RowDataRequest;
import com.nathanclaire.alantra.datasource.request.TableDataRequest;
import com.nathanclaire.alantra.datasource.service.process.DataSourceModuleService;

/**
 * @author Edward Banfa 
 *
 */
@Stateless
public class DataInputLoggerImpl implements DataInputLogger {

	@Inject DataSourceModuleService dataSourceModuleService;
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.DataInputLogger#logDataInput(com.nathanclaire.alantra.datasource.model.DataInputJob, com.nathanclaire.alantra.datasource.etl.TableDataLite)
	 */
	@Override
	public TableDataLite logDataInput(DataInputJob inputJob, TableDataLite tableDataLite) throws ApplicationException {
		// 1. Create the table reques
		TableDataRequest tableDataRequest = new TableDataRequest();
		PropertyUtils.initializeBaseFields(tableDataRequest);
		// 2. Set required properties
		tableDataRequest.setDataInputJobId(inputJob.getId());
		tableDataRequest.setCode(inputJob.getCode() + DateUtil.getCurrentTimeInMilliSeconds().toString());
		tableDataRequest.setName(inputJob.getName() + inputJob.getCode() + DateUtil.getCurrentTimeInMilliSeconds().toString());
		tableDataRequest.setPrimRecCreated(tableDataLite.getPrimEntityRecordsCreated());
		tableDataRequest.setPrimRecRead(tableDataLite.getPrimEntityRecordsRead());
		tableDataRequest.setPrimRecRejected(tableDataLite.getPrimEntityRecordsRejected());
		tableDataRequest.setSecRecCreated(tableDataLite.getSecEntityRecordsCreated());
		tableDataRequest.setSecRecRead(tableDataLite.getSecEntityRecordsRead());
		tableDataRequest.setSecRecRejected(tableDataLite.getSecEntityRecordsRejected());
		tableDataRequest.setTotRecCreated(tableDataLite.getTotalEntitiesCreated());
		tableDataRequest.setTotRecRead(tableDataLite.getTotalEntitiesRead());
		tableDataRequest.setTotRecRejected(tableDataLite.getTotalEntitiesRejected());
		// 3. Create the table data entity
		TableData tableData = dataSourceModuleService.createTableData(tableDataRequest);
		// 4. For each row in table data method param
		for(RowDataLite rowDataLite : tableDataLite.getRows())
		{
			// 5. Initialize a row data request
			RowDataRequest rowDataRequest = new RowDataRequest();
			PropertyUtils.initializeBaseFields(rowDataRequest);
			rowDataRequest.setCode(inputJob.getCode() + DateUtil.getCurrentTimeInMilliSeconds().toString());
			rowDataRequest.setName(inputJob.getName() + inputJob.getCode() + DateUtil.getCurrentTimeInMilliSeconds().toString());
			// 6. Set required properties
			if(rowDataLite.isHeaderRow())
				rowDataRequest.setIsHeaderFg(BaseEntityService.ENTITY_FLAG_YES);
			else 
				rowDataRequest.setIsHeaderFg(BaseEntityService.ENTITY_FLAG_NO);
			// 7. Create the row row entity with table data entity as its parent
			rowDataRequest.setTableDataId(tableData.getId());
			rowDataRequest.setCode(DateUtil.getCurrentTimeInMilliSeconds().toString());
			RowData rowData = dataSourceModuleService.createRowData(rowDataRequest);
			//  8. For each cell data in cell data in row data from param
			for(CellDataLite cellDataLite: rowDataLite.getColumns())
			{
				// 9. Initialize a cell data request
				CellDataRequest cellDataRequest = new CellDataRequest();
				PropertyUtils.initializeBaseFields(cellDataRequest);
				cellDataRequest.setCode(inputJob.getCode() + DateUtil.getCurrentTimeInMilliSeconds().toString());
				cellDataRequest.setName(inputJob.getName() + inputJob.getCode() + DateUtil.getCurrentTimeInMilliSeconds().toString());
				// 10. Set required properties
				cellDataRequest.setDataFieldId(cellDataLite.getDataFieldId());
				cellDataRequest.setCellData(cellDataLite.getData().toString());
				// 11. Create the cell data entity using row data entity as parent
				cellDataRequest.setRowDataId(rowData.getId());
				dataSourceModuleService.createCellData(cellDataRequest);
			}
		}
		return tableDataLite;
	}
}
