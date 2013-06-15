/**
 * 
 */
package com.nathanclaire.alantra.datasource.service.entity;

import com.nathanclaire.alantra.base.service.entity.BaseEntityService;
import com.nathanclaire.alantra.datasource.model.DataChannelAdapter;
import com.nathanclaire.alantra.datasource.request.DataChannelAdapterRequest;
import com.nathanclaire.alantra.datasource.response.DataChannelAdapterResponse;

/**
 * @author Edward Banfa
 *
 */
public interface DataChannelAdapterService extends BaseEntityService<DataChannelAdapter, DataChannelAdapterResponse, DataChannelAdapterRequest>
{
	public final String CSV_FILE_ADAPTER = "CSV_FILE_ADAPTER";
	public final String SMTP_POP_ADAPTER = "SMTP_POP_ADAPTER";
	public final String EXCEL_FILE_ADAPTER = "EXCEL_FILE_ADAPTER";
	
}
