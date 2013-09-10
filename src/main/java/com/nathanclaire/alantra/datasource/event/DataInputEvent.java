/**
 * 
 */
package com.nathanclaire.alantra.datasource.event;

import com.nathanclaire.alantra.datasource.etl.util.TableData;

/**
 * @author Edward Banfa
 *
 */
public class DataInputEvent {
	
	private Integer inputJobId;
	private String inputJobCode;
	private TableData tableData;
	/**
	 * @param inputJobId
	 * @param tableData
	 */
	public DataInputEvent(Integer inputJobId, TableData tableData) {
		this.inputJobId = inputJobId;
		this.tableData = tableData;
	}

	/**
	 * @param inputJobId
	 * @param inputJobCode
	 * @param tableData
	 */
	public DataInputEvent(Integer inputJobId, String inputJobCode,
			TableData tableData) {
		super();
		this.inputJobId = inputJobId;
		this.inputJobCode = inputJobCode;
		this.tableData = tableData;
	}

	/**
	 * @param inputJobId
	 */
	public DataInputEvent(Integer inputJobId) {
		this.inputJobId = inputJobId;
	}

	/**
	 * @return the inputJobId
	 */
	public Integer getInputJobId() {
		return inputJobId;
	}

	/**
	 * @param inputJobId the inputJobId to set
	 */
	public void setInputJobId(Integer inputJobId) {
		this.inputJobId = inputJobId;
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

	/**
	 * @return the inputJobCode
	 */
	public String getInputJobCode() {
		return inputJobCode;
	}

	/**
	 * @param inputJobCode the inputJobCode to set
	 */
	public void setInputJobCode(String inputJobCode) {
		this.inputJobCode = inputJobCode;
	}

}
