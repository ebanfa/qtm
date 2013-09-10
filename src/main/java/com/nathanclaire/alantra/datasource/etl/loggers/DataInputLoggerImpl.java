/**
 * 
 */
package com.nathanclaire.alantra.datasource.etl.loggers;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.nathanclaire.alantra.base.service.entity.BaseEntityService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.DateUtil;
import com.nathanclaire.alantra.base.util.PropertyUtil;
import com.nathanclaire.alantra.datasource.etl.util.CellData;
import com.nathanclaire.alantra.datasource.etl.util.RowDataLite;
import com.nathanclaire.alantra.datasource.etl.util.TableData;
import com.nathanclaire.alantra.datasource.model.DataInputJob;
import com.nathanclaire.alantra.datasource.model.DataRow;
import com.nathanclaire.alantra.datasource.model.DataTable;
import com.nathanclaire.alantra.datasource.request.CellDataRequest;
import com.nathanclaire.alantra.datasource.request.RowDataRequest;
import com.nathanclaire.alantra.datasource.request.TableDataRequest;
import com.nathanclaire.alantra.datasource.service.process.DataTableService;

/**
 * @author Edward Banfa 
 *
 */
@Stateless
public class DataInputLoggerImpl implements DataInputLogger {

	@Inject DataTableService dataTableService;
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.DataInputLogger#logDataInput(com.nathanclaire.alantra.datasource.model.DataInputJob, com.nathanclaire.alantra.datasource.etl.TableDataLite)
	 */
	@Override
	public TableData logDataInput(DataInputJob inputJob, TableData tableData) throws ApplicationException {
		// 1. Create the table reques
		TableDataRequest tableDataRequest = new TableDataRequest();
		PropertyUtil.initializeBaseFields(tableDataRequest);
		// 2. Set required properties
		tableDataRequest.setDataInputJobId(inputJob.getId());
		tableDataRequest.setCode(inputJob.getCode() + DateUtil.getCurrentTimeInMilliSeconds().toString());
		tableDataRequest.setName(inputJob.getName() + inputJob.getCode() + DateUtil.getCurrentTimeInMilliSeconds().toString());
		tableDataRequest.setPrimRecCreated(tableData.getPrimEntityRecordsCreated());
		tableDataRequest.setPrimRecRead(tableData.getPrimEntityRecordsRead());
		tableDataRequest.setPrimRecRejected(tableData.getPrimEntityRecordsRejected());
		tableDataRequest.setSecRecCreated(tableData.getSecEntityRecordsCreated());
		tableDataRequest.setSecRecRead(tableData.getSecEntityRecordsRead());
		tableDataRequest.setSecRecRejected(tableData.getSecEntityRecordsRejected());
		tableDataRequest.setTotRecCreated(tableData.getTotalEntitiesCreated());
		tableDataRequest.setTotRecRead(tableData.getTotalEntitiesRead());
		tableDataRequest.setTotRecRejected(tableData.getTotalEntitiesRejected());
		// 3. Create the table data entity
		DataTable dataTable = null;//dataTableService.createTableData(tableDataRequest);
		// 4. For each row in table data method param
		for(RowDataLite rowDataLite : tableData.getRows())
		{
			// 5. Initialize a row data request
			RowDataRequest rowDataRequest = new RowDataRequest();
			PropertyUtil.initializeBaseFields(rowDataRequest);
			rowDataRequest.setCode(inputJob.getCode() + DateUtil.getCurrentTimeInMilliSeconds().toString());
			rowDataRequest.setName(inputJob.getName() + inputJob.getCode() + DateUtil.getCurrentTimeInMilliSeconds().toString());
			// 6. Set required properties
			if(rowDataLite.isHeaderRow())
				rowDataRequest.setIsHeaderFg(BaseEntityService.ENTITY_FLAG_YES);
			else 
				rowDataRequest.setIsHeaderFg(BaseEntityService.ENTITY_FLAG_NO);
			// 7. Create the row row entity with table data entity as its parent
			rowDataRequest.setTableDataId(dataTable.getId());
			rowDataRequest.setCode(DateUtil.getCurrentTimeInMilliSeconds().toString());
			DataRow dataRow = null;//dataTableService.createRowData(rowDataRequest);
			//  8. For each cell data in cell data in row data from param
			for(CellData cellData: rowDataLite.getColumns())
			{
				// 9. Initialize a cell data request
				CellDataRequest cellDataRequest = new CellDataRequest();
				PropertyUtil.initializeBaseFields(cellDataRequest);
				cellDataRequest.setCode(inputJob.getCode() + DateUtil.getCurrentTimeInMilliSeconds().toString());
				cellDataRequest.setName(inputJob.getName() + inputJob.getCode() + DateUtil.getCurrentTimeInMilliSeconds().toString());
				// 10. Set required properties
				cellDataRequest.setDataFieldId(cellData.getDataFieldId());
				cellDataRequest.setCellData(cellData.getData().toString());
				// 11. Create the cell data entity using row data entity as parent
				cellDataRequest.setRowDataId(dataRow.getId());
				//dataTableService.createCellData(cellDataRequest);
			}
		}
		return tableData;
	}
}
