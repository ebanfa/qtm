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

import com.nathanclaire.alantra.messaging.model.MessageApplicationNotificationMap;
import com.nathanclaire.alantra.application.model.ApplicationEntityField;
import com.nathanclaire.alantra.messaging.request.MessageApplicationNotificationMapRequest;
import com.nathanclaire.alantra.messaging.response.MessageApplicationNotificationMapResponse;
import com.nathanclaire.alantra.application.response.ApplicationActivityResponse;
import com.nathanclaire.alantra.application.response.ApplicationEntityFieldResponse;
import com.nathanclaire.alantra.messaging.service.entity.MessageApplicationNotificationMapService;
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
@Path("/messageapplicationnotificationmap")
@Stateless
public class MessageApplicationNotificationMapRESTService extends BaseActivityRESTService<MessageApplicationNotificationMapResponse, MessageApplicationNotificationMapRequest> 
{
	@Inject
	MessageApplicationNotificationMapService messageApplicationNotificationMapService;
	
	@Inject 
	ApplicationEntityFieldService applicationEntityFieldService;
	
	private Logger logger = LoggerFactory.getLogger(MessageApplicationNotificationMapRESTService.class);
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#populateListActivityResponse(com.nathanclaire.alantra.messaging.response.MessageApplicationNotificationMapResponse, com.nathanclaire.alantra.base.response.ListActivityResponse, javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected ListActivityResponse<MessageApplicationNotificationMapResponse> populateListActivityResponse(
			ApplicationActivityResponse activity, ListActivityResponse<MessageApplicationNotificationMapResponse> response,
			MultivaluedMap<String, String> queryParameters) throws ApplicationException 
	{
		// Load the fields for the MessageApplicationNotificationMap entity
		List<ApplicationEntityFieldResponse> responseFields = new ArrayList<ApplicationEntityFieldResponse>();
		List<ApplicationEntityField> entityFields = messageApplicationNotificationMapService.getEntityFields();
		for(ApplicationEntityField entityField:entityFields)
		{
			responseFields.add(applicationEntityFieldService.convertModelToResponse(entityField));
		}
		response.setFields(responseFields);
		// Load the list of MessageApplicationNotificationMap's
		List<MessageApplicationNotificationMapResponse> dataItems = new ArrayList<MessageApplicationNotificationMapResponse>();
		for (MessageApplicationNotificationMap item:messageApplicationNotificationMapService.findAll(queryParameters))
		{
			dataItems.add(messageApplicationNotificationMapService.convertModelToResponse(item));
		}
		response.setData(dataItems);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getInstance(java.lang.Integer)
	 */
	@Override
	protected EditActivityResponse<MessageApplicationNotificationMapResponse> populateEditActivityResponse(
			Integer id,	ApplicationActivityResponse activity, EditActivityResponse<MessageApplicationNotificationMapResponse> response) 
					throws ApplicationException {
		// Load the fields for the MessageApplicationNotificationMap entity
		List<ApplicationEntityField> entityFields = messageApplicationNotificationMapService.getEntityFields();
		List<ApplicationEntityFieldResponse> responseFields = new ArrayList<ApplicationEntityFieldResponse>();
		// Convert the entity fields into response fields
		for(ApplicationEntityField entityField:entityFields)
		{
			responseFields.add(applicationEntityFieldService.convertModelToResponse(entityField));
		}
		response.setFields(responseFields);
		response.setRelatedEntitiesListData(messageApplicationNotificationMapService.relatedEntitesToListItems());
		if(id != null)
			response.setEntity(messageApplicationNotificationMapService.convertModelToResponse(messageApplicationNotificationMapService.findById(id)));
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
		return messageApplicationNotificationMapService.relatedEntitesToListItems();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#saveEditedEntityInstance(java.lang.Object)
	 */
	@Override
	protected EditActivityResponse<MessageApplicationNotificationMapResponse> saveEntityInstance(
			MessageApplicationNotificationMapRequest entityInstance) throws ApplicationException {
		MessageApplicationNotificationMap messageApplicationNotificationMap = messageApplicationNotificationMapService.create(entityInstance);
		return this.getEditActivityResponse(messageApplicationNotificationMap.getId());
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#saveEditedEntityInstance(java.lang.Object)
	 */
	@Override
	protected EditActivityResponse<MessageApplicationNotificationMapResponse> saveEditedEntityInstance(
			MessageApplicationNotificationMapRequest entityInstance) throws ApplicationException {
		MessageApplicationNotificationMap messageApplicationNotificationMap = messageApplicationNotificationMapService.update(entityInstance);
		return this.getEditActivityResponse(messageApplicationNotificationMap.getId());
	}
	
	@Override
	protected ListActivityResponse<MessageApplicationNotificationMapResponse> deleteEntityInstances(
			List<Integer> idsOfEntitiesToDelete) throws ApplicationException {
		for(Integer id: idsOfEntitiesToDelete)
		{
			 messageApplicationNotificationMapService.delete(id);
		}
		return this.getListActivityResponse(null);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getListActivityCode()
	 */
	@Override
	protected String getListActivityCode() throws ApplicationException {
		return messageApplicationNotificationMapService.getListActivityCode();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getEditActivityCode()
	 */
	@Override
	protected String getEditActivityCode() throws ApplicationException {
		return messageApplicationNotificationMapService.getEditActivityCode();
	}

}
