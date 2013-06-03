/**
 * 
 */
package com.nathanclaire.alantra.datasource.service.entity;

import com.nathanclaire.alantra.base.service.entity.BaseEntityService;
import com.nathanclaire.alantra.datasource.model.DataInputJobCategory;
import com.nathanclaire.alantra.datasource.request.DataInputJobCategoryRequest;
import com.nathanclaire.alantra.datasource.response.DataInputJobCategoryResponse;

/**
 * @author Edward Banfa
 *
 */
public interface DataInputJobCategoryService extends BaseEntityService<DataInputJobCategory, DataInputJobCategoryResponse, DataInputJobCategoryRequest>
{
	String TRANSACTION_DATA = "TRANSACTION_DATA";
	String CUSTOMER_DATA = "CUSTOMER_DATA";
}
