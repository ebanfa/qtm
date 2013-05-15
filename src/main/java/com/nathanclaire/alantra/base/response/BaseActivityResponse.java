/**
 * 
 */
package com.nathanclaire.alantra.base.response;

import java.util.ArrayList;
import java.util.List;

import com.nathanclaire.alantra.application.response.ApplicationEntityFieldResponse;


/**
 * @author Edward Banfa 
 *
 */
public class BaseActivityResponse extends BaseResponse {

	private String activityUrl;
	private List<RelatedActivityResponse> relatedActivities = new ArrayList<RelatedActivityResponse>();
	private List<ApplicationEntityFieldResponse> fields = new ArrayList<ApplicationEntityFieldResponse>();

	/**
	 * @return the relatedActivities
	 */
	public List<RelatedActivityResponse> getRelatedActivities() {
		return relatedActivities;
	}

	/**
	 * @param relatedActivities the relatedActivities to set
	 */
	public void setRelatedActivities(List<RelatedActivityResponse> relatedActivities) {
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

}
