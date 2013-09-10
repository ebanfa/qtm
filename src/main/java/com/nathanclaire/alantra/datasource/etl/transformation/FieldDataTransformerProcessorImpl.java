/**
 * 
 */
package com.nathanclaire.alantra.datasource.etl.transformation;

import java.util.Set;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.ErrorCodes;
import com.nathanclaire.alantra.base.util.ExceptionUtil;
import com.nathanclaire.alantra.datasource.annotation.etl.FieldDataTransformerProcessor;
import com.nathanclaire.alantra.datasource.etl.util.CellData;
import com.nathanclaire.alantra.datasource.etl.util.RowDataLite;
import com.nathanclaire.alantra.datasource.etl.util.TableData;
import com.nathanclaire.alantra.datasource.model.DataField;

/**
 * @author Edward Banfa 
 *
 */
@Stateless
@FieldDataTransformerProcessor
public class FieldDataTransformerProcessorImpl extends BaseDataProcessor  implements DataProcessor{

	@Inject DataTransformerProducer transformerProducer;
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.processors.BaseDataProcessor#processTableDataRow(com.nathanclaire.alantra.datasource.etl.RowDataLite, com.nathanclaire.alantra.datasource.etl.TableDataLite, java.util.Set)
	 */
	@Override
	protected TableData processTableDataRow(RowDataLite currentRow, TableData data, Set<DataField> fields) throws ApplicationException {
		for (CellData cellData: currentRow.getColumns())
			if(!cellData.isErrors())
				cellData = processCellData(currentRow, fields, cellData);
		return data;
	}

	private CellData processCellData(RowDataLite currentRow, Set<DataField> fields, CellData cellData)
			throws ApplicationException 
	{
		for(DataField field : fields)
			if(field.getCode().equals(cellData.getName()))
				cellData = transformCellData(currentRow, cellData, field);
		return cellData;
	}

	private CellData transformCellData(RowDataLite currentRow, CellData cellData, DataField field) 
			throws ApplicationException 
	{
		logger.debug("Transforming cell {} with data type {} and data {}",	
				cellData.getName(), cellData.getDataType(), cellData.getData());
		
		com.nathanclaire.alantra.datasource.etl.transformation.DataTransformer transformer;
		transformer = 	transformerProducer.getDataTransformer(field);
		if(transformer == null) 
			throw new ApplicationException(ErrorCodes.DP_DATA_TRANSFORMER_NOT_FOUND_ERROR_CD);
		try 
		{
			cellData = transformer.transform(cellData, field);
			if(cellData.isErrors())	currentRow.setErrors(true);
		} catch (ApplicationException e) {
			ExceptionUtil.processException(e, ErrorCodes.DP_FIELD_TRANSFORMATION_ERROR_CD);
		}
		return cellData;
	}
}
