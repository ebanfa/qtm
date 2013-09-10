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

import com.nathanclaire.alantra.rule.model.TransactionRuleTypeParameter;
import com.nathanclaire.alantra.rule.request.TransactionRuleTypeParameterRequest;
import com.nathanclaire.alantra.rule.response.TransactionRuleTypeParameterResponse;
import com.nathanclaire.alantra.application.model.ApplicationEntityField;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtil;
import com.nathanclaire.alantra.rule.model.TransactionRuleType;
import com.nathanclaire.alantra.rule.service.entity.TransactionRuleTypeService;
import com.nathanclaire.alantra.rule.model.Parameter;
import com.nathanclaire.alantra.rule.service.entity.ParameterService;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class TransactionRuleTypeParameterServiceImpl 
	extends BaseEntityServiceImpl<TransactionRuleTypeParameter, TransactionRuleTypeParameterResponse, TransactionRuleTypeParameterRequest> 
	implements TransactionRuleTypeParameterService
{

private static final String LIST_ITEM_TRANSACTIONRULETYPE = "transactionRuleType";
private static final String LIST_ITEM_PARAMETER = "parameter";
	private static final String ENTITY_NAME = "TransactionRuleTypeParameter";
	private static final String LIST_ACTIVITY_CODE = "LIST_RULE_TRANSACTIONRULETYPEPARAMETER";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_RULE_TRANSACTIONRULETYPEPARAMETER";
	
	private Logger logger = LoggerFactory.getLogger(TransactionRuleTypeParameterServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	@Inject TransactionRuleTypeService  transactionRuleTypeService;
	@Inject ParameterService  parameterService;
	
	/**
	 * @param entityClass
	 */
	public TransactionRuleTypeParameterServiceImpl() {
		super(TransactionRuleTypeParameter.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.transactionRuleTypeParameter.service.TransactionRuleTypeParameter#findById(java.lang.Integer)
	 */
	@Override
	public TransactionRuleTypeParameter findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.transactionRuleTypeParameter.service.TransactionRuleTypeParameter#findByCode(java.lang.String)
	 */
	@Override
	public TransactionRuleTypeParameter findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.rule.service.TransactionRuleTypeParameter#findByName(java.lang.String)
	 */
	@Override
	public TransactionRuleTypeParameter findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.rule.service.TransactionRuleTypeParameter#findAll(java.util.Map)
	 */
	@Override
	public List<TransactionRuleTypeParameter> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.rule.service.TransactionRuleTypeParameter#createTransactionRuleTypeParameter(com.nathanclaire.alantra.rule.rest.request.ServiceRequest)
	 */
	@Override
	public TransactionRuleTypeParameter create(TransactionRuleTypeParameterRequest transactionRuleTypeParameterRequest) throws ApplicationException {
		return createInstance(transactionRuleTypeParameterRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.rule.service.TransactionRuleTypeParameter#deleteTransactionRuleTypeParameter(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.rule.service.TransactionRuleTypeParameter#updateTransactionRuleTypeParameter(com.nathanclaire.alantra.transactionRuleTypeParameter.rest.request.ServiceRequest)
	 */
	@Override
	public TransactionRuleTypeParameter update(TransactionRuleTypeParameterRequest transactionRuleTypeParameterRequest) throws ApplicationException {
		return updateInstance(transactionRuleTypeParameterRequest);
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
        List<ListItemResponse> transactionRuleTypes = transactionRuleTypeService.asListItem();
        List<ListItemResponse> parameters = parameterService.asListItem();

        listItems.put(LIST_ITEM_TRANSACTIONRULETYPE, transactionRuleTypes);
        listItems.put(LIST_ITEM_PARAMETER, parameters);
		return listItems;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#asListItem()
	 */
	@Override
	public List<ListItemResponse> asListItem() throws ApplicationException {
		List<ListItemResponse> listItems = new ArrayList<ListItemResponse>();
		queryParameters.clear();
		for(TransactionRuleTypeParameter transactionRuleTypeParameter: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(transactionRuleTypeParameter.getId(), transactionRuleTypeParameter.getCode(), transactionRuleTypeParameter.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public TransactionRuleTypeParameter convertRequestToModel(TransactionRuleTypeParameterRequest transactionRuleTypeParameterRequest) 
     throws ApplicationException {
		TransactionRuleTypeParameter transactionRuleTypeParameter = new TransactionRuleTypeParameter();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtil.copyProperties(transactionRuleTypeParameterRequest, transactionRuleTypeParameter, allowedEntityFields);
    	//Process many to one relationships
        if (transactionRuleTypeParameterRequest.getTransactionRuleTypeId() != null)
    	{
    		TransactionRuleType transactionRuleType = getEntityManager().find(TransactionRuleType.class, transactionRuleTypeParameterRequest.getTransactionRuleTypeId());
    		transactionRuleTypeParameter.setTransactionRuleType(transactionRuleType);
    	}
        if (transactionRuleTypeParameterRequest.getParameterId() != null)
    	{
    		Parameter parameter = getEntityManager().find(Parameter.class, transactionRuleTypeParameterRequest.getParameterId());
    		transactionRuleTypeParameter.setParameter(parameter);
    	}
		return transactionRuleTypeParameter;
	}
	
	@Override
	public TransactionRuleTypeParameterResponse convertModelToResponse(TransactionRuleTypeParameter model) throws ApplicationException {
		if (model == null) return null;
		TransactionRuleTypeParameterResponse transactionRuleTypeParameterResponse = new TransactionRuleTypeParameterResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtil.copyProperties(model, transactionRuleTypeParameterResponse, allowedEntityFields);
		if(model.getTransactionRuleType() != null)
			transactionRuleTypeParameterResponse.setTransactionRuleTypeId(model.getTransactionRuleType().getId());
			transactionRuleTypeParameterResponse.setTransactionRuleTypeText(model.getTransactionRuleType().getName());
		if(model.getParameter() != null)
			transactionRuleTypeParameterResponse.setParameterId(model.getParameter().getId());
			transactionRuleTypeParameterResponse.setParameterText(model.getParameter().getName());
		return transactionRuleTypeParameterResponse;
	}

	/**
	 * @param customerId
	 * @param accountId
	 * @param chequeNo
	 * @param currencyId
	 * @param transactionRuleTypeParameterTypeId
	 * @param amount
	 */
	private void initializeQueryParameters(Integer transactionRuleTypeId, Integer parameterId, String name, String defaultVal, String description, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) {
		queryParameters.clear();
        if(transactionRuleTypeId != null)
            queryParameters.add("transactionRuleType", transactionRuleTypeId.toString());
        if(parameterId != null)
            queryParameters.add("parameter", parameterId.toString());
        if(name != null)
            queryParameters.add("name", name.toString());
        if(defaultVal != null)
            queryParameters.add("defaultVal", defaultVal.toString());
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
			MultivaluedMap<String, String> queryParameters,	CriteriaBuilder criteriaBuilder, Root<TransactionRuleTypeParameter> root) 
	{
		
		List<Predicate> predicates = new ArrayList<Predicate>() ;
        if (queryParameters.containsKey("transactionRuleType")) {
            Integer transactionRuleTypeId = Integer.valueOf(queryParameters.getFirst("transactionRuleType"));
            predicates.add(criteriaBuilder.equal(root.get("transactionRuleType").get("id"), transactionRuleTypeId));
        }
        if (queryParameters.containsKey("parameter")) {
            Integer parameterId = Integer.valueOf(queryParameters.getFirst("parameter"));
            predicates.add(criteriaBuilder.equal(root.get("parameter").get("id"), parameterId));
        }
		if (queryParameters.containsKey("name")) {
            String name = queryParameters.getFirst("name");
            predicates.add(criteriaBuilder.equal(root.get("name"), name));
        }
		if (queryParameters.containsKey("defaultVal")) {
            String defaultVal = queryParameters.getFirst("defaultVal");
            predicates.add(criteriaBuilder.equal(root.get("defaultVal"), defaultVal));
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
