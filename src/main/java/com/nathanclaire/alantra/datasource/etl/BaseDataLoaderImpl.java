/**
 * 
 */
package com.nathanclaire.alantra.datasource.etl;

import java.util.Set;

import com.nathanclaire.alantra.datasource.model.DataSource;
import com.nathanclaire.alantra.datasource.model.DataSourceField;
import com.nathanclaire.alantra.datasource.model.DataSourceStructure;

import csv.TableReader;

/**
 * @author Edward Banfa 
 *
 */
public abstract class BaseDataLoaderImpl {
	
	private DataSource dataSource;
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.DataLoader#loadData()
	 */
	public TableData loadData(DataSource dataSource) {
		// 1. Ensure we have a valid data source;
		if(dataSource == null) throw new RuntimeException();
		this.dataSource = dataSource;
		return loadDataImpl(getDataStructureFields(dataSource), new TableDataImpl());
	}
	/**
	 * @return
	 */
	private Set<DataSourceField> getDataStructureFields(DataSource dataSource) {
		DataSourceStructure dataSourceStructure = dataSource.getDataSourceStructure();
		// 6. Get data source fields
		Set<DataSourceField> dataSourceFields = dataSourceStructure.getDataSourceFields();
		return dataSourceFields;
	}

	/**
	 * @param columns
	 * @param rowData
	 * @param i
	 */
	protected void getCellData(String name, String data, RowData rowData) {
		CellData cellData = new CellDataImpl();
		cellData.setName(name);
		cellData.setData(data);
		rowData.getColumns().add(cellData);
	}
	
	/**
	 * @param dataStructureFields
	 * @param tableDataImpl
	 * @return
	 */
	protected abstract TableData loadDataImpl(Set<DataSourceField> dataSourceFields, TableData tableData);
	
	/**
	 * @param columns
	 * @return
	 */
	protected abstract RowData getRow(String[] columns);

	/**
	 * @param reader
	 * @return
	 */
	protected abstract String[] getColumns(TableReader reader);
	
	/**
	 * @return the dataSource
	 */
	public DataSource getDataSource() {
		return dataSource;
	}
	/**
	 * @param dataSource the dataSource to set
	 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}


}
