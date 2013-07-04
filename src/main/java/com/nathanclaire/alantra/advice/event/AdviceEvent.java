/**
 * 
 */
package com.nathanclaire.alantra.advice.event;

/**
 * @author Edward Banfa 
 *
 */
public class AdviceEvent {
	
	private Integer adviceId;
	private Integer adviceRequestId;
	
	/**
	 * @param adviceId
	 * @param adviceRequestId
	 */
	public AdviceEvent(Integer adviceId, Integer adviceRequestId) {
		this.adviceId = adviceId;
		this.adviceRequestId = adviceRequestId;
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
