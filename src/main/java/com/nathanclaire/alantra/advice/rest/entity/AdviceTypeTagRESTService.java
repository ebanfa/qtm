/**
 * 
 */
package com.nathanclaire.alantra.advice.rest.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.MultivaluedMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.advice.model.AdviceTypeTag;
import com.nathanclaire.alantra.application.model.ApplicationEntityField;
import com.nathanclaire.alantra.advice.request.AdviceTypeTagRequest;
import com.nathanclaire.alantra.advice.response.AdviceTypeTagResponse;
import com.nathanclaire.alantra.application.response.ApplicationActivityResponse;
import com.nathanclaire.alantra.application.response.ApplicationEntityFieldResponse;
import com.nathanclaire.alantra.advice.service.entity.AdviceTypeTagService;
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
@Path("/advicetypetag")
@Stateless
public class AdviceTypeTagRESTService extends BaseActivityRESTService<AdviceTypeTagResponse, AdviceTypeTagRequest> 
{
	@Inject
	AdviceTypeTagService adviceTypeTagService;
	
	@Inject 
	ApplicationEntityFieldService applicationEntityFieldService;
	
	private Logger logger = LoggerFactory.getLogger(AdviceTypeTagRESTService.class);
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#populateListActivityResponse(com.nathanclaire.alantra.advice.response.AdviceTypeTagResponse, com.nathanclaire.alantra.base.response.ListActivityResponse, javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected ListActivityResponse<AdviceTypeTagResponse> populateListActivityResponse(
			ApplicationActivityResponse activity, ListActivityResponse<AdviceTypeTagResponse> response,
			MultivaluedMap<String, String> queryParameters) throws ApplicationException 
	{
		// Load the fields for the AdviceTypeTag entity
		List<ApplicationEntityFieldResponse> responseFields = new ArrayList<ApplicationEntityFieldResponse>();
		List<ApplicationEntityField> entityFields = adviceTypeTagService.getEntityFields();
		for(ApplicationEntityField entityField:entityFields)
		{
			responseFields.add(applicationEntityFieldService.convertModelToResponse(entityField));
		}
		response.setFields(responseFields);
		// Load the list of AdviceTypeTag's
		List<AdviceTypeTagResponse> dataItems = new ArrayList<AdviceTypeTagResponse>();
		for (AdviceTypeTag item:adviceTypeTagService.findAll(queryParameters))
		{
			dataItems.add(adviceTypeTagService.convertModelToResponse(item));
		}
		response.setData(dataItems);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getInstance(java.lang.Integer)
	 */
	@Override
	protected EditActivityResponse<AdviceTypeTagResponse> populateEditActivityResponse(
			Integer id,	ApplicationActivityResponse activity, EditActivityResponse<AdviceTypeTagResponse> response) 
					throws ApplicationException {
		// Load the fields for the AdviceTypeTag entity
		List<ApplicationEntityField> entityFields = adviceTypeTagService.getEntityFields();
		List<ApplicationEntityFieldResponse> responseFields = new ArrayList<ApplicationEntityFieldResponse>();
		// Convert the entity fields into response fields
		for(ApplicationEntityField entityField:entityFields)
		{
			responseFields.add(applicationEntityFieldService.convertModelToResponse(entityField));
		}
		response.setFields(responseFields);
		response.setRelatedEntitiesListData(adviceTypeTagService.relatedEntitesToListItems());
		if(id != null)
			response.setEntity(adviceTypeTagService.convertModelToResponse(adviceTypeTagService.findById(id)));
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
		return adviceTypeTagService.relatedEntitesToListItems();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#saveEditedEntityInstance(java.lang.Object)
	 */
	@Override
	protected EditActivityResponse<AdviceTypeTagResponse> saveEntityInstance(
			AdviceTypeTagRequest entityInstance) throws ApplicationException {
		AdviceTypeTag adviceTypeTag = adviceTypeTagService.create(entityInstance);
		return this.getEditActivityResponse(adviceTypeTag.getId());
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#saveEditedEntityInstance(java.lang.Object)
	 */
	@Override
	protected EditActivityResponse<AdviceTypeTagResponse> saveEditedEntityInstance(
			AdviceTypeTagRequest entityInstance) throws ApplicationException {
		AdviceTypeTag adviceTypeTag = adviceTypeTagService.update(entityInstance);
		return this.getEditActivityResponse(adviceTypeTag.getId());
	}
	
	@Override
	protected ListActivityResponse<AdviceTypeTagResponse> deleteEntityInstances(
			List<Integer> idsOfEntitiesToDelete) throws ApplicationException {
		for(Integer id: idsOfEntitiesToDelete)
		{
			 adviceTypeTagService.delete(id);
		}
		return this.getListActivityResponse(null);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getListActivityCode()
	 */
	@Override
	protected String getListActivityCode() throws ApplicationException {
		return adviceTypeTagService.getListActivityCode();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getEditActivityCode()
	 */
	@Override
	protected String getEditActivityCode() throws ApplicationException {
		return adviceTypeTagService.getEditActivityCode();
	}

}
