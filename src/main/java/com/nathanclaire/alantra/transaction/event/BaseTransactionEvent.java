/**
 * 
 */
package com.nathanclaire.alantra.transaction.event;

/**
 * @author Edward Banfa 
 *
 */
public class BaseTransactionEvent {

	private Integer jobId;
	private Integer channelId;
	private Integer jobSummaryId;
	private Integer transactionId;
	private String  transactionTypeCode;

	/**
	 * 
	 */
	public BaseTransactionEvent() {
		// TODO Auto-generated constructor stub
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
	 * @return the transactionId
	 */
	public Integer getTransactionId() {
		return transactionId;
	}
	/**
	 * @param transactionId the transactionId to set
	 */
	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
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

	/**
	 * @return the transactionTypeCode
	 */
	public String getTransactionTypeCode() {
		return transactionTypeCode;
	}

	/**
	 * @param transactionTypeCode the transactionTypeCode to set
	 */
	public void setTransactionTypeCode(String transactionTypeCode) {
		this.transactionTypeCode = transactionTypeCode;
	}

}
