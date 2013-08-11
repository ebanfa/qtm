/**
 * 
 */
package com.nathanclaire.alantra.datasource.etl;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.datasource.model.Data;

/**
 * @author Edward Banfa 
 *
 */
public interface DataExtractor {

	public static final String NO_VALUE_PROVIDED = "DataExtractor.NO_VALUE_PROVIDED";
	public static final String INVALID_DATE_VALUE = "DataExtractor.INVALID_DATE_VALUE";
	public static final String INVALID_STRING_VALUE = "DataExtractor.INVALID_STRING_VALUE";
	public static final String INVALID_CELL_DATA_TYPE = "DataExtractor.INVALID_CELL_DATA_TYPE";
	public static final String USR_INVALID_DECIMAL_STRING = "DataExtractor.USR_INVALID_DECIMAL_STRING";
	public static final String USR_INVALID_INTEGER_STRING = "DataExtractor.USR_INVALID_INTEGER_STRING";
	public static final String USR_INCORRECT_DATE_TYPE_FOR_STRING = "DataExtractor.USR_INCORRECT_DATE_TYPE_FOR_STRING";
	
	/**
	 * @return
	 */
	public TableDataLite extract(Data data) throws ApplicationException ;

}
