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

import com.nathanclaire.alantra.transaction.model.CustCatTxnTypeNotificationOptions;
import com.nathanclaire.alantra.transaction.model.CustTxnTypeConfirmationOptions;
import com.nathanclaire.alantra.transaction.request.CustCatTxnTypeNotificationOptionsRequest;
import com.nathanclaire.alantra.transaction.response.CustCatTxnTypeNotificationOptionsResponse;
import com.nathanclaire.alantra.application.model.ApplicationEntityField;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtil;
import com.nathanclaire.alantra.customer.model.CustomerCategory;
import com.nathanclaire.alantra.customer.service.entity.CustomerCategoryService;
import com.nathanclaire.alantra.transaction.model.ServiceTransactionType;
import com.nathanclaire.alantra.transaction.service.entity.ServiceTransactionTypeService;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class CustCatTxnTypeNotificationOptionsServiceImpl 
	extends BaseEntityServiceImpl<CustCatTxnTypeNotificationOptions, CustCatTxnTypeNotificationOptionsResponse, CustCatTxnTypeNotificationOptionsRequest> 
	implements CustCatTxnTypeNotificationOptionsService
{

private static final String LIST_ITEM_CUSTOMERCATEGORY = "customerCategory";
private static final String LIST_ITEM_SERVICETRANSACTIONTYPE = "transactionType";
	private static final String ENTITY_NAME = "CustCatTxnTypeNotificationOptions";
	private static final String LIST_ACTIVITY_CODE = "LIST_TRANSACTION_CUSTCATTXNTYPENOTIFICATIONOPTIONS";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_TRANSACTION_CUSTCATTXNTYPENOTIFICATIONOPTIONS";
	
	private Logger logger = LoggerFactory.getLogger(CustCatTxnTypeNotificationOptionsServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	@Inject CustomerCategoryService  customerCategoryService;
	@Inject ServiceTransactionTypeService  transactionTypeService;
	
	/**
	 * @param entityClass
	 */
	public CustCatTxnTypeNotificationOptionsServiceImpl() {
		super(CustCatTxnTypeNotificationOptions.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.custCatTxnTypeNotificationOptions.service.CustCatTxnTypeNotificationOptions#findById(java.lang.Integer)
	 */
	@Override
	public CustCatTxnTypeNotificationOptions findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.custCatTxnTypeNotificationOptions.service.CustCatTxnTypeNotificationOptions#findByCode(java.lang.String)
	 */
	@Override
	public CustCatTxnTypeNotificationOptions findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.transaction.service.CustCatTxnTypeNotificationOptions#findByName(java.lang.String)
	 */
	@Override
	public CustCatTxnTypeNotificationOptions findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.transaction.service.CustCatTxnTypeNotificationOptions#findAll(java.util.Map)
	 */
	@Override
	public List<CustCatTxnTypeNotificationOptions> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.transaction.service.CustCatTxnTypeNotificationOptions#createCustCatTxnTypeNotificationOptions(com.nathanclaire.alantra.transaction.rest.request.ServiceRequest)
	 */
	@Override
	public CustCatTxnTypeNotificationOptions create(CustCatTxnTypeNotificationOptionsRequest custCatTxnTypeNotificationOptionsRequest) throws ApplicationException {
		return createInstance(custCatTxnTypeNotificationOptionsRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.transaction.service.CustCatTxnTypeNotificationOptions#deleteCustCatTxnTypeNotificationOptions(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.transaction.service.CustCatTxnTypeNotificationOptions#updateCustCatTxnTypeNotificationOptions(com.nathanclaire.alantra.custCatTxnTypeNotificationOptions.rest.request.ServiceRequest)
	 */
	@Override
	public CustCatTxnTypeNotificationOptions update(CustCatTxnTypeNotificationOptionsRequest custCatTxnTypeNotificationOptionsRequest) throws ApplicationException {
		return updateInstance(custCatTxnTypeNotificationOptionsRequest);
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
        List<ListItemResponse> customerCategorys = customerCategoryService.asListItem();
        List<ListItemResponse> transactionTypes = transactionTypeService.asListItem();

        listItems.put(LIST_ITEM_CUSTOMERCATEGORY, customerCategorys);
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
		for(CustCatTxnTypeNotificationOptions custCatTxnTypeNotificationOptions: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(custCatTxnTypeNotificationOptions.getId(), custCatTxnTypeNotificationOptions.getCode(), custCatTxnTypeNotificationOptions.getCode());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public CustCatTxnTypeNotificationOptions convertRequestToModel(CustCatTxnTypeNotificationOptionsRequest custCatTxnTypeNotificationOptionsRequest) 
     throws ApplicationException {
		CustCatTxnTypeNotificationOptions custCatTxnTypeNotificationOptions = new CustCatTxnTypeNotificationOptions();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtil.copyProperties(custCatTxnTypeNotificationOptionsRequest, custCatTxnTypeNotificationOptions, allowedEntityFields);
    	//Process many to one relationships
        if (custCatTxnTypeNotificationOptionsRequest.getCustomerCategoryId() != null)
    	{
    		CustomerCategory customerCategory = getEntityManager().find(CustomerCategory.class, custCatTxnTypeNotificationOptionsRequest.getCustomerCategoryId());
    		custCatTxnTypeNotificationOptions.setCustomerCategory(customerCategory);
    	}
        if (custCatTxnTypeNotificationOptionsRequest.getTransactionTypeId() != null)
    	{
    		ServiceTransactionType transactionType = getEntityManager().find(ServiceTransactionType.class, custCatTxnTypeNotificationOptionsRequest.getTransactionTypeId());
    		custCatTxnTypeNotificationOptions.setTransactionType(transactionType);
    	}
		return custCatTxnTypeNotificationOptions;
	}
	
	@Override
	public CustCatTxnTypeNotificationOptionsResponse convertModelToResponse(CustCatTxnTypeNotificationOptions model) throws ApplicationException {
		if (model == null) return null;
		CustCatTxnTypeNotificationOptionsResponse custCatTxnTypeNotificationOptionsResponse = new CustCatTxnTypeNotificationOptionsResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtil.copyProperties(model, custCatTxnTypeNotificationOptionsResponse, allowedEntityFields);
		if(model.getCustomerCategory() != null)
			custCatTxnTypeNotificationOptionsResponse.setCustomerCategoryId(model.getCustomerCategory().getId());
			custCatTxnTypeNotificationOptionsResponse.setCustomerCategoryText(model.getCustomerCategory().getName());
		if(model.getTransactionType() != null)
			custCatTxnTypeNotificationOptionsResponse.setTransactionTypeId(model.getTransactionType().getId());
			custCatTxnTypeNotificationOptionsResponse.setTransactionTypeText(model.getTransactionType().getName());
		return custCatTxnTypeNotificationOptionsResponse;
	}

	/**
	 * @param customerId
	 * @param accountId
	 * @param chequeNo
	 * @param currencyId
	 * @param custCatTxnTypeNotificationOptionsTypeId
	 * @param amount
	 */
	private void initializeQueryParameters(String description, Integer customerCategoryId, Integer transactionTypeId, Character emailFg, BigDecimal emailAmount, Character smsFg, BigDecimal smsAmount, Character ivrFg, BigDecimal ivrAmount, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) {
		queryParameters.clear();
        if(description != null)
            queryParameters.add("description", description.toString());
        if(customerCategoryId != null)
            queryParameters.add("customerCategory", customerCategoryId.toString());
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
			MultivaluedMap<String, String> queryParameters,	CriteriaBuilder criteriaBuilder, Root<CustCatTxnTypeNotificationOptions> root) 
	{
		
		List<Predicate> predicates = new ArrayList<Predicate>() ;
		if (queryParameters.containsKey("description")) {
            String description = queryParameters.getFirst("description");
            predicates.add(criteriaBuilder.equal(root.get("description"), description));
        }
        if (queryParameters.containsKey("customerCategory")) {
            Integer customerCategoryId = Integer.valueOf(queryParameters.getFirst("customerCategory"));
            predicates.add(criteriaBuilder.equal(root.get("customerCategory").get("id"), customerCategoryId));
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
	 * @see com.nathanclaire.alantra.transaction.service.entity.CustCatTxnTypeNotificationOptionsService#findCustomerCategoryOption(com.nathanclaire.alantra.customer.model.CustomerCategory, com.nathanclaire.alantra.transaction.model.ServiceTransactionType)
	 */
	@Override
	public CustCatTxnTypeNotificationOptions findCustomerCategoryOption(
			CustomerCategory customerCategory,
			ServiceTransactionType serviceTransactionType)
			throws ApplicationException {
		Map<String, String> criteria = new HashMap<String, String>();
		criteria.put("customerCategory", customerCategory.getId().toString());
		criteria.put("transactionType", serviceTransactionType.getId().toString());
		List<CustCatTxnTypeNotificationOptions> options = this.findByCriteria(criteria);
		if(!options.isEmpty())
			return options.get(0);
		return null;
	}
}
