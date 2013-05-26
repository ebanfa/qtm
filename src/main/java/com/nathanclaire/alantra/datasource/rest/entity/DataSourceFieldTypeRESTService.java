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

import com.nathanclaire.alantra.datasource.model.DataSourceFieldType;
import com.nathanclaire.alantra.application.model.ApplicationEntityField;
import com.nathanclaire.alantra.datasource.request.DataSourceFieldTypeRequest;
import com.nathanclaire.alantra.datasource.response.DataSourceFieldTypeResponse;
import com.nathanclaire.alantra.application.response.ApplicationActivityResponse;
import com.nathanclaire.alantra.application.response.ApplicationEntityFieldResponse;
import com.nathanclaire.alantra.datasource.service.entity.DataSourceFieldTypeService;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityFieldService;
import com.nathanclaire.alantra.base.response.EditActivityResponse;
import com.nathanclaire.alantra.base.response.ListActivityResponse;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.rest.BaseActivityRESTService;

/**
 * @author administrator
 *
 */
@Path("/datasourcefieldtype")
@Stateless
public class DataSourceFieldTypeRESTService extends BaseActivityRESTService<DataSourceFieldTypeResponse, DataSourceFieldTypeRequest> 
{
	@Inject
	DataSourceFieldTypeService dataSourceFieldTypeService;
	@Inject 
	ApplicationEntityFieldService applicationEntityFieldService;
	
	private Logger logger = LoggerFactory.getLogger(DataSourceFieldTypeRESTService.class);

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#populateListActivityResponse(com.nathanclaire.alantra.datasource.response.DataSourceFieldTypeResponse, com.nathanclaire.alantra.base.response.ListActivityResponse, javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected ListActivityResponse<DataSourceFieldTypeResponse> populateListActivityResponse(
			ApplicationActivityResponse activity,
			ListActivityResponse<DataSourceFieldTypeResponse> response,
			MultivaluedMap<String, String> queryParameters) 
	{
		List<ApplicationEntityFieldResponse> responseFields = new ArrayList<ApplicationEntityFieldResponse>();
		// Load the fields for the DataSourceFieldType entity
		List<ApplicationEntityField> entityFields = dataSourceFieldTypeService.getEntityFields();
		for(ApplicationEntityField entityField:entityFields)
		{
			responseFields.add(applicationEntityFieldService.convertModelToResponse(entityField));
		}
		response.setFields(responseFields);
		// Load the list of DataSourceFieldType's
		List<DataSourceFieldTypeResponse> dataItems = new ArrayList<DataSourceFieldTypeResponse>();
		for (DataSourceFieldType item:dataSourceFieldTypeService.findAll(queryParameters))
		{
			dataItems.add(dataSourceFieldTypeService.convertModelToResponse(item));
		}
		response.setData(dataItems);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getInstance(java.lang.Integer)
	 */
	@Override
	protected EditActivityResponse<DataSourceFieldTypeResponse> populateEditActivityResponse(
			Integer id,	ApplicationActivityResponse activity, EditActivityResponse<DataSourceFieldTypeResponse> response) 
	{
		// Load the fields for the DataSourceFieldType entity
		List<ApplicationEntityField> entityFields = dataSourceFieldTypeService.getEntityFields();
		List<ApplicationEntityFieldResponse> responseFields = new ArrayList<ApplicationEntityFieldResponse>();
		// Convert the entity fields into response fields
		for(ApplicationEntityField entityField:entityFields)
		{
			responseFields.add(applicationEntityFieldService.convertModelToResponse(entityField));
		}
		response.setFields(responseFields);
		response.setRelatedEntitiesListData(dataSourceFieldTypeService.relatedEntitesToListItems());
		if(id != null)
			response.setEntity(dataSourceFieldTypeService.convertModelToResponse(dataSourceFieldTypeService.findById(id)));
		// The response will now have the id of the embedded entity (WHY)
		if(response.getEntity() != null)
			response.setId(response.getEntity().getId());
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#prepareRelatedEntitiesListItems(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected Map<String, List<ListItemResponse>> prepareRelatedEntitiesListItems(MultivaluedMap<String, String> multivaluedMap) {
		return dataSourceFieldTypeService.relatedEntitesToListItems();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#saveEditedEntityInstance(java.lang.Object)
	 */
	@Override
	protected EditActivityResponse<DataSourceFieldTypeResponse> saveEntityInstance(
			DataSourceFieldTypeRequest entityInstance) {
		dataSourceFieldTypeService.create(entityInstance);
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#saveEditedEntityInstance(java.lang.Object)
	 */
	@Override
	protected EditActivityResponse<DataSourceFieldTypeResponse> saveEditedEntityInstance(
			DataSourceFieldTypeRequest entityInstance) {
		dataSourceFieldTypeService.update(entityInstance);
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getListActivityCode()
	 */
	@Override
	protected String getListActivityCode() {
		return dataSourceFieldTypeService.getListActivityCode();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getEditActivityCode()
	 */
	@Override
	protected String getEditActivityCode() {
		return dataSourceFieldTypeService.getEditActivityCode();
	}

}
