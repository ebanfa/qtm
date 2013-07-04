/**
 * 
 */
package com.nathanclaire.alantra.notification.util;

/**
 * @author Edward Banfa 
 *
 */
public class FilledTemplate {
	private String header;
	private String body;
	
	/**
	 * @param header
	 * @param body
	 */
	public FilledTemplate(String header, String body) {
		this.header = header;
		this.body = body;
	}
	
	public FilledTemplate() 
	{
	}

	/**
	 * @return the header
	 */
	public String getHeader() {
		return header;
	}
	/**
	 * @param header the header to set
	 */
	public void setHeader(String header) {
		this.header = header;
	}
	/**
	 * @return the body
	 */
	public String getBody() {
		return body;
	}
	/**
	 * @param body the body to set
	 */
	public void setBody(String body) {
		this.body = body;
	}

}
