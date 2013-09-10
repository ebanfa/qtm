/**
 * 
 */
package com.nathanclaire.alantra.datasource.etl.extraction;

import java.io.FileReader;
import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Stateless;

import au.com.bytecode.opencsv.CSVReader;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.DateUtil;
import com.nathanclaire.alantra.base.util.EntityUtil;
import com.nathanclaire.alantra.base.util.ErrorCodes;
import com.nathanclaire.alantra.base.util.ExceptionUtil;
import com.nathanclaire.alantra.base.util.StringUtil;
import com.nathanclaire.alantra.datasource.annotation.etl.CSVDataExtractor;
import com.nathanclaire.alantra.datasource.etl.util.CellData;
import com.nathanclaire.alantra.datasource.etl.util.RowDataLite;
import com.nathanclaire.alantra.datasource.etl.util.TableData;
import com.nathanclaire.alantra.datasource.model.Data;
import com.nathanclaire.alantra.datasource.model.DataChannel;
import com.nathanclaire.alantra.datasource.model.DataField;
import com.nathanclaire.alantra.datasource.util.TableDataUtil;

/**
 * @author Edward Banfa 
 *
 */
@Stateless
@CSVDataExtractor
public class CSVDataExtractorImpl extends BaseDataExtractor<String> implements DataExtractor {

	private static final String INVALID_URL_STRING_PASSED = "CSVDataExtractorImpl.INVALID_URL_STRING_PASSED";
	

	/**
	 * @param data
	 * @param tableDataToBePopulated
	 * @param dataStructure
	 * @return
	 * @throws ApplicationException
	 */
	protected TableData extractData(DataChannel channel, Data data, TableData tableDataToBePopulated) throws ApplicationException {
		EntityUtil.returnOrThrowIfParamsArrayContainsNull(
				new Object[] {channel, data, tableDataToBePopulated, (data!=null) ? data.getDataStructure(): null});
		try 
		{
			if(!StringUtil.isValidString(channel.getUrl()))
				throw new ApplicationException(INVALID_URL_STRING_PASSED);
			CSVReader reader = new CSVReader(new FileReader(channel.getUrl()));
			List<String[]> extractedData = reader.readAll();
			int recordsRead = processRows(data.getDataStructure(), 
					data.getDataStructure().getDataFields(), tableDataToBePopulated, extractedData);
			tableDataToBePopulated.setRecordsRead(recordsRead);
			reader.close();
		} catch (Exception e) {
			ExceptionUtil.processException(e, ErrorCodes.CSV_DATA_EXTRACTOR_ERROR_CD);
		}
		return tableDataToBePopulated;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.extractors.BaseDataExtractor#processStringDataField(com.nathanclaire.alantra.datasource.model.DataField, java.lang.Object, com.nathanclaire.alantra.datasource.etl.RowDataLite)
	 */
	@Override
	protected CellData processStringDataField(DataField dataField, CellData cellData, String data, RowDataLite currentRow) 
			throws ApplicationException 
	{
		if(StringUtil.isValidString(data))
			cellData.setData(data);
		else
			TableDataUtil.handleCellReadError(StringUtil.EMPTY_STRING, cellData, INVALID_STRING_VALUE, NO_VALUE_PROVIDED);
		return cellData;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.extractors.BaseDataExtractor#processIntegerDataField(com.nathanclaire.alantra.datasource.model.DataField, java.lang.Object, com.nathanclaire.alantra.datasource.etl.RowDataLite)
	 */
	@Override
	protected CellData processIntegerDataField(DataField dataField, CellData cellData, String data, RowDataLite currentRow) 
			throws ApplicationException 
	{
		if(StringUtil.isValidString(data))
			try {
		        cellData.setData(new Integer(data));
			} catch (Exception e) {
				TableDataUtil.handleCellReadError(data, cellData, USR_INVALID_INTEGER_STRING, e.getMessage());
			}
		else
			TableDataUtil.handleCellReadError(StringUtil.EMPTY_STRING, cellData, USR_INVALID_INTEGER_STRING, NO_VALUE_PROVIDED);
		return cellData;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.extractors.BaseDataExtractor#processDecimalDataField(com.nathanclaire.alantra.datasource.model.DataField, java.lang.Object, com.nathanclaire.alantra.datasource.etl.RowDataLite)
	 */
	@Override
	protected CellData processDecimalDataField(DataField dataField, CellData cellData, String data, RowDataLite currentRow) 
			throws ApplicationException 
	{
		// Clean decimal fields
		if(StringUtil.isValidString(data))
		{
			data = StringUtil.cleanDecimalString(data);
			try {
				BigDecimal cellValueBigDecimal = new BigDecimal(data);
		        cellData.setData(cellValueBigDecimal);
			} catch (Exception e) {
				TableDataUtil.handleCellReadError(data, cellData, USR_INVALID_DECIMAL_STRING, e.getMessage());
			}
		}
		else
			TableDataUtil.handleCellReadError(StringUtil.EMPTY_STRING, cellData, USR_INVALID_DECIMAL_STRING, NO_VALUE_PROVIDED);
		return cellData;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.extractors.BaseDataExtractor#processDateDataField(com.nathanclaire.alantra.datasource.model.DataField, java.lang.Object, com.nathanclaire.alantra.datasource.etl.RowDataLite)
	 */
	@Override
	protected CellData processDateDataField(DataField dataField, CellData cellData, String data, RowDataLite currentRow) 
			throws ApplicationException 
	{
		if(StringUtil.isValidString(data)) 
			cellData.setData(DateUtil.parseDateString(dataField.getFieldFormat(), data));
		else 
        	TableDataUtil.handleCellReadError(StringUtil.EMPTY_STRING, cellData, INVALID_DATE_VALUE, INVALID_CELL_DATA_TYPE);
		return cellData;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.extractors.BaseDataExtractor#processRelationshipDataField(com.nathanclaire.alantra.datasource.model.DataField, java.lang.Object, com.nathanclaire.alantra.datasource.etl.RowDataLite)
	 */
	@Override
	protected CellData processRelationshipDataField(DataField dataField, CellData cellData, String data, RowDataLite currentRow) 
			throws ApplicationException 
	{
			if(StringUtil.isValidString(data))
				cellData.setData(data);
			else
				TableDataUtil.handleCellReadError(StringUtil.EMPTY_STRING, cellData, INVALID_STRING_VALUE, NO_VALUE_PROVIDED);
		return cellData;
	}
}
