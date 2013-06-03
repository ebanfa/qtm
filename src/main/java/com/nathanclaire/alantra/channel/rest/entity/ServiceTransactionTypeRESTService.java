/**
 * 
 */
package com.nathanclaire.alantra.channel.rest.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.MultivaluedMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.channel.model.ServiceTransactionType;
import com.nathanclaire.alantra.application.model.ApplicationEntityField;
import com.nathanclaire.alantra.channel.request.ServiceTransactionTypeRequest;
import com.nathanclaire.alantra.channel.response.ServiceTransactionTypeResponse;
import com.nathanclaire.alantra.application.response.ApplicationActivityResponse;
import com.nathanclaire.alantra.application.response.ApplicationEntityFieldResponse;
import com.nathanclaire.alantra.channel.service.entity.ServiceTransactionTypeService;
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
@Path("/servicetransactiontype")
@Stateless
public class ServiceTransactionTypeRESTService extends BaseActivityRESTService<ServiceTransactionTypeResponse, ServiceTransactionTypeRequest> 
{
	@Inject
	ServiceTransactionTypeService serviceTransactionTypeService;
	
	@Inject 
	ApplicationEntityFieldService applicationEntityFieldService;
	
	private Logger logger = LoggerFactory.getLogger(ServiceTransactionTypeRESTService.class);
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#populateListActivityResponse(com.nathanclaire.alantra.channel.response.ServiceTransactionTypeResponse, com.nathanclaire.alantra.base.response.ListActivityResponse, javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected ListActivityResponse<ServiceTransactionTypeResponse> populateListActivityResponse(
			ApplicationActivityResponse activity, ListActivityResponse<ServiceTransactionTypeResponse> response,
			MultivaluedMap<String, String> queryParameters) throws ApplicationException 
	{
		// Load the fields for the ServiceTransactionType entity
		List<ApplicationEntityFieldResponse> responseFields = new ArrayList<ApplicationEntityFieldResponse>();
		List<ApplicationEntityField> entityFields = serviceTransactionTypeService.getEntityFields();
		for(ApplicationEntityField entityField:entityFields)
		{
			responseFields.add(applicationEntityFieldService.convertModelToResponse(entityField));
		}
		response.setFields(responseFields);
		// Load the list of ServiceTransactionType's
		List<ServiceTransactionTypeResponse> dataItems = new ArrayList<ServiceTransactionTypeResponse>();
		for (ServiceTransactionType item:serviceTransactionTypeService.findAll(queryParameters))
		{
			dataItems.add(serviceTransactionTypeService.convertModelToResponse(item));
		}
		response.setData(dataItems);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getInstance(java.lang.Integer)
	 */
	@Override
	protected EditActivityResponse<ServiceTransactionTypeResponse> populateEditActivityResponse(
			Integer id,	ApplicationActivityResponse activity, EditActivityResponse<ServiceTransactionTypeResponse> response) 
					throws ApplicationException {
		// Load the fields for the ServiceTransactionType entity
		List<ApplicationEntityField> entityFields = serviceTransactionTypeService.getEntityFields();
		List<ApplicationEntityFieldResponse> responseFields = new ArrayList<ApplicationEntityFieldResponse>();
		// Convert the entity fields into response fields
		for(ApplicationEntityField entityField:entityFields)
		{
			responseFields.add(applicationEntityFieldService.convertModelToResponse(entityField));
		}
		response.setFields(responseFields);
		response.setRelatedEntitiesListData(serviceTransactionTypeService.relatedEntitesToListItems());
		if(id != null)
			response.setEntity(serviceTransactionTypeService.convertModelToResponse(serviceTransactionTypeService.findById(id)));
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
		return serviceTransactionTypeService.relatedEntitesToListItems();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#saveEditedEntityInstance(java.lang.Object)
	 */
	@Override
	protected EditActivityResponse<ServiceTransactionTypeResponse> saveEntityInstance(
			ServiceTransactionTypeRequest entityInstance) throws ApplicationException {
		ServiceTransactionType serviceTransactionType = serviceTransactionTypeService.create(entityInstance);
		return this.getEditActivityResponse(serviceTransactionType.getId());
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#saveEditedEntityInstance(java.lang.Object)
	 */
	@Override
	protected EditActivityResponse<ServiceTransactionTypeResponse> saveEditedEntityInstance(
			ServiceTransactionTypeRequest entityInstance) throws ApplicationException {
		ServiceTransactionType serviceTransactionType = serviceTransactionTypeService.update(entityInstance);
		return this.getEditActivityResponse(serviceTransactionType.getId());
	}
	
	@Override
	protected ListActivityResponse<ServiceTransactionTypeResponse> deleteEntityInstances(
			List<Integer> idsOfEntitiesToDelete) throws ApplicationException {
		for(Integer id: idsOfEntitiesToDelete)
		{
			 serviceTransactionTypeService.delete(id);
		}
		return this.getListActivityResponse(null);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getListActivityCode()
	 */
	@Override
	protected String getListActivityCode() throws ApplicationException {
		return serviceTransactionTypeService.getListActivityCode();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getEditActivityCode()
	 */
	@Override
	protected String getEditActivityCode() throws ApplicationException {
		return serviceTransactionTypeService.getEditActivityCode();
	}

}
