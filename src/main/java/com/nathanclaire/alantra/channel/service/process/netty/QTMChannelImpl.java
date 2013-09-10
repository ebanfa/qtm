/**
 * 
 */
package com.nathanclaire.alantra.channel.service.process.netty;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.Bootstrap;
import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.EntityUtil;
import com.nathanclaire.alantra.channel.config.ChannelConfiguration;
import com.nathanclaire.alantra.channel.service.process.QTMChannel;
import com.nathanclaire.alantra.channel.service.process.QTMHandler;
import com.nathanclaire.alantra.channel.service.process.QTMPipeline;

/**
 * An implementation of {@link QTMChannel} 
 * based on the Netty framework.
 * 
 * @author Edward Banfa
 *
 */
public class QTMChannelImpl implements QTMChannel {

	private String status;
	private Boolean started;
	private Boolean initialized;
	private Channel channel;
	private QTMPipeline pipeline;
	private Bootstrap bootstrap;
	private ChannelConfiguration configuration;
	
	/**
	 * 
	 */
	public QTMChannelImpl() {
		this.started = false;
		this.initialized = false;
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.channel.QTMChannel#init()
	 */
	@Override
	public void init() throws ApplicationException {
		// Make sure this channel has not been started.
		if(initialized) return;
		// Ensure we have the necessary information
		EntityUtil.returnOrThrowIfParamsArrayContainsNull(
				new Object[]{configuration, pipeline}, "QTMChannelImpl.init");
		if(configuration.isLocalService())
			this.initializeServerChannel();
		else
			this.initializeClientChannel();
		initialized = true;
	}
	
	/**
	 * Initialize the Netty server channel.
	 * 
	 * @throws ApplicationException if an exception was encountered
	 */
	private void initializeServerChannel() throws ApplicationException {
		bootstrap = new ServerBootstrap( new NioServerSocketChannelFactory(
				Executors.newCachedThreadPool(), Executors.newCachedThreadPool()));
		bootstrap.setPipelineFactory((ChannelPipelineFactory) pipeline.getPipeline());
	}

	/**
	 * Initialize the Netty client channel
	 * 
	 * @throws ApplicationException if an exception was encountered
	 */
	private void initializeClientChannel() throws ApplicationException {
		bootstrap = new ClientBootstrap( new NioClientSocketChannelFactory(
				Executors.newCachedThreadPool(), Executors.newCachedThreadPool()));
		bootstrap.setPipelineFactory((ChannelPipelineFactory) pipeline.getPipeline());		
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.channel.QTMChannel#start()
	 */
	@Override
	public void start() throws ApplicationException {
		if(!initialized) init();
		if(configuration.isLocalService())
			startServer(configuration.getHost(), configuration.getPort());
		else
			startClient(configuration.getHost(), configuration.getPort());
		started = true;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.channel.QTMChannel#stop()
	 */
	@Override
	public void stop() throws ApplicationException {
		if(!started) return;
		if(configuration.isLocalService())
			stopServer();
		else
			stopClient();
		this.started = false;
	}

	/**
	 * Starts the Netty server channel on the
	 * specified host and port
	 * 
	 * @param host the host to connect to
	 * @param port the port to connect to
	 * @throws ApplicationException if an exception was encountered
	 */
	private void startServer(String host, Integer port) throws ApplicationException  {
		ServerBootstrap serverBootstrap = (ServerBootstrap) bootstrap;
		bootstrap.setOption("child.tcpNoDelay", true);
		bootstrap.setOption("child.keepAlive", true);
		InetSocketAddress address  = new InetSocketAddress(host, port);
		channel = serverBootstrap.bind(address);
	}
	
	/**
	 * Starts the Netty client channel. The channel will connect
	 * to the specified host and port. 
	 * 
	 * @param host the host to connect to
	 * @param port the port to connect to
	 * @throws ApplicationException if an exception was encountered
	 */
	private void startClient(String host, Integer port) throws ApplicationException  {
		ClientBootstrap clientBootstrap = (ClientBootstrap) bootstrap;
		InetSocketAddress address  = new InetSocketAddress(host, port);
		clientBootstrap.connect(address);
	}

	/**
	 * Close the Netty server channel and release any resources
	 * it may be holding onto
	 */
	private void stopServer() {
		channel.unbind();
		channel.close();
		bootstrap.releaseExternalResources();
	}

	/**
	 * Closes the Netty channel channel and release any resources
	 * it may be holding onto
	 */
	private void stopClient() {
		channel.disconnect();
		bootstrap.releaseExternalResources();		
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.channel.QTMChannel#getStatus()
	 */
	@Override
	public String getStatus() throws ApplicationException {
		return status;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.channel.QTMChannel#getConfiguration()
	 */
	@Override
	public ChannelConfiguration getConfiguration() throws ApplicationException {
		return configuration;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.channel.QTMChannel#setConfiguration(com.nathanclaire.alantra.channel.config.ChannelConfiguration)
	 */
	@Override
	public void setConfiguration(ChannelConfiguration configuration)
			throws ApplicationException {
		this.configuration = configuration;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.channel.QTMChannel#getPipeline()
	 */
	@Override
	public QTMPipeline getPipeline() throws ApplicationException {
		return pipeline;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.channel.QTMChannel#setPipeline(com.nathanclaire.alantra.channel.service.channel.QTMPipeline)
	 */
	@Override
	public void setPipeline(QTMPipeline pipeline) throws ApplicationException {
		this.pipeline = pipeline;
	}

	/**
	 * @return the started
	 */
	public Boolean getStarted() {
		return started;
	}

	/**
	 * @param started the started to set
	 */
	public void setStarted(Boolean started) {
		this.started = started;
	}

	/**
	 * @return the initialized
	 */
	public Boolean getInitialized() {
		return initialized;
	}

	/**
	 * @param initialized the initialized to set
	 */
	public void setInitialized(Boolean initialized) {
		this.initialized = initialized;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String objectString = "\nQTMChannelImpl [name=" + configuration.getName() + "]";
		try {
			for(QTMHandler handler : pipeline.getHandlers())
				objectString += "\nHandler[name=" +  handler.getConfiguration().getName() + 
				", seqNo=" + handler.getConfiguration().getSequenceNo() + "]";
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
		return objectString;
	}

}
