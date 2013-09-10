/**
 * 
 */
package com.nathanclaire.alantra.channel.service.schedule;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.service.process.BaseTimerService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.ExceptionUtil;
import com.nathanclaire.alantra.channel.config.ChannelConfiguration;
import com.nathanclaire.alantra.channel.service.process.ChannelConfigurationService;
import com.nathanclaire.alantra.channel.service.process.ChannelManagerService;

/**
 * @author Edward Banfa
 *
 */
@Startup
@Singleton
@ApplicationScoped
public class ChannelStartUpServiceImpl extends BaseTimerService implements ChannelStartUpService {

	@Inject ChannelManagerService channelManagerService;
	@Inject ChannelConfigurationService configurationService;
	private Logger logger = LoggerFactory.getLogger(getClass());

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.schedule.ChannelStartUpService#start()
	 */
	@Override
	@PostConstruct
	//@Schedule(minute="*/1", hour="*")
	public void start() {
		try {
			logger.debug("Loading channel services configuration ");
			// load all channel configurations and build the channels
			int channelStarted = 0;
			List<ChannelConfiguration> configurations = configurationService.loadAll();
			for(ChannelConfiguration configuration : configurations){
				if(configuration.isAutoStart()){
					channelManagerService.start(configuration);
					channelStarted ++;
				}
			}
			logger.debug("Started {} channel services", channelStarted);
		} catch (ApplicationException e) {
			ExceptionUtil.logException(e);
		}
	}
	
	@PreDestroy
	public void shutdown(){
		logger.debug("Shutting down start up service");
		try {
			channelManagerService.stopAll();
		} catch (Exception e) {
			ExceptionUtil.logException(e);
		}
	}

}
