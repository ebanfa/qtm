/**
 * 
 */
package com.nathanclaire.alantra.channel.service.process;

import java.util.List;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.channel.config.ChannelConfiguration;

/**
 * @author Edward Banfa
 *
 */
public interface ChannelManagerService {

	/**
	 * Get all the channels currently being managed by this manager.
	 * 
	 * @return
	 * @throws ApplicationException
	 */
	public List<QTMChannel> getAllChannels() throws ApplicationException;
	
	/**
	 * Get all currently active channels that are being managed by this manager
	 * 
	 * @return
	 * @throws ApplicationException
	 */
	public List<QTMChannel> getAllActiveChannels() throws ApplicationException;
	
	/**
	 * Get all currently inactive channels that are being managed by this manager
	 * 
	 * @return
	 * @throws ApplicationException
	 */
	public List<QTMChannel> getAllInactiveChannels() throws ApplicationException;
	
	/**
	 * Build and start a new {@link QTMChannel}
	 * 
	 * @param channelConfiguration
	 * @return
	 * @throws ApplicationException
	 */
	public QTMChannel start(ChannelConfiguration channelConfiguration) throws ApplicationException;
	
	/**
	 * Build and start QTM Channels from the provided arguments
	 * 
	 * @param channelConfigurations
	 * @return
	 * @throws ApplicationException
	 */
	public List<QTMChannel> startAll(List<ChannelConfiguration> channelConfigurations) throws ApplicationException;
	
	/**
	 * Stop all the {@link QTMChannel} currently being managed by this manager
	 * 
	 * @throws ApplicationException
	 */
	public void stopAll() throws ApplicationException;

	/**
	 * Find and stop the {@link QTMChannel} represented by the passed {@link ChannelConfiguration}
	 * 
	 * @param channelConfiguration
	 * @throws ApplicationException
	 */
	public void stop(ChannelConfiguration channelConfiguration) throws ApplicationException;
	
	/**
	 * Find and stop all the {@link QTMChannel}s represented by the passed list of {@link ChannelConfiguration}s
	 * 
	 * @param channelConfigurations
	 * @throws ApplicationException
	 */
	public void stoptAll(List<ChannelConfiguration> channelConfigurations) throws ApplicationException;
	
}
