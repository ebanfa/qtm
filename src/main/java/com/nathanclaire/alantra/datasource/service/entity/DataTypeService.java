/**
 * 
 */
package com.nathanclaire.alantra.datasource.service.entity;

import com.nathanclaire.alantra.base.service.entity.BaseEntityService;
import com.nathanclaire.alantra.datasource.model.DataType;
import com.nathanclaire.alantra.datasource.request.DataTypeRequest;
import com.nathanclaire.alantra.datasource.response.DataTypeResponse;

/**
 * @author Edward Banfa
 *
 */
public interface DataTypeService extends BaseEntityService<DataType, DataTypeResponse, DataTypeRequest>
{

	public static final String TRANSACTION_DATA = "TRANSACTION_DATA";
	public static final String ADVICE_REQUEST_DATA = "ADVICE_REQUEST_DATA";
	
}
