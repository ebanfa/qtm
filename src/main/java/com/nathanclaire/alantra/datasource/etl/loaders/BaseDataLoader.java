/**
 * 
 */
package com.nathanclaire.alantra.datasource.etl.loaders;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.application.model.ApplicationEntity;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.request.BaseRequest;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.StringUtil;
import com.nathanclaire.alantra.datasource.etl.RowDataLite;
import com.nathanclaire.alantra.datasource.etl.TableDataLite;
import com.nathanclaire.alantra.datasource.model.DataField;
import com.nathanclaire.alantra.datasource.service.entity.DataFieldTypeService;
import com.nathanclaire.alantra.datasource.service.entity.DataInputJobService;

/**
 * @author Edward Banfa 
 *
 */
public abstract class BaseDataLoader {
	
	private static final String RELATED_REQ_ID_SUFFIX = "Id";
	private static final String ENTITY_REQ_CLASS_SUFFIX = "Request";
	private static final String ENTITY_REQUEST_PACKAGE_NAME = ".request.";
	private static final String APP_BASE_PACKAGE_NAME = "com.nathanclaire.alantra.";
	private static final String INVALID_ENTITY_CODE = "BaseDataLoader.INVALID_ENTITY_CODE";
	private static final String USR_TARGET_INVOCATION_ERROR = "BaseDataLoader.USR_TARGET_INVOCATION_ERROR";
	private static final String INVALID_ENTITY_REQUEST_CLASS = "BaseDataLoader.INVALID_ENTITY_REQUEST_CLASS";
	private static final String ENTITY_CLASS_NOT_FOUND = "BaseDataLoader.ENTITY_CLASS_NOT_FOUND";
	private static final String UNSUPPORTED_TARGET_FIELD_TYPE = "BaseDataLoader.UNSUPPORTED_TARGET_FIELD_TYPE";
	private static final String USR_WRONG_TARGET_FIELD_TYPE = "BaseDataLoader.USR_WRONG_TARGET_FIELD_TYPE";

	@Inject DataInputJobService inputJobService;
	@Inject ApplicationEntityService entityService;
	
	private Logger logger = LoggerFactory.getLogger(BaseDataLoader.class);
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.DataLoader#loadData(com.nathanclaire.alantra.datasource.etl.TableDataLite)
	 */
	public TableDataLite loadData(TableDataLite tableDataLite, Set<DataField> fields) throws ApplicationException {
		// Get the request class for the primary and secondary entities 
		Class<? extends BaseRequest> primEntityRequestClass = 
				getEntityRequestClass(tableDataLite.getPrimEntityName());
		Class<? extends BaseRequest> secEntityRequestClass = 
				getEntityRequestClass(tableDataLite.getSecEntityName());
		// Load each individual row
		int rowNo = 0;
		for(RowDataLite row: tableDataLite.getRows()){
			rowNo++;
			try {
				if(!row.isErrors()){
					logger.debug("Loading row {} of data for primary entity {} and secondary entity {}", rowNo,
							tableDataLite.getPrimEntityName(), tableDataLite.getSecEntityName());
					tableDataLite = loadTableDataRow(tableDataLite, fields, row, primEntityRequestClass, secEntityRequestClass);
				}
				else
					logger.debug("Ignoring row {} with errors for entity {}:", rowNo, tableDataLite.getPrimEntityName());
			} catch (ApplicationException e) {
				logger.error("Error loading entity data: {}", e.getCode());
			}
		}
		return tableDataLite;
	}
	
	private Class<? extends BaseRequest> getEntityRequestClass(String entityCode) throws ApplicationException {
		if(entityCode == null)	return null;
		ApplicationEntity entity = entityService.findByName(entityCode);
		if(entity != null)	return getEntityRequestClass(entity);
		return null;
	}

	/**
	 * @param entity
	 * @return
	 * @throws ApplicationException 
	 */
	private Class<? extends BaseRequest> getEntityRequestClass(ApplicationEntity entity) throws ApplicationException 
	{
		String[] entityCodeSplitArray =  entity.getCode().split("_"); 
		if(entityCodeSplitArray.length < 2)	throw new ApplicationException(INVALID_ENTITY_CODE);
		String entityPackageName = entityCodeSplitArray[0].toLowerCase();
		String entityRequestClassName = entity.getName().concat(ENTITY_REQ_CLASS_SUFFIX);
		String entityRequestPackageName = APP_BASE_PACKAGE_NAME.concat(entityPackageName).concat(ENTITY_REQUEST_PACKAGE_NAME);

		Class<? extends BaseRequest> entityRequestClass = null;
		try {
			entityRequestClass = (Class<? extends BaseRequest>) 
					Class.forName(entityRequestPackageName.concat(entityRequestClassName));
		} catch (ClassNotFoundException e) {
			throw new ApplicationException(ENTITY_CLASS_NOT_FOUND);
		}
		return entityRequestClass;
	}
	
	protected void invokeMethodOnEntityRequestInstance(Class<? extends BaseRequest> entityRequestClass, 
			BaseRequest entityRequestInstance, DataField field, String dataType, Object data) throws ApplicationException {
		if(entityRequestClass == null)
			throw new ApplicationException(INVALID_ENTITY_REQUEST_CLASS);
		// Array of the type of the single parameter
		try 
		{
			String fieldSetterMehodNm = getFieldSetterMethodName(field);
			Method method = entityRequestClass.getDeclaredMethod(fieldSetterMehodNm, getParameterType(dataType, field));
			logger.debug("Executing method {} with field setter name {} on class {} " +
					"for field {} with configured data type {} and cell data type {} and data {}", 
					method.getName(), fieldSetterMehodNm, entityRequestClass.getName(), 
					field.getName(), field.getDataFieldType().getName(), dataType, data);
			// Cast the data into the required type and  invoke the method on the instance
			if(dataType.equals(DataFieldTypeService.STRING))
				invokeStringMethodOnEntity(entityRequestInstance, data, method);
			else if (dataType.equals(DataFieldTypeService.INTEGER))
				invokeIntegerMethodOnEntity(entityRequestInstance, data, method);
			else if (dataType.equals(DataFieldTypeService.DATE))
				invokeDateMethodOnEntity(entityRequestInstance, data, method);
			else if (dataType.equals(DataFieldTypeService.DECIMAL))
				invokeDecimalMethodOnEntity(entityRequestInstance, data, method);
			else if (dataType.equals(DataFieldTypeService.RELATIONSHIP))
				invokeIntegerMethodOnEntity(entityRequestInstance, data, method);
			else
				throw new ApplicationException(UNSUPPORTED_TARGET_FIELD_TYPE);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new ApplicationException(USR_TARGET_INVOCATION_ERROR, e.getMessage());
		}
	}

	/**
	 * @param entityRequestInstance
	 * @param data
	 * @param method
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws ApplicationException
	 */
	private void invokeStringMethodOnEntity(BaseRequest entityRequestInstance,
			Object data, Method method) throws IllegalAccessException,
			IllegalArgumentException, InvocationTargetException,
			ApplicationException {
		if(data instanceof String)
			method.invoke(entityRequestInstance, (String) data);
		else
			throw new ApplicationException(USR_WRONG_TARGET_FIELD_TYPE);
	}

	/**
	 * @param entityRequestInstance
	 * @param data
	 * @param method
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws ApplicationException
	 */
	private void invokeIntegerMethodOnEntity(BaseRequest entityRequestInstance,
			Object data, Method method) throws IllegalAccessException,
			IllegalArgumentException, InvocationTargetException,
			ApplicationException {
		if(data instanceof Integer)
			method.invoke(entityRequestInstance, (Integer) data);
		else
			throw new ApplicationException(USR_WRONG_TARGET_FIELD_TYPE);
	}

	/**
	 * @param entityRequestInstance
	 * @param data
	 * @param method
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws ApplicationException
	 */
	private void invokeDecimalMethodOnEntity(BaseRequest entityRequestInstance,
			Object data, Method method) throws IllegalAccessException,
			IllegalArgumentException, InvocationTargetException,
			ApplicationException {
		if(data instanceof BigDecimal)
			method.invoke(entityRequestInstance, (BigDecimal) data);
		else
			throw new ApplicationException(USR_WRONG_TARGET_FIELD_TYPE);
	}

	/**
	 * @param entityRequestInstance
	 * @param data
	 * @param method
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws ApplicationException
	 */
	private void invokeDateMethodOnEntity(BaseRequest entityRequestInstance,
			Object data, Method method) throws IllegalAccessException,
			IllegalArgumentException, InvocationTargetException,
			ApplicationException {
		if(data instanceof Date)
			method.invoke(entityRequestInstance, (Date) data);
		else
			throw new ApplicationException(USR_WRONG_TARGET_FIELD_TYPE);
	}

	/**
	 * @param dataType
	 * @param field 
	 */
	protected Class[] getParameterType(String dataType, DataField field)
	{
		Class[] parameterType = new Class[1];
		if(dataType.equals(DataFieldTypeService.STRING))
			parameterType[0] = String.class;
		else if (dataType.equals(DataFieldTypeService.INTEGER))
			parameterType[0] = Integer.class;
		else if (dataType.equals(DataFieldTypeService.DATE))
			parameterType[0] = Date.class;
		else if (dataType.equals(DataFieldTypeService.DECIMAL))
			parameterType[0] = BigDecimal.class;
		else if (dataType.equals(DataFieldTypeService.RELATIONSHIP))
			parameterType[0] = Integer.class;
		return parameterType;
	}

	/**
	 * @param field
	 */
	protected String getFieldSetterMethodName(DataField field) {
		String setterMethodNameSuffix = "set";
		String entityFieldName = StringUtil.capitalizeFirstLetter(field.getTargetEntityField());
		if(field.getDataFieldType().getCode().equals(DataFieldTypeService.RELATIONSHIP))
			entityFieldName = entityFieldName.concat(RELATED_REQ_ID_SUFFIX);
		return setterMethodNameSuffix.concat(entityFieldName);
	}
	
	/**
	 * @param primEntityRequestClass
	 */
	protected BaseRequest getEntityInstance(Class<? extends BaseRequest> entityRequestClass) {
		if(entityRequestClass != null)
			try {
				return entityRequestClass.newInstance();
			} catch (Exception e) {
				logger.error(e.getMessage());
			} 
		return null;
	}

	/**
	 * @param tableDataLite
	 * @param currentRow
	 * @param secEntityRequestClass 
	 * @param primEntityRequestClass 
	 * @return
	 * @throws ApplicationException
	 */
	protected abstract TableDataLite loadTableDataRow(TableDataLite tableDataLite, Set<DataField> field, 
			RowDataLite currentRow,	Class<? extends BaseRequest> primEntityRequestClass,
			Class<? extends BaseRequest> secEntityRequestClass) throws ApplicationException;
}
