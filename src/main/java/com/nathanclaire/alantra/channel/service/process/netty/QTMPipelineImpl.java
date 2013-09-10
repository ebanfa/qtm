/**
 * 
 */
package com.nathanclaire.alantra.channel.service.process.netty;

import java.util.ArrayList;
import java.util.List;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.channel.config.PipelineConfiguration;
import com.nathanclaire.alantra.channel.service.process.QTMHandler;
import com.nathanclaire.alantra.channel.service.process.QTMPipeline;

/**
 * @author Edward Banfa
 *
 */
public class QTMPipelineImpl implements QTMPipeline {
	
	private Object pipeline;
	private PipelineConfiguration configuration;
	private List<QTMHandler> handlers = new ArrayList<QTMHandler>();

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.channel.QTMPipeline#init()
	 */
	@Override
	public void init() throws ApplicationException {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.channel.QTMPipeline#getConfiguration()
	 */
	@Override
	public PipelineConfiguration getConfiguration() throws ApplicationException {
		return configuration;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.channel.QTMPipeline#setConfiguration(com.nathanclaire.alantra.channel.config.PipelineConfiguration)
	 */
	@Override
	public void setConfiguration(PipelineConfiguration configuration)
			throws ApplicationException {
		this.configuration = configuration;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.channel.QTMPipeline#getHandlers()
	 */
	@Override
	public List<QTMHandler> getHandlers() throws ApplicationException {
		return handlers;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.channel.QTMPipeline#setHandlers(java.util.List)
	 */
	@Override
	public void setHandlers(List<QTMHandler> handlers)
			throws ApplicationException {
		this.handlers = handlers;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.channel.QTMPipeline#getHandler(java.lang.String)
	 */
	@Override
	public QTMHandler getHandler(String name) throws ApplicationException {
		for(QTMHandler handler : handlers)
			if(handler.getConfiguration().getName().equals(name))
				return handler;
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.channel.QTMPipeline#add(com.nathanclaire.alantra.channel.service.channel.QTMHandler)
	 */
	@Override
	public QTMHandler add(QTMHandler handler) throws ApplicationException {
		for(QTMHandler entry : handlers)
			if(entry.getConfiguration().getName().equals(handler.getConfiguration().getName()))
				return handler;
		handlers.add(handler);
		return handler;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.channel.QTMPipeline#remove(com.nathanclaire.alantra.channel.service.channel.QTMHandler)
	 */
	@Override
	public void remove(QTMHandler handler) throws ApplicationException {
		for(QTMHandler entry : handlers)
			if(entry.getConfiguration().getName().equals(handler.getConfiguration().getName()))
				handlers.remove(handler);
	}

	/**
	 * @return the pipeline
	 */
	public Object getPipeline() {
		return pipeline;
	}

	/**
	 * @param pipeline the pipeline to set
	 */
	public void setPipeline(Object pipeline) {
		this.pipeline = pipeline;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "QTMPipelineImpl [pipeline=" + pipeline + ", configuration="
				+ configuration + "]";
	}
}
