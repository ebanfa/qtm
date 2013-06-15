/**
 * 
 */
package com.nathanclaire.alantra.datasource.etl.extractors;

import javax.ejb.Stateless;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.datasource.etl.RowData;
import com.nathanclaire.alantra.datasource.etl.TableData;
import com.nathanclaire.alantra.datasource.model.Data;

/**
 * @author Edward Banfa 
 *
 */
@Stateless
public class ExcelDataExtractorImpl  extends BaseDataExtractor<Object> implements ExcelDataExtractor {


	@Override
	protected TableData extractData(Data data, TableData tableDataToBePopulated) throws ApplicationException {
		return null;
	}


	@Override
	protected void getCellData(String cellName, String cellDataType,	Object cellData, RowData currentRow) {
	}
	
}
