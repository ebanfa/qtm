/**
 * Alantra.
 */
package com.nathanclaire.alantra.rule.rest.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.MultivaluedMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.rule.model.TransactionRuleSpace;
import com.nathanclaire.alantra.application.model.ApplicationEntityField;
import com.nathanclaire.alantra.rule.request.TransactionRuleSpaceRequest;
import com.nathanclaire.alantra.rule.response.TransactionRuleSpaceResponse;
import com.nathanclaire.alantra.application.response.ApplicationActivityResponse;
import com.nathanclaire.alantra.application.response.ApplicationEntityFieldResponse;
import com.nathanclaire.alantra.rule.service.entity.TransactionRuleSpaceService;
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
@Path("/transactionrulespace")
@Stateless
public class TransactionRuleSpaceRESTService extends BaseActivityRESTService<TransactionRuleSpaceResponse, TransactionRuleSpaceRequest> 
{
	@Inject
	TransactionRuleSpaceService transactionRuleSpaceService;
	
	@Inject 
	ApplicationEntityFieldService applicationEntityFieldService;
	
	private Logger logger = LoggerFactory.getLogger(TransactionRuleSpaceRESTService.class);
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#populateListActivityResponse(com.nathanclaire.alantra.transactionRuleSpace.response.TransactionRuleSpaceResponse, com.nathanclaire.alantra.base.response.ListActivityResponse, javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected ListActivityResponse<TransactionRuleSpaceResponse> populateListActivityResponse(
			ApplicationActivityResponse activity, ListActivityResponse<TransactionRuleSpaceResponse> response,
			MultivaluedMap<String, String> queryParameters) throws ApplicationException 
	{
		// Load the fields for the TransactionRuleSpace entity
		List<ApplicationEntityFieldResponse> responseFields = new ArrayList<ApplicationEntityFieldResponse>();
		List<ApplicationEntityField> entityFields = transactionRuleSpaceService.getEntityFields();
		for(ApplicationEntityField entityField:entityFields)
		{
			responseFields.add(applicationEntityFieldService.convertModelToResponse(entityField));
		}
		response.setFields(responseFields);
		// Load the list of TransactionRuleSpace's
		List<TransactionRuleSpaceResponse> dataItems = new ArrayList<TransactionRuleSpaceResponse>();
		for (TransactionRuleSpace item:transactionRuleSpaceService.findAll(queryParameters))
		{
			dataItems.add(transactionRuleSpaceService.convertModelToResponse(item));
		}
		response.setData(dataItems);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getInstance(java.lang.Integer)
	 */
	@Override
	protected EditActivityResponse<TransactionRuleSpaceResponse> populateEditActivityResponse(
			Integer id,	ApplicationActivityResponse activity, EditActivityResponse<TransactionRuleSpaceResponse> response) 
					throws ApplicationException {
		// Load the fields for the TransactionRuleSpace entity
		List<ApplicationEntityField> entityFields = transactionRuleSpaceService.getEntityFields();
		List<ApplicationEntityFieldResponse> responseFields = new ArrayList<ApplicationEntityFieldResponse>();
		// Convert the entity fields into response fields
		for(ApplicationEntityField entityField:entityFields)
		{
			responseFields.add(applicationEntityFieldService.convertModelToResponse(entityField));
		}
		response.setFields(responseFields);
		response.setRelatedEntitiesListData(transactionRuleSpaceService.relatedEntitesToListItems());
		if(id != null)
			response.setEntity(transactionRuleSpaceService.convertModelToResponse(transactionRuleSpaceService.findById(id)));
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
		return transactionRuleSpaceService.relatedEntitesToListItems();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#saveEditedEntityInstance(java.lang.Object)
	 */
	@Override
	protected EditActivityResponse<TransactionRuleSpaceResponse> saveEntityInstance(
			TransactionRuleSpaceRequest entityInstance) throws ApplicationException {
		TransactionRuleSpace transactionRuleSpace = transactionRuleSpaceService.create(entityInstance);
		return this.getEditActivityResponse(transactionRuleSpace.getId());
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#saveEditedEntityInstance(java.lang.Object)
	 */
	@Override
	protected EditActivityResponse<TransactionRuleSpaceResponse> saveEditedEntityInstance(
			TransactionRuleSpaceRequest entityInstance) throws ApplicationException {
		TransactionRuleSpace transactionRuleSpace = transactionRuleSpaceService.update(entityInstance);
		return this.getEditActivityResponse(transactionRuleSpace.getId());
	}
	
	@Override
	protected ListActivityResponse<TransactionRuleSpaceResponse> deleteEntityInstances(
			List<Integer> idsOfEntitiesToDelete) throws ApplicationException {
		for(Integer id: idsOfEntitiesToDelete)
		{
			 transactionRuleSpaceService.delete(id);
		}
		return this.getListActivityResponse(null);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getListActivityCode()
	 */
	@Override
	protected String getListActivityCode() throws ApplicationException {
		return transactionRuleSpaceService.getListActivityCode();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getEditActivityCode()
	 */
	@Override
	protected String getEditActivityCode() throws ApplicationException {
		return transactionRuleSpaceService.getEditActivityCode();
	}

}
