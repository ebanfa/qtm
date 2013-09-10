/**
 * 
 */
package com.nathanclaire.alantra.channel.service.process.netty;

import org.jboss.netty.channel.ChannelHandler;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.channel.config.HandlerConfiguration;
import com.nathanclaire.alantra.channel.service.process.QTMHandler;

/**
 * @author Edward Banfa
 *
 */
public class QTMHandlerImpl implements QTMHandler {
	
	private ChannelHandler handler;
	private HandlerConfiguration configuration;

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.channel.QTMHandler#init()
	 */
	@Override
	public void init() throws ApplicationException {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.channel.QTMHandler#getConfiguration()
	 */
	@Override
	public HandlerConfiguration getConfiguration() throws ApplicationException {
		return configuration;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.channel.QTMHandler#setConfiguration(com.nathanclaire.alantra.channel.config.HandlerConfiguration)
	 */
	@Override
	public void setConfiguration(HandlerConfiguration configuration)
			throws ApplicationException {
		this.configuration = configuration;
	}

	/**
	 * @return the handler
	 */
	public ChannelHandler getHandler() {
		return handler;
	}

	/**
	 * @param handler the handler to set
	 */
	public void setHandler(Object handler) {
		this.handler = (ChannelHandler) handler;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "QTMHandlerImpl [handler=" + handler + ", configuration="
				+ configuration + "]";
	}

}
