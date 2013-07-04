/**
 * 
 */
package com.nathanclaire.alantra.notification.event;


/**
 * @author Edward Banfa 
 *
 */
public class NotificationEvent {

	private Integer userId;
	private String bodyText;
	private String headerText;
	private Integer notificationId;
	private String notificationTypeCode;
	
	/**
	 * @param userId
	 * @param bodyText
	 * @param headerText
	 * @param customerId
	 * @param notificationId
	 */
	public NotificationEvent(String notificationTypeCode, 
			Integer notificationId,	Integer userId, String headerText, String bodyText) {
		this.userId = userId;
		this.bodyText = bodyText;
		this.headerText = headerText;
		this.notificationId = notificationId;
		this.notificationTypeCode = notificationTypeCode;
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
	 * @return the bodyText
	 */
	public String getBodyText() {
		return bodyText;
	}
	/**
	 * @param bodyText the bodyText to set
	 */
	public void setBodyText(String bodyText) {
		this.bodyText = bodyText;
	}
	/**
	 * @return the headerText
	 */
	public String getHeaderText() {
		return headerText;
	}
	/**
	 * @param headerText the headerText to set
	 */
	public void setHeaderText(String headerText) {
		this.headerText = headerText;
	}
	
	/**
	 * @return the notificationId
	 */
	public Integer getNotificationId() {
		return notificationId;
	}
	/**
	 * @param notificationId the notificationId to set
	 */
	public void setNotificationId(Integer notificationId) {
		this.notificationId = notificationId;
	}
	/**
	 * @return the notificationTypeCode
	 */
	public String getNotificationTypeCode() {
		return notificationTypeCode;
	}
	/**
	 * @param notificationTypeCode the notificationTypeCode to set
	 */
	public void setNotificationTypeCode(String notificationTypeCode) {
		this.notificationTypeCode = notificationTypeCode;
	}

}
