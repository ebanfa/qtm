/**
 * 
 */
package com.nathanclaire.alantra.channel.handler.codec;

import static org.jboss.netty.handler.codec.http.HttpHeaders.Names.CONTENT_LENGTH;
import static org.jboss.netty.handler.codec.http.HttpHeaders.Names.CONTENT_TYPE;

import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.http.HttpResponse;
import org.jboss.netty.handler.codec.http.HttpResponseStatus;
import org.jboss.netty.util.CharsetUtil;

import com.nathanclaire.alantra.channel.handler.HttpUtil;
import com.nathanclaire.alantra.rule.engine.BusinessObjectData;


/**
 * @author Edward Banfa
 *
 */
public class HttpTransactionDataEncoder extends AbstractBusinessObjectEncoder{

	/* (non-Javadoc)
	 * @see org.jboss.netty.handler.codec.oneone.OneToOneEncoder#encode(org.jboss.netty.channel.ChannelHandlerContext, org.jboss.netty.channel.Channel, java.lang.Object)
	 */
	@Override
	protected Object encode(ChannelHandlerContext ctx, Channel channel, Object object) throws Exception {
		BusinessObjectData businessObjectData = (BusinessObjectData) object;
		return testResultResponse(businessObjectData);
	}

	private HttpResponse testResultResponse(
			BusinessObjectData businessObjectData) {
	    /** Buffer that stores the response content */
		StringBuilder buf = new StringBuilder();
        buf.setLength(0);
        buf.append("Qrion Transaction Manager\r\n");
        buf.append("===================================\r\n");
        buf.append("Validation results: " + (businessObjectData.isValid() ? "Successfull" : "Failed") + "\r\n");
        buf.append("Processing results: " + (businessObjectData.isProcessed() ? "Successfull" : "Failed") + "\r\n");
        buf.append("Routing results: " + (businessObjectData.isRouted() ? "Successfull" : "Failed") + "\r\n");
		HttpResponse response =  HttpUtil.buildHttpResponse(HttpResponseStatus.OK);
        response.setContent(ChannelBuffers.copiedBuffer(buf.toString(), CharsetUtil.UTF_8));
        response.setHeader(CONTENT_TYPE, "text/plain; charset=UTF-8");
        response.setHeader(CONTENT_LENGTH, response.getContent().readableBytes());
		return response;
	}

}
