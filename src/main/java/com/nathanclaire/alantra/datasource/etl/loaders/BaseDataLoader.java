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
import com.nathanclaire.alantra.base.util.PropertyUtils;
import com.nathanclaire.alantra.base.util.StringUtil;
import com.nathanclaire.alantra.datasource.etl.RowData;
import com.nathanclaire.alantra.datasource.etl.TableData;
import com.nathanclaire.alantra.datasource.model.DataField;
import com.nathanclaire.alantra.datasource.model.DataInputJob;
import com.nathanclaire.alantra.datasource.model.DataInputJobSummary;
import com.nathanclaire.alantra.datasource.request.DataInputJobSummaryRequest;
import com.nathanclaire.alantra.datasource.service.entity.DataFieldTypeService;
import com.nathanclaire.alantra.datasource.service.entity.DataInputJobService;
import com.nathanclaire.alantra.datasource.service.entity.DataInputJobSummaryService;
import com.nathanclaire.alantra.datasource.service.entity.DataInputJobSummaryStatusService;

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
	private static final String COULD_NOT_CREATE_JOB_SUMMARY = "BaseDataLoader.COULD_NOT_CREATE_JOB_SUMMARY";
	
	@Inject ApplicationEntityService entityService;
	@Inject DataInputJobService inputJobService;
	@Inject DataInputJobSummaryService summaryService;
	@Inject DataInputJobSummaryStatusService summaryStatusService;
	
	private Logger logger = LoggerFactory.getLogger(BaseDataLoader.class);
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.DataLoader#loadData(com.nathanclaire.alantra.datasource.etl.TableData)
	 */
	public TableData loadData(TableData tableData, Set<DataField> fields) throws ApplicationException {
		logger.debug("Loading {} rows of data for primary entity {} and secondary entity {}", tableData.getRows().size(),
				tableData.getPrimEntityName(), tableData.getSecEntityName());
		// Get the request class for the primary and secondary entities 
		Class<? extends BaseRequest> primEntityRequestClass = 
				getEntityRequestClass(tableData.getPrimEntityName());
		Class<? extends BaseRequest> secEntityRequestClass = 
				getEntityRequestClass(tableData.getSecEntityName());
		//
		DataInputJobSummaryRequest summaryRequest = initializeSummary(inputJobService.findById(tableData.getJobId()), tableData);
		DataInputJobSummary jobSummary = summaryService.create(summaryRequest);
		if(jobSummary == null)
			throw new ApplicationException(COULD_NOT_CREATE_JOB_SUMMARY);
		tableData.setJobSummaryId(jobSummary.getId());
		// Load each individual row
		for(RowData row: tableData.getRows()){
			try {
				tableData = loadTableDataRow(tableData, fields, row, primEntityRequestClass, secEntityRequestClass);
			} catch (ApplicationException e) {
				logger.error("Error loading entity data: {}", e.getCode());
			}
		}
		return tableData;
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
		if(entityCodeSplitArray.length < 2)
			throw new ApplicationException(INVALID_ENTITY_CODE);
		String entityPackageName = entityCodeSplitArray[0].toLowerCase();
		String entityRequestClassName = entity.getName().concat(ENTITY_REQ_CLASS_SUFFIX);
		String entityRequestPackageName = APP_BASE_PACKAGE_NAME.concat(entityPackageName).concat(ENTITY_REQUEST_PACKAGE_NAME);
		try {
			Class<? extends BaseRequest> entityRequestClass = 
					(Class<? extends BaseRequest>) Class.forName(entityRequestPackageName.concat(entityRequestClassName));
			return entityRequestClass;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	protected void invokeMethodOnEntityRequestInstance(Class<? extends BaseRequest> entityRequestClass, 
			BaseRequest entityRequestInstance, DataField field, String dataType, Object data) throws ApplicationException {
		if(entityRequestClass == null) return;
		// Array of the type of the single parameter
		Class[] parameterType = getParameterType(dataType, field);
		Method method;
		try 
		{
			String fieldSetterMehodNm = getFieldSetterMethodName(field);
			method = entityRequestClass.getDeclaredMethod(fieldSetterMehodNm, parameterType);
			logger.debug("Executing method {} with field setter name {} on class {} " +
					"for field {} with configured data type {} and cell data type {} and data {}", 
					method.getName(), fieldSetterMehodNm, entityRequestClass.getName(), 
					field.getName(), field.getDataFieldType().getName(), dataType, data);
			// Cast the data into the required type and 
			// invoke the method on the instance
			if(dataType.equals(DataFieldTypeService.STRING))
				method.invoke(entityRequestInstance, (String) data);
			else if (dataType.equals(DataFieldTypeService.INTEGER))
				method.invoke(entityRequestInstance, (Integer) data);
			else if (dataType.equals(DataFieldTypeService.DATE))
				method.invoke(entityRequestInstance, (Date) data);
			else if (dataType.equals(DataFieldTypeService.DECIMAL))
				method.invoke(entityRequestInstance, (BigDecimal) data);
			else if (dataType.equals(DataFieldTypeService.RELATIONSHIP))
				method.invoke(entityRequestInstance, (Integer) data);
			
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
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
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		return null;
	}
	

	protected DataInputJobSummaryRequest initializeSummary(DataInputJob inputJob, TableData tableData) throws ApplicationException{
		DataInputJobSummaryRequest dataInputJobSummary = new DataInputJobSummaryRequest();
		PropertyUtils.initializeBaseFields(dataInputJobSummary);
		dataInputJobSummary.setCode(Long.valueOf(new Date().getTime()).toString());
		dataInputJobSummary.setDataInputJobId(inputJob.getId());
		dataInputJobSummary.setDataInputJobText(inputJob.getName());
		dataInputJobSummary.setPrimEntityName(tableData.getPrimEntityName());
		dataInputJobSummary.setSecEntityName(tableData.getSecEntityName());
		dataInputJobSummary.setRecordsRead(0);
		dataInputJobSummary.setRecordsRejected(0);
		dataInputJobSummary.setTotalEntitiesCreated(0);
		dataInputJobSummary.setTotalEntitiesRejected(0);
		dataInputJobSummary.setTxnsMatched(0);
		dataInputJobSummary.setTxnsUnmatched(0);
		dataInputJobSummary.setDataInputJobSummaryStatusId(
				summaryStatusService.findByCode(DataInputJobSummaryStatusService.RUNNING).getId());
		
		return dataInputJobSummary;
	}
	

	/**
	 * @param tableData
	 * @param currentRow
	 * @param secEntityRequestClass 
	 * @param primEntityRequestClass 
	 * @return
	 * @throws ApplicationException
	 */
	protected abstract TableData loadTableDataRow(TableData tableData, Set<DataField> field, 
			RowData currentRow,	Class<? extends BaseRequest> primEntityRequestClass,
			Class<? extends BaseRequest> secEntityRequestClass) throws ApplicationException;
}
