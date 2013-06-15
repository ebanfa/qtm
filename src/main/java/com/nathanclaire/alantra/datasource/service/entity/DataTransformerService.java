/**
 * 
 */
package com.nathanclaire.alantra.datasource.service.entity;

import com.nathanclaire.alantra.base.service.entity.BaseEntityService;
import com.nathanclaire.alantra.datasource.model.DataTransformer;
import com.nathanclaire.alantra.datasource.request.DataTransformerRequest;
import com.nathanclaire.alantra.datasource.response.DataTransformerResponse;

/**
 * @author Edward Banfa
 *
 */
public interface DataTransformerService extends BaseEntityService<DataTransformer, DataTransformerResponse, DataTransformerRequest>
{

	public final String NO_OPERATION_DATA_TRANSFORMER = "NO_OPERATION_DATA_TRANSFORMER";
	public final String REL_ENTITY_CODE_TO_ID_DATA_TRANSFORMER = "REL_ENTITY_CODE_TO_ID_DATA_TRANSFORMER";
																   
	
}
