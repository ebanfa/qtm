/**
 * 
 */
package com.nathanclaire.alantra.businessobject.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Edward Banfa
 *
 */
public class BusinessObjectSearchInfo {
	
	private String businesObjectName;
	private Map<String, BusinessObjectFieldSearchInfo> searchFields = 
			new HashMap<String, BusinessObjectFieldSearchInfo>();

	/**
	 * @return the businesObjectName
	 */
	public String getBusinesObjectName() {
		return businesObjectName;
	}

	/**
	 * @param businesObjectName the businesObjectName to set
	 */
	public void setBusinesObjectName(String businesObjectName) {
		this.businesObjectName = businesObjectName;
	}

	/**
	 * @return the searchFields
	 */
	public Map<String, BusinessObjectFieldSearchInfo> getSearchFields() {
		return searchFields;
	}

	/**
	 * @param searchFields the searchFields to set
	 */
	public void setSearchFields(Map<String, BusinessObjectFieldSearchInfo> searchFields) {
		this.searchFields = searchFields;
	}

}
