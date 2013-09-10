/**
 * Alantra.
 */
package com.nathanclaire.alantra.channel.rest.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.MultivaluedMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.channel.model.ChannelPipelineHandler;
import com.nathanclaire.alantra.application.model.ApplicationEntityField;
import com.nathanclaire.alantra.channel.request.ChannelPipelineHandlerRequest;
import com.nathanclaire.alantra.channel.response.ChannelPipelineHandlerResponse;
import com.nathanclaire.alantra.application.response.ApplicationActivityResponse;
import com.nathanclaire.alantra.application.response.ApplicationEntityFieldResponse;
import com.nathanclaire.alantra.channel.service.entity.ChannelPipelineHandlerService;
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
@Path("/channelpipelinehandler")
@Stateless
public class ChannelPipelineHandlerRESTService extends BaseActivityRESTService<ChannelPipelineHandlerResponse, ChannelPipelineHandlerRequest> 
{
	@Inject
	ChannelPipelineHandlerService channelPipelineHandlerService;
	
	@Inject 
	ApplicationEntityFieldService applicationEntityFieldService;
	
	private Logger logger = LoggerFactory.getLogger(ChannelPipelineHandlerRESTService.class);
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#populateListActivityResponse(com.nathanclaire.alantra.channelPipelineHandler.response.ChannelPipelineHandlerResponse, com.nathanclaire.alantra.base.response.ListActivityResponse, javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected ListActivityResponse<ChannelPipelineHandlerResponse> populateListActivityResponse(
			ApplicationActivityResponse activity, ListActivityResponse<ChannelPipelineHandlerResponse> response,
			MultivaluedMap<String, String> queryParameters) throws ApplicationException 
	{
		// Load the fields for the ChannelPipelineHandler entity
		List<ApplicationEntityFieldResponse> responseFields = new ArrayList<ApplicationEntityFieldResponse>();
		List<ApplicationEntityField> entityFields = channelPipelineHandlerService.getEntityFields();
		for(ApplicationEntityField entityField:entityFields)
		{
			responseFields.add(applicationEntityFieldService.convertModelToResponse(entityField));
		}
		response.setFields(responseFields);
		// Load the list of ChannelPipelineHandler's
		List<ChannelPipelineHandlerResponse> dataItems = new ArrayList<ChannelPipelineHandlerResponse>();
		for (ChannelPipelineHandler item:channelPipelineHandlerService.findAll(queryParameters))
		{
			dataItems.add(channelPipelineHandlerService.convertModelToResponse(item));
		}
		response.setData(dataItems);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getInstance(java.lang.Integer)
	 */
	@Override
	protected EditActivityResponse<ChannelPipelineHandlerResponse> populateEditActivityResponse(
			Integer id,	ApplicationActivityResponse activity, EditActivityResponse<ChannelPipelineHandlerResponse> response) 
					throws ApplicationException {
		// Load the fields for the ChannelPipelineHandler entity
		List<ApplicationEntityField> entityFields = channelPipelineHandlerService.getEntityFields();
		List<ApplicationEntityFieldResponse> responseFields = new ArrayList<ApplicationEntityFieldResponse>();
		// Convert the entity fields into response fields
		for(ApplicationEntityField entityField:entityFields)
		{
			responseFields.add(applicationEntityFieldService.convertModelToResponse(entityField));
		}
		response.setFields(responseFields);
		response.setRelatedEntitiesListData(channelPipelineHandlerService.relatedEntitesToListItems());
		if(id != null)
			response.setEntity(channelPipelineHandlerService.convertModelToResponse(channelPipelineHandlerService.findById(id)));
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
		return channelPipelineHandlerService.relatedEntitesToListItems();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#saveEditedEntityInstance(java.lang.Object)
	 */
	@Override
	protected EditActivityResponse<ChannelPipelineHandlerResponse> saveEntityInstance(
			ChannelPipelineHandlerRequest entityInstance) throws ApplicationException {
		ChannelPipelineHandler channelPipelineHandler = channelPipelineHandlerService.create(entityInstance);
		return this.getEditActivityResponse(channelPipelineHandler.getId());
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#saveEditedEntityInstance(java.lang.Object)
	 */
	@Override
	protected EditActivityResponse<ChannelPipelineHandlerResponse> saveEditedEntityInstance(
			ChannelPipelineHandlerRequest entityInstance) throws ApplicationException {
		ChannelPipelineHandler channelPipelineHandler = channelPipelineHandlerService.update(entityInstance);
		return this.getEditActivityResponse(channelPipelineHandler.getId());
	}
	
	@Override
	protected ListActivityResponse<ChannelPipelineHandlerResponse> deleteEntityInstances(
			List<Integer> idsOfEntitiesToDelete) throws ApplicationException {
		for(Integer id: idsOfEntitiesToDelete)
		{
			 channelPipelineHandlerService.delete(id);
		}
		return this.getListActivityResponse(null);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getListActivityCode()
	 */
	@Override
	protected String getListActivityCode() throws ApplicationException {
		return channelPipelineHandlerService.getListActivityCode();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getEditActivityCode()
	 */
	@Override
	protected String getEditActivityCode() throws ApplicationException {
		return channelPipelineHandlerService.getEditActivityCode();
	}

}
