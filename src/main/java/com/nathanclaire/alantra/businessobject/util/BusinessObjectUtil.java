/**
 * 
 */
package com.nathanclaire.alantra.businessobject.util;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.model.BaseEntity;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.DateUtil;
import com.nathanclaire.alantra.base.util.EntityUtil;
import com.nathanclaire.alantra.base.util.ErrorCodes;
import com.nathanclaire.alantra.base.util.ExceptionUtil;
import com.nathanclaire.alantra.base.util.StringUtil;
import com.nathanclaire.alantra.businessobject.data.BusinessObjectData;
import com.nathanclaire.alantra.businessobject.data.BusinessObjectDataImpl;
import com.nathanclaire.alantra.businessobject.data.BusinessObjectFieldData;
import com.nathanclaire.alantra.businessobject.data.BusinessObjectFieldDataImpl;

/**
 * @author Edward Banfa
 *
 */
public class BusinessObjectUtil {
	
	public static final String ID_FIELD_NAME = "id";
	private static Logger logger = LoggerFactory.getLogger(BusinessObjectUtil.class);
	
	public static BusinessObjectData clone(BusinessObjectData businessObject){
		BusinessObjectData clone = new BusinessObjectDataImpl();
		clone.setBusinessObjectClassName(businessObject.getBusinessObjectClassName());
		clone.setBusinessObjectName(businessObject.getBusinessObjectName());
		clone.setProcessCategoryCode(businessObject.getProcessCategoryCode());
		clone.setValid(businessObject.isValid());
		clone.setProcessed(businessObject.isProcessed());
		clone.setRouted(businessObject.isRouted());
		for(String dataValueName: businessObject.getDataValues().keySet())
			clone.setDataValue(dataValueName, businessObject.getDataValues().get(dataValueName));
		return clone;
	}

	public static BaseEntity businessObjectToEntityInstance(
			BusinessObjectData businessObjectData) throws ApplicationException {
		// Create a new instance of the business object
		Object object = 
				EntityUtil.newInstance(businessObjectData.getBusinessObjectClassName());
		prepareBusinessObject(businessObjectData);
		copyDataFromBusinessObject(businessObjectData, object);
		return (BaseEntity) object;
	}

	private static void copyDataFromBusinessObject(BusinessObjectData businessObjectData, Object object) throws ApplicationException {
		EntityUtil.returnOrThrowIfParamsArrayContainsNull(new Object[]{
				businessObjectData,	object}, "BusinessObjectUtil.copyDataFromBusinessObject");
		// Get the class of the business object
		Class<? extends BaseEntity> businessObjectClass = (Class<? extends BaseEntity>) object.getClass();
		//
		logger.debug("Copying data from {}" , businessObjectData);
		Method[] methods = businessObjectClass.getMethods();
		for(String fieldName : businessObjectData.getDataValues().keySet()) {
			Object dataValue = businessObjectData.getDataValues().get(fieldName);
			String setterMethodName = "set" + StringUtil.capitalizeFirstLetter(fieldName);
			// Loop through all the methods
			for(Method method: methods){
				if(method.getName().equals(setterMethodName)){
					Class paramType = method.getParameterTypes()[0];
					logger.debug("Invoking method {} with parameter type {}", method.getName(), paramType.getName());
					try {
						method.invoke(object, dataValue);
					} catch (Exception e) {
						ExceptionUtil.logException(e);
					}
				}
			}
		}
	}
	public static void copyDataToBusinessObject(BusinessObjectData toBusinessObject, Object fromObject)  
			throws ApplicationException 
	{
		EntityUtil.returnOrThrowIfParamsArrayContainsNull(new Object[]{
				toBusinessObject,	fromObject}, "BusinessObjectUtil.copyDataToBusinessObject");// Get the class of the business object
		Class<? extends BaseEntity> businessObjectClass = (Class<? extends BaseEntity>) fromObject.getClass();
		// Get all the methods in the class of the business object/entity
		Method[] methods = businessObjectClass.getMethods();
		// Simple filter for the methods we are interested in
		String setterMethodPrefix = "get";
		// Loop through each method
		for(Method method: methods){
			// filter
			if(method.getName().startsWith(setterMethodPrefix) && isPropertyMethod(method)){
				// Get the type of the arguments
				if(method.getParameterTypes().length == 0){
					logger.debug("Invoking method {} ", method.getName());
					try {
						// Invoke
						Object returnValue = method.invoke(fromObject, new Object[0]);
						if(returnValue != null)
							toBusinessObject.setDataValue(
									propertyNameFromMethodName(method.getName()), returnValue);
					} catch (Exception e) {
						ExceptionUtil.logAndProcessException(e, ErrorCodes.BOU_DATA_COPY_TO_BO_ERROR_CD);
					}
				}
			}
		}
	}
	private static String propertyNameFromMethodName(String name) throws ApplicationException 
	{
		EntityUtil.returnOrThrowIfParamsArrayContainsNull(new Object[]{}, 
				"BusinessObjectUtil.propertyNameFromMethodName");
		if(name.length() > 3)
			return StringUtil.unCapitalizeFirstLetter(name.substring(3));
		return name;
	}

	private static boolean isPropertyMethod(Method method) {
		if(method.getName().equals("getClass"))
			return false;
		return true;
	}

	public static void copyDataToBusinessObject(BusinessObjectData toBusinessObject, 
			Object fromObject, List<BusinessObjectFieldData> fieldsToCopy) throws ApplicationException {
		
		EntityUtil.returnOrThrowIfParamsArrayContainsNull(new Object[]{
				toBusinessObject,	fromObject, fieldsToCopy}, "copyDataToBusinessObject");
		// Get the class of the business object
		Class<? extends BaseEntity> businessObjectClass = (Class<? extends BaseEntity>) fromObject.getClass();
		// Get all the methods in the class of the business object/entity
		Method[] methods = businessObjectClass.getMethods();
		// Loop through each method
		for(Method method: methods)
		{
			for(BusinessObjectFieldData fieldData: fieldsToCopy)
			{
				if(method.getName().equalsIgnoreCase("get" + fieldData.getFieldName()))
				{
					logger.debug("The method.getGenericReturnType() {}", method.getGenericReturnType());
					// Get the type of the arguments
					if(method.getParameterTypes().length == 0){
						try {
							BusinessObjectFieldData fieldDataValue = new BusinessObjectFieldDataImpl();
							fieldDataValue.setFieldName(fieldData.getFieldName());
							fieldDataValue.setFieldSequence(fieldData.getFieldSequence());
							// Invoke
							Object returnValue = method.invoke(fromObject, new Object[0]);
							if(returnValue != null)
							{
								Type typeOfField = method.getGenericReturnType();
								if(typeOfField.toString().contains(EntityUtil.BASE_PACKAGE_NM))
								{
									logger.debug("Processing relationship field {}", fieldData.getFieldName());
									BaseEntity relatedEntityInstance = (BaseEntity) returnValue;
									fieldDataValue.setFieldValue(relatedEntityInstance.getCode());
									fieldDataValue.setFieldDataType(fieldData.getFieldDataType());
								}
								else {
									if(fieldData.getFieldName().equals(ID_FIELD_NAME))
										toBusinessObject.setId((Integer) returnValue);
									logger.debug("Processing non relationship field {}", fieldData.getFieldName());
									fieldDataValue.setFieldValue(returnValue);
									fieldDataValue.setFieldDataType(fieldData.getFieldDataType());
								}
							}
							toBusinessObject.setDataValue(fieldData.getFieldName(), fieldDataValue);
						} catch (Exception e) {
							ExceptionUtil.logAndProcessException(e, ErrorCodes.BOU_DATA_COPY_TO_BO_ERROR_CD);
						}
					}
				}
			}
		}
		
	}
	

	public static BusinessObjectData prepareBusinessObject(BusinessObjectData businessObjectData) {
		if(businessObjectData == null)
			businessObjectData = new BusinessObjectDataImpl();
		if(businessObjectData.getDataValue("code") == null)
			businessObjectData.setDataValue("code", DateUtil.getCurrentTime());
		if(businessObjectData.getDataValue("effectiveDt") == null)
			businessObjectData.setDataValue("effectiveDt", new Date());
		if(businessObjectData.getDataValue("createdDt") == null)
			businessObjectData.setDataValue("createdDt", new Date());
		return businessObjectData;
	}
	
	public static BusinessObjectData prepareBusinessObject(String businessObjectName, 
			String businessObjectClassName, String processCategoryCode, Map<String, Object> dataValues) {
		
		BusinessObjectData businessObjectData = new BusinessObjectDataImpl();
		businessObjectData.setBusinessObjectName(businessObjectName);
		businessObjectData.setBusinessObjectClassName(businessObjectClassName);
		businessObjectData.setProcessCategoryCode(processCategoryCode);
		businessObjectData.setDataValues(dataValues);
		businessObjectData.setDataValue("name", businessObjectName.concat("@").concat(DateUtil.getCurrentTime()));
		businessObjectData.setDataValue("code", DateUtil.getCurrentTime());
		businessObjectData.setDataValue("effectiveDt", new Date());
		businessObjectData.setDataValue("createdDt", new Date());
		businessObjectData.setDataValue("createdByUsr", "System");
		businessObjectData.setDataValue("recSt", 'A');
		return businessObjectData;
	}
	

}
