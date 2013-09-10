/**
 * 
 */
package com.nathanclaire.alantra.base.util;

import java.math.BigDecimal;

/**
 * @author Edward Banfa
 *
 */
public class BigDecimalUtil {
	
	public static BigDecimal toBigDecimal(Object value) throws ApplicationException
	{
		if(value instanceof BigDecimal)
			return (BigDecimal) value;
		if(value instanceof String)
			return new BigDecimal((String) value);
		if(value instanceof Integer)
			return new BigDecimal(((Integer) value));
		if(value instanceof Float)
			return new BigDecimal(((Float) value));
		if(value instanceof Double)
			return new BigDecimal(((Double) value));
		else
			throw new ApplicationException(
					ErrorCodes.BDU_BIG_DECIMAL_CONVERSION_ERROR, 
					ErrorCodes.BDU_INVALID_SOURCE_DATA_TY_ERROR_MSG);
	}
	
	/**
	 * @param firstValue
	 * @param secondValue
	 * @return
	 */
	public static Boolean isLessThan(BigDecimal firstValue, BigDecimal secondValue)
	{
		return (firstValue.compareTo(secondValue) < 0) ?  true : false;
	}
	
	/**
	 * @param firstValue
	 * @param secondValue
	 * @return
	 */
	public static Boolean isEqualsTo(BigDecimal firstValue, BigDecimal secondValue)
	{
		return (firstValue.compareTo(secondValue) == 0) ?  true : false;
	}
	
	/**
	 * @param firstValue
	 * @param secondValue
	 * @return
	 */
	public static Boolean isGreaterThan(BigDecimal firstValue, BigDecimal secondValue)
	{
		return (firstValue.compareTo(secondValue) > 0) ?  true : false;
	}

}
