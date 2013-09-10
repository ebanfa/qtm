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

import com.nathanclaire.alantra.rule.model.TransactionRuleAction;
import com.nathanclaire.alantra.application.model.ApplicationEntityField;
import com.nathanclaire.alantra.rule.request.TransactionRuleActionRequest;
import com.nathanclaire.alantra.rule.response.TransactionRuleActionResponse;
import com.nathanclaire.alantra.application.response.ApplicationActivityResponse;
import com.nathanclaire.alantra.application.response.ApplicationEntityFieldResponse;
import com.nathanclaire.alantra.rule.service.entity.TransactionRuleActionService;
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
@Path("/transactionruleaction")
@Stateless
public class TransactionRuleActionRESTService extends BaseActivityRESTService<TransactionRuleActionResponse, TransactionRuleActionRequest> 
{
	@Inject
	TransactionRuleActionService transactionRuleActionService;
	
	@Inject 
	ApplicationEntityFieldService applicationEntityFieldService;
	
	private Logger logger = LoggerFactory.getLogger(TransactionRuleActionRESTService.class);
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#populateListActivityResponse(com.nathanclaire.alantra.transactionRuleAction.response.TransactionRuleActionResponse, com.nathanclaire.alantra.base.response.ListActivityResponse, javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected ListActivityResponse<TransactionRuleActionResponse> populateListActivityResponse(
			ApplicationActivityResponse activity, ListActivityResponse<TransactionRuleActionResponse> response,
			MultivaluedMap<String, String> queryParameters) throws ApplicationException 
	{
		// Load the fields for the TransactionRuleAction entity
		List<ApplicationEntityFieldResponse> responseFields = new ArrayList<ApplicationEntityFieldResponse>();
		List<ApplicationEntityField> entityFields = transactionRuleActionService.getEntityFields();
		for(ApplicationEntityField entityField:entityFields)
		{
			responseFields.add(applicationEntityFieldService.convertModelToResponse(entityField));
		}
		response.setFields(responseFields);
		// Load the list of TransactionRuleAction's
		List<TransactionRuleActionResponse> dataItems = new ArrayList<TransactionRuleActionResponse>();
		for (TransactionRuleAction item:transactionRuleActionService.findAll(queryParameters))
		{
			dataItems.add(transactionRuleActionService.convertModelToResponse(item));
		}
		response.setData(dataItems);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getInstance(java.lang.Integer)
	 */
	@Override
	protected EditActivityResponse<TransactionRuleActionResponse> populateEditActivityResponse(
			Integer id,	ApplicationActivityResponse activity, EditActivityResponse<TransactionRuleActionResponse> response) 
					throws ApplicationException {
		// Load the fields for the TransactionRuleAction entity
		List<ApplicationEntityField> entityFields = transactionRuleActionService.getEntityFields();
		List<ApplicationEntityFieldResponse> responseFields = new ArrayList<ApplicationEntityFieldResponse>();
		// Convert the entity fields into response fields
		for(ApplicationEntityField entityField:entityFields)
		{
			responseFields.add(applicationEntityFieldService.convertModelToResponse(entityField));
		}
		response.setFields(responseFields);
		response.setRelatedEntitiesListData(transactionRuleActionService.relatedEntitesToListItems());
		if(id != null)
			response.setEntity(transactionRuleActionService.convertModelToResponse(transactionRuleActionService.findById(id)));
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
		return transactionRuleActionService.relatedEntitesToListItems();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#saveEditedEntityInstance(java.lang.Object)
	 */
	@Override
	protected EditActivityResponse<TransactionRuleActionResponse> saveEntityInstance(
			TransactionRuleActionRequest entityInstance) throws ApplicationException {
		TransactionRuleAction transactionRuleAction = transactionRuleActionService.create(entityInstance);
		return this.getEditActivityResponse(transactionRuleAction.getId());
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#saveEditedEntityInstance(java.lang.Object)
	 */
	@Override
	protected EditActivityResponse<TransactionRuleActionResponse> saveEditedEntityInstance(
			TransactionRuleActionRequest entityInstance) throws ApplicationException {
		TransactionRuleAction transactionRuleAction = transactionRuleActionService.update(entityInstance);
		return this.getEditActivityResponse(transactionRuleAction.getId());
	}
	
	@Override
	protected ListActivityResponse<TransactionRuleActionResponse> deleteEntityInstances(
			List<Integer> idsOfEntitiesToDelete) throws ApplicationException {
		for(Integer id: idsOfEntitiesToDelete)
		{
			 transactionRuleActionService.delete(id);
		}
		return this.getListActivityResponse(null);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getListActivityCode()
	 */
	@Override
	protected String getListActivityCode() throws ApplicationException {
		return transactionRuleActionService.getListActivityCode();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getEditActivityCode()
	 */
	@Override
	protected String getEditActivityCode() throws ApplicationException {
		return transactionRuleActionService.getEditActivityCode();
	}

}
