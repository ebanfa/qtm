/**
 * 
 */
package com.nathanclaire.alantra.datasource.etl.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Edward Banfa 
 *
 */
public class TableData {
	
	private Integer jobId;
	private Integer dataId;
	private Integer channelId;
	private Integer dataInputId;
	private Integer jobSummaryId;
	private Integer dataStructureId;
	private String businessObjectClassName;
    private String  primEntityName;
    private Integer primEntityRecordsCreated=0;
    private Integer primEntityRecordsRejected=0;
    private Integer primEntityInputPercenatge=0;
    private Character inputStatusFg = 'C';
	
	private Integer userId;
	private Integer customerId;
	
	private String  tableInputStatus;
	private String  sourceServiceCode;
	private String  sourceChannelText;
    private Integer recordsRead=0;
    private Integer recordsRejected=0;
    private Integer recordsReadPercentage=0;
    private Integer txnsMatched=0;
    private Integer txnsUnmatched =0;
    private String  txnMatchPercentage;
    private boolean errors;
	private String statusText;
	private String statusDescription;

	private List<RowData> rows = new ArrayList<RowData>();

	/**
	 * @return the tableInputStatus
	 */
	public String getTableInputStatus() {
		return tableInputStatus;
	}

	/**
	 * @param tableInputStatus the tableInputStatus to set
	 */
	public void setTableInputStatus(String tableInputStatus) {
		this.tableInputStatus = tableInputStatus;
	}

	/**
	 * @return the sourceServiceCode
	 */
	public String getSourceServiceCode() {
		return sourceServiceCode;
	}

	/**
	 * @param sourceServiceCode the sourceServiceCode to set
	 */
	public void setSourceServiceCode(String sourceServiceCode) {
		this.sourceServiceCode = sourceServiceCode;
	}

	/**
	 * @return the sourceChannelText
	 */
	public String getSourceChannelText() {
		return sourceChannelText;
	}

	/**
	 * @param sourceChannelText the sourceChannelText to set
	 */
	public void setSourceChannelText(String sourceChannelText) {
		this.sourceChannelText = sourceChannelText;
	}

	/**
	 * @return the recordsRead
	 */
	public Integer getRecordsRead() {
		return recordsRead;
	}

	/**
	 * @param recordsRead the recordsRead to set
	 */
	public void setRecordsRead(Integer recordsRead) {
		this.recordsRead = recordsRead;
	}

	/**
	 * @return the recordsRejected
	 */
	public Integer getRecordsRejected() {
		return recordsRejected;
	}

	/**
	 * @param recordsRejected the recordsRejected to set
	 */
	public void setRecordsRejected(Integer recordsRejected) {
		this.recordsRejected = recordsRejected;
	}

	/**
	 * @return the recordsReadPercentage
	 */
	public Integer getRecordsReadPercentage() {
		return recordsReadPercentage;
	}

	/**
	 * @param recordsReadPercentage the recordsReadPercentage to set
	 */
	public void setRecordsReadPercentage(Integer recordsReadPercentage) {
		this.recordsReadPercentage = recordsReadPercentage;
	}

	/**
	 * @return the primEntityName
	 */
	public String getPrimEntityName() {
		return primEntityName;
	}

	/**
	 * @param primEntityName the primEntityName to set
	 */
	public void setPrimEntityName(String primEntityName) {
		this.primEntityName = primEntityName;
	}

	/**
	 * @return the primEntityRecordsCreated
	 */
	public Integer getPrimEntityRecordsCreated() {
		return primEntityRecordsCreated;
	}

	/**
	 * @param primEntityRecordsCreated the primEntityRecordsCreated to set
	 */
	public void setPrimEntityRecordsCreated(Integer primEntityRecordsCreated) {
		this.primEntityRecordsCreated = primEntityRecordsCreated;
	}

	/**
	 * @return the primEntityRecordsRejected
	 */
	public Integer getPrimEntityRecordsRejected() {
		return primEntityRecordsRejected;
	}

	/**
	 * @param primEntityRecordsRejected the primEntityRecordsRejected to set
	 */
	public void setPrimEntityRecordsRejected(Integer primEntityRecordsRejected) {
		this.primEntityRecordsRejected = primEntityRecordsRejected;
	}

	/**
	 * @return the primEntityInputPercenatge
	 */
	public Integer getPrimEntityInputPercenatge() {
		return primEntityInputPercenatge;
	}

	/**
	 * @param primEntityInputPercenatge the primEntityInputPercenatge to set
	 */
	public void setPrimEntityInputPercenatge(Integer primEntityInputPercenatge) {
		this.primEntityInputPercenatge = primEntityInputPercenatge;
	}

	/**
	 * @return the txnsMatched
	 */
	public Integer getTxnsMatched() {
		return txnsMatched;
	}

	/**
	 * @param txnsMatched the txnsMatched to set
	 */
	public void setTxnsMatched(Integer txnsMatched) {
		this.txnsMatched = txnsMatched;
	}

	/**
	 * @return the txnsUnmatched
	 */
	public Integer getTxnsUnmatched() {
		return txnsUnmatched;
	}

	/**
	 * @param txnsUnmatched the txnsUnmatched to set
	 */
	public void setTxnsUnmatched(Integer txnsUnmatched) {
		this.txnsUnmatched = txnsUnmatched;
	}

	/**
	 * @return the txnMatchPercentage
	 */
	public String getTxnMatchPercentage() {
		return txnMatchPercentage;
	}

	/**
	 * @param txnMatchPercentage the txnMatchPercentage to set
	 */
	public void setTxnMatchPercentage(String txnMatchPercentage) {
		this.txnMatchPercentage = txnMatchPercentage;
	}

	/**
	 * @return the rows
	 */
	public List<RowData> getRows() {
		return rows;
	}

	/**
	 * @param rows the rows to set
	 */
	public void setRows(List<RowData> rows) {
		this.rows = rows;
	}

	/**
	 * @return the jobId
	 */
	public Integer getJobId() {
		return jobId;
	}

	/**
	 * @param jobId the jobId to set
	 */
	public void setJobId(Integer jobId) {
		this.jobId = jobId;
	}

	/**
	 * @return the channelId
	 */
	public Integer getChannelId() {
		return channelId;
	}

	/**
	 * @param channelId the channelId to set
	 */
	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}

	/**
	 * @return the dataId
	 */
	public Integer getDataId() {
		return dataId;
	}

	/**
	 * @param dataId the dataId to set
	 */
	public void setDataId(Integer dataId) {
		this.dataId = dataId;
	}

	/**
	 * @return the dataInputId
	 */
	public Integer getDataInputId() {
		return dataInputId;
	}

	/**
	 * @param dataInputId the dataInputId to set
	 */
	public void setDataInputId(Integer dataInputId) {
		this.dataInputId = dataInputId;
	}

	/**
	 * @return the dataStructureId
	 */
	public Integer getDataStructureId() {
		return dataStructureId;
	}

	/**
	 * @param dataStructureId the dataStructureId to set
	 */
	public void setDataStructureId(Integer dataStructureId) {
		this.dataStructureId = dataStructureId;
	}

	/**
	 * @return the jobSummaryId
	 */
	public Integer getJobSummaryId() {
		return jobSummaryId;
	}

	/**
	 * @param jobSummaryId the jobSummaryId to set
	 */
	public void setJobSummaryId(Integer jobSummaryId) {
		this.jobSummaryId = jobSummaryId;
	}

	public int getPrimEntityRecordsRead() {
		// TODO Auto-generated method stub
		return 0;
	}

	public Integer getSecEntityRecordsRead() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getTotalEntitiesRead() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * @return the statusText
	 */
	public String getStatusText() {
		return statusText;
	}

	/**
	 * @param statusText the statusText to set
	 */
	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}

	/**
	 * @return the statusDescription
	 */
	public String getStatusDescription() {
		return statusDescription;
	}

	/**
	 * @param statusDescription the statusDescription to set
	 */
	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}

	/**
	 * @return the errors
	 */
	public boolean isErrors() {
		return errors;
	}

	/**
	 * @param errors the errors to set
	 */
	public void setErrors(boolean errors) {
		this.errors = errors;
	}

	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * @return the customerId
	 */
	public Integer getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TableData [primEntityName=" + primEntityName + ", errors="
				+ errors + ", rows=" + rows.size() + "]";
	}

	/**
	 * @return the businessObjectClassName
	 */
	public String getBusinessObjectClassName() {
		return businessObjectClassName;
	}

	/**
	 * @param businessObjectClassName the businessObjectClassName to set
	 */
	public void setBusinessObjectClassName(String businessObjectClassName) {
		this.businessObjectClassName = businessObjectClassName;
	}

	/**
	 * @return the inputStatusFg
	 */
	public Character getInputStatusFg() {
		return inputStatusFg;
	}

	/**
	 * @param inputStatusFg the inputStatusFg to set
	 */
	public void setInputStatusFg(Character inputStatusFg) {
		this.inputStatusFg = inputStatusFg;
	}
	
	
	
}
