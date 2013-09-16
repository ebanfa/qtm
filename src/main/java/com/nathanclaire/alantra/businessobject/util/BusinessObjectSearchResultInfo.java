package com.nathanclaire.alantra.businessobject.util;

import java.util.ArrayList;
import java.util.List;

import com.nathanclaire.alantra.rule.engine.BusinessObjectData;

/**
 * Holds information and search results from {@link BusinessObjectData} 
 * search operation.
 * 
 * @author Edward Banfa
 *
 */
public class BusinessObjectSearchResultInfo {

	private Boolean errors;
	private String errorMessage;
	private String businessObjectName;
	private List<BusinessObjectFieldData> fields = new ArrayList<BusinessObjectFieldData>();
	private List<BusinessObjectData> data = new ArrayList<BusinessObjectData>();
	
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
	 * @return the results
	 */
	public List<BusinessObjectData> getData() {
		return data;
	}
	/**
	 * @param results the results to set
	 */
	public void setData(List<BusinessObjectData> data) {
		this.data = data;
	}
	/**
	 * @return the fields
	 */
	public List<BusinessObjectFieldData> getFields() {
		return fields;
	}
	/**
	 * @param fields the fields to set
	 */
	public void setFields(List<BusinessObjectFieldData> fields) {
		this.fields = fields;
	}
}
