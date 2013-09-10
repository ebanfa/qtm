/**
 * 
 */
package com.nathanclaire.alantra.datasource.etl.transformation;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.datasource.etl.util.CellData;
import com.nathanclaire.alantra.datasource.model.DataField;

/**
 * @author Edward Banfa 
 *
 */
public interface DataTransformer 
{
	public static final String ENTITY_FIELD_ERROR = "DataTransformer.ENTITY_FIELD_ERROR";
	public static final String ENTITY_DATE_FIELD_ERROR = "DataTransformer.ENTITY_DATE_FIELD_ERROR";
	public static final String USR_WRONG_FIELD_DATA_TYPE = "DataTransformer.USR_WRONG_FIELD_DATA_TYPE";
	public static final String ENTITY_RELATIONSHIP_FIELD_ERROR = "DataTransformer.ENTITY_RELATIONSHIP_FIELD_ERROR";

	public CellData transform(CellData cellData, DataField field) throws ApplicationException;
}
