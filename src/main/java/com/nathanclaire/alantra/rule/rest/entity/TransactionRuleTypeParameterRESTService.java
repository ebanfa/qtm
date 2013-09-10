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

import com.nathanclaire.alantra.rule.model.TransactionRuleTypeParameter;
import com.nathanclaire.alantra.application.model.ApplicationEntityField;
import com.nathanclaire.alantra.rule.request.TransactionRuleTypeParameterRequest;
import com.nathanclaire.alantra.rule.response.TransactionRuleTypeParameterResponse;
import com.nathanclaire.alantra.application.response.ApplicationActivityResponse;
import com.nathanclaire.alantra.application.response.ApplicationEntityFieldResponse;
import com.nathanclaire.alantra.rule.service.entity.TransactionRuleTypeParameterService;
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
@Path("/transactionruletypeparameter")
@Stateless
public class TransactionRuleTypeParameterRESTService extends BaseActivityRESTService<TransactionRuleTypeParameterResponse, TransactionRuleTypeParameterRequest> 
{
	@Inject
	TransactionRuleTypeParameterService transactionRuleTypeParameterService;
	
	@Inject 
	ApplicationEntityFieldService applicationEntityFieldService;
	
	private Logger logger = LoggerFactory.getLogger(TransactionRuleTypeParameterRESTService.class);
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#populateListActivityResponse(com.nathanclaire.alantra.transactionRuleTypeParameter.response.TransactionRuleTypeParameterResponse, com.nathanclaire.alantra.base.response.ListActivityResponse, javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected ListActivityResponse<TransactionRuleTypeParameterResponse> populateListActivityResponse(
			ApplicationActivityResponse activity, ListActivityResponse<TransactionRuleTypeParameterResponse> response,
			MultivaluedMap<String, String> queryParameters) throws ApplicationException 
	{
		// Load the fields for the TransactionRuleTypeParameter entity
		List<ApplicationEntityFieldResponse> responseFields = new ArrayList<ApplicationEntityFieldResponse>();
		List<ApplicationEntityField> entityFields = transactionRuleTypeParameterService.getEntityFields();
		for(ApplicationEntityField entityField:entityFields)
		{
			responseFields.add(applicationEntityFieldService.convertModelToResponse(entityField));
		}
		response.setFields(responseFields);
		// Load the list of TransactionRuleTypeParameter's
		List<TransactionRuleTypeParameterResponse> dataItems = new ArrayList<TransactionRuleTypeParameterResponse>();
		for (TransactionRuleTypeParameter item:transactionRuleTypeParameterService.findAll(queryParameters))
		{
			dataItems.add(transactionRuleTypeParameterService.convertModelToResponse(item));
		}
		response.setData(dataItems);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getInstance(java.lang.Integer)
	 */
	@Override
	protected EditActivityResponse<TransactionRuleTypeParameterResponse> populateEditActivityResponse(
			Integer id,	ApplicationActivityResponse activity, EditActivityResponse<TransactionRuleTypeParameterResponse> response) 
					throws ApplicationException {
		// Load the fields for the TransactionRuleTypeParameter entity
		List<ApplicationEntityField> entityFields = transactionRuleTypeParameterService.getEntityFields();
		List<ApplicationEntityFieldResponse> responseFields = new ArrayList<ApplicationEntityFieldResponse>();
		// Convert the entity fields into response fields
		for(ApplicationEntityField entityField:entityFields)
		{
			responseFields.add(applicationEntityFieldService.convertModelToResponse(entityField));
		}
		response.setFields(responseFields);
		response.setRelatedEntitiesListData(transactionRuleTypeParameterService.relatedEntitesToListItems());
		if(id != null)
			response.setEntity(transactionRuleTypeParameterService.convertModelToResponse(transactionRuleTypeParameterService.findById(id)));
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
		return transactionRuleTypeParameterService.relatedEntitesToListItems();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#saveEditedEntityInstance(java.lang.Object)
	 */
	@Override
	protected EditActivityResponse<TransactionRuleTypeParameterResponse> saveEntityInstance(
			TransactionRuleTypeParameterRequest entityInstance) throws ApplicationException {
		TransactionRuleTypeParameter transactionRuleTypeParameter = transactionRuleTypeParameterService.create(entityInstance);
		return this.getEditActivityResponse(transactionRuleTypeParameter.getId());
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#saveEditedEntityInstance(java.lang.Object)
	 */
	@Override
	protected EditActivityResponse<TransactionRuleTypeParameterResponse> saveEditedEntityInstance(
			TransactionRuleTypeParameterRequest entityInstance) throws ApplicationException {
		TransactionRuleTypeParameter transactionRuleTypeParameter = transactionRuleTypeParameterService.update(entityInstance);
		return this.getEditActivityResponse(transactionRuleTypeParameter.getId());
	}
	
	@Override
	protected ListActivityResponse<TransactionRuleTypeParameterResponse> deleteEntityInstances(
			List<Integer> idsOfEntitiesToDelete) throws ApplicationException {
		for(Integer id: idsOfEntitiesToDelete)
		{
			 transactionRuleTypeParameterService.delete(id);
		}
		return this.getListActivityResponse(null);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getListActivityCode()
	 */
	@Override
	protected String getListActivityCode() throws ApplicationException {
		return transactionRuleTypeParameterService.getListActivityCode();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getEditActivityCode()
	 */
	@Override
	protected String getEditActivityCode() throws ApplicationException {
		return transactionRuleTypeParameterService.getEditActivityCode();
	}

}
