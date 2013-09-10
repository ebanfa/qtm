/**
 * 
 */
package com.nathanclaire.alantra.datasource.etl.util;

/**
 * @author Edward Banfa 
 *
 */
public class CellData {

	private String name;
	private String dataType;
	private Object data;
	private Integer dataFieldId;
	private boolean errors;
	private String statusText;
	private String statusDescription;
	
	
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
	 * @return the dataType
	 */
	public String getDataType() {
		return dataType;
	}
	/**
	 * @param dataType the dataType to set
	 */
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	/**
	 * @return the data
	 */
	public Object getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(Object data) {
		this.data = data;
	}
	/**
	 * @return the dataFieldId
	 */
	public Integer getDataFieldId() {
		return dataFieldId;
	}
	/**
	 * @param dataFieldId the dataFieldId to set
	 */
	public void setDataFieldId(Integer dataFieldId) {
		this.dataFieldId = dataFieldId;
	}
	/**
	 * @return the errors
	 */
	public boolean isErrors() {
		return errors;
	}
	/**
	 * @param errors the errors to set
	 */
	public void setErrors(boolean errors) {
		this.errors = errors;
	}
	/**
	 * @return the statusText
	 */
	public String getStatusText() {
		return statusText;
	}
	/**
	 * @param statusText the statusText to set
	 */
	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}
	/**
	 * @return the statusDescription
	 */
	public String getStatusDescription() {
		return statusDescription;
	}
	/**
	 * @param statusDescription the statusDescription to set
	 */
	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}
}
