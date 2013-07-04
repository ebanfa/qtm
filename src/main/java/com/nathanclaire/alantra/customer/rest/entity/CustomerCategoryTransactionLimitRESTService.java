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

import com.nathanclaire.alantra.customer.model.CustomerCategoryTransactionLimit;
import com.nathanclaire.alantra.application.model.ApplicationEntityField;
import com.nathanclaire.alantra.customer.request.CustomerCategoryTransactionLimitRequest;
import com.nathanclaire.alantra.customer.response.CustomerCategoryTransactionLimitResponse;
import com.nathanclaire.alantra.application.response.ApplicationActivityResponse;
import com.nathanclaire.alantra.application.response.ApplicationEntityFieldResponse;
import com.nathanclaire.alantra.customer.service.entity.CustomerCategoryTransactionLimitService;
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
@Path("/customercategorytransactionlimit")
@Stateless
public class CustomerCategoryTransactionLimitRESTService extends BaseActivityRESTService<CustomerCategoryTransactionLimitResponse, CustomerCategoryTransactionLimitRequest> 
{
	@Inject
	CustomerCategoryTransactionLimitService customerCategoryTransactionLimitService;
	
	@Inject 
	ApplicationEntityFieldService applicationEntityFieldService;
	
	private Logger logger = LoggerFactory.getLogger(CustomerCategoryTransactionLimitRESTService.class);
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#populateListActivityResponse(com.nathanclaire.alantra.customer.response.CustomerCategoryTransactionLimitResponse, com.nathanclaire.alantra.base.response.ListActivityResponse, javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected ListActivityResponse<CustomerCategoryTransactionLimitResponse> populateListActivityResponse(
			ApplicationActivityResponse activity, ListActivityResponse<CustomerCategoryTransactionLimitResponse> response,
			MultivaluedMap<String, String> queryParameters) throws ApplicationException 
	{
		// Load the fields for the CustomerCategoryTransactionLimit entity
		List<ApplicationEntityFieldResponse> responseFields = new ArrayList<ApplicationEntityFieldResponse>();
		List<ApplicationEntityField> entityFields = customerCategoryTransactionLimitService.getEntityFields();
		for(ApplicationEntityField entityField:entityFields)
		{
			responseFields.add(applicationEntityFieldService.convertModelToResponse(entityField));
		}
		response.setFields(responseFields);
		// Load the list of CustomerCategoryTransactionLimit's
		List<CustomerCategoryTransactionLimitResponse> dataItems = new ArrayList<CustomerCategoryTransactionLimitResponse>();
		for (CustomerCategoryTransactionLimit item:customerCategoryTransactionLimitService.findAll(queryParameters))
		{
			dataItems.add(customerCategoryTransactionLimitService.convertModelToResponse(item));
		}
		response.setData(dataItems);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getInstance(java.lang.Integer)
	 */
	@Override
	protected EditActivityResponse<CustomerCategoryTransactionLimitResponse> populateEditActivityResponse(
			Integer id,	ApplicationActivityResponse activity, EditActivityResponse<CustomerCategoryTransactionLimitResponse> response) 
					throws ApplicationException {
		// Load the fields for the CustomerCategoryTransactionLimit entity
		List<ApplicationEntityField> entityFields = customerCategoryTransactionLimitService.getEntityFields();
		List<ApplicationEntityFieldResponse> responseFields = new ArrayList<ApplicationEntityFieldResponse>();
		// Convert the entity fields into response fields
		for(ApplicationEntityField entityField:entityFields)
		{
			responseFields.add(applicationEntityFieldService.convertModelToResponse(entityField));
		}
		response.setFields(responseFields);
		response.setRelatedEntitiesListData(customerCategoryTransactionLimitService.relatedEntitesToListItems());
		if(id != null)
			response.setEntity(customerCategoryTransactionLimitService.convertModelToResponse(customerCategoryTransactionLimitService.findById(id)));
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
		return customerCategoryTransactionLimitService.relatedEntitesToListItems();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#saveEditedEntityInstance(java.lang.Object)
	 */
	@Override
	protected EditActivityResponse<CustomerCategoryTransactionLimitResponse> saveEntityInstance(
			CustomerCategoryTransactionLimitRequest entityInstance) throws ApplicationException {
		CustomerCategoryTransactionLimit customerCategoryTransactionLimit = customerCategoryTransactionLimitService.create(entityInstance);
		return this.getEditActivityResponse(customerCategoryTransactionLimit.getId());
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#saveEditedEntityInstance(java.lang.Object)
	 */
	@Override
	protected EditActivityResponse<CustomerCategoryTransactionLimitResponse> saveEditedEntityInstance(
			CustomerCategoryTransactionLimitRequest entityInstance) throws ApplicationException {
		CustomerCategoryTransactionLimit customerCategoryTransactionLimit = customerCategoryTransactionLimitService.update(entityInstance);
		return this.getEditActivityResponse(customerCategoryTransactionLimit.getId());
	}
	
	@Override
	protected ListActivityResponse<CustomerCategoryTransactionLimitResponse> deleteEntityInstances(
			List<Integer> idsOfEntitiesToDelete) throws ApplicationException {
		for(Integer id: idsOfEntitiesToDelete)
		{
			 customerCategoryTransactionLimitService.delete(id);
		}
		return this.getListActivityResponse(null);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getListActivityCode()
	 */
	@Override
	protected String getListActivityCode() throws ApplicationException {
		return customerCategoryTransactionLimitService.getListActivityCode();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getEditActivityCode()
	 */
	@Override
	protected String getEditActivityCode() throws ApplicationException {
		return customerCategoryTransactionLimitService.getEditActivityCode();
	}

}
