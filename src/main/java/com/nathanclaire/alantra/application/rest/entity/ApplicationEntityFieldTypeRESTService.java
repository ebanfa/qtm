/**
 * 
 */
package com.nathanclaire.alantra.application.rest.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.MultivaluedMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.application.model.ApplicationEntityFieldType;
import com.nathanclaire.alantra.application.model.ApplicationEntityField;
import com.nathanclaire.alantra.application.request.ApplicationEntityFieldTypeRequest;
import com.nathanclaire.alantra.application.response.ApplicationEntityFieldTypeResponse;
import com.nathanclaire.alantra.application.response.ApplicationActivityResponse;
import com.nathanclaire.alantra.application.response.ApplicationEntityFieldResponse;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityFieldTypeService;
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
@Path("/applicationentityfieldtype")
@Stateless
public class ApplicationEntityFieldTypeRESTService extends BaseActivityRESTService<ApplicationEntityFieldTypeResponse, ApplicationEntityFieldTypeRequest> 
{
	@Inject
	ApplicationEntityFieldTypeService applicationEntityFieldTypeService;
	
	@Inject 
	ApplicationEntityFieldService applicationEntityFieldService;
	
	private Logger logger = LoggerFactory.getLogger(ApplicationEntityFieldTypeRESTService.class);
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#populateListActivityResponse(com.nathanclaire.alantra.application.response.ApplicationEntityFieldTypeResponse, com.nathanclaire.alantra.base.response.ListActivityResponse, javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected ListActivityResponse<ApplicationEntityFieldTypeResponse> populateListActivityResponse(
			ApplicationActivityResponse activity, ListActivityResponse<ApplicationEntityFieldTypeResponse> response,
			MultivaluedMap<String, String> queryParameters) throws ApplicationException 
	{
		// Load the fields for the ApplicationEntityFieldType entity
		List<ApplicationEntityFieldResponse> responseFields = new ArrayList<ApplicationEntityFieldResponse>();
		List<ApplicationEntityField> entityFields = applicationEntityFieldTypeService.getEntityFields();
		for(ApplicationEntityField entityField:entityFields)
		{
			responseFields.add(applicationEntityFieldService.convertModelToResponse(entityField));
		}
		response.setFields(responseFields);
		// Load the list of ApplicationEntityFieldType's
		List<ApplicationEntityFieldTypeResponse> dataItems = new ArrayList<ApplicationEntityFieldTypeResponse>();
		for (ApplicationEntityFieldType item:applicationEntityFieldTypeService.findAll(queryParameters))
		{
			dataItems.add(applicationEntityFieldTypeService.convertModelToResponse(item));
		}
		response.setData(dataItems);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getInstance(java.lang.Integer)
	 */
	@Override
	protected EditActivityResponse<ApplicationEntityFieldTypeResponse> populateEditActivityResponse(
			Integer id,	ApplicationActivityResponse activity, EditActivityResponse<ApplicationEntityFieldTypeResponse> response) 
					throws ApplicationException {
		// Load the fields for the ApplicationEntityFieldType entity
		List<ApplicationEntityField> entityFields = applicationEntityFieldTypeService.getEntityFields();
		List<ApplicationEntityFieldResponse> responseFields = new ArrayList<ApplicationEntityFieldResponse>();
		// Convert the entity fields into response fields
		for(ApplicationEntityField entityField:entityFields)
		{
			responseFields.add(applicationEntityFieldService.convertModelToResponse(entityField));
		}
		response.setFields(responseFields);
		response.setRelatedEntitiesListData(applicationEntityFieldTypeService.relatedEntitesToListItems());
		if(id != null)
			response.setEntity(applicationEntityFieldTypeService.convertModelToResponse(applicationEntityFieldTypeService.findById(id)));
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
		return applicationEntityFieldTypeService.relatedEntitesToListItems();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#saveEditedEntityInstance(java.lang.Object)
	 */
	@Override
	protected EditActivityResponse<ApplicationEntityFieldTypeResponse> saveEntityInstance(
			ApplicationEntityFieldTypeRequest entityInstance) throws ApplicationException {
		ApplicationEntityFieldType applicationEntityFieldType = applicationEntityFieldTypeService.create(entityInstance);
		return this.getEditActivityResponse(applicationEntityFieldType.getId());
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#saveEditedEntityInstance(java.lang.Object)
	 */
	@Override
	protected EditActivityResponse<ApplicationEntityFieldTypeResponse> saveEditedEntityInstance(
			ApplicationEntityFieldTypeRequest entityInstance) throws ApplicationException {
		ApplicationEntityFieldType applicationEntityFieldType = applicationEntityFieldTypeService.update(entityInstance);
		return this.getEditActivityResponse(applicationEntityFieldType.getId());
	}
	
	@Override
	protected ListActivityResponse<ApplicationEntityFieldTypeResponse> deleteEntityInstances(
			List<Integer> idsOfEntitiesToDelete) throws ApplicationException {
		for(Integer id: idsOfEntitiesToDelete)
		{
			 applicationEntityFieldTypeService.delete(id);
		}
		return this.getListActivityResponse(null);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getListActivityCode()
	 */
	@Override
	protected String getListActivityCode() throws ApplicationException {
		return applicationEntityFieldTypeService.getListActivityCode();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getEditActivityCode()
	 */
	@Override
	protected String getEditActivityCode() throws ApplicationException {
		return applicationEntityFieldTypeService.getEditActivityCode();
	}

}
