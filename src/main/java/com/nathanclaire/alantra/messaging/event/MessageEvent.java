/**
 * 
 */
package com.nathanclaire.alantra.messaging.event;

/**
 * @author Edward Banfa 
 *
 */
public class MessageEvent {

	private String jobCode;
	private Integer jobId;
	private String senderType;
	private Integer messageId;
	private String messageText;
	private Integer customerId;
	private String customerName;
	private String messageCode;
	private Integer systemUserId;
	private Integer attachmentId;
	private String sourceAddress;
	private Integer dataChannelId;
	private String messageTypeCode;
	private Integer attachmentJobId;
	private String destinationAddress;
	private String attachmentFileName;
	private String attachmentFileType;
	private boolean containsAttachment;
	private String messageApplicationCode;
	private String messageApplicationActionCode;
	private String statusInformation;
	
	public static final String SENDER_TY_USER = "USER";
	public static final String SENDER_TY_CUSTOMER = "CUSTOMER";
	public static final String SENDER_TY_UNCLASSIFIED = "UNCLASSIFIED";
	
	/**
	 * @param jobId
	 * @param senderType
	 * @param messageId
	 * @param customerId
	 * @param systemUserId
	 * @param attachmentId
	 * @param messageTypeCode
	 * @param attachmentJobId
	 * @param attachmentFileName
	 * @param attachmentFileType
	 * @param containsAttachment
	 */
	public MessageEvent(Integer jobId, Integer messageId,  String messageTypeCode,
			Integer customerId, Integer systemUserId, Integer attachmentId,	Integer attachmentJobId, boolean containsAttachment) {
		this.jobId = jobId;
		this.messageId = messageId;
		this.customerId = customerId;
		this.systemUserId = systemUserId;
		this.attachmentId = attachmentId;
		this.messageTypeCode = messageTypeCode;
		this.attachmentJobId = attachmentJobId;
		this.containsAttachment = containsAttachment;
	}

	/**
	 * 
	 */
	public MessageEvent() {
	}

	/**
	 * @return the messageId
	 */
	public Integer getMessageId() {
		return messageId;
	}

	/**
	 * @param messageId the messageId to set
	 */
	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
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
	 * @return the attachmentJobId
	 */
	public Integer getAttachmentJobId() {
		return attachmentJobId;
	}

	/**
	 * @param attachmentJobId the attachmentJobId to set
	 */
	public void setAttachmentJobId(Integer attachmentJobId) {
		this.attachmentJobId = attachmentJobId;
	}

	/**
	 * @return the systemUserId
	 */
	public Integer getSystemUserId() {
		return systemUserId;
	}

	/**
	 * @param systemUserId the systemUserId to set
	 */
	public void setSystemUserId(Integer systemUserId) {
		this.systemUserId = systemUserId;
	}

	/**
	 * @return the containsAttachment
	 */
	public boolean isContainsAttachment() {
		return containsAttachment;
	}

	/**
	 * @param containsAttachment the containsAttachment to set
	 */
	public void setContainsAttachment(boolean containsAttachment) {
		this.containsAttachment = containsAttachment;
	}


	/**
	 * @return the attachmentId
	 */
	public Integer getAttachmentId() {
		return attachmentId;
	}

	/**
	 * @param attachmentId the attachmentId to set
	 */
	public void setAttachmentId(Integer attachmentId) {
		this.attachmentId = attachmentId;
	}

	/**
	 * @return the attachmentFileName
	 */
	public String getAttachmentFileName() {
		return attachmentFileName;
	}

	/**
	 * @param attachmentFileName the attachmentFileName to set
	 */
	public void setAttachmentFileName(String attachmentFileName) {
		this.attachmentFileName = attachmentFileName;
	}

	/**
	 * @return the senderType
	 */
	public String getSenderType() {
		return senderType;
	}

	/**
	 * @param senderType the senderType to set
	 */
	public void setSenderType(String senderType) {
		this.senderType = senderType;
	}

	/**
	 * @return the attachmentFileType
	 */
	public String getAttachmentFileType() {
		return attachmentFileType;
	}

	/**
	 * @param attachmentFileType the attachmentFileType to set
	 */
	public void setAttachmentFileType(String attachmentFileType) {
		this.attachmentFileType = attachmentFileType;
	}

	/**
	 * @return the messageTypeCode
	 */
	public String getMessageTypeCode() {
		return messageTypeCode;
	}

	/**
	 * @param messageTypeCode the messageTypeCode to set
	 */
	public void setMessageTypeCode(String messageTypeCode) {
		this.messageTypeCode = messageTypeCode;
	}

	/**
	 * @return the jobCode
	 */
	public String getJobCode() {
		return jobCode;
	}

	/**
	 * @param jobCode the jobCode to set
	 */
	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}

	/**
	 * @return the sourceAddress
	 */
	public String getSourceAddress() {
		return sourceAddress;
	}

	/**
	 * @param sourceAddress the sourceAddress to set
	 */
	public void setSourceAddress(String sourceAddress) {
		this.sourceAddress = sourceAddress;
	}

	/**
	 * @return the destinationAddress
	 */
	public String getDestinationAddress() {
		return destinationAddress;
	}

	/**
	 * @param destinationAddress the destinationAddress to set
	 */
	public void setDestinationAddress(String destinationAddress) {
		this.destinationAddress = destinationAddress;
	}

	/**
	 * @return the messageCode
	 */
	public String getMessageCode() {
		return messageCode;
	}

	/**
	 * @param messageCode the messageCode to set
	 */
	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}

	/**
	 * @return the messageText
	 */
	public String getMessageText() {
		return messageText;
	}

	/**
	 * @param messageText the messageText to set
	 */
	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}

	/**
	 * @return the dataChannelId
	 */
	public Integer getDataChannelId() {
		return dataChannelId;
	}

	/**
	 * @param dataChannelId the dataChannelId to set
	 */
	public void setDataChannelId(Integer dataChannelId) {
		this.dataChannelId = dataChannelId;
	}

	/**
	 * @return the messageApplicationCode
	 */
	public String getMessageApplicationCode() {
		return messageApplicationCode;
	}

	/**
	 * @param messageApplicationCode the messageApplicationCode to set
	 */
	public void setMessageApplicationCode(String messageApplication) {
		this.messageApplicationCode = messageApplication;
	}

	/**
	 * @return the messageApplicationActionCode
	 */
	public String getMessageApplicationActionCode() {
		return messageApplicationActionCode;
	}

	/**
	 * @param messageApplicationActionCode the messageApplicationActionCode to set
	 */
	public void setMessageApplicationActionCode(String messageApplicationActionCode) {
		this.messageApplicationActionCode = messageApplicationActionCode;
	}

	/**
	 * @return the statusInformation
	 */
	public String getStatusInformation() {
		return statusInformation;
	}

	/**
	 * @param statusInformation the statusInformation to set
	 */
	public void setStatusInformation(String statusInformation) {
		this.statusInformation = statusInformation;
	}

	/**
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * @param customerName the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

}
