/**
 * 
 */
package com.nathanclaire.alantra.channel.service.process;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateful;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.ErrorCodes;
import com.nathanclaire.alantra.base.util.ExceptionUtil;
import com.nathanclaire.alantra.channel.config.ChannelConfiguration;

/**
 * @author Edward Banfa
 *
 */
@Stateful
public class ChannelManagerServiceImpl implements ChannelManagerService {

	@Inject ChannelBuilderService channelBuilderService;
	private List<QTMChannel> channels = new ArrayList<QTMChannel>();
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	private static final String ACTIVE_CHANNEL_STATUS = "ACTIVE";
	private static final String INACTIVE_CHANNEL_STATUS = "INACTIVE";

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.channel.ChannelManagerService#getAllChannels()
	 */
	@Override
	public List<QTMChannel> getAllChannels() throws ApplicationException {
		return channels;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.channel.ChannelManagerService#getAllActiveChannels()
	 */
	@Override
	public List<QTMChannel> getAllActiveChannels() throws ApplicationException {
		List<QTMChannel> activeChannels =  new ArrayList<QTMChannel>();
		for(QTMChannel channel: channels)
			if(channel.getStatus().equals(ACTIVE_CHANNEL_STATUS))
				activeChannels.add(channel);
		return activeChannels;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.channel.ChannelManagerService#getAllInactiveChannels()
	 */
	@Override
	public List<QTMChannel> getAllInactiveChannels() throws ApplicationException {
		List<QTMChannel> inactiveChannels =  new ArrayList<QTMChannel>();
		for(QTMChannel channel: channels)
			if(channel.getStatus().equals(INACTIVE_CHANNEL_STATUS))
				inactiveChannels.add(channel);
		return inactiveChannels;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.channel.ChannelManagerService#start(com.nathanclaire.alantra.channel.config.ChannelConfiguration)
	 */
	@Override
	public QTMChannel start(ChannelConfiguration channelConfiguration) throws ApplicationException 
	{
		if(isAlreadyRunning(channelConfiguration)) {
			logger.debug("Channel {} already running. Skiping...", channelConfiguration.getCode());
			return getChannel(channelConfiguration);
		}
		try {
			logger.debug("Starting channel {}", channelConfiguration.getName());
			QTMChannel channel = channelBuilderService.build(channelConfiguration);
			logger.debug("Starting channel {} ", channel);
			channel.start();
			channels.add(channel);
			return channel;
		} catch (Exception e) {
			e.printStackTrace();
			ExceptionUtil.processException(e, 
					ErrorCodes.CMS_START_CHANNEL_ERROR_CD, e.getMessage());
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.channel.ChannelManagerService#startAll(java.util.List)
	 */
	@Override
	public List<QTMChannel> startAll(List<ChannelConfiguration> channelConfigurations) throws ApplicationException 
	{
		logger.debug("Starting {} channels", channelConfigurations.size());
		List<QTMChannel> channelsStarted = new ArrayList<QTMChannel>();
		for(ChannelConfiguration configuration : channelConfigurations)
			channelsStarted.add(start(configuration));
		// Add this list of newly started services to the master list of services
		return channelsStarted;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.channel.ChannelManagerService#stopAll()
	 */
	@Override
	public void stopAll() throws ApplicationException {
		for(QTMChannel channel : channels)
			channel.stop();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.channel.ChannelManagerService#stop(com.nathanclaire.alantra.channel.config.ChannelConfiguration)
	 */
	@Override
	public void stop(ChannelConfiguration channelConfiguration)	throws ApplicationException {
		for(QTMChannel channel : channels)
			if(channel.getConfiguration().getName().equals(channelConfiguration.getName()))
				channel.stop();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.channel.ChannelManagerService#stoptAll(java.util.List)
	 */
	@Override
	public void stoptAll(List<ChannelConfiguration> channelConfigurations) throws ApplicationException {
		for(ChannelConfiguration configuration : channelConfigurations)
			stop(configuration);
	}
	
	private Boolean isAlreadyRunning(ChannelConfiguration configuration) throws ApplicationException {
		for(QTMChannel channel : channels)
			if(channel.getConfiguration().getCode().equals(configuration.getCode()))
				return true;
		return false;
	}
	
	private QTMChannel getChannel(ChannelConfiguration configuration) throws ApplicationException{
		for(QTMChannel channel : channels)
			if(channel.getConfiguration().getCode().equals(configuration.getCode()))
				return channel;
		return null;
	}
}
