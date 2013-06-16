/**
 * 
 */
package com.nathanclaire.alantra.datasource.etl.extractors;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
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

	private Logger logger = LoggerFactory.getLogger(ExcelDataExtractorImpl.class);

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.extractors.BaseDataExtractor#extractData(com.nathanclaire.alantra.datasource.model.Data, com.nathanclaire.alantra.datasource.etl.TableData)
	 */
	@Override
	protected TableData extractData(Data data, TableData tableDataToBePopulated) throws ApplicationException {

		DataStructure dataStructure = data.getDataStructure();
		try {
			// Use a file
			//Workbook wb = WorkbookFactory.create(new File(data.getDataChannel().getUrl()));
			//Sheet sheet = wb.getSheetAt(0);
			List<Cell[]> rowsImpl = new ArrayList<Cell[]>();
			
			POIFSFileSystem fileSystem = new POIFSFileSystem (new FileInputStream(data.getDataChannel().getUrl()));
			HSSFWorkbook workBook = new HSSFWorkbook (fileSystem);
			//Get first sheet from the workbook
			HSSFSheet sheet = workBook.getSheetAt(0);
			/*//Iterate through each rows from first sheet
			Iterator<Row> rowIterator = sheet.iterator();
			//Iterate through each rows from first sheet
			Iterator<Row> rowIterator = sheet.iterator();
			while(rowIterator.hasNext()) {
				Row row = rowIterator.next();
				System.out.println ("Row No.: " + row.getRowNum ());
			}*/
		    for (Row row : sheet) {
		    	List<Cell> cells = PropertyUtils.copyIterator(row.cellIterator());
		    	Cell[] cellsAsArray = cells.toArray(new Cell[cells.size()]);
		    	rowsImpl.add(cellsAsArray);
		    }
		    logger.info("Processing {} excel rows", sheet.getPhysicalNumberOfRows() );
			this.processRows(dataStructure, dataStructure.getDataFields(), tableDataToBePopulated, rowsImpl);
		}  catch (IOException e) {
			e.printStackTrace();
		}
		return tableDataToBePopulated;
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
		        cellData.setData(cell.getRichStringCellValue().getString());
	        	System.out.println(">>>>>>>>cell.getRichStringCellValue():" + cell.getRichStringCellValue().getString());
		        break;
		    case Cell.CELL_TYPE_NUMERIC:
		        if (DateUtil.isCellDateFormatted(cell)) {
		        	cellData.setData(cell.getDateCellValue());
		        	System.out.println(">>>>>>>>cell.getDateCellValue():" + cell.getDateCellValue());
		        } else {
		        	cellData.setData(cell.getNumericCellValue());
		        	System.out.println(">>>>>>>>cell.getNumericCellValue():" + cell.getNumericCellValue());
		        }
		        break;
		    case Cell.CELL_TYPE_BOOLEAN:
	        	cellData.setData(cell.getBooleanCellValue());
		        break;
		    case Cell.CELL_TYPE_FORMULA:
		        break;
		    default:
		        break;
		}
		currentRow.getColumns().add(cellData);
	}
	
}
