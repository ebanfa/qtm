/**
 * 
 */
package com.nathanclaire.alantra.datasource.etl.extractors;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.datasource.etl.RowData;
import com.nathanclaire.alantra.datasource.etl.TableData;
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
	
	private static final String CONFIG_ERROR_NO_CHANNEL_FOUND = "BaseProcessService.DATA_IMPORT_SERVICE_NOT_FOUND";

	/**
	 * @param data
	 * @return
	 * @throws ApplicationException
	 */
	public TableData extract(Data data) throws ApplicationException 
	{
		return extractData(data, initializeTableData(data));
	}

	/**
	 * @param data
	 * @param tableDataToBePopulated
	 * @return
	 * @throws ApplicationException 
	 */
	protected TableData initializeTableData(Data data) throws ApplicationException 
	{
		TableData tableDataToBePopulated = new TableData();
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
	protected abstract TableData extractData(Data data, TableData tableDataToBePopulated) throws ApplicationException;
		
	
	/**
	 * @param dataStructure
	 * @param dataFields
	 * @param tableToBePopulated
	 * @param extractedData
	 * @return
	 * @throws IOException
	 */
	protected int processRows(DataStructure dataStructure, Set<DataField> dataFields, 
			TableData tableToBePopulated, List<T[]> extractedData) {
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
			// Fetch all columns in a given row
			RowData currentRow = new RowData();
			for(int i = 0; i < row.length; i++) 
			{
				for(DataField dataField:dataFields)
					if(dataField.getSeqNo() == (i + 1))
						getCellData(dataField.getCode(), dataField.getDataFieldType().getCode(), row[i], currentRow);
			}
			tableToBePopulated.getRows().add(currentRow);
			rowCount++;
			recordsRead ++;
		}
		return recordsRead;
	}

	protected abstract void getCellData(String cellName, String cellDataType, T cellData, RowData currentRow);

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
		if(skipFirstRowFG.equals('Y')) return true;
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
		if(strictFG.equals('Y')) return true;
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

}
