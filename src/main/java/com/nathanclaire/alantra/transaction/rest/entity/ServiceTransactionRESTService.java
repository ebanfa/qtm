/**
 * 
 */
package com.nathanclaire.alantra.transaction.rest.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.advice.model.Advice;
import com.nathanclaire.alantra.application.model.ApplicationEntityField;
import com.nathanclaire.alantra.application.response.ApplicationActivityResponse;
import com.nathanclaire.alantra.application.response.ApplicationEntityFieldResponse;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityFieldService;
import com.nathanclaire.alantra.base.response.EditActivityResponse;
import com.nathanclaire.alantra.base.response.ListActivityResponse;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.rest.BaseActivityRESTService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.Messages;
import com.nathanclaire.alantra.transaction.model.ServiceTransaction;
import com.nathanclaire.alantra.transaction.request.ServiceTransactionRequest;
import com.nathanclaire.alantra.transaction.response.ServiceTransactionResponse;
import com.nathanclaire.alantra.transaction.service.entity.ServiceTransactionService;
import com.nathanclaire.alantra.transaction.service.process.TransactionMatchingService;

/**
 * @author administrator
 *
 */
@Path("/servicetransaction")
@Stateless
public class ServiceTransactionRESTService extends BaseActivityRESTService<ServiceTransactionResponse, ServiceTransactionRequest> 
{
	private static final String USR_TRANSACTION_NOT_MATCHED_ERROR = "ServiceTransactionRESTService.USR_TRANSACTION_NOT_MATCHED_ERROR";
	@Inject ServiceTransactionService serviceTransactionService;
	@Inject TransactionMatchingService transactionMatchingService;
	
	@Inject ApplicationEntityFieldService applicationEntityFieldService;
	
	private Logger logger = LoggerFactory.getLogger(ServiceTransactionRESTService.class);
	
	@GET
	@Path("/autoMatch")
    @Produces(MediaType.APPLICATION_JSON)
	public ListActivityResponse<ServiceTransactionResponse> autoMatchTransactions(
			@QueryParam ("idsOfTransactions[]") List<Integer> idsOfTransactions) throws ApplicationException
	{
		
		boolean transactionNotMatched = false;
    	ListActivityResponse<ServiceTransactionResponse> response = this.getListActivityResponse(null);
		for(Integer id: idsOfTransactions){
			try {
				ServiceTransaction transaction = serviceTransactionService.findById(id);
				if(transaction != null){
					Advice advice = transactionMatchingService.match(transaction);
					if(advice == null)
						transactionNotMatched = true;
				}
			} catch (ApplicationException e) {
				logger.error("Error matching transaction with id :{}. Error message: {}", e.getMessage());
				processActivityResponseException(response, e);
			}
		}
		if (transactionNotMatched) 
			processActivityResponseException(response, Messages.getString(USR_TRANSACTION_NOT_MATCHED_ERROR));
		return response;
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#populateListActivityResponse(com.nathanclaire.alantra.transaction.response.ServiceTransactionResponse, com.nathanclaire.alantra.base.response.ListActivityResponse, javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected ListActivityResponse<ServiceTransactionResponse> populateListActivityResponse(
			ApplicationActivityResponse activity, ListActivityResponse<ServiceTransactionResponse> response,
			MultivaluedMap<String, String> queryParameters) throws ApplicationException 
	{
		// Load the fields for the ServiceTransaction entity
		List<ApplicationEntityFieldResponse> responseFields = new ArrayList<ApplicationEntityFieldResponse>();
		List<ApplicationEntityField> entityFields = serviceTransactionService.getEntityFields();
		for(ApplicationEntityField entityField:entityFields)
		{
			responseFields.add(applicationEntityFieldService.convertModelToResponse(entityField));
		}
		response.setFields(responseFields);
		// Load the list of ServiceTransaction's
		List<ServiceTransactionResponse> dataItems = new ArrayList<ServiceTransactionResponse>();
		for (ServiceTransaction item:serviceTransactionService.findAll(queryParameters))
		{
			dataItems.add(serviceTransactionService.convertModelToResponse(item));
		}
		response.setData(dataItems);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getInstance(java.lang.Integer)
	 */
	@Override
	protected EditActivityResponse<ServiceTransactionResponse> populateEditActivityResponse(
			Integer id,	ApplicationActivityResponse activity, EditActivityResponse<ServiceTransactionResponse> response) 
					throws ApplicationException {
		// Load the fields for the ServiceTransaction entity
		List<ApplicationEntityField> entityFields = serviceTransactionService.getEntityFields();
		List<ApplicationEntityFieldResponse> responseFields = new ArrayList<ApplicationEntityFieldResponse>();
		// Convert the entity fields into response fields
		for(ApplicationEntityField entityField:entityFields)
		{
			responseFields.add(applicationEntityFieldService.convertModelToResponse(entityField));
		}
		response.setFields(responseFields);
		response.setRelatedEntitiesListData(serviceTransactionService.relatedEntitesToListItems());
		if(id != null)
			response.setEntity(serviceTransactionService.convertModelToResponse(serviceTransactionService.findById(id)));
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
		return serviceTransactionService.relatedEntitesToListItems();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#saveEditedEntityInstance(java.lang.Object)
	 */
	@Override
	protected EditActivityResponse<ServiceTransactionResponse> saveEntityInstance(
			ServiceTransactionRequest entityInstance) throws ApplicationException {
		ServiceTransaction serviceTransaction = serviceTransactionService.create(entityInstance);
		return this.getEditActivityResponse(serviceTransaction.getId());
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#saveEditedEntityInstance(java.lang.Object)
	 */
	@Override
	protected EditActivityResponse<ServiceTransactionResponse> saveEditedEntityInstance(
			ServiceTransactionRequest entityInstance) throws ApplicationException {
		ServiceTransaction serviceTransaction = serviceTransactionService.update(entityInstance);
		return this.getEditActivityResponse(serviceTransaction.getId());
	}
	
	@Override
	protected ListActivityResponse<ServiceTransactionResponse> deleteEntityInstances(
			List<Integer> idsOfEntitiesToDelete) throws ApplicationException {
		for(Integer id: idsOfEntitiesToDelete)
		{
			 serviceTransactionService.delete(id);
		}
		return this.getListActivityResponse(null);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getListActivityCode()
	 */
	@Override
	protected String getListActivityCode() throws ApplicationException {
		return serviceTransactionService.getListActivityCode();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getEditActivityCode()
	 */
	@Override
	protected String getEditActivityCode() throws ApplicationException {
		return serviceTransactionService.getEditActivityCode();
	}

}
