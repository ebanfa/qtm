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

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.DateUtil;
import com.nathanclaire.alantra.base.util.StringUtil;
import com.nathanclaire.alantra.datasource.annotation.etl.NoOperationDataTransformer;
import com.nathanclaire.alantra.datasource.etl.util.CellData;
import com.nathanclaire.alantra.datasource.model.DataField;
import com.nathanclaire.alantra.datasource.service.entity.DataFieldTypeService;

/**
 * @author Edward Banfa 
 *
 */
@Stateless
@NoOperationDataTransformer
public class NoOperationDataTransformerImpl extends BaseDataTransformer
		implements DataTransformer {
	private Logger logger = LoggerFactory.getLogger(NoOperationDataTransformerImpl.class);

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.DataTransformer#transform(com.nathanclaire.alantra.datasource.etl.CellDataLite, com.nathanclaire.alantra.datasource.model.DataField)
	 */
	@Override
	public CellData transform(CellData cellData, DataField field) throws ApplicationException 
	{
		// Validate the data (null data check)
		if(validateCellDataValueRequired(cellData, field).isErrors()) return cellData;
		// Transform the data based on the data type
		if(field.getDataFieldType().getCode().equals(DataFieldTypeService.STRING))
			return cellData;
		else if(field.getDataFieldType().getCode().equals(DataFieldTypeService.INTEGER)) 
			return transformIntegerField(cellData);
		else if(field.getDataFieldType().getCode().equals(DataFieldTypeService.DECIMAL))
			return transformDecimalField(cellData);
		else if(field.getDataFieldType().getCode().equals(DataFieldTypeService.DATE))
			return transformDateField(cellData);
		else if(field.getDataFieldType().getCode().equals(DataFieldTypeService.RELATIONSHIP))
			return transformRelationshipField(cellData, field);
		else {
			setCellDataTypeError(cellData);
			return cellData;
		}
	}

	/**
	 * @param cellData
	 * @param field
	 */
	private CellData validateCellDataValueRequired(CellData cellData, DataField field) 
	{
		if(StringUtil.flagToBoolean(field.getRequiredFg()) && cellData.getData() == null)
		{
			cellData.setErrors(true);
			cellData.setStatusDescription(ENTITY_FIELD_ERROR);
			cellData.setStatusDescription(USR_WRONG_FIELD_DATA_TYPE);
		}
		return cellData;
	}

	/**
	 * @param cellData
	 * @param field
	 * @return
	 * @throws ApplicationException
	 */
	private CellData transformRelationshipField(CellData cellData,	DataField field) 
			throws ApplicationException 
	{
		if(cellData.getData() instanceof Integer)
			return cellData;
		else if(cellData.getData() instanceof String)	{
			try {
				String stringValue = (String) cellData.getData();
				Integer integerValue = this.getRelatedIDFromRelatedCode(field, stringValue);
				cellData.setData(integerValue);
			} catch (Exception e) {
				logger.error(e.getMessage());
				cellData.setErrors(true);
				cellData.setStatusDescription(ENTITY_RELATIONSHIP_FIELD_ERROR);
				cellData.setStatusDescription(e.getMessage());
			}
		}
		else
			setCellDataTypeError(cellData);
		return cellData;
	}

	/**
	 * @param cellData
	 * @return
	 */
	private CellData transformDateField(CellData cellData) {
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

	/**
	 * @param cellData
	 * @return
	 */
	private CellData transformDecimalField(CellData cellData) {
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

	/**
	 * @param cellData
	 * @return
	 */
	private CellData transformIntegerField(CellData cellData) {
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

	/**
	 * @param cellData
	 */
	private void setCellDataTypeError(CellData cellData) {
		cellData.setErrors(true);
		cellData.setStatusDescription(ENTITY_FIELD_ERROR);
		cellData.setStatusDescription(USR_WRONG_FIELD_DATA_TYPE);
	}
}
