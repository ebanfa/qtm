/**
 * 
 */
package com.nathanclaire.alantra.transaction.event;

/**
 * @author Edward Banfa 
 *
 */
public class TransactionMatchingEvent extends BaseTransactionEvent {
	
	
	private Integer adviceId;
	
	/**
	 * @param adviceId
	 * @param transactionId
	 */
	public TransactionMatchingEvent(Integer adviceId, Integer transactionId) {
		this.adviceId = adviceId;
		this.setTransactionId(transactionId);
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
	
}
