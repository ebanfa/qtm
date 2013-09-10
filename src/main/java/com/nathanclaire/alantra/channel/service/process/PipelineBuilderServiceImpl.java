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
import com.nathanclaire.alantra.channel.config.PipelineConfiguration;
import com.nathanclaire.alantra.channel.service.process.netty.QTMPipelineFactory;
import com.nathanclaire.alantra.channel.service.process.netty.QTMPipelineImpl;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class PipelineBuilderServiceImpl extends BaseProcessService implements PipelineBuilderService {

	@Inject QTMPipelineFactory qtmPipelineFactory;
	@Inject HandlerBuilderService handlerBuilderService;
	private Logger logger = LoggerFactory.getLogger(getClass());

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.channel.PipelineBuilderService#build(com.nathanclaire.alantra.channel.config.PipelineConfiguration)
	 */
	@Override
	public QTMPipeline build(PipelineConfiguration configuration) throws ApplicationException {
		logger.debug("Processing pipeline configuration for {} handlers.", configuration.getHandlers().size());
		EntityUtil.returnOrThrowIfParamsArrayContainsNull(
				new Object[]{configuration}, "PipelineBuilderServiceImpl.build");
		// Build all the handlers for this pipeline
		List<QTMHandler> handlers = handlerBuilderService.buildAll(configuration.getHandlers());
		QTMPipeline pipeline = new QTMPipelineImpl();
		pipeline.setHandlers(handlers);
		pipeline.setConfiguration(configuration);
		// This object here is actually a Netty ChannelPipelineFactory
		pipeline.setPipeline(qtmPipelineFactory.getPipeline(handlers));
		logger.debug("Pipeline built.");
		return pipeline;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.channel.PipelineBuilderService#buildAll(java.util.List)
	 */
	@Override
	public List<QTMPipeline> buildAll(List<PipelineConfiguration> configurations)
			throws ApplicationException 
	{
		logger.debug("Building {} pipelines.", configurations.size());
		EntityUtil.returnOrThrowIfParamsArrayContainsNull(new Object[]{configurations, "PipelineBuilderServiceImpl.buildAll"});
		List<QTMPipeline> pipelines = new ArrayList<QTMPipeline>();
		for(PipelineConfiguration configuration : configurations)
			pipelines.add(this.build(configuration));
		return pipelines;
	}

}
