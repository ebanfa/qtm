/**
 * 
 */
package com.nathanclaire.alantra.channel.handler;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelEvent;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.DownstreamMessageEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

import com.nathanclaire.alantra.rule.engine.BusinessObjectData;

/**
 * Abstract class for {@link BusinessLogicHandler}s
 * 
 * @author Edward Banfa
 *
 */
public class AbstractBusinessLogicHandler extends SimpleChannelUpstreamHandler
		implements BusinessLogicHandler {
	

	protected void sendDown(ChannelHandlerContext ctx, MessageEvent e,
			BusinessObjectData businessObject) {
		// Send back the response
		Channel channel = e.getChannel();
		ChannelFuture channelFuture = Channels.future(e.getChannel());
		ChannelEvent responseEvent = new DownstreamMessageEvent(channel, 
				channelFuture, businessObject, channel.getRemoteAddress());
		ctx.sendDownstream(responseEvent);
	}

}
