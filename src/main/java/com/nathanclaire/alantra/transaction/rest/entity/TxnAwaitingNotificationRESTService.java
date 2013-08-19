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

import com.nathanclaire.alantra.transaction.model.TxnAwaitingNotification;
import com.nathanclaire.alantra.application.model.ApplicationEntityField;
import com.nathanclaire.alantra.transaction.request.TxnAwaitingNotificationRequest;
import com.nathanclaire.alantra.transaction.response.TxnAwaitingNotificationResponse;
import com.nathanclaire.alantra.application.response.ApplicationActivityResponse;
import com.nathanclaire.alantra.application.response.ApplicationEntityFieldResponse;
import com.nathanclaire.alantra.transaction.service.entity.TxnAwaitingNotificationService;
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
@Path("/txnawaitingnotification")
@Stateless
public class TxnAwaitingNotificationRESTService extends BaseActivityRESTService<TxnAwaitingNotificationResponse, TxnAwaitingNotificationRequest> 
{
	@Inject
	TxnAwaitingNotificationService txnAwaitingNotificationService;
	
	@Inject 
	ApplicationEntityFieldService applicationEntityFieldService;
	
	private Logger logger = LoggerFactory.getLogger(TxnAwaitingNotificationRESTService.class);
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#populateListActivityResponse(com.nathanclaire.alantra.txnAwaitingNotification.response.TxnAwaitingNotificationResponse, com.nathanclaire.alantra.base.response.ListActivityResponse, javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected ListActivityResponse<TxnAwaitingNotificationResponse> populateListActivityResponse(
			ApplicationActivityResponse activity, ListActivityResponse<TxnAwaitingNotificationResponse> response,
			MultivaluedMap<String, String> queryParameters) throws ApplicationException 
	{
		// Load the fields for the TxnAwaitingNotification entity
		List<ApplicationEntityFieldResponse> responseFields = new ArrayList<ApplicationEntityFieldResponse>();
		List<ApplicationEntityField> entityFields = txnAwaitingNotificationService.getEntityFields();
		for(ApplicationEntityField entityField:entityFields)
		{
			responseFields.add(applicationEntityFieldService.convertModelToResponse(entityField));
		}
		response.setFields(responseFields);
		// Load the list of TxnAwaitingNotification's
		List<TxnAwaitingNotificationResponse> dataItems = new ArrayList<TxnAwaitingNotificationResponse>();
		for (TxnAwaitingNotification item:txnAwaitingNotificationService.findAll(queryParameters))
		{
			dataItems.add(txnAwaitingNotificationService.convertModelToResponse(item));
		}
		response.setData(dataItems);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getInstance(java.lang.Integer)
	 */
	@Override
	protected EditActivityResponse<TxnAwaitingNotificationResponse> populateEditActivityResponse(
			Integer id,	ApplicationActivityResponse activity, EditActivityResponse<TxnAwaitingNotificationResponse> response) 
					throws ApplicationException {
		// Load the fields for the TxnAwaitingNotification entity
		List<ApplicationEntityField> entityFields = txnAwaitingNotificationService.getEntityFields();
		List<ApplicationEntityFieldResponse> responseFields = new ArrayList<ApplicationEntityFieldResponse>();
		// Convert the entity fields into response fields
		for(ApplicationEntityField entityField:entityFields)
		{
			responseFields.add(applicationEntityFieldService.convertModelToResponse(entityField));
		}
		response.setFields(responseFields);
		response.setRelatedEntitiesListData(txnAwaitingNotificationService.relatedEntitesToListItems());
		if(id != null)
			response.setEntity(txnAwaitingNotificationService.convertModelToResponse(txnAwaitingNotificationService.findById(id)));
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
		return txnAwaitingNotificationService.relatedEntitesToListItems();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#saveEditedEntityInstance(java.lang.Object)
	 */
	@Override
	protected EditActivityResponse<TxnAwaitingNotificationResponse> saveEntityInstance(
			TxnAwaitingNotificationRequest entityInstance) throws ApplicationException {
		TxnAwaitingNotification txnAwaitingNotification = txnAwaitingNotificationService.create(entityInstance);
		return this.getEditActivityResponse(txnAwaitingNotification.getId());
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#saveEditedEntityInstance(java.lang.Object)
	 */
	@Override
	protected EditActivityResponse<TxnAwaitingNotificationResponse> saveEditedEntityInstance(
			TxnAwaitingNotificationRequest entityInstance) throws ApplicationException {
		TxnAwaitingNotification txnAwaitingNotification = txnAwaitingNotificationService.update(entityInstance);
		return this.getEditActivityResponse(txnAwaitingNotification.getId());
	}
	
	@Override
	protected ListActivityResponse<TxnAwaitingNotificationResponse> deleteEntityInstances(
			List<Integer> idsOfEntitiesToDelete) throws ApplicationException {
		for(Integer id: idsOfEntitiesToDelete)
		{
			 txnAwaitingNotificationService.delete(id);
		}
		return this.getListActivityResponse(null);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getListActivityCode()
	 */
	@Override
	protected String getListActivityCode() throws ApplicationException {
		return txnAwaitingNotificationService.getListActivityCode();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getEditActivityCode()
	 */
	@Override
	protected String getEditActivityCode() throws ApplicationException {
		return txnAwaitingNotificationService.getEditActivityCode();
	}

}
