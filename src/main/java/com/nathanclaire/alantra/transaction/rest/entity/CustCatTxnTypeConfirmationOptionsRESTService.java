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

import com.nathanclaire.alantra.transaction.model.CustCatTxnTypeConfirmationOptions;
import com.nathanclaire.alantra.application.model.ApplicationEntityField;
import com.nathanclaire.alantra.transaction.request.CustCatTxnTypeConfirmationOptionsRequest;
import com.nathanclaire.alantra.transaction.response.CustCatTxnTypeConfirmationOptionsResponse;
import com.nathanclaire.alantra.application.response.ApplicationActivityResponse;
import com.nathanclaire.alantra.application.response.ApplicationEntityFieldResponse;
import com.nathanclaire.alantra.transaction.service.entity.CustCatTxnTypeConfirmationOptionsService;
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
@Path("/custcattxntypeconfirmationoptions")
@Stateless
public class CustCatTxnTypeConfirmationOptionsRESTService extends BaseActivityRESTService<CustCatTxnTypeConfirmationOptionsResponse, CustCatTxnTypeConfirmationOptionsRequest> 
{
	@Inject
	CustCatTxnTypeConfirmationOptionsService custCatTxnTypeConfirmationOptionsService;
	
	@Inject 
	ApplicationEntityFieldService applicationEntityFieldService;
	
	private Logger logger = LoggerFactory.getLogger(CustCatTxnTypeConfirmationOptionsRESTService.class);
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#populateListActivityResponse(com.nathanclaire.alantra.custCatTxnTypeConfirmationOptions.response.CustCatTxnTypeConfirmationOptionsResponse, com.nathanclaire.alantra.base.response.ListActivityResponse, javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected ListActivityResponse<CustCatTxnTypeConfirmationOptionsResponse> populateListActivityResponse(
			ApplicationActivityResponse activity, ListActivityResponse<CustCatTxnTypeConfirmationOptionsResponse> response,
			MultivaluedMap<String, String> queryParameters) throws ApplicationException 
	{
		// Load the fields for the CustCatTxnTypeConfirmationOptions entity
		List<ApplicationEntityFieldResponse> responseFields = new ArrayList<ApplicationEntityFieldResponse>();
		List<ApplicationEntityField> entityFields = custCatTxnTypeConfirmationOptionsService.getEntityFields();
		for(ApplicationEntityField entityField:entityFields)
		{
			responseFields.add(applicationEntityFieldService.convertModelToResponse(entityField));
		}
		response.setFields(responseFields);
		// Load the list of CustCatTxnTypeConfirmationOptions's
		List<CustCatTxnTypeConfirmationOptionsResponse> dataItems = new ArrayList<CustCatTxnTypeConfirmationOptionsResponse>();
		for (CustCatTxnTypeConfirmationOptions item:custCatTxnTypeConfirmationOptionsService.findAll(queryParameters))
		{
			dataItems.add(custCatTxnTypeConfirmationOptionsService.convertModelToResponse(item));
		}
		response.setData(dataItems);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getInstance(java.lang.Integer)
	 */
	@Override
	protected EditActivityResponse<CustCatTxnTypeConfirmationOptionsResponse> populateEditActivityResponse(
			Integer id,	ApplicationActivityResponse activity, EditActivityResponse<CustCatTxnTypeConfirmationOptionsResponse> response) 
					throws ApplicationException {
		// Load the fields for the CustCatTxnTypeConfirmationOptions entity
		List<ApplicationEntityField> entityFields = custCatTxnTypeConfirmationOptionsService.getEntityFields();
		List<ApplicationEntityFieldResponse> responseFields = new ArrayList<ApplicationEntityFieldResponse>();
		// Convert the entity fields into response fields
		for(ApplicationEntityField entityField:entityFields)
		{
			responseFields.add(applicationEntityFieldService.convertModelToResponse(entityField));
		}
		response.setFields(responseFields);
		response.setRelatedEntitiesListData(custCatTxnTypeConfirmationOptionsService.relatedEntitesToListItems());
		if(id != null)
			response.setEntity(custCatTxnTypeConfirmationOptionsService.convertModelToResponse(custCatTxnTypeConfirmationOptionsService.findById(id)));
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
		return custCatTxnTypeConfirmationOptionsService.relatedEntitesToListItems();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#saveEditedEntityInstance(java.lang.Object)
	 */
	@Override
	protected EditActivityResponse<CustCatTxnTypeConfirmationOptionsResponse> saveEntityInstance(
			CustCatTxnTypeConfirmationOptionsRequest entityInstance) throws ApplicationException {
		CustCatTxnTypeConfirmationOptions custCatTxnTypeConfirmationOptions = custCatTxnTypeConfirmationOptionsService.create(entityInstance);
		return this.getEditActivityResponse(custCatTxnTypeConfirmationOptions.getId());
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#saveEditedEntityInstance(java.lang.Object)
	 */
	@Override
	protected EditActivityResponse<CustCatTxnTypeConfirmationOptionsResponse> saveEditedEntityInstance(
			CustCatTxnTypeConfirmationOptionsRequest entityInstance) throws ApplicationException {
		CustCatTxnTypeConfirmationOptions custCatTxnTypeConfirmationOptions = custCatTxnTypeConfirmationOptionsService.update(entityInstance);
		return this.getEditActivityResponse(custCatTxnTypeConfirmationOptions.getId());
	}
	
	@Override
	protected ListActivityResponse<CustCatTxnTypeConfirmationOptionsResponse> deleteEntityInstances(
			List<Integer> idsOfEntitiesToDelete) throws ApplicationException {
		for(Integer id: idsOfEntitiesToDelete)
		{
			 custCatTxnTypeConfirmationOptionsService.delete(id);
		}
		return this.getListActivityResponse(null);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getListActivityCode()
	 */
	@Override
	protected String getListActivityCode() throws ApplicationException {
		return custCatTxnTypeConfirmationOptionsService.getListActivityCode();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getEditActivityCode()
	 */
	@Override
	protected String getEditActivityCode() throws ApplicationException {
		return custCatTxnTypeConfirmationOptionsService.getEditActivityCode();
	}

}
