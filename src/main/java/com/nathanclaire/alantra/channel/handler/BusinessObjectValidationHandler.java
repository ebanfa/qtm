/**
 * 
 */
package com.nathanclaire.alantra.channel.handler;

import javax.ejb.Stateful;
import javax.inject.Inject;

import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.ChannelHandler;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.handler.codec.http.DefaultHttpResponse;
import org.jboss.netty.handler.codec.http.HttpHeaders;
import org.jboss.netty.handler.codec.http.HttpResponse;
import org.jboss.netty.handler.codec.http.HttpResponseStatus;
import org.jboss.netty.handler.codec.http.HttpVersion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.application.service.entity.ApplicationEntityFieldService;
import com.nathanclaire.alantra.businessobject.data.BusinessObjectData;
import com.nathanclaire.alantra.businessobject.service.process.BusinessObjectCreationService;
import com.nathanclaire.alantra.rule.service.process.RulesEngine;

/**
 * A {@link ChannelHandler} the implements validation for received
 * business object. It is expected the event has already being processed by 
 * the decoder relevant for the given transport.
 * 
 * Business object validation involves calling the business object validation
 * service to validate the object. If the validation succeeds then 
 * the object data is passed to the next handler in the pipeline.
 * If validation fails, the business object does not proceed
 * up the pipeline and an error message sent back.
 * 
 * @author Edward Banfa
 *
 */
@Stateful
public class BusinessObjectValidationHandler extends
		AbstractBusinessLogicHandler {
	
	@Inject RulesEngine rulesEngine;
    @Inject BusinessObjectCreationService objectCreationService;
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
			rulesEngine.validate(businessObject);
			objectCreationService.create(businessObject);
		} catch (Exception e1) {
			logger.debug("Error executing validation rules on {}. Error message: {}", businessObject, e1);
			businessObject.setValid(false);
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
