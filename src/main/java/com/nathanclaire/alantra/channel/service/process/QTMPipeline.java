/**
 * 
 */
package com.nathanclaire.alantra.channel.service.process;

import java.util.List;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.channel.config.PipelineConfiguration;

/**
 * @author Edward Banfa
 *
 */
public interface QTMPipeline {

	public void init() throws ApplicationException;
	
	public Object getPipeline() throws ApplicationException;
	
	public void setPipeline(Object pipeline) throws ApplicationException;
	
	public PipelineConfiguration getConfiguration() throws ApplicationException;
	
	public void setConfiguration(PipelineConfiguration configuration) throws ApplicationException;
	
	public List<QTMHandler> getHandlers() throws ApplicationException;
	
	public void setHandlers(List<QTMHandler> handlers) throws ApplicationException;
	
	public QTMHandler getHandler(String name) throws ApplicationException;
	
	public QTMHandler add(QTMHandler handler) throws ApplicationException;
	
	public void remove(QTMHandler handler) throws ApplicationException;

}
