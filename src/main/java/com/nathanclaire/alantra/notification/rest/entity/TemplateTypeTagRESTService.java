/**
 * 
 */
package com.nathanclaire.alantra.notification.rest.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.MultivaluedMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.notification.model.TemplateTypeTag;
import com.nathanclaire.alantra.application.model.ApplicationEntityField;
import com.nathanclaire.alantra.notification.request.TemplateTypeTagRequest;
import com.nathanclaire.alantra.notification.response.TemplateTypeTagResponse;
import com.nathanclaire.alantra.application.response.ApplicationActivityResponse;
import com.nathanclaire.alantra.application.response.ApplicationEntityFieldResponse;
import com.nathanclaire.alantra.notification.service.entity.TemplateTypeTagService;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityFieldService;
import com.nathanclaire.alantra.base.response.EditActivityResponse;
import com.nathanclaire.alantra.base.response.ListActivityResponse;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.rest.BaseActivityRESTService;
import com.nathanclaire.alantra.base.util.ApplicationException;

/**
 * @author administrator
 *
 */
@Path("/templatetypetag")
@Stateless
public class TemplateTypeTagRESTService extends BaseActivityRESTService<TemplateTypeTagResponse, TemplateTypeTagRequest> 
{
	@Inject
	TemplateTypeTagService templateTypeTagService;
	
	@Inject 
	ApplicationEntityFieldService applicationEntityFieldService;
	
	private Logger logger = LoggerFactory.getLogger(TemplateTypeTagRESTService.class);
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#populateListActivityResponse(com.nathanclaire.alantra.notification.response.TemplateTypeTagResponse, com.nathanclaire.alantra.base.response.ListActivityResponse, javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected ListActivityResponse<TemplateTypeTagResponse> populateListActivityResponse(
			ApplicationActivityResponse activity, ListActivityResponse<TemplateTypeTagResponse> response,
			MultivaluedMap<String, String> queryParameters) throws ApplicationException 
	{
		// Load the fields for the TemplateTypeTag entity
		List<ApplicationEntityFieldResponse> responseFields = new ArrayList<ApplicationEntityFieldResponse>();
		List<ApplicationEntityField> entityFields = templateTypeTagService.getEntityFields();
		for(ApplicationEntityField entityField:entityFields)
		{
			responseFields.add(applicationEntityFieldService.convertModelToResponse(entityField));
		}
		response.setFields(responseFields);
		// Load the list of TemplateTypeTag's
		List<TemplateTypeTagResponse> dataItems = new ArrayList<TemplateTypeTagResponse>();
		for (TemplateTypeTag item:templateTypeTagService.findAll(queryParameters))
		{
			dataItems.add(templateTypeTagService.convertModelToResponse(item));
		}
		response.setData(dataItems);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getInstance(java.lang.Integer)
	 */
	@Override
	protected EditActivityResponse<TemplateTypeTagResponse> populateEditActivityResponse(
			Integer id,	ApplicationActivityResponse activity, EditActivityResponse<TemplateTypeTagResponse> response) 
					throws ApplicationException {
		// Load the fields for the TemplateTypeTag entity
		List<ApplicationEntityField> entityFields = templateTypeTagService.getEntityFields();
		List<ApplicationEntityFieldResponse> responseFields = new ArrayList<ApplicationEntityFieldResponse>();
		// Convert the entity fields into response fields
		for(ApplicationEntityField entityField:entityFields)
		{
			responseFields.add(applicationEntityFieldService.convertModelToResponse(entityField));
		}
		response.setFields(responseFields);
		response.setRelatedEntitiesListData(templateTypeTagService.relatedEntitesToListItems());
		if(id != null)
			response.setEntity(templateTypeTagService.convertModelToResponse(templateTypeTagService.findById(id)));
		// The response will now have the id of the embedded entity (WHY)
		if(response.getEntity() != null)
			response.setId(response.getEntity().getId());
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#prepareRelatedEntitiesListItems(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected Map<String, List<ListItemResponse>> prepareRelatedEntitiesListItems(MultivaluedMap<String, String> multivaluedMap) 
				   throws ApplicationException {
		return templateTypeTagService.relatedEntitesToListItems();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#saveEditedEntityInstance(java.lang.Object)
	 */
	@Override
	protected EditActivityResponse<TemplateTypeTagResponse> saveEntityInstance(
			TemplateTypeTagRequest entityInstance) throws ApplicationException {
		TemplateTypeTag templateTypeTag = templateTypeTagService.create(entityInstance);
		return this.getEditActivityResponse(templateTypeTag.getId());
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#saveEditedEntityInstance(java.lang.Object)
	 */
	@Override
	protected EditActivityResponse<TemplateTypeTagResponse> saveEditedEntityInstance(
			TemplateTypeTagRequest entityInstance) throws ApplicationException {
		TemplateTypeTag templateTypeTag = templateTypeTagService.update(entityInstance);
		return this.getEditActivityResponse(templateTypeTag.getId());
	}
	
	@Override
	protected ListActivityResponse<TemplateTypeTagResponse> deleteEntityInstances(
			List<Integer> idsOfEntitiesToDelete) throws ApplicationException {
		for(Integer id: idsOfEntitiesToDelete)
		{
			 templateTypeTagService.delete(id);
		}
		return this.getListActivityResponse(null);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getListActivityCode()
	 */
	@Override
	protected String getListActivityCode() throws ApplicationException {
		return templateTypeTagService.getListActivityCode();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getEditActivityCode()
	 */
	@Override
	protected String getEditActivityCode() throws ApplicationException {
		return templateTypeTagService.getEditActivityCode();
	}

}
