/**
 * 
 */
package com.nathanclaire.alantra.channel.service.process;

import java.util.List;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.channel.config.HandlerConfiguration;

/**
 * @author Edward Banfa
 *
 */
public interface HandlerBuilderService {
	
	public QTMHandler build(HandlerConfiguration configuration) throws ApplicationException;
	
	public List<QTMHandler> buildAll(List<HandlerConfiguration> configurations) throws ApplicationException;

}
