/**
 * 
 */
package com.nathanclaire.alantra.messaging.event;

/**
 * @author Edward Banfa 
 *
 */
public class MessagingEvent {

	private Integer msgId;
	private Integer custId;
	private Integer userId;
	private Character inOutFg;
	private Boolean custMsg;
	private Boolean userMsg;
	
	/**
	 * @param msgId
	 * @param custId
	 * @param userId
	 * @param inOutFg
	 * @param custMsg
	 * @param userMsg
	 */
	public MessagingEvent(Integer msgId, Integer custId, Integer userId,
			Character inOutFg, Boolean custMsg, Boolean userMsg) {
		this.msgId = msgId;
		this.custId = custId;
		this.userId = userId;
		this.inOutFg = inOutFg;
		this.custMsg = custMsg;
		this.userMsg = userMsg;
	}
	/**
	 * @return the msgId
	 */
	public Integer getMsgId() {
		return msgId;
	}
	/**
	 * @param msgId the msgId to set
	 */
	public void setMsgId(Integer msgId) {
		this.msgId = msgId;
	}
	/**
	 * @return the custId
	 */
	public Integer getCustId() {
		return custId;
	}
	/**
	 * @param custId the custId to set
	 */
	public void setCustId(Integer custId) {
		this.custId = custId;
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
	 * @return the inOutFg
	 */
	public Character getInOutFg() {
		return inOutFg;
	}
	/**
	 * @param inOutFg the inOutFg to set
	 */
	public void setInOutFg(Character inOutFg) {
		this.inOutFg = inOutFg;
	}
	/**
	 * @return the custMsg
	 */
	public Boolean getCustMsg() {
		return custMsg;
	}
	/**
	 * @param custMsg the custMsg to set
	 */
	public void setCustMsg(Boolean custMsg) {
		this.custMsg = custMsg;
	}
	/**
	 * @return the userMsg
	 */
	public Boolean getUserMsg() {
		return userMsg;
	}
	/**
	 * @param userMsg the userMsg to set
	 */
	public void setUserMsg(Boolean userMsg) {
		this.userMsg = userMsg;
	}
}
