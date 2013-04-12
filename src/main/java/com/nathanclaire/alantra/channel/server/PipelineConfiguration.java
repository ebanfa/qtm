/**
 * 
 */
package com.nathanclaire.alantra.channel.server;

/**
 * @author Edward Banfa 
 *
 */
public class PipelineConfiguration 
{
	private String  pipelineBuilderName;
	private String  pipelineBuilderClassName;
	
	/**
	 * @param pipelineBuilderName
	 * @param pipelineBuilderClassName
	 */
	public PipelineConfiguration(String pipelineBuilderName,
			String pipelineBuilderClassName) {
		this.pipelineBuilderName = pipelineBuilderName;
		this.pipelineBuilderClassName = pipelineBuilderClassName;
	}
	/**
	 * @return the pipelineBuilderName
	 */
	public String getPipelineBuilderName() {
		return pipelineBuilderName;
	}
	/**
	 * @param pipelineBuilderName the pipelineBuilderName to set
	 */
	public void setPipelineBuilderName(String pipelineBuilderName) {
		this.pipelineBuilderName = pipelineBuilderName;
	}
	/**
	 * @return the pipelineBuilderClassName
	 */
	public String getPipelineBuilderClassName() {
		return pipelineBuilderClassName;
	}
	/**
	 * @param pipelineBuilderClassName the pipelineBuilderClassName to set
	 */
	public void setPipelineBuilderClassName(String pipelineBuilderClassName) {
		this.pipelineBuilderClassName = pipelineBuilderClassName;
	}

}
