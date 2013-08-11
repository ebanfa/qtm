/**
 * 
 */
package com.nathanclaire.alantra.datasource.etl.extractors;

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
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtils;
import com.nathanclaire.alantra.base.util.StringUtil;
import com.nathanclaire.alantra.datasource.etl.CellDataLite;
import com.nathanclaire.alantra.datasource.etl.RowDataLite;
import com.nathanclaire.alantra.datasource.etl.TableDataLite;
import com.nathanclaire.alantra.datasource.model.Data;
import com.nathanclaire.alantra.datasource.model.DataField;
import com.nathanclaire.alantra.datasource.model.DataStructure;

/**
 * @author Edward Banfa 
 *
 */
@Stateless
public class ExcelDataExtractorImpl  extends BaseDataExtractor<Cell> implements ExcelDataExtractor {
	
	private Logger logger = LoggerFactory.getLogger(ExcelDataExtractorImpl.class);

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.extractors.BaseDataExtractor#extractData(com.nathanclaire.alantra.datasource.model.Data, com.nathanclaire.alantra.datasource.etl.TableDataLite)
	 */
	@Override
	protected TableDataLite extractData(Data data, TableDataLite tableDataToBePopulated) throws ApplicationException {

		DataStructure dataStructure = data.getDataStructure();
		try {
			List<Cell[]> rowsImpl = new ArrayList<Cell[]>();
			
			POIFSFileSystem fileSystem = new POIFSFileSystem (new FileInputStream(data.getDataChannel().getUrl()));
			HSSFWorkbook workBook = new HSSFWorkbook (fileSystem);
			//Get first sheet from the workbook
			HSSFSheet sheet = workBook.getSheetAt(0);
		    for (Row row : sheet) {
		    	List<Cell> cells = PropertyUtils.copyIterator(row.cellIterator());
		    	Cell[] cellsAsArray = cells.toArray(new Cell[cells.size()]);
		    	rowsImpl.add(cellsAsArray);
		    }
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
	protected CellDataLite processStringDataField(DataField dataField, Cell cell, RowDataLite currentRow) throws ApplicationException 
	{
		CellDataLite cellDataLite = initializeCell(dataField.getCode(), dataField.getDataFieldType().getCode());
		if(cell.getCellType() == Cell.CELL_TYPE_STRING)
		{
			if(cell.getRichStringCellValue() != null)
				cellDataLite.setData(cell.getRichStringCellValue().getString());
			else
				handleCellReadError(StringUtil.EMPTY_STRING, 
						cellDataLite, INVALID_STRING_VALUE, NO_VALUE_PROVIDED);
		}
		else if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC)
		{
			if (DateUtil.isCellDateFormatted(cell)) {
				if(cell.getDateCellValue() != null)
					cellDataLite.setData(cell.getDateCellValue().toString());
				else
					handleCellReadError(cell.getNumericCellValue(), 
							cellDataLite, INVALID_STRING_VALUE, USR_INCORRECT_DATE_TYPE_FOR_STRING);
			}
			else {
				try {
					cellDataLite.setData(parseNumericString(cell));
				} catch (NumberFormatException e) {
					handleCellReadError(cell.getNumericCellValue(), 
							cellDataLite, USR_INCORRECT_NUMERIC_TYPE_FOR_STRING, e.getMessage());
				}
			}
		} 
		else if(cell.getCellType() == Cell.CELL_TYPE_BLANK)
			handleCellReadError(StringUtil.EMPTY_STRING, 
					cellDataLite, INVALID_STRING_VALUE, NO_VALUE_PROVIDED);
		else
			handleCellReadError(StringUtil.EMPTY_STRING, 
					cellDataLite, INVALID_STRING_VALUE, INVALID_CELL_DATA_TYPE);
		return cellDataLite;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.extractors.BaseDataExtractor#processDecimalDataField(com.nathanclaire.alantra.datasource.model.DataField, java.lang.Object, com.nathanclaire.alantra.datasource.etl.RowDataLite)
	 */
	protected CellDataLite processDecimalDataField(DataField dataField, Cell cell, RowDataLite currentRow) throws ApplicationException {
		CellDataLite cellDataLite = initializeCell(dataField.getCode(), dataField.getDataFieldType().getCode());
		if(cell.getCellType() == Cell.CELL_TYPE_STRING)
		{
			if(cell.getRichStringCellValue() != null)
			{
				String cellValue = cell.getRichStringCellValue().getString();
				try {
					BigDecimal cellValueBigDecimal = new BigDecimal(cellValue);
			        cellDataLite.setData(cellValueBigDecimal);
				} catch (NumberFormatException e) {
					handleCellReadError(cellValue, cellDataLite, USR_INVALID_DECIMAL_STRING, e.getMessage());
				}
				catch (Exception e) {
					handleCellReadError(cellValue, cellDataLite, USR_INVALID_DECIMAL_STRING, e.getMessage());
				}
			}
			else
				handleCellReadError(StringUtil.EMPTY_STRING, cellDataLite, USR_INVALID_DECIMAL_STRING, NO_VALUE_PROVIDED);
		}
		else if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC)
		{
			try {
		        cellDataLite.setData(new BigDecimal(cell.getNumericCellValue()));
			} catch (NumberFormatException e) {
				handleCellReadError(cell.getNumericCellValue(), 
						cellDataLite, USR_INVALID_DECIMAL_STRING, e.getMessage());
			} catch (Exception e) {
				handleCellReadError(cell.getNumericCellValue(), 
						cellDataLite, USR_INVALID_DECIMAL_STRING, e.getMessage());
			}
		}
		else if(cell.getCellType() == Cell.CELL_TYPE_BLANK)
			handleCellReadError(StringUtil.EMPTY_STRING, 
					cellDataLite, USR_INVALID_DECIMAL_STRING, NO_VALUE_PROVIDED);
		else
			handleCellReadError(StringUtil.EMPTY_STRING, 
					cellDataLite, USR_INVALID_DECIMAL_STRING, NO_VALUE_PROVIDED);
		return cellDataLite;
	}


	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.extractors.BaseDataExtractor#processIntegerDataField(com.nathanclaire.alantra.datasource.model.DataField, java.lang.Object, com.nathanclaire.alantra.datasource.etl.RowDataLite)
	 */
	protected CellDataLite processIntegerDataField(DataField dataField, Cell cell, RowDataLite currentRow) throws ApplicationException {
		CellDataLite cellDataLite = initializeCell(dataField.getCode(), dataField.getDataFieldType().getCode());
		if(cell.getCellType() == Cell.CELL_TYPE_STRING)
		{
			if(cell.getRichStringCellValue() != null)
			{
				String cellValue = cell.getRichStringCellValue().getString();
				try {
			        cellDataLite.setData(new Integer(cellValue));
				} catch (NumberFormatException e) {
					handleCellReadError(cellValue, cellDataLite, USR_INVALID_INTEGER_STRING, e.getMessage());
				} catch (Exception e) {
					handleCellReadError(cellValue, cellDataLite, USR_INVALID_INTEGER_STRING, e.getMessage());
				}
			}
			else
				handleCellReadError(StringUtil.EMPTY_STRING, cellDataLite, USR_INVALID_INTEGER_STRING, NO_VALUE_PROVIDED);
		}
		else if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC)
		{
			try {
		        cellDataLite.setData(new BigDecimal(cell.getNumericCellValue()));
			} catch (NumberFormatException e) {
				handleCellReadError(cell.getNumericCellValue(), 
						cellDataLite, USR_INVALID_INTEGER_STRING, e.getMessage());
			} catch (Exception e) {
				handleCellReadError(cell.getNumericCellValue(), 
						cellDataLite, USR_INVALID_INTEGER_STRING, e.getMessage());
			}
		}
		else if(cell.getCellType() == Cell.CELL_TYPE_BLANK)
			handleCellReadError(StringUtil.EMPTY_STRING, 
					cellDataLite, USR_INVALID_INTEGER_STRING, NO_VALUE_PROVIDED);
		else
			handleCellReadError(StringUtil.EMPTY_STRING, 
					cellDataLite, USR_INVALID_INTEGER_STRING, NO_VALUE_PROVIDED);
		return cellDataLite;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.extractors.BaseDataExtractor#processDateDataField(com.nathanclaire.alantra.datasource.model.DataField, java.lang.Object, com.nathanclaire.alantra.datasource.etl.RowDataLite)
	 */
	protected CellDataLite processDateDataField(DataField dataField, Cell cell, RowDataLite currentRow) throws ApplicationException {
		CellDataLite cellDataLite = initializeCell(dataField.getCode(), dataField.getDataFieldType().getCode());
		if(cell.getCellType() == Cell.CELL_TYPE_STRING)
			parseDateString(dataField, cell, cellDataLite);
		else if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC)
		{
	        if (DateUtil.isCellDateFormatted(cell)) 
	        	if(cell.getDateCellValue() != null)
	        		cellDataLite.setData(cell.getDateCellValue());
	        	else
		        	handleCellReadError(StringUtil.EMPTY_STRING, 
		        			cellDataLite, INVALID_DATE_VALUE, NO_VALUE_PROVIDED);
	        else
	        	handleCellReadError(cell.getNumericCellValue(), 
	        			cellDataLite, INVALID_DATE_VALUE, INVALID_CELL_DATA_TYPE);
		}
		else 
        	handleCellReadError(StringUtil.EMPTY_STRING, 
        			cellDataLite, INVALID_DATE_VALUE, INVALID_CELL_DATA_TYPE);
		return cellDataLite;
	}


	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.extractors.BaseDataExtractor#processRelationshipDataField(com.nathanclaire.alantra.datasource.model.DataField, java.lang.Object, com.nathanclaire.alantra.datasource.etl.RowDataLite)
	 */
	protected CellDataLite processRelationshipDataField(DataField dataField, Cell cell, RowDataLite currentRow) throws ApplicationException {
		return this.processStringDataField(dataField, cell, currentRow);
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
	 * @param cellDataLite
	 * @throws ApplicationException 
	 */
	private void parseDateString(DataField dataField, Cell cell, CellDataLite cellDataLite) throws ApplicationException 
	{
		if(cell.getRichStringCellValue() == null) {
			handleCellReadError(StringUtil.EMPTY_STRING, 
					cellDataLite, USR_INVALID_DATE_STRING, NO_VALUE_PROVIDED);
			return;
		}
		String cellValue = cell.getRichStringCellValue().getString();
		try {
			// Convert the string to a date using the default date format
			String dateFormat = dataField.getFieldFormat();
			if(!StringUtil.isValidString(dateFormat))
				dateFormat = DEFAULT_DATE_FORMAT;
			Date date = parseDateString(dateFormat, cellValue); 
		    cellDataLite.setData(date);
		} catch (Exception e) {
			handleCellReadError(cellValue, cellDataLite, USR_INVALID_DATE_STRING, e.getMessage());
		} 
	}

}
