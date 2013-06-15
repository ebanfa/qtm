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

import com.nathanclaire.alantra.datasource.model.DataInputJobRecordsSummary;
import com.nathanclaire.alantra.application.model.ApplicationEntityField;
import com.nathanclaire.alantra.datasource.request.DataInputJobRecordsSummaryRequest;
import com.nathanclaire.alantra.datasource.response.DataInputJobRecordsSummaryResponse;
import com.nathanclaire.alantra.application.response.ApplicationActivityResponse;
import com.nathanclaire.alantra.application.response.ApplicationEntityFieldResponse;
import com.nathanclaire.alantra.datasource.service.entity.DataInputJobRecordsSummaryService;
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
@Path("/datainputjobrecordssummary")
@Stateless
public class DataInputJobRecordsSummaryRESTService extends BaseActivityRESTService<DataInputJobRecordsSummaryResponse, DataInputJobRecordsSummaryRequest> 
{
	@Inject
	DataInputJobRecordsSummaryService dataInputJobRecordsSummaryService;
	
	@Inject 
	ApplicationEntityFieldService applicationEntityFieldService;
	
	private Logger logger = LoggerFactory.getLogger(DataInputJobRecordsSummaryRESTService.class);
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#populateListActivityResponse(com.nathanclaire.alantra.datasource.response.DataInputJobRecordsSummaryResponse, com.nathanclaire.alantra.base.response.ListActivityResponse, javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected ListActivityResponse<DataInputJobRecordsSummaryResponse> populateListActivityResponse(
			ApplicationActivityResponse activity, ListActivityResponse<DataInputJobRecordsSummaryResponse> response,
			MultivaluedMap<String, String> queryParameters) throws ApplicationException 
	{
		// Load the fields for the DataInputJobRecordsSummary entity
		List<ApplicationEntityFieldResponse> responseFields = new ArrayList<ApplicationEntityFieldResponse>();
		List<ApplicationEntityField> entityFields = dataInputJobRecordsSummaryService.getEntityFields();
		for(ApplicationEntityField entityField:entityFields)
		{
			responseFields.add(applicationEntityFieldService.convertModelToResponse(entityField));
		}
		response.setFields(responseFields);
		// Load the list of DataInputJobRecordsSummary's
		List<DataInputJobRecordsSummaryResponse> dataItems = new ArrayList<DataInputJobRecordsSummaryResponse>();
		for (DataInputJobRecordsSummary item:dataInputJobRecordsSummaryService.findAll(queryParameters))
		{
			dataItems.add(dataInputJobRecordsSummaryService.convertModelToResponse(item));
		}
		response.setData(dataItems);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getInstance(java.lang.Integer)
	 */
	@Override
	protected EditActivityResponse<DataInputJobRecordsSummaryResponse> populateEditActivityResponse(
			Integer id,	ApplicationActivityResponse activity, EditActivityResponse<DataInputJobRecordsSummaryResponse> response) 
					throws ApplicationException {
		// Load the fields for the DataInputJobRecordsSummary entity
		List<ApplicationEntityField> entityFields = dataInputJobRecordsSummaryService.getEntityFields();
		List<ApplicationEntityFieldResponse> responseFields = new ArrayList<ApplicationEntityFieldResponse>();
		// Convert the entity fields into response fields
		for(ApplicationEntityField entityField:entityFields)
		{
			responseFields.add(applicationEntityFieldService.convertModelToResponse(entityField));
		}
		response.setFields(responseFields);
		response.setRelatedEntitiesListData(dataInputJobRecordsSummaryService.relatedEntitesToListItems());
		if(id != null)
			response.setEntity(dataInputJobRecordsSummaryService.convertModelToResponse(dataInputJobRecordsSummaryService.findById(id)));
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
		return dataInputJobRecordsSummaryService.relatedEntitesToListItems();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#saveEditedEntityInstance(java.lang.Object)
	 */
	@Override
	protected EditActivityResponse<DataInputJobRecordsSummaryResponse> saveEntityInstance(
			DataInputJobRecordsSummaryRequest entityInstance) throws ApplicationException {
		DataInputJobRecordsSummary dataInputJobRecordsSummary = dataInputJobRecordsSummaryService.create(entityInstance);
		return this.getEditActivityResponse(dataInputJobRecordsSummary.getId());
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#saveEditedEntityInstance(java.lang.Object)
	 */
	@Override
	protected EditActivityResponse<DataInputJobRecordsSummaryResponse> saveEditedEntityInstance(
			DataInputJobRecordsSummaryRequest entityInstance) throws ApplicationException {
		DataInputJobRecordsSummary dataInputJobRecordsSummary = dataInputJobRecordsSummaryService.update(entityInstance);
		return this.getEditActivityResponse(dataInputJobRecordsSummary.getId());
	}
	
	@Override
	protected ListActivityResponse<DataInputJobRecordsSummaryResponse> deleteEntityInstances(
			List<Integer> idsOfEntitiesToDelete) throws ApplicationException {
		for(Integer id: idsOfEntitiesToDelete)
		{
			 dataInputJobRecordsSummaryService.delete(id);
		}
		return this.getListActivityResponse(null);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getListActivityCode()
	 */
	@Override
	protected String getListActivityCode() throws ApplicationException {
		return dataInputJobRecordsSummaryService.getListActivityCode();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getEditActivityCode()
	 */
	@Override
	protected String getEditActivityCode() throws ApplicationException {
		return dataInputJobRecordsSummaryService.getEditActivityCode();
	}

}
