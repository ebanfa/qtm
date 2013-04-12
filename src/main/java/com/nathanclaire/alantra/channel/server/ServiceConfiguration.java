/**
 * 
 */
package com.nathanclaire.alantra.channel.server;

/**
 * @author Edward Banfa 
 *
 */
public class ServiceConfiguration {
	
	private int port;
	private String ipAddress;
	private String serviceName;
	private String serviceMode;
	private PipelineConfiguration pipelineConfiguration;
	
	/**
	 * 
	 */
	public ServiceConfiguration() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the port
	 */
	public int getPort() {
		return port;
	}
	/**
	 * @param port the port to set
	 */
	public void setPort(int port) {
		this.port = port;
	}
	/**
	 * @return the ipAddress
	 */
	public String getIpAddress() {
		return ipAddress;
	}
	/**
	 * @param ipAddress the ipAddress to set
	 */
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	/**
	 * @return the serviceName
	 */
	public String getServiceName() {
		return serviceName;
	}
	/**
	 * @param serviceName the serviceName to set
	 */
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	/**
	 * @return the serviceMode
	 */
	public String getServiceMode() {
		return serviceMode;
	}
	/**
	 * @param serviceMode the serviceMode to set
	 */
	public void setServiceMode(String serviceMode) {
		this.serviceMode = serviceMode;
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
	
}
