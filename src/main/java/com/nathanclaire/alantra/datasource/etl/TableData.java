/**
 * 
 */
package com.nathanclaire.alantra.datasource.etl;

import java.util.List;

/**
 * @author Edward Banfa 
 *
 */
public interface TableData {
	
	public int getRecordsRead();
	
	public void setRecordsRead(int recordsRead);
	
	public int getRecordsCreated();
	
	public void setRecordsCreated(int recordsCreated);
	
	public int getTxnMatched();
	
	public void setTxnMatched(int matchedTxns);
	
	public int getTxnUnMatched();
	
	public void setTxnUnMatched(int unmatchedTxns);

	/**
	 * @param rows
	 */
	public void setRows(List<RowData> rows);
	
	/**
	 * @return
	 */
	public List<RowData> getRows();
}
