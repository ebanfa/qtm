/**
 * 
 */
package com.nathanclaire.alantra.rule.util;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.model.BaseEntity;
import com.nathanclaire.alantra.base.util.DateUtil;
import com.nathanclaire.alantra.base.util.EntityUtil;
import com.nathanclaire.alantra.base.util.ExceptionUtil;
import com.nathanclaire.alantra.base.util.StringUtil;
import com.nathanclaire.alantra.rule.engine.BusinessObjectData;
import com.nathanclaire.alantra.rule.engine.BusinessObjectDataImpl;

/**
 * @author Edward Banfa
 *
 */
public class BusinessObjectUtil {
	
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

	public static BaseEntity createBusinessObjectInstance(
			BusinessObjectData businessObjectData) {
		// Create a new instance of the business object
		Object object = 
				EntityUtil.newInstance(businessObjectData.getBusinessObjectClassName());
		prepareBusinessObject(businessObjectData);
		copyData(businessObjectData, object);
		return (BaseEntity) object;
	}

	private static void copyData(BusinessObjectData businessObjectData,
			Object object) {
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
	
	public  static void printData(Object object) {
		// Get the class of the business object
		//
		logger.debug("Printing object data from {}" , object);
		Method[] methods = object.getClass().getMethods();
			// Loop through all the methods
			for(Method method: methods){
				if(method.getName().startsWith("get")){
					Class paramType = method.getParameterTypes()[0];
					logger.debug("Invoking method {} with parameter type {}", method.getName(), paramType.getName());
					try {
						Object returnValue = method.invoke(object, null);
						logger.debug("Invoked method {} wand got back {}", method.getName(), returnValue);
					} catch (Exception e) {
						e.printStackTrace();
						ExceptionUtil.logException(e);
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
