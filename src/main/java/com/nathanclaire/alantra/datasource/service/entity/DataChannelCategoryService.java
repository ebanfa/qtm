/**
 * 
 */
package com.nathanclaire.alantra.datasource.service.entity;

import com.nathanclaire.alantra.base.service.entity.BaseEntityService;
import com.nathanclaire.alantra.datasource.model.DataChannelCategory;
import com.nathanclaire.alantra.datasource.request.DataChannelCategoryRequest;
import com.nathanclaire.alantra.datasource.response.DataChannelCategoryResponse;

/**
 * @author Edward Banfa
 *
 */
public interface DataChannelCategoryService extends BaseEntityService<DataChannelCategory, DataChannelCategoryResponse, DataChannelCategoryRequest>
{

	public static final String SMS_DATA_CHANNEL = "SMS_CHANNEL";
	public static final String EMAIL_DATA_CHANNEL = "EMAIL_CHANNEL";
	
}
