/**
 * 
 */
package com.nathanclaire.alantra.channel.handler;

import javax.ejb.Stateful;
import javax.inject.Inject;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelEvent;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.DownstreamMessageEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.businessobject.data.BusinessObjectData;
import com.nathanclaire.alantra.rule.engine.RuleChain;
import com.nathanclaire.alantra.rule.engine.RuleSpace;
import com.nathanclaire.alantra.rule.engine.RuleTable;
import com.nathanclaire.alantra.rule.service.process.RulesEngine;

/**
 * This class handles invocation of all routing rules 
 * defined within the default {@link RuleChain} ({@code INBOUND}) 
 * of the {@code PROCESSING} {@link RuleTable} in the default 
 * {@link RuleSpace}.
 * 
 * TODO: Since this handler is a upstream and as well as a
 * downstream handler, it could be called in a pipeline as
 * the data is going out. That means, the wrong {@link RuleChain} 
 * will be used for outbound data.
 * 
 * @author Edward Banfa
 *
 */
@Stateful
public class BusinessObjectRoutingHandler extends
		AbstractBusinessLogicHandler {
	
	@Inject RulesEngine rulesEngine;
	private Logger logger = LoggerFactory.getLogger(getClass());
	/* (non-Javadoc)
	 * @see org.jboss.netty.channel.SimpleChannelHandler#messageReceived(org.jboss.netty.channel.ChannelHandlerContext, org.jboss.netty.channel.MessageEvent)
	 */
	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
			throws Exception {
		BusinessObjectData businessObject = (BusinessObjectData) e.getMessage();
		logger.debug("Received business object: {}", businessObject);
		try {
			rulesEngine.route(businessObject);
			businessObject.setRouted(true);
		} catch (Exception e1) {
			logger.debug("Error executing routing rules on {}. Error message: {}", businessObject, e1);
			businessObject.setRouted(false);
		}
		sendDown(ctx, e, businessObject);
		// Send this message to any handlers upstream
		// TODO: What if there are no more handlers upstream?
		super.messageReceived(ctx, e);
	}
	
	@Override
    public void exceptionCaught(
            ChannelHandlerContext ctx, ExceptionEvent e) {
        // Close the connection when an exception is raised.
		logger.debug("Unexpected exception from downstream.{}", e.getCause());
        e.getChannel().close();
    }

}
