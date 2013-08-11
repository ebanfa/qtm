/**
 * 
 */
package com.nathanclaire.alantra.datasource.etl.loaders;

import java.util.Set;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.request.BaseRequest;
import com.nathanclaire.alantra.base.service.process.EntityDataInputService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.StringUtil;
import com.nathanclaire.alantra.datasource.etl.CellDataLite;
import com.nathanclaire.alantra.datasource.etl.DataLoader;
import com.nathanclaire.alantra.datasource.etl.EntityDataInputServiceProducer;
import com.nathanclaire.alantra.datasource.etl.RowDataLite;
import com.nathanclaire.alantra.datasource.etl.TableDataLite;
import com.nathanclaire.alantra.datasource.model.DataField;

/**
 * @author Edward Banfa 
 *
 */
@Stateless
public class DataLoaderImpl extends BaseDataLoader implements DataLoader 
{
	@Inject EntityDataInputServiceProducer entityDataInputServiceProducer;
	
	private Logger logger = LoggerFactory.getLogger(DataLoaderImpl.class);
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.loaders.BaseDataLoader#loadTableDataRow(com.nathanclaire.alantra.datasource.etl.TableDataLite, com.nathanclaire.alantra.datasource.etl.RowDataLite, java.lang.Class, java.lang.Class)
	 */
	@Override
	protected TableDataLite loadTableDataRow(TableDataLite tableDataLite, Set<DataField> fields, RowDataLite currentRow, 
			Class<? extends BaseRequest> primEntityRequestClass, Class<? extends BaseRequest> secEntityRequestClass) 
			throws ApplicationException 
	{
		BaseRequest primEntityRequestInstance = getEntityInstance(primEntityRequestClass);
		BaseRequest secEntityRequestInstance = getEntityInstance(secEntityRequestClass);
		// Only load the data if the row contains no errors
		if(currentRow.isErrors()) {
			logger.debug("Not loading row with error for entity {}", tableDataLite.getPrimEntityName());
			return tableDataLite;
		}
		logger.debug("Loading table data for {} of {} rows", tableDataLite.getPrimEntityName(), tableDataLite.getRows().size());
		rowToEntity(tableDataLite, fields, currentRow, primEntityRequestClass, 
				secEntityRequestClass, primEntityRequestInstance, secEntityRequestInstance);
		EntityDataInputService dataInputService = 
				entityDataInputServiceProducer.getEntityDataInputService(tableDataLite.getPrimEntityName());
		if(dataInputService != null)
			dataInputService.processDataInput(primEntityRequestInstance, secEntityRequestInstance, tableDataLite);
		else
		{
			tableDataLite.setErrors(true);
			tableDataLite.setStatusText(DATA_LOADING_ERROR);
			tableDataLite.setStatusDescription(DATA_INPUT_SERVICE_NOT_FOUND);
		}
		return tableDataLite;
	}
	/**
	 * @param tableDataLite
	 * @param fields
	 * @param currentRow
	 * @param primEntityRequestClass
	 * @param secEntityRequestClass
	 * @param primEntityRequestInstance
	 * @param secEntityRequestInstance
	 */
	private void rowToEntity(
			TableDataLite tableDataLite, 
			Set<DataField> fields, 
			RowDataLite currentRow,	
			Class<? extends BaseRequest> primEntityRequestClass,  
			Class<? extends BaseRequest> secEntityRequestClass, 
			BaseRequest primEntityRequestInstance, 
			BaseRequest secEntityRequestInstance) throws ApplicationException
	{
		for(CellDataLite cellDataLite: currentRow.getColumns())
		{
			if(!cellDataLite.isErrors()){
				cellToEntityProperty(tableDataLite, fields, primEntityRequestClass, 
						secEntityRequestClass, primEntityRequestInstance, secEntityRequestInstance, cellDataLite);
				if(cellDataLite.isErrors())
					currentRow.setErrors(true);
			}
			else
				currentRow.setErrors(true);
		}
	}
	/**
	 * @param tableDataLite
	 * @param fields
	 * @param primEntityRequestClass
	 * @param secEntityRequestClass
	 * @param primEntityRequestInstance
	 * @param secEntityRequestInstance
	 * @param cellDataLite
	 */
	private void cellToEntityProperty(TableDataLite tableDataLite,
			Set<DataField> fields,
			Class<? extends BaseRequest> primEntityRequestClass,
			Class<? extends BaseRequest> secEntityRequestClass,
			BaseRequest primEntityRequestInstance,
			BaseRequest secEntityRequestInstance, CellDataLite cellDataLite) throws ApplicationException {
		for(DataField field : fields)
		{
			if(!StringUtil.isValidString(field.getTargetEntityCd())){
				cellDataLite.setErrors(true);
				cellDataLite.setStatusDescription(ENTITY_FIELD_ERROR);
				cellDataLite.setStatusDescription(TARGET_ENTITY_FIELD_NOT_SPECIFIED);
				return;
			}
			if(cellDataLite.getName().equals(field.getCode())){
				if(field.getTargetEntityCd().equals(tableDataLite.getPrimEntityName()))
					cellToEntityField(primEntityRequestClass, primEntityRequestInstance, cellDataLite, field);
				if(field.getTargetEntityCd().equals(tableDataLite.getSecEntityName()))
					cellToEntityField(secEntityRequestClass, secEntityRequestInstance, cellDataLite, field);
			}
		}
	}
	/**
	 * @param entityRequestClass
	 * @param entityRequestInstance
	 * @param cellDataLite
	 * @param field
	 */
	private void cellToEntityField(
			Class<? extends BaseRequest> entityRequestClass,
			BaseRequest entityRequestInstance, CellDataLite cellDataLite, DataField field) {
		try {
			if(cellDataLite.isErrors())
				return;
			if(StringUtil.flagToBoolean(field.getRequiredFg()) && cellDataLite.getData() == null)
				throw new ApplicationException(REQ_FIELD_VALUE_NOT_PROVIDED);
			this.invokeMethodOnEntityRequestInstance(entityRequestClass, 
					entityRequestInstance, field, cellDataLite.getDataType(), cellDataLite.getData());
		} catch (Exception e) {
			cellDataLite.setErrors(true);
			cellDataLite.setStatusDescription(ENTITY_FIELD_ERROR);
			cellDataLite.setStatusDescription(e.getMessage());
		}
	}
}
