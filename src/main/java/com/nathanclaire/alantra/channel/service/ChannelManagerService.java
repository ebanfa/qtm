/**
 * 
 */
package com.nathanclaire.alantra.channel.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.channel.model.Service;
import com.nathanclaire.alantra.channel.model.ServiceProtocolAdapter;
import com.nathanclaire.alantra.channel.server.PipelineConfiguration;
import com.nathanclaire.alantra.channel.server.ServiceChannel;
import com.nathanclaire.alantra.channel.server.ServiceConfiguration;
import com.nathanclaire.alantra.channel.server.ServiceImpl;
import com.nathanclaire.alantra.channel.service.entity.ServiceCategoryService;

/**
 * @author Edward Banfa 
 *
 */
@Startup
@Singleton
public class ChannelManagerService implements ChannelManager 
{
	@PersistenceContext(unitName = "primary")
    protected EntityManager entityManager;
	/**
	 * List of all the services that this channel manager is managing
	 */
	private List<ServiceChannel> services = null;
	
	/**
	 *  Logger
	 */
	Logger logger = LoggerFactory.getLogger(ChannelManagerService.class);
	
	/**
	 * We are dependent on this guy.
	 */
	@Resource(name = "java:jboss/datasources/alantraDS")
	DataSource ds; 
	
	/**
	 * Called on channel manager init
	 */
	@PostConstruct
	public void start()
	{
		logger.info("Channel manager initialized");
		List<ServiceConfiguration> serviceConfigurations = this.getServiceDefinitions();
		this.startServices(serviceConfigurations);
	}
	
	/**
	 * Called before channel manager is destroyed
	 */
	@PreDestroy
	public void stop()
	{
		logger.info("Stopping channel manager");
		this.stopServices();
	}
	
	/**
	 *  Start all the services
	 */
	private void startServices(List<ServiceConfiguration> serviceConfigurations)
	{
		logger.info("Starting {} services", serviceConfigurations.size());
		// Initial collection of services
		services = new ArrayList<ServiceChannel>();
		for(ServiceConfiguration configuration: serviceConfigurations)
		{
			startService(configuration);
		} 
	}
	
	/**
	 * @param port
	 * @return
	 */
	private void startService(ServiceConfiguration configuration)
	{
		logger.info("Starting service: {}", configuration.getServiceName() );
		ServiceChannel service = new ServiceImpl(configuration);
		try {
			service.start();
			services.add(service);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Stop all services managed by this channel manager
	 */
	private void stopServices() 
	{
		logger.info("Stopping {} services", services.size());
		for(ServiceChannel service: services)
		{
			service.stop();
		}
		logger.info("Services stopped");
		services.clear();
	}
	
	
	/**
	 * @return
	 */
	private List<ServiceConfiguration> getServiceDefinitions()
	{
		// Get all the channels defined in the backend
		List<Service> channels = this.getAllChannels();
		logger.info("Loaded {} channels", channels.size());
		// Container for our service definitions
		List<ServiceConfiguration> serviceConfigurations = new ArrayList<ServiceConfiguration>();
		for(Service channel:channels)
		{
			logger.info("Processing loaded channel: {}", channel.getName());
			// Only load definitions for network services
			if(channel.getServiceCategory().getCode().
					equals(ServiceCategoryService.NETWORK_SERVICE_CODE))
			{
				ServiceConfiguration configuration = this.loadServiceDefinition(channel);
				serviceConfigurations.add(configuration);
			}
		}
		return serviceConfigurations;
	}
	
	/**
	 * Get all the channels defined in the back end
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<Service> getAllChannels() 
    {
		return entityManager.createQuery("SELECT c FROM Service c").getResultList();
    }
	
	/**
	 * Builds a service definition from the provided channel
	 * @param channel
	 * @return
	 */
	private ServiceConfiguration loadServiceDefinition(Service channel)
	{
		ServiceConfiguration configuration = new ServiceConfiguration();
		// Basic channel parameters
		configuration.setServiceName(channel.getName());
		configuration.setPort(channel.getPortNo());
		configuration.setIpAddress(channel.getIpAddress());
		// ServiceChannel mode
		configuration.setServiceMode(channel.getServiceMode().getName());
		// ServiceChannel application protocol handler e.g ISO 8583
		this.loadProtocolAdapterDefinition(channel, configuration);
		return configuration;
	}
	
	/**
	 * @param channel
	 * @param configuration
	 */
	private void loadProtocolAdapterDefinition(Service channel, ServiceConfiguration configuration)
	{
		ServiceProtocolAdapter protocolAdapter = channel.getServiceProtocolAdapter();
		PipelineConfiguration pipelineConfiguration = 
				new PipelineConfiguration(protocolAdapter.getName(), protocolAdapter.getClassName());
		configuration.setPipelineConfiguration(pipelineConfiguration);
	}
	
	
}
