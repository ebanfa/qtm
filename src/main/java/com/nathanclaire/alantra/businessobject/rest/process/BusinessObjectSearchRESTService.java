/**
 * 
 */
package com.nathanclaire.alantra.businessobject.rest.process;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import com.nathanclaire.alantra.application.model.ApplicationEntityField;
import com.nathanclaire.alantra.application.response.ApplicationEntityFieldResponse;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityFieldService;
import com.nathanclaire.alantra.application.service.process.ActivityService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.ExceptionUtil;
import com.nathanclaire.alantra.businessobject.service.process.BusinessObjectSearchService;
import com.nathanclaire.alantra.businessobject.util.BusinessObjectFieldData;
import com.nathanclaire.alantra.businessobject.util.BusinessObjectRESTUtil;
import com.nathanclaire.alantra.businessobject.util.BusinessObjectSearchInfo;
import com.nathanclaire.alantra.businessobject.util.BusinessObjectSearchResultInfo;
import com.nathanclaire.alantra.rule.engine.BusinessObjectData;

/**
 * @author Edward Banfa
 *
 */
@Path("/search")
@Stateless
public class BusinessObjectSearchRESTService {

	@Inject ActivityService activityService;
	@Inject BusinessObjectSearchService businessObjectSearchService;
	@Inject ApplicationEntityFieldService applicationEntityFieldService;
	
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public BusinessObjectSearchResultInfo findBusinessObjects(@Context UriInfo uriInfo) 
    {
    	BusinessObjectSearchResultInfo searchResultInfo = new BusinessObjectSearchResultInfo();
		try {
			// Extract the items from the map
			BusinessObjectSearchInfo searchInfo = 
					BusinessObjectRESTUtil.mapToBusinessObjectSearchInfo(uriInfo.getQueryParameters());
			// Get the activity list fields
			searchResultInfo.setBusinessObjectName(searchInfo.getBusinesObjectName());
			List<BusinessObjectFieldData> entityListFields = 
					activityService.getEntityListFields(searchInfo.getBusinesObjectName());
			// Do the search
			List<BusinessObjectData> results = businessObjectSearchService.find(searchInfo);
			searchResultInfo.setFields(entityListFields);
			searchResultInfo.setData(results);
			searchResultInfo.setErrors(false);
		} 
		catch (ApplicationException e) {
			searchResultInfo.setErrors(true);
			searchResultInfo.setErrorMessage(e.getMessage());
			ExceptionUtil.logException(e);
		}
		return searchResultInfo;
    }
    
    @GET
    @Path("/searchFields")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ApplicationEntityFieldResponse> getEntitySearchFields(@QueryParam("entityName") String entityName)
    {
    	List<ApplicationEntityFieldResponse> fieldResponses = new ArrayList<ApplicationEntityFieldResponse>();
    	try {
    		List<ApplicationEntityField> fields = applicationEntityFieldService.getEntitySearchFields(entityName);
    		for(ApplicationEntityField field: fields)
    			fieldResponses.add(applicationEntityFieldService.convertModelToResponse(field));
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
    	return fieldResponses;
    }

}
