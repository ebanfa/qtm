/**
 * 
 */
package com.nathanclaire.alantra.businessobject.util;

import javax.ws.rs.core.MultivaluedMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Edward Banfa
 *
 */
public class BusinessObjectRESTUtil {

	private static Logger logger = LoggerFactory.getLogger(BusinessObjectRESTUtil.class);
	
	public static BusinessObjectSearchInfo mapToBusinessObjectSearchInfo(
			MultivaluedMap<String, String> queryParameters) {
		logger.debug("processing search map : {}", queryParameters);
		for(String entry : queryParameters.keySet()) 
			if(entry.endsWith("_options")){
				String fieldOption = queryParameters.getFirst(entry);
				String fieldName = fieldNameFromOPtionName(entry);
			}
		return new BusinessObjectSearchInfo();
	}

	private static String fieldNameFromOPtionName(String fieldName) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
