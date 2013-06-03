/**
 * 
 */
package com.nathanclaire.alantra.datasource.etl;

/**
 * @author Edward Banfa 
 *
 */
public class CellDataImpl implements CellData {

	private String name;
	private String data;
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.CellData#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.CellData#setName(java.lang.String)
	 */
	@Override
	public void setName(String name) {
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.CellData#setData(java.lang.String)
	 */
	@Override
	public void setData(String data) {
		this.data = data;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.CellData#getData()
	 */
	@Override
	public String getData() {
		return data;
	}

}
