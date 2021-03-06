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

import com.nathanclaire.alantra.datasource.model.Data;
import com.nathanclaire.alantra.application.model.ApplicationEntityField;
import com.nathanclaire.alantra.datasource.request.DataRequest;
import com.nathanclaire.alantra.datasource.response.DataResponse;
import com.nathanclaire.alantra.application.response.ApplicationActivityResponse;
import com.nathanclaire.alantra.application.response.ApplicationEntityFieldResponse;
import com.nathanclaire.alantra.datasource.service.entity.DataEntityService;
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
@Path("/data")
@Stateless
public class DataRESTService extends BaseActivityRESTService<DataResponse, DataRequest> 
{
	@Inject
	DataEntityService dataEntityService;
	
	@Inject 
	ApplicationEntityFieldService applicationEntityFieldService;
	
	private Logger logger = LoggerFactory.getLogger(DataRESTService.class);
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#populateListActivityResponse(com.nathanclaire.alantra.datasource.response.DataResponse, com.nathanclaire.alantra.base.response.ListActivityResponse, javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected ListActivityResponse<DataResponse> populateListActivityResponse(
			ApplicationActivityResponse activity, ListActivityResponse<DataResponse> response,
			MultivaluedMap<String, String> queryParameters) throws ApplicationException 
	{
		// Load the fields for the Data entity
		List<ApplicationEntityFieldResponse> responseFields = new ArrayList<ApplicationEntityFieldResponse>();
		List<ApplicationEntityField> entityFields = dataEntityService.getEntityFields();
		for(ApplicationEntityField entityField:entityFields)
		{
			responseFields.add(applicationEntityFieldService.convertModelToResponse(entityField));
		}
		response.setFields(responseFields);
		// Load the list of Data's
		List<DataResponse> dataItems = new ArrayList<DataResponse>();
		for (Data item:dataEntityService.findAll(queryParameters))
		{
			dataItems.add(dataEntityService.convertModelToResponse(item));
		}
		response.setData(dataItems);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getInstance(java.lang.Integer)
	 */
	@Override
	protected EditActivityResponse<DataResponse> populateEditActivityResponse(
			Integer id,	ApplicationActivityResponse activity, EditActivityResponse<DataResponse> response) 
					throws ApplicationException {
		// Load the fields for the Data entity
		List<ApplicationEntityField> entityFields = dataEntityService.getEntityFields();
		List<ApplicationEntityFieldResponse> responseFields = new ArrayList<ApplicationEntityFieldResponse>();
		// Convert the entity fields into response fields
		for(ApplicationEntityField entityField:entityFields)
		{
			responseFields.add(applicationEntityFieldService.convertModelToResponse(entityField));
		}
		response.setFields(responseFields);
		response.setRelatedEntitiesListData(dataEntityService.relatedEntitesToListItems());
		if(id != null)
			response.setEntity(dataEntityService.convertModelToResponse(dataEntityService.findById(id)));
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
		return dataEntityService.relatedEntitesToListItems();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#saveEditedEntityInstance(java.lang.Object)
	 */
	@Override
	protected EditActivityResponse<DataResponse> saveEntityInstance(
			DataRequest entityInstance) throws ApplicationException {
		Data data = dataEntityService.create(entityInstance);
		return this.getEditActivityResponse(data.getId());
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#saveEditedEntityInstance(java.lang.Object)
	 */
	@Override
	protected EditActivityResponse<DataResponse> saveEditedEntityInstance(
			DataRequest entityInstance) throws ApplicationException {
		Data data = dataEntityService.update(entityInstance);
		return this.getEditActivityResponse(data.getId());
	}
	
	@Override
	protected ListActivityResponse<DataResponse> deleteEntityInstances(
			List<Integer> idsOfEntitiesToDelete) throws ApplicationException {
		for(Integer id: idsOfEntitiesToDelete)
		{
			 dataEntityService.delete(id);
		}
		return this.getListActivityResponse(null);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getListActivityCode()
	 */
	@Override
	protected String getListActivityCode() throws ApplicationException {
		return dataEntityService.getListActivityCode();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getEditActivityCode()
	 */
	@Override
	protected String getEditActivityCode() throws ApplicationException {
		return dataEntityService.getEditActivityCode();
	}

}
