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

import com.nathanclaire.alantra.rule.model.TransactionRuleCategory;
import com.nathanclaire.alantra.application.model.ApplicationEntityField;
import com.nathanclaire.alantra.rule.request.TransactionRuleCategoryRequest;
import com.nathanclaire.alantra.rule.response.TransactionRuleCategoryResponse;
import com.nathanclaire.alantra.application.response.ApplicationActivityResponse;
import com.nathanclaire.alantra.application.response.ApplicationEntityFieldResponse;
import com.nathanclaire.alantra.rule.service.entity.TransactionRuleCategoryService;
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
@Path("/transactionrulecategory")
@Stateless
public class TransactionRuleCategoryRESTService extends BaseActivityRESTService<TransactionRuleCategoryResponse, TransactionRuleCategoryRequest> 
{
	@Inject
	TransactionRuleCategoryService transactionRuleCategoryService;
	
	@Inject 
	ApplicationEntityFieldService applicationEntityFieldService;
	
	private Logger logger = LoggerFactory.getLogger(TransactionRuleCategoryRESTService.class);
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#populateListActivityResponse(com.nathanclaire.alantra.transactionRuleCategory.response.TransactionRuleCategoryResponse, com.nathanclaire.alantra.base.response.ListActivityResponse, javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected ListActivityResponse<TransactionRuleCategoryResponse> populateListActivityResponse(
			ApplicationActivityResponse activity, ListActivityResponse<TransactionRuleCategoryResponse> response,
			MultivaluedMap<String, String> queryParameters) throws ApplicationException 
	{
		// Load the fields for the TransactionRuleCategory entity
		List<ApplicationEntityFieldResponse> responseFields = new ArrayList<ApplicationEntityFieldResponse>();
		List<ApplicationEntityField> entityFields = transactionRuleCategoryService.getEntityFields();
		for(ApplicationEntityField entityField:entityFields)
		{
			responseFields.add(applicationEntityFieldService.convertModelToResponse(entityField));
		}
		response.setFields(responseFields);
		// Load the list of TransactionRuleCategory's
		List<TransactionRuleCategoryResponse> dataItems = new ArrayList<TransactionRuleCategoryResponse>();
		for (TransactionRuleCategory item:transactionRuleCategoryService.findAll(queryParameters))
		{
			dataItems.add(transactionRuleCategoryService.convertModelToResponse(item));
		}
		response.setData(dataItems);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getInstance(java.lang.Integer)
	 */
	@Override
	protected EditActivityResponse<TransactionRuleCategoryResponse> populateEditActivityResponse(
			Integer id,	ApplicationActivityResponse activity, EditActivityResponse<TransactionRuleCategoryResponse> response) 
					throws ApplicationException {
		// Load the fields for the TransactionRuleCategory entity
		List<ApplicationEntityField> entityFields = transactionRuleCategoryService.getEntityFields();
		List<ApplicationEntityFieldResponse> responseFields = new ArrayList<ApplicationEntityFieldResponse>();
		// Convert the entity fields into response fields
		for(ApplicationEntityField entityField:entityFields)
		{
			responseFields.add(applicationEntityFieldService.convertModelToResponse(entityField));
		}
		response.setFields(responseFields);
		response.setRelatedEntitiesListData(transactionRuleCategoryService.relatedEntitesToListItems());
		if(id != null)
			response.setEntity(transactionRuleCategoryService.convertModelToResponse(transactionRuleCategoryService.findById(id)));
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
		return transactionRuleCategoryService.relatedEntitesToListItems();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#saveEditedEntityInstance(java.lang.Object)
	 */
	@Override
	protected EditActivityResponse<TransactionRuleCategoryResponse> saveEntityInstance(
			TransactionRuleCategoryRequest entityInstance) throws ApplicationException {
		TransactionRuleCategory transactionRuleCategory = transactionRuleCategoryService.create(entityInstance);
		return this.getEditActivityResponse(transactionRuleCategory.getId());
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#saveEditedEntityInstance(java.lang.Object)
	 */
	@Override
	protected EditActivityResponse<TransactionRuleCategoryResponse> saveEditedEntityInstance(
			TransactionRuleCategoryRequest entityInstance) throws ApplicationException {
		TransactionRuleCategory transactionRuleCategory = transactionRuleCategoryService.update(entityInstance);
		return this.getEditActivityResponse(transactionRuleCategory.getId());
	}
	
	@Override
	protected ListActivityResponse<TransactionRuleCategoryResponse> deleteEntityInstances(
			List<Integer> idsOfEntitiesToDelete) throws ApplicationException {
		for(Integer id: idsOfEntitiesToDelete)
		{
			 transactionRuleCategoryService.delete(id);
		}
		return this.getListActivityResponse(null);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getListActivityCode()
	 */
	@Override
	protected String getListActivityCode() throws ApplicationException {
		return transactionRuleCategoryService.getListActivityCode();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getEditActivityCode()
	 */
	@Override
	protected String getEditActivityCode() throws ApplicationException {
		return transactionRuleCategoryService.getEditActivityCode();
	}

}
