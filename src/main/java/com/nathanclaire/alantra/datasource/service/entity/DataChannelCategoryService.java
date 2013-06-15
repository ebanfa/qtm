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

	String EMAIL_DATA_SOURCE_CATEGORY = "EMAIL";
	
}
