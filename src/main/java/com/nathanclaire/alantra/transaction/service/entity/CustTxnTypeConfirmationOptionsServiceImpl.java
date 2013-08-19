/**
 * 
 */
package com.nathanclaire.alantra.transaction.service.entity;

import java.math.BigDecimal;
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

import com.nathanclaire.alantra.transaction.model.CustTxnTypeConfirmationOptions;
import com.nathanclaire.alantra.transaction.model.CustTxnTypeNotificationOptions;
import com.nathanclaire.alantra.transaction.request.CustTxnTypeConfirmationOptionsRequest;
import com.nathanclaire.alantra.transaction.response.CustTxnTypeConfirmationOptionsResponse;
import com.nathanclaire.alantra.application.model.ApplicationEntityField;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtils;
import com.nathanclaire.alantra.customer.model.Customer;
import com.nathanclaire.alantra.customer.service.entity.CustomerService;
import com.nathanclaire.alantra.transaction.model.ServiceTransactionType;
import com.nathanclaire.alantra.transaction.service.entity.ServiceTransactionTypeService;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class CustTxnTypeConfirmationOptionsServiceImpl 
	extends BaseEntityServiceImpl<CustTxnTypeConfirmationOptions, CustTxnTypeConfirmationOptionsResponse, CustTxnTypeConfirmationOptionsRequest> 
	implements CustTxnTypeConfirmationOptionsService
{

private static final String LIST_ITEM_CUSTOMER = "customer";
private static final String LIST_ITEM_SERVICETRANSACTIONTYPE = "transactionType";
	private static final String ENTITY_NAME = "CustTxnTypeConfirmationOptions";
	private static final String LIST_ACTIVITY_CODE = "LIST_TRANSACTION_CUSTTXNTYPECONFIRMATIONOPTIONS";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_TRANSACTION_CUSTTXNTYPECONFIRMATIONOPTIONS";
	
	private Logger logger = LoggerFactory.getLogger(CustTxnTypeConfirmationOptionsServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	@Inject CustomerService  customerService;
	@Inject ServiceTransactionTypeService  transactionTypeService;
	
	/**
	 * @param entityClass
	 */
	public CustTxnTypeConfirmationOptionsServiceImpl() {
		super(CustTxnTypeConfirmationOptions.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.custTxnTypeConfirmationOptions.service.CustTxnTypeConfirmationOptions#findById(java.lang.Integer)
	 */
	@Override
	public CustTxnTypeConfirmationOptions findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.custTxnTypeConfirmationOptions.service.CustTxnTypeConfirmationOptions#findByCode(java.lang.String)
	 */
	@Override
	public CustTxnTypeConfirmationOptions findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.transaction.service.CustTxnTypeConfirmationOptions#findByName(java.lang.String)
	 */
	@Override
	public CustTxnTypeConfirmationOptions findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.transaction.service.CustTxnTypeConfirmationOptions#findAll(java.util.Map)
	 */
	@Override
	public List<CustTxnTypeConfirmationOptions> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.transaction.service.CustTxnTypeConfirmationOptions#createCustTxnTypeConfirmationOptions(com.nathanclaire.alantra.transaction.rest.request.ServiceRequest)
	 */
	@Override
	public CustTxnTypeConfirmationOptions create(CustTxnTypeConfirmationOptionsRequest custTxnTypeConfirmationOptionsRequest) throws ApplicationException {
		return createInstance(custTxnTypeConfirmationOptionsRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.transaction.service.CustTxnTypeConfirmationOptions#deleteCustTxnTypeConfirmationOptions(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.transaction.service.CustTxnTypeConfirmationOptions#updateCustTxnTypeConfirmationOptions(com.nathanclaire.alantra.custTxnTypeConfirmationOptions.rest.request.ServiceRequest)
	 */
	@Override
	public CustTxnTypeConfirmationOptions update(CustTxnTypeConfirmationOptionsRequest custTxnTypeConfirmationOptionsRequest) throws ApplicationException {
		return updateInstance(custTxnTypeConfirmationOptionsRequest);
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
        List<ListItemResponse> customers = customerService.asListItem();
        List<ListItemResponse> transactionTypes = transactionTypeService.asListItem();

        listItems.put(LIST_ITEM_CUSTOMER, customers);
        listItems.put(LIST_ITEM_SERVICETRANSACTIONTYPE, transactionTypes);
		return listItems;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#asListItem()
	 */
	@Override
	public List<ListItemResponse> asListItem() throws ApplicationException {
		List<ListItemResponse> listItems = new ArrayList<ListItemResponse>();
		queryParameters.clear();
		for(CustTxnTypeConfirmationOptions custTxnTypeConfirmationOptions: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(custTxnTypeConfirmationOptions.getId(), custTxnTypeConfirmationOptions.getCode(), custTxnTypeConfirmationOptions.getCode());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public CustTxnTypeConfirmationOptions convertRequestToModel(CustTxnTypeConfirmationOptionsRequest custTxnTypeConfirmationOptionsRequest) 
     throws ApplicationException {
		CustTxnTypeConfirmationOptions custTxnTypeConfirmationOptions = new CustTxnTypeConfirmationOptions();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(custTxnTypeConfirmationOptionsRequest, custTxnTypeConfirmationOptions, allowedEntityFields);
    	//Process many to one relationships
        if (custTxnTypeConfirmationOptionsRequest.getCustomerId() != null)
    	{
    		Customer customer = getEntityManager().find(Customer.class, custTxnTypeConfirmationOptionsRequest.getCustomerId());
    		custTxnTypeConfirmationOptions.setCustomer(customer);
    	}
        if (custTxnTypeConfirmationOptionsRequest.getTransactionTypeId() != null)
    	{
    		ServiceTransactionType transactionType = getEntityManager().find(ServiceTransactionType.class, custTxnTypeConfirmationOptionsRequest.getTransactionTypeId());
    		custTxnTypeConfirmationOptions.setTransactionType(transactionType);
    	}
		return custTxnTypeConfirmationOptions;
	}
	
	@Override
	public CustTxnTypeConfirmationOptionsResponse convertModelToResponse(CustTxnTypeConfirmationOptions model) throws ApplicationException {
		if (model == null) return null;
		CustTxnTypeConfirmationOptionsResponse custTxnTypeConfirmationOptionsResponse = new CustTxnTypeConfirmationOptionsResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(model, custTxnTypeConfirmationOptionsResponse, allowedEntityFields);
		if(model.getCustomer() != null)
			custTxnTypeConfirmationOptionsResponse.setCustomerId(model.getCustomer().getId());
			custTxnTypeConfirmationOptionsResponse.setCustomerText(model.getCustomer().getName());
		if(model.getTransactionType() != null)
			custTxnTypeConfirmationOptionsResponse.setTransactionTypeId(model.getTransactionType().getId());
			custTxnTypeConfirmationOptionsResponse.setTransactionTypeText(model.getTransactionType().getName());
		return custTxnTypeConfirmationOptionsResponse;
	}

	/**
	 * @param customerId
	 * @param accountId
	 * @param chequeNo
	 * @param currencyId
	 * @param custTxnTypeConfirmationOptionsTypeId
	 * @param amount
	 */
	private void initializeQueryParameters(String description, Integer customerId, Integer transactionTypeId, Character emailFg, BigDecimal emailAmount, Character smsFg, BigDecimal smsAmount, Character ivrFg, BigDecimal ivrAmount, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) {
		queryParameters.clear();
        if(description != null)
            queryParameters.add("description", description.toString());
        if(customerId != null)
            queryParameters.add("customer", customerId.toString());
        if(transactionTypeId != null)
            queryParameters.add("transactionType", transactionTypeId.toString());
        if(emailFg != null)
            queryParameters.add("emailFg", emailFg.toString());
        if(emailAmount != null)
            queryParameters.add("emailAmount", emailAmount.toString());
        if(smsFg != null)
            queryParameters.add("smsFg", smsFg.toString());
        if(smsAmount != null)
            queryParameters.add("smsAmount", smsAmount.toString());
        if(ivrFg != null)
            queryParameters.add("ivrFg", ivrFg.toString());
        if(ivrAmount != null)
            queryParameters.add("ivrAmount", ivrAmount.toString());
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
			MultivaluedMap<String, String> queryParameters,	CriteriaBuilder criteriaBuilder, Root<CustTxnTypeConfirmationOptions> root) 
	{
		
		List<Predicate> predicates = new ArrayList<Predicate>() ;
		if (queryParameters.containsKey("description")) {
            String description = queryParameters.getFirst("description");
            predicates.add(criteriaBuilder.equal(root.get("description"), description));
        }
        if (queryParameters.containsKey("customer")) {
            Integer customerId = Integer.valueOf(queryParameters.getFirst("customer"));
            predicates.add(criteriaBuilder.equal(root.get("customer").get("id"), customerId));
        }
        if (queryParameters.containsKey("transactionType")) {
            Integer transactionTypeId = Integer.valueOf(queryParameters.getFirst("transactionType"));
            predicates.add(criteriaBuilder.equal(root.get("transactionType").get("id"), transactionTypeId));
        }
		if (queryParameters.containsKey("emailFg")) {
            String emailFg = queryParameters.getFirst("emailFg");
            predicates.add(criteriaBuilder.equal(root.get("emailFg"), emailFg));
        }
		if (queryParameters.containsKey("emailAmount")) {
            BigDecimal emailAmount = new BigDecimal(queryParameters.getFirst("emailAmount"));
            predicates.add(criteriaBuilder.equal(root.get("emailAmount"), emailAmount));
        }
		if (queryParameters.containsKey("smsFg")) {
            String smsFg = queryParameters.getFirst("smsFg");
            predicates.add(criteriaBuilder.equal(root.get("smsFg"), smsFg));
        }
		if (queryParameters.containsKey("smsAmount")) {
            BigDecimal smsAmount = new BigDecimal(queryParameters.getFirst("smsAmount"));
            predicates.add(criteriaBuilder.equal(root.get("smsAmount"), smsAmount));
        }
		if (queryParameters.containsKey("ivrFg")) {
            String ivrFg = queryParameters.getFirst("ivrFg");
            predicates.add(criteriaBuilder.equal(root.get("ivrFg"), ivrFg));
        }
		if (queryParameters.containsKey("ivrAmount")) {
            BigDecimal ivrAmount = new BigDecimal(queryParameters.getFirst("ivrAmount"));
            predicates.add(criteriaBuilder.equal(root.get("ivrAmount"), ivrAmount));
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
	 * @see com.nathanclaire.alantra.transaction.service.entity.CustTxnTypeConfirmationOptionsService#findCustomerOption(com.nathanclaire.alantra.customer.model.Customer, com.nathanclaire.alantra.transaction.model.ServiceTransactionType)
	 */
	@Override
	public CustTxnTypeConfirmationOptions findCustomerOption(Customer customer,
			ServiceTransactionType serviceTransactionType)
			throws ApplicationException {
		Map<String, String> criteria = new HashMap<String, String>();
		criteria.put("customer", customer.getId().toString());
		criteria.put("transactionType", serviceTransactionType.getId().toString());
		List<CustTxnTypeConfirmationOptions> options = this.findByCriteria(criteria);
		if(!options.isEmpty())
			return options.get(0);
		return null;
	}
}
