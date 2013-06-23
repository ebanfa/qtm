/**
 * 
 */
package com.nathanclaire.alantra.messaging.event;

/**
 * @author Edward Banfa 
 *
 */

public class MessageCreationEvent extends MessageEvent {
	

	 private String attachmentFileName;
	 private String attachmentDataType;

	 
	/**
	 * 
	 */
	public MessageCreationEvent() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param jobId
	 * @param messageId
	 * @param customerId
	 * @param systemUserId
	 * @param attachmentJobId
	 * @param attachmentFileName
	 * @param attachmentDataType
	 * @param containsAttachment
	 */
	public MessageCreationEvent(Integer messageId, Integer attachmentId) {
		this.setMessageId(messageId);
		this.setAttachmentId(attachmentId);
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
	 * @return the attachmentDataType
	 */
	public String getAttachmentDataType() {
		return attachmentDataType;
	}

	/**
	 * @param attachmentDataType the attachmentDataType to set
	 */
	public void setAttachmentDataType(String attachmentDataType) {
		this.attachmentDataType = attachmentDataType;
	}
	

}
