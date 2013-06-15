/**
 * 
 */
package com.nathanclaire.alantra.datasource.etl.extractors;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import javax.ejb.Stateless;

import au.com.bytecode.opencsv.CSVReader;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.datasource.etl.CellData;
import com.nathanclaire.alantra.datasource.etl.RowData;
import com.nathanclaire.alantra.datasource.etl.TableData;
import com.nathanclaire.alantra.datasource.model.Data;
import com.nathanclaire.alantra.datasource.model.DataStructure;
import com.nathanclaire.alantra.datasource.service.entity.DataFieldTypeService;

/**
 * @author Edward Banfa 
 *
 */
@Stateless
public class CSVDataExtractorImpl extends BaseDataExtractor<String> implements CSVDataExtractor {

	private static final String CSV_FILE_NOT_FOUND = "CSVDataExtractorImpl.CSV_FILE_NOT_FOUND";
	private static final String INPUT_OUTPUT_ERROR = "CSVDataExtractorImpl.INPUT_OUTPUT_ERROR";

	/**
	 * @param data
	 * @param tableDataToBePopulated
	 * @param dataStructure
	 * @return
	 * @throws ApplicationException
	 */
	protected TableData extractData(Data data, TableData tableDataToBePopulated) throws ApplicationException {
		DataStructure dataStructure = data.getDataStructure();
		try 
		{
			CSVReader reader = new CSVReader(new FileReader(data.getDataChannel().getUrl()));
			List<String[]> extractedData = reader.readAll();
			tableDataToBePopulated.setSourceChannelText(getDataChannelCategory(data).getCode());
			int recordsRead = processRows(dataStructure, dataStructure.getDataFields(), tableDataToBePopulated, extractedData);
			tableDataToBePopulated.setRecordsRead(recordsRead);
			reader.close();
		} catch (FileNotFoundException e) {
			throw new ApplicationException(CSV_FILE_NOT_FOUND, e.getMessage());
		} catch (IOException e) {
			throw new ApplicationException(INPUT_OUTPUT_ERROR, e.getMessage());
		}
		return tableDataToBePopulated;
	}
	
	/**
	 * @param columns
	 * @param rowData
	 * @param i
	 */
	protected void getCellData(String cellName, String dataType, String data, RowData rowData) {
		CellData cellData = new CellData();
		cellData.setName(cellName);
		cellData.setDataType(dataType);
		// Clean decimal fields
		if(dataType.equals(DataFieldTypeService.DECIMAL))
			data = cleanDecimal(data);
		cellData.setData((String) data);
		rowData.getColumns().add(cellData);
	}
}
