/**
 * 
 */
package com.nathanclaire.alantra.base.util;

/**
 * @author Edward Banfa 
 *
 */
public class StringUtil {

	public static String EMPTY_STRING = "";
	public static String UNDERSCORE = "_";
	
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
}
