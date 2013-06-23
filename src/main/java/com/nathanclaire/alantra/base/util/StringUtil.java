/**
 * 
 */
package com.nathanclaire.alantra.base.util;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.messaging.model.MessageTypeTag;

/**
 * @author Edward Banfa 
 *
 */
public class StringUtil {

	public static String EMPTY_STRING = "";
	public static String UNDERSCORE = "_";
	private static Logger logger = LoggerFactory.getLogger(StringUtil.class);
	
	public static String capitalizeFirstLetter(String original){
	    if(original.length() == 0)
	        return original;
	    return original.substring(0, 1).toUpperCase() + original.substring(1);
	}
	
	public static boolean isValidString(String string)
	{
		if(string == null) return false;
		if(string.trim().isEmpty()) return false;
		return true;
	}
	
	public static boolean flagToBoolean(char flag)
	{
		if(flag == 'Y' | flag == 'y') return true;
		else return false;
	}
	
	public static String extractRegexGroupFromText(String text, String pattern, int groupNo) {
		logger.debug("Extracting group {} from text {} using pattern {}", groupNo, text, pattern);
		Pattern r = Pattern.compile(pattern);
		// Now create matcher object.
		Matcher m = r.matcher(text);
		String extractedGroup = "";
		if (m.find()) {
			try {
				extractedGroup =  m.group(groupNo);
				logger.debug("Extracted group {} with text {} from text {}", groupNo, extractedGroup, text);
			} catch (IndexOutOfBoundsException e) {
				logger.error(e.getMessage());
			}
		} else {
			logger.debug("Could not extracted group {} from text {}", groupNo, text);
		}
		return extractedGroup;
	}
	
	public static String match(String stringToLookIn, String stringToLookFor, boolean isRegex) 
	{
		logger.debug("Searching for string: {} within string: {}", stringToLookFor, stringToLookIn);
		// - If the message type tag is a regex the apply the regex
		if(isRegex)
		{
			// Create a Pattern object
			Pattern r = Pattern.compile(stringToLookFor);
			Matcher m = r.matcher(stringToLookIn);
			if (m.find()) return stringToLookFor;
			// Else return null
			else return null;
		}
		// - Else we just do a java.lang.String.contains()
		else 
		{
			if(stringToLookIn.equalsIgnoreCase(stringToLookFor) | stringToLookIn.contains(stringToLookFor)) 
				return stringToLookFor;
			// Else return null
			else return null;
		}
	}

	public static BigDecimal toBigDecimal(String matchResult) 
	{
		return null;
	}
}
