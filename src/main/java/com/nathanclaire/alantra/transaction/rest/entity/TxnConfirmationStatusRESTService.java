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

import com.nathanclaire.alantra.transaction.model.TxnConfirmationStatus;
import com.nathanclaire.alantra.application.model.ApplicationEntityField;
import com.nathanclaire.alantra.transaction.request.TxnConfirmationStatusRequest;
import com.nathanclaire.alantra.transaction.response.TxnConfirmationStatusResponse;
import com.nathanclaire.alantra.application.response.ApplicationActivityResponse;
import com.nathanclaire.alantra.application.response.ApplicationEntityFieldResponse;
import com.nathanclaire.alantra.transaction.service.entity.TxnConfirmationStatusService;
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
@Path("/txnconfirmationstatus")
@Stateless
public class TxnConfirmationStatusRESTService extends BaseActivityRESTService<TxnConfirmationStatusResponse, TxnConfirmationStatusRequest> 
{
	@Inject
	TxnConfirmationStatusService txnConfirmationStatusService;
	
	@Inject 
	ApplicationEntityFieldService applicationEntityFieldService;
	
	private Logger logger = LoggerFactory.getLogger(TxnConfirmationStatusRESTService.class);
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#populateListActivityResponse(com.nathanclaire.alantra.txnConfirmationStatus.response.TxnConfirmationStatusResponse, com.nathanclaire.alantra.base.response.ListActivityResponse, javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected ListActivityResponse<TxnConfirmationStatusResponse> populateListActivityResponse(
			ApplicationActivityResponse activity, ListActivityResponse<TxnConfirmationStatusResponse> response,
			MultivaluedMap<String, String> queryParameters) throws ApplicationException 
	{
		// Load the fields for the TxnConfirmationStatus entity
		List<ApplicationEntityFieldResponse> responseFields = new ArrayList<ApplicationEntityFieldResponse>();
		List<ApplicationEntityField> entityFields = txnConfirmationStatusService.getEntityFields();
		for(ApplicationEntityField entityField:entityFields)
		{
			responseFields.add(applicationEntityFieldService.convertModelToResponse(entityField));
		}
		response.setFields(responseFields);
		// Load the list of TxnConfirmationStatus's
		List<TxnConfirmationStatusResponse> dataItems = new ArrayList<TxnConfirmationStatusResponse>();
		for (TxnConfirmationStatus item:txnConfirmationStatusService.findAll(queryParameters))
		{
			dataItems.add(txnConfirmationStatusService.convertModelToResponse(item));
		}
		response.setData(dataItems);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getInstance(java.lang.Integer)
	 */
	@Override
	protected EditActivityResponse<TxnConfirmationStatusResponse> populateEditActivityResponse(
			Integer id,	ApplicationActivityResponse activity, EditActivityResponse<TxnConfirmationStatusResponse> response) 
					throws ApplicationException {
		// Load the fields for the TxnConfirmationStatus entity
		List<ApplicationEntityField> entityFields = txnConfirmationStatusService.getEntityFields();
		List<ApplicationEntityFieldResponse> responseFields = new ArrayList<ApplicationEntityFieldResponse>();
		// Convert the entity fields into response fields
		for(ApplicationEntityField entityField:entityFields)
		{
			responseFields.add(applicationEntityFieldService.convertModelToResponse(entityField));
		}
		response.setFields(responseFields);
		response.setRelatedEntitiesListData(txnConfirmationStatusService.relatedEntitesToListItems());
		if(id != null)
			response.setEntity(txnConfirmationStatusService.convertModelToResponse(txnConfirmationStatusService.findById(id)));
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
		return txnConfirmationStatusService.relatedEntitesToListItems();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#saveEditedEntityInstance(java.lang.Object)
	 */
	@Override
	protected EditActivityResponse<TxnConfirmationStatusResponse> saveEntityInstance(
			TxnConfirmationStatusRequest entityInstance) throws ApplicationException {
		TxnConfirmationStatus txnConfirmationStatus = txnConfirmationStatusService.create(entityInstance);
		return this.getEditActivityResponse(txnConfirmationStatus.getId());
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#saveEditedEntityInstance(java.lang.Object)
	 */
	@Override
	protected EditActivityResponse<TxnConfirmationStatusResponse> saveEditedEntityInstance(
			TxnConfirmationStatusRequest entityInstance) throws ApplicationException {
		TxnConfirmationStatus txnConfirmationStatus = txnConfirmationStatusService.update(entityInstance);
		return this.getEditActivityResponse(txnConfirmationStatus.getId());
	}
	
	@Override
	protected ListActivityResponse<TxnConfirmationStatusResponse> deleteEntityInstances(
			List<Integer> idsOfEntitiesToDelete) throws ApplicationException {
		for(Integer id: idsOfEntitiesToDelete)
		{
			 txnConfirmationStatusService.delete(id);
		}
		return this.getListActivityResponse(null);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getListActivityCode()
	 */
	@Override
	protected String getListActivityCode() throws ApplicationException {
		return txnConfirmationStatusService.getListActivityCode();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getEditActivityCode()
	 */
	@Override
	protected String getEditActivityCode() throws ApplicationException {
		return txnConfirmationStatusService.getEditActivityCode();
	}

}
