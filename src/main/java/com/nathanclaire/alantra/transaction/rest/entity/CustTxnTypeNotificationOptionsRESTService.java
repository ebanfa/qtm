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

import com.nathanclaire.alantra.transaction.model.CustTxnTypeNotificationOptions;
import com.nathanclaire.alantra.application.model.ApplicationEntityField;
import com.nathanclaire.alantra.transaction.request.CustTxnTypeNotificationOptionsRequest;
import com.nathanclaire.alantra.transaction.response.CustTxnTypeNotificationOptionsResponse;
import com.nathanclaire.alantra.application.response.ApplicationActivityResponse;
import com.nathanclaire.alantra.application.response.ApplicationEntityFieldResponse;
import com.nathanclaire.alantra.transaction.service.entity.CustTxnTypeNotificationOptionsService;
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
@Path("/custtxntypenotificationoptions")
@Stateless
public class CustTxnTypeNotificationOptionsRESTService extends BaseActivityRESTService<CustTxnTypeNotificationOptionsResponse, CustTxnTypeNotificationOptionsRequest> 
{
	@Inject
	CustTxnTypeNotificationOptionsService custTxnTypeNotificationOptionsService;
	
	@Inject 
	ApplicationEntityFieldService applicationEntityFieldService;
	
	private Logger logger = LoggerFactory.getLogger(CustTxnTypeNotificationOptionsRESTService.class);
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#populateListActivityResponse(com.nathanclaire.alantra.custTxnTypeNotificationOptions.response.CustTxnTypeNotificationOptionsResponse, com.nathanclaire.alantra.base.response.ListActivityResponse, javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected ListActivityResponse<CustTxnTypeNotificationOptionsResponse> populateListActivityResponse(
			ApplicationActivityResponse activity, ListActivityResponse<CustTxnTypeNotificationOptionsResponse> response,
			MultivaluedMap<String, String> queryParameters) throws ApplicationException 
	{
		// Load the fields for the CustTxnTypeNotificationOptions entity
		List<ApplicationEntityFieldResponse> responseFields = new ArrayList<ApplicationEntityFieldResponse>();
		List<ApplicationEntityField> entityFields = custTxnTypeNotificationOptionsService.getEntityFields();
		for(ApplicationEntityField entityField:entityFields)
		{
			responseFields.add(applicationEntityFieldService.convertModelToResponse(entityField));
		}
		response.setFields(responseFields);
		// Load the list of CustTxnTypeNotificationOptions's
		List<CustTxnTypeNotificationOptionsResponse> dataItems = new ArrayList<CustTxnTypeNotificationOptionsResponse>();
		for (CustTxnTypeNotificationOptions item:custTxnTypeNotificationOptionsService.findAll(queryParameters))
		{
			dataItems.add(custTxnTypeNotificationOptionsService.convertModelToResponse(item));
		}
		response.setData(dataItems);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getInstance(java.lang.Integer)
	 */
	@Override
	protected EditActivityResponse<CustTxnTypeNotificationOptionsResponse> populateEditActivityResponse(
			Integer id,	ApplicationActivityResponse activity, EditActivityResponse<CustTxnTypeNotificationOptionsResponse> response) 
					throws ApplicationException {
		// Load the fields for the CustTxnTypeNotificationOptions entity
		List<ApplicationEntityField> entityFields = custTxnTypeNotificationOptionsService.getEntityFields();
		List<ApplicationEntityFieldResponse> responseFields = new ArrayList<ApplicationEntityFieldResponse>();
		// Convert the entity fields into response fields
		for(ApplicationEntityField entityField:entityFields)
		{
			responseFields.add(applicationEntityFieldService.convertModelToResponse(entityField));
		}
		response.setFields(responseFields);
		response.setRelatedEntitiesListData(custTxnTypeNotificationOptionsService.relatedEntitesToListItems());
		if(id != null)
			response.setEntity(custTxnTypeNotificationOptionsService.convertModelToResponse(custTxnTypeNotificationOptionsService.findById(id)));
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
		return custTxnTypeNotificationOptionsService.relatedEntitesToListItems();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#saveEditedEntityInstance(java.lang.Object)
	 */
	@Override
	protected EditActivityResponse<CustTxnTypeNotificationOptionsResponse> saveEntityInstance(
			CustTxnTypeNotificationOptionsRequest entityInstance) throws ApplicationException {
		CustTxnTypeNotificationOptions custTxnTypeNotificationOptions = custTxnTypeNotificationOptionsService.create(entityInstance);
		return this.getEditActivityResponse(custTxnTypeNotificationOptions.getId());
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#saveEditedEntityInstance(java.lang.Object)
	 */
	@Override
	protected EditActivityResponse<CustTxnTypeNotificationOptionsResponse> saveEditedEntityInstance(
			CustTxnTypeNotificationOptionsRequest entityInstance) throws ApplicationException {
		CustTxnTypeNotificationOptions custTxnTypeNotificationOptions = custTxnTypeNotificationOptionsService.update(entityInstance);
		return this.getEditActivityResponse(custTxnTypeNotificationOptions.getId());
	}
	
	@Override
	protected ListActivityResponse<CustTxnTypeNotificationOptionsResponse> deleteEntityInstances(
			List<Integer> idsOfEntitiesToDelete) throws ApplicationException {
		for(Integer id: idsOfEntitiesToDelete)
		{
			 custTxnTypeNotificationOptionsService.delete(id);
		}
		return this.getListActivityResponse(null);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getListActivityCode()
	 */
	@Override
	protected String getListActivityCode() throws ApplicationException {
		return custTxnTypeNotificationOptionsService.getListActivityCode();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getEditActivityCode()
	 */
	@Override
	protected String getEditActivityCode() throws ApplicationException {
		return custTxnTypeNotificationOptionsService.getEditActivityCode();
	}

}
