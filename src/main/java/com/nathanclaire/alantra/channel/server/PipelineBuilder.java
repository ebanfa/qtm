/**
 * 
 */
package com.nathanclaire.alantra.channel.server;

import org.jboss.netty.channel.ChannelPipeline;

/**
 * @author Edward Banfa 
 *
 */
public interface PipelineBuilder 
{
	public ChannelPipeline build(PipelineConfiguration configuration);
}
