/**
 * 
 */
package com.nathanclaire.alantra.datasource.service.entity;

import com.nathanclaire.alantra.base.service.entity.BaseEntityService;
import com.nathanclaire.alantra.datasource.model.DataChannel;
import com.nathanclaire.alantra.datasource.request.DataChannelRequest;
import com.nathanclaire.alantra.datasource.response.DataChannelResponse;

/**
 * @author Edward Banfa
 *
 */
public interface DataChannelService extends BaseEntityService<DataChannel, DataChannelResponse, DataChannelRequest>
{

	String TRANSACTION_DATA_IMPORT_SERVICE = null;
	
}
