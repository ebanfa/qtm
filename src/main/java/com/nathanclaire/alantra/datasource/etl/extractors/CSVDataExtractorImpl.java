/**
 * 
 */
package com.nathanclaire.alantra.datasource.etl.extractors;

import java.io.FileReader;
import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Stateless;

import au.com.bytecode.opencsv.CSVReader;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.StringUtil;
import com.nathanclaire.alantra.datasource.etl.CellDataLite;
import com.nathanclaire.alantra.datasource.etl.RowDataLite;
import com.nathanclaire.alantra.datasource.etl.TableDataLite;
import com.nathanclaire.alantra.datasource.model.Data;
import com.nathanclaire.alantra.datasource.model.DataField;
import com.nathanclaire.alantra.datasource.model.DataStructure;
import com.nathanclaire.alantra.datasource.service.entity.DataFieldTypeService;

/**
 * @author Edward Banfa 
 *
 */
@Stateless
public class CSVDataExtractorImpl extends BaseDataExtractor<String> implements CSVDataExtractor {

	private static final String INVALID_URL_STRING_PASSED = "CSVDataExtractorImpl.INVALID_URL_STRING_PASSED";
	

	/**
	 * @param data
	 * @param tableDataToBePopulated
	 * @param dataStructure
	 * @return
	 * @throws ApplicationException
	 */
	protected TableDataLite extractData(Data data, TableDataLite tableDataToBePopulated) throws ApplicationException {
		DataStructure dataStructure = data.getDataStructure();
		try 
		{
			if(!StringUtil.isValidString(data.getDataChannel().getUrl()))
				throw new ApplicationException(INVALID_URL_STRING_PASSED);
			CSVReader reader = new CSVReader(new FileReader(data.getDataChannel().getUrl()));
			List<String[]> extractedData = reader.readAll();
			tableDataToBePopulated.setSourceChannelText(getDataChannelCategory(data).getCode());
			int recordsRead = processRows(dataStructure, dataStructure.getDataFields(), tableDataToBePopulated, extractedData);
			tableDataToBePopulated.setRecordsRead(recordsRead);
			reader.close();
		} catch (Exception e) {
			throw new ApplicationException(USR_DATA_EXTRACTION_ERROR, e.getMessage());
		}
		return tableDataToBePopulated;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.extractors.BaseDataExtractor#processStringDataField(com.nathanclaire.alantra.datasource.model.DataField, java.lang.Object, com.nathanclaire.alantra.datasource.etl.RowDataLite)
	 */
	@Override
	protected CellDataLite processStringDataField(DataField dataField, String data, RowDataLite currentRow) 
			throws ApplicationException 
	{
		String cellDataType = dataField.getDataFieldType().getCode();
		CellDataLite cellDataLite = this.initializeCell(dataField.getCode(), cellDataType);
		if(cellDataType.equals(DataFieldTypeService.STRING)) 
		{
			if(StringUtil.isValidString(data))
				cellDataLite.setData(data);
			else
				handleCellReadError(StringUtil.EMPTY_STRING, 
						cellDataLite, INVALID_STRING_VALUE, NO_VALUE_PROVIDED);
		}
		return cellDataLite;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.extractors.BaseDataExtractor#processIntegerDataField(com.nathanclaire.alantra.datasource.model.DataField, java.lang.Object, com.nathanclaire.alantra.datasource.etl.RowDataLite)
	 */
	@Override
	protected CellDataLite processIntegerDataField(DataField dataField, String data, RowDataLite currentRow) 
			throws ApplicationException 
	{
		String cellDataType = dataField.getDataFieldType().getCode();
		CellDataLite cellDataLite = this.initializeCell(dataField.getCode(), cellDataType);
		if(cellDataType.equals(DataFieldTypeService.INTEGER))
		{
			if(StringUtil.isValidString(data))
			{
				try {
			        cellDataLite.setData(new Integer(data));
				} catch (Exception e) {
					handleCellReadError(data, cellDataLite, USR_INVALID_INTEGER_STRING, e.getMessage());
				}
			}
			else
				handleCellReadError(StringUtil.EMPTY_STRING, cellDataLite, USR_INVALID_INTEGER_STRING, NO_VALUE_PROVIDED);
		}
		return cellDataLite;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.extractors.BaseDataExtractor#processDecimalDataField(com.nathanclaire.alantra.datasource.model.DataField, java.lang.Object, com.nathanclaire.alantra.datasource.etl.RowDataLite)
	 */
	@Override
	protected CellDataLite processDecimalDataField(DataField dataField, String data, RowDataLite currentRow) 
			throws ApplicationException 
	{
		String cellDataType = dataField.getDataFieldType().getCode();
		CellDataLite cellDataLite = this.initializeCell(dataField.getCode(), cellDataType);
		// Clean decimal fields
		if(cellDataType.equals(DataFieldTypeService.DECIMAL))
		{
			if(StringUtil.isValidString(data))
			{
				data = cleanDecimal(data);
				try {
					BigDecimal cellValueBigDecimal = new BigDecimal(data);
			        cellDataLite.setData(cellValueBigDecimal);
				} catch (Exception e) {
					handleCellReadError(data, cellDataLite, USR_INVALID_DECIMAL_STRING, e.getMessage());
				}
			}
			else
				handleCellReadError(StringUtil.EMPTY_STRING, cellDataLite, USR_INVALID_DECIMAL_STRING, NO_VALUE_PROVIDED);
		}
		return cellDataLite;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.extractors.BaseDataExtractor#processDateDataField(com.nathanclaire.alantra.datasource.model.DataField, java.lang.Object, com.nathanclaire.alantra.datasource.etl.RowDataLite)
	 */
	@Override
	protected CellDataLite processDateDataField(DataField dataField, String data, RowDataLite currentRow) 
			throws ApplicationException 
	{
		String cellDataType = dataField.getDataFieldType().getCode();
		CellDataLite cellDataLite = this.initializeCell(dataField.getCode(), cellDataType);
		if(cellDataType.equals(DataFieldTypeService.DATE))
		{
			if(StringUtil.isValidString(data)) {
				cellDataLite.setData(parseDateString(dataField.getFieldFormat(), data));
			}
			else 
	        	handleCellReadError(StringUtil.EMPTY_STRING, 
	        			cellDataLite, INVALID_DATE_VALUE, INVALID_CELL_DATA_TYPE);
		}
		return cellDataLite;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.extractors.BaseDataExtractor#processRelationshipDataField(com.nathanclaire.alantra.datasource.model.DataField, java.lang.Object, com.nathanclaire.alantra.datasource.etl.RowDataLite)
	 */
	@Override
	protected CellDataLite processRelationshipDataField(DataField dataField, String data, RowDataLite currentRow) 
			throws ApplicationException 
	{
		String cellDataType = dataField.getDataFieldType().getCode();
		CellDataLite cellDataLite = this.initializeCell(dataField.getCode(), cellDataType);
		if(cellDataType.equals(DataFieldTypeService.RELATIONSHIP))
		{
			if(StringUtil.isValidString(data))
				cellDataLite.setData(data);
			else
				handleCellReadError(StringUtil.EMPTY_STRING, 
						cellDataLite, INVALID_STRING_VALUE, NO_VALUE_PROVIDED);
		}
		return cellDataLite;
	}
}
