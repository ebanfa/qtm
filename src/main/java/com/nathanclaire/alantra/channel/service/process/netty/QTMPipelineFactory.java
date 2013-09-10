/**
 * 
 */
package com.nathanclaire.alantra.channel.service.process.netty;

import java.util.List;

import org.jboss.netty.channel.ChannelPipelineFactory;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.channel.service.process.QTMHandler;

/**
 * Creates a Netty {@link ChannelPipelineFactory}.
 * 
 * @author Edward Banfa
 *
 */
public interface QTMPipelineFactory {
	
	public Object getPipeline(List<QTMHandler> handlerDefinitions) throws ApplicationException;

}
