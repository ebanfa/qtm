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

import com.nathanclaire.alantra.channel.model.ServiceProtocolAdapter;
import com.nathanclaire.alantra.application.model.ApplicationEntityField;
import com.nathanclaire.alantra.channel.request.ServiceProtocolAdapterRequest;
import com.nathanclaire.alantra.channel.response.ServiceProtocolAdapterResponse;
import com.nathanclaire.alantra.application.response.ApplicationActivityResponse;
import com.nathanclaire.alantra.application.response.ApplicationEntityFieldResponse;
import com.nathanclaire.alantra.channel.service.entity.ServiceProtocolAdapterService;
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
@Path("/serviceprotocoladapter")
@Stateless
public class ServiceProtocolAdapterRESTService extends BaseActivityRESTService<ServiceProtocolAdapterResponse, ServiceProtocolAdapterRequest> 
{
	@Inject
	ServiceProtocolAdapterService serviceProtocolAdapterService;
	
	@Inject 
	ApplicationEntityFieldService applicationEntityFieldService;
	
	private Logger logger = LoggerFactory.getLogger(ServiceProtocolAdapterRESTService.class);
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#populateListActivityResponse(com.nathanclaire.alantra.channel.response.ServiceProtocolAdapterResponse, com.nathanclaire.alantra.base.response.ListActivityResponse, javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected ListActivityResponse<ServiceProtocolAdapterResponse> populateListActivityResponse(
			ApplicationActivityResponse activity, ListActivityResponse<ServiceProtocolAdapterResponse> response,
			MultivaluedMap<String, String> queryParameters) throws ApplicationException 
	{
		// Load the fields for the ServiceProtocolAdapter entity
		List<ApplicationEntityFieldResponse> responseFields = new ArrayList<ApplicationEntityFieldResponse>();
		List<ApplicationEntityField> entityFields = serviceProtocolAdapterService.getEntityFields();
		for(ApplicationEntityField entityField:entityFields)
		{
			responseFields.add(applicationEntityFieldService.convertModelToResponse(entityField));
		}
		response.setFields(responseFields);
		// Load the list of ServiceProtocolAdapter's
		List<ServiceProtocolAdapterResponse> dataItems = new ArrayList<ServiceProtocolAdapterResponse>();
		for (ServiceProtocolAdapter item:serviceProtocolAdapterService.findAll(queryParameters))
		{
			dataItems.add(serviceProtocolAdapterService.convertModelToResponse(item));
		}
		response.setData(dataItems);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getInstance(java.lang.Integer)
	 */
	@Override
	protected EditActivityResponse<ServiceProtocolAdapterResponse> populateEditActivityResponse(
			Integer id,	ApplicationActivityResponse activity, EditActivityResponse<ServiceProtocolAdapterResponse> response) 
					throws ApplicationException {
		// Load the fields for the ServiceProtocolAdapter entity
		List<ApplicationEntityField> entityFields = serviceProtocolAdapterService.getEntityFields();
		List<ApplicationEntityFieldResponse> responseFields = new ArrayList<ApplicationEntityFieldResponse>();
		// Convert the entity fields into response fields
		for(ApplicationEntityField entityField:entityFields)
		{
			responseFields.add(applicationEntityFieldService.convertModelToResponse(entityField));
		}
		response.setFields(responseFields);
		response.setRelatedEntitiesListData(serviceProtocolAdapterService.relatedEntitesToListItems());
		if(id != null)
			response.setEntity(serviceProtocolAdapterService.convertModelToResponse(serviceProtocolAdapterService.findById(id)));
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
		return serviceProtocolAdapterService.relatedEntitesToListItems();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#saveEditedEntityInstance(java.lang.Object)
	 */
	@Override
	protected EditActivityResponse<ServiceProtocolAdapterResponse> saveEntityInstance(
			ServiceProtocolAdapterRequest entityInstance) throws ApplicationException {
		ServiceProtocolAdapter serviceProtocolAdapter = serviceProtocolAdapterService.create(entityInstance);
		return this.getEditActivityResponse(serviceProtocolAdapter.getId());
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#saveEditedEntityInstance(java.lang.Object)
	 */
	@Override
	protected EditActivityResponse<ServiceProtocolAdapterResponse> saveEditedEntityInstance(
			ServiceProtocolAdapterRequest entityInstance) throws ApplicationException {
		ServiceProtocolAdapter serviceProtocolAdapter = serviceProtocolAdapterService.update(entityInstance);
		return this.getEditActivityResponse(serviceProtocolAdapter.getId());
	}
	
	@Override
	protected ListActivityResponse<ServiceProtocolAdapterResponse> deleteEntityInstances(
			List<Integer> idsOfEntitiesToDelete) throws ApplicationException {
		for(Integer id: idsOfEntitiesToDelete)
		{
			 serviceProtocolAdapterService.delete(id);
		}
		return this.getListActivityResponse(null);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getListActivityCode()
	 */
	@Override
	protected String getListActivityCode() throws ApplicationException {
		return serviceProtocolAdapterService.getListActivityCode();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getEditActivityCode()
	 */
	@Override
	protected String getEditActivityCode() throws ApplicationException {
		return serviceProtocolAdapterService.getEditActivityCode();
	}

}
