/**
 * 
 */
package com.nathanclaire.alantra.channel.service.process;

import java.util.List;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.channel.config.PipelineConfiguration;

/**
 * @author Edward Banfa
 *
 */
public interface PipelineBuilderService {
	
	public QTMPipeline build(PipelineConfiguration configuration) throws ApplicationException;

	public List<QTMPipeline> buildAll(List<PipelineConfiguration> configurations) throws ApplicationException;

}
