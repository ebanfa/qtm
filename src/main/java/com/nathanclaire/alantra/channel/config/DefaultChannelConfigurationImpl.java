/**
 * 
 */
package com.nathanclaire.alantra.channel.config;

/**
 * @author Edward Banfa
 *
 */
public class DefaultChannelConfigurationImpl implements ChannelConfiguration {

	private String code;
	private String name;
	private String host;
	private Integer port;
	private String description;
	private String channelType;
	private Boolean autoStart;
	private PipelineConfiguration pipelineConfiguration;
	private Boolean localService;
	
	/**
	 * @param code
	 * @param name
	 * @param host
	 * @param port
	 * @param description
	 * @param channelType
	 * @param pipelineConfiguration
	 */
	public DefaultChannelConfigurationImpl(String code, String name, String host, Integer port, 
			String description, String channelType, PipelineConfiguration pipelineConfiguration) {
		this.code = code;
		this.name = name;
		this.host = host;
		this.port = port;
		this.description = description;
		this.channelType = channelType;
		this.pipelineConfiguration = pipelineConfiguration;
	}
	public DefaultChannelConfigurationImpl(String host, Integer port,
			String channelType, PipelineConfiguration pipelineConfiguration) {
		this.host = host;
		this.port = port;
		this.channelType = channelType;
		this.pipelineConfiguration = pipelineConfiguration;
	}
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
	 * @return the host
	 */
	public String getHost() {
		return host;
	}
	/**
	 * @param host the host to set
	 */
	public void setHost(String host) {
		this.host = host;
	}
	/**
	 * @return the port
	 */
	public Integer getPort() {
		return port;
	}
	/**
	 * @param port the port to set
	 */
	public void setPort(Integer port) {
		this.port = port;
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
	 * @return the channelType
	 */
	public String getChannelType() {
		return channelType;
	}
	/**
	 * @param channelType the channelType to set
	 */
	public void setChannelType(String channelType) {
		this.channelType = channelType;
	}
	/**
	 * @return the pipelineConfiguration
	 */
	public PipelineConfiguration getPipelineConfiguration() {
		return pipelineConfiguration;
	}
	/**
	 * @param pipelineConfiguration the pipelineConfiguration to set
	 */
	public void setPipelineConfiguration(PipelineConfiguration pipelineConfiguration) {
		this.pipelineConfiguration = pipelineConfiguration;
	}
	/**
	 * @return the localService
	 */
	public Boolean isLocalService() {
		return localService;
	}
	/**
	 * @param localService the localService to set
	 */
	public void setLocalService(Boolean localService) {
		this.localService = localService;
	}
	/**
	 * @return the autoStart
	 */
	public Boolean isAutoStart() {
		return autoStart;
	}
	/**
	 * @param autoStart the autoStart to set
	 */
	public void setAutoStart(Boolean autoStart) {
		this.autoStart = autoStart;
	}
}
