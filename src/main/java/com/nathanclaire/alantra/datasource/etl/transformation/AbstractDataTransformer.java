/**
 * 
 */
package com.nathanclaire.alantra.datasource.etl.transformation;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.model.BaseEntity;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.EntityUtil;
import com.nathanclaire.alantra.base.util.ErrorCodes;
import com.nathanclaire.alantra.base.util.ExceptionUtil;
import com.nathanclaire.alantra.base.util.StringUtil;
import com.nathanclaire.alantra.datasource.etl.util.CellData;
import com.nathanclaire.alantra.datasource.model.DataField;
import com.nathanclaire.alantra.datasource.model.DataFieldMap;
import com.nathanclaire.alantra.datasource.service.entity.DataFieldTypeService;

/**
 * @author Edward Banfa 
 *
 */
public abstract class AbstractDataTransformer implements DataTransformer {
	
	@Inject private EntityManager entityManager;
	private Logger logger = LoggerFactory.getLogger(getClass());
	private static final String E_WHERE = " e where e.code ='";
	private static final String E_SELECT = "select e from ";
	private static final String E_SELECT_ID = "select e.id from ";
	
	@Override
	public CellData transform(CellData cellData, DataFieldMap fieldMap) throws ApplicationException 
	{
		EntityUtil.returnOrThrowIfParamsArrayContainsNull(new Object[]{cellData, fieldMap}, 
				"AbstractDataTransformer.transform");
		DataField field = fieldMap.getDataField();
		logger.debug("Transforming cell data: {} for field: {} ", cellData, field);
		if(cellData.getData() == null) 
			return cellData;
		// Validate the data (null data check)
		if(validateCellDataValueRequired(cellData, field).isErrors()) return cellData;
		// Transform the data based on the data type
		if(field.getDataFieldType().getCode().equals(DataFieldTypeService.STRING))
			return transformStringField(cellData, fieldMap);
		else if(field.getDataFieldType().getCode().equals(DataFieldTypeService.CHARACTER)) 
			return transformCharacterField(cellData, fieldMap);
		else if(field.getDataFieldType().getCode().equals(DataFieldTypeService.INTEGER)) 
			return transformIntegerField(cellData, fieldMap);
		else if(field.getDataFieldType().getCode().equals(DataFieldTypeService.DECIMAL))
			return transformDecimalField(cellData, fieldMap);
		else if(field.getDataFieldType().getCode().equals(DataFieldTypeService.DATE))
			return transformDateField(cellData, fieldMap);
		else if(field.getDataFieldType().getCode().equals(DataFieldTypeService.RELATIONSHIP))
			return transformRelationshipField(cellData, fieldMap);
		else {
			setCellDataTypeError(cellData);
			return cellData;
		}
	}
	
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
	
	/**
	 * @param field
	 * @param code
	 * @return
	 */
	protected Integer getRelatedIDFromRelatedCode(DataField field, String code) throws ApplicationException
	{
		EntityUtil.returnOrThrowIfParamsArrayContainsNull(new Object[]{code, field}, 
				"AbstractDataTransformer.getRelatedIDFromRelatedCode");
		
		String queryString = E_SELECT_ID + field.getRelBusinessObjectCd() + E_WHERE + code + "'";
		logger.info("Using the follow query string: {}", queryString); 
		try {
			Query query = entityManager.createQuery(queryString);
			return (Integer) query.getSingleResult();
		} catch (NoResultException e) {
			ExceptionUtil.logAndProcessException(e, ErrorCodes.RELATED_ENTITY_NOT_FOUND_ERROR_CD);
		} catch (Exception e) {
			ExceptionUtil.logAndProcessException(e, ErrorCodes.RELATED_ENTITY_SEARCH_ERROR_CD);
		}
		return null;
	}
	
	/**
	 * @param field
	 * @param code
	 * @return
	 */
	protected BaseEntity getRelatedObjectFromRelatedCode(DataField field, String code) throws ApplicationException
	{
		EntityUtil.returnOrThrowIfParamsArrayContainsNull(new Object[]{code, field}, 
				"AbstractDataTransformer.getRelatedIDFromRelatedCode");
		
		String queryString = E_SELECT + field.getRelBusinessObjectCd() + E_WHERE + code + "'";
		logger.info("Using the follow query string: {}", queryString); 
		try {
			Query query = entityManager.createQuery(queryString);
			return (BaseEntity) query.getSingleResult();
		} catch (NoResultException e) {
			ExceptionUtil.logAndProcessException(e, ErrorCodes.RELATED_ENTITY_NOT_FOUND_ERROR_CD);
		} catch (Exception e) {
			ExceptionUtil.logAndProcessException(e, ErrorCodes.RELATED_ENTITY_SEARCH_ERROR_CD);
		}
		return null;
	}

	protected abstract CellData transformStringField(CellData cellData, DataFieldMap fieldMap) 
			throws ApplicationException;
	
	protected abstract CellData transformCharacterField(CellData cellData, DataFieldMap fieldMap) 
			throws ApplicationException;

	protected abstract CellData transformRelationshipField(CellData cellData, DataFieldMap fieldMap) 
			throws ApplicationException;

	protected abstract CellData transformDateField(CellData cellData, DataFieldMap fieldMap) 
			throws ApplicationException;
	
	protected abstract CellData transformDecimalField(CellData cellData, DataFieldMap fieldMap) 
			throws ApplicationException;

	protected abstract CellData transformIntegerField(CellData cellData, DataFieldMap fieldMap) 
			throws ApplicationException;

	/**
	 * @param cellData
	 */
	protected void setCellDataTypeError(CellData cellData) {
		setCellDataError(cellData, ENTITY_FIELD_ERROR, USR_WRONG_FIELD_DATA_TYPE);
	}

	protected void setCellDataError(CellData cellData, String statusText, String statusDescription){
		cellData.setErrors(true);
		cellData.setStatusText(statusText);
		cellData.setStatusDescription(statusDescription);
	}
}
