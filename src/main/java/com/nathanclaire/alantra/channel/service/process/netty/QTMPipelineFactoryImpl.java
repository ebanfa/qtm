/**
 * 
 */
package com.nathanclaire.alantra.channel.service.process.netty;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.jboss.netty.channel.ChannelHandler;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.EntityUtil;
import com.nathanclaire.alantra.base.util.ErrorCodes;
import com.nathanclaire.alantra.base.util.ExceptionUtil;
import com.nathanclaire.alantra.channel.handler.ChannelHandlerFactory;
import com.nathanclaire.alantra.channel.service.process.QTMHandler;

/**
 * Creates a {@link ChannelPipelineFactory}. This class uses an instance of
 * {@link ChannelHandlerFactory} to produced handlers which are handed over to
 * an instance of {@link ChannelPipelineFactory}.
 * 
 * @author Edward Banfa
 *
 */
@Stateless
public class QTMPipelineFactoryImpl implements QTMPipelineFactory {

	@Inject ChannelHandlerFactory handlerFactory;
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * Returns an instance of {@link ChannelPipelineFactory}. Uses
	 * an instance of {@link ChannelHandlerFactory} to create {@link ChannelHandler}s
	 * from a list of {@link QTMHandler}s. The {@link ChannelHandler}s 
	 * are then handed over to an instance of {@link ChannelHandlerFactory}.
	 * 
	 * @param handlerDefinitions
	 * @return
	 * @throws ApplicationException
	 */
	@Override
	public ChannelPipelineFactory getPipeline(List<QTMHandler> handlerDefinitions) throws ApplicationException {
		EntityUtil.returnOrThrowIfParamsArrayContainsNull(new Object[]{handlerDefinitions}, 
				"QTMPipelineFactory.QTMPipelineFactory(List<QTMHandler> handlers)");
		try {
			List<ChannelHandler> channelHandlers = new ArrayList<ChannelHandler>();
			Collections.sort(handlerDefinitions, new QTMHandlerComparer());
			for(QTMHandler handler : handlerDefinitions){
				String handlerName = handler.getConfiguration().getClassName();
				// Resolve the handler
				ChannelHandler channelHandler = 
						handlerFactory.getChannelHandler(handlerName);
				handler.setHandler(channelHandler);
				channelHandlers.add(channelHandler);
			}
			return new ChannelPipelineFactoryImpl(channelHandlers);
		} catch (ApplicationException e) {
			ExceptionUtil.processException(e, ErrorCodes.QPF_PIPELINE_FACTORY_INITIALIZATION_ERROR_CD);
		}
		return null;
	}
	
	/**
	 * The actual implementation of {@link ChannelPipelineFactory}.
	 * 
	 * @author Edward Banfa
	 *
	 */
	private class ChannelPipelineFactoryImpl implements ChannelPipelineFactory
	{
		List<ChannelHandler> channelHandlers;
		public ChannelPipelineFactoryImpl(List<ChannelHandler> channelHandlers)
		{
			this.channelHandlers = channelHandlers;
		}
		@Override
		public ChannelPipeline getPipeline() throws Exception {			
			return Channels.pipeline(channelHandlers.toArray(new ChannelHandler[0]));
		}
		
	}
	
	/**
	 * Comparator implementation to sort entity fields by sequence number
	 * @author Edward Banfa 
	 *
	 */
	private class QTMHandlerComparer implements Comparator<QTMHandler> {
		  @Override
		  public int compare(QTMHandler x, QTMHandler y) {
			  try {
				return x.getConfiguration().getSequenceNo().compareTo(y.getConfiguration().getSequenceNo());
			} catch (ApplicationException e) {
				throw new RuntimeException(e);
			}
		  }
	}

}
