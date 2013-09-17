/**
 * 
 */
package com.nathanclaire.alantra.businessobject.data;

import java.util.HashMap;
import java.util.Map;


/**
 * Contains all the information needed to 
 * search for {@link BusinessObjectData}
 * 
 * @author Edward Banfa
 *
 */
public class SearchData {
	
	private String businesObjectName;
	private Map<String, SearchFieldData> searchFields = 
			new HashMap<String, SearchFieldData>();

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
	public Map<String, SearchFieldData> getSearchFields() {
		return searchFields;
	}

	/**
	 * @param searchFields the searchFields to set
	 */
	public void setSearchFields(Map<String, SearchFieldData> searchFields) {
		this.searchFields = searchFields;
	}

}
