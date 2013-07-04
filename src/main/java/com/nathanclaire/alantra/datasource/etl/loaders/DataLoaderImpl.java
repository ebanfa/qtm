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
import com.nathanclaire.alantra.datasource.etl.CellData;
import com.nathanclaire.alantra.datasource.etl.DataLoader;
import com.nathanclaire.alantra.datasource.etl.RowData;
import com.nathanclaire.alantra.datasource.etl.TableData;
import com.nathanclaire.alantra.datasource.etl.producers.EntityDataInputServiceProducer;
import com.nathanclaire.alantra.datasource.model.DataField;
import com.nathanclaire.alantra.datasource.service.process.DataInputJobRunnerImpl;

/**
 * @author Edward Banfa 
 *
 */
@Stateless
public class DataLoaderImpl extends BaseDataLoader implements DataLoader {

	@Inject EntityDataInputServiceProducer entityDataInputServiceProducer;
	private Logger logger = LoggerFactory.getLogger(DataLoaderImpl.class);
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.loaders.BaseDataLoader#loadTableDataRow(com.nathanclaire.alantra.datasource.etl.TableData, com.nathanclaire.alantra.datasource.etl.RowData, java.lang.Class, java.lang.Class)
	 */
	@Override
	protected TableData loadTableDataRow(TableData tableData, Set<DataField> fields, RowData currentRow, 
			Class<? extends BaseRequest> primEntityRequestClass, Class<? extends BaseRequest> secEntityRequestClass) 
			throws ApplicationException 
	{
		logger.info("Loading table data for {} of {} rows", tableData.getPrimEntityName(), tableData.getRows().size());
		BaseRequest primEntityRequestInstance = getEntityInstance(primEntityRequestClass);
		BaseRequest secEntityRequestInstance = getEntityInstance(secEntityRequestClass);
		
		for(CellData cellData: currentRow.getColumns())
		{
			for(DataField field : fields)
			{
				if(cellData.getName().equals(field.getCode()))
				{
					// Only process this field if it specifies a
					// valid target entity.
					if(StringUtil.isValidString(field.getTargetEntityCd()))
					{
						if(field.getTargetEntityCd().equals(tableData.getPrimEntityName()))
							this.invokeMethodOnEntityRequestInstance(primEntityRequestClass, 
									primEntityRequestInstance, field, cellData.getDataType(), cellData.getData());
						//
						if(field.getTargetEntityCd().equals(tableData.getSecEntityName()))
							this.invokeMethodOnEntityRequestInstance(secEntityRequestClass, 
									secEntityRequestInstance, field, cellData.getDataType(), cellData.getData());
					}
				};
			}
		}
		EntityDataInputService dataInputService = entityDataInputServiceProducer.getEntityDataInputService(tableData.getPrimEntityName());
		if(dataInputService == null)
			throw new ApplicationException(DATA_INPUT_SERVICE_NOT_FOUND);
		dataInputService.processDataInput(primEntityRequestInstance, secEntityRequestInstance, tableData);
		return tableData;
	}
}
