/**
 * 
 */
package com.nathanclaire.alantra.datasource.event;

import java.util.HashMap;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.service.process.BaseProcessService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.ExceptionUtil;
import com.nathanclaire.alantra.datasource.annotation.DataLoadedEvent;
import com.nathanclaire.alantra.datasource.etl.util.CellData;
import com.nathanclaire.alantra.datasource.etl.util.RowData;
import com.nathanclaire.alantra.datasource.etl.util.TableData;
import com.nathanclaire.alantra.datasource.model.DataCell;
import com.nathanclaire.alantra.datasource.model.DataRow;
import com.nathanclaire.alantra.datasource.model.DataTable;
import com.nathanclaire.alantra.datasource.service.entity.DataFieldService;
import com.nathanclaire.alantra.datasource.service.entity.DataInputJobService;
import com.nathanclaire.alantra.rule.engine.BusinessObjectData;
import com.nathanclaire.alantra.rule.service.process.BusinessObjectCreationService;
import com.nathanclaire.alantra.rule.service.process.TransactionRuleProcessingService;
import com.nathanclaire.alantra.rule.service.process.TransactionRuleRoutingService;
import com.nathanclaire.alantra.rule.service.process.TransactionRuleValidationService;
import com.nathanclaire.alantra.rule.util.BusinessObjectUtil;

/**
 * Listens to {@link DataLoadedEvent} and creates the necessary
 * information to record the data that was loaded. As of now,
 * all this class does to create {@link DataTable}s, {@link DataRow}s and
 * {@link DataCell} to represent the {@link TableData} that was loaded.
 * 
 * @author Edward Banfa
 *
 */
@Stateless
public class DataLoadedEventListenerImpl extends BaseProcessService implements	DataLoadedEventListener 
{
	@Inject DataInputJobService dataInputJobService;
	@Inject DataFieldService dataFieldService;
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Inject @DataLoadedEvent Event<DataInputEvent> dataLoadedEvent;
	@Inject TransactionRuleRoutingService routingService;
	@Inject TransactionRuleValidationService validationService;
	@Inject TransactionRuleProcessingService processingService;
	@Inject BusinessObjectCreationService objectCreationService;

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.event.DataExtractedEventListener#processDataExtractedEvent(com.nathanclaire.alantra.datasource.event.DataInputEvent)
	 */
	@Override
	public void processDataLoadedEvent(@Observes @DataLoadedEvent DataInputEvent event)	throws ApplicationException  {
		logger.debug("Processing data loaded event for input job {}", event.getInputJobCode());
		try {
			BusinessObjectData businessObjectData = 
					prepareTableDataBO(event.getTableData());
			// Validate the business object first
			validationService.validate(businessObjectData);
			if(businessObjectData.isValid())
				return;
			// Create the data table
			DataTable dataTable = 
					(DataTable) objectCreationService.create(businessObjectData);
			// Create the rows of the data table
			createDataRow(dataTable, event.getTableData());
			// Process and route the data table
			processingService.process(businessObjectData);
			routingService.route(businessObjectData);
			
		} catch (Exception e) {
			ExceptionUtil.logException(e);
		}
	}
	
	/**
	 * Creates and persists a {@link DataRow} instance.
	 * 
	 * @param dataTable The parent table of the data row
	 * @param data the source table data
	 * @throws ApplicationException if an exception was encountered
	 */
	private void createDataRow(DataTable dataTable, TableData data)	throws ApplicationException 
	{
		for(RowData rowData : data.getRows())
		{
			BusinessObjectData businessObjectData = prepareDataRowBO(dataTable, rowData);
			validationService.validate(businessObjectData);
			// Create the row data
			DataRow dataRow = 
					(DataRow) objectCreationService.create(businessObjectData);
			// Process the cells in the row
			for(CellData cellData : rowData.getColumns()) {
				createDataCell(dataRow, cellData);
			}
		}
	}
	
	/**
	 * Creates and persists a {@link DataCell} instance.
	 * 
	 * @param dataRow The parent row of the data cell
	 * @param cellData the source cell data
	 * @throws ApplicationException if an exception was encountered
	 */
	private void createDataCell(DataRow dataRow, CellData cellData)	throws ApplicationException 
	{
		BusinessObjectData businessObjectData = prepareDataCellBO(dataRow, cellData);
		validationService.validate(businessObjectData);
		// Create the cell data
		objectCreationService.create(businessObjectData);
	}

	/**
	 * Prepare a {@link BusinessObjectData} from a {@link TableData}.
	 * 
	 * @param tableData the table data
	 * @return the initialized business object
	 * @throws ApplicationException if an exception was encountered
	 */
	private BusinessObjectData prepareTableDataBO(TableData tableData) throws ApplicationException 
	{
		BusinessObjectData businessObjectData = BusinessObjectUtil.prepareBusinessObject("DataTable", 
				"com.nathanclaire.alantra.datasource.model.DataTable", "DATASOURCE", new HashMap<String, Object>());
		businessObjectData.setDataValue("primEntityNm", "DataTable");
		businessObjectData.setDataValue("dataInputJob", dataInputJobService.findById(tableData.getJobId()));
		businessObjectData.setDataValue("primRecRead", tableData.getPrimEntityRecordsRead());
		businessObjectData.setDataValue("primRecCreated", tableData.getPrimEntityRecordsCreated());
		businessObjectData.setDataValue("primRecRejected", tableData.getPrimEntityRecordsRejected());
		businessObjectData.setDataValue("totRecRead", tableData.getPrimEntityRecordsRead());
		businessObjectData.setDataValue("totRecCreated",  tableData.getPrimEntityRecordsCreated());
		businessObjectData.setDataValue("importStatusFg", tableData.getInputStatusFg());
		return businessObjectData;
	}
	
	/**
	 * Prepare a {@link BusinessObjectData} for a {@link DataRow}.
	 * @param dataTable 
	 * 
	 * @return the initialized business object
	 * @throws ApplicationException if an exception was encountered
	 */
	private BusinessObjectData prepareDataRowBO(DataTable dataTable, RowData rowData) throws ApplicationException {
		BusinessObjectData businessObjectData =  BusinessObjectUtil.prepareBusinessObject("DataRow", 
				"com.nathanclaire.alantra.datasource.model.DataRow", "DATASOURCE", new HashMap<String, Object>());
		businessObjectData.setDataValue("dataTable", dataTable);
		businessObjectData.setDataValue("importStatusFg", rowData.getInputStatusFg());
		businessObjectData.setDataValue("isHeaderFg", (rowData.isHeaderRow() ? 'Y' : 'N'));
		return businessObjectData;
	}
	
	/**
	 * Prepare a {@link BusinessObjectData} for a {@link DataCell}.
	 * @param dataRow 
	 * 
	 * @return the initialized business object
	 * @throws ApplicationException if an exception was encountered
	 */
	private BusinessObjectData prepareDataCellBO(DataRow dataRow, CellData cellData) throws ApplicationException {
		BusinessObjectData businessObjectData = BusinessObjectUtil.prepareBusinessObject("DataCell", 
				"com.nathanclaire.alantra.datasource.model.DataCell", "DATASOURCE", new HashMap<String, Object>());
		businessObjectData.setDataValue("dataRow", dataRow);
		businessObjectData.setDataValue("cellData", (cellData.getData() != null ? cellData.getData().toString() : ""));
		businessObjectData.setDataValue("importStatusFg", cellData.getInputStatusFg());
		businessObjectData.setDataValue("dataField", dataFieldService.findById(cellData.getDataFieldId()));
		return businessObjectData;
	}
	
}
