/**
 * 
 */
package com.nathanclaire.alantra.businessobject.data;

/**
 * @author Edward Banfa
 *
 */
public interface BusinessObjectFieldData {
	
	/**
	 * @return the fieldName
	 */
	public String getFieldName() ;
	/**
	 * @param fieldName the fieldName to set
	 */
	public void setFieldName(String fieldName);
	/**
	 * @return the fieldValue
	 */
	public Object getFieldValue();
	
	/**
	 * @param fieldValue the fieldValue to set
	 */
	public void setFieldValue(Object fieldValue) ;
	
	/**
	 * @return the fieldDataType
	 */
	public String getFieldDataType() ;
	
	/**
	 * @param fieldDataType the fieldDataType to set
	 */
	public void setFieldDataType(String fieldDataType) ;
	
	/**
	 * @return the fieldSequence
	 */
	public Integer getFieldSequence() ;
	/**
	 * @param fieldSequence the fieldSequence to set
	 */
	public void setFieldSequence(Integer fieldSequence);

}
