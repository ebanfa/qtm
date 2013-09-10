/**
 * 
 */
package com.nathanclaire.alantra.channel.handler;

import org.jboss.netty.channel.ChannelHandler;

import com.nathanclaire.alantra.base.util.ApplicationException;

/**
 * A registry for Netty {@link ChannelHandler}s.
 * 
 * @author Edward Banfa
 *
 */
public interface BusinessObjectHandlerRegistry {
	
	public ChannelHandler get(String name) throws ApplicationException;
	
	public Boolean containsHandler(String name) throws ApplicationException;

}
