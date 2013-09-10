/**
 * 
 */
package com.nathanclaire.alantra.datasource.etl.extraction;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.EntityUtil;
import com.nathanclaire.alantra.base.util.ErrorCodes;
import com.nathanclaire.alantra.base.util.ExceptionUtil;
import com.nathanclaire.alantra.datasource.etl.util.CellData;
import com.nathanclaire.alantra.datasource.etl.util.RowDataLite;
import com.nathanclaire.alantra.datasource.etl.util.TableData;
import com.nathanclaire.alantra.datasource.model.Data;
import com.nathanclaire.alantra.datasource.model.DataChannel;
import com.nathanclaire.alantra.datasource.model.DataField;
import com.nathanclaire.alantra.datasource.model.DataInputJob;
import com.nathanclaire.alantra.datasource.model.DataStructure;
import com.nathanclaire.alantra.datasource.service.entity.DataFieldTypeService;
import com.nathanclaire.alantra.datasource.service.process.DataInputService;
import com.nathanclaire.alantra.datasource.service.process.DataService;
import com.nathanclaire.alantra.datasource.util.TableDataUtil;


/**
 * @author Edward Banfa 
 *
 */
public abstract class BaseDataExtractor<T> {

	public static final String USR_INCORRECT_NUMERIC_TYPE_FOR_STRING = "BaseDataExtractor.USR_INCORRECT_NUMERIC_TYPE_FOR_STRING";
	public static final String USR_INCORRECT_STRING_TYPE_CELL = "BaseDataExtractor.USR_INCORRECT_STRING_TYPE_CELL";
	public static final String USR_UNKNOWN_CELL_DATA_TYPE = "BaseDataExtractor.USR_UNKNOWN_CELL_DATA_TYPE";
	public static final String USR_DATA_EXTRACTION_ERROR = "BaseDataExtractor.USR_DATA_EXTRACTION_ERROR";
	
	@Inject DataService dataService;
	@Inject DataInputService dataInputService;
	private Logger logger = LoggerFactory.getLogger(BaseDataExtractor.class);
	

	public TableData extract(DataInputJob inputJob) throws ApplicationException 
	{
		dataInputService.validateDataInputJob(inputJob);
		Data data = dataService.getData(inputJob);
		return extract(dataService.getDataChannel(inputJob), data);
	}
	
	public TableData extract(DataChannel channel, Data data) throws ApplicationException 
	{
		try {
			return extractData(channel, data, TableDataUtil.initializeTableData(channel, data));
		} catch (Exception e) {
			ExceptionUtil.processException(e, ErrorCodes.BDE_DATA_EXTRACTOR_ERROR_CD);
		}
		return null;
	}
	
	/**
	 * @param dataStructure
	 * @param dataFields
	 * @param tableToBePopulated
	 * @param extractedData
	 * @return
	 * @throws ApplicationException 
	 * @throws IOException
	 */
	protected int processRows(DataStructure dataStructure, Set<DataField> dataFields, 
			TableData tableToBePopulated, List<T[]> extractedData) throws ApplicationException {
		logger.debug("Processing {} row ", extractedData.size());
		int rowCount = 0;
		int recordsRead = 0;
		for(T[] row : extractedData)
		{
			if(rowCount==0) 	
			{
				rowCount++;
				if(dataService.skipFirstRow(dataStructure))
					continue;
			}
			logger.debug("Row has {} columns ", row.length);
			// Fetch all columns in a given row
			RowDataLite currentRow = new RowDataLite();
			for(int i = 0; i < row.length; i++) 
			{
				for(DataField dataField:dataFields)
					if(dataField.getSeqNo() == (i + 1))
					{
						CellData cellData = getCellData(dataField, row[i], currentRow);
						cellData.setDataFieldId(dataField.getId());
						currentRow.getColumns().add(cellData);
					}
			}
			tableToBePopulated.getRows().add(currentRow);
			rowCount++;
			recordsRead ++;
		}
		return recordsRead;
	}
	
	protected CellData getCellData(DataField dataField, T data, RowDataLite currentRow) throws ApplicationException
	{
		EntityUtil.returnOrThrowIfParamsArrayContainsNull(
				new Object[] {dataField, data, currentRow, (dataField!=null) ? dataField.getDataFieldType(): null});
		
		String dataFieldTypeCode = dataField.getDataFieldType().getCode();
		CellData cellData = TableDataUtil.initializeCellData(dataField.getCode(), dataFieldTypeCode);
		
		if(dataFieldTypeCode.equals(DataFieldTypeService.STRING)) 
			return processStringDataField(dataField, cellData, data, currentRow);
		else if(dataFieldTypeCode.equals(DataFieldTypeService.INTEGER)) 
			return processIntegerDataField(dataField, cellData, data, currentRow);
		else if(dataFieldTypeCode.equals(DataFieldTypeService.DECIMAL)) 
			return processDecimalDataField(dataField, cellData, data, currentRow);
		else if(dataFieldTypeCode.equals(DataFieldTypeService.DATE)) 
			return processDateDataField(dataField, cellData, data, currentRow);
		else if(dataFieldTypeCode.equals(DataFieldTypeService.RELATIONSHIP)) 
			return processRelationshipDataField(dataField, cellData, data, currentRow);
		else
			throw new ApplicationException(ErrorCodes.BDE_DATA_EXTRACTOR_ERROR_CD, ErrorCodes.UNKNOWN_CELL_DATA_TYPE_ERROR_MSG);
	}

	/**
	 * @param data
	 * @param tableDataToBePopulated
	 * @param dataStructure
	 * @return
	 * @throws ApplicationException
	 */
	protected abstract TableData extractData(DataChannel channel, Data data, TableData tableDataToBePopulated) throws ApplicationException;
		
	/**
	 * @param dataField
	 * @param data
	 * @param currentRow
	 * @return
	 * @throws ApplicationException
	 */
	protected abstract CellData processStringDataField(DataField dataField, CellData cellData, T data, RowDataLite currentRow) throws ApplicationException;
	
	/**
	 * @param dataField
	 * @param data
	 * @param currentRow
	 * @return
	 * @throws ApplicationException
	 */
	protected abstract CellData processIntegerDataField(DataField dataField, CellData cellData, T data, RowDataLite currentRow) throws ApplicationException;
	
	/**
	 * @param dataField
	 * @param data
	 * @param currentRow
	 * @return
	 * @throws ApplicationException
	 */
	protected abstract CellData processDecimalDataField(DataField dataField, CellData cellData, T data, RowDataLite currentRow) throws ApplicationException;
	
	/**
	 * @param dataField
	 * @param data
	 * @param currentRow
	 * @return
	 * @throws ApplicationException
	 */
	protected abstract CellData processDateDataField(DataField dataField, CellData cellData, T data, RowDataLite currentRow) throws ApplicationException;
	
	/**
	 * @param dataField
	 * @param data
	 * @param currentRow
	 * @return
	 * @throws ApplicationException
	 */
	protected abstract CellData processRelationshipDataField(DataField dataField, CellData cellData, T data, RowDataLite currentRow) throws ApplicationException;

}
