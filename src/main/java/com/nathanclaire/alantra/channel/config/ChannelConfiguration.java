/**
 * 
 */
package com.nathanclaire.alantra.channel.config;

import com.nathanclaire.alantra.channel.service.process.QTMChannel;

/**
 * Configuration information for a channel {@link QTMChannel}.
 * 
 * @author Edward Banfa
 * 
 */
public interface ChannelConfiguration {

	public String getHost();

	public void setHost(String host);

	public Integer getPort();

	public void setPort(Integer port);

	public String getName();

	public void setName(String name);

	public String getCode();

	public void setCode(String code);

	public String getDescription();

	public void setDescription(String description);

	public String getChannelType();

	public void setChannelType(String channelType);
	
	public Boolean isLocalService();
	
	public void setLocalService(Boolean isLocalService);
	
	public Boolean isAutoStart();
	
	public void setAutoStart(Boolean autoStart);

	public PipelineConfiguration getPipelineConfiguration();

	public void setPipelineConfiguration(
			PipelineConfiguration pipelineConfiguration);
}
