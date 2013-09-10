/**
 * 
 */
package com.nathanclaire.alantra.channel.service.process;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.channel.config.ChannelConfiguration;
import com.nathanclaire.alantra.datasource.model.DataChannel;

/**
 * A {@link QTMChannel} is a dynamic  {@link DataChannel}.
 * Dynamic in the sense that the type of data read/written and
 * the communications protocol used at runtime are configurable.
 * 
 * A channel is compose of a single {@link QTMPipeline}. This pipeline
 * contains the list of {@link QTMHandler}'s configured for the channel.
 * The set of handlers configured for a channel determine communications, 
 * data format and data handling logic of the channel.
 * 
 * 
 * @author Edward Banfa
 * 
 */
public interface QTMChannel {
	
	public void init() throws ApplicationException;
	
	public void start() throws ApplicationException;

	public void stop() throws ApplicationException;
	
	public String getStatus() throws ApplicationException;
	
	public ChannelConfiguration getConfiguration() throws ApplicationException;
	
	public void setConfiguration(ChannelConfiguration configuration) throws ApplicationException;
	
	public QTMPipeline getPipeline() throws ApplicationException;
	
	public void setPipeline(QTMPipeline pipeline) throws ApplicationException;
	
	
}
