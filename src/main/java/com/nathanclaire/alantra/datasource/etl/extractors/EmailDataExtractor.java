/**
 * 
 */
package com.nathanclaire.alantra.datasource.etl.extractors;

import com.nathanclaire.alantra.datasource.etl.DataExtractor;

/**
 * @author Edward Banfa 
 *
 */
public interface EmailDataExtractor extends DataExtractor {
	
	public static final String UNKNOWN_ERROR_WHILE_FETCHING_EMAILS = "EmailDataExtractor.UNKNOWN_ERROR_WHILE_FETCHING_EMAILS";

}
