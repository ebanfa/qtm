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
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.ws.rs.core.MultivaluedMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.application.model.ApplicationEntityField;

import com.nathanclaire.alantra.advice.model.Advice;
import com.nathanclaire.alantra.customer.model.Customer;
import com.nathanclaire.alantra.advice.model.AdviceStatus;
import com.nathanclaire.alantra.advice.model.AdviceType;
import com.nathanclaire.alantra.advice.request.AdviceRequest;
import com.nathanclaire.alantra.advice.response.AdviceResponse;
import com.nathanclaire.alantra.customer.service.entity.CustomerService;
import com.nathanclaire.alantra.advice.service.entity.AdviceStatusService;
import com.nathanclaire.alantra.advice.service.entity.AdviceTypeService;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.base.util.PropertyUtils;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class AdviceServiceImpl 
	extends BaseEntityServiceImpl<Advice, AdviceResponse, AdviceRequest> 
	implements AdviceService
{
	private static final String LIST_ITEM_CUSTOMER = "customer";
	private static final String LIST_ITEM_ADVICESTATUS = "adviceStatus";
	private static final String LIST_ITEM_ADVICETYPE = "adviceType";
	private static final String ENTITY_NAME = "Advice";
	private static final String LIST_ACTIVITY_CODE = "LIST_ADVICE_ADVICE";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_ADVICE_ADVICE";
	
	private Logger logger = LoggerFactory.getLogger(AdviceServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	@Inject
	CustomerService  customerService;
	@Inject
	AdviceStatusService  adviceStatusService;
	@Inject
	AdviceTypeService  adviceTypeService;
	
	/**
	 * @param entityClass
	 */
	public AdviceServiceImpl() {
		super(Advice.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.Advice#findById(java.lang.Integer)
	 */
	@Override
	public Advice findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.Advice#findByCode(java.lang.String)
	 */
	@Override
	public Advice findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.Advice#findByName(java.lang.String)
	 */
	@Override
	public Advice findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.Advice#findAll(java.util.Map)
	 */
	@Override
	public List<Advice> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.Advice#createAdvice(com.nathanclaire.alantra.advice.rest.request.ServiceRequest)
	 */
	@Override
	public Advice create(AdviceRequest adviceRequest) {
		return createInstance(adviceRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.Advice#deleteAdvice(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.Advice#updateAdvice(com.nathanclaire.alantra.advice.rest.request.ServiceRequest)
	 */
	@Override
	public Advice update(AdviceRequest adviceRequest) {
		return updateInstance(adviceRequest);
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#getListActivityCode()
	 */
	@Override
	public String getListActivityCode() {
		return LIST_ACTIVITY_CODE;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#getEditActivityCode()
	 */
	@Override
	public String getEditActivityCode() {
		return EDIT_ACTIVITY_CODE;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#getEntityName()
	 */
	@Override
	public String getEntityName() {
		return ENTITY_NAME;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#getEntityFields()
	 */
	@Override
	public List<ApplicationEntityField> getEntityFields() {
		return applicationEntityService.getFieldsForEntity(ENTITY_NAME);
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#relatedEntitesToListItems()
	 */
	@Override
	public Map<String, List<ListItemResponse>> relatedEntitesToListItems() 
	{
		Map<String, List<ListItemResponse>> listItems = new HashMap<String, List<ListItemResponse>>(); 
		List<ListItemResponse> customers = customerService.asListItem();
		List<ListItemResponse> adviceStatuss = adviceStatusService.asListItem();
		List<ListItemResponse> adviceTypes = adviceTypeService.asListItem();
    	
		listItems.put(LIST_ITEM_CUSTOMER, customers); 
		listItems.put(LIST_ITEM_ADVICESTATUS, adviceStatuss); 
		listItems.put(LIST_ITEM_ADVICETYPE, adviceTypes); 
		return listItems;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#asListItem()
	 */
	@Override
	public List<ListItemResponse> asListItem() {
		List<ListItemResponse> listItems = new ArrayList<ListItemResponse>();
		queryParameters.clear();
		for(Advice advice: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(advice.getId(), advice.getCode(), advice.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public Advice convertRequestToModel(AdviceRequest adviceRequest) 
    {
		Advice advice = new Advice();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(adviceRequest, advice, allowedEntityFields);
    	//Process many to one relationships
    	if (adviceRequest.getCustomerId() != null)
    	{
    		Customer customer = getEntityManager().find(Customer.class, adviceRequest.getCustomerId());
    		advice.setCustomer(customer);
    	}
    	if (adviceRequest.getAdviceStatusId() != null)
    	{
    		AdviceStatus adviceStatus = getEntityManager().find(AdviceStatus.class, adviceRequest.getAdviceStatusId());
    		advice.setAdviceStatus(adviceStatus);
    	}
    	if (adviceRequest.getAdviceTypeId() != null)
    	{
    		AdviceType adviceType = getEntityManager().find(AdviceType.class, adviceRequest.getAdviceTypeId());
    		advice.setAdviceType(adviceType);
    	}
		return advice;
	}
	
	@Override
	public AdviceResponse convertModelToResponse(Advice model) {
		if (model == null) return null;
		AdviceResponse adviceResponse = new AdviceResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(model, adviceResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		if(model.getCustomer() != null)
			adviceResponse.setCustomerId(model.getCustomer().getId());
		if(model.getAdviceStatus() != null)
			adviceResponse.setAdviceStatusId(model.getAdviceStatus().getId());
		if(model.getAdviceType() != null)
			adviceResponse.setAdviceTypeId(model.getAdviceType().getId());
		return adviceResponse;
	}
}
