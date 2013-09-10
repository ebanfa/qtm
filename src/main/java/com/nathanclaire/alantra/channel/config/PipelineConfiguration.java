/**
 * 
 */
package com.nathanclaire.alantra.channel.config;

import java.util.List;

/**
 * @author Edward Banfa
 * 
 */
public interface PipelineConfiguration {

	public List<HandlerConfiguration> getHandlers();

	public void setHandlers(List<HandlerConfiguration> handlers);

}
