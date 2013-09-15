/**
 * 
 */
package com.nathanclaire.alantra.datasource.service.entity;

import com.nathanclaire.alantra.base.service.entity.BaseEntityService;
import com.nathanclaire.alantra.datasource.model.DataFieldType;
import com.nathanclaire.alantra.datasource.request.DataFieldTypeRequest;
import com.nathanclaire.alantra.datasource.response.DataFieldTypeResponse;

/**
 * @author Edward Banfa
 *
 */
public interface DataFieldTypeService extends BaseEntityService<DataFieldType, DataFieldTypeResponse, DataFieldTypeRequest>
{
	public final String DATE = "DATE";
	public final String STRING = "STRING";
	public final String DECIMAL = "DECIMAL";
	public final String INTEGER = "INTEGER";
	public final String CHARACTER = "CHARACTER";
	public final String RELATIONSHIP = "RELATIONSHIP";
	
}
