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

import com.nathanclaire.alantra.customer.model.CustomerTypeTransactionLimit;
import com.nathanclaire.alantra.application.model.ApplicationEntityField;
import com.nathanclaire.alantra.customer.request.CustomerTypeTransactionLimitRequest;
import com.nathanclaire.alantra.customer.response.CustomerTypeTransactionLimitResponse;
import com.nathanclaire.alantra.application.response.ApplicationActivityResponse;
import com.nathanclaire.alantra.application.response.ApplicationEntityFieldResponse;
import com.nathanclaire.alantra.customer.service.entity.CustomerTypeTransactionLimitService;
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
@Path("/customertypetransactionlimit")
@Stateless
public class CustomerTypeTransactionLimitRESTService extends BaseActivityRESTService<CustomerTypeTransactionLimitResponse, CustomerTypeTransactionLimitRequest> 
{
	@Inject
	CustomerTypeTransactionLimitService customerTypeTransactionLimitService;
	
	@Inject 
	ApplicationEntityFieldService applicationEntityFieldService;
	
	private Logger logger = LoggerFactory.getLogger(CustomerTypeTransactionLimitRESTService.class);
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#populateListActivityResponse(com.nathanclaire.alantra.customer.response.CustomerTypeTransactionLimitResponse, com.nathanclaire.alantra.base.response.ListActivityResponse, javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected ListActivityResponse<CustomerTypeTransactionLimitResponse> populateListActivityResponse(
			ApplicationActivityResponse activity, ListActivityResponse<CustomerTypeTransactionLimitResponse> response,
			MultivaluedMap<String, String> queryParameters) throws ApplicationException 
	{
		// Load the fields for the CustomerTypeTransactionLimit entity
		List<ApplicationEntityFieldResponse> responseFields = new ArrayList<ApplicationEntityFieldResponse>();
		List<ApplicationEntityField> entityFields = customerTypeTransactionLimitService.getEntityFields();
		for(ApplicationEntityField entityField:entityFields)
		{
			responseFields.add(applicationEntityFieldService.convertModelToResponse(entityField));
		}
		response.setFields(responseFields);
		// Load the list of CustomerTypeTransactionLimit's
		List<CustomerTypeTransactionLimitResponse> dataItems = new ArrayList<CustomerTypeTransactionLimitResponse>();
		for (CustomerTypeTransactionLimit item:customerTypeTransactionLimitService.findAll(queryParameters))
		{
			dataItems.add(customerTypeTransactionLimitService.convertModelToResponse(item));
		}
		response.setData(dataItems);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getInstance(java.lang.Integer)
	 */
	@Override
	protected EditActivityResponse<CustomerTypeTransactionLimitResponse> populateEditActivityResponse(
			Integer id,	ApplicationActivityResponse activity, EditActivityResponse<CustomerTypeTransactionLimitResponse> response) 
					throws ApplicationException {
		// Load the fields for the CustomerTypeTransactionLimit entity
		List<ApplicationEntityField> entityFields = customerTypeTransactionLimitService.getEntityFields();
		List<ApplicationEntityFieldResponse> responseFields = new ArrayList<ApplicationEntityFieldResponse>();
		// Convert the entity fields into response fields
		for(ApplicationEntityField entityField:entityFields)
		{
			responseFields.add(applicationEntityFieldService.convertModelToResponse(entityField));
		}
		response.setFields(responseFields);
		response.setRelatedEntitiesListData(customerTypeTransactionLimitService.relatedEntitesToListItems());
		if(id != null)
			response.setEntity(customerTypeTransactionLimitService.convertModelToResponse(customerTypeTransactionLimitService.findById(id)));
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
		return customerTypeTransactionLimitService.relatedEntitesToListItems();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#saveEditedEntityInstance(java.lang.Object)
	 */
	@Override
	protected EditActivityResponse<CustomerTypeTransactionLimitResponse> saveEntityInstance(
			CustomerTypeTransactionLimitRequest entityInstance) throws ApplicationException {
		CustomerTypeTransactionLimit customerTypeTransactionLimit = customerTypeTransactionLimitService.create(entityInstance);
		return this.getEditActivityResponse(customerTypeTransactionLimit.getId());
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#saveEditedEntityInstance(java.lang.Object)
	 */
	@Override
	protected EditActivityResponse<CustomerTypeTransactionLimitResponse> saveEditedEntityInstance(
			CustomerTypeTransactionLimitRequest entityInstance) throws ApplicationException {
		CustomerTypeTransactionLimit customerTypeTransactionLimit = customerTypeTransactionLimitService.update(entityInstance);
		return this.getEditActivityResponse(customerTypeTransactionLimit.getId());
	}
	
	@Override
	protected ListActivityResponse<CustomerTypeTransactionLimitResponse> deleteEntityInstances(
			List<Integer> idsOfEntitiesToDelete) throws ApplicationException {
		for(Integer id: idsOfEntitiesToDelete)
		{
			 customerTypeTransactionLimitService.delete(id);
		}
		return this.getListActivityResponse(null);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getListActivityCode()
	 */
	@Override
	protected String getListActivityCode() throws ApplicationException {
		return customerTypeTransactionLimitService.getListActivityCode();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getEditActivityCode()
	 */
	@Override
	protected String getEditActivityCode() throws ApplicationException {
		return customerTypeTransactionLimitService.getEditActivityCode();
	}

}
