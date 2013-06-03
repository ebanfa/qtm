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

import com.nathanclaire.alantra.advice.model.AdviceChannelCategory;
import com.nathanclaire.alantra.application.model.ApplicationEntityField;
import com.nathanclaire.alantra.advice.request.AdviceChannelCategoryRequest;
import com.nathanclaire.alantra.advice.response.AdviceChannelCategoryResponse;
import com.nathanclaire.alantra.application.response.ApplicationActivityResponse;
import com.nathanclaire.alantra.application.response.ApplicationEntityFieldResponse;
import com.nathanclaire.alantra.advice.service.entity.AdviceChannelCategoryService;
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
@Path("/advicechannelcategory")
@Stateless
public class AdviceChannelCategoryRESTService extends BaseActivityRESTService<AdviceChannelCategoryResponse, AdviceChannelCategoryRequest> 
{
	@Inject
	AdviceChannelCategoryService adviceChannelCategoryService;
	
	@Inject 
	ApplicationEntityFieldService applicationEntityFieldService;
	
	private Logger logger = LoggerFactory.getLogger(AdviceChannelCategoryRESTService.class);
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#populateListActivityResponse(com.nathanclaire.alantra.advice.response.AdviceChannelCategoryResponse, com.nathanclaire.alantra.base.response.ListActivityResponse, javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected ListActivityResponse<AdviceChannelCategoryResponse> populateListActivityResponse(
			ApplicationActivityResponse activity, ListActivityResponse<AdviceChannelCategoryResponse> response,
			MultivaluedMap<String, String> queryParameters) throws ApplicationException 
	{
		// Load the fields for the AdviceChannelCategory entity
		List<ApplicationEntityFieldResponse> responseFields = new ArrayList<ApplicationEntityFieldResponse>();
		List<ApplicationEntityField> entityFields = adviceChannelCategoryService.getEntityFields();
		for(ApplicationEntityField entityField:entityFields)
		{
			responseFields.add(applicationEntityFieldService.convertModelToResponse(entityField));
		}
		response.setFields(responseFields);
		// Load the list of AdviceChannelCategory's
		List<AdviceChannelCategoryResponse> dataItems = new ArrayList<AdviceChannelCategoryResponse>();
		for (AdviceChannelCategory item:adviceChannelCategoryService.findAll(queryParameters))
		{
			dataItems.add(adviceChannelCategoryService.convertModelToResponse(item));
		}
		response.setData(dataItems);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getInstance(java.lang.Integer)
	 */
	@Override
	protected EditActivityResponse<AdviceChannelCategoryResponse> populateEditActivityResponse(
			Integer id,	ApplicationActivityResponse activity, EditActivityResponse<AdviceChannelCategoryResponse> response) 
					throws ApplicationException {
		// Load the fields for the AdviceChannelCategory entity
		List<ApplicationEntityField> entityFields = adviceChannelCategoryService.getEntityFields();
		List<ApplicationEntityFieldResponse> responseFields = new ArrayList<ApplicationEntityFieldResponse>();
		// Convert the entity fields into response fields
		for(ApplicationEntityField entityField:entityFields)
		{
			responseFields.add(applicationEntityFieldService.convertModelToResponse(entityField));
		}
		response.setFields(responseFields);
		response.setRelatedEntitiesListData(adviceChannelCategoryService.relatedEntitesToListItems());
		if(id != null)
			response.setEntity(adviceChannelCategoryService.convertModelToResponse(adviceChannelCategoryService.findById(id)));
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
		return adviceChannelCategoryService.relatedEntitesToListItems();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#saveEditedEntityInstance(java.lang.Object)
	 */
	@Override
	protected EditActivityResponse<AdviceChannelCategoryResponse> saveEntityInstance(
			AdviceChannelCategoryRequest entityInstance) throws ApplicationException {
		AdviceChannelCategory adviceChannelCategory = adviceChannelCategoryService.create(entityInstance);
		return this.getEditActivityResponse(adviceChannelCategory.getId());
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#saveEditedEntityInstance(java.lang.Object)
	 */
	@Override
	protected EditActivityResponse<AdviceChannelCategoryResponse> saveEditedEntityInstance(
			AdviceChannelCategoryRequest entityInstance) throws ApplicationException {
		AdviceChannelCategory adviceChannelCategory = adviceChannelCategoryService.update(entityInstance);
		return this.getEditActivityResponse(adviceChannelCategory.getId());
	}
	
	@Override
	protected ListActivityResponse<AdviceChannelCategoryResponse> deleteEntityInstances(
			List<Integer> idsOfEntitiesToDelete) throws ApplicationException {
		for(Integer id: idsOfEntitiesToDelete)
		{
			 adviceChannelCategoryService.delete(id);
		}
		return this.getListActivityResponse(null);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getListActivityCode()
	 */
	@Override
	protected String getListActivityCode() throws ApplicationException {
		return adviceChannelCategoryService.getListActivityCode();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getEditActivityCode()
	 */
	@Override
	protected String getEditActivityCode() throws ApplicationException {
		return adviceChannelCategoryService.getEditActivityCode();
	}

}
