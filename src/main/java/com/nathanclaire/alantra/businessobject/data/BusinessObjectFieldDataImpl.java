/**
 * 
 */
package com.nathanclaire.alantra.businessobject.data;

/**
 * @author Edward Banfa
 *
 */
public class BusinessObjectFieldDataImpl  implements BusinessObjectFieldData{
	
	private String fieldName;
	private Object fieldValue;
	private String fieldDataType;
	private Integer fieldSequence;
	
	/**
	 * @return the fieldName
	 */
	public String getFieldName() {
		return fieldName;
	}
	/**
	 * @param fieldName the fieldName to set
	 */
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	/**
	 * @return the fieldDataType
	 */
	public String getFieldDataType() {
		return fieldDataType;
	}
	/**
	 * @param fieldDataType the fieldDataType to set
	 */
	public void setFieldDataType(String fieldDataType) {
		this.fieldDataType = fieldDataType;
	}
	/**
	 * @return the fieldValue
	 */
	public Object getFieldValue() {
		return fieldValue;
	}
	/**
	 * @param fieldValue the fieldValue to set
	 */
	public void setFieldValue(Object fieldValue) {
		this.fieldValue = fieldValue;
	}
	/**
	 * @return the fieldSequence
	 */
	public Integer getFieldSequence() {
		return fieldSequence;
	}
	/**
	 * @param fieldSequence the fieldSequence to set
	 */
	public void setFieldSequence(Integer fieldSequence) {
		this.fieldSequence = fieldSequence;
	}

}
