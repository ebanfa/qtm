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

import com.nathanclaire.alantra.datasource.model.DataInputJobSummaryStatus;
import com.nathanclaire.alantra.application.model.ApplicationEntityField;
import com.nathanclaire.alantra.datasource.request.DataInputJobSummaryStatusRequest;
import com.nathanclaire.alantra.datasource.response.DataInputJobSummaryStatusResponse;
import com.nathanclaire.alantra.application.response.ApplicationActivityResponse;
import com.nathanclaire.alantra.application.response.ApplicationEntityFieldResponse;
import com.nathanclaire.alantra.datasource.service.entity.DataInputJobSummaryStatusService;
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
@Path("/datainputjobsummarystatus")
@Stateless
public class DataInputJobSummaryStatusRESTService extends BaseActivityRESTService<DataInputJobSummaryStatusResponse, DataInputJobSummaryStatusRequest> 
{
	@Inject
	DataInputJobSummaryStatusService dataInputJobSummaryStatusService;
	
	@Inject 
	ApplicationEntityFieldService applicationEntityFieldService;
	
	private Logger logger = LoggerFactory.getLogger(DataInputJobSummaryStatusRESTService.class);
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#populateListActivityResponse(com.nathanclaire.alantra.datasource.response.DataInputJobSummaryStatusResponse, com.nathanclaire.alantra.base.response.ListActivityResponse, javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected ListActivityResponse<DataInputJobSummaryStatusResponse> populateListActivityResponse(
			ApplicationActivityResponse activity, ListActivityResponse<DataInputJobSummaryStatusResponse> response,
			MultivaluedMap<String, String> queryParameters) throws ApplicationException 
	{
		// Load the fields for the DataInputJobSummaryStatus entity
		List<ApplicationEntityFieldResponse> responseFields = new ArrayList<ApplicationEntityFieldResponse>();
		List<ApplicationEntityField> entityFields = dataInputJobSummaryStatusService.getEntityFields();
		for(ApplicationEntityField entityField:entityFields)
		{
			responseFields.add(applicationEntityFieldService.convertModelToResponse(entityField));
		}
		response.setFields(responseFields);
		// Load the list of DataInputJobSummaryStatus's
		List<DataInputJobSummaryStatusResponse> dataItems = new ArrayList<DataInputJobSummaryStatusResponse>();
		for (DataInputJobSummaryStatus item:dataInputJobSummaryStatusService.findAll(queryParameters))
		{
			dataItems.add(dataInputJobSummaryStatusService.convertModelToResponse(item));
		}
		response.setData(dataItems);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getInstance(java.lang.Integer)
	 */
	@Override
	protected EditActivityResponse<DataInputJobSummaryStatusResponse> populateEditActivityResponse(
			Integer id,	ApplicationActivityResponse activity, EditActivityResponse<DataInputJobSummaryStatusResponse> response) 
					throws ApplicationException {
		// Load the fields for the DataInputJobSummaryStatus entity
		List<ApplicationEntityField> entityFields = dataInputJobSummaryStatusService.getEntityFields();
		List<ApplicationEntityFieldResponse> responseFields = new ArrayList<ApplicationEntityFieldResponse>();
		// Convert the entity fields into response fields
		for(ApplicationEntityField entityField:entityFields)
		{
			responseFields.add(applicationEntityFieldService.convertModelToResponse(entityField));
		}
		response.setFields(responseFields);
		response.setRelatedEntitiesListData(dataInputJobSummaryStatusService.relatedEntitesToListItems());
		if(id != null)
			response.setEntity(dataInputJobSummaryStatusService.convertModelToResponse(dataInputJobSummaryStatusService.findById(id)));
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
		return dataInputJobSummaryStatusService.relatedEntitesToListItems();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#saveEditedEntityInstance(java.lang.Object)
	 */
	@Override
	protected EditActivityResponse<DataInputJobSummaryStatusResponse> saveEntityInstance(
			DataInputJobSummaryStatusRequest entityInstance) throws ApplicationException {
		DataInputJobSummaryStatus dataInputJobSummaryStatus = dataInputJobSummaryStatusService.create(entityInstance);
		return this.getEditActivityResponse(dataInputJobSummaryStatus.getId());
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#saveEditedEntityInstance(java.lang.Object)
	 */
	@Override
	protected EditActivityResponse<DataInputJobSummaryStatusResponse> saveEditedEntityInstance(
			DataInputJobSummaryStatusRequest entityInstance) throws ApplicationException {
		DataInputJobSummaryStatus dataInputJobSummaryStatus = dataInputJobSummaryStatusService.update(entityInstance);
		return this.getEditActivityResponse(dataInputJobSummaryStatus.getId());
	}
	
	@Override
	protected ListActivityResponse<DataInputJobSummaryStatusResponse> deleteEntityInstances(
			List<Integer> idsOfEntitiesToDelete) throws ApplicationException {
		for(Integer id: idsOfEntitiesToDelete)
		{
			 dataInputJobSummaryStatusService.delete(id);
		}
		return this.getListActivityResponse(null);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getListActivityCode()
	 */
	@Override
	protected String getListActivityCode() throws ApplicationException {
		return dataInputJobSummaryStatusService.getListActivityCode();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getEditActivityCode()
	 */
	@Override
	protected String getEditActivityCode() throws ApplicationException {
		return dataInputJobSummaryStatusService.getEditActivityCode();
	}

}
