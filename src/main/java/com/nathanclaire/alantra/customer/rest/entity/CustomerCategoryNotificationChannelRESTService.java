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

import com.nathanclaire.alantra.customer.model.CustomerCategoryCommsChannel;
import com.nathanclaire.alantra.application.model.ApplicationEntityField;
import com.nathanclaire.alantra.customer.request.CustomerCategoryNotificationChannelRequest;
import com.nathanclaire.alantra.customer.response.CustomerCategoryNotificationChannelResponse;
import com.nathanclaire.alantra.application.response.ApplicationActivityResponse;
import com.nathanclaire.alantra.application.response.ApplicationEntityFieldResponse;
import com.nathanclaire.alantra.customer.service.entity.CustomerCategoryNotificationChannelService;
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
@Path("/customercategorynotificationchannel")
@Stateless
public class CustomerCategoryNotificationChannelRESTService extends BaseActivityRESTService<CustomerCategoryNotificationChannelResponse, CustomerCategoryNotificationChannelRequest> 
{
	@Inject
	CustomerCategoryNotificationChannelService customerCategoryNotificationChannelService;
	
	@Inject 
	ApplicationEntityFieldService applicationEntityFieldService;
	
	private Logger logger = LoggerFactory.getLogger(CustomerCategoryNotificationChannelRESTService.class);
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#populateListActivityResponse(com.nathanclaire.alantra.customer.response.CustomerCategoryNotificationChannelResponse, com.nathanclaire.alantra.base.response.ListActivityResponse, javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected ListActivityResponse<CustomerCategoryNotificationChannelResponse> populateListActivityResponse(
			ApplicationActivityResponse activity, ListActivityResponse<CustomerCategoryNotificationChannelResponse> response,
			MultivaluedMap<String, String> queryParameters) throws ApplicationException 
	{
		// Load the fields for the CustomerCategoryNotificationChannel entity
		List<ApplicationEntityFieldResponse> responseFields = new ArrayList<ApplicationEntityFieldResponse>();
		List<ApplicationEntityField> entityFields = customerCategoryNotificationChannelService.getEntityFields();
		for(ApplicationEntityField entityField:entityFields)
		{
			responseFields.add(applicationEntityFieldService.convertModelToResponse(entityField));
		}
		response.setFields(responseFields);
		// Load the list of CustomerCategoryNotificationChannel's
		List<CustomerCategoryNotificationChannelResponse> dataItems = new ArrayList<CustomerCategoryNotificationChannelResponse>();
		for (CustomerCategoryCommsChannel item:customerCategoryNotificationChannelService.findAll(queryParameters))
		{
			dataItems.add(customerCategoryNotificationChannelService.convertModelToResponse(item));
		}
		response.setData(dataItems);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getInstance(java.lang.Integer)
	 */
	@Override
	protected EditActivityResponse<CustomerCategoryNotificationChannelResponse> populateEditActivityResponse(
			Integer id,	ApplicationActivityResponse activity, EditActivityResponse<CustomerCategoryNotificationChannelResponse> response) 
					throws ApplicationException {
		// Load the fields for the CustomerCategoryNotificationChannel entity
		List<ApplicationEntityField> entityFields = customerCategoryNotificationChannelService.getEntityFields();
		List<ApplicationEntityFieldResponse> responseFields = new ArrayList<ApplicationEntityFieldResponse>();
		// Convert the entity fields into response fields
		for(ApplicationEntityField entityField:entityFields)
		{
			responseFields.add(applicationEntityFieldService.convertModelToResponse(entityField));
		}
		response.setFields(responseFields);
		response.setRelatedEntitiesListData(customerCategoryNotificationChannelService.relatedEntitesToListItems());
		if(id != null)
			response.setEntity(customerCategoryNotificationChannelService.convertModelToResponse(customerCategoryNotificationChannelService.findById(id)));
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
		return customerCategoryNotificationChannelService.relatedEntitesToListItems();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#saveEditedEntityInstance(java.lang.Object)
	 */
	@Override
	protected EditActivityResponse<CustomerCategoryNotificationChannelResponse> saveEntityInstance(
			CustomerCategoryNotificationChannelRequest entityInstance) throws ApplicationException {
		CustomerCategoryCommsChannel customerCategoryCommsChannel = customerCategoryNotificationChannelService.create(entityInstance);
		return this.getEditActivityResponse(customerCategoryCommsChannel.getId());
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#saveEditedEntityInstance(java.lang.Object)
	 */
	@Override
	protected EditActivityResponse<CustomerCategoryNotificationChannelResponse> saveEditedEntityInstance(
			CustomerCategoryNotificationChannelRequest entityInstance) throws ApplicationException {
		CustomerCategoryCommsChannel customerCategoryCommsChannel = customerCategoryNotificationChannelService.update(entityInstance);
		return this.getEditActivityResponse(customerCategoryCommsChannel.getId());
	}
	
	@Override
	protected ListActivityResponse<CustomerCategoryNotificationChannelResponse> deleteEntityInstances(
			List<Integer> idsOfEntitiesToDelete) throws ApplicationException {
		for(Integer id: idsOfEntitiesToDelete)
		{
			 customerCategoryNotificationChannelService.delete(id);
		}
		return this.getListActivityResponse(null);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getListActivityCode()
	 */
	@Override
	protected String getListActivityCode() throws ApplicationException {
		return customerCategoryNotificationChannelService.getListActivityCode();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getEditActivityCode()
	 */
	@Override
	protected String getEditActivityCode() throws ApplicationException {
		return customerCategoryNotificationChannelService.getEditActivityCode();
	}

}
