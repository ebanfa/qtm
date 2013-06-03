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

import com.nathanclaire.alantra.channel.model.ServiceTransactionStatus;
import com.nathanclaire.alantra.application.model.ApplicationEntityField;
import com.nathanclaire.alantra.channel.request.ServiceTransactionStatusRequest;
import com.nathanclaire.alantra.channel.response.ServiceTransactionStatusResponse;
import com.nathanclaire.alantra.application.response.ApplicationActivityResponse;
import com.nathanclaire.alantra.application.response.ApplicationEntityFieldResponse;
import com.nathanclaire.alantra.channel.service.entity.ServiceTransactionStatusService;
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
@Path("/servicetransactionstatus")
@Stateless
public class ServiceTransactionStatusRESTService extends BaseActivityRESTService<ServiceTransactionStatusResponse, ServiceTransactionStatusRequest> 
{
	@Inject
	ServiceTransactionStatusService serviceTransactionStatusService;
	
	@Inject 
	ApplicationEntityFieldService applicationEntityFieldService;
	
	private Logger logger = LoggerFactory.getLogger(ServiceTransactionStatusRESTService.class);
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#populateListActivityResponse(com.nathanclaire.alantra.channel.response.ServiceTransactionStatusResponse, com.nathanclaire.alantra.base.response.ListActivityResponse, javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected ListActivityResponse<ServiceTransactionStatusResponse> populateListActivityResponse(
			ApplicationActivityResponse activity, ListActivityResponse<ServiceTransactionStatusResponse> response,
			MultivaluedMap<String, String> queryParameters) throws ApplicationException 
	{
		// Load the fields for the ServiceTransactionStatus entity
		List<ApplicationEntityFieldResponse> responseFields = new ArrayList<ApplicationEntityFieldResponse>();
		List<ApplicationEntityField> entityFields = serviceTransactionStatusService.getEntityFields();
		for(ApplicationEntityField entityField:entityFields)
		{
			responseFields.add(applicationEntityFieldService.convertModelToResponse(entityField));
		}
		response.setFields(responseFields);
		// Load the list of ServiceTransactionStatus's
		List<ServiceTransactionStatusResponse> dataItems = new ArrayList<ServiceTransactionStatusResponse>();
		for (ServiceTransactionStatus item:serviceTransactionStatusService.findAll(queryParameters))
		{
			dataItems.add(serviceTransactionStatusService.convertModelToResponse(item));
		}
		response.setData(dataItems);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getInstance(java.lang.Integer)
	 */
	@Override
	protected EditActivityResponse<ServiceTransactionStatusResponse> populateEditActivityResponse(
			Integer id,	ApplicationActivityResponse activity, EditActivityResponse<ServiceTransactionStatusResponse> response) 
					throws ApplicationException {
		// Load the fields for the ServiceTransactionStatus entity
		List<ApplicationEntityField> entityFields = serviceTransactionStatusService.getEntityFields();
		List<ApplicationEntityFieldResponse> responseFields = new ArrayList<ApplicationEntityFieldResponse>();
		// Convert the entity fields into response fields
		for(ApplicationEntityField entityField:entityFields)
		{
			responseFields.add(applicationEntityFieldService.convertModelToResponse(entityField));
		}
		response.setFields(responseFields);
		response.setRelatedEntitiesListData(serviceTransactionStatusService.relatedEntitesToListItems());
		if(id != null)
			response.setEntity(serviceTransactionStatusService.convertModelToResponse(serviceTransactionStatusService.findById(id)));
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
		return serviceTransactionStatusService.relatedEntitesToListItems();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#saveEditedEntityInstance(java.lang.Object)
	 */
	@Override
	protected EditActivityResponse<ServiceTransactionStatusResponse> saveEntityInstance(
			ServiceTransactionStatusRequest entityInstance) throws ApplicationException {
		ServiceTransactionStatus serviceTransactionStatus = serviceTransactionStatusService.create(entityInstance);
		return this.getEditActivityResponse(serviceTransactionStatus.getId());
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#saveEditedEntityInstance(java.lang.Object)
	 */
	@Override
	protected EditActivityResponse<ServiceTransactionStatusResponse> saveEditedEntityInstance(
			ServiceTransactionStatusRequest entityInstance) throws ApplicationException {
		ServiceTransactionStatus serviceTransactionStatus = serviceTransactionStatusService.update(entityInstance);
		return this.getEditActivityResponse(serviceTransactionStatus.getId());
	}
	
	@Override
	protected ListActivityResponse<ServiceTransactionStatusResponse> deleteEntityInstances(
			List<Integer> idsOfEntitiesToDelete) throws ApplicationException {
		for(Integer id: idsOfEntitiesToDelete)
		{
			 serviceTransactionStatusService.delete(id);
		}
		return this.getListActivityResponse(null);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getListActivityCode()
	 */
	@Override
	protected String getListActivityCode() throws ApplicationException {
		return serviceTransactionStatusService.getListActivityCode();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getEditActivityCode()
	 */
	@Override
	protected String getEditActivityCode() throws ApplicationException {
		return serviceTransactionStatusService.getEditActivityCode();
	}

}
