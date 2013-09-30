package com.nathanclaire.alantra.businessobject.data;

import java.util.ArrayList;
import java.util.List;


/**
 * Holds {@link BusinessObjectData} information . 
 * 
 * @author Edward Banfa
 *
 */
public class BusinessObjectResponse {

	private Boolean errors;
	private String errorMessage;
	private String businessObjectName;
	private BusinessObjectData data;
	private List<BusinessObjectData> dataList = new ArrayList<BusinessObjectData>();
	private List<BusinessObjectFieldData> dataFields = new ArrayList<BusinessObjectFieldData>();
	
	/**
	 * @return the errors
	 */
	public Boolean getErrors() {
		return errors;
	}
	/**
	 * @param errors the errors to set
	 */
	public void setErrors(Boolean errors) {
		this.errors = errors;
	}
	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}
	/**
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	/**
	 * @return the businessObjectName
	 */
	public String getBusinessObjectName() {
		return businessObjectName;
	}
	/**
	 * @param businessObjectName the businessObjectName to set
	 */
	public void setBusinessObjectName(String businessObjectName) {
		this.businessObjectName = businessObjectName;
	}
	/**
	 * @return the data
	 */
	public BusinessObjectData getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(BusinessObjectData data) {
		this.data = data;
	}
	/**
	 * @return the dataList
	 */
	public List<BusinessObjectData> getDataList() {
		return dataList;
	}
	/**
	 * @param dataList the dataList to set
	 */
	public void setDataList(List<BusinessObjectData> dataList) {
		this.dataList = dataList;
	}
	/**
	 * @return the dataFields
	 */
	public List<BusinessObjectFieldData> getDataFields() {
		return dataFields;
	}
	/**
	 * @param entityListFields the dataFields to set
	 */
	public void setDataFields(List<BusinessObjectFieldData> entityListFields) {
		this.dataFields = entityListFields;
	}
	
	
}
