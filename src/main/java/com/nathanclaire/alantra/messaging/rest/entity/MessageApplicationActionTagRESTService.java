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

import com.nathanclaire.alantra.messaging.model.MessageApplicationActionTag;
import com.nathanclaire.alantra.application.model.ApplicationEntityField;
import com.nathanclaire.alantra.messaging.request.MessageApplicationActionTagRequest;
import com.nathanclaire.alantra.messaging.response.MessageApplicationActionTagResponse;
import com.nathanclaire.alantra.application.response.ApplicationActivityResponse;
import com.nathanclaire.alantra.application.response.ApplicationEntityFieldResponse;
import com.nathanclaire.alantra.messaging.service.entity.MessageApplicationActionTagService;
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
@Path("/messageapplicationactiontag")
@Stateless
public class MessageApplicationActionTagRESTService extends BaseActivityRESTService<MessageApplicationActionTagResponse, MessageApplicationActionTagRequest> 
{
	@Inject
	MessageApplicationActionTagService messageApplicationActionTagService;
	
	@Inject 
	ApplicationEntityFieldService applicationEntityFieldService;
	
	private Logger logger = LoggerFactory.getLogger(MessageApplicationActionTagRESTService.class);
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#populateListActivityResponse(com.nathanclaire.alantra.messaging.response.MessageApplicationActionTagResponse, com.nathanclaire.alantra.base.response.ListActivityResponse, javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected ListActivityResponse<MessageApplicationActionTagResponse> populateListActivityResponse(
			ApplicationActivityResponse activity, ListActivityResponse<MessageApplicationActionTagResponse> response,
			MultivaluedMap<String, String> queryParameters) throws ApplicationException 
	{
		// Load the fields for the MessageApplicationActionTag entity
		List<ApplicationEntityFieldResponse> responseFields = new ArrayList<ApplicationEntityFieldResponse>();
		List<ApplicationEntityField> entityFields = messageApplicationActionTagService.getEntityFields();
		for(ApplicationEntityField entityField:entityFields)
		{
			responseFields.add(applicationEntityFieldService.convertModelToResponse(entityField));
		}
		response.setFields(responseFields);
		// Load the list of MessageApplicationActionTag's
		List<MessageApplicationActionTagResponse> dataItems = new ArrayList<MessageApplicationActionTagResponse>();
		for (MessageApplicationActionTag item:messageApplicationActionTagService.findAll(queryParameters))
		{
			dataItems.add(messageApplicationActionTagService.convertModelToResponse(item));
		}
		response.setData(dataItems);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getInstance(java.lang.Integer)
	 */
	@Override
	protected EditActivityResponse<MessageApplicationActionTagResponse> populateEditActivityResponse(
			Integer id,	ApplicationActivityResponse activity, EditActivityResponse<MessageApplicationActionTagResponse> response) 
					throws ApplicationException {
		// Load the fields for the MessageApplicationActionTag entity
		List<ApplicationEntityField> entityFields = messageApplicationActionTagService.getEntityFields();
		List<ApplicationEntityFieldResponse> responseFields = new ArrayList<ApplicationEntityFieldResponse>();
		// Convert the entity fields into response fields
		for(ApplicationEntityField entityField:entityFields)
		{
			responseFields.add(applicationEntityFieldService.convertModelToResponse(entityField));
		}
		response.setFields(responseFields);
		response.setRelatedEntitiesListData(messageApplicationActionTagService.relatedEntitesToListItems());
		if(id != null)
			response.setEntity(messageApplicationActionTagService.convertModelToResponse(messageApplicationActionTagService.findById(id)));
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
		return messageApplicationActionTagService.relatedEntitesToListItems();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#saveEditedEntityInstance(java.lang.Object)
	 */
	@Override
	protected EditActivityResponse<MessageApplicationActionTagResponse> saveEntityInstance(
			MessageApplicationActionTagRequest entityInstance) throws ApplicationException {
		MessageApplicationActionTag messageApplicationActionTag = messageApplicationActionTagService.create(entityInstance);
		return this.getEditActivityResponse(messageApplicationActionTag.getId());
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#saveEditedEntityInstance(java.lang.Object)
	 */
	@Override
	protected EditActivityResponse<MessageApplicationActionTagResponse> saveEditedEntityInstance(
			MessageApplicationActionTagRequest entityInstance) throws ApplicationException {
		MessageApplicationActionTag messageApplicationActionTag = messageApplicationActionTagService.update(entityInstance);
		return this.getEditActivityResponse(messageApplicationActionTag.getId());
	}
	
	@Override
	protected ListActivityResponse<MessageApplicationActionTagResponse> deleteEntityInstances(
			List<Integer> idsOfEntitiesToDelete) throws ApplicationException {
		for(Integer id: idsOfEntitiesToDelete)
		{
			 messageApplicationActionTagService.delete(id);
		}
		return this.getListActivityResponse(null);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getListActivityCode()
	 */
	@Override
	protected String getListActivityCode() throws ApplicationException {
		return messageApplicationActionTagService.getListActivityCode();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getEditActivityCode()
	 */
	@Override
	protected String getEditActivityCode() throws ApplicationException {
		return messageApplicationActionTagService.getEditActivityCode();
	}

}
