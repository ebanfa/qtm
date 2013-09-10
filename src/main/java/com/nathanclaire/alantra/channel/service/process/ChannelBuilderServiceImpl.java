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

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.EntityUtil;
import com.nathanclaire.alantra.channel.config.ChannelConfiguration;
import com.nathanclaire.alantra.channel.service.process.netty.QTMChannelImpl;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class ChannelBuilderServiceImpl implements ChannelBuilderService {
	
	@Inject PipelineBuilderService pipelineBuilderService;
	private Logger logger = LoggerFactory.getLogger(getClass());

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.channel.ChannelBuilderService#build(com.nathanclaire.alantra.channel.config.ChannelConfiguration)
	 */
	@Override
	public QTMChannel build(ChannelConfiguration configuration) throws ApplicationException {
		EntityUtil.returnOrThrowIfParamsArrayContainsNull(new Object[]{configuration}, "ChannelBuilderService.build");
		logger.debug("Building channel {}", configuration.getName());
		QTMChannel channel = new QTMChannelImpl();
		channel.setConfiguration(configuration);
		QTMPipeline pipeline = 
				pipelineBuilderService.build(configuration.getPipelineConfiguration());
		channel.setPipeline(pipeline);
		channel.init();
		logger.debug("Successfully built channel {} on {}:{}", 
				configuration.getName(), configuration.getHost(), configuration.getPort());
		return channel;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.channel.ChannelBuilderService#buildAll(com.nathanclaire.alantra.channel.config.ChannelConfiguration)
	 */
	@Override
	public List<QTMChannel> buildAll(List<ChannelConfiguration> configurations)	
			throws ApplicationException 
	{
		logger.debug("Building {} channels", configurations.size());
		EntityUtil.returnOrThrowIfParamsArrayContainsNull(new Object[]{configurations, "ChannelBuilderService.buildAll"});
		List<QTMChannel> channels = new ArrayList<QTMChannel>();
		for(ChannelConfiguration configuration : configurations)
			channels.add(this.build(configuration));
		return channels;
	}


}
