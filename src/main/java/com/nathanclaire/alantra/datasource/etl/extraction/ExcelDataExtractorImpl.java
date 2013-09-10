/**
 * 
 */
package com.nathanclaire.alantra.datasource.etl.extraction;

import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.ErrorCodes;
import com.nathanclaire.alantra.base.util.PropertyUtil;
import com.nathanclaire.alantra.base.util.StringUtil;
import com.nathanclaire.alantra.datasource.annotation.etl.ExcelDataExtractor;
import com.nathanclaire.alantra.datasource.etl.util.CellData;
import com.nathanclaire.alantra.datasource.etl.util.RowDataLite;
import com.nathanclaire.alantra.datasource.etl.util.TableData;
import com.nathanclaire.alantra.datasource.model.Data;
import com.nathanclaire.alantra.datasource.model.DataChannel;
import com.nathanclaire.alantra.datasource.model.DataField;
import com.nathanclaire.alantra.datasource.model.DataStructure;
import com.nathanclaire.alantra.datasource.util.TableDataUtil;

/**
 * @author Edward Banfa 
 *
 */
@Stateless
@ExcelDataExtractor
public class ExcelDataExtractorImpl  extends BaseDataExtractor<Cell> implements DataExtractor {
	
	private Logger logger = LoggerFactory.getLogger(ExcelDataExtractorImpl.class);

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.extractors.BaseDataExtractor#extractData(com.nathanclaire.alantra.datasource.model.Data, com.nathanclaire.alantra.datasource.etl.TableDataLite)
	 */
	@Override
	protected TableData extractData(DataChannel channel, Data data, TableData tableDataToBePopulated) throws ApplicationException {

		try {
			List<Cell[]> rowsImpl = new ArrayList<Cell[]>();
			
			POIFSFileSystem fileSystem = new POIFSFileSystem (new FileInputStream(channel.getUrl()));
			HSSFWorkbook workBook = new HSSFWorkbook (fileSystem);
			//Get first sheet from the workbook
			HSSFSheet sheet = workBook.getSheetAt(0);
		    for (Row row : sheet) {
		    	List<Cell> cells = PropertyUtil.copyIterator(row.cellIterator());
		    	Cell[] cellsAsArray = cells.toArray(new Cell[cells.size()]);
		    	rowsImpl.add(cellsAsArray);
		    }
			DataStructure dataStructure = data.getDataStructure();
		    logger.info("Processing {} excel rows", sheet.getPhysicalNumberOfRows() );
			this.processRows(dataStructure, dataStructure.getDataFields(), tableDataToBePopulated, rowsImpl);
		}  catch (Exception e) {
			throw new ApplicationException(USR_DATA_EXTRACTION_ERROR, e.getMessage());
		}
		return tableDataToBePopulated;
	}

	
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.extractors.BaseDataExtractor#processStringDataField(com.nathanclaire.alantra.datasource.model.DataField, java.lang.Object, com.nathanclaire.alantra.datasource.etl.RowDataLite)
	 */
	protected CellData processStringDataField(DataField dataField, CellData cellData, Cell cell, RowDataLite currentRow) throws ApplicationException 
	{
		if(cell.getCellType() == Cell.CELL_TYPE_STRING)
		{
			if(cell.getRichStringCellValue() != null)
				cellData.setData(cell.getRichStringCellValue().getString());
			else
				TableDataUtil.handleCellReadError(StringUtil.EMPTY_STRING, 
						cellData, INVALID_STRING_VALUE, NO_VALUE_PROVIDED);
		}
		else if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC)
		{
			if (DateUtil.isCellDateFormatted(cell)) {
				if(cell.getDateCellValue() != null)
					cellData.setData(cell.getDateCellValue().toString());
				else
					TableDataUtil.handleCellReadError(cell.getNumericCellValue(), 
							cellData, INVALID_STRING_VALUE, USR_INCORRECT_DATE_TYPE_FOR_STRING);
			}
			else {
				try {
					cellData.setData(parseNumericString(cell));
				} catch (NumberFormatException e) {
					TableDataUtil.handleCellReadError(cell.getNumericCellValue(), 
							cellData, USR_INCORRECT_NUMERIC_TYPE_FOR_STRING, e.getMessage());
				}
			}
		} 
		else if(cell.getCellType() == Cell.CELL_TYPE_BLANK)
			TableDataUtil.handleCellReadError(StringUtil.EMPTY_STRING, cellData, INVALID_STRING_VALUE, NO_VALUE_PROVIDED);
		else
			TableDataUtil.handleCellReadError(StringUtil.EMPTY_STRING, cellData, INVALID_STRING_VALUE, INVALID_CELL_DATA_TYPE);
		return cellData;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.extractors.BaseDataExtractor#processDecimalDataField(com.nathanclaire.alantra.datasource.model.DataField, java.lang.Object, com.nathanclaire.alantra.datasource.etl.RowDataLite)
	 */
	protected CellData processDecimalDataField(DataField dataField, CellData cellData, Cell cell, RowDataLite currentRow) throws ApplicationException {
		if(cell.getCellType() == Cell.CELL_TYPE_STRING)
		{
			if(cell.getRichStringCellValue() != null)
			{
				String cellValue = cell.getRichStringCellValue().getString();
				try {
					BigDecimal cellValueBigDecimal = new BigDecimal(cellValue);
			        cellData.setData(cellValueBigDecimal);
				} catch (NumberFormatException e) {
					TableDataUtil.handleCellReadError(cellValue, cellData, USR_INVALID_DECIMAL_STRING, e.getMessage());
				}
				catch (Exception e) {
					TableDataUtil.handleCellReadError(cellValue, cellData, USR_INVALID_DECIMAL_STRING, e.getMessage());
				}
			}
			else
				TableDataUtil.handleCellReadError(StringUtil.EMPTY_STRING, cellData, USR_INVALID_DECIMAL_STRING, NO_VALUE_PROVIDED);
		}
		else if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC)
		{
			try {
		        cellData.setData(new BigDecimal(cell.getNumericCellValue()));
			} catch (NumberFormatException e) {
				TableDataUtil.handleCellReadError(cell.getNumericCellValue(), cellData, USR_INVALID_DECIMAL_STRING, e.getMessage());
			} catch (Exception e) {
				TableDataUtil.handleCellReadError(cell.getNumericCellValue(), cellData, USR_INVALID_DECIMAL_STRING, e.getMessage());
			}
		}
		else if(cell.getCellType() == Cell.CELL_TYPE_BLANK)
			TableDataUtil.handleCellReadError(StringUtil.EMPTY_STRING, cellData, USR_INVALID_DECIMAL_STRING, NO_VALUE_PROVIDED);
		else
			TableDataUtil.handleCellReadError(StringUtil.EMPTY_STRING, cellData, USR_INVALID_DECIMAL_STRING, NO_VALUE_PROVIDED);
		return cellData;
	}


	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.extractors.BaseDataExtractor#processIntegerDataField(com.nathanclaire.alantra.datasource.model.DataField, java.lang.Object, com.nathanclaire.alantra.datasource.etl.RowDataLite)
	 */
	protected CellData processIntegerDataField(DataField dataField, CellData cellData, Cell cell, RowDataLite currentRow) 
			throws ApplicationException 
	{
		if(cell.getCellType() == Cell.CELL_TYPE_STRING)
		{
			if(cell.getRichStringCellValue() != null)
			{
				String cellValue = cell.getRichStringCellValue().getString();
				try {
			        cellData.setData(new Integer(cellValue));
				} catch (NumberFormatException e) {
					TableDataUtil.handleCellReadError(cellValue, cellData, USR_INVALID_INTEGER_STRING, e.getMessage());
				} catch (Exception e) {
					TableDataUtil.handleCellReadError(cellValue, cellData, USR_INVALID_INTEGER_STRING, e.getMessage());
				}
			}
			else
				TableDataUtil.handleCellReadError(StringUtil.EMPTY_STRING, cellData, USR_INVALID_INTEGER_STRING, NO_VALUE_PROVIDED);
		}
		else if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC)
		{
			try {
		        cellData.setData(new BigDecimal(cell.getNumericCellValue()));
			} catch (NumberFormatException e) {
				TableDataUtil.handleCellReadError(cell.getNumericCellValue(), cellData, USR_INVALID_INTEGER_STRING, e.getMessage());
			} catch (Exception e) {
				TableDataUtil.handleCellReadError(cell.getNumericCellValue(), cellData, USR_INVALID_INTEGER_STRING, e.getMessage());
			}
		}
		else if(cell.getCellType() == Cell.CELL_TYPE_BLANK)
			TableDataUtil.handleCellReadError(StringUtil.EMPTY_STRING, cellData, USR_INVALID_INTEGER_STRING, NO_VALUE_PROVIDED);
		else
			TableDataUtil.handleCellReadError(StringUtil.EMPTY_STRING, cellData, USR_INVALID_INTEGER_STRING, NO_VALUE_PROVIDED);
		return cellData;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.extractors.BaseDataExtractor#processDateDataField(com.nathanclaire.alantra.datasource.model.DataField, java.lang.Object, com.nathanclaire.alantra.datasource.etl.RowDataLite)
	 */
	protected CellData processDateDataField(DataField dataField, CellData cellData, Cell cell, RowDataLite currentRow) 
			throws ApplicationException 
	{
		if(cell.getCellType() == Cell.CELL_TYPE_STRING)
			parseDateString(dataField, cell, cellData);
		else if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC)
		{
	        if (DateUtil.isCellDateFormatted(cell)) 
	        	if(cell.getDateCellValue() != null)
	        		cellData.setData(cell.getDateCellValue());
	        	else
		        	TableDataUtil.handleCellReadError(StringUtil.EMPTY_STRING, cellData, INVALID_DATE_VALUE, NO_VALUE_PROVIDED);
	        else
	        	TableDataUtil.handleCellReadError(cell.getNumericCellValue(), cellData, INVALID_DATE_VALUE, INVALID_CELL_DATA_TYPE);
		}
		else 
        	TableDataUtil.handleCellReadError(StringUtil.EMPTY_STRING, cellData, INVALID_DATE_VALUE, INVALID_CELL_DATA_TYPE);
		return cellData;
	}


	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.extractors.BaseDataExtractor#processRelationshipDataField(com.nathanclaire.alantra.datasource.model.DataField, java.lang.Object, com.nathanclaire.alantra.datasource.etl.RowDataLite)
	 */
	protected CellData processRelationshipDataField(DataField dataField, CellData cellData, Cell cell, RowDataLite currentRow) 
			throws ApplicationException 
	{
		return this.processStringDataField(dataField, cellData, cell, currentRow);
	}
	
	/**
	 * @param cell
	 * @return
	 */
	private String parseNumericString(Cell cell) 
	{
		String parsedValue = null;
		String oldValue = new Double(cell.getNumericCellValue()).toString();
		if(oldValue.contains(StringUtil.EXPONENTIAL_SYMBOL)){
			parsedValue = oldValue.replaceAll("E[0-9]+", StringUtil.EMPTY_STRING);
			if(parsedValue.contains(StringUtil.DECIMAL_SYMBOL)) 
				parsedValue = parsedValue.replace(StringUtil.DECIMAL_SYMBOL, StringUtil.EMPTY_STRING);
		}
		else{
			Integer intValue = new BigDecimal(cell.getNumericCellValue()).intValue();
			parsedValue = intValue.toString();
		}
		return parsedValue;
	}

	/**
	 * @param dataField
	 * @param cell
	 * @param cellData
	 * @throws ApplicationException 
	 */
	private void parseDateString(DataField dataField, Cell cell, CellData cellData) throws ApplicationException 
	{
		if(cell.getRichStringCellValue() == null) {
			TableDataUtil.handleCellReadError(StringUtil.EMPTY_STRING, cellData, ErrorCodes.INVALID_DATE_STRING, NO_VALUE_PROVIDED);
			return;
		}
		String cellValue = cell.getRichStringCellValue().getString();
		try {
			// Convert the string to a date using the default date format
			String dateFormat = dataField.getFieldFormat();
			if(!StringUtil.isValidString(dateFormat))
				dateFormat = com.nathanclaire.alantra.base.util.DateUtil.DEFAULT_DATE_FORMAT;
			Date date = com.nathanclaire.alantra.base.util.DateUtil.parseDateString(dateFormat, cellValue); 
		    cellData.setData(date);
		} catch (Exception e) {
			TableDataUtil.handleCellReadError(cellValue, cellData, ErrorCodes.INVALID_DATE_STRING, e.getMessage());
		} 
	}

}
