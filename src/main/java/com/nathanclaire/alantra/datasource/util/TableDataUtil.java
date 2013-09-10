/**
 * 
 */
package com.nathanclaire.alantra.datasource.util;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.datasource.etl.util.CellData;
import com.nathanclaire.alantra.datasource.etl.util.TableData;
import com.nathanclaire.alantra.datasource.model.Data;
import com.nathanclaire.alantra.datasource.model.DataChannel;

/**
 * @author Edward Banfa
 *
 */
public class TableDataUtil {
	
	public static CellData initializeCellData(String cellName, String cellDataType)
			throws ApplicationException 
	{		
		CellData cellData = new CellData();
		cellData.setName(cellName);
		cellData.setDataType(cellDataType);
		return cellData;
	}
	
	public static TableData initializeTableData(DataChannel channel, Data data) throws ApplicationException 
	{
		TableData tableDataToBePopulated = new TableData();
		tableDataToBePopulated.setSourceServiceCode(channel.getCode());
		tableDataToBePopulated.setSourceChannelText(channel.getCode());
		tableDataToBePopulated.setDataId(data.getId());
		tableDataToBePopulated.setChannelId(channel.getId());
		return tableDataToBePopulated;
	}
	
	public static void handleCellReadError(Object data, CellData cellData, String statusText, String statusDescription) {
		cellData.setData(data);
		cellData.setErrors(true);
		cellData.setStatusText(statusText);
		cellData.setStatusDescription(statusDescription);
	}

}
