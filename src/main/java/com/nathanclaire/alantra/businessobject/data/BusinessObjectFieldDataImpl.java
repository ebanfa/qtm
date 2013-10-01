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
	private Boolean required;
	private Object fieldValue;
	private String fieldDataType;
	private Integer fieldSequence;
	private String fieldDescription;
	private String relatedBusinessObjectName;
	
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
	/**
	 * @return the fieldDecription
	 */
	public String getFieldDescription() {
		return fieldDescription;
	}
	/**
	 * @param fieldDecription the fieldDecription to set
	 */
	public void setFieldDescription(String fieldDecription) {
		this.fieldDescription = fieldDecription;
	}
	
	/**
	 * @return the required
	 */
	public Boolean getRequired() {
		return required;
	}
	/**
	 * @param required the required to set
	 */
	public void setRequired(Boolean required) {
		this.required = required;
	}
	
	/**
	 * @return the relatedBusinessObjectName
	 */
	public String getRelatedBusinessObjectName() {
		return relatedBusinessObjectName;
	}
	/**
	 * @param relatedBusinessObjectName the relatedBusinessObjectName to set
	 */
	public void setRelatedBusinessObjectName(String relatedBusinessObjectName) {
		this.relatedBusinessObjectName = relatedBusinessObjectName;
	}

}
