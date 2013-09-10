/**
 * 
 */
package com.nathanclaire.alantra.rule.service.entity;

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

import com.nathanclaire.alantra.rule.model.TransactionRuleCategory;
import com.nathanclaire.alantra.rule.request.TransactionRuleCategoryRequest;
import com.nathanclaire.alantra.rule.response.TransactionRuleCategoryResponse;
import com.nathanclaire.alantra.application.model.ApplicationEntityField;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtil;
import com.nathanclaire.alantra.rule.model.TransactionRuleSpace;
import com.nathanclaire.alantra.rule.service.entity.TransactionRuleSpaceService;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class TransactionRuleCategoryServiceImpl 
	extends BaseEntityServiceImpl<TransactionRuleCategory, TransactionRuleCategoryResponse, TransactionRuleCategoryRequest> 
	implements TransactionRuleCategoryService
{

private static final String LIST_ITEM_TRANSACTIONRULESPACE = "transactionRuleSpace";
	private static final String ENTITY_NAME = "TransactionRuleCategory";
	private static final String LIST_ACTIVITY_CODE = "LIST_RULE_TRANSACTIONRULECATEGORY";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_RULE_TRANSACTIONRULECATEGORY";
	
	private Logger logger = LoggerFactory.getLogger(TransactionRuleCategoryServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	@Inject TransactionRuleSpaceService  transactionRuleSpaceService;
	
	/**
	 * @param entityClass
	 */
	public TransactionRuleCategoryServiceImpl() {
		super(TransactionRuleCategory.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.transactionRuleCategory.service.TransactionRuleCategory#findById(java.lang.Integer)
	 */
	@Override
	public TransactionRuleCategory findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.transactionRuleCategory.service.TransactionRuleCategory#findByCode(java.lang.String)
	 */
	@Override
	public TransactionRuleCategory findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.rule.service.TransactionRuleCategory#findByName(java.lang.String)
	 */
	@Override
	public TransactionRuleCategory findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.rule.service.TransactionRuleCategory#findAll(java.util.Map)
	 */
	@Override
	public List<TransactionRuleCategory> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.rule.service.TransactionRuleCategory#createTransactionRuleCategory(com.nathanclaire.alantra.rule.rest.request.ServiceRequest)
	 */
	@Override
	public TransactionRuleCategory create(TransactionRuleCategoryRequest transactionRuleCategoryRequest) throws ApplicationException {
		return createInstance(transactionRuleCategoryRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.rule.service.TransactionRuleCategory#deleteTransactionRuleCategory(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.rule.service.TransactionRuleCategory#updateTransactionRuleCategory(com.nathanclaire.alantra.transactionRuleCategory.rest.request.ServiceRequest)
	 */
	@Override
	public TransactionRuleCategory update(TransactionRuleCategoryRequest transactionRuleCategoryRequest) throws ApplicationException {
		return updateInstance(transactionRuleCategoryRequest);
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
        List<ListItemResponse> transactionRuleSpaces = transactionRuleSpaceService.asListItem();

        listItems.put(LIST_ITEM_TRANSACTIONRULESPACE, transactionRuleSpaces);
		return listItems;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#asListItem()
	 */
	@Override
	public List<ListItemResponse> asListItem() throws ApplicationException {
		List<ListItemResponse> listItems = new ArrayList<ListItemResponse>();
		queryParameters.clear();
		for(TransactionRuleCategory transactionRuleCategory: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(transactionRuleCategory.getId(), transactionRuleCategory.getCode(), transactionRuleCategory.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public TransactionRuleCategory convertRequestToModel(TransactionRuleCategoryRequest transactionRuleCategoryRequest) 
     throws ApplicationException {
		TransactionRuleCategory transactionRuleCategory = new TransactionRuleCategory();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtil.copyProperties(transactionRuleCategoryRequest, transactionRuleCategory, allowedEntityFields);
    	//Process many to one relationships
        if (transactionRuleCategoryRequest.getTransactionRuleSpaceId() != null)
    	{
    		TransactionRuleSpace transactionRuleSpace = getEntityManager().find(TransactionRuleSpace.class, transactionRuleCategoryRequest.getTransactionRuleSpaceId());
    		transactionRuleCategory.setTransactionRuleSpace(transactionRuleSpace);
    	}
		return transactionRuleCategory;
	}
	
	@Override
	public TransactionRuleCategoryResponse convertModelToResponse(TransactionRuleCategory model) throws ApplicationException {
		if (model == null) return null;
		TransactionRuleCategoryResponse transactionRuleCategoryResponse = new TransactionRuleCategoryResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtil.copyProperties(model, transactionRuleCategoryResponse, allowedEntityFields);
		if(model.getTransactionRuleSpace() != null)
			transactionRuleCategoryResponse.setTransactionRuleSpaceId(model.getTransactionRuleSpace().getId());
			transactionRuleCategoryResponse.setTransactionRuleSpaceText(model.getTransactionRuleSpace().getName());
		return transactionRuleCategoryResponse;
	}

	/**
	 * @param customerId
	 * @param accountId
	 * @param chequeNo
	 * @param currencyId
	 * @param transactionRuleCategoryTypeId
	 * @param amount
	 */
	private void initializeQueryParameters(Integer transactionRuleSpaceId, String name, String description, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) {
		queryParameters.clear();
        if(transactionRuleSpaceId != null)
            queryParameters.add("transactionRuleSpace", transactionRuleSpaceId.toString());
        if(name != null)
            queryParameters.add("name", name.toString());
        if(description != null)
            queryParameters.add("description", description.toString());
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
			MultivaluedMap<String, String> queryParameters,	CriteriaBuilder criteriaBuilder, Root<TransactionRuleCategory> root) 
	{
		
		List<Predicate> predicates = new ArrayList<Predicate>() ;
        if (queryParameters.containsKey("transactionRuleSpace")) {
            Integer transactionRuleSpaceId = Integer.valueOf(queryParameters.getFirst("transactionRuleSpace"));
            predicates.add(criteriaBuilder.equal(root.get("transactionRuleSpace").get("id"), transactionRuleSpaceId));
        }
		if (queryParameters.containsKey("name")) {
            String name = queryParameters.getFirst("name");
            predicates.add(criteriaBuilder.equal(root.get("name"), name));
        }
		if (queryParameters.containsKey("description")) {
            String description = queryParameters.getFirst("description");
            predicates.add(criteriaBuilder.equal(root.get("description"), description));
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
}
