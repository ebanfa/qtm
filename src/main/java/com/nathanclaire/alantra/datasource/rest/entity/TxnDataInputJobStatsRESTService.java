/**
 * 
 */
package com.nathanclaire.alantra.datasource.rest.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.MultivaluedMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.datasource.model.TxnDataInputJobStats;
import com.nathanclaire.alantra.application.model.ApplicationEntityField;
import com.nathanclaire.alantra.datasource.request.TxnDataInputJobStatsRequest;
import com.nathanclaire.alantra.datasource.response.TxnDataInputJobStatsResponse;
import com.nathanclaire.alantra.application.response.ApplicationActivityResponse;
import com.nathanclaire.alantra.application.response.ApplicationEntityFieldResponse;
import com.nathanclaire.alantra.datasource.service.entity.TxnDataInputJobStatsService;
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
@Path("/txndatainputjobstats")
@Stateless
public class TxnDataInputJobStatsRESTService extends BaseActivityRESTService<TxnDataInputJobStatsResponse, TxnDataInputJobStatsRequest> 
{
	@Inject
	TxnDataInputJobStatsService txnDataInputJobStatsService;
	
	@Inject 
	ApplicationEntityFieldService applicationEntityFieldService;
	
	private Logger logger = LoggerFactory.getLogger(TxnDataInputJobStatsRESTService.class);
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#populateListActivityResponse(com.nathanclaire.alantra.datasource.response.TxnDataInputJobStatsResponse, com.nathanclaire.alantra.base.response.ListActivityResponse, javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected ListActivityResponse<TxnDataInputJobStatsResponse> populateListActivityResponse(
			ApplicationActivityResponse activity, ListActivityResponse<TxnDataInputJobStatsResponse> response,
			MultivaluedMap<String, String> queryParameters) throws ApplicationException 
	{
		// Load the fields for the TxnDataInputJobStats entity
		List<ApplicationEntityFieldResponse> responseFields = new ArrayList<ApplicationEntityFieldResponse>();
		List<ApplicationEntityField> entityFields = txnDataInputJobStatsService.getEntityFields();
		for(ApplicationEntityField entityField:entityFields)
		{
			responseFields.add(applicationEntityFieldService.convertModelToResponse(entityField));
		}
		response.setFields(responseFields);
		// Load the list of TxnDataInputJobStats's
		List<TxnDataInputJobStatsResponse> dataItems = new ArrayList<TxnDataInputJobStatsResponse>();
		for (TxnDataInputJobStats item:txnDataInputJobStatsService.findAll(queryParameters))
		{
			dataItems.add(txnDataInputJobStatsService.convertModelToResponse(item));
		}
		response.setData(dataItems);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getInstance(java.lang.Integer)
	 */
	@Override
	protected EditActivityResponse<TxnDataInputJobStatsResponse> populateEditActivityResponse(
			Integer id,	ApplicationActivityResponse activity, EditActivityResponse<TxnDataInputJobStatsResponse> response) 
					throws ApplicationException {
		// Load the fields for the TxnDataInputJobStats entity
		List<ApplicationEntityField> entityFields = txnDataInputJobStatsService.getEntityFields();
		List<ApplicationEntityFieldResponse> responseFields = new ArrayList<ApplicationEntityFieldResponse>();
		// Convert the entity fields into response fields
		for(ApplicationEntityField entityField:entityFields)
		{
			responseFields.add(applicationEntityFieldService.convertModelToResponse(entityField));
		}
		response.setFields(responseFields);
		response.setRelatedEntitiesListData(txnDataInputJobStatsService.relatedEntitesToListItems());
		if(id != null)
			response.setEntity(txnDataInputJobStatsService.convertModelToResponse(txnDataInputJobStatsService.findById(id)));
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
		return txnDataInputJobStatsService.relatedEntitesToListItems();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#saveEditedEntityInstance(java.lang.Object)
	 */
	@Override
	protected EditActivityResponse<TxnDataInputJobStatsResponse> saveEntityInstance(
			TxnDataInputJobStatsRequest entityInstance) throws ApplicationException {
		TxnDataInputJobStats txnDataInputJobStats = txnDataInputJobStatsService.create(entityInstance);
		return this.getEditActivityResponse(txnDataInputJobStats.getId());
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#saveEditedEntityInstance(java.lang.Object)
	 */
	@Override
	protected EditActivityResponse<TxnDataInputJobStatsResponse> saveEditedEntityInstance(
			TxnDataInputJobStatsRequest entityInstance) throws ApplicationException {
		TxnDataInputJobStats txnDataInputJobStats = txnDataInputJobStatsService.update(entityInstance);
		return this.getEditActivityResponse(txnDataInputJobStats.getId());
	}
	
	@Override
	protected ListActivityResponse<TxnDataInputJobStatsResponse> deleteEntityInstances(
			List<Integer> idsOfEntitiesToDelete) throws ApplicationException {
		for(Integer id: idsOfEntitiesToDelete)
		{
			 txnDataInputJobStatsService.delete(id);
		}
		return this.getListActivityResponse(null);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getListActivityCode()
	 */
	@Override
	protected String getListActivityCode() throws ApplicationException {
		return txnDataInputJobStatsService.getListActivityCode();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getEditActivityCode()
	 */
	@Override
	protected String getEditActivityCode() throws ApplicationException {
		return txnDataInputJobStatsService.getEditActivityCode();
	}

}
