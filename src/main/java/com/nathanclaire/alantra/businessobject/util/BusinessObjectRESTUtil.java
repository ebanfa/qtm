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

	public static final String ENTITY_ID = "id";
	public static final String ENTITY_NAME = "entityName";
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
		SearchData searchData = extractDefaultParameters(queryParameters);
		// Each search field is mapped by its name
		Map<String, SearchFieldData> searchFields = new HashMap<String, SearchFieldData>();
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
		}
		searchData.setSearchFields(searchFields);
		return searchData;
	}

	/**
	 * Extract the default search parameters, form the query
	 * parameters.
	 * 
	 * @param queryParameters the query parameters
	 * @throws ApplicationException if an exception was encountered
	 */
	public static SearchData extractDefaultParameters(MultivaluedMap<String, String> queryParameters)
			throws ApplicationException {
		// Lest first validate the request
		validateQueryParameter(queryParameters);
		SearchData searchData = new SearchData();
		// Loop through the parameters and process
		for(String parameterName : queryParameters.keySet()) 
		{
			if(parameterName.equals(ENTITY_NAME)){
				searchData.setBusinesObjectName(queryParameters.getFirst(ENTITY_NAME));
			}
			if(parameterName.equals(ENTITY_ID)){
				try {
					searchData.setBusinessObjectId((Integer.valueOf(queryParameters.getFirst(ENTITY_ID))));
				} catch (NumberFormatException e) {
					throw new ApplicationException(ErrorCodes.BORU_INVALID_ENTITY_ID_ERROR_CD);
				}
			}
		}
		return searchData;
	}

	/**
	 * Validates the query parameter. This involves just searching
	 * for specific parameters within the provided {@link MultivaluedMap}.
	 * 
	 * @param queryParameters the queryParameters we are validating
	 * @throws ApplicationException if an exception was encountered
	 */
	public static void validateQueryParameter(MultivaluedMap<String, String> queryParameters)
			throws ApplicationException {
		if(!queryParameters.containsKey(ENTITY_NAME))
			throw new ApplicationException(
					ErrorCodes.BORU_SEARCH_FIELD_ERROR_CD, 
					ErrorCodes.BORU_SEARCH_FIELD_ERROR_MSG);
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
