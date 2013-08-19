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

import com.nathanclaire.alantra.customer.model.CustomerTypeCommsChannel;
import com.nathanclaire.alantra.application.model.ApplicationEntityField;
import com.nathanclaire.alantra.customer.request.CustomerTypeNotificationChannelRequest;
import com.nathanclaire.alantra.customer.response.CustomerTypeNotificationChannelResponse;
import com.nathanclaire.alantra.application.response.ApplicationActivityResponse;
import com.nathanclaire.alantra.application.response.ApplicationEntityFieldResponse;
import com.nathanclaire.alantra.customer.service.entity.CustomerTypeNotificationChannelService;
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
@Path("/customertypenotificationchannel")
@Stateless
public class CustomerTypeNotificationChannelRESTService extends BaseActivityRESTService<CustomerTypeNotificationChannelResponse, CustomerTypeNotificationChannelRequest> 
{
	@Inject
	CustomerTypeNotificationChannelService customerTypeNotificationChannelService;
	
	@Inject 
	ApplicationEntityFieldService applicationEntityFieldService;
	
	private Logger logger = LoggerFactory.getLogger(CustomerTypeNotificationChannelRESTService.class);
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#populateListActivityResponse(com.nathanclaire.alantra.customer.response.CustomerTypeNotificationChannelResponse, com.nathanclaire.alantra.base.response.ListActivityResponse, javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected ListActivityResponse<CustomerTypeNotificationChannelResponse> populateListActivityResponse(
			ApplicationActivityResponse activity, ListActivityResponse<CustomerTypeNotificationChannelResponse> response,
			MultivaluedMap<String, String> queryParameters) throws ApplicationException 
	{
		// Load the fields for the CustomerTypeNotificationChannel entity
		List<ApplicationEntityFieldResponse> responseFields = new ArrayList<ApplicationEntityFieldResponse>();
		List<ApplicationEntityField> entityFields = customerTypeNotificationChannelService.getEntityFields();
		for(ApplicationEntityField entityField:entityFields)
		{
			responseFields.add(applicationEntityFieldService.convertModelToResponse(entityField));
		}
		response.setFields(responseFields);
		// Load the list of CustomerTypeNotificationChannel's
		List<CustomerTypeNotificationChannelResponse> dataItems = new ArrayList<CustomerTypeNotificationChannelResponse>();
		for (CustomerTypeCommsChannel item:customerTypeNotificationChannelService.findAll(queryParameters))
		{
			dataItems.add(customerTypeNotificationChannelService.convertModelToResponse(item));
		}
		response.setData(dataItems);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getInstance(java.lang.Integer)
	 */
	@Override
	protected EditActivityResponse<CustomerTypeNotificationChannelResponse> populateEditActivityResponse(
			Integer id,	ApplicationActivityResponse activity, EditActivityResponse<CustomerTypeNotificationChannelResponse> response) 
					throws ApplicationException {
		// Load the fields for the CustomerTypeNotificationChannel entity
		List<ApplicationEntityField> entityFields = customerTypeNotificationChannelService.getEntityFields();
		List<ApplicationEntityFieldResponse> responseFields = new ArrayList<ApplicationEntityFieldResponse>();
		// Convert the entity fields into response fields
		for(ApplicationEntityField entityField:entityFields)
		{
			responseFields.add(applicationEntityFieldService.convertModelToResponse(entityField));
		}
		response.setFields(responseFields);
		response.setRelatedEntitiesListData(customerTypeNotificationChannelService.relatedEntitesToListItems());
		if(id != null)
			response.setEntity(customerTypeNotificationChannelService.convertModelToResponse(customerTypeNotificationChannelService.findById(id)));
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
		return customerTypeNotificationChannelService.relatedEntitesToListItems();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#saveEditedEntityInstance(java.lang.Object)
	 */
	@Override
	protected EditActivityResponse<CustomerTypeNotificationChannelResponse> saveEntityInstance(
			CustomerTypeNotificationChannelRequest entityInstance) throws ApplicationException {
		CustomerTypeCommsChannel customerTypeCommsChannel = customerTypeNotificationChannelService.create(entityInstance);
		return this.getEditActivityResponse(customerTypeCommsChannel.getId());
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#saveEditedEntityInstance(java.lang.Object)
	 */
	@Override
	protected EditActivityResponse<CustomerTypeNotificationChannelResponse> saveEditedEntityInstance(
			CustomerTypeNotificationChannelRequest entityInstance) throws ApplicationException {
		CustomerTypeCommsChannel customerTypeCommsChannel = customerTypeNotificationChannelService.update(entityInstance);
		return this.getEditActivityResponse(customerTypeCommsChannel.getId());
	}
	
	@Override
	protected ListActivityResponse<CustomerTypeNotificationChannelResponse> deleteEntityInstances(
			List<Integer> idsOfEntitiesToDelete) throws ApplicationException {
		for(Integer id: idsOfEntitiesToDelete)
		{
			 customerTypeNotificationChannelService.delete(id);
		}
		return this.getListActivityResponse(null);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getListActivityCode()
	 */
	@Override
	protected String getListActivityCode() throws ApplicationException {
		return customerTypeNotificationChannelService.getListActivityCode();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getEditActivityCode()
	 */
	@Override
	protected String getEditActivityCode() throws ApplicationException {
		return customerTypeNotificationChannelService.getEditActivityCode();
	}

}
