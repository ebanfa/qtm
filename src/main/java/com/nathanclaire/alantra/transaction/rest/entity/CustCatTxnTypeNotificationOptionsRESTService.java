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

import com.nathanclaire.alantra.transaction.model.CustCatTxnTypeNotificationOptions;
import com.nathanclaire.alantra.application.model.ApplicationEntityField;
import com.nathanclaire.alantra.transaction.request.CustCatTxnTypeNotificationOptionsRequest;
import com.nathanclaire.alantra.transaction.response.CustCatTxnTypeNotificationOptionsResponse;
import com.nathanclaire.alantra.application.response.ApplicationActivityResponse;
import com.nathanclaire.alantra.application.response.ApplicationEntityFieldResponse;
import com.nathanclaire.alantra.transaction.service.entity.CustCatTxnTypeNotificationOptionsService;
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
@Path("/custcattxntypenotificationoptions")
@Stateless
public class CustCatTxnTypeNotificationOptionsRESTService extends BaseActivityRESTService<CustCatTxnTypeNotificationOptionsResponse, CustCatTxnTypeNotificationOptionsRequest> 
{
	@Inject
	CustCatTxnTypeNotificationOptionsService custCatTxnTypeNotificationOptionsService;
	
	@Inject 
	ApplicationEntityFieldService applicationEntityFieldService;
	
	private Logger logger = LoggerFactory.getLogger(CustCatTxnTypeNotificationOptionsRESTService.class);
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#populateListActivityResponse(com.nathanclaire.alantra.custCatTxnTypeNotificationOptions.response.CustCatTxnTypeNotificationOptionsResponse, com.nathanclaire.alantra.base.response.ListActivityResponse, javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected ListActivityResponse<CustCatTxnTypeNotificationOptionsResponse> populateListActivityResponse(
			ApplicationActivityResponse activity, ListActivityResponse<CustCatTxnTypeNotificationOptionsResponse> response,
			MultivaluedMap<String, String> queryParameters) throws ApplicationException 
	{
		// Load the fields for the CustCatTxnTypeNotificationOptions entity
		List<ApplicationEntityFieldResponse> responseFields = new ArrayList<ApplicationEntityFieldResponse>();
		List<ApplicationEntityField> entityFields = custCatTxnTypeNotificationOptionsService.getEntityFields();
		for(ApplicationEntityField entityField:entityFields)
		{
			responseFields.add(applicationEntityFieldService.convertModelToResponse(entityField));
		}
		response.setFields(responseFields);
		// Load the list of CustCatTxnTypeNotificationOptions's
		List<CustCatTxnTypeNotificationOptionsResponse> dataItems = new ArrayList<CustCatTxnTypeNotificationOptionsResponse>();
		for (CustCatTxnTypeNotificationOptions item:custCatTxnTypeNotificationOptionsService.findAll(queryParameters))
		{
			dataItems.add(custCatTxnTypeNotificationOptionsService.convertModelToResponse(item));
		}
		response.setData(dataItems);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getInstance(java.lang.Integer)
	 */
	@Override
	protected EditActivityResponse<CustCatTxnTypeNotificationOptionsResponse> populateEditActivityResponse(
			Integer id,	ApplicationActivityResponse activity, EditActivityResponse<CustCatTxnTypeNotificationOptionsResponse> response) 
					throws ApplicationException {
		// Load the fields for the CustCatTxnTypeNotificationOptions entity
		List<ApplicationEntityField> entityFields = custCatTxnTypeNotificationOptionsService.getEntityFields();
		List<ApplicationEntityFieldResponse> responseFields = new ArrayList<ApplicationEntityFieldResponse>();
		// Convert the entity fields into response fields
		for(ApplicationEntityField entityField:entityFields)
		{
			responseFields.add(applicationEntityFieldService.convertModelToResponse(entityField));
		}
		response.setFields(responseFields);
		response.setRelatedEntitiesListData(custCatTxnTypeNotificationOptionsService.relatedEntitesToListItems());
		if(id != null)
			response.setEntity(custCatTxnTypeNotificationOptionsService.convertModelToResponse(custCatTxnTypeNotificationOptionsService.findById(id)));
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
		return custCatTxnTypeNotificationOptionsService.relatedEntitesToListItems();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#saveEditedEntityInstance(java.lang.Object)
	 */
	@Override
	protected EditActivityResponse<CustCatTxnTypeNotificationOptionsResponse> saveEntityInstance(
			CustCatTxnTypeNotificationOptionsRequest entityInstance) throws ApplicationException {
		CustCatTxnTypeNotificationOptions custCatTxnTypeNotificationOptions = custCatTxnTypeNotificationOptionsService.create(entityInstance);
		return this.getEditActivityResponse(custCatTxnTypeNotificationOptions.getId());
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#saveEditedEntityInstance(java.lang.Object)
	 */
	@Override
	protected EditActivityResponse<CustCatTxnTypeNotificationOptionsResponse> saveEditedEntityInstance(
			CustCatTxnTypeNotificationOptionsRequest entityInstance) throws ApplicationException {
		CustCatTxnTypeNotificationOptions custCatTxnTypeNotificationOptions = custCatTxnTypeNotificationOptionsService.update(entityInstance);
		return this.getEditActivityResponse(custCatTxnTypeNotificationOptions.getId());
	}
	
	@Override
	protected ListActivityResponse<CustCatTxnTypeNotificationOptionsResponse> deleteEntityInstances(
			List<Integer> idsOfEntitiesToDelete) throws ApplicationException {
		for(Integer id: idsOfEntitiesToDelete)
		{
			 custCatTxnTypeNotificationOptionsService.delete(id);
		}
		return this.getListActivityResponse(null);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getListActivityCode()
	 */
	@Override
	protected String getListActivityCode() throws ApplicationException {
		return custCatTxnTypeNotificationOptionsService.getListActivityCode();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getEditActivityCode()
	 */
	@Override
	protected String getEditActivityCode() throws ApplicationException {
		return custCatTxnTypeNotificationOptionsService.getEditActivityCode();
	}

}
