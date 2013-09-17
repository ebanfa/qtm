/**
 * 
 */
package com.nathanclaire.alantra.base.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Edward Banfa 
 *
 */
public class StringUtil {

	public static final String EMPTY_STRING = "";
	public static final String UNDERSCORE = "_";
	public static final String DECIMAL_SYMBOL = ".";
	public static final String EXPONENTIAL_SYMBOL = "E";
	private static Logger logger = LoggerFactory.getLogger(StringUtil.class);
	public static String EMAIL_REGEX = "[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})";

	private static final String randChars = "$#_";
	private static final String numbers = "01234567890";
	private static final String alphabet = 
			"abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	
	public static String capitalizeFirstLetter(String original){
	    if(original.length() == 0)
	        return original;
	    return original.substring(0, 1).toUpperCase() + original.substring(1);
	}
	public static String unCapitalizeFirstLetter(String original){
	    if(original.length() == 0)
	        return original;
	    return original.substring(0, 1).toLowerCase() + original.substring(1);
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
		logger.info("Extracting group {} from text {} using pattern {}", groupNo, text, pattern);
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
		if(!isValidString(stringToLookIn))
			return null;
		if(!isValidString(stringToLookFor))
			return null;
		// - If the message type tag is a regex the apply the regex
		if(isRegex)
		{
			// Create a Pattern object
			Pattern r = Pattern.compile(stringToLookFor);
			Matcher m = r.matcher(stringToLookIn);
			if (m.find()) return m.group();
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
	
	public static String cleanDecimalString(String data) 
	{
		String decimal = data.toString();
		if(decimal.contains(",")) data = decimal.replaceAll(",", "").trim();
		return data;
	}

	/**
	 * @param amountString
	 * @return
	 */
	public static BigDecimal toBigDecimal(String amountString) 
	{
		return new BigDecimal(amountString);
	}

	/**
	 * @param stringToLookInside
	 * @param stringToReplace
	 * @param replacementString
	 * @param isRegex
	 * @return
	 */
	public static String findAndReplace(String stringToLookInside, 
			String stringToReplace, String replacementString, boolean isRegex) 
	{
		return stringToLookInside.replaceAll(stringToReplace, replacementString);
	}

	public static String extractEmailFromText(String text) 
	{
		String email = null;
		Pattern pattern = Pattern.compile(EMAIL_REGEX);
		Matcher matcher = pattern.matcher(text);
		while (matcher.find()) {
			email = matcher.group();
			break;
		}
		return email;
		
	}

	public static List<String> parseCommaSeparatedString(String text) {
		List<String> tokens = new ArrayList<String>();
		for(String token :text.split("\\,"))
			tokens.add(token);
		return tokens;
	}
	
	public static String[] split(String value, char delim) {
        final int end = value.length();
        final List<String> res = new ArrayList<String>();

        int start = 0;
        for (int i = 0; i < end; i ++) {
            if (value.charAt(i) == delim) {
                if (start == i) {
                    res.add(EMPTY_STRING);
                } else {
                    res.add(value.substring(start, i));
                }
                start = i + 1;
            }
        }

        if (start == 0) { // If no delimiter was found in the value
            res.add(value);
        } else {
            if (start != end) {
                // Add the last element if it's not empty.
                res.add(value.substring(start, end));
            } else {
                // Truncate trailing empty elements.
                for (int i = res.size() - 1; i >= 0; i --) {
                    if (res.get(i).length() == 0) {
                        res.remove(i);
                    } else {
                        break;
                    }
                }
            }
        }

        return res.toArray(new String[res.size()]);
    }
	
	public static String getRandomString(int len) {
		char[] choices = ( alphabet + numbers + randChars).toCharArray();
		Random random = new Random();
	    StringBuilder salt = new StringBuilder(len);
	    for (int i = 0; i<len; ++i)
	      salt.append(choices[random.nextInt(choices.length)]);
	    return salt.toString();
	  }
}
