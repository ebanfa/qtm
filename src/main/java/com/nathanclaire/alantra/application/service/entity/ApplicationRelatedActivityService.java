/**
 * 
 */
package com.nathanclaire.alantra.application.service.entity;

import java.util.List;

import com.nathanclaire.alantra.application.model.ApplicationRelatedActivity;
import com.nathanclaire.alantra.application.request.ApplicationRelatedActivityRequest;
import com.nathanclaire.alantra.application.response.ApplicationRelatedActivityResponse;
import com.nathanclaire.alantra.base.service.entity.BaseEntityService;
import com.nathanclaire.alantra.base.util.ApplicationException;

/**
 * @author Edward Banfa
 *
 */
public interface ApplicationRelatedActivityService extends BaseEntityService<ApplicationRelatedActivity, ApplicationRelatedActivityResponse, ApplicationRelatedActivityRequest>
{
	public List<ApplicationRelatedActivity> getRelatedActivities(String parentActivityCode) throws ApplicationException ;
}
