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

import com.nathanclaire.alantra.advice.model.AdviceRequestMessageStatus;
import com.nathanclaire.alantra.application.model.ApplicationEntityField;
import com.nathanclaire.alantra.advice.request.AdviceRequestMessageStatusRequest;
import com.nathanclaire.alantra.advice.response.AdviceRequestMessageStatusResponse;
import com.nathanclaire.alantra.application.response.ApplicationActivityResponse;
import com.nathanclaire.alantra.application.response.ApplicationEntityFieldResponse;
import com.nathanclaire.alantra.advice.service.entity.AdviceRequestMessageStatusService;
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
@Path("/advicerequestmessagestatus")
@Stateless
public class AdviceRequestMessageStatusRESTService extends BaseActivityRESTService<AdviceRequestMessageStatusResponse, AdviceRequestMessageStatusRequest> 
{
	@Inject
	AdviceRequestMessageStatusService adviceRequestMessageStatusService;
	
	@Inject 
	ApplicationEntityFieldService applicationEntityFieldService;
	
	private Logger logger = LoggerFactory.getLogger(AdviceRequestMessageStatusRESTService.class);
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#populateListActivityResponse(com.nathanclaire.alantra.advice.response.AdviceRequestMessageStatusResponse, com.nathanclaire.alantra.base.response.ListActivityResponse, javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected ListActivityResponse<AdviceRequestMessageStatusResponse> populateListActivityResponse(
			ApplicationActivityResponse activity, ListActivityResponse<AdviceRequestMessageStatusResponse> response,
			MultivaluedMap<String, String> queryParameters) throws ApplicationException 
	{
		// Load the fields for the AdviceRequestMessageStatus entity
		List<ApplicationEntityFieldResponse> responseFields = new ArrayList<ApplicationEntityFieldResponse>();
		List<ApplicationEntityField> entityFields = adviceRequestMessageStatusService.getEntityFields();
		for(ApplicationEntityField entityField:entityFields)
		{
			responseFields.add(applicationEntityFieldService.convertModelToResponse(entityField));
		}
		response.setFields(responseFields);
		// Load the list of AdviceRequestMessageStatus's
		List<AdviceRequestMessageStatusResponse> dataItems = new ArrayList<AdviceRequestMessageStatusResponse>();
		for (AdviceRequestMessageStatus item:adviceRequestMessageStatusService.findAll(queryParameters))
		{
			dataItems.add(adviceRequestMessageStatusService.convertModelToResponse(item));
		}
		response.setData(dataItems);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getInstance(java.lang.Integer)
	 */
	@Override
	protected EditActivityResponse<AdviceRequestMessageStatusResponse> populateEditActivityResponse(
			Integer id,	ApplicationActivityResponse activity, EditActivityResponse<AdviceRequestMessageStatusResponse> response) 
					throws ApplicationException {
		// Load the fields for the AdviceRequestMessageStatus entity
		List<ApplicationEntityField> entityFields = adviceRequestMessageStatusService.getEntityFields();
		List<ApplicationEntityFieldResponse> responseFields = new ArrayList<ApplicationEntityFieldResponse>();
		// Convert the entity fields into response fields
		for(ApplicationEntityField entityField:entityFields)
		{
			responseFields.add(applicationEntityFieldService.convertModelToResponse(entityField));
		}
		response.setFields(responseFields);
		response.setRelatedEntitiesListData(adviceRequestMessageStatusService.relatedEntitesToListItems());
		if(id != null)
			response.setEntity(adviceRequestMessageStatusService.convertModelToResponse(adviceRequestMessageStatusService.findById(id)));
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
		return adviceRequestMessageStatusService.relatedEntitesToListItems();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#saveEditedEntityInstance(java.lang.Object)
	 */
	@Override
	protected EditActivityResponse<AdviceRequestMessageStatusResponse> saveEntityInstance(
			AdviceRequestMessageStatusRequest entityInstance) throws ApplicationException {
		AdviceRequestMessageStatus adviceRequestMessageStatus = adviceRequestMessageStatusService.create(entityInstance);
		return this.getEditActivityResponse(adviceRequestMessageStatus.getId());
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#saveEditedEntityInstance(java.lang.Object)
	 */
	@Override
	protected EditActivityResponse<AdviceRequestMessageStatusResponse> saveEditedEntityInstance(
			AdviceRequestMessageStatusRequest entityInstance) throws ApplicationException {
		AdviceRequestMessageStatus adviceRequestMessageStatus = adviceRequestMessageStatusService.update(entityInstance);
		return this.getEditActivityResponse(adviceRequestMessageStatus.getId());
	}
	
	@Override
	protected ListActivityResponse<AdviceRequestMessageStatusResponse> deleteEntityInstances(
			List<Integer> idsOfEntitiesToDelete) throws ApplicationException {
		for(Integer id: idsOfEntitiesToDelete)
		{
			 adviceRequestMessageStatusService.delete(id);
		}
		return this.getListActivityResponse(null);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getListActivityCode()
	 */
	@Override
	protected String getListActivityCode() throws ApplicationException {
		return adviceRequestMessageStatusService.getListActivityCode();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getEditActivityCode()
	 */
	@Override
	protected String getEditActivityCode() throws ApplicationException {
		return adviceRequestMessageStatusService.getEditActivityCode();
	}

}
