/**
 * 
 */
package com.nathanclaire.alantra.channel.service.process;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.channel.config.HandlerConfiguration;

/**
 * @author Edward Banfa
 *
 */
public interface QTMHandler {

	public void init() throws ApplicationException;
	
	public Object getHandler() throws ApplicationException;
	
	public void setHandler(Object handler) throws ApplicationException;
		
	public HandlerConfiguration getConfiguration() throws ApplicationException;
	
	public void setConfiguration(HandlerConfiguration configuration)  throws ApplicationException;
}
