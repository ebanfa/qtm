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

import com.nathanclaire.alantra.transaction.model.CustTypeTxnTypeNotificationOptions;
import com.nathanclaire.alantra.application.model.ApplicationEntityField;
import com.nathanclaire.alantra.transaction.request.CustTypeTxnTypeNotificationOptionsRequest;
import com.nathanclaire.alantra.transaction.response.CustTypeTxnTypeNotificationOptionsResponse;
import com.nathanclaire.alantra.application.response.ApplicationActivityResponse;
import com.nathanclaire.alantra.application.response.ApplicationEntityFieldResponse;
import com.nathanclaire.alantra.transaction.service.entity.CustTypeTxnTypeNotificationOptionsService;
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
@Path("/custtypetxntypenotificationoptions")
@Stateless
public class CustTypeTxnTypeNotificationOptionsRESTService extends BaseActivityRESTService<CustTypeTxnTypeNotificationOptionsResponse, CustTypeTxnTypeNotificationOptionsRequest> 
{
	@Inject
	CustTypeTxnTypeNotificationOptionsService custTypeTxnTypeNotificationOptionsService;
	
	@Inject 
	ApplicationEntityFieldService applicationEntityFieldService;
	
	private Logger logger = LoggerFactory.getLogger(CustTypeTxnTypeNotificationOptionsRESTService.class);
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#populateListActivityResponse(com.nathanclaire.alantra.custTypeTxnTypeNotificationOptions.response.CustTypeTxnTypeNotificationOptionsResponse, com.nathanclaire.alantra.base.response.ListActivityResponse, javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected ListActivityResponse<CustTypeTxnTypeNotificationOptionsResponse> populateListActivityResponse(
			ApplicationActivityResponse activity, ListActivityResponse<CustTypeTxnTypeNotificationOptionsResponse> response,
			MultivaluedMap<String, String> queryParameters) throws ApplicationException 
	{
		// Load the fields for the CustTypeTxnTypeNotificationOptions entity
		List<ApplicationEntityFieldResponse> responseFields = new ArrayList<ApplicationEntityFieldResponse>();
		List<ApplicationEntityField> entityFields = custTypeTxnTypeNotificationOptionsService.getEntityFields();
		for(ApplicationEntityField entityField:entityFields)
		{
			responseFields.add(applicationEntityFieldService.convertModelToResponse(entityField));
		}
		response.setFields(responseFields);
		// Load the list of CustTypeTxnTypeNotificationOptions's
		List<CustTypeTxnTypeNotificationOptionsResponse> dataItems = new ArrayList<CustTypeTxnTypeNotificationOptionsResponse>();
		for (CustTypeTxnTypeNotificationOptions item:custTypeTxnTypeNotificationOptionsService.findAll(queryParameters))
		{
			dataItems.add(custTypeTxnTypeNotificationOptionsService.convertModelToResponse(item));
		}
		response.setData(dataItems);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getInstance(java.lang.Integer)
	 */
	@Override
	protected EditActivityResponse<CustTypeTxnTypeNotificationOptionsResponse> populateEditActivityResponse(
			Integer id,	ApplicationActivityResponse activity, EditActivityResponse<CustTypeTxnTypeNotificationOptionsResponse> response) 
					throws ApplicationException {
		// Load the fields for the CustTypeTxnTypeNotificationOptions entity
		List<ApplicationEntityField> entityFields = custTypeTxnTypeNotificationOptionsService.getEntityFields();
		List<ApplicationEntityFieldResponse> responseFields = new ArrayList<ApplicationEntityFieldResponse>();
		// Convert the entity fields into response fields
		for(ApplicationEntityField entityField:entityFields)
		{
			responseFields.add(applicationEntityFieldService.convertModelToResponse(entityField));
		}
		response.setFields(responseFields);
		response.setRelatedEntitiesListData(custTypeTxnTypeNotificationOptionsService.relatedEntitesToListItems());
		if(id != null)
			response.setEntity(custTypeTxnTypeNotificationOptionsService.convertModelToResponse(custTypeTxnTypeNotificationOptionsService.findById(id)));
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
		return custTypeTxnTypeNotificationOptionsService.relatedEntitesToListItems();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#saveEditedEntityInstance(java.lang.Object)
	 */
	@Override
	protected EditActivityResponse<CustTypeTxnTypeNotificationOptionsResponse> saveEntityInstance(
			CustTypeTxnTypeNotificationOptionsRequest entityInstance) throws ApplicationException {
		CustTypeTxnTypeNotificationOptions custTypeTxnTypeNotificationOptions = custTypeTxnTypeNotificationOptionsService.create(entityInstance);
		return this.getEditActivityResponse(custTypeTxnTypeNotificationOptions.getId());
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#saveEditedEntityInstance(java.lang.Object)
	 */
	@Override
	protected EditActivityResponse<CustTypeTxnTypeNotificationOptionsResponse> saveEditedEntityInstance(
			CustTypeTxnTypeNotificationOptionsRequest entityInstance) throws ApplicationException {
		CustTypeTxnTypeNotificationOptions custTypeTxnTypeNotificationOptions = custTypeTxnTypeNotificationOptionsService.update(entityInstance);
		return this.getEditActivityResponse(custTypeTxnTypeNotificationOptions.getId());
	}
	
	@Override
	protected ListActivityResponse<CustTypeTxnTypeNotificationOptionsResponse> deleteEntityInstances(
			List<Integer> idsOfEntitiesToDelete) throws ApplicationException {
		for(Integer id: idsOfEntitiesToDelete)
		{
			 custTypeTxnTypeNotificationOptionsService.delete(id);
		}
		return this.getListActivityResponse(null);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getListActivityCode()
	 */
	@Override
	protected String getListActivityCode() throws ApplicationException {
		return custTypeTxnTypeNotificationOptionsService.getListActivityCode();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getEditActivityCode()
	 */
	@Override
	protected String getEditActivityCode() throws ApplicationException {
		return custTypeTxnTypeNotificationOptionsService.getEditActivityCode();
	}

}
