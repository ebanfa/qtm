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

import com.nathanclaire.alantra.transaction.model.CustTxnTypeConfirmationOptions;
import com.nathanclaire.alantra.application.model.ApplicationEntityField;
import com.nathanclaire.alantra.transaction.request.CustTxnTypeConfirmationOptionsRequest;
import com.nathanclaire.alantra.transaction.response.CustTxnTypeConfirmationOptionsResponse;
import com.nathanclaire.alantra.application.response.ApplicationActivityResponse;
import com.nathanclaire.alantra.application.response.ApplicationEntityFieldResponse;
import com.nathanclaire.alantra.transaction.service.entity.CustTxnTypeConfirmationOptionsService;
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
@Path("/custtxntypeconfirmationoptions")
@Stateless
public class CustTxnTypeConfirmationOptionsRESTService extends BaseActivityRESTService<CustTxnTypeConfirmationOptionsResponse, CustTxnTypeConfirmationOptionsRequest> 
{
	@Inject
	CustTxnTypeConfirmationOptionsService custTxnTypeConfirmationOptionsService;
	
	@Inject 
	ApplicationEntityFieldService applicationEntityFieldService;
	
	private Logger logger = LoggerFactory.getLogger(CustTxnTypeConfirmationOptionsRESTService.class);
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#populateListActivityResponse(com.nathanclaire.alantra.custTxnTypeConfirmationOptions.response.CustTxnTypeConfirmationOptionsResponse, com.nathanclaire.alantra.base.response.ListActivityResponse, javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected ListActivityResponse<CustTxnTypeConfirmationOptionsResponse> populateListActivityResponse(
			ApplicationActivityResponse activity, ListActivityResponse<CustTxnTypeConfirmationOptionsResponse> response,
			MultivaluedMap<String, String> queryParameters) throws ApplicationException 
	{
		// Load the fields for the CustTxnTypeConfirmationOptions entity
		List<ApplicationEntityFieldResponse> responseFields = new ArrayList<ApplicationEntityFieldResponse>();
		List<ApplicationEntityField> entityFields = custTxnTypeConfirmationOptionsService.getEntityFields();
		for(ApplicationEntityField entityField:entityFields)
		{
			responseFields.add(applicationEntityFieldService.convertModelToResponse(entityField));
		}
		response.setFields(responseFields);
		// Load the list of CustTxnTypeConfirmationOptions's
		List<CustTxnTypeConfirmationOptionsResponse> dataItems = new ArrayList<CustTxnTypeConfirmationOptionsResponse>();
		for (CustTxnTypeConfirmationOptions item:custTxnTypeConfirmationOptionsService.findAll(queryParameters))
		{
			dataItems.add(custTxnTypeConfirmationOptionsService.convertModelToResponse(item));
		}
		response.setData(dataItems);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getInstance(java.lang.Integer)
	 */
	@Override
	protected EditActivityResponse<CustTxnTypeConfirmationOptionsResponse> populateEditActivityResponse(
			Integer id,	ApplicationActivityResponse activity, EditActivityResponse<CustTxnTypeConfirmationOptionsResponse> response) 
					throws ApplicationException {
		// Load the fields for the CustTxnTypeConfirmationOptions entity
		List<ApplicationEntityField> entityFields = custTxnTypeConfirmationOptionsService.getEntityFields();
		List<ApplicationEntityFieldResponse> responseFields = new ArrayList<ApplicationEntityFieldResponse>();
		// Convert the entity fields into response fields
		for(ApplicationEntityField entityField:entityFields)
		{
			responseFields.add(applicationEntityFieldService.convertModelToResponse(entityField));
		}
		response.setFields(responseFields);
		response.setRelatedEntitiesListData(custTxnTypeConfirmationOptionsService.relatedEntitesToListItems());
		if(id != null)
			response.setEntity(custTxnTypeConfirmationOptionsService.convertModelToResponse(custTxnTypeConfirmationOptionsService.findById(id)));
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
		return custTxnTypeConfirmationOptionsService.relatedEntitesToListItems();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#saveEditedEntityInstance(java.lang.Object)
	 */
	@Override
	protected EditActivityResponse<CustTxnTypeConfirmationOptionsResponse> saveEntityInstance(
			CustTxnTypeConfirmationOptionsRequest entityInstance) throws ApplicationException {
		CustTxnTypeConfirmationOptions custTxnTypeConfirmationOptions = custTxnTypeConfirmationOptionsService.create(entityInstance);
		return this.getEditActivityResponse(custTxnTypeConfirmationOptions.getId());
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#saveEditedEntityInstance(java.lang.Object)
	 */
	@Override
	protected EditActivityResponse<CustTxnTypeConfirmationOptionsResponse> saveEditedEntityInstance(
			CustTxnTypeConfirmationOptionsRequest entityInstance) throws ApplicationException {
		CustTxnTypeConfirmationOptions custTxnTypeConfirmationOptions = custTxnTypeConfirmationOptionsService.update(entityInstance);
		return this.getEditActivityResponse(custTxnTypeConfirmationOptions.getId());
	}
	
	@Override
	protected ListActivityResponse<CustTxnTypeConfirmationOptionsResponse> deleteEntityInstances(
			List<Integer> idsOfEntitiesToDelete) throws ApplicationException {
		for(Integer id: idsOfEntitiesToDelete)
		{
			 custTxnTypeConfirmationOptionsService.delete(id);
		}
		return this.getListActivityResponse(null);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getListActivityCode()
	 */
	@Override
	protected String getListActivityCode() throws ApplicationException {
		return custTxnTypeConfirmationOptionsService.getListActivityCode();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getEditActivityCode()
	 */
	@Override
	protected String getEditActivityCode() throws ApplicationException {
		return custTxnTypeConfirmationOptionsService.getEditActivityCode();
	}

}
