/**
 * 
 */
package com.nathanclaire.alantra.base.response;

import java.util.ArrayList;
import java.util.List;

import com.nathanclaire.alantra.application.response.ApplicationEntityFieldResponse;
import com.nathanclaire.alantra.application.response.ApplicationRelatedActivityResponse;


/**
 * @author Edward Banfa 
 *
 */
public class BaseActivityResponse extends BaseResponse {

	private String activityUrl;
	private Integer errorCode;
	private String errorMessage;
	private String displayNm;
	private List<ApplicationRelatedActivityResponse> relatedActivities = new ArrayList<ApplicationRelatedActivityResponse>();
	private List<ApplicationEntityFieldResponse> fields = new ArrayList<ApplicationEntityFieldResponse>();

	/**
	 * @return the relatedActivities
	 */
	public List<ApplicationRelatedActivityResponse> getRelatedActivities() {
		return relatedActivities;
	}

	/**
	 * @param relatedActivities the relatedActivities to set
	 */
	public void setRelatedActivities(List<ApplicationRelatedActivityResponse> relatedActivities) {
		this.relatedActivities = relatedActivities;
	}

	/**
	 * @return the activityUrl
	 */
	public String getActivityUrl() {
		return activityUrl;
	}

	/**
	 * @param activityUrl the activityUrl to set
	 */
	public void setActivityUrl(String activityUrl) {
		this.activityUrl = activityUrl;
	}

	/**
	 * @return the fields
	 */
	public List<ApplicationEntityFieldResponse> getFields() {
		return fields;
	}

	/**
	 * @param fields the fields to set
	 */
	public void setFields(List<ApplicationEntityFieldResponse> fields) {
		this.fields = fields;
	}

	/**
	 * @return the errorCode
	 */
	public Integer getErrorCode() {
		return errorCode;
	}

	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
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
	 * @return the displayNm
	 */
	public String getDisplayNm() {
		return displayNm;
	}

	/**
	 * @param displayNm the displayNm to set
	 */
	public void setDisplayNm(String displayNm) {
		this.displayNm = displayNm;
	}

}
