/**
 * 
 */
package com.nathanclaire.alantra.messaging.request;


/**
 * @author Edward Banfa 
 *
 */
public class SendMessageRequest {
	
	private Integer[] toList;
	private Integer[] ccList;
	private String text;
	private String type;
	private String subject;
	private String category;
	private String transport;
	private String targetMode;
	private String classification;
	
	
	
	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}
	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}
	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}
	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	/**
	 * @return the transport
	 */
	public String getTransport() {
		return transport;
	}
	/**
	 * @param transport the transport to set
	 */
	public void setTransport(String transport) {
		this.transport = transport;
	}
	/**
	 * @return the classification
	 */
	public String getClassification() {
		return classification;
	}
	/**
	 * @param classification the classification to set
	 */
	public void setClassification(String classification) {
		this.classification = classification;
	}
	/**
	 * @return the targetMode
	 */
	public String getTargetMode() {
		return targetMode;
	}
	/**
	 * @param targetMode the targetMode to set
	 */
	public void setTargetMode(String targetMode) {
		this.targetMode = targetMode;
	}
	
	public Integer[] getToList() {
		return toList;
	}
	/**
	 * @return the ccList
	 */
	public Integer[] getCcList() {
		return ccList;
	}
	/**
	 * @param ccList the ccList to set
	 */
	public void setCcList(Integer[] ccList) {
		this.ccList = ccList;
	}
	/**
	 * @param toList the toList to set
	 */
	public void setToList(Integer[] toList) {
		this.toList = toList;
	}

}
