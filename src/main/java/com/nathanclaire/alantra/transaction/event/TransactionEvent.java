/**
 * 
 */
package com.nathanclaire.alantra.transaction.event;

/**
 * @author Edward Banfa 
 *
 */
public class TransactionEvent extends BaseTransactionEvent {
	
	private Integer adviceId;
	private Integer jobId;
	private Integer channelId;
	private Integer transactionId;
	private Integer jobSummaryId;
	
	/**
	 * @param adviceId
	 * @param transactionId
	 */
	public TransactionEvent(Integer adviceId, Integer transactionId) {
		super();
		this.adviceId = adviceId;
		this.transactionId = transactionId;
	}

	/**
	 * 
	 */
	public TransactionEvent() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the adviceId
	 */
	public Integer getAdviceId() {
		return adviceId;
	}

	/**
	 * @param adviceId the adviceId to set
	 */
	public void setAdviceId(Integer adviceId) {
		this.adviceId = adviceId;
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
	

}
