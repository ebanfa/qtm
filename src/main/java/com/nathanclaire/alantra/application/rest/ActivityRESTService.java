/**
 * 
 */
package com.nathanclaire.alantra.application.rest;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.application.model.ApplicationActivity;
import com.nathanclaire.alantra.application.model.ApplicationEntity;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.application.service.process.ActivityService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.ErrorCodes;
import com.nathanclaire.alantra.base.util.ExceptionUtil;
import com.nathanclaire.alantra.businessobject.data.BusinessObjectData;
import com.nathanclaire.alantra.businessobject.data.BusinessObjectDataImpl;
import com.nathanclaire.alantra.businessobject.data.BusinessObjectFieldData;
import com.nathanclaire.alantra.businessobject.service.process.BusinessObjectSearchService;
import com.nathanclaire.alantra.businessobject.util.BusinessObjectUtil;

/**
 * Fetches an activity definition from the DB
 * 
 * @author Edward Banfa
 *
 */
@Path("/activity")
@Stateless
public class ActivityRESTService {

	@Inject ActivityService activityService;
	@Inject ApplicationEntityService entityService;
	@Inject BusinessObjectSearchService searchService;
	private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * Uses the activity URL parameter to fetch and
     * returns the definition of an {@link ApplicationActivity}
     * 
     * @param uriInfo the request information
     * @return the activity response
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
	public ActivityResponse getActivity(@Context UriInfo uriInfo)
	{
    	ActivityResponse activityResponse = null;
    	try {
			String activityURL = ActivityRESTUtil.getActivityURL(uriInfo.getQueryParameters());
			logger.debug("Loading application activity for activity: {}", activityURL);
			// Find the activity with the provided activity URL
			ApplicationActivity applicationActivity = activityService.findByActivityURL(activityURL);
			logger.debug("Loaded application activity {}", applicationActivity);
			if(applicationActivity == null)
				throw new ApplicationException(
					ErrorCodes.ARU_LOAD_ACTIVITY_ERROR_CD, 
					ErrorCodes.ACTIVITY_WITH_URL_NOT_FOUND_ERROR_MSG);
			// Build the response
			activityResponse = 
					ActivityRESTUtil.applicationActivityToActivityResponse(applicationActivity);
			// If the request contained an entity id then
			// we look for the entity and attach it as the business object
			// to the response
			if(ActivityRESTUtil.requestContainsEntityId(uriInfo.getQueryParameters()))
			{
				Integer entityId = ActivityRESTUtil.getEntityIdFromRequest(uriInfo.getQueryParameters());
				ApplicationEntity entity = applicationActivity.getApplicationEntity();
				if(entity == null)
					throw new ApplicationException(
							ErrorCodes.ARU_LOAD_ACTIVITY_ERROR_CD, 
							ErrorCodes.ACTIVITY_HAS_NO_ENTITY_ERROR_MSG);
				// get the fields for the entity (TODO: Need to differentiate from list/edit/view mode)
				List<BusinessObjectFieldData> entityListFields = 
						activityService.getEntityViewFields(entity.getName());
				BusinessObjectData entityData = 
						searchService.findById(entity.getName(), entityId, entityListFields);
				BusinessObjectUtil.copyDataToBusinessObject(entityData, entity, entityListFields);
				activityResponse.setBusinessObjectData(entityData);
			}
		} catch (ApplicationException e) {
			ExceptionUtil.logException(e);
			activityResponse = ActivityRESTUtil.generateErrorResponse(e, uriInfo.getQueryParameters());
		}
		return activityResponse;
	}
	
}
