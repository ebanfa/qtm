/**
 * 
 */
package com.nathanclaire.alantra.base.util;

/**
 * @author Edward Banfa 
 *
 */
public class SystemException extends RuntimeException {
	
	private String code;

	/**
	 * @param code
	 */
	public SystemException(String code) {
		this.code = code;
	}
	
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}


}
