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

import com.nathanclaire.alantra.rule.model.TransactionRuleCondition;
import com.nathanclaire.alantra.rule.request.TransactionRuleConditionRequest;
import com.nathanclaire.alantra.rule.response.TransactionRuleConditionResponse;
import com.nathanclaire.alantra.application.model.ApplicationEntityField;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtil;
import com.nathanclaire.alantra.rule.model.TransactionRule;
import com.nathanclaire.alantra.rule.service.entity.TransactionRuleService;
import com.nathanclaire.alantra.rule.model.Operator;
import com.nathanclaire.alantra.rule.service.entity.OperatorService;
import com.nathanclaire.alantra.rule.model.TransactionRuleConditionAttribute;
import com.nathanclaire.alantra.rule.service.entity.TransactionRuleConditionAttributeService;
import com.nathanclaire.alantra.rule.model.TransactionRuleConditionParameter;
import com.nathanclaire.alantra.rule.service.entity.TransactionRuleConditionParameterService;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class TransactionRuleConditionServiceImpl 
	extends BaseEntityServiceImpl<TransactionRuleCondition, TransactionRuleConditionResponse, TransactionRuleConditionRequest> 
	implements TransactionRuleConditionService
{

private static final String LIST_ITEM_TRANSACTIONRULE = "transactionRule";
private static final String LIST_ITEM_OPERATOR = "operator";
private static final String LIST_ITEM_TRANSACTIONRULECONDITIONATTRIBUTE = "transactionRuleConditionAttribute";
private static final String LIST_ITEM_TRANSACTIONRULECONDITIONPARAMETER = "transactionRuleConditionParameter";
	private static final String ENTITY_NAME = "TransactionRuleCondition";
	private static final String LIST_ACTIVITY_CODE = "LIST_RULE_TRANSACTIONRULECONDITION";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_RULE_TRANSACTIONRULECONDITION";
	
	private Logger logger = LoggerFactory.getLogger(TransactionRuleConditionServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	@Inject TransactionRuleService  transactionRuleService;
	@Inject OperatorService  operatorService;
	@Inject TransactionRuleConditionAttributeService  transactionRuleConditionAttributeService;
	@Inject TransactionRuleConditionParameterService  transactionRuleConditionParameterService;
	
	/**
	 * @param entityClass
	 */
	public TransactionRuleConditionServiceImpl() {
		super(TransactionRuleCondition.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.transactionRuleCondition.service.TransactionRuleCondition#findById(java.lang.Integer)
	 */
	@Override
	public TransactionRuleCondition findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.transactionRuleCondition.service.TransactionRuleCondition#findByCode(java.lang.String)
	 */
	@Override
	public TransactionRuleCondition findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.rule.service.TransactionRuleCondition#findByName(java.lang.String)
	 */
	@Override
	public TransactionRuleCondition findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.rule.service.TransactionRuleCondition#findAll(java.util.Map)
	 */
	@Override
	public List<TransactionRuleCondition> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.rule.service.TransactionRuleCondition#createTransactionRuleCondition(com.nathanclaire.alantra.rule.rest.request.ServiceRequest)
	 */
	@Override
	public TransactionRuleCondition create(TransactionRuleConditionRequest transactionRuleConditionRequest) throws ApplicationException {
		return createInstance(transactionRuleConditionRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.rule.service.TransactionRuleCondition#deleteTransactionRuleCondition(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.rule.service.TransactionRuleCondition#updateTransactionRuleCondition(com.nathanclaire.alantra.transactionRuleCondition.rest.request.ServiceRequest)
	 */
	@Override
	public TransactionRuleCondition update(TransactionRuleConditionRequest transactionRuleConditionRequest) throws ApplicationException {
		return updateInstance(transactionRuleConditionRequest);
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
        List<ListItemResponse> transactionRules = transactionRuleService.asListItem();
        List<ListItemResponse> operators = operatorService.asListItem();
        List<ListItemResponse> transactionRuleConditionAttributes = transactionRuleConditionAttributeService.asListItem();
        List<ListItemResponse> transactionRuleConditionParameters = transactionRuleConditionParameterService.asListItem();

        listItems.put(LIST_ITEM_TRANSACTIONRULE, transactionRules);
        listItems.put(LIST_ITEM_OPERATOR, operators);
        listItems.put(LIST_ITEM_TRANSACTIONRULECONDITIONATTRIBUTE, transactionRuleConditionAttributes);
        listItems.put(LIST_ITEM_TRANSACTIONRULECONDITIONPARAMETER, transactionRuleConditionParameters);
		return listItems;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#asListItem()
	 */
	@Override
	public List<ListItemResponse> asListItem() throws ApplicationException {
		List<ListItemResponse> listItems = new ArrayList<ListItemResponse>();
		queryParameters.clear();
		for(TransactionRuleCondition transactionRuleCondition: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(transactionRuleCondition.getId(), transactionRuleCondition.getCode(), transactionRuleCondition.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public TransactionRuleCondition convertRequestToModel(TransactionRuleConditionRequest transactionRuleConditionRequest) 
     throws ApplicationException {
		TransactionRuleCondition transactionRuleCondition = new TransactionRuleCondition();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtil.copyProperties(transactionRuleConditionRequest, transactionRuleCondition, allowedEntityFields);
    	//Process many to one relationships
        if (transactionRuleConditionRequest.getTransactionRuleId() != null)
    	{
    		TransactionRule transactionRule = getEntityManager().find(TransactionRule.class, transactionRuleConditionRequest.getTransactionRuleId());
    		transactionRuleCondition.setTransactionRule(transactionRule);
    	}
        if (transactionRuleConditionRequest.getOperatorId() != null)
    	{
    		Operator operator = getEntityManager().find(Operator.class, transactionRuleConditionRequest.getOperatorId());
    		transactionRuleCondition.setOperator(operator);
    	}
        if (transactionRuleConditionRequest.getTransactionRuleConditionAttributeId() != null)
    	{
    		TransactionRuleConditionAttribute transactionRuleConditionAttribute = getEntityManager().find(TransactionRuleConditionAttribute.class, transactionRuleConditionRequest.getTransactionRuleConditionAttributeId());
    		transactionRuleCondition.setTransactionRuleConditionAttribute(transactionRuleConditionAttribute);
    	}
        if (transactionRuleConditionRequest.getTransactionRuleConditionParameterId() != null)
    	{
    		TransactionRuleConditionParameter transactionRuleConditionParameter = getEntityManager().find(TransactionRuleConditionParameter.class, transactionRuleConditionRequest.getTransactionRuleConditionParameterId());
    		transactionRuleCondition.setTransactionRuleConditionParameter(transactionRuleConditionParameter);
    	}
		return transactionRuleCondition;
	}
	
	@Override
	public TransactionRuleConditionResponse convertModelToResponse(TransactionRuleCondition model) throws ApplicationException {
		if (model == null) return null;
		TransactionRuleConditionResponse transactionRuleConditionResponse = new TransactionRuleConditionResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtil.copyProperties(model, transactionRuleConditionResponse, allowedEntityFields);
		if(model.getTransactionRule() != null)
			transactionRuleConditionResponse.setTransactionRuleId(model.getTransactionRule().getId());
			transactionRuleConditionResponse.setTransactionRuleText(model.getTransactionRule().getName());
		if(model.getOperator() != null)
			transactionRuleConditionResponse.setOperatorId(model.getOperator().getId());
			transactionRuleConditionResponse.setOperatorText(model.getOperator().getName());
		if(model.getTransactionRuleConditionAttribute() != null)
			transactionRuleConditionResponse.setTransactionRuleConditionAttributeId(model.getTransactionRuleConditionAttribute().getId());
			transactionRuleConditionResponse.setTransactionRuleConditionAttributeText(model.getTransactionRuleConditionAttribute().getName());
		if(model.getTransactionRuleConditionParameter() != null)
			transactionRuleConditionResponse.setTransactionRuleConditionParameterId(model.getTransactionRuleConditionParameter().getId());
			transactionRuleConditionResponse.setTransactionRuleConditionParameterText(model.getTransactionRuleConditionParameter().getName());
		return transactionRuleConditionResponse;
	}

	/**
	 * @param customerId
	 * @param accountId
	 * @param chequeNo
	 * @param currencyId
	 * @param transactionRuleConditionTypeId
	 * @param amount
	 */
	private void initializeQueryParameters(Integer transactionRuleId, Integer operatorId, Integer transactionRuleConditionAttributeId, Integer transactionRuleConditionParameterId, String name, String description, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) {
		queryParameters.clear();
        if(transactionRuleId != null)
            queryParameters.add("transactionRule", transactionRuleId.toString());
        if(operatorId != null)
            queryParameters.add("operator", operatorId.toString());
        if(transactionRuleConditionAttributeId != null)
            queryParameters.add("transactionRuleConditionAttribute", transactionRuleConditionAttributeId.toString());
        if(transactionRuleConditionParameterId != null)
            queryParameters.add("transactionRuleConditionParameter", transactionRuleConditionParameterId.toString());
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
			MultivaluedMap<String, String> queryParameters,	CriteriaBuilder criteriaBuilder, Root<TransactionRuleCondition> root) 
	{
		
		List<Predicate> predicates = new ArrayList<Predicate>() ;
        if (queryParameters.containsKey("transactionRule")) {
            Integer transactionRuleId = Integer.valueOf(queryParameters.getFirst("transactionRule"));
            predicates.add(criteriaBuilder.equal(root.get("transactionRule").get("id"), transactionRuleId));
        }
        if (queryParameters.containsKey("operator")) {
            Integer operatorId = Integer.valueOf(queryParameters.getFirst("operator"));
            predicates.add(criteriaBuilder.equal(root.get("operator").get("id"), operatorId));
        }
        if (queryParameters.containsKey("transactionRuleConditionAttribute")) {
            Integer transactionRuleConditionAttributeId = Integer.valueOf(queryParameters.getFirst("transactionRuleConditionAttribute"));
            predicates.add(criteriaBuilder.equal(root.get("transactionRuleConditionAttribute").get("id"), transactionRuleConditionAttributeId));
        }
        if (queryParameters.containsKey("transactionRuleConditionParameter")) {
            Integer transactionRuleConditionParameterId = Integer.valueOf(queryParameters.getFirst("transactionRuleConditionParameter"));
            predicates.add(criteriaBuilder.equal(root.get("transactionRuleConditionParameter").get("id"), transactionRuleConditionParameterId));
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
