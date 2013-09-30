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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.application.model.ApplicationEntityField;
import com.nathanclaire.alantra.application.response.ApplicationEntityFieldResponse;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityFieldService;
import com.nathanclaire.alantra.application.service.process.ActivityService;
import com.nathanclaire.alantra.base.rest.AbstractRESTService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.businessobject.data.BusinessObjectData;
import com.nathanclaire.alantra.businessobject.data.BusinessObjectFieldData;
import com.nathanclaire.alantra.businessobject.data.BusinessObjectFieldDataImpl;
import com.nathanclaire.alantra.businessobject.data.BusinessObjectResponse;
import com.nathanclaire.alantra.businessobject.data.SearchData;
import com.nathanclaire.alantra.businessobject.service.process.BusinessObjectSearchService;
import com.nathanclaire.alantra.businessobject.util.BusinessObjectRESTUtil;

/**
 * @author Edward Banfa
 *
 */
@Path("/search")
@Stateless
public class BusinessObjectSearchRESTService extends AbstractRESTService {

	
	@Inject ActivityService activityService;
	@Inject BusinessObjectSearchService businessObjectSearchService;
	@Inject ApplicationEntityFieldService applicationEntityFieldService;
	private Logger logger = LoggerFactory.getLogger(getClass());
	
    /**
     * @param uriInfo
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public BusinessObjectResponse findByCriteria(@Context UriInfo uriInfo) 
    {
    	BusinessObjectResponse businessObjectResponse = new BusinessObjectResponse();
		try {
			// Extract the items from the map
			SearchData searchData = 
					BusinessObjectRESTUtil.mapToBusinessObjectSearchInfo(uriInfo.getQueryParameters());
			// Get the activity list fields
			businessObjectResponse.setBusinessObjectName(searchData.getBusinesObjectName());
			List<BusinessObjectFieldData> entityListFields = 
					activityService.getEntityListFields(searchData.getBusinesObjectName());
			logger.debug("Loaded {} fields for entity {}", 
					entityListFields.size(), searchData.getBusinesObjectName());
			// Do the search
			List<BusinessObjectData> results = businessObjectSearchService.find(searchData, entityListFields);
			businessObjectResponse.setDataFields(entityListFields);
			businessObjectResponse.setDataList(results);
			businessObjectResponse.setErrors(false);
		} 
		catch (ApplicationException e) {
			this.processRESTException(e, businessObjectResponse);
		}
		return businessObjectResponse;
    }

    @GET
    @Path("/single/{id:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    public BusinessObjectResponse findById(@Context UriInfo uriInfo) 
    {
    	BusinessObjectResponse businessObjectResponse = new BusinessObjectResponse();
		try {
			// Extract the items from the map
			SearchData searchData = 
					BusinessObjectRESTUtil.mapToBusinessObjectSearchInfo(uriInfo.getQueryParameters());
			// Get the activity list fields
			businessObjectResponse.setBusinessObjectName(searchData.getBusinesObjectName());
			List<BusinessObjectFieldData> entityListFields = 
					activityService.getEntityListFields(searchData.getBusinesObjectName());
			logger.debug("Loaded {} fields for entity {}", 
					entityListFields.size(), searchData.getBusinesObjectName());
			// Do the search
			BusinessObjectData businessObjectData = businessObjectSearchService.findById(
					searchData.getBusinesObjectName(), searchData.getBusinessObjectId(), entityListFields);
			businessObjectResponse.setDataFields(entityListFields);
			businessObjectResponse.setData(businessObjectData);
			businessObjectResponse.setErrors(false);
		} 
		catch (ApplicationException e) {
			this.processRESTException(e, businessObjectResponse);
		}
		return businessObjectResponse;
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
