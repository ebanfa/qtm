/**
 * 
 */
package com.nathanclaire.alantra.datasource.service.entity;

import com.nathanclaire.alantra.base.service.entity.BaseEntityService;
import com.nathanclaire.alantra.datasource.model.DataInputJobType;
import com.nathanclaire.alantra.datasource.request.DataInputJobTypeRequest;
import com.nathanclaire.alantra.datasource.response.DataInputJobTypeResponse;

/**
 * @author Edward Banfa
 *
 */
public interface DataInputJobTypeService extends BaseEntityService<DataInputJobType, DataInputJobTypeResponse, DataInputJobTypeRequest>
{

	public static final String CYCLIC = "CYCLIC";
	public static final String NON_CYCLIC = "NON_CYCLIC";
	
}
