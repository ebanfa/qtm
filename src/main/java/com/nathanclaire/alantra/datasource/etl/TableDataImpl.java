/**
 * 
 */
package com.nathanclaire.alantra.datasource.etl;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Edward Banfa 
 *
 */
public class TableDataImpl implements TableData {
	
	private int recordsRead;
	private int recordsCreated;
	private int matchedTxn;
	private int unmatchedTxns;
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.TableData#getRecordsRead()
	 */
	@Override
	public int getRecordsRead() {
		return recordsRead;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.TableData#setRecordsRead(int)
	 */
	@Override
	public void setRecordsRead(int recordsRead) {
		this.recordsRead = recordsRead;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.TableData#getRecordsCreated()
	 */
	@Override
	public int getRecordsCreated() {
		return recordsCreated;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.TableData#setRecordsCreated(int)
	 */
	@Override
	public void setRecordsCreated(int recordsCreated) {
		this.recordsCreated = recordsCreated;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.TableData#getTxnMatched()
	 */
	@Override
	public int getTxnMatched() {
		return matchedTxn;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.TableData#setTxnMatched(int)
	 */
	@Override
	public void setTxnMatched(int matchedTxn) {
		this.matchedTxn = matchedTxn;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.TableData#getTxnUmMatched()
	 */
	@Override
	public int getTxnUnMatched() {
		return unmatchedTxns;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.TableData#setTxnUnMatched(int)
	 */
	@Override
	public void setTxnUnMatched(int unmatchedTxns) {
		this.unmatchedTxns = unmatchedTxns;
	}

	List<RowData> rows = new ArrayList<RowData>();
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.TableData#setRows(java.util.List)
	 */
	@Override
	public void setRows(List<RowData> rows) {
		this.rows = rows;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.TableData#getRows()
	 */
	@Override
	public List<RowData> getRows() {
		return rows;
	}

}
