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

import com.nathanclaire.alantra.datasource.model.DataInputJobRecordsSummaryStatus;
import com.nathanclaire.alantra.application.model.ApplicationEntityField;
import com.nathanclaire.alantra.datasource.request.DataInputJobRecordsSummaryStatusRequest;
import com.nathanclaire.alantra.datasource.response.DataInputJobRecordsSummaryStatusResponse;
import com.nathanclaire.alantra.application.response.ApplicationActivityResponse;
import com.nathanclaire.alantra.application.response.ApplicationEntityFieldResponse;
import com.nathanclaire.alantra.datasource.service.entity.DataInputJobRecordsSummaryStatusService;
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
@Path("/datainputjobrecordssummarystatus")
@Stateless
public class DataInputJobRecordsSummaryStatusRESTService extends BaseActivityRESTService<DataInputJobRecordsSummaryStatusResponse, DataInputJobRecordsSummaryStatusRequest> 
{
	@Inject
	DataInputJobRecordsSummaryStatusService dataInputJobRecordsSummaryStatusService;
	
	@Inject 
	ApplicationEntityFieldService applicationEntityFieldService;
	
	private Logger logger = LoggerFactory.getLogger(DataInputJobRecordsSummaryStatusRESTService.class);
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#populateListActivityResponse(com.nathanclaire.alantra.datasource.response.DataInputJobRecordsSummaryStatusResponse, com.nathanclaire.alantra.base.response.ListActivityResponse, javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected ListActivityResponse<DataInputJobRecordsSummaryStatusResponse> populateListActivityResponse(
			ApplicationActivityResponse activity, ListActivityResponse<DataInputJobRecordsSummaryStatusResponse> response,
			MultivaluedMap<String, String> queryParameters) throws ApplicationException 
	{
		// Load the fields for the DataInputJobRecordsSummaryStatus entity
		List<ApplicationEntityFieldResponse> responseFields = new ArrayList<ApplicationEntityFieldResponse>();
		List<ApplicationEntityField> entityFields = dataInputJobRecordsSummaryStatusService.getEntityFields();
		for(ApplicationEntityField entityField:entityFields)
		{
			responseFields.add(applicationEntityFieldService.convertModelToResponse(entityField));
		}
		response.setFields(responseFields);
		// Load the list of DataInputJobRecordsSummaryStatus's
		List<DataInputJobRecordsSummaryStatusResponse> dataItems = new ArrayList<DataInputJobRecordsSummaryStatusResponse>();
		for (DataInputJobRecordsSummaryStatus item:dataInputJobRecordsSummaryStatusService.findAll(queryParameters))
		{
			dataItems.add(dataInputJobRecordsSummaryStatusService.convertModelToResponse(item));
		}
		response.setData(dataItems);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getInstance(java.lang.Integer)
	 */
	@Override
	protected EditActivityResponse<DataInputJobRecordsSummaryStatusResponse> populateEditActivityResponse(
			Integer id,	ApplicationActivityResponse activity, EditActivityResponse<DataInputJobRecordsSummaryStatusResponse> response) 
					throws ApplicationException {
		// Load the fields for the DataInputJobRecordsSummaryStatus entity
		List<ApplicationEntityField> entityFields = dataInputJobRecordsSummaryStatusService.getEntityFields();
		List<ApplicationEntityFieldResponse> responseFields = new ArrayList<ApplicationEntityFieldResponse>();
		// Convert the entity fields into response fields
		for(ApplicationEntityField entityField:entityFields)
		{
			responseFields.add(applicationEntityFieldService.convertModelToResponse(entityField));
		}
		response.setFields(responseFields);
		response.setRelatedEntitiesListData(dataInputJobRecordsSummaryStatusService.relatedEntitesToListItems());
		if(id != null)
			response.setEntity(dataInputJobRecordsSummaryStatusService.convertModelToResponse(dataInputJobRecordsSummaryStatusService.findById(id)));
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
		return dataInputJobRecordsSummaryStatusService.relatedEntitesToListItems();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#saveEditedEntityInstance(java.lang.Object)
	 */
	@Override
	protected EditActivityResponse<DataInputJobRecordsSummaryStatusResponse> saveEntityInstance(
			DataInputJobRecordsSummaryStatusRequest entityInstance) throws ApplicationException {
		DataInputJobRecordsSummaryStatus dataInputJobRecordsSummaryStatus = dataInputJobRecordsSummaryStatusService.create(entityInstance);
		return this.getEditActivityResponse(dataInputJobRecordsSummaryStatus.getId());
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#saveEditedEntityInstance(java.lang.Object)
	 */
	@Override
	protected EditActivityResponse<DataInputJobRecordsSummaryStatusResponse> saveEditedEntityInstance(
			DataInputJobRecordsSummaryStatusRequest entityInstance) throws ApplicationException {
		DataInputJobRecordsSummaryStatus dataInputJobRecordsSummaryStatus = dataInputJobRecordsSummaryStatusService.update(entityInstance);
		return this.getEditActivityResponse(dataInputJobRecordsSummaryStatus.getId());
	}
	
	@Override
	protected ListActivityResponse<DataInputJobRecordsSummaryStatusResponse> deleteEntityInstances(
			List<Integer> idsOfEntitiesToDelete) throws ApplicationException {
		for(Integer id: idsOfEntitiesToDelete)
		{
			 dataInputJobRecordsSummaryStatusService.delete(id);
		}
		return this.getListActivityResponse(null);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getListActivityCode()
	 */
	@Override
	protected String getListActivityCode() throws ApplicationException {
		return dataInputJobRecordsSummaryStatusService.getListActivityCode();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getEditActivityCode()
	 */
	@Override
	protected String getEditActivityCode() throws ApplicationException {
		return dataInputJobRecordsSummaryStatusService.getEditActivityCode();
	}

}
