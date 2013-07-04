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

import com.nathanclaire.alantra.customer.model.CustomerClassificationTransactionLimit;
import com.nathanclaire.alantra.application.model.ApplicationEntityField;
import com.nathanclaire.alantra.customer.request.CustomerClassificationTransactionLimitRequest;
import com.nathanclaire.alantra.customer.response.CustomerClassificationTransactionLimitResponse;
import com.nathanclaire.alantra.application.response.ApplicationActivityResponse;
import com.nathanclaire.alantra.application.response.ApplicationEntityFieldResponse;
import com.nathanclaire.alantra.customer.service.entity.CustomerClassificationTransactionLimitService;
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
@Path("/customerclassificationtransactionlimit")
@Stateless
public class CustomerClassificationTransactionLimitRESTService extends BaseActivityRESTService<CustomerClassificationTransactionLimitResponse, CustomerClassificationTransactionLimitRequest> 
{
	@Inject
	CustomerClassificationTransactionLimitService customerClassificationTransactionLimitService;
	
	@Inject 
	ApplicationEntityFieldService applicationEntityFieldService;
	
	private Logger logger = LoggerFactory.getLogger(CustomerClassificationTransactionLimitRESTService.class);
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#populateListActivityResponse(com.nathanclaire.alantra.customer.response.CustomerClassificationTransactionLimitResponse, com.nathanclaire.alantra.base.response.ListActivityResponse, javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected ListActivityResponse<CustomerClassificationTransactionLimitResponse> populateListActivityResponse(
			ApplicationActivityResponse activity, ListActivityResponse<CustomerClassificationTransactionLimitResponse> response,
			MultivaluedMap<String, String> queryParameters) throws ApplicationException 
	{
		// Load the fields for the CustomerClassificationTransactionLimit entity
		List<ApplicationEntityFieldResponse> responseFields = new ArrayList<ApplicationEntityFieldResponse>();
		List<ApplicationEntityField> entityFields = customerClassificationTransactionLimitService.getEntityFields();
		for(ApplicationEntityField entityField:entityFields)
		{
			responseFields.add(applicationEntityFieldService.convertModelToResponse(entityField));
		}
		response.setFields(responseFields);
		// Load the list of CustomerClassificationTransactionLimit's
		List<CustomerClassificationTransactionLimitResponse> dataItems = new ArrayList<CustomerClassificationTransactionLimitResponse>();
		for (CustomerClassificationTransactionLimit item:customerClassificationTransactionLimitService.findAll(queryParameters))
		{
			dataItems.add(customerClassificationTransactionLimitService.convertModelToResponse(item));
		}
		response.setData(dataItems);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getInstance(java.lang.Integer)
	 */
	@Override
	protected EditActivityResponse<CustomerClassificationTransactionLimitResponse> populateEditActivityResponse(
			Integer id,	ApplicationActivityResponse activity, EditActivityResponse<CustomerClassificationTransactionLimitResponse> response) 
					throws ApplicationException {
		// Load the fields for the CustomerClassificationTransactionLimit entity
		List<ApplicationEntityField> entityFields = customerClassificationTransactionLimitService.getEntityFields();
		List<ApplicationEntityFieldResponse> responseFields = new ArrayList<ApplicationEntityFieldResponse>();
		// Convert the entity fields into response fields
		for(ApplicationEntityField entityField:entityFields)
		{
			responseFields.add(applicationEntityFieldService.convertModelToResponse(entityField));
		}
		response.setFields(responseFields);
		response.setRelatedEntitiesListData(customerClassificationTransactionLimitService.relatedEntitesToListItems());
		if(id != null)
			response.setEntity(customerClassificationTransactionLimitService.convertModelToResponse(customerClassificationTransactionLimitService.findById(id)));
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
		return customerClassificationTransactionLimitService.relatedEntitesToListItems();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#saveEditedEntityInstance(java.lang.Object)
	 */
	@Override
	protected EditActivityResponse<CustomerClassificationTransactionLimitResponse> saveEntityInstance(
			CustomerClassificationTransactionLimitRequest entityInstance) throws ApplicationException {
		CustomerClassificationTransactionLimit customerClassificationTransactionLimit = customerClassificationTransactionLimitService.create(entityInstance);
		return this.getEditActivityResponse(customerClassificationTransactionLimit.getId());
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#saveEditedEntityInstance(java.lang.Object)
	 */
	@Override
	protected EditActivityResponse<CustomerClassificationTransactionLimitResponse> saveEditedEntityInstance(
			CustomerClassificationTransactionLimitRequest entityInstance) throws ApplicationException {
		CustomerClassificationTransactionLimit customerClassificationTransactionLimit = customerClassificationTransactionLimitService.update(entityInstance);
		return this.getEditActivityResponse(customerClassificationTransactionLimit.getId());
	}
	
	@Override
	protected ListActivityResponse<CustomerClassificationTransactionLimitResponse> deleteEntityInstances(
			List<Integer> idsOfEntitiesToDelete) throws ApplicationException {
		for(Integer id: idsOfEntitiesToDelete)
		{
			 customerClassificationTransactionLimitService.delete(id);
		}
		return this.getListActivityResponse(null);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getListActivityCode()
	 */
	@Override
	protected String getListActivityCode() throws ApplicationException {
		return customerClassificationTransactionLimitService.getListActivityCode();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getEditActivityCode()
	 */
	@Override
	protected String getEditActivityCode() throws ApplicationException {
		return customerClassificationTransactionLimitService.getEditActivityCode();
	}

}
