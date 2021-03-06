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

import com.nathanclaire.alantra.datasource.model.DataField;
import com.nathanclaire.alantra.application.model.ApplicationEntityField;
import com.nathanclaire.alantra.datasource.request.DataFieldRequest;
import com.nathanclaire.alantra.datasource.response.DataFieldResponse;
import com.nathanclaire.alantra.application.response.ApplicationActivityResponse;
import com.nathanclaire.alantra.application.response.ApplicationEntityFieldResponse;
import com.nathanclaire.alantra.datasource.service.entity.DataFieldService;
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
@Path("/datafield")
@Stateless
public class DataFieldRESTService extends BaseActivityRESTService<DataFieldResponse, DataFieldRequest> 
{
	@Inject
	DataFieldService dataFieldService;
	
	@Inject 
	ApplicationEntityFieldService applicationEntityFieldService;
	
	private Logger logger = LoggerFactory.getLogger(DataFieldRESTService.class);
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#populateListActivityResponse(com.nathanclaire.alantra.datasource.response.DataFieldResponse, com.nathanclaire.alantra.base.response.ListActivityResponse, javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected ListActivityResponse<DataFieldResponse> populateListActivityResponse(
			ApplicationActivityResponse activity, ListActivityResponse<DataFieldResponse> response,
			MultivaluedMap<String, String> queryParameters) throws ApplicationException 
	{
		// Load the fields for the DataField entity
		List<ApplicationEntityFieldResponse> responseFields = new ArrayList<ApplicationEntityFieldResponse>();
		List<ApplicationEntityField> entityFields = dataFieldService.getEntityFields();
		for(ApplicationEntityField entityField:entityFields)
		{
			responseFields.add(applicationEntityFieldService.convertModelToResponse(entityField));
		}
		response.setFields(responseFields);
		// Load the list of DataField's
		List<DataFieldResponse> dataItems = new ArrayList<DataFieldResponse>();
		for (DataField item:dataFieldService.findAll(queryParameters))
		{
			dataItems.add(dataFieldService.convertModelToResponse(item));
		}
		response.setData(dataItems);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getInstance(java.lang.Integer)
	 */
	@Override
	protected EditActivityResponse<DataFieldResponse> populateEditActivityResponse(
			Integer id,	ApplicationActivityResponse activity, EditActivityResponse<DataFieldResponse> response) 
					throws ApplicationException {
		// Load the fields for the DataField entity
		List<ApplicationEntityField> entityFields = dataFieldService.getEntityFields();
		List<ApplicationEntityFieldResponse> responseFields = new ArrayList<ApplicationEntityFieldResponse>();
		// Convert the entity fields into response fields
		for(ApplicationEntityField entityField:entityFields)
		{
			responseFields.add(applicationEntityFieldService.convertModelToResponse(entityField));
		}
		response.setFields(responseFields);
		response.setRelatedEntitiesListData(dataFieldService.relatedEntitesToListItems());
		if(id != null)
			response.setEntity(dataFieldService.convertModelToResponse(dataFieldService.findById(id)));
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
		return dataFieldService.relatedEntitesToListItems();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#saveEditedEntityInstance(java.lang.Object)
	 */
	@Override
	protected EditActivityResponse<DataFieldResponse> saveEntityInstance(
			DataFieldRequest entityInstance) throws ApplicationException {
		DataField dataField = dataFieldService.create(entityInstance);
		return this.getEditActivityResponse(dataField.getId());
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#saveEditedEntityInstance(java.lang.Object)
	 */
	@Override
	protected EditActivityResponse<DataFieldResponse> saveEditedEntityInstance(
			DataFieldRequest entityInstance) throws ApplicationException {
		DataField dataField = dataFieldService.update(entityInstance);
		return this.getEditActivityResponse(dataField.getId());
	}
	
	@Override
	protected ListActivityResponse<DataFieldResponse> deleteEntityInstances(
			List<Integer> idsOfEntitiesToDelete) throws ApplicationException {
		for(Integer id: idsOfEntitiesToDelete)
		{
			 dataFieldService.delete(id);
		}
		return this.getListActivityResponse(null);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getListActivityCode()
	 */
	@Override
	protected String getListActivityCode() throws ApplicationException {
		return dataFieldService.getListActivityCode();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getEditActivityCode()
	 */
	@Override
	protected String getEditActivityCode() throws ApplicationException {
		return dataFieldService.getEditActivityCode();
	}

}
