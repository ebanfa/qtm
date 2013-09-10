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
public interface ChannelBuilderService {

	/**
	 * Builds a {@link QTMChannel} from a {@link ChannelConfiguration}.
	 * 
	 * @param configuration for the channel
	 * @return the QTM channel object
	 * @throws ApplicationException if the channel cannot be created
	 */
	public QTMChannel build(ChannelConfiguration configuration) throws ApplicationException;

	/**
	 * Builds a list of {@link QTMChannel} from a list of {@link ChannelConfiguration}.
	 * @param configurations list of configurations
	 * @return the list of channels
	 * @throws ApplicationException if any of the channels cannot be created
	 */
	public List<QTMChannel> buildAll(List<ChannelConfiguration> configurations) throws ApplicationException;
	
}
