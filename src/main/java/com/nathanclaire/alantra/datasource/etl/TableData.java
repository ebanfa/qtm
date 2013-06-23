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
public class TableData {
	
	private Integer jobId;
	private Integer channelId;
	private Integer dataId;
	private Integer dataInputId;
	private Integer dataStructureId;
	private Integer jobSummaryId;
	
	private String tableInputStatus;
	private String sourceServiceCode;
	private String sourceChannelText;
    private int recordsRead=0;
    private int recordsRejected=0;
    private int recordsReadPercentage=0;
    private String primEntityName;
    private int primEntityRecordsCreated=0;
    private int primEntityRecordsRejected=0;
    private int primEntityInputPercenatge=0;
    private String secEntityName;
    private Integer secEntityRecordsCreated=0;
    private Integer secEntityRecordsRejected=0;
    private Integer secEntityInputPercenatge=0;
    private int totalEntitiesCreated=0;
    private int totalEntitiesRejected=0;
    private int totalEntitiesInputPercentage;
    private Integer txnsMatched=0;
    private Integer txnsUnmatched =0;
    private String txnMatchPercentage;

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
	public int getRecordsRead() {
		return recordsRead;
	}

	/**
	 * @param recordsRead the recordsRead to set
	 */
	public void setRecordsRead(int recordsRead) {
		this.recordsRead = recordsRead;
	}

	/**
	 * @return the recordsRejected
	 */
	public int getRecordsRejected() {
		return recordsRejected;
	}

	/**
	 * @param recordsRejected the recordsRejected to set
	 */
	public void setRecordsRejected(int recordsRejected) {
		this.recordsRejected = recordsRejected;
	}

	/**
	 * @return the recordsReadPercentage
	 */
	public int getRecordsReadPercentage() {
		return recordsReadPercentage;
	}

	/**
	 * @param recordsReadPercentage the recordsReadPercentage to set
	 */
	public void setRecordsReadPercentage(int recordsReadPercentage) {
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
	public int getPrimEntityRecordsCreated() {
		return primEntityRecordsCreated;
	}

	/**
	 * @param primEntityRecordsCreated the primEntityRecordsCreated to set
	 */
	public void setPrimEntityRecordsCreated(int primEntityRecordsCreated) {
		this.primEntityRecordsCreated = primEntityRecordsCreated;
	}

	/**
	 * @return the primEntityRecordsRejected
	 */
	public int getPrimEntityRecordsRejected() {
		return primEntityRecordsRejected;
	}

	/**
	 * @param primEntityRecordsRejected the primEntityRecordsRejected to set
	 */
	public void setPrimEntityRecordsRejected(int primEntityRecordsRejected) {
		this.primEntityRecordsRejected = primEntityRecordsRejected;
	}

	/**
	 * @return the primEntityInputPercenatge
	 */
	public int getPrimEntityInputPercenatge() {
		return primEntityInputPercenatge;
	}

	/**
	 * @param primEntityInputPercenatge the primEntityInputPercenatge to set
	 */
	public void setPrimEntityInputPercenatge(int primEntityInputPercenatge) {
		this.primEntityInputPercenatge = primEntityInputPercenatge;
	}

	/**
	 * @return the secEntityName
	 */
	public String getSecEntityName() {
		return secEntityName;
	}

	/**
	 * @param secEntityName the secEntityName to set
	 */
	public void setSecEntityName(String secEntityName) {
		this.secEntityName = secEntityName;
	}

	/**
	 * @return the secEntityRecordsCreated
	 */
	public Integer getSecEntityRecordsCreated() {
		return secEntityRecordsCreated;
	}

	/**
	 * @param secEntityRecordsCreated the secEntityRecordsCreated to set
	 */
	public void setSecEntityRecordsCreated(Integer secEntityRecordsCreated) {
		this.secEntityRecordsCreated = secEntityRecordsCreated;
	}

	/**
	 * @return the secEntityRecordsRejected
	 */
	public Integer getSecEntityRecordsRejected() {
		return secEntityRecordsRejected;
	}

	/**
	 * @param secEntityRecordsRejected the secEntityRecordsRejected to set
	 */
	public void setSecEntityRecordsRejected(Integer secEntityRecordsRejected) {
		this.secEntityRecordsRejected = secEntityRecordsRejected;
	}

	/**
	 * @return the secEntityInputPercenatge
	 */
	public Integer getSecEntityInputPercenatge() {
		return secEntityInputPercenatge;
	}

	/**
	 * @param secEntityInputPercenatge the secEntityInputPercenatge to set
	 */
	public void setSecEntityInputPercenatge(Integer secEntityInputPercenatge) {
		this.secEntityInputPercenatge = secEntityInputPercenatge;
	}

	/**
	 * @return the totalEntitiesCreated
	 */
	public int getTotalEntitiesCreated() {
		return totalEntitiesCreated;
	}

	/**
	 * @param totalEntitiesCreated the totalEntitiesCreated to set
	 */
	public void setTotalEntitiesCreated(int totalEntitiesCreated) {
		this.totalEntitiesCreated = totalEntitiesCreated;
	}

	/**
	 * @return the totalEntitiesRejected
	 */
	public int getTotalEntitiesRejected() {
		return totalEntitiesRejected;
	}

	/**
	 * @param totalEntitiesRejected the totalEntitiesRejected to set
	 */
	public void setTotalEntitiesRejected(int totalEntitiesRejected) {
		this.totalEntitiesRejected = totalEntitiesRejected;
	}

	/**
	 * @return the totalEntitiesInputPercentage
	 */
	public int getTotalEntitiesInputPercentage() {
		return totalEntitiesInputPercentage;
	}

	/**
	 * @param totalEntitiesInputPercentage the totalEntitiesInputPercentage to set
	 */
	public void setTotalEntitiesInputPercentage(int totalEntitiesInputPercentage) {
		this.totalEntitiesInputPercentage = totalEntitiesInputPercentage;
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
	
	
	
}
