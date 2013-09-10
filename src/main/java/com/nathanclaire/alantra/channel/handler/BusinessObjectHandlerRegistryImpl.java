/**
 * 
 */
package com.nathanclaire.alantra.channel.handler;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.inject.Inject;

import org.jboss.netty.channel.ChannelHandler;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.channel.handler.codec.HttpTransactionDataDecoder;
import com.nathanclaire.alantra.channel.handler.codec.HttpTransactionDataEncoder;

/**
 * A registry of {@link BusinessObjectHandler}s. The handlers are cast to
 * {@link ChannelHandler} before being returned as the return value of the
 * implemented methods. 
 * {@link BusinessObjectHandler}s are Netty {@link ChannelHandler}s.
 * 
 * @author Edward Banfa
 *
 */
@Stateful
public class BusinessObjectHandlerRegistryImpl implements BusinessObjectHandlerRegistry {

	private static final String BUSINESS_OBJECT_ROUTING_HANDLER ="BO_ROUTER";
	private static final String BUSINESS_OBJECT_VALIDATION_HANDLER ="BO_VALIDATOR";
	private static final String BUSINESS_OBJECT_PROCESSING_HANDLER ="BO_PROCESSOR";
	private static final String HTTP_TRANSACTION_DATA_DECODER = "HTTP_TXN_DATA_DECODER";
	private static final String HTTP_TRANSACTION_DATA_ENCODER = "HTTP_TXN_DATA_ENCODER";
	
	@Inject BusinessObjectRoutingHandler businessObjectRoutingHandler;
	@Inject BusinessObjectValidationHandler businessObjectValidationHandler;
	@Inject BusinessObjectProcessingHandler businessObjectProcessingHandler;
	
	private Map<String, BusinessObjectHandler> handler = new HashMap<String, BusinessObjectHandler>();
	
	@PostConstruct
	private void init()
	{
		handler.put(BUSINESS_OBJECT_ROUTING_HANDLER, businessObjectRoutingHandler);
		handler.put(BUSINESS_OBJECT_PROCESSING_HANDLER, businessObjectProcessingHandler);
		handler.put(BUSINESS_OBJECT_VALIDATION_HANDLER, businessObjectValidationHandler);
		handler.put(HTTP_TRANSACTION_DATA_DECODER, new HttpTransactionDataDecoder());
		handler.put(HTTP_TRANSACTION_DATA_ENCODER, new HttpTransactionDataEncoder());
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.process.netty.handler.ChannelHandlerRegistry#get(java.lang.String)
	 */
	@Override
	public ChannelHandler get(String name) throws ApplicationException {
		return (ChannelHandler) handler.get(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.process.netty.handler.ChannelHandlerRegistry#containsHandler(java.lang.String)
	 */
	@Override
	public Boolean containsHandler(String name) throws ApplicationException {
		if(handler.containsKey(name))
			return true;
		return false;
	}

}
