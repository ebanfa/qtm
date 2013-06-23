/**
 * 
 */
package com.nathanclaire.alantra.messaging.util;

import java.util.Map;

/**
 * @author Edward Banfa 
 *
 */
public class MessageLite {

	private String messageTo;
	private String messageFrom;
	private String subjectLine;
	private String messageBody;
	private String messageId;
	private String messageTemplate;
	private String attachementFileName;
	private String attachementMimeType;
	private boolean containsAttachement;
	private Map<String, String> templateTagValues;
	
	/**
	 * @return the messageTo
	 */
	public String getMessageTo() {
		return messageTo;
	}
	/**
	 * @param messageTo the messageTo to set
	 */
	public void setMessageTo(String messageTo) {
		this.messageTo = messageTo;
	}
	/**
	 * @return the messageFrom
	 */
	public String getMessageFrom() {
		return messageFrom;
	}
	/**
	 * @param messageFrom the messageFrom to set
	 */
	public void setMessageFrom(String messageFrom) {
		this.messageFrom = messageFrom;
	}
	/**
	 * @return the subjectLine
	 */
	public String getSubjectLine() {
		return subjectLine;
	}
	/**
	 * @param subjectLine the subjectLine to set
	 */
	public void setSubjectLine(String subjectLine) {
		this.subjectLine = subjectLine;
	}
	/**
	 * @return the messageBody
	 */
	public String getMessageBody() {
		return messageBody;
	}
	/**
	 * @param messageBody the messageBody to set
	 */
	public void setMessageBody(String messageBody) {
		this.messageBody = messageBody;
	}
	/**
	 * @return the messageId
	 */
	public String getMessageId() {
		return messageId;
	}
	/**
	 * @param messageId the messageId to set
	 */
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	/**
	 * @return the attachementFileName
	 */
	public String getAttachementFileName() {
		return attachementFileName;
	}
	/**
	 * @param attachementFileName the attachementFileName to set
	 */
	public void setAttachementFileName(String attachementFileName) {
		this.attachementFileName = attachementFileName;
	}
	/**
	 * @return the containsAttachement
	 */
	public boolean isContainsAttachement() {
		return containsAttachement;
	}
	/**
	 * @param containsAttachement the containsAttachement to set
	 */
	public void setContainsAttachement(boolean containsAttachement) {
		this.containsAttachement = containsAttachement;
	}
	/**
	 * @return the attachementMimeType
	 */
	public String getAttachementMimeType() {
		return attachementMimeType;
	}
	/**
	 * @param attachementMimeType the attachementMimeType to set
	 */
	public void setAttachementMimeType(String attachementMimeType) {
		this.attachementMimeType = attachementMimeType;
	}
	/**
	 * @return the messageTemplate
	 */
	public String getMessageTemplate() {
		return messageTemplate;
	}
	/**
	 * @param messageTemplate the messageTemplate to set
	 */
	public void setMessageTemplate(String messageTemplate) {
		this.messageTemplate = messageTemplate;
	}
	/**
	 * @return the templateTagValues
	 */
	public Map<String, String> getTemplateTagValues() {
		return templateTagValues;
	}
	/**
	 * @param templateTagValues the templateTagValues to set
	 */
	public void setTemplateTagValues(Map<String, String> templateTagValues) {
		this.templateTagValues = templateTagValues;
	}
}
