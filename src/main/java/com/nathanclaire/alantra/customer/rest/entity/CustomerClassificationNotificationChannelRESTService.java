/**
 * 
 */
package com.nathanclaire.alantra.customer.rest.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.MultivaluedMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.customer.model.CustomerClassificationCommsChannel;
import com.nathanclaire.alantra.application.model.ApplicationEntityField;
import com.nathanclaire.alantra.customer.request.CustomerClassificationNotificationChannelRequest;
import com.nathanclaire.alantra.customer.response.CustomerClassificationNotificationChannelResponse;
import com.nathanclaire.alantra.application.response.ApplicationActivityResponse;
import com.nathanclaire.alantra.application.response.ApplicationEntityFieldResponse;
import com.nathanclaire.alantra.customer.service.entity.CustomerClassificationNotificationChannelService;
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
@Path("/customerclassificationnotificationchannel")
@Stateless
public class CustomerClassificationNotificationChannelRESTService extends BaseActivityRESTService<CustomerClassificationNotificationChannelResponse, CustomerClassificationNotificationChannelRequest> 
{
	@Inject
	CustomerClassificationNotificationChannelService customerClassificationNotificationChannelService;
	
	@Inject 
	ApplicationEntityFieldService applicationEntityFieldService;
	
	private Logger logger = LoggerFactory.getLogger(CustomerClassificationNotificationChannelRESTService.class);
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#populateListActivityResponse(com.nathanclaire.alantra.customer.response.CustomerClassificationNotificationChannelResponse, com.nathanclaire.alantra.base.response.ListActivityResponse, javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected ListActivityResponse<CustomerClassificationNotificationChannelResponse> populateListActivityResponse(
			ApplicationActivityResponse activity, ListActivityResponse<CustomerClassificationNotificationChannelResponse> response,
			MultivaluedMap<String, String> queryParameters) throws ApplicationException 
	{
		// Load the fields for the CustomerClassificationNotificationChannel entity
		List<ApplicationEntityFieldResponse> responseFields = new ArrayList<ApplicationEntityFieldResponse>();
		List<ApplicationEntityField> entityFields = customerClassificationNotificationChannelService.getEntityFields();
		for(ApplicationEntityField entityField:entityFields)
		{
			responseFields.add(applicationEntityFieldService.convertModelToResponse(entityField));
		}
		response.setFields(responseFields);
		// Load the list of CustomerClassificationNotificationChannel's
		List<CustomerClassificationNotificationChannelResponse> dataItems = new ArrayList<CustomerClassificationNotificationChannelResponse>();
		for (CustomerClassificationCommsChannel item:customerClassificationNotificationChannelService.findAll(queryParameters))
		{
			dataItems.add(customerClassificationNotificationChannelService.convertModelToResponse(item));
		}
		response.setData(dataItems);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getInstance(java.lang.Integer)
	 */
	@Override
	protected EditActivityResponse<CustomerClassificationNotificationChannelResponse> populateEditActivityResponse(
			Integer id,	ApplicationActivityResponse activity, EditActivityResponse<CustomerClassificationNotificationChannelResponse> response) 
					throws ApplicationException {
		// Load the fields for the CustomerClassificationNotificationChannel entity
		List<ApplicationEntityField> entityFields = customerClassificationNotificationChannelService.getEntityFields();
		List<ApplicationEntityFieldResponse> responseFields = new ArrayList<ApplicationEntityFieldResponse>();
		// Convert the entity fields into response fields
		for(ApplicationEntityField entityField:entityFields)
		{
			responseFields.add(applicationEntityFieldService.convertModelToResponse(entityField));
		}
		response.setFields(responseFields);
		response.setRelatedEntitiesListData(customerClassificationNotificationChannelService.relatedEntitesToListItems());
		if(id != null)
			response.setEntity(customerClassificationNotificationChannelService.convertModelToResponse(customerClassificationNotificationChannelService.findById(id)));
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
		return customerClassificationNotificationChannelService.relatedEntitesToListItems();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#saveEditedEntityInstance(java.lang.Object)
	 */
	@Override
	protected EditActivityResponse<CustomerClassificationNotificationChannelResponse> saveEntityInstance(
			CustomerClassificationNotificationChannelRequest entityInstance) throws ApplicationException {
		CustomerClassificationCommsChannel customerClassificationCommsChannel = customerClassificationNotificationChannelService.create(entityInstance);
		return this.getEditActivityResponse(customerClassificationCommsChannel.getId());
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#saveEditedEntityInstance(java.lang.Object)
	 */
	@Override
	protected EditActivityResponse<CustomerClassificationNotificationChannelResponse> saveEditedEntityInstance(
			CustomerClassificationNotificationChannelRequest entityInstance) throws ApplicationException {
		CustomerClassificationCommsChannel customerClassificationCommsChannel = customerClassificationNotificationChannelService.update(entityInstance);
		return this.getEditActivityResponse(customerClassificationCommsChannel.getId());
	}
	
	@Override
	protected ListActivityResponse<CustomerClassificationNotificationChannelResponse> deleteEntityInstances(
			List<Integer> idsOfEntitiesToDelete) throws ApplicationException {
		for(Integer id: idsOfEntitiesToDelete)
		{
			 customerClassificationNotificationChannelService.delete(id);
		}
		return this.getListActivityResponse(null);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getListActivityCode()
	 */
	@Override
	protected String getListActivityCode() throws ApplicationException {
		return customerClassificationNotificationChannelService.getListActivityCode();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getEditActivityCode()
	 */
	@Override
	protected String getEditActivityCode() throws ApplicationException {
		return customerClassificationNotificationChannelService.getEditActivityCode();
	}

}
