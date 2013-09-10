/**
 * 
 */
package com.nathanclaire.alantra.channel.config;

/**
 * @author Edward Banfa
 *
 */
public interface HandlerConfiguration {

	public String getName();

	public void setName(String name);

	public String getCode();

	public void setCode(String code);

	public String getDescription();

	public void setDescription(String description);

	public String getClassName();

	public void setClassName(String className);

	public String getHandlerType();

	public void setHandlerType(String handlerType);

	public Boolean isStateless();

	public void setStateless(Boolean stateless);
	
	public Integer getSequenceNo();
	
	public void setSequenceNo(Integer sequenceNo);
	
}
