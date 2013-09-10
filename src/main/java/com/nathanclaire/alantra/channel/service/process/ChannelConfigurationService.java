/**
 * 
 */
package com.nathanclaire.alantra.channel.service.process;

import java.util.List;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.channel.config.ChannelConfiguration;

/**
 * @author Edward Banfa
 *
 */
public interface ChannelConfigurationService {

	public List<ChannelConfiguration> loadAll() throws ApplicationException;
	
	public ChannelConfiguration load(String channelCode) throws ApplicationException;

}
