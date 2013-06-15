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

import com.nathanclaire.alantra.datasource.model.DataInputJobSummary;
import com.nathanclaire.alantra.application.model.ApplicationEntityField;
import com.nathanclaire.alantra.datasource.request.DataInputJobSummaryRequest;
import com.nathanclaire.alantra.datasource.response.DataInputJobSummaryResponse;
import com.nathanclaire.alantra.application.response.ApplicationActivityResponse;
import com.nathanclaire.alantra.application.response.ApplicationEntityFieldResponse;
import com.nathanclaire.alantra.datasource.service.entity.DataInputJobSummaryService;
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
@Path("/datainputjobsummary")
@Stateless
public class DataInputJobSummaryRESTService extends BaseActivityRESTService<DataInputJobSummaryResponse, DataInputJobSummaryRequest> 
{
	@Inject
	DataInputJobSummaryService dataInputJobSummaryService;
	
	@Inject 
	ApplicationEntityFieldService applicationEntityFieldService;
	
	private Logger logger = LoggerFactory.getLogger(DataInputJobSummaryRESTService.class);
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#populateListActivityResponse(com.nathanclaire.alantra.datasource.response.DataInputJobSummaryResponse, com.nathanclaire.alantra.base.response.ListActivityResponse, javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected ListActivityResponse<DataInputJobSummaryResponse> populateListActivityResponse(
			ApplicationActivityResponse activity, ListActivityResponse<DataInputJobSummaryResponse> response,
			MultivaluedMap<String, String> queryParameters) throws ApplicationException 
	{
		// Load the fields for the DataInputJobSummary entity
		List<ApplicationEntityFieldResponse> responseFields = new ArrayList<ApplicationEntityFieldResponse>();
		List<ApplicationEntityField> entityFields = dataInputJobSummaryService.getEntityFields();
		for(ApplicationEntityField entityField:entityFields)
		{
			responseFields.add(applicationEntityFieldService.convertModelToResponse(entityField));
		}
		response.setFields(responseFields);
		// Load the list of DataInputJobSummary's
		List<DataInputJobSummaryResponse> dataItems = new ArrayList<DataInputJobSummaryResponse>();
		for (DataInputJobSummary item:dataInputJobSummaryService.findAll(queryParameters))
		{
			dataItems.add(dataInputJobSummaryService.convertModelToResponse(item));
		}
		response.setData(dataItems);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getInstance(java.lang.Integer)
	 */
	@Override
	protected EditActivityResponse<DataInputJobSummaryResponse> populateEditActivityResponse(
			Integer id,	ApplicationActivityResponse activity, EditActivityResponse<DataInputJobSummaryResponse> response) 
					throws ApplicationException {
		// Load the fields for the DataInputJobSummary entity
		List<ApplicationEntityField> entityFields = dataInputJobSummaryService.getEntityFields();
		List<ApplicationEntityFieldResponse> responseFields = new ArrayList<ApplicationEntityFieldResponse>();
		// Convert the entity fields into response fields
		for(ApplicationEntityField entityField:entityFields)
		{
			responseFields.add(applicationEntityFieldService.convertModelToResponse(entityField));
		}
		response.setFields(responseFields);
		response.setRelatedEntitiesListData(dataInputJobSummaryService.relatedEntitesToListItems());
		if(id != null)
			response.setEntity(dataInputJobSummaryService.convertModelToResponse(dataInputJobSummaryService.findById(id)));
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
		return dataInputJobSummaryService.relatedEntitesToListItems();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#saveEditedEntityInstance(java.lang.Object)
	 */
	@Override
	protected EditActivityResponse<DataInputJobSummaryResponse> saveEntityInstance(
			DataInputJobSummaryRequest entityInstance) throws ApplicationException {
		DataInputJobSummary dataInputJobSummary = dataInputJobSummaryService.create(entityInstance);
		return this.getEditActivityResponse(dataInputJobSummary.getId());
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#saveEditedEntityInstance(java.lang.Object)
	 */
	@Override
	protected EditActivityResponse<DataInputJobSummaryResponse> saveEditedEntityInstance(
			DataInputJobSummaryRequest entityInstance) throws ApplicationException {
		DataInputJobSummary dataInputJobSummary = dataInputJobSummaryService.update(entityInstance);
		return this.getEditActivityResponse(dataInputJobSummary.getId());
	}
	
	@Override
	protected ListActivityResponse<DataInputJobSummaryResponse> deleteEntityInstances(
			List<Integer> idsOfEntitiesToDelete) throws ApplicationException {
		for(Integer id: idsOfEntitiesToDelete)
		{
			 dataInputJobSummaryService.delete(id);
		}
		return this.getListActivityResponse(null);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getListActivityCode()
	 */
	@Override
	protected String getListActivityCode() throws ApplicationException {
		return dataInputJobSummaryService.getListActivityCode();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getEditActivityCode()
	 */
	@Override
	protected String getEditActivityCode() throws ApplicationException {
		return dataInputJobSummaryService.getEditActivityCode();
	}

}
