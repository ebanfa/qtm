/**
 * 
 */
package com.nathanclaire.alantra.application.rest;

import java.util.ArrayList;
import java.util.List;

import com.nathanclaire.alantra.application.model.ApplicationActivity;
import com.nathanclaire.alantra.base.util.RelatedActivityResponse;
import com.nathanclaire.alantra.businessobject.data.BusinessObjectData;

/**
 * Encapsulates the data of an {@link ApplicationActivity}.
 * This is response return to activity request from external clients (eg: The UI)
 * 
 * @author Edward Banfa
 *
 */
public class ActivityResponse {
	
	public static final String LIST_ACTIVITY_TY = "LIST";
	public static final String VIEW_ACTIVITY_TY = "VIEW";
	public static final String EDIT_ACTIVITY_TY = "EDIT";
	
	private String activityURL;
	private String activityName;
	private String activityTypeName;
	private String businessObjectName;
	
	private String errorMessage;
	// Default error code face (means no errors)
	private Boolean errors = false;
	
	// If the activity is either a view of edit activity
	// then this field will hold the current entity we
	// are view/editing
	private BusinessObjectData businessObjectData;
	
	// If the current activity is a list activity,
	// then this field will hold the list of business objects
	private List<BusinessObjectData> listOfBusinessObjectData = new ArrayList<BusinessObjectData>();
	
	// All the activities related to the current activity.
	private List<RelatedActivityResponse> listOfRelatedActivities = new ArrayList<RelatedActivityResponse>();

	/**
	 * @return the activityURL
	 */
	public String getActivityURL() {
		return activityURL;
	}

	/**
	 * @param activityURL the activityURL to set
	 */
	public void setActivityURL(String activityURL) {
		this.activityURL = activityURL;
	}

	/**
	 * @return the activityName
	 */
	public String getActivityName() {
		return activityName;
	}

	/**
	 * @param activityName the activityName to set
	 */
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	/**
	 * @return the activityTypeName
	 */
	public String getActivityTypeName() {
		return activityTypeName;
	}

	/**
	 * @param activityTypeName the activityTypeName to set
	 */
	public void setActivityTypeName(String activityTypeName) {
		this.activityTypeName = activityTypeName;
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
	 * @return the businessObjectData
	 */
	public BusinessObjectData getBusinessObjectData() {
		return businessObjectData;
	}

	/**
	 * @param businessObjectData the businessObjectData to set
	 */
	public void setBusinessObjectData(BusinessObjectData businessObjectData) {
		this.businessObjectData = businessObjectData;
	}

	/**
	 * @return the listOfBusinessObjectData
	 */
	public List<BusinessObjectData> getListOfBusinessObjectData() {
		return listOfBusinessObjectData;
	}

	/**
	 * @param listOfBusinessObjectData the listOfBusinessObjectData to set
	 */
	public void setListOfBusinessObjectData(
			List<BusinessObjectData> listOfBusinessObjectData) {
		this.listOfBusinessObjectData = listOfBusinessObjectData;
	}

	/**
	 * @return the listOfRelatedActivities
	 */
	public List<RelatedActivityResponse> getListOfRelatedActivities() {
		return listOfRelatedActivities;
	}

	/**
	 * @param listOfRelatedActivities the listOfRelatedActivities to set
	 */
	public void setListOfRelatedActivities(
			List<RelatedActivityResponse> listOfRelatedActivities) {
		this.listOfRelatedActivities = listOfRelatedActivities;
	}

	/**
	 * @return the errors
	 */
	public Boolean isErrors() {
		return errors;
	}

	/**
	 * @param errors the errors to set
	 */
	public void setErrors(Boolean errors) {
		this.errors = errors;
	}

}
