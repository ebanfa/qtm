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
import com.nathanclaire.alantra.base.util.PropertyUtil;
import com.nathanclaire.alantra.transaction.model.ServiceTransaction;
import com.nathanclaire.alantra.transaction.model.TxnAwaitingNotification;
import com.nathanclaire.alantra.transaction.model.TxnNotificationStatus;
import com.nathanclaire.alantra.transaction.request.TxnAwaitingNotificationRequest;
import com.nathanclaire.alantra.transaction.response.TxnAwaitingNotificationResponse;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class TxnAwaitingNotificationServiceImpl 
	extends BaseEntityServiceImpl<TxnAwaitingNotification, TxnAwaitingNotificationResponse, TxnAwaitingNotificationRequest> 
	implements TxnAwaitingNotificationService
{

private static final String LIST_ITEM_TXNNOTIFICATIONSTATUS = "txnNotificationStatus";
private static final String LIST_ITEM_SERVICETRANSACTION = "serviceTransaction";
	private static final String ENTITY_NAME = "TxnAwaitingNotification";
	private static final String LIST_ACTIVITY_CODE = "LIST_TRANSACTION_TXNAWAITINGNOTIFICATION";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_TRANSACTION_TXNAWAITINGNOTIFICATION";
	
	private Logger logger = LoggerFactory.getLogger(TxnAwaitingNotificationServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	@Inject TxnNotificationStatusService  txnNotificationStatusService;
	@Inject ServiceTransactionService  serviceTransactionService;
	
	/**
	 * @param entityClass
	 */
	public TxnAwaitingNotificationServiceImpl() {
		super(TxnAwaitingNotification.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.txnAwaitingNotification.service.TxnAwaitingNotification#findById(java.lang.Integer)
	 */
	@Override
	public TxnAwaitingNotification findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.txnAwaitingNotification.service.TxnAwaitingNotification#findByCode(java.lang.String)
	 */
	@Override
	public TxnAwaitingNotification findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.transaction.service.TxnAwaitingNotification#findByName(java.lang.String)
	 */
	@Override
	public TxnAwaitingNotification findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.transaction.service.TxnAwaitingNotification#findAll(java.util.Map)
	 */
	@Override
	public List<TxnAwaitingNotification> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.transaction.service.TxnAwaitingNotification#createTxnAwaitingNotification(com.nathanclaire.alantra.transaction.rest.request.ServiceRequest)
	 */
	@Override
	public TxnAwaitingNotification create(TxnAwaitingNotificationRequest txnAwaitingNotificationRequest) throws ApplicationException {
		return createInstance(txnAwaitingNotificationRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.transaction.service.TxnAwaitingNotification#deleteTxnAwaitingNotification(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.transaction.service.TxnAwaitingNotification#updateTxnAwaitingNotification(com.nathanclaire.alantra.txnAwaitingNotification.rest.request.ServiceRequest)
	 */
	@Override
	public TxnAwaitingNotification update(TxnAwaitingNotificationRequest txnAwaitingNotificationRequest) throws ApplicationException {
		return updateInstance(txnAwaitingNotificationRequest);
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
        List<ListItemResponse> txnNotificationStatuss = txnNotificationStatusService.asListItem();
        List<ListItemResponse> serviceTransactions = serviceTransactionService.asListItem();

        listItems.put(LIST_ITEM_TXNNOTIFICATIONSTATUS, txnNotificationStatuss);
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
		for(TxnAwaitingNotification txnAwaitingNotification: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(txnAwaitingNotification.getId(), txnAwaitingNotification.getCode(), txnAwaitingNotification.getCode());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public TxnAwaitingNotification convertRequestToModel(TxnAwaitingNotificationRequest txnAwaitingNotificationRequest) 
     throws ApplicationException {
		TxnAwaitingNotification txnAwaitingNotification = new TxnAwaitingNotification();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtil.copyProperties(txnAwaitingNotificationRequest, txnAwaitingNotification, allowedEntityFields);
    	//Process many to one relationships
        if (txnAwaitingNotificationRequest.getTxnNotificationStatusId() != null)
    	{
    		TxnNotificationStatus txnNotificationStatus = getEntityManager().find(TxnNotificationStatus.class, txnAwaitingNotificationRequest.getTxnNotificationStatusId());
    		txnAwaitingNotification.setTxnNotificationStatus(txnNotificationStatus);
    	}
        if (txnAwaitingNotificationRequest.getServiceTransactionId() != null)
    	{
    		ServiceTransaction serviceTransaction = getEntityManager().find(ServiceTransaction.class, txnAwaitingNotificationRequest.getServiceTransactionId());
    		txnAwaitingNotification.setServiceTransaction(serviceTransaction);
    	}
		return txnAwaitingNotification;
	}
	
	@Override
	public TxnAwaitingNotificationResponse convertModelToResponse(TxnAwaitingNotification model) throws ApplicationException {
		if (model == null) return null;
		TxnAwaitingNotificationResponse txnAwaitingNotificationResponse = new TxnAwaitingNotificationResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtil.copyProperties(model, txnAwaitingNotificationResponse, allowedEntityFields);
		if(model.getTxnNotificationStatus() != null)
			txnAwaitingNotificationResponse.setTxnNotificationStatusId(model.getTxnNotificationStatus().getId());
			txnAwaitingNotificationResponse.setTxnNotificationStatusText(model.getTxnNotificationStatus().getName());
		if(model.getServiceTransaction() != null)
			txnAwaitingNotificationResponse.setServiceTransactionId(model.getServiceTransaction().getId());
			txnAwaitingNotificationResponse.setServiceTransactionText(model.getServiceTransaction().getName());
		return txnAwaitingNotificationResponse;
	}

	/**
	 * @param customerId
	 * @param accountId
	 * @param chequeNo
	 * @param currencyId
	 * @param txnAwaitingNotificationTypeId
	 * @param amount
	 */
	private void initializeQueryParameters(Integer txnNotificationStatusId, Integer serviceTransactionId, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) {
		queryParameters.clear();
        if(txnNotificationStatusId != null)
            queryParameters.add("txnNotificationStatus", txnNotificationStatusId.toString());
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
			MultivaluedMap<String, String> queryParameters,	CriteriaBuilder criteriaBuilder, Root<TxnAwaitingNotification> root) 
	{
		
		List<Predicate> predicates = new ArrayList<Predicate>() ;
        if (queryParameters.containsKey("txnNotificationStatus")) {
            Integer txnNotificationStatusId = Integer.valueOf(queryParameters.getFirst("txnNotificationStatus"));
            predicates.add(criteriaBuilder.equal(root.get("txnNotificationStatus").get("id"), txnNotificationStatusId));
        }
        if (queryParameters.containsKey("serviceTransaction")) {
            Integer serviceTransactionId = Integer.valueOf(queryParameters.getFirst("serviceTransaction"));
            predicates.add(criteriaBuilder.equal(root.get("serviceTransaction").get("id"), serviceTransactionId));
        }
        if (queryParameters.containsKey("txnNotificationStatus.code")) {
            String txnNotificationStatusCode = queryParameters.getFirst("txnNotificationStatus.code");
            predicates.add(criteriaBuilder.equal(root.get("txnNotificationStatus").get("code"), txnNotificationStatusCode));
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

	@Override
	public List<TxnAwaitingNotification> getTransactionsAwaitingNotification() throws ApplicationException {
		Map<String, String> criteria = new HashMap<String, String>();
		criteria.put("txnNotificationStatus.code", TxnNotificationStatusService.NOT_SENT);
		return findByCriteria(criteria);
	}
}
