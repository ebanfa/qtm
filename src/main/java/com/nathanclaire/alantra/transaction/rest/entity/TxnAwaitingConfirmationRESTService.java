/**
 * Alantra.
 */
package com.nathanclaire.alantra.transaction.rest.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.MultivaluedMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.transaction.model.TxnAwaitingConfirmation;
import com.nathanclaire.alantra.application.model.ApplicationEntityField;
import com.nathanclaire.alantra.transaction.request.TxnAwaitingConfirmationRequest;
import com.nathanclaire.alantra.transaction.response.TxnAwaitingConfirmationResponse;
import com.nathanclaire.alantra.application.response.ApplicationActivityResponse;
import com.nathanclaire.alantra.application.response.ApplicationEntityFieldResponse;
import com.nathanclaire.alantra.transaction.service.entity.TxnAwaitingConfirmationService;
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
@Path("/txnawaitingconfirmation")
@Stateless
public class TxnAwaitingConfirmationRESTService extends BaseActivityRESTService<TxnAwaitingConfirmationResponse, TxnAwaitingConfirmationRequest> 
{
	@Inject
	TxnAwaitingConfirmationService txnAwaitingConfirmationService;
	
	@Inject 
	ApplicationEntityFieldService applicationEntityFieldService;
	
	private Logger logger = LoggerFactory.getLogger(TxnAwaitingConfirmationRESTService.class);
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#populateListActivityResponse(com.nathanclaire.alantra.txnAwaitingConfirmation.response.TxnAwaitingConfirmationResponse, com.nathanclaire.alantra.base.response.ListActivityResponse, javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected ListActivityResponse<TxnAwaitingConfirmationResponse> populateListActivityResponse(
			ApplicationActivityResponse activity, ListActivityResponse<TxnAwaitingConfirmationResponse> response,
			MultivaluedMap<String, String> queryParameters) throws ApplicationException 
	{
		// Load the fields for the TxnAwaitingConfirmation entity
		List<ApplicationEntityFieldResponse> responseFields = new ArrayList<ApplicationEntityFieldResponse>();
		List<ApplicationEntityField> entityFields = txnAwaitingConfirmationService.getEntityFields();
		for(ApplicationEntityField entityField:entityFields)
		{
			responseFields.add(applicationEntityFieldService.convertModelToResponse(entityField));
		}
		response.setFields(responseFields);
		// Load the list of TxnAwaitingConfirmation's
		List<TxnAwaitingConfirmationResponse> dataItems = new ArrayList<TxnAwaitingConfirmationResponse>();
		for (TxnAwaitingConfirmation item:txnAwaitingConfirmationService.findAll(queryParameters))
		{
			dataItems.add(txnAwaitingConfirmationService.convertModelToResponse(item));
		}
		response.setData(dataItems);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getInstance(java.lang.Integer)
	 */
	@Override
	protected EditActivityResponse<TxnAwaitingConfirmationResponse> populateEditActivityResponse(
			Integer id,	ApplicationActivityResponse activity, EditActivityResponse<TxnAwaitingConfirmationResponse> response) 
					throws ApplicationException {
		// Load the fields for the TxnAwaitingConfirmation entity
		List<ApplicationEntityField> entityFields = txnAwaitingConfirmationService.getEntityFields();
		List<ApplicationEntityFieldResponse> responseFields = new ArrayList<ApplicationEntityFieldResponse>();
		// Convert the entity fields into response fields
		for(ApplicationEntityField entityField:entityFields)
		{
			responseFields.add(applicationEntityFieldService.convertModelToResponse(entityField));
		}
		response.setFields(responseFields);
		response.setRelatedEntitiesListData(txnAwaitingConfirmationService.relatedEntitesToListItems());
		if(id != null)
			response.setEntity(txnAwaitingConfirmationService.convertModelToResponse(txnAwaitingConfirmationService.findById(id)));
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
		return txnAwaitingConfirmationService.relatedEntitesToListItems();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#saveEditedEntityInstance(java.lang.Object)
	 */
	@Override
	protected EditActivityResponse<TxnAwaitingConfirmationResponse> saveEntityInstance(
			TxnAwaitingConfirmationRequest entityInstance) throws ApplicationException {
		TxnAwaitingConfirmation txnAwaitingConfirmation = txnAwaitingConfirmationService.create(entityInstance);
		return this.getEditActivityResponse(txnAwaitingConfirmation.getId());
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#saveEditedEntityInstance(java.lang.Object)
	 */
	@Override
	protected EditActivityResponse<TxnAwaitingConfirmationResponse> saveEditedEntityInstance(
			TxnAwaitingConfirmationRequest entityInstance) throws ApplicationException {
		TxnAwaitingConfirmation txnAwaitingConfirmation = txnAwaitingConfirmationService.update(entityInstance);
		return this.getEditActivityResponse(txnAwaitingConfirmation.getId());
	}
	
	@Override
	protected ListActivityResponse<TxnAwaitingConfirmationResponse> deleteEntityInstances(
			List<Integer> idsOfEntitiesToDelete) throws ApplicationException {
		for(Integer id: idsOfEntitiesToDelete)
		{
			 txnAwaitingConfirmationService.delete(id);
		}
		return this.getListActivityResponse(null);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getListActivityCode()
	 */
	@Override
	protected String getListActivityCode() throws ApplicationException {
		return txnAwaitingConfirmationService.getListActivityCode();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getEditActivityCode()
	 */
	@Override
	protected String getEditActivityCode() throws ApplicationException {
		return txnAwaitingConfirmationService.getEditActivityCode();
	}

}
