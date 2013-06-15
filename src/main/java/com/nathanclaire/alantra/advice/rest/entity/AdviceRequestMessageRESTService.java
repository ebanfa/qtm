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

import com.nathanclaire.alantra.advice.model.AdviceRequestMessage;
import com.nathanclaire.alantra.application.model.ApplicationEntityField;
import com.nathanclaire.alantra.advice.request.AdviceRequestMessageRequest;
import com.nathanclaire.alantra.advice.response.AdviceRequestMessageResponse;
import com.nathanclaire.alantra.application.response.ApplicationActivityResponse;
import com.nathanclaire.alantra.application.response.ApplicationEntityFieldResponse;
import com.nathanclaire.alantra.advice.service.entity.AdviceRequestMessageService;
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
@Path("/advicerequestmessage")
@Stateless
public class AdviceRequestMessageRESTService extends BaseActivityRESTService<AdviceRequestMessageResponse, AdviceRequestMessageRequest> 
{
	@Inject
	AdviceRequestMessageService adviceRequestMessageService;
	
	@Inject 
	ApplicationEntityFieldService applicationEntityFieldService;
	
	private Logger logger = LoggerFactory.getLogger(AdviceRequestMessageRESTService.class);
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#populateListActivityResponse(com.nathanclaire.alantra.advice.response.AdviceRequestMessageResponse, com.nathanclaire.alantra.base.response.ListActivityResponse, javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected ListActivityResponse<AdviceRequestMessageResponse> populateListActivityResponse(
			ApplicationActivityResponse activity, ListActivityResponse<AdviceRequestMessageResponse> response,
			MultivaluedMap<String, String> queryParameters) throws ApplicationException 
	{
		// Load the fields for the AdviceRequestMessage entity
		List<ApplicationEntityFieldResponse> responseFields = new ArrayList<ApplicationEntityFieldResponse>();
		List<ApplicationEntityField> entityFields = adviceRequestMessageService.getEntityFields();
		for(ApplicationEntityField entityField:entityFields)
		{
			responseFields.add(applicationEntityFieldService.convertModelToResponse(entityField));
		}
		response.setFields(responseFields);
		// Load the list of AdviceRequestMessage's
		List<AdviceRequestMessageResponse> dataItems = new ArrayList<AdviceRequestMessageResponse>();
		for (AdviceRequestMessage item:adviceRequestMessageService.findAll(queryParameters))
		{
			dataItems.add(adviceRequestMessageService.convertModelToResponse(item));
		}
		response.setData(dataItems);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getInstance(java.lang.Integer)
	 */
	@Override
	protected EditActivityResponse<AdviceRequestMessageResponse> populateEditActivityResponse(
			Integer id,	ApplicationActivityResponse activity, EditActivityResponse<AdviceRequestMessageResponse> response) 
					throws ApplicationException {
		// Load the fields for the AdviceRequestMessage entity
		List<ApplicationEntityField> entityFields = adviceRequestMessageService.getEntityFields();
		List<ApplicationEntityFieldResponse> responseFields = new ArrayList<ApplicationEntityFieldResponse>();
		// Convert the entity fields into response fields
		for(ApplicationEntityField entityField:entityFields)
		{
			responseFields.add(applicationEntityFieldService.convertModelToResponse(entityField));
		}
		response.setFields(responseFields);
		response.setRelatedEntitiesListData(adviceRequestMessageService.relatedEntitesToListItems());
		if(id != null)
			response.setEntity(adviceRequestMessageService.convertModelToResponse(adviceRequestMessageService.findById(id)));
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
		return adviceRequestMessageService.relatedEntitesToListItems();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#saveEditedEntityInstance(java.lang.Object)
	 */
	@Override
	protected EditActivityResponse<AdviceRequestMessageResponse> saveEntityInstance(
			AdviceRequestMessageRequest entityInstance) throws ApplicationException {
		AdviceRequestMessage adviceRequestMessage = adviceRequestMessageService.create(entityInstance);
		return this.getEditActivityResponse(adviceRequestMessage.getId());
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#saveEditedEntityInstance(java.lang.Object)
	 */
	@Override
	protected EditActivityResponse<AdviceRequestMessageResponse> saveEditedEntityInstance(
			AdviceRequestMessageRequest entityInstance) throws ApplicationException {
		AdviceRequestMessage adviceRequestMessage = adviceRequestMessageService.update(entityInstance);
		return this.getEditActivityResponse(adviceRequestMessage.getId());
	}
	
	@Override
	protected ListActivityResponse<AdviceRequestMessageResponse> deleteEntityInstances(
			List<Integer> idsOfEntitiesToDelete) throws ApplicationException {
		for(Integer id: idsOfEntitiesToDelete)
		{
			 adviceRequestMessageService.delete(id);
		}
		return this.getListActivityResponse(null);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getListActivityCode()
	 */
	@Override
	protected String getListActivityCode() throws ApplicationException {
		return adviceRequestMessageService.getListActivityCode();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getEditActivityCode()
	 */
	@Override
	protected String getEditActivityCode() throws ApplicationException {
		return adviceRequestMessageService.getEditActivityCode();
	}

}
