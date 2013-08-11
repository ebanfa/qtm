/**
 * 
 */
package com.nathanclaire.alantra.datasource.etl.transformers;

import java.math.BigDecimal;
import java.util.Date;

import javax.ejb.Stateless;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.DateUtil;
import com.nathanclaire.alantra.base.util.StringUtil;
import com.nathanclaire.alantra.datasource.etl.CellDataLite;
import com.nathanclaire.alantra.datasource.model.DataField;
import com.nathanclaire.alantra.datasource.service.entity.DataFieldTypeService;

/**
 * @author Edward Banfa 
 *
 */
@Stateless
public class NoOperationDataTransformerImpl extends BaseDataTransformer
		implements NoOperationDataTransformer {
	private Logger logger = LoggerFactory.getLogger(NoOperationDataTransformerImpl.class);

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.DataTransformer#transform(com.nathanclaire.alantra.datasource.etl.CellDataLite, com.nathanclaire.alantra.datasource.model.DataField)
	 */
	@Override
	public CellDataLite transform(CellDataLite cellDataLite, DataField field) throws ApplicationException 
	{
		// Validate the data (null data check)
		if(validateCellData(cellDataLite, field).isErrors())
			return cellDataLite;
		// Transform the data based on the data type
		if(field.getDataFieldType().getCode().equals(DataFieldTypeService.STRING))
			return cellDataLite;
		else if(field.getDataFieldType().getCode().equals(DataFieldTypeService.INTEGER)) {
			return transformIntegerField(cellDataLite);
		}
		else if(field.getDataFieldType().getCode().equals(DataFieldTypeService.DECIMAL)) {
			return transformDecimalField(cellDataLite);
		}
		else if(field.getDataFieldType().getCode().equals(DataFieldTypeService.DATE)) {
			return transformDateField(cellDataLite);
		}
		else if(field.getDataFieldType().getCode().equals(DataFieldTypeService.RELATIONSHIP)) {
			return transformRelationshipField(cellDataLite, field);
		}
		else {
			setCellDataTypeError(cellDataLite);
			return cellDataLite;
		}
	}

	/**
	 * @param cellDataLite
	 * @param field
	 */
	private CellDataLite validateCellData(CellDataLite cellDataLite, DataField field) {
		if(cellDataLite.getData() == null){
			if(StringUtil.flagToBoolean(field.getRequiredFg()))	{
				cellDataLite.setErrors(true);
				cellDataLite.setStatusDescription(ENTITY_FIELD_ERROR);
				cellDataLite.setStatusDescription(USR_WRONG_FIELD_DATA_TYPE);
			}
		}
		return cellDataLite;
	}

	/**
	 * @param cellDataLite
	 * @param field
	 * @return
	 * @throws ApplicationException
	 */
	private CellDataLite transformRelationshipField(CellDataLite cellDataLite,	DataField field) 
			throws ApplicationException 
	{
		if(cellDataLite.getData() instanceof Integer)
			return cellDataLite;
		else if(cellDataLite.getData() instanceof String)	{
			try {
				String stringValue = (String) cellDataLite.getData();
				Integer integerValue = this.getRelatedIDFromRelatedCode(field, stringValue);
				cellDataLite.setData(integerValue);
			} catch (Exception e) {
				logger.error(e.getMessage());
				cellDataLite.setErrors(true);
				cellDataLite.setStatusDescription(ENTITY_RELATIONSHIP_FIELD_ERROR);
				cellDataLite.setStatusDescription(e.getMessage());
			}
		}
		else
			setCellDataTypeError(cellDataLite);
		return cellDataLite;
	}

	/**
	 * @param cellDataLite
	 * @return
	 */
	private CellDataLite transformDateField(CellDataLite cellDataLite) {
		if(cellDataLite.getData() instanceof DateTime)
			cellDataLite.setData(((DateTime) cellDataLite.getData()).toDate());
		if(cellDataLite.getData() instanceof Date)
			return cellDataLite;
		else if(cellDataLite.getData() instanceof String){
			try {
				String stringValue = (String) cellDataLite.getData().toString();
				Date date = DateUtil.convertStringToJavaDate(stringValue);
				cellDataLite.setData(date);
			} catch (Exception e) {
				cellDataLite.setErrors(true);
				cellDataLite.setStatusDescription(ENTITY_DATE_FIELD_ERROR);
				cellDataLite.setStatusDescription(e.getMessage());
			}
		}
		else 
			setCellDataTypeError(cellDataLite);
		return cellDataLite;
	}

	/**
	 * @param cellDataLite
	 * @return
	 */
	private CellDataLite transformDecimalField(CellDataLite cellDataLite) {
		if(cellDataLite.getData() instanceof BigDecimal)
			return cellDataLite;
		else if(cellDataLite.getData() instanceof String)	{
			try {
				String stringValue = (String) cellDataLite.getData();
				cellDataLite.setData(new BigDecimal(stringValue));
			} catch (Exception e) {
				cellDataLite.setErrors(true);
				cellDataLite.setStatusDescription(ENTITY_FIELD_ERROR);
				cellDataLite.setStatusDescription(e.getMessage());
			}
		}
		else
			setCellDataTypeError(cellDataLite);
		return cellDataLite;
	}

	/**
	 * @param cellDataLite
	 * @return
	 */
	private CellDataLite transformIntegerField(CellDataLite cellDataLite) {
		if(cellDataLite.getData() instanceof Integer)
			return cellDataLite;
		else if(cellDataLite.getData() instanceof String)	{
			try {
				String stringValue = (String) cellDataLite.getData();
				cellDataLite.setData(Integer.valueOf(stringValue));
			} catch (Exception e) {
				cellDataLite.setErrors(true);
				cellDataLite.setStatusDescription(ENTITY_FIELD_ERROR);
				cellDataLite.setStatusDescription(e.getMessage());
			}
		}
		else
			setCellDataTypeError(cellDataLite);
		return cellDataLite;
	}

	/**
	 * @param cellDataLite
	 */
	private void setCellDataTypeError(CellDataLite cellDataLite) {
		cellDataLite.setErrors(true);
		cellDataLite.setStatusDescription(ENTITY_FIELD_ERROR);
		cellDataLite.setStatusDescription(USR_WRONG_FIELD_DATA_TYPE);
	}
}
