/**
 * 
 */
package com.nathanclaire.alantra.datasource.etl.transformation;

import java.math.BigDecimal;
import java.util.Date;

import javax.ejb.Stateless;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.model.BaseEntity;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.DateUtil;
import com.nathanclaire.alantra.base.util.ExceptionUtil;
import com.nathanclaire.alantra.base.util.StringUtil;
import com.nathanclaire.alantra.datasource.annotation.etl.NoOperationDataTransformer;
import com.nathanclaire.alantra.datasource.etl.util.CellData;
import com.nathanclaire.alantra.datasource.model.DataField;
import com.nathanclaire.alantra.datasource.model.DataFieldMap;
import com.nathanclaire.alantra.datasource.service.entity.DataFieldTypeService;

/**
 * @author Edward Banfa 
 */
@Stateless
@NoOperationDataTransformer
public class NoOperationDataTransformerImpl extends AbstractDataTransformer
		implements DataTransformer {
	protected Logger logger = LoggerFactory.getLogger(NoOperationDataTransformerImpl.class);


	/**
	 * @param cellData
	 * @param field
	 */
	protected CellData validateCellDataValueRequired(CellData cellData, DataField field) 
	{
		if(StringUtil.flagToBoolean(field.getRequiredFg()) && cellData.getData() == null)
		{
			cellData.setErrors(true);
			cellData.setStatusDescription(ENTITY_FIELD_ERROR);
			cellData.setStatusDescription(USR_WRONG_FIELD_DATA_TYPE);
		}
		return cellData;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.transformation.AbstractDataTransformer#transformStringField(com.nathanclaire.alantra.datasource.etl.util.CellData, com.nathanclaire.alantra.datasource.model.DataFieldMap)
	 */
	@Override
	protected CellData transformStringField(CellData cellData, DataFieldMap fieldMap) 
			throws ApplicationException {
		return cellData;
	}

	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.transformation.AbstractDataTransformer#transformCharacterField(com.nathanclaire.alantra.datasource.etl.util.CellData, com.nathanclaire.alantra.datasource.model.DataFieldMap)
	 */
	@Override
	protected CellData transformCharacterField(CellData cellData, DataFieldMap fieldMap) throws ApplicationException {
		if(cellData.getData() instanceof Character){
			return cellData;
			
		}
		else if(cellData.getData() instanceof String)	{
			try {
				String stringValue = (String) cellData.getData();
				cellData.setData(stringValue.charAt(0));
			} catch (Exception e) {
				cellData.setErrors(true);
				cellData.setStatusDescription(ENTITY_FIELD_ERROR);
				cellData.setStatusDescription(e.getMessage());
			}
		}
		else
			setCellDataTypeError(cellData);
		return cellData;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.transformation.AbstractDataTransformer#transformRelationshipField(com.nathanclaire.alantra.datasource.etl.util.CellData, com.nathanclaire.alantra.datasource.model.DataFieldMap)
	 */
	protected CellData transformRelationshipField(CellData cellData, DataFieldMap fieldMap) 
			throws ApplicationException 
	{
		if(cellData.getData() instanceof Integer)
			return cellData;
		else if(cellData.getData() instanceof String)	{
			try {
				String stringValue = (String) cellData.getData();
				//Integer integerValue = this.getRelatedIDFromRelatedCode(field, stringValue);
				BaseEntity entity = this.getRelatedObjectFromRelatedCode(fieldMap.getDataField(), stringValue);
				cellData.setData(entity);
			} catch (Exception e) {
				ExceptionUtil.logException(e);
				cellData.setErrors(true);
				cellData.setStatusDescription(ENTITY_RELATIONSHIP_FIELD_ERROR);
				cellData.setStatusDescription(e.getMessage());
			}
		}
		else
			setCellDataTypeError(cellData);
		return cellData;
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.transformation.AbstractDataTransformer#transformDateField(com.nathanclaire.alantra.datasource.etl.util.CellData, com.nathanclaire.alantra.datasource.model.DataFieldMap)
	 */
	protected CellData transformDateField(CellData cellData, DataFieldMap fieldMap) {
		if(cellData.getData() instanceof DateTime)
			cellData.setData(((DateTime) cellData.getData()).toDate());
		if(cellData.getData() instanceof Date)
			return cellData;
		else if(cellData.getData() instanceof String){
			try {
				String stringValue = (String) cellData.getData().toString();
				Date date = DateUtil.convertStringToJavaDate(stringValue);
				cellData.setData(date);
			} catch (Exception e) {
				cellData.setErrors(true);
				cellData.setStatusDescription(ENTITY_DATE_FIELD_ERROR);
				cellData.setStatusDescription(e.getMessage());
			}
		}
		else 
			setCellDataTypeError(cellData);
		return cellData;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.transformation.AbstractDataTransformer#transformDecimalField(com.nathanclaire.alantra.datasource.etl.util.CellData, com.nathanclaire.alantra.datasource.model.DataFieldMap)
	 */
	protected CellData transformDecimalField(CellData cellData, DataFieldMap fieldMap) {
		if(cellData.getData() instanceof BigDecimal)
			return cellData;
		else if(cellData.getData() instanceof String)	{
			try {
				String stringValue = (String) cellData.getData();
				cellData.setData(new BigDecimal(stringValue));
			} catch (Exception e) {
				cellData.setErrors(true);
				cellData.setStatusDescription(ENTITY_FIELD_ERROR);
				cellData.setStatusDescription(e.getMessage());
			}
		}
		else
			setCellDataTypeError(cellData);
		return cellData;
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.transformation.AbstractDataTransformer#transformIntegerField(com.nathanclaire.alantra.datasource.etl.util.CellData, com.nathanclaire.alantra.datasource.model.DataFieldMap)
	 */
	protected CellData transformIntegerField(CellData cellData, DataFieldMap fieldMap) {
		if(cellData.getData() instanceof Integer)
			return cellData;
		else if(cellData.getData() instanceof String)	{
			try {
				String stringValue = (String) cellData.getData();
				cellData.setData(Integer.valueOf(stringValue));
			} catch (Exception e) {
				cellData.setErrors(true);
				cellData.setStatusDescription(ENTITY_FIELD_ERROR);
				cellData.setStatusDescription(e.getMessage());
			}
		}
		else
			setCellDataTypeError(cellData);
		return cellData;
	}
}
