/**
 * 
 */
package com.nathanclaire.alantra.advice.event;

/**
 * @author Edward Banfa 
 *
 */
public class AdviceRequestMessageCreationEvent {
	
	private Integer adviceRequestId;

	/**
	 * @param adviceRequestId
	 */
	public AdviceRequestMessageCreationEvent(Integer adviceRequestId) {
		this.adviceRequestId = adviceRequestId;
	}

	/**
	 * @return the adviceRequestId
	 */
	public Integer getAdviceRequestId() {
		return adviceRequestId;
	}

	/**
	 * @param adviceRequestId the adviceRequestId to set
	 */
	public void setAdviceRequestId(Integer adviceRequestId) {
		this.adviceRequestId = adviceRequestId;
	}

}
