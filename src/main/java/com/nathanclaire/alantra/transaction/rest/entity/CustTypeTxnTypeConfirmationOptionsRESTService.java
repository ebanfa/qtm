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

import com.nathanclaire.alantra.transaction.model.CustTypeTxnTypeConfirmationOptions;
import com.nathanclaire.alantra.application.model.ApplicationEntityField;
import com.nathanclaire.alantra.transaction.request.CustTypeTxnTypeConfirmationOptionsRequest;
import com.nathanclaire.alantra.transaction.response.CustTypeTxnTypeConfirmationOptionsResponse;
import com.nathanclaire.alantra.application.response.ApplicationActivityResponse;
import com.nathanclaire.alantra.application.response.ApplicationEntityFieldResponse;
import com.nathanclaire.alantra.transaction.service.entity.CustTypeTxnTypeConfirmationOptionsService;
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
@Path("/custtypetxntypeconfirmationoptions")
@Stateless
public class CustTypeTxnTypeConfirmationOptionsRESTService extends BaseActivityRESTService<CustTypeTxnTypeConfirmationOptionsResponse, CustTypeTxnTypeConfirmationOptionsRequest> 
{
	@Inject
	CustTypeTxnTypeConfirmationOptionsService custTypeTxnTypeConfirmationOptionsService;
	
	@Inject 
	ApplicationEntityFieldService applicationEntityFieldService;
	
	private Logger logger = LoggerFactory.getLogger(CustTypeTxnTypeConfirmationOptionsRESTService.class);
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#populateListActivityResponse(com.nathanclaire.alantra.custTypeTxnTypeConfirmationOptions.response.CustTypeTxnTypeConfirmationOptionsResponse, com.nathanclaire.alantra.base.response.ListActivityResponse, javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected ListActivityResponse<CustTypeTxnTypeConfirmationOptionsResponse> populateListActivityResponse(
			ApplicationActivityResponse activity, ListActivityResponse<CustTypeTxnTypeConfirmationOptionsResponse> response,
			MultivaluedMap<String, String> queryParameters) throws ApplicationException 
	{
		// Load the fields for the CustTypeTxnTypeConfirmationOptions entity
		List<ApplicationEntityFieldResponse> responseFields = new ArrayList<ApplicationEntityFieldResponse>();
		List<ApplicationEntityField> entityFields = custTypeTxnTypeConfirmationOptionsService.getEntityFields();
		for(ApplicationEntityField entityField:entityFields)
		{
			responseFields.add(applicationEntityFieldService.convertModelToResponse(entityField));
		}
		response.setFields(responseFields);
		// Load the list of CustTypeTxnTypeConfirmationOptions's
		List<CustTypeTxnTypeConfirmationOptionsResponse> dataItems = new ArrayList<CustTypeTxnTypeConfirmationOptionsResponse>();
		for (CustTypeTxnTypeConfirmationOptions item:custTypeTxnTypeConfirmationOptionsService.findAll(queryParameters))
		{
			dataItems.add(custTypeTxnTypeConfirmationOptionsService.convertModelToResponse(item));
		}
		response.setData(dataItems);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getInstance(java.lang.Integer)
	 */
	@Override
	protected EditActivityResponse<CustTypeTxnTypeConfirmationOptionsResponse> populateEditActivityResponse(
			Integer id,	ApplicationActivityResponse activity, EditActivityResponse<CustTypeTxnTypeConfirmationOptionsResponse> response) 
					throws ApplicationException {
		// Load the fields for the CustTypeTxnTypeConfirmationOptions entity
		List<ApplicationEntityField> entityFields = custTypeTxnTypeConfirmationOptionsService.getEntityFields();
		List<ApplicationEntityFieldResponse> responseFields = new ArrayList<ApplicationEntityFieldResponse>();
		// Convert the entity fields into response fields
		for(ApplicationEntityField entityField:entityFields)
		{
			responseFields.add(applicationEntityFieldService.convertModelToResponse(entityField));
		}
		response.setFields(responseFields);
		response.setRelatedEntitiesListData(custTypeTxnTypeConfirmationOptionsService.relatedEntitesToListItems());
		if(id != null)
			response.setEntity(custTypeTxnTypeConfirmationOptionsService.convertModelToResponse(custTypeTxnTypeConfirmationOptionsService.findById(id)));
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
		return custTypeTxnTypeConfirmationOptionsService.relatedEntitesToListItems();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#saveEditedEntityInstance(java.lang.Object)
	 */
	@Override
	protected EditActivityResponse<CustTypeTxnTypeConfirmationOptionsResponse> saveEntityInstance(
			CustTypeTxnTypeConfirmationOptionsRequest entityInstance) throws ApplicationException {
		CustTypeTxnTypeConfirmationOptions custTypeTxnTypeConfirmationOptions = custTypeTxnTypeConfirmationOptionsService.create(entityInstance);
		return this.getEditActivityResponse(custTypeTxnTypeConfirmationOptions.getId());
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#saveEditedEntityInstance(java.lang.Object)
	 */
	@Override
	protected EditActivityResponse<CustTypeTxnTypeConfirmationOptionsResponse> saveEditedEntityInstance(
			CustTypeTxnTypeConfirmationOptionsRequest entityInstance) throws ApplicationException {
		CustTypeTxnTypeConfirmationOptions custTypeTxnTypeConfirmationOptions = custTypeTxnTypeConfirmationOptionsService.update(entityInstance);
		return this.getEditActivityResponse(custTypeTxnTypeConfirmationOptions.getId());
	}
	
	@Override
	protected ListActivityResponse<CustTypeTxnTypeConfirmationOptionsResponse> deleteEntityInstances(
			List<Integer> idsOfEntitiesToDelete) throws ApplicationException {
		for(Integer id: idsOfEntitiesToDelete)
		{
			 custTypeTxnTypeConfirmationOptionsService.delete(id);
		}
		return this.getListActivityResponse(null);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getListActivityCode()
	 */
	@Override
	protected String getListActivityCode() throws ApplicationException {
		return custTypeTxnTypeConfirmationOptionsService.getListActivityCode();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getEditActivityCode()
	 */
	@Override
	protected String getEditActivityCode() throws ApplicationException {
		return custTypeTxnTypeConfirmationOptionsService.getEditActivityCode();
	}

}
