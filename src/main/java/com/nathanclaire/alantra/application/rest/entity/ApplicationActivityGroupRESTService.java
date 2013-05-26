/**
 * 
 */
package com.nathanclaire.alantra.application.rest.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.application.model.ApplicationActivityGroup;
import com.nathanclaire.alantra.application.model.ApplicationEntityField;
import com.nathanclaire.alantra.application.request.ApplicationActivityGroupRequest;
import com.nathanclaire.alantra.application.response.ApplicationActivityGroupResponse;
import com.nathanclaire.alantra.application.response.ApplicationActivityResponse;
import com.nathanclaire.alantra.application.response.ApplicationEntityFieldResponse;
import com.nathanclaire.alantra.application.service.entity.ApplicationActivityGroupService;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityFieldService;
import com.nathanclaire.alantra.base.response.EditActivityResponse;
import com.nathanclaire.alantra.base.response.ListActivityResponse;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.rest.BaseActivityRESTService;

/**
 * @author administrator
 *
 */
@Path("/applicationactivitygroup")
@Stateless
public class ApplicationActivityGroupRESTService extends BaseActivityRESTService<ApplicationActivityGroupResponse, ApplicationActivityGroupRequest> 
{
	@Inject
	ApplicationActivityGroupService applicationActivityGroupService;
	@Inject 
	ApplicationEntityFieldService applicationEntityFieldService;
	
	private Logger logger = LoggerFactory.getLogger(ApplicationActivityGroupRESTService.class);

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#populateListActivityResponse(com.nathanclaire.alantra.application.response.ApplicationActivityGroupResponse, com.nathanclaire.alantra.base.response.ListActivityResponse, javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected ListActivityResponse<ApplicationActivityGroupResponse> populateListActivityResponse(
			ApplicationActivityResponse activity,
			ListActivityResponse<ApplicationActivityGroupResponse> response,
			MultivaluedMap<String, String> queryParameters) 
	{
		List<ApplicationEntityFieldResponse> responseFields = new ArrayList<ApplicationEntityFieldResponse>();
		// Load the fields for the ApplicationActivityGroup entity
		List<ApplicationEntityField> entityFields = applicationActivityGroupService.getEntityFields();
		for(ApplicationEntityField entityField:entityFields)
		{
			responseFields.add(applicationEntityFieldService.convertModelToResponse(entityField));
		}
		response.setFields(responseFields);
		// Load the list of ApplicationActivityGroup's
		List<ApplicationActivityGroupResponse> dataItems = new ArrayList<ApplicationActivityGroupResponse>();
		for (ApplicationActivityGroup item:applicationActivityGroupService.findAll(queryParameters))
		{
			dataItems.add(applicationActivityGroupService.convertModelToResponse(item));
		}
		response.setData(dataItems);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getInstance(java.lang.Integer)
	 */
	@Override
	protected EditActivityResponse<ApplicationActivityGroupResponse> populateEditActivityResponse(
			Integer id,	ApplicationActivityResponse activity, EditActivityResponse<ApplicationActivityGroupResponse> response) 
	{
		// Load the fields for the ApplicationActivityGroup entity
		List<ApplicationEntityField> entityFields = applicationActivityGroupService.getEntityFields();
		List<ApplicationEntityFieldResponse> responseFields = new ArrayList<ApplicationEntityFieldResponse>();
		// Convert the entity fields into response fields
		for(ApplicationEntityField entityField:entityFields)
		{
			responseFields.add(applicationEntityFieldService.convertModelToResponse(entityField));
		}
		response.setFields(responseFields);
		response.setRelatedEntitiesListData(applicationActivityGroupService.relatedEntitesToListItems());
		if(id != null)
			response.setEntity(applicationActivityGroupService.convertModelToResponse(applicationActivityGroupService.findById(id)));
		// The response will now have the id of the embedded entity (WHY)
		if(response.getEntity() != null)
			response.setId(response.getEntity().getId());
		return response;
	}

    /**
     * @param uriInfo
     * @return
     */
    @GET
    @Path("/modules/{id:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ApplicationActivityGroupResponse> getRelatedEntitiesAsListItems(@PathParam("id") Integer moduleId)
    {
		// Load the list of ApplicationActivityGroup's
		List<ApplicationActivityGroupResponse> dataItems = new ArrayList<ApplicationActivityGroupResponse>();
		for (ApplicationActivityGroup item:applicationActivityGroupService.findGroupsInModule(moduleId))
		{
			dataItems.add(applicationActivityGroupService.convertModelToResponse(item));
		}
    	return dataItems;
    }

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#prepareRelatedEntitiesListItems(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected Map<String, List<ListItemResponse>> prepareRelatedEntitiesListItems(MultivaluedMap<String, String> multivaluedMap) {
		return applicationActivityGroupService.relatedEntitesToListItems();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#saveEditedEntityInstance(java.lang.Object)
	 */
	@Override
	protected EditActivityResponse<ApplicationActivityGroupResponse> saveEntityInstance(
			ApplicationActivityGroupRequest entityInstance) {
		applicationActivityGroupService.create(entityInstance);
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#saveEditedEntityInstance(java.lang.Object)
	 */
	@Override
	protected EditActivityResponse<ApplicationActivityGroupResponse> saveEditedEntityInstance(
			ApplicationActivityGroupRequest entityInstance) {
		applicationActivityGroupService.update(entityInstance);
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getListActivityCode()
	 */
	@Override
	protected String getListActivityCode() {
		return applicationActivityGroupService.getListActivityCode();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getEditActivityCode()
	 */
	@Override
	protected String getEditActivityCode() {
		return applicationActivityGroupService.getEditActivityCode();
	}

}
