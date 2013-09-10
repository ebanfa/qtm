/**
 * 
 */
package com.nathanclaire.alantra.channel.service.process;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.service.process.BaseProcessService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.EntityUtil;
import com.nathanclaire.alantra.base.util.ErrorCodes;
import com.nathanclaire.alantra.base.util.ExceptionUtil;
import com.nathanclaire.alantra.base.util.StringUtil;
import com.nathanclaire.alantra.channel.config.ChannelConfiguration;
import com.nathanclaire.alantra.channel.config.DefaultChannelConfigurationImpl;
import com.nathanclaire.alantra.channel.config.DefaultHandlerConfigurationImpl;
import com.nathanclaire.alantra.channel.config.DefaultPipelineConfigurationImpl;
import com.nathanclaire.alantra.channel.config.HandlerConfiguration;
import com.nathanclaire.alantra.channel.config.PipelineConfiguration;
import com.nathanclaire.alantra.channel.model.Channel;
import com.nathanclaire.alantra.channel.model.ChannelHandler;
import com.nathanclaire.alantra.channel.model.ChannelPipeline;
import com.nathanclaire.alantra.channel.model.ChannelPipelineHandler;
import com.nathanclaire.alantra.channel.service.entity.ChannelService;
import com.nathanclaire.alantra.datasource.model.DataChannel;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class ChannelConfigurationServiceImpl extends BaseProcessService	implements ChannelConfigurationService {
	
	@Inject ChannelService channelService;
	private Logger logger = LoggerFactory.getLogger(getClass());

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.channel.ChannelConfigurationService#loadAll()
	 */
	@Override
	public List<ChannelConfiguration> loadAll() throws ApplicationException {
		return buildAll(channelService.findAll(null));
	}

	/**
	 * Builds a list of {@link ChannelConfiguration}s from a list of {@link Channel}s
	 * 
	 * @param channels list the channels we are building the configurations for
	 * @return
	 * @throws ApplicationException if we could not build the configurations
	 */
	private List<ChannelConfiguration> buildAll(List<com.nathanclaire.alantra.channel.model.Channel> channels) 
			throws ApplicationException 
	{
		logger.debug("Processing {} channel configurations.", channels.size());
		List<ChannelConfiguration> configurations = new ArrayList<ChannelConfiguration>();
		for(com.nathanclaire.alantra.channel.model.Channel channel:channels) 
			configurations.add(buildChannelConfiguration(channel));
		return configurations;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.channel.ChannelConfigurationService#load(java.lang.String)
	 */
	@Override
	public ChannelConfiguration load(String channelCode) throws ApplicationException {
		EntityUtil.returnOrThrowIfParamsArrayContainsNull(new Object[]{channelCode}, "ChannelConfigurationServiceImpl.load");
		return buildChannelConfiguration(getChannel(channelCode));
	}

	/**
	 * Utility method to retrieve a {@link Channel}. This function should be moved to 
	 * {@link ChannelService}
	 * 
	 * @param channelCode the code of entity instance we are searching for
	 * @return the channel
	 * @throws ApplicationException if a channel was not found
	 */
	private com.nathanclaire.alantra.channel.model.Channel getChannel(String channelCode) throws ApplicationException {
		return (com.nathanclaire.alantra.channel.model.Channel)
				EntityUtil.returnOrThrowIfNull(channelService.findByCode(channelCode), 
						ErrorCodes.BPS_ENTITY_INSTANCE_NOT_FOUND_ERROR_CD, "com.nathanclaire.alantra.channel.model.Channel");
	}

	/**
	 * Builds a {@link ChannelConfiguration} from a {@link Channel}
	 * 
	 * @param channel
	 * @return the {@link ChannelConfiguration}. {@code null} otherwise.
	 * @throws ApplicationException 
	 *         if an exception was encountered while building the {@link ChannelConfiguration}
	 */
	private ChannelConfiguration buildChannelConfiguration(com.nathanclaire.alantra.channel.model.Channel channel)
			throws ApplicationException 
	{
		EntityUtil.returnOrThrowIfParamsArrayContainsNull(
				new Object[]{channel}, "ChannelConfigurationServiceImpl.buildChannelConfiguration");
		try {
			String channelType = channel.getChannelType().getCode();
			DataChannel dataChannel = channel.getDataChannel();
			logger.debug("Building configuration for channel: {}, " +
					"with data channel: {}, and type: {}", channel, dataChannel, channelType);
			// Build the pipeline configuration
			PipelineConfiguration pipelineConfiguration = buildPipelineConfiguration(channel.getPipeline());
			// Initialize the channel configuration
			ChannelConfiguration channelConfiguration = 
					new DefaultChannelConfigurationImpl(dataChannel.getIpAddr(),
							dataChannel.getPortNo(), channelType, pipelineConfiguration);
			channelConfiguration.setCode(channel.getCode());
			channelConfiguration.setName(channel.getName());
			channelConfiguration.setDescription(channel.getDescription());
			channelConfiguration.setAutoStart(StringUtil.flagToBoolean(channel.getAutoStartFg()));
			channelConfiguration.setLocalService(StringUtil.flagToBoolean(dataChannel.getLocalServiceFg()));
			logger.debug("Configuration {} built.", channelConfiguration.getName());
			return channelConfiguration;
		} catch (Exception e) {
			ExceptionUtil.processException(e, 
					ErrorCodes.CCS_CHANNEL_CONFIGURATION_CREATION_ERROR_CD, e.getMessage());
		}
		return null;
	}
	
	/**
	 * Builds a pipeline configuration from a {@link ChannelPipeline}
	 * 
	 * @param pipeline
	 * @return  the built pipeline configuration
	 * @throws ApplicationException if the pipeline configuration could not be built
	 */
	private PipelineConfiguration buildPipelineConfiguration(ChannelPipeline pipeline) throws ApplicationException{
		try {
			List<HandlerConfiguration> handlers = new ArrayList<HandlerConfiguration>();
			// Build the handler configuration for each of the handlers present in the pipeline - handler mapping
			for(ChannelPipelineHandler handlerMapping: pipeline.getChannelPipelineHandlers())
				handlers.add(buildHandlerConfiguration(handlerMapping));
			// Initialize the pipeline configuration
			PipelineConfiguration pipelineConfiguration = new DefaultPipelineConfigurationImpl(handlers);
			return pipelineConfiguration;
		} catch (Exception e) {
			ExceptionUtil.processException(e, 
					ErrorCodes.CCS_CHANNEL_CONFIGURATION_CREATION_ERROR_CD, 
					ErrorCodes.CCS_PIPELINE_CONFIGURATION_CREATION_ERROR_MSG);
		}
		return null;
	}
	
	/**
	 * Builds a {@link HandlerConfiguration} from a {@link ChannelHandler}
	 * 
	 * @param handler
	 * @return the built handler configuration
	 * @throws ApplicationException if the handler configuration could not be built
	 */
	private HandlerConfiguration buildHandlerConfiguration (ChannelPipelineHandler handlerMapping) throws ApplicationException{
		try {
			HandlerConfiguration handlerConfiguration = new DefaultHandlerConfigurationImpl();
			handlerConfiguration.setSequenceNo(handlerMapping.getSequenceNo());
			handlerConfiguration.setName(handlerMapping.getChannelHandler().getName());
			handlerConfiguration.setClassName(handlerMapping.getChannelHandler().getClassName());
			return handlerConfiguration;
		} catch (Exception e) {
			ExceptionUtil.processException(e, 
					ErrorCodes.CCS_CHANNEL_CONFIGURATION_CREATION_ERROR_CD, 
					ErrorCodes.CCS_HANDLER_CONFIGURATION_CREATION_ERROR_MSG);
		}
		return null;
	}
}
