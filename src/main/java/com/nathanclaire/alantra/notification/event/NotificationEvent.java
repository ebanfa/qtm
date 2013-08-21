/**
 * 
 */
package com.nathanclaire.alantra.notification.event;


/**
 * @author Edward Banfa 
 *
 */
public class NotificationEvent {
	public static final String CUST_RECIPIENT = "CUSTOMER";
	public static final String USER_RECIPIENT = "USER";
	
	/**
	 * 
	 */
	public NotificationEvent() {
		// TODO Auto-generated constructor stub
	}
	private String bodyText;
	private String headerText;
	private String recipientType;
	private String channelCatCode;
	private String notificationTypeCode;
	private Integer userId;
	private Integer channelId;
	private Integer customerId;
	private Integer notificationId;
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
	 * @return the recipientType
	 */
	public String getRecipientType() {
		return recipientType;
	}
	/**
	 * @param recipientType the recipientType to set
	 */
	public void setRecipientType(String recipientType) {
		this.recipientType = recipientType;
	}
	/**
	 * @return the channelCatCode
	 */
	public String getChannelCatCode() {
		return channelCatCode;
	}
	/**
	 * @param channelCatCode the channelCatCode to set
	 */
	public void setChannelCatCode(String channelCatCode) {
		this.channelCatCode = channelCatCode;
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
	 * @param bodyText
	 * @param headerText
	 * @param recipientType
	 * @param channelCatCode
	 * @param notificationTypeCode
	 * @param userId
	 * @param channelId
	 * @param customerId
	 * @param notificationId
	 */
	public NotificationEvent(String bodyText, String headerText,
			String recipientType, String channelCatCode,
			String notificationTypeCode, Integer userId, Integer channelId,
			Integer customerId, Integer notificationId) {
		super();
		this.bodyText = bodyText;
		this.headerText = headerText;
		this.recipientType = recipientType;
		this.channelCatCode = channelCatCode;
		this.notificationTypeCode = notificationTypeCode;
		this.userId = userId;
		this.channelId = channelId;
		this.customerId = customerId;
		this.notificationId = notificationId;
	}
	
}
