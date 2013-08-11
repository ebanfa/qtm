/**
 * 
 */
package com.nathanclaire.alantra.base.util;

import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * @author Edward Banfa 
 *
 */
public class DateUtil {

	private static final String DATE_FORMAT = "dd/MM/yyyy";
	/**
	 * Uses joda time to convert a string to a java util date
	 * @param dateString
	 * @return
	 */
	private static final String INVALID_DATE_STRING_PROVIDED = "DateUtil.INVALID_DATE_STRING_PROVIDED";
	public static Date convertStringToJavaDate(String dateString) throws ApplicationException
	{
		try {
			DateTimeFormatter formatter = DateTimeFormat.forPattern(DATE_FORMAT);
			DateTime dt = formatter.parseDateTime(dateString);
			return dt.toDate();
		} catch (Exception e) {
			throw new ApplicationException(INVALID_DATE_STRING_PROVIDED);
		}
	}
	
	/**
	 * @param dataFormatString
	 * @param dateString
	 * @return
	 */
	public static Date convertStringToJavaDate(String dataFormatString, String dateString)
	{
		DateTimeFormatter formatter = DateTimeFormat.forPattern(dataFormatString);
		DateTime dt = formatter.parseDateTime(dateString);
		return dt.toDate();
	}
	
	public static Long getCurrentTimeInMilliSeconds() {
		return new Date().getTime();
	}

}
