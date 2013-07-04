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

import com.nathanclaire.alantra.customer.model.CustomerTransactionLimit;
import com.nathanclaire.alantra.application.model.ApplicationEntityField;
import com.nathanclaire.alantra.customer.request.CustomerTransactionLimitRequest;
import com.nathanclaire.alantra.customer.response.CustomerTransactionLimitResponse;
import com.nathanclaire.alantra.application.response.ApplicationActivityResponse;
import com.nathanclaire.alantra.application.response.ApplicationEntityFieldResponse;
import com.nathanclaire.alantra.customer.service.entity.CustomerTransactionLimitService;
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
@Path("/customertransactionlimit")
@Stateless
public class CustomerTransactionLimitRESTService extends BaseActivityRESTService<CustomerTransactionLimitResponse, CustomerTransactionLimitRequest> 
{
	@Inject
	CustomerTransactionLimitService customerTransactionLimitService;
	
	@Inject 
	ApplicationEntityFieldService applicationEntityFieldService;
	
	private Logger logger = LoggerFactory.getLogger(CustomerTransactionLimitRESTService.class);
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#populateListActivityResponse(com.nathanclaire.alantra.customer.response.CustomerTransactionLimitResponse, com.nathanclaire.alantra.base.response.ListActivityResponse, javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected ListActivityResponse<CustomerTransactionLimitResponse> populateListActivityResponse(
			ApplicationActivityResponse activity, ListActivityResponse<CustomerTransactionLimitResponse> response,
			MultivaluedMap<String, String> queryParameters) throws ApplicationException 
	{
		// Load the fields for the CustomerTransactionLimit entity
		List<ApplicationEntityFieldResponse> responseFields = new ArrayList<ApplicationEntityFieldResponse>();
		List<ApplicationEntityField> entityFields = customerTransactionLimitService.getEntityFields();
		for(ApplicationEntityField entityField:entityFields)
		{
			responseFields.add(applicationEntityFieldService.convertModelToResponse(entityField));
		}
		response.setFields(responseFields);
		// Load the list of CustomerTransactionLimit's
		List<CustomerTransactionLimitResponse> dataItems = new ArrayList<CustomerTransactionLimitResponse>();
		for (CustomerTransactionLimit item:customerTransactionLimitService.findAll(queryParameters))
		{
			dataItems.add(customerTransactionLimitService.convertModelToResponse(item));
		}
		response.setData(dataItems);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getInstance(java.lang.Integer)
	 */
	@Override
	protected EditActivityResponse<CustomerTransactionLimitResponse> populateEditActivityResponse(
			Integer id,	ApplicationActivityResponse activity, EditActivityResponse<CustomerTransactionLimitResponse> response) 
					throws ApplicationException {
		// Load the fields for the CustomerTransactionLimit entity
		List<ApplicationEntityField> entityFields = customerTransactionLimitService.getEntityFields();
		List<ApplicationEntityFieldResponse> responseFields = new ArrayList<ApplicationEntityFieldResponse>();
		// Convert the entity fields into response fields
		for(ApplicationEntityField entityField:entityFields)
		{
			responseFields.add(applicationEntityFieldService.convertModelToResponse(entityField));
		}
		response.setFields(responseFields);
		response.setRelatedEntitiesListData(customerTransactionLimitService.relatedEntitesToListItems());
		if(id != null)
			response.setEntity(customerTransactionLimitService.convertModelToResponse(customerTransactionLimitService.findById(id)));
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
		return customerTransactionLimitService.relatedEntitesToListItems();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#saveEditedEntityInstance(java.lang.Object)
	 */
	@Override
	protected EditActivityResponse<CustomerTransactionLimitResponse> saveEntityInstance(
			CustomerTransactionLimitRequest entityInstance) throws ApplicationException {
		CustomerTransactionLimit customerTransactionLimit = customerTransactionLimitService.create(entityInstance);
		return this.getEditActivityResponse(customerTransactionLimit.getId());
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#saveEditedEntityInstance(java.lang.Object)
	 */
	@Override
	protected EditActivityResponse<CustomerTransactionLimitResponse> saveEditedEntityInstance(
			CustomerTransactionLimitRequest entityInstance) throws ApplicationException {
		CustomerTransactionLimit customerTransactionLimit = customerTransactionLimitService.update(entityInstance);
		return this.getEditActivityResponse(customerTransactionLimit.getId());
	}
	
	@Override
	protected ListActivityResponse<CustomerTransactionLimitResponse> deleteEntityInstances(
			List<Integer> idsOfEntitiesToDelete) throws ApplicationException {
		for(Integer id: idsOfEntitiesToDelete)
		{
			 customerTransactionLimitService.delete(id);
		}
		return this.getListActivityResponse(null);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getListActivityCode()
	 */
	@Override
	protected String getListActivityCode() throws ApplicationException {
		return customerTransactionLimitService.getListActivityCode();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getEditActivityCode()
	 */
	@Override
	protected String getEditActivityCode() throws ApplicationException {
		return customerTransactionLimitService.getEditActivityCode();
	}

}
