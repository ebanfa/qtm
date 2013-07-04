/**
 * 
 */
package com.nathanclaire.alantra.messaging.rest.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.MultivaluedMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.messaging.model.MessageApplicationAction;
import com.nathanclaire.alantra.application.model.ApplicationEntityField;
import com.nathanclaire.alantra.messaging.request.MessageApplicationActionRequest;
import com.nathanclaire.alantra.messaging.response.MessageApplicationActionResponse;
import com.nathanclaire.alantra.application.response.ApplicationActivityResponse;
import com.nathanclaire.alantra.application.response.ApplicationEntityFieldResponse;
import com.nathanclaire.alantra.messaging.service.entity.MessageApplicationActionService;
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
@Path("/messageapplicationaction")
@Stateless
public class MessageApplicationActionRESTService extends BaseActivityRESTService<MessageApplicationActionResponse, MessageApplicationActionRequest> 
{
	@Inject
	MessageApplicationActionService messageApplicationActionService;
	
	@Inject 
	ApplicationEntityFieldService applicationEntityFieldService;
	
	private Logger logger = LoggerFactory.getLogger(MessageApplicationActionRESTService.class);
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#populateListActivityResponse(com.nathanclaire.alantra.messaging.response.MessageApplicationActionResponse, com.nathanclaire.alantra.base.response.ListActivityResponse, javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected ListActivityResponse<MessageApplicationActionResponse> populateListActivityResponse(
			ApplicationActivityResponse activity, ListActivityResponse<MessageApplicationActionResponse> response,
			MultivaluedMap<String, String> queryParameters) throws ApplicationException 
	{
		// Load the fields for the MessageApplicationAction entity
		List<ApplicationEntityFieldResponse> responseFields = new ArrayList<ApplicationEntityFieldResponse>();
		List<ApplicationEntityField> entityFields = messageApplicationActionService.getEntityFields();
		for(ApplicationEntityField entityField:entityFields)
		{
			responseFields.add(applicationEntityFieldService.convertModelToResponse(entityField));
		}
		response.setFields(responseFields);
		// Load the list of MessageApplicationAction's
		List<MessageApplicationActionResponse> dataItems = new ArrayList<MessageApplicationActionResponse>();
		for (MessageApplicationAction item:messageApplicationActionService.findAll(queryParameters))
		{
			dataItems.add(messageApplicationActionService.convertModelToResponse(item));
		}
		response.setData(dataItems);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getInstance(java.lang.Integer)
	 */
	@Override
	protected EditActivityResponse<MessageApplicationActionResponse> populateEditActivityResponse(
			Integer id,	ApplicationActivityResponse activity, EditActivityResponse<MessageApplicationActionResponse> response) 
					throws ApplicationException {
		// Load the fields for the MessageApplicationAction entity
		List<ApplicationEntityField> entityFields = messageApplicationActionService.getEntityFields();
		List<ApplicationEntityFieldResponse> responseFields = new ArrayList<ApplicationEntityFieldResponse>();
		// Convert the entity fields into response fields
		for(ApplicationEntityField entityField:entityFields)
		{
			responseFields.add(applicationEntityFieldService.convertModelToResponse(entityField));
		}
		response.setFields(responseFields);
		response.setRelatedEntitiesListData(messageApplicationActionService.relatedEntitesToListItems());
		if(id != null)
			response.setEntity(messageApplicationActionService.convertModelToResponse(messageApplicationActionService.findById(id)));
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
		return messageApplicationActionService.relatedEntitesToListItems();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#saveEditedEntityInstance(java.lang.Object)
	 */
	@Override
	protected EditActivityResponse<MessageApplicationActionResponse> saveEntityInstance(
			MessageApplicationActionRequest entityInstance) throws ApplicationException {
		MessageApplicationAction messageApplicationAction = messageApplicationActionService.create(entityInstance);
		return this.getEditActivityResponse(messageApplicationAction.getId());
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#saveEditedEntityInstance(java.lang.Object)
	 */
	@Override
	protected EditActivityResponse<MessageApplicationActionResponse> saveEditedEntityInstance(
			MessageApplicationActionRequest entityInstance) throws ApplicationException {
		MessageApplicationAction messageApplicationAction = messageApplicationActionService.update(entityInstance);
		return this.getEditActivityResponse(messageApplicationAction.getId());
	}
	
	@Override
	protected ListActivityResponse<MessageApplicationActionResponse> deleteEntityInstances(
			List<Integer> idsOfEntitiesToDelete) throws ApplicationException {
		for(Integer id: idsOfEntitiesToDelete)
		{
			 messageApplicationActionService.delete(id);
		}
		return this.getListActivityResponse(null);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getListActivityCode()
	 */
	@Override
	protected String getListActivityCode() throws ApplicationException {
		return messageApplicationActionService.getListActivityCode();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getEditActivityCode()
	 */
	@Override
	protected String getEditActivityCode() throws ApplicationException {
		return messageApplicationActionService.getEditActivityCode();
	}

}
