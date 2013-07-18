/**
 * 
 */
package com.nathanclaire.alantra.security.service.process;

import java.util.List;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.datasource.model.DataChannel;
import com.nathanclaire.alantra.datasource.model.DataChannelCategory;
import com.nathanclaire.alantra.security.model.SystemUser;

/**
 * @author Edward Banfa 
 *
 */
public interface SystemUserChannelService {
	

	public static final String INVALID_USER_SPECIFIED = "SystemUserChannelService.INVALID_USER_SPECIFIED";
	
	public List<DataChannel> getSystemUserOutboundChannels(SystemUser systemUser) throws ApplicationException;

	public List<DataChannel> getSystemUserChannels(SystemUser systemUser) throws ApplicationException;

	public List<DataChannelCategory> getSystemUserChannelCategories(SystemUser systemUser) throws ApplicationException;
}
