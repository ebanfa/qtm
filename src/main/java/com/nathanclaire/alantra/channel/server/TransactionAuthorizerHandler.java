/**
 * 
 */
package com.nathanclaire.alantra.channel.server;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

/**
 * @author Edward Banfa 
 *
 */
public class TransactionAuthorizerHandler extends
		SimpleChannelUpstreamHandler {

	/* (non-Javadoc)
	 * @see org.jboss.netty.channel.SimpleChannelUpstreamHandler#messageReceived(org.jboss.netty.channel.ChannelHandlerContext, org.jboss.netty.channel.MessageEvent)
	 */
	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
			throws Exception {
		// TODO Auto-generated method stub
		/*Transaction transaction = (Transaction) e.getMessage();
		System.out.println(">>>>>>>>>>>>>>>>>>>Got transaction from: " + transaction.getCustomerName()); 
		transaction.setCustomerName("Wanted " + transaction.getCustomerName());
		transaction.setReturnCode(0);
		// Send back the reponse
		Channel channel = e.getChannel();
		ChannelFuture channelFuture = Channels.future(e.getChannel());
		ChannelEvent responseEvent = new DownstreamMessageEvent(channel, channelFuture, transaction, channel.getRemoteAddress());
		ctx.sendDownstream(responseEvent);*/
		super.messageReceived(ctx, e);
	}

}
