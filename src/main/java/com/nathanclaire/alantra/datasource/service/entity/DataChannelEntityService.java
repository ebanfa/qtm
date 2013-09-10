/**
 * 
 */
package com.nathanclaire.alantra.datasource.service.entity;

import com.nathanclaire.alantra.base.service.entity.BaseEntityService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.datasource.model.DataChannel;
import com.nathanclaire.alantra.datasource.model.DataChannelCategory;
import com.nathanclaire.alantra.datasource.request.DataChannelRequest;
import com.nathanclaire.alantra.datasource.response.DataChannelResponse;

/**
 * @author Edward Banfa
 *
 */
public interface DataChannelEntityService extends BaseEntityService<DataChannel, DataChannelResponse, DataChannelRequest>
{
	public final String INBOUND_CHANNEL = "I";
	public final String OUTBOUND_CHANNEL = "O";
	public final String BIRDIRECTIONAL_CHANNEL = "B";
	public final String TRANSACTION_DATA_IMPORT_SERVICE = "TRANSACTION_DATA_IMPORT_SERVICE";
	
	public static final String CONFIG_ERROR_INVALID_NO_PARENT_TYPE_FOUND = 
			"DataChannelService.CONFIG_ERROR_INVALID_NO_PARENT_TYPE_FOUND";
	public static final String CONFIG_ERROR_INVALID_NO_PARENT_CATEGORY_FOUND = 
			"DataChannelService.CONFIG_ERROR_INVALID_NO_PARENT_CATEGORY_FOUND";
	public static final String INVALID_CHANNEL_SPECIFIED = "DataChannelService.INVALID_CHANNEL_SPECIFIED";
	
	public DataChannelCategory getChannelCategory(DataChannel channel) throws ApplicationException;
	
}
