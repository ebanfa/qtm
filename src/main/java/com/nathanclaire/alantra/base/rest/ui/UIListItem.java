/**
 * 
 */
package com.nathanclaire.alantra.base.rest.ui;

/**
 * @author Edward Banfa 
 *
 */
public class UIListItem {
	
	private String itemName;
	private String itemCode;
	
	public UIListItem(String code, String name) {
		this.itemCode = code;
		this.itemName = name;
	}
	/**
	 * @return the itemName
	 */
	public String getItemName() {
		return itemName;
	}
	/**
	 * @param itemName the itemName to set
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	/**
	 * @return the itemCode
	 */
	public String getItemCode() {
		return itemCode;
	}
	/**
	 * @param itemCode the itemCode to set
	 */
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

}
