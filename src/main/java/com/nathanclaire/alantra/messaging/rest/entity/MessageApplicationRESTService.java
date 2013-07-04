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

import com.nathanclaire.alantra.messaging.model.MessageApplication;
import com.nathanclaire.alantra.application.model.ApplicationEntityField;
import com.nathanclaire.alantra.messaging.request.MessageApplicationRequest;
import com.nathanclaire.alantra.messaging.response.MessageApplicationResponse;
import com.nathanclaire.alantra.application.response.ApplicationActivityResponse;
import com.nathanclaire.alantra.application.response.ApplicationEntityFieldResponse;
import com.nathanclaire.alantra.messaging.service.entity.MessageApplicationService;
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
@Path("/messageapplication")
@Stateless
public class MessageApplicationRESTService extends BaseActivityRESTService<MessageApplicationResponse, MessageApplicationRequest> 
{
	@Inject
	MessageApplicationService messageApplicationService;
	
	@Inject 
	ApplicationEntityFieldService applicationEntityFieldService;
	
	private Logger logger = LoggerFactory.getLogger(MessageApplicationRESTService.class);
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#populateListActivityResponse(com.nathanclaire.alantra.messaging.response.MessageApplicationResponse, com.nathanclaire.alantra.base.response.ListActivityResponse, javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected ListActivityResponse<MessageApplicationResponse> populateListActivityResponse(
			ApplicationActivityResponse activity, ListActivityResponse<MessageApplicationResponse> response,
			MultivaluedMap<String, String> queryParameters) throws ApplicationException 
	{
		// Load the fields for the MessageApplication entity
		List<ApplicationEntityFieldResponse> responseFields = new ArrayList<ApplicationEntityFieldResponse>();
		List<ApplicationEntityField> entityFields = messageApplicationService.getEntityFields();
		for(ApplicationEntityField entityField:entityFields)
		{
			responseFields.add(applicationEntityFieldService.convertModelToResponse(entityField));
		}
		response.setFields(responseFields);
		// Load the list of MessageApplication's
		List<MessageApplicationResponse> dataItems = new ArrayList<MessageApplicationResponse>();
		for (MessageApplication item:messageApplicationService.findAll(queryParameters))
		{
			dataItems.add(messageApplicationService.convertModelToResponse(item));
		}
		response.setData(dataItems);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getInstance(java.lang.Integer)
	 */
	@Override
	protected EditActivityResponse<MessageApplicationResponse> populateEditActivityResponse(
			Integer id,	ApplicationActivityResponse activity, EditActivityResponse<MessageApplicationResponse> response) 
					throws ApplicationException {
		// Load the fields for the MessageApplication entity
		List<ApplicationEntityField> entityFields = messageApplicationService.getEntityFields();
		List<ApplicationEntityFieldResponse> responseFields = new ArrayList<ApplicationEntityFieldResponse>();
		// Convert the entity fields into response fields
		for(ApplicationEntityField entityField:entityFields)
		{
			responseFields.add(applicationEntityFieldService.convertModelToResponse(entityField));
		}
		response.setFields(responseFields);
		response.setRelatedEntitiesListData(messageApplicationService.relatedEntitesToListItems());
		if(id != null)
			response.setEntity(messageApplicationService.convertModelToResponse(messageApplicationService.findById(id)));
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
		return messageApplicationService.relatedEntitesToListItems();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#saveEditedEntityInstance(java.lang.Object)
	 */
	@Override
	protected EditActivityResponse<MessageApplicationResponse> saveEntityInstance(
			MessageApplicationRequest entityInstance) throws ApplicationException {
		MessageApplication messageApplication = messageApplicationService.create(entityInstance);
		return this.getEditActivityResponse(messageApplication.getId());
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#saveEditedEntityInstance(java.lang.Object)
	 */
	@Override
	protected EditActivityResponse<MessageApplicationResponse> saveEditedEntityInstance(
			MessageApplicationRequest entityInstance) throws ApplicationException {
		MessageApplication messageApplication = messageApplicationService.update(entityInstance);
		return this.getEditActivityResponse(messageApplication.getId());
	}
	
	@Override
	protected ListActivityResponse<MessageApplicationResponse> deleteEntityInstances(
			List<Integer> idsOfEntitiesToDelete) throws ApplicationException {
		for(Integer id: idsOfEntitiesToDelete)
		{
			 messageApplicationService.delete(id);
		}
		return this.getListActivityResponse(null);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getListActivityCode()
	 */
	@Override
	protected String getListActivityCode() throws ApplicationException {
		return messageApplicationService.getListActivityCode();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getEditActivityCode()
	 */
	@Override
	protected String getEditActivityCode() throws ApplicationException {
		return messageApplicationService.getEditActivityCode();
	}

}
