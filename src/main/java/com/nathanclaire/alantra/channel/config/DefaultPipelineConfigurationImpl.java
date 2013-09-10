package com.nathanclaire.alantra.channel.config;

import java.util.List;

public class DefaultPipelineConfigurationImpl implements PipelineConfiguration {
	
	private List<HandlerConfiguration> handlers;

	/**
	 * @param handlers
	 */
	public DefaultPipelineConfigurationImpl(List<HandlerConfiguration> handlers) {
		this.handlers = handlers;
	}

	/**
	 * @return the handlers
	 */
	public List<HandlerConfiguration> getHandlers() {
		return handlers;
	}

	/**
	 * @param handlers the handlers to set
	 */
	public void setHandlers(List<HandlerConfiguration> handlers) {
		this.handlers = handlers;
	}

}
