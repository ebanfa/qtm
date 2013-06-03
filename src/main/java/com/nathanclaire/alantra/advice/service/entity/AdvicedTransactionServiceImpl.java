/**
 * 
 */
package com.nathanclaire.alantra.advice.service.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.MultivaluedMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.application.model.ApplicationEntityField;

import com.nathanclaire.alantra.advice.model.AdvicedTransaction;
import com.nathanclaire.alantra.advice.model.Advice;
import com.nathanclaire.alantra.channel.model.ServiceTransaction;
import com.nathanclaire.alantra.advice.request.AdvicedTransactionRequest;
import com.nathanclaire.alantra.advice.response.AdvicedTransactionResponse;
import com.nathanclaire.alantra.advice.service.entity.AdviceService;
import com.nathanclaire.alantra.channel.service.entity.ServiceTransactionService;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtils;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class AdvicedTransactionServiceImpl 
	extends BaseEntityServiceImpl<AdvicedTransaction, AdvicedTransactionResponse, AdvicedTransactionRequest> 
	implements AdvicedTransactionService
{
	private static final String LIST_ITEM_ADVICE = "advice";
	private static final String LIST_ITEM_SERVICETRANSACTION = "serviceTransaction";
	private static final String ENTITY_NAME = "AdvicedTransaction";
	private static final String LIST_ACTIVITY_CODE = "LIST_ADVICE_ADVICEDTRANSACTION";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_ADVICE_ADVICEDTRANSACTION";
	
	private Logger logger = LoggerFactory.getLogger(AdvicedTransactionServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	@Inject
	AdviceService  adviceService;
	@Inject
	ServiceTransactionService  serviceTransactionService;
	
	/**
	 * @param entityClass
	 */
	public AdvicedTransactionServiceImpl() {
		super(AdvicedTransaction.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.AdvicedTransaction#findById(java.lang.Integer)
	 */
	@Override
	public AdvicedTransaction findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.AdvicedTransaction#findByCode(java.lang.String)
	 */
	@Override
	public AdvicedTransaction findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.AdvicedTransaction#findByName(java.lang.String)
	 */
	@Override
	public AdvicedTransaction findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.AdvicedTransaction#findAll(java.util.Map)
	 */
	@Override
	public List<AdvicedTransaction> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.AdvicedTransaction#createAdvicedTransaction(com.nathanclaire.alantra.advice.rest.request.ServiceRequest)
	 */
	@Override
	public AdvicedTransaction create(AdvicedTransactionRequest advicedTransactionRequest) throws ApplicationException {
		return createInstance(advicedTransactionRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.AdvicedTransaction#deleteAdvicedTransaction(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.AdvicedTransaction#updateAdvicedTransaction(com.nathanclaire.alantra.advice.rest.request.ServiceRequest)
	 */
	@Override
	public AdvicedTransaction update(AdvicedTransactionRequest advicedTransactionRequest) throws ApplicationException {
		return updateInstance(advicedTransactionRequest);
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#getListActivityCode()
	 */
	@Override
	public String getListActivityCode() throws ApplicationException {
		return LIST_ACTIVITY_CODE;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#getEditActivityCode()
	 */
	@Override
	public String getEditActivityCode() throws ApplicationException {
		return EDIT_ACTIVITY_CODE;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#getEntityName()
	 */
	@Override
	public String getEntityName() throws ApplicationException {
		return ENTITY_NAME;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#getEntityFields()
	 */
	@Override
	public List<ApplicationEntityField> getEntityFields() throws ApplicationException {
		return applicationEntityService.getFieldsForEntity(ENTITY_NAME);
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#relatedEntitesToListItems()
	 */
	@Override
	public Map<String, List<ListItemResponse>> relatedEntitesToListItems() 
	 throws ApplicationException {
		Map<String, List<ListItemResponse>> listItems = new HashMap<String, List<ListItemResponse>>(); 
		List<ListItemResponse> advices = adviceService.asListItem();
		List<ListItemResponse> serviceTransactions = serviceTransactionService.asListItem();
    	
		listItems.put(LIST_ITEM_ADVICE, advices); 
		listItems.put(LIST_ITEM_SERVICETRANSACTION, serviceTransactions); 
		return listItems;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#asListItem()
	 */
	@Override
	public List<ListItemResponse> asListItem() throws ApplicationException {
		List<ListItemResponse> listItems = new ArrayList<ListItemResponse>();
		queryParameters.clear();
		for(AdvicedTransaction advicedtransaction: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(advicedtransaction.getId(), advicedtransaction.getCode(), advicedtransaction.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public AdvicedTransaction convertRequestToModel(AdvicedTransactionRequest advicedTransactionRequest) 
     throws ApplicationException {
		AdvicedTransaction advicedTransaction = new AdvicedTransaction();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(advicedTransactionRequest, advicedTransaction, allowedEntityFields);
    	//Process many to one relationships
    	if (advicedTransactionRequest.getAdviceId() != null)
    	{
    		Advice advice = getEntityManager().find(Advice.class, advicedTransactionRequest.getAdviceId());
    		advicedTransaction.setAdvice(advice);
    	}
    	if (advicedTransactionRequest.getServiceTransactionId() != null)
    	{
    		ServiceTransaction serviceTransaction = getEntityManager().find(ServiceTransaction.class, advicedTransactionRequest.getServiceTransactionId());
    		advicedTransaction.setServiceTransaction(serviceTransaction);
    	}
		return advicedTransaction;
	}
	
	@Override
	public AdvicedTransactionResponse convertModelToResponse(AdvicedTransaction model) throws ApplicationException {
		if (model == null) return null;
		AdvicedTransactionResponse advicedTransactionResponse = new AdvicedTransactionResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(model, advicedTransactionResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		if(model.getAdvice() != null)
			advicedTransactionResponse.setAdviceId(model.getAdvice().getId());
			advicedTransactionResponse.setAdviceText(model.getAdvice().getName());
		if(model.getServiceTransaction() != null)
			advicedTransactionResponse.setServiceTransactionId(model.getServiceTransaction().getId());
			advicedTransactionResponse.setServiceTransactionText(model.getServiceTransaction().getName());
		return advicedTransactionResponse;
	}
}
