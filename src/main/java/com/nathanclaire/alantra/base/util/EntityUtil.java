/**
 * 
 */
package com.nathanclaire.alantra.base.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.application.model.ApplicationEntity;
import com.nathanclaire.alantra.base.request.BaseRequest;
import com.nathanclaire.alantra.datasource.model.DataField;
import com.nathanclaire.alantra.datasource.service.entity.DataFieldTypeService;
import com.nathanclaire.alantra.rule.engine.RuleAction;

/**
 * @author Edward Banfa 
 *
 */
public class EntityUtil {

	public static final String SET_METH_NAME_SUFFIX = "set";
	public static Object NULL_VALUE = null;
	public static Logger logger = LoggerFactory.getLogger(EntityUtil.class);
	public static final String ENTITY_REQ_CLASS_SUFFIX = "Request";
	public static final String ENTITY_REQUEST_PACKAGE_NAME = ".request.";
	public static final String APP_BASE_PACKAGE_NAME = "com.nathanclaire.alantra.";
	public static final String INVALID_ENTITY_CODE = "BaseDataLoader.INVALID_ENTITY_CODE";
	public static final String ENTITY_CLASS_NOT_FOUND = "BaseDataLoader.ENTITY_CLASS_NOT_FOUND";
	public static final String RELATED_REQ_ID_SUFFIX = "Id";
	public static final String USR_TARGET_INVOCATION_ERROR = "BaseDataLoader.USR_TARGET_INVOCATION_ERROR";
	public static final String INVALID_ENTITY_REQUEST_CLASS = "BaseDataLoader.INVALID_ENTITY_REQUEST_CLASS";
	public static final String UNSUPPORTED_TARGET_FIELD_TYPE = "BaseDataLoader.UNSUPPORTED_TARGET_FIELD_TYPE";
	public static final String USR_WRONG_TARGET_FIELD_TYPE = "BaseDataLoader.USR_WRONG_TARGET_FIELD_TYPE";
	
	/**
	 * @param object
	 * @param execptionCode
	 * @param exceptionMsg
	 * @return
	 * @throws ApplicationException
	 */
	public static Object returnOrThrowIfNull(Object object, String execptionCode, String exceptionMsg) throws ApplicationException
	{
		if(object != null) return object;
		else throw new ApplicationException(execptionCode, exceptionMsg);
	}

	/**
	 * @param objects
	 * @param execptionCode
	 * @param exceptionMsg
	 * @return
	 * @throws ApplicationException
	 */
	public static Object[] returnOrThrowIfContainsNull(Object[] objects, String execptionCode, String exceptionMsg) 
			throws ApplicationException
	{
		for(int i=0; i < objects.length; i++)
			if(objects[i] == null)
				throw new ApplicationException(execptionCode, exceptionMsg);
		return objects;
	}
	/**
	 * @param objects
	 * @return
	 * @throws ApplicationException
	 */
	public static Object[] returnOrThrowIfParamsArrayContainsNull(Object[] objects) 
			throws ApplicationException
	{
		
		return returnOrThrowIfContainsNull(objects, 
				ErrorCodes.BPS_SERVICE_EXECUTION_ERROR_CD, ErrorCodes.BPS_NULL_PARAMETER_ERROR_MSG);
	}
	
	public static Object[] returnOrThrowIfParamsArrayContainsNull(Object[] objects, String message) 
			throws ApplicationException
	{
		
		return returnOrThrowIfContainsNull(objects, 
				ErrorCodes.BPS_SERVICE_EXECUTION_ERROR_CD, message);
	}

	/**
	 * Default code string that can be used as a value for the code property on an entity
	 * @return
	 */
	public static String generateDefaultEntityCode() {
		return DateUtil.getCurrentTimeInMilliSeconds().toString();
	}
	

	/**
	 * @param entity
	 * @return
	 * @throws ApplicationException 
	 */
	public static Class<? extends BaseRequest> getEntityRequestClass(ApplicationEntity entity) throws ApplicationException 
	{
		String[] entityCodeSplitArray =  entity.getCode().split(StringUtil.UNDERSCORE); 
		if(entityCodeSplitArray.length < 2)	throw new ApplicationException(INVALID_ENTITY_CODE);
		String entityPackageName = entityCodeSplitArray[0].toLowerCase();
		String entityRequestClassName = entity.getName().concat(ENTITY_REQ_CLASS_SUFFIX);
		String entityRequestPackageName = APP_BASE_PACKAGE_NAME.concat(entityPackageName).concat(ENTITY_REQUEST_PACKAGE_NAME);

		Class<? extends BaseRequest> entityRequestClass = null;
		try {
			entityRequestClass = (Class<? extends BaseRequest>) Class.forName(entityRequestPackageName.concat(entityRequestClassName));
		} catch (ClassNotFoundException e) {
			throw new ApplicationException(ENTITY_CLASS_NOT_FOUND);
		}
		return entityRequestClass;
	}

	/**
	 * @param entityRequestClass
	 * @param entityRequestInstance
	 * @param field
	 * @param dataType
	 * @param data
	 * @throws ApplicationException
	 */
	public static void invokeMethodOnEntityRequestInstance(Class<? extends BaseRequest> entityRequestClass, 
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
				invokeMethodOnEntity(entityRequestInstance, data, method);
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
	public static void invokeMethodOnEntity(BaseRequest entityRequestInstance, Object data, Method method) 
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException,	ApplicationException {
		if(data instanceof String)
			method.invoke(entityRequestInstance, (String) data);
		if(data instanceof Integer)
			method.invoke(entityRequestInstance, (Integer) data);
		if(data instanceof BigDecimal)
			method.invoke(entityRequestInstance, (BigDecimal) data);
		if(data instanceof Date)
			method.invoke(entityRequestInstance, (Date) data);
		else
			throw new ApplicationException(USR_WRONG_TARGET_FIELD_TYPE);
	}

	/**
	 * @param dataType
	 * @param field 
	 */
	public static Class[] getParameterType(String dataType, DataField field)
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
	public static String getFieldSetterMethodName(DataField field) 
	{
		String entityFieldName = ""; // TODO StringUtil.capitalizeFirstLetter(field.getBusinessObjectFieldCd());
		if(field.getDataFieldType().getCode().equals(DataFieldTypeService.RELATIONSHIP))
			entityFieldName = entityFieldName.concat(RELATED_REQ_ID_SUFFIX);
		return SET_METH_NAME_SUFFIX.concat(entityFieldName);
	}
	
	/**
	 * @param primEntityRequestClass
	 * @throws ApplicationException 
	 */
	public static BaseRequest getEntityInstance(Class<? extends BaseRequest> entityRequestClass) throws ApplicationException {
		returnOrThrowIfParamsArrayContainsNull(new Object[]{entityRequestClass});
		try {
			return entityRequestClass.newInstance();
		} catch (Exception e) {
			ExceptionUtil.logException(e);
		}
		return null;
	}

	public static Object newInstance(String className) {
		try {
			return Class.forName(className).newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

}
