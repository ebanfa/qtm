/**
 * 
 */
package com.nathanclaire.alantra.channel.server;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import com.nathanclaire.alantra.channel.server.HTTPISO8583PipelineBuilder;

/**
 * @author Edward Banfa 
 *
 */
public class ServiceChannelPipelineFactory implements ChannelPipelineFactory 
{
	private PipelineBuilder pipelineBuilder = null;
	private ServiceConfiguration configuration = null;
	
	/**
	 * 
	 */
	public ServiceChannelPipelineFactory(ServiceConfiguration configuration) 
	{
		this.configuration = configuration;
		processConfiguration();
	}

	/* (non-Javadoc)
	 * @see org.jboss.netty.channel.ChannelPipelineFactory#getPipeline()
	 */
	public ChannelPipeline getPipeline() throws Exception 
	{
        return pipelineBuilder.build(configuration.getPipelineConfiguration());
    }
	
	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	private void processConfiguration()
	{
		String builderClassName = configuration.getPipelineConfiguration().getPipelineBuilderClassName();
		try {
			Class<PipelineBuilder> builderClass = (Class<PipelineBuilder>) Class.forName(builderClassName);
			pipelineBuilder = builderClass.newInstance();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the configuration
	 */
	public ServiceConfiguration getConfiguration() {
		return configuration;
	}

	/**
	 * @param configuration the configuration to set
	 */
	public void setConfiguration(ServiceConfiguration configuration) {
		this.configuration = configuration;
	}

	/**
	 * @return the pipelineBuilder
	 */
	public PipelineBuilder getPipelineBuilder() {
		return pipelineBuilder;
	}

	/**
	 * @param pipelineBuilder the pipelineBuilder to set
	 */
	public void setPipelineBuilder(PipelineBuilder pipelineBuilder) {
		this.pipelineBuilder = pipelineBuilder;
	}

}
