/**
 * 
 */
package com.nathanclaire.alantra.security.service.process;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.service.process.BaseProcessService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.datasource.model.DataChannel;
import com.nathanclaire.alantra.datasource.model.DataChannelCategory;
import com.nathanclaire.alantra.datasource.model.DataChannelType;
import com.nathanclaire.alantra.datasource.service.entity.DataChannelService;
import com.nathanclaire.alantra.security.model.SystemUser;
import com.nathanclaire.alantra.security.model.SystemUserNotificationChannel;

/**
 * @author Edward Banfa 
 *
 */
public class SystemUserChannelServiceImpl extends BaseProcessService implements
		SystemUserChannelService {
	
	private Logger logger = LoggerFactory.getLogger(SystemUserChannelServiceImpl.class);

	@Override
	public List<DataChannelCategory> getSystemUserChannelCategories(SystemUser systemUser)
			throws ApplicationException {
		if(systemUser == null)
			throw new ApplicationException(INVALID_USER_SPECIFIED);
		logger.debug("Loading channel categories for user {}", systemUser.getUsername());
		Set<SystemUserNotificationChannel> notificationChannels = systemUser.getSystemUserNotificationChannels();
		List<DataChannelCategory> channelCategories = new ArrayList<DataChannelCategory>();
		for(SystemUserNotificationChannel notificationChannel: notificationChannels)
		{
			channelCategories.add(notificationChannel.getDataChannelCategory());
		}
		return channelCategories;
	}
	
	@Override
	public List<DataChannel> getSystemUserChannels(SystemUser systemUser) throws ApplicationException {
		if(systemUser == null)
			throw new ApplicationException(INVALID_USER_SPECIFIED);
		logger.debug("Loading communication channels for user {}", systemUser);
		List<DataChannel> channels = new ArrayList<DataChannel>();
		
		for(DataChannelCategory channelCategory : getSystemUserChannelCategories(systemUser))
		{
			for(DataChannelType channelType : channelCategory.getDataChannelTypes())
			{
				channels.addAll(channelType.getDataChannels());
			}
		}
		logger.debug("Customer {} has {} communucations channels configured", systemUser, channels.size());
		return channels;
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.security.service.process.SystemUserChannelService#getSystemUserOutboundChannels(com.nathanclaire.alantra.security.model.SystemUser)
	 */
	@Override
	public List<DataChannel> getSystemUserOutboundChannels(SystemUser systemUser) throws ApplicationException {
		if(systemUser == null)
			throw new ApplicationException(INVALID_USER_SPECIFIED);
		logger.debug("Loading outbound communication channels for user {}", systemUser);
		List<DataChannel> channels = new ArrayList<DataChannel>();
		
		for(DataChannel channel : getSystemUserChannels(systemUser))
		{
			logger.debug("Checking for outbound channel flag in channel {} with flag {}", channel, channel.getInboundOutboundCd());
			if(channel.getInboundOutboundCd().equals(DataChannelService.OUTBOUND_CHANNEL))
				channels.add(channel);
			else if(channel.getInboundOutboundCd().equals(DataChannelService.BIRDIRECTIONAL_CHANNEL))
				channels.add(channel);
		}
		return channels;
	}

}
