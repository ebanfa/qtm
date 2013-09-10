/**
 * Alantra.
 */
package com.nathanclaire.alantra.rule.rest.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.MultivaluedMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.rule.model.ParameterTypeOperator;
import com.nathanclaire.alantra.application.model.ApplicationEntityField;
import com.nathanclaire.alantra.rule.request.ParameterTypeOperatorRequest;
import com.nathanclaire.alantra.rule.response.ParameterTypeOperatorResponse;
import com.nathanclaire.alantra.application.response.ApplicationActivityResponse;
import com.nathanclaire.alantra.application.response.ApplicationEntityFieldResponse;
import com.nathanclaire.alantra.rule.service.entity.ParameterTypeOperatorService;
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
@Path("/parametertypeoperator")
@Stateless
public class ParameterTypeOperatorRESTService extends BaseActivityRESTService<ParameterTypeOperatorResponse, ParameterTypeOperatorRequest> 
{
	@Inject
	ParameterTypeOperatorService parameterTypeOperatorService;
	
	@Inject 
	ApplicationEntityFieldService applicationEntityFieldService;
	
	private Logger logger = LoggerFactory.getLogger(ParameterTypeOperatorRESTService.class);
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#populateListActivityResponse(com.nathanclaire.alantra.parameterTypeOperator.response.ParameterTypeOperatorResponse, com.nathanclaire.alantra.base.response.ListActivityResponse, javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected ListActivityResponse<ParameterTypeOperatorResponse> populateListActivityResponse(
			ApplicationActivityResponse activity, ListActivityResponse<ParameterTypeOperatorResponse> response,
			MultivaluedMap<String, String> queryParameters) throws ApplicationException 
	{
		// Load the fields for the ParameterTypeOperator entity
		List<ApplicationEntityFieldResponse> responseFields = new ArrayList<ApplicationEntityFieldResponse>();
		List<ApplicationEntityField> entityFields = parameterTypeOperatorService.getEntityFields();
		for(ApplicationEntityField entityField:entityFields)
		{
			responseFields.add(applicationEntityFieldService.convertModelToResponse(entityField));
		}
		response.setFields(responseFields);
		// Load the list of ParameterTypeOperator's
		List<ParameterTypeOperatorResponse> dataItems = new ArrayList<ParameterTypeOperatorResponse>();
		for (ParameterTypeOperator item:parameterTypeOperatorService.findAll(queryParameters))
		{
			dataItems.add(parameterTypeOperatorService.convertModelToResponse(item));
		}
		response.setData(dataItems);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getInstance(java.lang.Integer)
	 */
	@Override
	protected EditActivityResponse<ParameterTypeOperatorResponse> populateEditActivityResponse(
			Integer id,	ApplicationActivityResponse activity, EditActivityResponse<ParameterTypeOperatorResponse> response) 
					throws ApplicationException {
		// Load the fields for the ParameterTypeOperator entity
		List<ApplicationEntityField> entityFields = parameterTypeOperatorService.getEntityFields();
		List<ApplicationEntityFieldResponse> responseFields = new ArrayList<ApplicationEntityFieldResponse>();
		// Convert the entity fields into response fields
		for(ApplicationEntityField entityField:entityFields)
		{
			responseFields.add(applicationEntityFieldService.convertModelToResponse(entityField));
		}
		response.setFields(responseFields);
		response.setRelatedEntitiesListData(parameterTypeOperatorService.relatedEntitesToListItems());
		if(id != null)
			response.setEntity(parameterTypeOperatorService.convertModelToResponse(parameterTypeOperatorService.findById(id)));
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
		return parameterTypeOperatorService.relatedEntitesToListItems();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#saveEditedEntityInstance(java.lang.Object)
	 */
	@Override
	protected EditActivityResponse<ParameterTypeOperatorResponse> saveEntityInstance(
			ParameterTypeOperatorRequest entityInstance) throws ApplicationException {
		ParameterTypeOperator parameterTypeOperator = parameterTypeOperatorService.create(entityInstance);
		return this.getEditActivityResponse(parameterTypeOperator.getId());
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#saveEditedEntityInstance(java.lang.Object)
	 */
	@Override
	protected EditActivityResponse<ParameterTypeOperatorResponse> saveEditedEntityInstance(
			ParameterTypeOperatorRequest entityInstance) throws ApplicationException {
		ParameterTypeOperator parameterTypeOperator = parameterTypeOperatorService.update(entityInstance);
		return this.getEditActivityResponse(parameterTypeOperator.getId());
	}
	
	@Override
	protected ListActivityResponse<ParameterTypeOperatorResponse> deleteEntityInstances(
			List<Integer> idsOfEntitiesToDelete) throws ApplicationException {
		for(Integer id: idsOfEntitiesToDelete)
		{
			 parameterTypeOperatorService.delete(id);
		}
		return this.getListActivityResponse(null);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getListActivityCode()
	 */
	@Override
	protected String getListActivityCode() throws ApplicationException {
		return parameterTypeOperatorService.getListActivityCode();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getEditActivityCode()
	 */
	@Override
	protected String getEditActivityCode() throws ApplicationException {
		return parameterTypeOperatorService.getEditActivityCode();
	}

}
