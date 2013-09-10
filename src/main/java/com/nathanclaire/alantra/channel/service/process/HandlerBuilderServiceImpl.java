/**
 * 
 */
package com.nathanclaire.alantra.channel.service.process;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.service.process.BaseProcessService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.EntityUtil;
import com.nathanclaire.alantra.channel.config.HandlerConfiguration;
import com.nathanclaire.alantra.channel.service.process.netty.QTMHandlerImpl;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class HandlerBuilderServiceImpl extends BaseProcessService implements HandlerBuilderService {
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.channel.HandlerBuilderService#build(com.nathanclaire.alantra.channel.config.HandlerConfiguration)
	 */
	@Override
	public QTMHandler build(HandlerConfiguration configuration)	throws ApplicationException {
		EntityUtil.returnOrThrowIfParamsArrayContainsNull(new Object[]{configuration, "HandlerBuilderServiceImpl.build"});
		logger.debug("Processing channel handler configuration {}.", configuration.getClassName());
		QTMHandler handler = new QTMHandlerImpl();
		handler.setConfiguration(configuration);
		logger.debug("Handler built.");
		return handler;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.channel.HandlerBuilderService#buildAll(java.util.List)
	 */
	@Override
	public List<QTMHandler> buildAll(List<HandlerConfiguration> configurations)	throws ApplicationException 
	{
		logger.debug("Building {} channel handlers", configurations.size());
		EntityUtil.returnOrThrowIfParamsArrayContainsNull(new Object[]{configurations, "HandlerBuilderServiceImpl.buildAll"});
		List<QTMHandler> handlers = new ArrayList<QTMHandler>();
		for(HandlerConfiguration configuration :configurations )
			handlers.add(build(configuration));
		return handlers;
	}

}
