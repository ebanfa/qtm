/**
 * 
 */
package com.nathanclaire.alantra.channel.handler.codec;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.http.DefaultHttpResponse;
import org.jboss.netty.handler.codec.http.HttpHeaders;
import org.jboss.netty.handler.codec.http.HttpMethod;
import org.jboss.netty.handler.codec.http.HttpRequest;
import org.jboss.netty.handler.codec.http.HttpResponse;
import org.jboss.netty.handler.codec.http.HttpResponseStatus;
import org.jboss.netty.handler.codec.http.HttpVersion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.ErrorCodes;
import com.nathanclaire.alantra.base.util.ExceptionUtil;
import com.nathanclaire.alantra.base.util.StringUtil;
import com.nathanclaire.alantra.channel.handler.BusinessObjectData;
import com.nathanclaire.alantra.channel.handler.BusinessObjectDataImpl;

/**
 * Represents an object that knows how to decode a
 * {@link BusinessObjectData} from a {@link HttpRequest}. 
 * Only the {@link HttpMethod} {@code HttpMethod.POST} is supported.
 * 
 * @author Edward Banfa
 *
 */
public class HttpTransactionDataDecoder extends AbstractBusinessObjectDecoder {
	
	public static final char EQUALS_SIGN = '=';
	public static final char AMPERSAND = '&';
	private Logger logger = LoggerFactory.getLogger(getClass());
	private static String TRANSACTION_BUSINESS_OBJECT_NAME = "TRANSACTION";

	/* (non-Javadoc)
	 * @see org.jboss.netty.handler.codec.oneone.OneToOneDecoder#decode(org.jboss.netty.channel.ChannelHandlerContext, org.jboss.netty.channel.Channel, java.lang.Object)
	 */
	@Override
	protected Object decode(ChannelHandlerContext ctx, Channel channel, Object object) throws Exception 
	{
		HttpRequest request = (HttpRequest) object;
		if(!isValidHttpMessage(channel, request)){
			// Just send a bad request response status
			// don't want to specify exactly what went wrong
			this.sendResponse(channel, HttpResponseStatus.BAD_REQUEST);
			return null;
		}
		try {
			Map<String, String> postData = this.parsedData(request.getContent(), channel);
			System.out.println("Recieved parsed POST data:" + postData);
			BusinessObjectData businessObject = new BusinessObjectDataImpl();
			for(String postDataAttribute : postData.keySet())
				businessObject.setDataValue(postDataAttribute, postData.get(postDataAttribute));
			logger.debug("Http Transaction Data Handler");
			businessObject.setBusinessObjectName(TRANSACTION_BUSINESS_OBJECT_NAME);
			return businessObject;
		} catch (Exception e) {
			ExceptionUtil.logException(e);
			this.sendResponse(channel, HttpResponseStatus.BAD_REQUEST);
		}
		return null;
	}

	/**
	 * Validates the {@link HttpRequest}.
	 * 
	 * @param channel
	 * @param request
	 * @return
	 */
	private Boolean isValidHttpMessage(Channel channel, HttpRequest request) {
		if(!this.checkMethod(request, channel)){
			logger.debug("Invalid Http request method: {}", request.getMethod());
			return false;
		}
		if(!this.checkContentLength(request, channel)){
			logger.debug("Invalid content length: {}", HttpHeaders.getContentLength(request));
			return false;
		}
		return true;
	}

	/**
	 * The {@link HttpMethod} used to send the {@link HttpRequest}
	 * Only Http POST requests are allowed.
	 * 
	 * @param request
	 * @param channel
	 */
	private Boolean checkMethod(HttpRequest request, Channel channel) {

		HttpMethod method = request.getMethod();
		if (method.compareTo(HttpMethod.POST) == 0)
			return true;
		return false;
	}

	/**
	 * Checks the content length of the {@link HttpRequest}.
	 * If there is no content it will return the appropriate
	 * response.
	 * @param request
	 * @param channel
	 */
	private Boolean checkContentLength(HttpRequest request, Channel channel) {
		Long contentLength = HttpHeaders.getContentLength(request);
		if (contentLength > 0)
			return true;
		return false;
	}
	
	/**
	 * Send a {@link HttpResponse} back to the client.
	 * 
	 * @param channel the channel to use.
	 * @param status the {@link HttpResponseStatus} to use.
	 */
	private void sendResponse(Channel channel, HttpResponseStatus status)
	{
		HttpResponse response = new DefaultHttpResponse(HttpVersion.HTTP_1_1, status);
		response.setContent(ChannelBuffers.EMPTY_BUFFER);
		HttpHeaders.setContentLength(response, 0);
		channel.write(response);
	}

	/**
	 * Parses the post data into a {@link Map} of
	 * POST key and data values.
	 * 
	 * @param content
	 * @param channel
	 * @return
	 * @throws ApplicationException
	 */
	private Map<String, String> parsedData(ChannelBuffer content, Channel channel) throws ApplicationException {

		Map<String, String> data = new HashMap<String, String>();
		String postData = content.toString(Charset.defaultCharset());
		if(!StringUtil.isValidString(postData))
			throw new ApplicationException(
					ErrorCodes.HTDH_PARSE_DATA_ERROR_CD, 
					ErrorCodes.HTDH_INVALID_POST_DATA_ERROR_MSG);
		String[] keyValuePairs = StringUtil.split(postData, AMPERSAND);
		for(String keyValuePair : keyValuePairs){
			String[] keyAndValue = StringUtil.split(keyValuePair, EQUALS_SIGN);
			if(keyAndValue.length < 2)
				throw new ApplicationException(
						ErrorCodes.HTDH_PARSE_DATA_ERROR_CD, 
						ErrorCodes.HTDH_INVALID_KEY_VALUE_PAIR_ERROR_MSG);
			String key = keyAndValue[0];
			String value = keyAndValue[1];
			data.put(key, value);
		}
		logger.debug("Processed POST data into key value pairs :{}", data);
		return data;
	}
}
