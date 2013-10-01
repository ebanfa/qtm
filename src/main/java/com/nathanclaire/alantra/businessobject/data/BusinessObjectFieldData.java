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
	
	/**
	 * @return the fieldDecription
	 */
	public String getFieldDescription() ;
	
	/**
	 * @param fieldDecription the fieldDecription to set
	 */
	public void setFieldDescription(String fieldDecription);
	
	/**
	 * @return the required
	 */
	public Boolean getRequired() ;
	/**
	 * @param required the required to set
	 */
	public void setRequired(Boolean required);

	/**
	 * @return the relatedBusinessObjectName
	 */
	public String getRelatedBusinessObjectName() ;
	
	/**
	 * @param relatedBusinessObjectName the relatedBusinessObjectName to set
	 */
	public void setRelatedBusinessObjectName(String relatedBusinessObjectName) ;

}
