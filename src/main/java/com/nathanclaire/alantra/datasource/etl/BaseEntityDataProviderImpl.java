/**
 * 
 */
package com.nathanclaire.alantra.datasource.etl;

import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.channel.service.process.TransactionProcessorService;

/**
 * @author Edward Banfa 
 *
 */
public abstract class BaseEntityDataProviderImpl {
	
	private static final String TABLEDATA_NOT_PROVIDED = null;

	private TableData tableData;
	
	private final String DATE_FORMAT = "dd/MM/yyyy";
	
	private Logger logger = LoggerFactory.getLogger(BaseEntityDataProviderImpl.class);

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.EntityDataProvider#loadEntityData()
	 */
	public void loadEntityData(TableData tableData) throws ApplicationException{
		// 1. Loop through table
		int rowCount = 0;
		int rowsCreated = 0;
		if (tableData == null)
			throw new ApplicationException(TABLEDATA_NOT_PROVIDED);
		this.tableData = tableData;
		for (RowData rowData: tableData.getRows())
		{
			if(rowCount < 1){
				rowCount ++;
				continue;
			}
			// 2. For each row
			try {
				processRow(rowData);
			} catch (ApplicationException e) {
				if(e.getCode().equals(TransactionProcessorService.ACCOUNT_NO_NOT_PROVIDED) | 
						e.getCode().equals(TransactionProcessorService.CHEQUE_NO_NOT_PROVIDED) | 
						e.getCode().equals(TransactionProcessorService.TRANSACTION_ALREADY_EXISTS))
					logger.error(e.getMessage());
				else
					throw e;
			}
				rowsCreated ++;
			rowCount ++;
			tableData.setRecordsCreated(rowsCreated);
		}
	}

	/**
	 * @param rowData
	 * @throws ApplicationException 
	 */
	protected abstract void processRow(RowData rowData) throws ApplicationException;
	
	/**
	 * Uses joda time to convert a string to a java util date
	 * @param dateString
	 * @return
	 */
	protected Date convertStringToJavaDate(String dateString)
	{
		DateTimeFormatter formatter = DateTimeFormat.forPattern(DATE_FORMAT);
		DateTime dt = formatter.parseDateTime(dateString);
		return dt.toDate();
	}

	/**
	 * @return the tableData
	 */
	public TableData getTableData() {
		return tableData;
	}

	/**
	 * @param tableData the tableData to set
	 */
	public void setTableData(TableData tableData) {
		this.tableData = tableData;
	}

}
