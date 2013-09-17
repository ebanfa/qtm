/**
 * 
 */
package com.nathanclaire.alantra.businessobject.util;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.MultivaluedMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.ErrorCodes;
import com.nathanclaire.alantra.base.util.StringUtil;
import com.nathanclaire.alantra.businessobject.data.SearchData;
import com.nathanclaire.alantra.businessobject.data.SearchFieldData;

/**
 * @author Edward Banfa
 *
 */
public class BusinessObjectRESTUtil {

	private static final String ENTITY_NAME = "entityName";
	private static Logger logger = LoggerFactory.getLogger(BusinessObjectRESTUtil.class);
	
	/**
	 * Builds a {@link SearchData} from the query parameters.
	 * 
	 * @param queryParameters
	 * @return
	 * @throws ApplicationException
	 */
	public static SearchData mapToBusinessObjectSearchInfo(MultivaluedMap<String, String> queryParameters) 
			throws ApplicationException 
	{
		logger.debug("Processing search map : {}", queryParameters);
		if(!queryParameters.containsKey(ENTITY_NAME))
			throw new ApplicationException(ErrorCodes.BORU_SEARCH_FIELD_ERROR_CD, ErrorCodes.BORU_SEARCH_FIELD_ERROR_MSG);
		
		// Each search field is mapped by its name
		Map<String, SearchFieldData> searchFields = new HashMap<String, SearchFieldData>();
		SearchData searchData = new SearchData();
		// Loop through the parameters and process
		for(String parameterName : queryParameters.keySet()) 
		{
			if(parameterName.endsWith("_options"))
			{
				String fieldOptionValue = queryParameters.getFirst(parameterName);
				String fieldName = fieldNameFromOptionName(parameterName);
				String fieldValue = cleanSearchFieldValue(queryParameters.getFirst(fieldName));
				// Verify that we have valid field information
				if(StringUtil.isValidString(fieldName) && 
						!StringUtil.isValidString(fieldValue))
					continue;
				SearchFieldData searchFieldData = new SearchFieldData();
				searchFieldData.setFieldName(fieldName);
				searchFieldData.setFieldValue(fieldValue);
				searchFieldData.setFieldSearchOperator(fieldOptionValue);
				searchFields.put(fieldName, searchFieldData);
			}
			if(parameterName.equals(ENTITY_NAME)){
				searchData.setBusinesObjectName(queryParameters.getFirst(ENTITY_NAME));
			}
		}
		searchData.setSearchFields(searchFields);
		return searchData;
	}
	
	/**
	 * Clean up the field value {@link String}.
	 * 
	 * @param value the string to be cleaned
	 * @return the cleaned search string
	 */
	public static String cleanSearchFieldValue(String value)
	{
		if(!StringUtil.isValidString(value))
			return null;
		value = value.trim();
		value = value.replaceAll(";", "");
		value = value.replaceAll("\\,", "");
		value = value.replaceAll("\\.", "");
		value = value.replaceAll(">", "");
		value = value.replaceAll("<", "");
		value = value.replaceAll("=", "");
		value = value.replaceAll("%", "");
		value = value.replaceAll("@", "");
		value = value.replaceAll("\\)", "");
		value = value.replaceAll("\\(", "");
		value = value.replaceAll("\\'", "");
		value = value.replaceAll("\\\"", "");
		value = value.replaceAll("\\]", "");
		value = value.replaceAll("\\[", "");
		value = value.replaceAll("\\}", "");
		value = value.replaceAll("\\{", "");
		value = value.replaceAll("\\|", "");
		value = value.replaceAll("\\\\", "");
		value = value.replaceAll("\\?", "");
		value = value.replaceAll("\\+", "");
		value = value.replaceAll("\\$", "");
		value = value.replaceAll("\\*", "");
		value = value.replaceAll("\\/", "");
		value = value.replaceAll("\\;", "");
		return value;
	}

	/**
	 * @param fieldOptionName
	 * @return
	 */
	private static String fieldNameFromOptionName(String fieldOptionName) 
	{
		Integer startOfOptionsTag = fieldOptionName.indexOf("_options");
		String fieldName = fieldOptionName.substring(0, startOfOptionsTag);
		logger.debug("The field name is : {}", fieldName);
		return fieldName;
	}
	
	
	
}
