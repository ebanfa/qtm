/**
 * 
 */
package com.nathanclaire.alantra.datasource.etl.loading;

import java.util.Set;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.request.BaseRequest;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.EntityUtil;
import com.nathanclaire.alantra.base.util.StringUtil;
import com.nathanclaire.alantra.datasource.annotation.etl.EntityDataLoader;
import com.nathanclaire.alantra.datasource.etl.service.EntityDataInputService;
import com.nathanclaire.alantra.datasource.etl.service.EntityDataInputServiceProducer;
import com.nathanclaire.alantra.datasource.etl.util.CellData;
import com.nathanclaire.alantra.datasource.etl.util.RowDataLite;
import com.nathanclaire.alantra.datasource.etl.util.TableData;
import com.nathanclaire.alantra.datasource.model.DataField;

/**
 * @author Edward Banfa 
 *
 */
@Stateless
@EntityDataLoader
public class EntityDataLoaderImpl extends BaseDataLoader implements DataLoader 
{
	@Inject EntityDataInputServiceProducer entityDataInputServiceProducer;
	
	private Logger logger = LoggerFactory.getLogger(EntityDataLoaderImpl.class);
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.loaders.BaseDataLoader#loadTableDataRow(com.nathanclaire.alantra.datasource.etl.TableDataLite, com.nathanclaire.alantra.datasource.etl.RowDataLite, java.lang.Class, java.lang.Class)
	 */
	@Override
	protected TableData loadTableDataRow(TableData tableData, Set<DataField> fields, RowDataLite currentRow, 
			Class<? extends BaseRequest> primEntityRequestClass, Class<? extends BaseRequest> secEntityRequestClass) 
			throws ApplicationException 
	{
		BaseRequest primEntityRequestInstance = EntityUtil.getEntityInstance(primEntityRequestClass);
		BaseRequest secEntityRequestInstance = EntityUtil.getEntityInstance(secEntityRequestClass);
		// Only load the data if the row contains no errors
		if(currentRow.isErrors()) {
			logger.debug("Not loading row with error for entity {}", tableData.getPrimEntityName());
			return tableData;
		}
		logger.debug("Loading table data for {} of {} rows", tableData.getPrimEntityName(), tableData.getRows().size());
		rowToEntity(tableData, fields, currentRow, primEntityRequestClass, 
				secEntityRequestClass, primEntityRequestInstance, secEntityRequestInstance);
		EntityDataInputService dataInputService = 
				entityDataInputServiceProducer.getEntityDataInputService(tableData.getPrimEntityName());
		if(dataInputService != null)
			dataInputService.processDataInput(primEntityRequestInstance, secEntityRequestInstance, tableData);
		else
		{
			tableData.setErrors(true);
			tableData.setStatusText(DATA_LOADING_ERROR);
			tableData.setStatusDescription(DATA_INPUT_SERVICE_NOT_FOUND);
		}
		return tableData;
	}
	/**
	 * @param tableData
	 * @param fields
	 * @param currentRow
	 * @param primEntityRequestClass
	 * @param secEntityRequestClass
	 * @param primEntityRequestInstance
	 * @param secEntityRequestInstance
	 */
	private void rowToEntity(
			TableData tableData, 
			Set<DataField> fields, 
			RowDataLite currentRow,	
			Class<? extends BaseRequest> primEntityRequestClass,  
			Class<? extends BaseRequest> secEntityRequestClass, 
			BaseRequest primEntityRequestInstance, 
			BaseRequest secEntityRequestInstance) throws ApplicationException
	{
		for(CellData cellData: currentRow.getColumns())
		{
			if(!cellData.isErrors()){
				cellToEntityProperty(tableData, fields, primEntityRequestClass, 
						secEntityRequestClass, primEntityRequestInstance, secEntityRequestInstance, cellData);
				if(cellData.isErrors())
					currentRow.setErrors(true);
			}
			else
				currentRow.setErrors(true);
		}
	}
	/**
	 * @param tableData
	 * @param fields
	 * @param primEntityRequestClass
	 * @param secEntityRequestClass
	 * @param primEntityRequestInstance
	 * @param secEntityRequestInstance
	 * @param cellData
	 */
	private void cellToEntityProperty(TableData tableData,
			Set<DataField> fields,
			Class<? extends BaseRequest> primEntityRequestClass,
			Class<? extends BaseRequest> secEntityRequestClass,
			BaseRequest primEntityRequestInstance,
			BaseRequest secEntityRequestInstance, CellData cellData) throws ApplicationException {
		for(DataField field : fields)
		{
			if(!StringUtil.isValidString(field.getTargetEntityCd())){
				cellData.setErrors(true);
				cellData.setStatusDescription(ENTITY_FIELD_ERROR);
				cellData.setStatusDescription(TARGET_ENTITY_FIELD_NOT_SPECIFIED);
				return;
			}
			if(cellData.getName().equals(field.getCode())){
				if(field.getTargetEntityCd().equals(tableData.getPrimEntityName()))
					cellToEntityField(primEntityRequestClass, primEntityRequestInstance, cellData, field);
				if(field.getTargetEntityCd().equals(tableData.getSecEntityName()))
					cellToEntityField(secEntityRequestClass, secEntityRequestInstance, cellData, field);
			}
		}
	}
	/**
	 * @param entityRequestClass
	 * @param entityRequestInstance
	 * @param cellData
	 * @param field
	 */
	private void cellToEntityField(
			Class<? extends BaseRequest> entityRequestClass,
			BaseRequest entityRequestInstance, CellData cellData, DataField field) {
		try {
			if(cellData.isErrors())
				return;
			if(StringUtil.flagToBoolean(field.getRequiredFg()) && cellData.getData() == null)
				throw new ApplicationException(REQ_FIELD_VALUE_NOT_PROVIDED);
			EntityUtil.invokeMethodOnEntityRequestInstance(entityRequestClass, 
					entityRequestInstance, field, cellData.getDataType(), cellData.getData());
		} catch (Exception e) {
			cellData.setErrors(true);
			cellData.setStatusDescription(ENTITY_FIELD_ERROR);
			cellData.setStatusDescription(e.getMessage());
		}
	}
}
