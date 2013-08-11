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
import com.nathanclaire.alantra.datasource.etl.CellDataLite;
import com.nathanclaire.alantra.datasource.etl.DataTransformerProducer;
import com.nathanclaire.alantra.datasource.etl.RowDataLite;
import com.nathanclaire.alantra.datasource.etl.TableDataLite;
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
	 * @see com.nathanclaire.alantra.datasource.etl.processors.BaseDataProcessor#processTableDataRow(com.nathanclaire.alantra.datasource.etl.RowDataLite, com.nathanclaire.alantra.datasource.etl.TableDataLite, java.util.Set)
	 */
	@Override
	protected TableDataLite processTableDataRow(RowDataLite currentRow, TableDataLite data, Set<DataField> fields) throws ApplicationException {
		for (CellDataLite cellDataLite: currentRow.getColumns())
		{
			// Do not attempt to transform cells with errors
			if(!cellDataLite.isErrors())
			{
				for(DataField field : fields)
				{		
					if(field.getCode().equals(cellDataLite.getName()))
					{
						logger.debug("Transforming cell {} with data type {} and data {}", 
								cellDataLite.getName(), cellDataLite.getDataType(), cellDataLite.getData());
						com.nathanclaire.alantra.datasource.etl.DataTransformer transformer = 
								transformerProducer.getDataTransformer(field);
						if(transformer == null) throw new ApplicationException(DATA_TRANSFORMER_NOT_FOUND);
						try {
							cellDataLite = transformer.transform(cellDataLite, field);
							if(cellDataLite.isErrors())
								currentRow.setErrors(true);
						} catch (ApplicationException e) {
							logger.error("{}. {}", e.getCode(), e.getMessage());
						}
					}
				}
			}
		}
		return data;
	}
}
