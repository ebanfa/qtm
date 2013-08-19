/**
 * 
 */
package com.nathanclaire.alantra.transaction.service.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.ws.rs.core.MultivaluedMap;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.application.model.ApplicationEntityField;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtils;
import com.nathanclaire.alantra.transaction.model.ServiceTransaction;
import com.nathanclaire.alantra.transaction.model.TxnAwaitingConfirmation;
import com.nathanclaire.alantra.transaction.model.TxnConfirmationStatus;
import com.nathanclaire.alantra.transaction.request.TxnAwaitingConfirmationRequest;
import com.nathanclaire.alantra.transaction.response.TxnAwaitingConfirmationResponse;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class TxnAwaitingConfirmationServiceImpl 
	extends BaseEntityServiceImpl<TxnAwaitingConfirmation, TxnAwaitingConfirmationResponse, TxnAwaitingConfirmationRequest> 
	implements TxnAwaitingConfirmationService
{

private static final String LIST_ITEM_TXNCONFIRMATIONSTATUS = "txnConfirmationStatus";
private static final String LIST_ITEM_SERVICETRANSACTION = "serviceTransaction";
	private static final String ENTITY_NAME = "TxnAwaitingConfirmation";
	private static final String LIST_ACTIVITY_CODE = "LIST_TRANSACTION_TXNAWAITINGCONFIRMATION";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_TRANSACTION_TXNAWAITINGCONFIRMATION";
	
	private Logger logger = LoggerFactory.getLogger(TxnAwaitingConfirmationServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	@Inject TxnConfirmationStatusService  txnConfirmationStatusService;
	@Inject ServiceTransactionService  serviceTransactionService;
	
	/**
	 * @param entityClass
	 */
	public TxnAwaitingConfirmationServiceImpl() {
		super(TxnAwaitingConfirmation.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.txnAwaitingConfirmation.service.TxnAwaitingConfirmation#findById(java.lang.Integer)
	 */
	@Override
	public TxnAwaitingConfirmation findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.txnAwaitingConfirmation.service.TxnAwaitingConfirmation#findByCode(java.lang.String)
	 */
	@Override
	public TxnAwaitingConfirmation findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.transaction.service.TxnAwaitingConfirmation#findByName(java.lang.String)
	 */
	@Override
	public TxnAwaitingConfirmation findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.transaction.service.TxnAwaitingConfirmation#findAll(java.util.Map)
	 */
	@Override
	public List<TxnAwaitingConfirmation> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.transaction.service.TxnAwaitingConfirmation#createTxnAwaitingConfirmation(com.nathanclaire.alantra.transaction.rest.request.ServiceRequest)
	 */
	@Override
	public TxnAwaitingConfirmation create(TxnAwaitingConfirmationRequest txnAwaitingConfirmationRequest) throws ApplicationException {
		return createInstance(txnAwaitingConfirmationRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.transaction.service.TxnAwaitingConfirmation#deleteTxnAwaitingConfirmation(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.transaction.service.TxnAwaitingConfirmation#updateTxnAwaitingConfirmation(com.nathanclaire.alantra.txnAwaitingConfirmation.rest.request.ServiceRequest)
	 */
	@Override
	public TxnAwaitingConfirmation update(TxnAwaitingConfirmationRequest txnAwaitingConfirmationRequest) throws ApplicationException {
		return updateInstance(txnAwaitingConfirmationRequest);
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
        List<ListItemResponse> txnConfirmationStatuss = txnConfirmationStatusService.asListItem();
        List<ListItemResponse> serviceTransactions = serviceTransactionService.asListItem();

        listItems.put(LIST_ITEM_TXNCONFIRMATIONSTATUS, txnConfirmationStatuss);
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
		for(TxnAwaitingConfirmation txnAwaitingConfirmation: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(txnAwaitingConfirmation.getId(), txnAwaitingConfirmation.getCode(), txnAwaitingConfirmation.getCode());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public TxnAwaitingConfirmation convertRequestToModel(TxnAwaitingConfirmationRequest txnAwaitingConfirmationRequest) 
     throws ApplicationException {
		TxnAwaitingConfirmation txnAwaitingConfirmation = new TxnAwaitingConfirmation();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(txnAwaitingConfirmationRequest, txnAwaitingConfirmation, allowedEntityFields);
    	//Process many to one relationships
        if (txnAwaitingConfirmationRequest.getTxnConfirmationStatusId() != null)
    	{
    		TxnConfirmationStatus txnConfirmationStatus = getEntityManager().find(TxnConfirmationStatus.class, txnAwaitingConfirmationRequest.getTxnConfirmationStatusId());
    		txnAwaitingConfirmation.setTxnConfirmationStatus(txnConfirmationStatus);
    	}
        if (txnAwaitingConfirmationRequest.getServiceTransactionId() != null)
    	{
    		ServiceTransaction serviceTransaction = getEntityManager().find(ServiceTransaction.class, txnAwaitingConfirmationRequest.getServiceTransactionId());
    		txnAwaitingConfirmation.setServiceTransaction(serviceTransaction);
    	}
		return txnAwaitingConfirmation;
	}
	
	@Override
	public TxnAwaitingConfirmationResponse convertModelToResponse(TxnAwaitingConfirmation model) throws ApplicationException {
		if (model == null) return null;
		TxnAwaitingConfirmationResponse txnAwaitingConfirmationResponse = new TxnAwaitingConfirmationResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(model, txnAwaitingConfirmationResponse, allowedEntityFields);
		if(model.getTxnConfirmationStatus() != null)
			txnAwaitingConfirmationResponse.setTxnConfirmationStatusId(model.getTxnConfirmationStatus().getId());
			txnAwaitingConfirmationResponse.setTxnConfirmationStatusText(model.getTxnConfirmationStatus().getName());
		if(model.getServiceTransaction() != null)
			txnAwaitingConfirmationResponse.setServiceTransactionId(model.getServiceTransaction().getId());
			txnAwaitingConfirmationResponse.setServiceTransactionText(model.getServiceTransaction().getName());
		return txnAwaitingConfirmationResponse;
	}

	/**
	 * @param customerId
	 * @param accountId
	 * @param chequeNo
	 * @param currencyId
	 * @param txnAwaitingConfirmationTypeId
	 * @param amount
	 */
	private void initializeQueryParameters(Integer txnConfirmationStatusId, Integer serviceTransactionId, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) {
		queryParameters.clear();
        if(txnConfirmationStatusId != null)
            queryParameters.add("txnConfirmationStatus", txnConfirmationStatusId.toString());
        if(serviceTransactionId != null)
            queryParameters.add("serviceTransaction", serviceTransactionId.toString());
        if(id != null)
            queryParameters.add("id", id.toString());
        if(code != null)
            queryParameters.add("code", code.toString());
        if(effectiveDt != null)
            queryParameters.add("effectiveDt", effectiveDt.toString());
        if(recSt != null)
            queryParameters.add("recSt", recSt.toString());
        if(versionNo != null)
            queryParameters.add("versionNo", versionNo.toString());
        if(rowTs != null)
            queryParameters.add("rowTs", rowTs.toString());
        if(createdDt != null)
            queryParameters.add("createdDt", createdDt.toString());
        if(createdByUsr != null)
            queryParameters.add("createdByUsr", createdByUsr.toString());
        if(lastModifiedDt != null)
            queryParameters.add("lastModifiedDt", lastModifiedDt.toString());
        if(lastModifiedUsr != null)
            queryParameters.add("lastModifiedUsr", lastModifiedUsr.toString());
		
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl#extractPredicates(javax.ws.rs.core.MultivaluedMap, javax.persistence.criteria.CriteriaBuilder, javax.persistence.criteria.Root)
	 */
	@Override
	protected Predicate[] extractPredicates(
			MultivaluedMap<String, String> queryParameters,	CriteriaBuilder criteriaBuilder, Root<TxnAwaitingConfirmation> root) 
	{
		
		List<Predicate> predicates = new ArrayList<Predicate>() ;
        if (queryParameters.containsKey("txnConfirmationStatus")) {
            Integer txnConfirmationStatusId = Integer.valueOf(queryParameters.getFirst("txnConfirmationStatus"));
            predicates.add(criteriaBuilder.equal(root.get("txnConfirmationStatus").get("id"), txnConfirmationStatusId));
        }
        if (queryParameters.containsKey("serviceTransaction")) {
            Integer serviceTransactionId = Integer.valueOf(queryParameters.getFirst("serviceTransaction"));
            predicates.add(criteriaBuilder.equal(root.get("serviceTransaction").get("id"), serviceTransactionId));
        }
        if (queryParameters.containsKey("txnConfirmationStatus.code")) {
            String txnConfirmationStatusCode = queryParameters.getFirst("txnConfirmationStatus.code");
            predicates.add(criteriaBuilder.equal(root.get("txnConfirmationStatus").get("code"), txnConfirmationStatusCode));
        }
		if (queryParameters.containsKey("id")) {
            Integer id = Integer.valueOf(queryParameters.getFirst("id"));
            predicates.add(criteriaBuilder.equal(root.get("id"), id));
        }
		if (queryParameters.containsKey("code")) {
            String code = queryParameters.getFirst("code");
            predicates.add(criteriaBuilder.equal(root.get("code"), code));
        }
		if (queryParameters.containsKey("effectiveDt")) {
			DateTime effectiveDt = new DateTime(queryParameters.getFirst("effectiveDt"));
            predicates.add(criteriaBuilder.equal(root.get("effectiveDt"), effectiveDt.toDate()));
        }
		if (queryParameters.containsKey("recSt")) {
            String recSt = queryParameters.getFirst("recSt");
            predicates.add(criteriaBuilder.equal(root.get("recSt"), recSt));
        }
		if (queryParameters.containsKey("versionNo")) {
            Integer versionNo = Integer.valueOf(queryParameters.getFirst("versionNo"));
            predicates.add(criteriaBuilder.equal(root.get("versionNo"), versionNo));
        }
		if (queryParameters.containsKey("rowTs")) {
			DateTime rowTs = new DateTime(queryParameters.getFirst("rowTs"));
            predicates.add(criteriaBuilder.equal(root.get("rowTs"), rowTs.toDate()));
        }
		if (queryParameters.containsKey("createdDt")) {
			DateTime createdDt = new DateTime(queryParameters.getFirst("createdDt"));
            predicates.add(criteriaBuilder.equal(root.get("createdDt"), createdDt.toDate()));
        }
		if (queryParameters.containsKey("createdByUsr")) {
            String createdByUsr = queryParameters.getFirst("createdByUsr");
            predicates.add(criteriaBuilder.equal(root.get("createdByUsr"), createdByUsr));
        }
		if (queryParameters.containsKey("lastModifiedDt")) {
			DateTime lastModifiedDt = new DateTime(queryParameters.getFirst("lastModifiedDt"));
            predicates.add(criteriaBuilder.equal(root.get("lastModifiedDt"), lastModifiedDt.toDate()));
        }
		if (queryParameters.containsKey("lastModifiedUsr")) {
            String lastModifiedUsr = queryParameters.getFirst("lastModifiedUsr");
            predicates.add(criteriaBuilder.equal(root.get("lastModifiedUsr"), lastModifiedUsr));
        }
        return predicates.toArray(new Predicate[]{});
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.transaction.service.entity.TxnAwaitingConfirmationService#getTransactionsAwaitingConfirmation()
	 */
	@Override
	public List<TxnAwaitingConfirmation> getTransactionsAwaitingConfirmation() throws ApplicationException {
		Map<String, String> criteria = new HashMap<String, String>();
		criteria.put("txnConfirmationStatus.code", TxnConfirmationStatusService.UNCONFIRMED);
		return this.findByCriteria(criteria);
	}
}
