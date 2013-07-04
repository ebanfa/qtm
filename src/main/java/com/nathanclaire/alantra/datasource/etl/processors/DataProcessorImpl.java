/**
 * 
 */
package com.nathanclaire.alantra.datasource.etl.processors;

import java.util.Set;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.datasource.etl.CellData;
import com.nathanclaire.alantra.datasource.etl.RowData;
import com.nathanclaire.alantra.datasource.etl.TableData;
import com.nathanclaire.alantra.datasource.etl.producers.DataTransformerProducer;
import com.nathanclaire.alantra.datasource.model.DataField;

/**
 * @author Edward Banfa 
 *
 */
@Stateless
public class DataProcessorImpl extends BaseDataProcessor {

	@Inject DataTransformerProducer transformerProducer;
	private Logger logger = LoggerFactory.getLogger(DataProcessorImpl.class);
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.processors.BaseDataProcessor#processTableDataRow(com.nathanclaire.alantra.datasource.etl.RowData, com.nathanclaire.alantra.datasource.etl.TableData, java.util.Set)
	 */
	@Override
	protected TableData processTableDataRow(RowData currentRow, TableData data, Set<DataField> fields) throws ApplicationException {
		for (CellData cellData: currentRow.getColumns())
		{
			for(DataField field : fields)
			{		
				if(field.getCode().equals(cellData.getName()))
				{
					logger.debug("Transforming cell {} with data type {} and data {}", cellData.getName(), cellData.getDataType(), cellData.getData());
					com.nathanclaire.alantra.datasource.etl.DataTransformer transformer = 
							transformerProducer.getDataTransformer(field);
					if(transformer == null) throw new ApplicationException(DATA_TRANSFORMER_NOT_FOUND);
					cellData = transformer.transform(cellData, field);
				}
			}
		}
		return data;
	}
}
