/**
 * 
 */
package com.nathanclaire.alantra.datasource.etl.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.model.BaseEntity;
import com.nathanclaire.alantra.base.request.BaseRequest;
import com.nathanclaire.alantra.base.service.process.BaseProcessService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.datasource.etl.util.TableData;
import com.nathanclaire.alantra.datasource.model.DataChannel;

/**
 * @author Edward Banfa
 *
 */
public abstract class BaseEntityDataInputService extends BaseProcessService {

	private Logger logger = LoggerFactory.getLogger(getClass());
	protected static final String DIS_ENTITY_INSTANCE_EXIST = "BaseDataInputService.DIS_ENTITY_INSTANCE_EXIST";
	protected static final String INVALID_PRIM_ENTITY_SPECIFIED = "BaseDataInputService.INVALID_PRIM_ENTITY_SPECIFIED";
	
	/**
	 * @param primaryEntity
	 * @param secondaryEntity
	 * @param tableData
	 * @return
	 * @throws ApplicationException
	 */
	public BaseEntity processDataInput(BaseRequest primaryEntity, 
			BaseRequest secondaryEntity, TableData tableData) throws ApplicationException
	{
		BaseEntity entityInstance = null;
		try 
		{
			if(primaryEntity == null) throw new ApplicationException(INVALID_PRIM_ENTITY_SPECIFIED);
			DataChannel channel = getDataImportService(tableData.getSourceServiceCode());
			entityInstance = this.doDataInput(channel, primaryEntity, secondaryEntity, tableData);
			if(entityInstance != null) 
				this.flagDataInputAccepted(tableData);
			return entityInstance;
		} catch (Exception e) {
			logger.error("Error processing data input for: " +
					"Primary entity {}, Secondary entity {}. {}", primaryEntity, secondaryEntity, e.getMessage());
			this.flagDataInputRejected(tableData);
		}
		return entityInstance;
	}
	
	protected void flagDataInputAccepted(TableData tableData) throws ApplicationException
	{
		logger.error("Accepting data input for: " +
				"Primary entity {}, Secondary entity {}.", tableData.getPrimEntityName(), tableData.getSecEntityName());
	}
	
	protected void flagDataInputRejected(TableData tableData) throws ApplicationException
	{
		logger.error("Rejecting data input for: " +
				"Primary entity {}, Secondary entity {}.", tableData.getPrimEntityName(), tableData.getSecEntityName());
	}

	public abstract BaseEntity doDataInput(DataChannel channel, BaseRequest primaryEntity, 
			BaseRequest secondaryEntity, TableData tableData) throws ApplicationException;
}
