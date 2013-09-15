/**
 * 
 */
package com.nathanclaire.alantra.datasource.etl.loading;

import java.util.Set;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.Entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.StringUtil;
import com.nathanclaire.alantra.datasource.annotation.etl.EntityDataLoader;
import com.nathanclaire.alantra.datasource.etl.service.BusinessObjectInputService;
import com.nathanclaire.alantra.datasource.etl.util.CellData;
import com.nathanclaire.alantra.datasource.etl.util.RowData;
import com.nathanclaire.alantra.datasource.etl.util.TableData;
import com.nathanclaire.alantra.datasource.model.DataField;
import com.nathanclaire.alantra.rule.engine.BusinessObjectData;
import com.nathanclaire.alantra.rule.engine.BusinessObjectDataImpl;

/**
 * An implementation of {@link DataLoader} that loads data
 * into JEE {@link Entity}s.
 * 
 * @author Edward Banfa 
 *
 */
@Stateless
@EntityDataLoader
public class EntityDataLoaderImpl extends BaseDataLoader implements DataLoader 
{
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Inject BusinessObjectInputService businessObjectInputService;
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.loaders.BaseDataLoader#loadTableDataRow(com.nathanclaire.alantra.datasource.etl.TableDataLite, com.nathanclaire.alantra.datasource.etl.RowDataLite, java.lang.Class, java.lang.Class)
	 */
	@Override
	protected TableData loadTableDataRow(RowData currentRow, Set<DataField> fields) 
			throws ApplicationException 
	{	
		logger.debug("Loading {}", currentRow);
		// Initialize the primary and the secondary business objects
		BusinessObjectData businessObjectData = 
				new BusinessObjectDataImpl(currentRow.getTableData().getPrimEntityName());
		businessObjectData.setBusinessObjectClassName(currentRow.getTableData().getBusinessObjectClassName());
		// Process the each cell data, basically we figure which business object the cell data
		// belongs to, then we copy its data into the business object field
		for(CellData cellData: currentRow.getColumns())
			processCellData(fields, businessObjectData, cellData);
		logger.debug("Loading {}", businessObjectData);
		businessObjectInputService.loadBusinessObject(businessObjectData);
		
		return currentRow.getTableData();
	}

	/**
	 * Figures out which {@link BusinessObjectData} object a
	 * {@link CellData} belongs to then copies the data into
	 * the field of the {@link BusinessObjectData}.
	 * 
	 * @param fields the set of fields belong to the business objects
	 * @param businessObject the primary business object
	 * @param secondaryBusinessObject the secondary business object
	 * @param cellData the cell data
	 */
	private void processCellData(Set<DataField> fields,	BusinessObjectData businessObject, CellData cellData) 
	{
		logger.debug("Processing {}", cellData);
		for(DataField field : fields)
			if(cellData.getName().equals(field.getCode()))
				cellToBusinessObjectField(businessObject, cellData, field);
	}
	
	/**
	 * Copies the data in a {@link CellData} in a field of a {@link BusinessObjectData}.
	 * 
	 * @param businessObject the business object
	 * @param cellData the cell data
	 * @param field the field that belongs to the business object
	 */
	private void cellToBusinessObjectField(BusinessObjectData businessObject, CellData cellData, DataField field) {
		try {
			if(cellData.isErrors())
				return;
			if(StringUtil.flagToBoolean(field.getRequiredFg()) && cellData.getData() == null)
				throw new ApplicationException(REQ_FIELD_VALUE_NOT_PROVIDED);
			// Set the data value on the business object, with the field name as 
			// the key
			 businessObject.setDataValue(cellData.getBusinessObjectFieldCd(), cellData.getData());
		} catch (Exception e) {
			cellData.setErrors(true);
			cellData.setStatusDescription(ENTITY_FIELD_ERROR);
			cellData.setStatusDescription(e.getMessage());
		}
	}
}
