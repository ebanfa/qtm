/**
 * 
 */
package com.nathanclaire.alantra.channel.config;

/**
 * @author Edward Banfa
 *
 */
public class DefaultHandlerConfigurationImpl implements HandlerConfiguration {
	
	private String code;
	private String name;
	private String className;
	private String description;
	private String handlerType;
	private Boolean stateless;
	private Integer sequenceNo;
	
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the className
	 */
	public String getClassName() {
		return className;
	}
	/**
	 * @param className the className to set
	 */
	public void setClassName(String className) {
		this.className = className;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the handlerType
	 */
	public String getHandlerType() {
		return handlerType;
	}
	/**
	 * @param handlerType the handlerType to set
	 */
	public void setHandlerType(String handlerType) {
		this.handlerType = handlerType;
	}
	/**
	 * @return the stateless
	 */
	public Boolean isStateless() {
		return stateless;
	}
	/**
	 * @param stateless the stateless to set
	 */
	public void setStateless(Boolean stateless) {
		this.stateless = stateless;
	}
	/**
	 * @return the sequenceNo
	 */
	public Integer getSequenceNo() {
		return sequenceNo;
	}
	/**
	 * @param sequenceNo the sequenceNo to set
	 */
	public void setSequenceNo(Integer sequenceNo) {
		this.sequenceNo = sequenceNo;
	}

}
