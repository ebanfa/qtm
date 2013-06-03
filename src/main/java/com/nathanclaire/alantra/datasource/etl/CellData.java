/**
 * 
 */
package com.nathanclaire.alantra.datasource.etl;


/**
 * @author Edward Banfa 
 *
 */
public interface CellData {
	
	/**
	 * @return
	 */
	public String getName();
	/**
	 * @param name
	 */
	public void setName(String name);

	/**
	 * @param data
	 */
	public void setData(String data);
	
	/**
	 * @return
	 */
	public String getData();

}
