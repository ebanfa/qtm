/**
 * 
 */
package com.nathanclaire.alantra.base.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Edward Banfa
 *
 */
public class ExceptionUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(ExceptionUtil.class);
	

	public static void processException(Exception e, String errorCode) throws ApplicationException {
		processException(e, errorCode, e.getMessage());
	}

	public static void logAndProcessException(Exception e, String errorCode) throws ApplicationException {
		logException(e);
		processException(e, errorCode);
	}
	
	public static void processException(Exception e, String errorCode, String errorMessage) throws ApplicationException {
		if(e instanceof ApplicationException)
			throw new ApplicationException(errorCode, e.getMessage());
		else throw new ApplicationException(errorCode, errorMessage);
	}

	public static void logException(Exception e) {
		if(e instanceof ApplicationException)
			logger.error("{}. {}", ((ApplicationException) e).getCode(), ((ApplicationException) e).getMessage());
		else
			logger.error("Generic exception. Cause: {}. Message: {}", e.getCause(), e.getMessage());
	}
}
