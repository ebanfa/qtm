/**
 * 
 */
package com.nathanclaire.alantra.channel.handler;

import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.handler.codec.http.DefaultHttpResponse;
import org.jboss.netty.handler.codec.http.HttpHeaders;
import org.jboss.netty.handler.codec.http.HttpResponse;
import org.jboss.netty.handler.codec.http.HttpResponseStatus;
import org.jboss.netty.handler.codec.http.HttpVersion;

/**
 * @author Edward Banfa
 *
 */
public class HttpUtil {
	
	
	public static HttpResponse buildHttpResponse(HttpResponseStatus status){
		HttpResponse response = new DefaultHttpResponse(HttpVersion.HTTP_1_1, status);
		response.setContent(ChannelBuffers.EMPTY_BUFFER);
		HttpHeaders.setContentLength(response, 0);
		return response;
	}

}
