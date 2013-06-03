/**
 * 
 */
package com.nathanclaire.alantra.datasource.etl;

import java.io.FileNotFoundException;
import java.util.Set;

import com.nathanclaire.alantra.datasource.model.DataSource;
import com.nathanclaire.alantra.datasource.model.DataSourceField;

import csv.TableReader;

/**
 * @author Edward Banfa 
 *
 */
public class CSVDataLoader extends BaseDataLoaderImpl implements DataLoader {
	
	/**
	 * @param fieldCount
	 * @param tableData
	 * @param reader
	 * @return
	 * @throws RuntimeException
	 */
	protected TableData loadDataImpl(Set<DataSourceField> dataSourceFields, TableData tableData) {
		int rowsRead = 0;
		TableReader reader = loadCSVFile(this.getDataSource());
		while(reader.hasNext())
		{
			String[] columns = getColumns(reader);
			if(columns.length != dataSourceFields.size()) 
				throw new RuntimeException("Column count does not match field count");
			RowData rowData = getRow(columns);
			tableData.getRows().add(rowData);
			rowsRead ++;
		}
		tableData.setRecordsRead(rowsRead);
		return tableData;
	}
	
	/**
	 * @param dataSource
	 * @param reader
	 * @return
	 * @throws RuntimeException
	 */
	private TableReader loadCSVFile(DataSource dataSource)
			throws RuntimeException {
		TableReader reader = null;
		try 
		{
			reader = new csv.impl.CSVReader(new java.io.File(dataSource.getDsUrl()));
			
		} catch (FileNotFoundException e) 
		{
			throw new RuntimeException("Failed to load data file: " + dataSource.getDsUrl());
		}
		return reader;
	}

	/**
	 * @param columns
	 * @return
	 */
	protected RowData getRow(String[] columns) {
		RowData rowData = new RowDataImpl();
		if(columns == null)throw new RuntimeException("Invalid CSV row provided for loading");
		for(int i = 0; i < columns.length; i++)
			getCellData("", columns[i], rowData);
		return rowData;
	}

	/**
	 * @param reader
	 * @return
	 */
	protected String[] getColumns(TableReader reader) {
		Object[] row = reader.next();
		if(row.length < 1)throw new RuntimeException("Empty row encountered");
		return ((String)row[0]).split(",");
	}

}
