/**
 * 
 */
package com.nathanclaire.alantra.base.response;

/**
 * @author Edward Banfa 
 *
 */
public class ListItemResponse {
	
	private Integer id;
	private String name;
	private String code;
	
	/**
	 * 
	 */
	public ListItemResponse() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param id
	 * @param name
	 * @param code
	 */
	public ListItemResponse(Integer id, String name, String code) {
		this.id = id;
		this.name = name;
		this.code = code;
	}
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
