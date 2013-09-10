/**
 * 
 */
package com.nathanclaire.alantra.channel.handler;

import org.jboss.netty.channel.ChannelHandler;

import com.nathanclaire.alantra.base.util.ApplicationException;

/**
 * A factory for Netty {@link ChannelHandler}s
 * 
 * @author Edward Banfa
 *
 */
public interface ChannelHandlerFactory {
	
	/**
	 * Get the {@link ChannelHandler} with the specified name.
	 * 
	 * @param name the name of the {@link ChannelHandler}.
	 * @return the {@link ChannelHandler}.
	 * @throws ApplicationException if an exception was encountered
	 */
	public ChannelHandler getChannelHandler(String name) throws ApplicationException;

}
