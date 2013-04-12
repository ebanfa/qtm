/**
 * 
 */
package com.nathanclaire.alantra.channel.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.group.ChannelGroup;
import org.jboss.netty.channel.group.DefaultChannelGroup;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.util.Messages;

/**
 * @author Edward Banfa 
 *
 */
public class ServiceImpl implements ServiceChannel {


	private ChannelGroup allChannels = null;
	private ServerBootstrap bootstrap = null;
	private ServiceConfiguration configuration;
	private Logger logger = LoggerFactory.getLogger(ServiceImpl.class);
	
	
	/**
	 * @param port
	 * @param ipAddress
	 */
	public ServiceImpl(ServiceConfiguration confguration)
	{
		this.configuration = confguration;
		this.setUp();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.server.ServiceChannel#start(int, java.lang.String)
	 */
	@Override
	public void start() throws Exception
	{
		startServer();
	}
	
	/**
	 * @param port
	 * @param ipAddress
	 */
	private void setUp() 
	{
		logger.debug("Preparing service {}", configuration.getServiceName());
		allChannels = new DefaultChannelGroup(configuration.getServiceName());
		bootstrap = new ServerBootstrap(
										new NioServerSocketChannelFactory(
												Executors.newCachedThreadPool(),
												Executors.newCachedThreadPool())
										);
		// Set up the pipeline factory.
	    bootstrap.setPipelineFactory( new ServiceChannelPipelineFactory(configuration));
	}
	
	/**
	 * @param port
	 * @param ipAddress
	 * @throws Exception
	 */
	private void startServer() throws Exception
	{
		// Bind and start to accept incoming connections.
		logger.info("Starting service {} in {} mode on {} and port {}", 
				configuration.getServiceName(), configuration.getServiceMode(), configuration.getIpAddress(), configuration.getPort());
		Channel channel = bootstrap.bind(new InetSocketAddress(configuration.getIpAddress(), configuration.getPort()));
		allChannels.add(channel);
		logger.info("ServiceChannel {} started", configuration.getServiceName());
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.server.ServiceChannel#stop()
	 */
	@Override
	public void stop() 
	{
		allChannels.close();
	}

	/**
	 * @return the confguration
	 */
	public ServiceConfiguration getConfiguration() {
		return configuration;
	}

	/**
	 * @param confguration the confguration to set
	 */
	public void setConfiguration(ServiceConfiguration configuration) {
		this.configuration = configuration;
	}
}
