/**
 * 
 */
package com.nathanclaire.alantra.datasource.etl.extractors;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellReference;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtils;
import com.nathanclaire.alantra.datasource.etl.CellData;
import com.nathanclaire.alantra.datasource.etl.RowData;
import com.nathanclaire.alantra.datasource.etl.TableData;
import com.nathanclaire.alantra.datasource.model.Data;
import com.nathanclaire.alantra.datasource.model.DataStructure;

/**
 * @author Edward Banfa 
 *
 */
@Stateless
public class ExcelDataExtractorImpl  extends BaseDataExtractor<Cell> implements ExcelDataExtractor {


	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.extractors.BaseDataExtractor#extractData(com.nathanclaire.alantra.datasource.model.Data, com.nathanclaire.alantra.datasource.etl.TableData)
	 */
	@Override
	protected TableData extractData(Data data, TableData tableDataToBePopulated) throws ApplicationException {

		DataStructure dataStructure = data.getDataStructure();
		try {
			// Use a file
			Workbook wb = WorkbookFactory.create(new File(data.getDataChannel().getUrl()));
			Sheet sheet = wb.getSheetAt(0);
			List<Cell[]> rows = new ArrayList<Cell[]>();
		    for (Row row : sheet) {
		    	List<Cell> cells = PropertyUtils.copyIterator(row.iterator());
		    	Cell[] cellsAsArray = cells.toArray(new Cell[cells.size()]);
		    	rows.add(cellsAsArray);
		    }
			this.processRows(dataStructure, dataStructure.getDataFields(), tableDataToBePopulated, rows);
		}  catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}


	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.extractors.BaseDataExtractor#getCellData(java.lang.String, java.lang.String, java.lang.Object, com.nathanclaire.alantra.datasource.etl.RowData)
	 */
	@Override
	protected void getCellData(String cellName, String cellDataType,	Cell cell, RowData currentRow) 
	{
		CellData cellData = new CellData();
		cellData.setName(cellName);
		cellData.setDataType(cellDataType);
		
		switch (cell.getCellType()) 
		{
			case Cell.CELL_TYPE_STRING:
		        System.out.println(cell.getRichStringCellValue().getString());
		        cellData.setData(cell.getRichStringCellValue().getString());
		        break;
		    case Cell.CELL_TYPE_NUMERIC:
		        if (DateUtil.isCellDateFormatted(cell)) {
		        	cellData.setData(cell.getDateCellValue());
		            System.out.println(cell.getDateCellValue());
		        } else {
		        	cellData.setData(cell.getNumericCellValue());
		            System.out.println(cell.getNumericCellValue());
		        }
		        break;
		    case Cell.CELL_TYPE_BOOLEAN:
	        	cellData.setData(cell.getBooleanCellValue());
		        System.out.println(cell.getBooleanCellValue());
		        break;
		    case Cell.CELL_TYPE_FORMULA:
		        System.out.println(cell.getCellFormula());
		        break;
		    default:
		        System.out.println();
		}
		currentRow.getColumns().add(cellData);
	}
	
}
