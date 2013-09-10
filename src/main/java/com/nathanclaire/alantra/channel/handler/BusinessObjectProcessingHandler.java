/**
 * 
 */
package com.nathanclaire.alantra.channel.handler;

import javax.ejb.Stateful;
import javax.inject.Inject;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.rule.engine.RuleChain;
import com.nathanclaire.alantra.rule.engine.RuleSpace;
import com.nathanclaire.alantra.rule.engine.RuleTable;
import com.nathanclaire.alantra.rule.service.process.RulesEngine;

/**
 * This class handles invocation of all processing rules 
 * defined within the default {@link RuleChain} ({@code PROCESS}) 
 * of the {@code PROCESSING} {@link RuleTable} in the default 
 * {@link RuleSpace}.
 * 
 * @author Edward Banfa
 *
 */
@Stateful
public class BusinessObjectProcessingHandler extends
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
			rulesEngine.process(businessObject);
			businessObject.setProcessed(true);
		} catch (Exception e1) {
			logger.debug("Error executing processing rules on {}. Error message: {}", businessObject, e1);
			businessObject.setProcessed(false);
		}
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
