/**
 * 
 */
package com.nathanclaire.alantra.application.service.entity;

import com.nathanclaire.alantra.application.model.ApplicationActivity;
import com.nathanclaire.alantra.application.request.ApplicationActivityRequest;
import com.nathanclaire.alantra.application.response.ApplicationActivityResponse;
import com.nathanclaire.alantra.base.service.entity.BaseEntityService;
import com.nathanclaire.alantra.base.util.ApplicationException;

/**
 * @author Edward Banfa
 * 
 */
public interface ApplicationActivityService
		extends
		BaseEntityService<ApplicationActivity, ApplicationActivityResponse, ApplicationActivityRequest> {
	
	/**
	 * Find an activity by its URL.
	 * 
	 * @param activityURL the URL of the activity
	 * @return the activity
	 * @throws ApplicationException if an exception is encountered
	 */
	public ApplicationActivity findByActivityURL(String activityURL) throws ApplicationException;

}
