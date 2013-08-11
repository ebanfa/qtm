/**
 * 
 */
package com.nathanclaire.alantra.datasource.etl.extractors;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.service.entity.BaseEntityService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.StringUtil;
import com.nathanclaire.alantra.datasource.etl.CellDataLite;
import com.nathanclaire.alantra.datasource.etl.RowDataLite;
import com.nathanclaire.alantra.datasource.etl.TableDataLite;
import com.nathanclaire.alantra.datasource.model.Data;
import com.nathanclaire.alantra.datasource.model.DataChannel;
import com.nathanclaire.alantra.datasource.model.DataChannelCategory;
import com.nathanclaire.alantra.datasource.model.DataChannelType;
import com.nathanclaire.alantra.datasource.model.DataField;
import com.nathanclaire.alantra.datasource.model.DataStructure;


/**
 * @author Edward Banfa 
 *
 */
public abstract class BaseDataExtractor<T> {

	public static final String DEFAULT_DATE_FORMAT = "dd/MM/yyyy";
	public static final String DATE = "DATE";
	public static final String STRING = "STRING";
	public static final String DECIMAL = "DECIMAL";
	public static final String INTEGER = "INTEGER";
	public static final String RELATIONSHIP = "RELATIONSHIP";

	public static final String USR_INVALID_DATE_STRING = "BaseDataExtractor.USR_INVALID_DATE_STRING";
	public static final String USR_INCORRECT_NUMERIC_TYPE_FOR_STRING = "BaseDataExtractor.USR_INCORRECT_NUMERIC_TYPE_FOR_STRING";
	public static final String USR_INCORRECT_STRING_TYPE_CELL = "BaseDataExtractor.USR_INCORRECT_STRING_TYPE_CELL";
	public static final String USR_UNKNOWN_CELL_DATA_TYPE = "BaseDataExtractor.USR_UNKNOWN_CELL_DATA_TYPE";
	public static final String USR_DATA_EXTRACTION_ERROR = "BaseDataExtractor.USR_DATA_EXTRACTION_ERROR";
	
	private static final String CONFIG_ERROR_NO_CHANNEL_FOUND = "BaseProcessService.DATA_IMPORT_SERVICE_NOT_FOUND";

	private Logger logger = LoggerFactory.getLogger(BaseDataExtractor.class);
	/**
	 * @param data
	 * @return
	 * @throws ApplicationException
	 */
	public TableDataLite extract(Data data) throws ApplicationException 
	{
		logger.debug("Extracting data {}", data.getCode());
		return extractData(data, initializeTableData(data));
	}

	/**
	 * @param data
	 * @param tableDataToBePopulated
	 * @return
	 * @throws ApplicationException 
	 */
	protected TableDataLite initializeTableData(Data data) throws ApplicationException 
	{
		TableDataLite tableDataToBePopulated = new TableDataLite();
		DataChannel dataChannel = data.getDataChannel();
		if(dataChannel == null)
			throw new ApplicationException(CONFIG_ERROR_NO_CHANNEL_FOUND);
		tableDataToBePopulated.setSourceServiceCode(dataChannel.getCode());
		tableDataToBePopulated.setSourceChannelText(dataChannel.getCode());
		tableDataToBePopulated.setDataId(data.getId());
		tableDataToBePopulated.setChannelId(data.getDataChannel().getId());
		return tableDataToBePopulated;
	}

	/**
	 * @param data
	 * @param tableDataToBePopulated
	 * @param dataStructure
	 * @return
	 * @throws ApplicationException
	 */
	protected abstract TableDataLite extractData(Data data, TableDataLite tableDataToBePopulated) throws ApplicationException;
		
	
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
			TableDataLite tableToBePopulated, List<T[]> extractedData) throws ApplicationException {
		logger.debug("Processing {} row ", extractedData.size());
		int rowCount = 0;
		int recordsRead = 0;
		for(T[] row : extractedData)
		{
			if(rowCount==0) 	
			{
				rowCount++;
				if(skipFirstRow(dataStructure))
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
						CellDataLite cellDataLite = getCellData(dataField, row[i], currentRow);
						cellDataLite.setDataFieldId(dataField.getId());
						currentRow.getColumns().add(cellDataLite);
					}
			}
			tableToBePopulated.getRows().add(currentRow);
			rowCount++;
			recordsRead ++;
		}
		return recordsRead;
	}
	
	protected CellDataLite getCellData(DataField dataField, T data, RowDataLite currentRow) throws ApplicationException
	{
		if(dataField.getDataFieldType().getCode().equals(STRING)) return processStringDataField(dataField, data, currentRow);
		else if(dataField.getDataFieldType().getCode().equals(INTEGER)) return processIntegerDataField(dataField, data, currentRow);
		else if(dataField.getDataFieldType().getCode().equals(DECIMAL)) return processDecimalDataField(dataField, data, currentRow);
		else if(dataField.getDataFieldType().getCode().equals(DATE)) return processDateDataField(dataField, data, currentRow);
		else if(dataField.getDataFieldType().getCode().equals(RELATIONSHIP)) 
			return processRelationshipDataField(dataField, data, currentRow);
		else
			throw new ApplicationException(USR_UNKNOWN_CELL_DATA_TYPE);
	}
	
	/**
	 * @param dataField
	 * @param data
	 * @param currentRow
	 * @return
	 * @throws ApplicationException
	 */
	protected abstract CellDataLite processStringDataField(DataField dataField, T data, RowDataLite currentRow) throws ApplicationException;
	
	/**
	 * @param dataField
	 * @param data
	 * @param currentRow
	 * @return
	 * @throws ApplicationException
	 */
	protected abstract CellDataLite processIntegerDataField(DataField dataField, T data, RowDataLite currentRow) throws ApplicationException;
	
	/**
	 * @param dataField
	 * @param data
	 * @param currentRow
	 * @return
	 * @throws ApplicationException
	 */
	protected abstract CellDataLite processDecimalDataField(DataField dataField, T data, RowDataLite currentRow) throws ApplicationException;
	
	/**
	 * @param dataField
	 * @param data
	 * @param currentRow
	 * @return
	 * @throws ApplicationException
	 */
	protected abstract CellDataLite processDateDataField(DataField dataField, T data, RowDataLite currentRow) throws ApplicationException;
	
	/**
	 * @param dataField
	 * @param data
	 * @param currentRow
	 * @return
	 * @throws ApplicationException
	 */
	protected abstract CellDataLite processRelationshipDataField(DataField dataField, T data, RowDataLite currentRow) throws ApplicationException;

	/**
	 * @param dataField
	 * @param cell
	 * @return
	 * @throws ApplicationException
	 */
	protected CellDataLite initializeCell(String cellName, String cellDataType)
			throws ApplicationException 
	{		
		CellDataLite cellDataLite = new CellDataLite();
		cellDataLite.setName(cellName);
		cellDataLite.setDataType(cellDataType);
		return cellDataLite;
	}
	
	/**
	 * @param data
	 * @param loadedData
	 */
	protected DataChannelCategory getDataChannelCategory(Data data) 
	{
		DataChannel dataSource = data.getDataChannel();
		DataChannelType dataChannelType = dataSource.getDataChannelType();
		return dataChannelType.getDataChannelCategory();
	}
	
	/**
	 * @param dataSource
	 * @return
	 */
	protected boolean skipFirstRow(DataStructure dataSourceStructure)
	{
		Character skipFirstRowFG = dataSourceStructure.getSkipFirstFg();
		if(skipFirstRowFG == null)	return false;
		if(skipFirstRowFG.equals(BaseEntityService.ENTITY_FLAG_YES)) return true;
		return false;
	}
	
	/**
	 * @param dataSourceStructure
	 * @return
	 */
	protected boolean isStrict(DataStructure dataSourceStructure)	
	{
		Character strictFG = dataSourceStructure.getStrictFg();
		if(strictFG == null) return false;
		if(strictFG.equals(BaseEntityService.ENTITY_FLAG_YES)) return true;
		return false;
	}

	/**
	 * @param data
	 * @param dataTypeCode
	 * @return
	 */
	protected String cleanDecimal(String data) 
	{
		String decimal = data.toString();
		if(decimal.contains(",")) data = decimal.replaceAll(",", "").trim();
		return data;
	}
	/**
	 * @param dateFormat
	 * @param dateString
	 * @return
	 * @throws ApplicationException
	 */
	protected Date parseDateString(String dateFormat, String dateString) throws ApplicationException {
		try {
			// Convert the string to a date using the default date format
			if(!StringUtil.isValidString(dateFormat))
				dateFormat = DEFAULT_DATE_FORMAT;
			DateTime dateTime = DateTime.parse(dateString, DateTimeFormat.forPattern(dateFormat));
		    return dateTime.toDate();
		} catch (Exception e) {
			throw new ApplicationException(USR_INVALID_DATE_STRING);
		}
	}
	/**
	 * @param cell
	 * @param cellDataLite
	 * @param e
	 */
	protected void handleCellReadError(Object data, CellDataLite cellDataLite, String statusText, String statusDescription) {
		cellDataLite.setData(data);
		cellDataLite.setErrors(true);
		cellDataLite.setStatusText(statusText);
		cellDataLite.setStatusDescription(statusDescription);
	}

}
