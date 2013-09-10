/**
 * 
 */
package com.nathanclaire.alantra.channel.handler;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.jboss.netty.channel.ChannelHandler;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.ErrorCodes;
import com.nathanclaire.alantra.base.util.ExceptionUtil;

/**
 * Default implemention of a Netty {@link ChannelHandler} factory.
 * 
 * @author Edward Banfa
 *
 */
@Stateless
public class ChannelHandlerFactoryImpl implements ChannelHandlerFactory {

	private static final String NETTY_HANDLER_PACKAGE_PREFIX = "org.jboss.netty.handler";

	@Inject BusinessObjectHandlerRegistry handlerRegistry;

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.process.netty.ChannelHandlerFactory#getChannelHandler(java.lang.String)
	 */
	@Override
	public ChannelHandler getChannelHandler(String name) throws ApplicationException {
		if(name.startsWith(NETTY_HANDLER_PACKAGE_PREFIX))
			return getNettyChannelHandler(name);
		return getQTMChannelHandler(name);
	}

	/**
	 * Returns a Netty {@link ChannelHandler} implementation
	 * from the handlers supplied with the Netty framework.
	 * 
	 * @param className the class name of the handler
	 * @return the channel handler
	 * @throws ApplicationException if an exception was encounterd.
	 */
	private ChannelHandler getNettyChannelHandler(String className)  throws ApplicationException {
		try {
			Class clazz = Class.forName(className);
			return (ChannelHandler) clazz.newInstance();
		} catch (Exception e) {
			ExceptionUtil.logAndProcessException(e, ErrorCodes.CHF_CHANNEL_CREATION_ERROR_CD);
		}
		return null;
	}
	

	/**
	 * Returns an QTM implementation of a Netty {@link ChannelHandler}.
	 * All QTM implementations of {@link ChannelHandler} are EJB session
	 * beans (stateful or stateless). The handlers are stored by the stateful
	 * component {@link BusinessObjectHandlerRegistry}.
	 * 
	 * @param name the name of the handler
	 * @return the channel handler
	 * @throws ApplicationException if the handler was not found
	 */
	private ChannelHandler getQTMChannelHandler(String name) throws ApplicationException{
		if(!handlerRegistry.containsHandler(name))
			throw new ApplicationException(
					ErrorCodes.CHF_CHANNEL_CREATION_ERROR_CD, 
					ErrorCodes.CHF_CHANNEL_CREATION_ERROR_CD);
			return handlerRegistry.get(name);
	}

}
