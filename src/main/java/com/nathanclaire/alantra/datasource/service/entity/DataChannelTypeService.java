/**
 * 
 */
package com.nathanclaire.alantra.datasource.service.entity;

import com.nathanclaire.alantra.base.service.entity.BaseEntityService;
import com.nathanclaire.alantra.datasource.model.DataChannelType;
import com.nathanclaire.alantra.datasource.request.DataChannelTypeRequest;
import com.nathanclaire.alantra.datasource.response.DataChannelTypeResponse;

/**
 * @author Edward Banfa
 *
 */
public interface DataChannelTypeService extends BaseEntityService<DataChannelType, DataChannelTypeResponse, DataChannelTypeRequest>
{

	public static final String FILE_CSV_CHANNEL = "FILE_CSV";
	public static final String FILE_EXCEL_CHANNEL = "FILE_EXCEL";
	
}
