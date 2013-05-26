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

import com.nathanclaire.alantra.advice.model.AdviceStatus;
import com.nathanclaire.alantra.advice.request.AdviceStatusRequest;
import com.nathanclaire.alantra.advice.response.AdviceStatusResponse;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.base.util.PropertyUtils;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class AdviceStatusServiceImpl 
	extends BaseEntityServiceImpl<AdviceStatus, AdviceStatusResponse, AdviceStatusRequest> 
	implements AdviceStatusService
{
	private static final String ENTITY_NAME = "AdviceStatus";
	private static final String LIST_ACTIVITY_CODE = "LIST_ADVICE_ADVICESTATUS";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_ADVICE_ADVICESTATUS";
	
	private Logger logger = LoggerFactory.getLogger(AdviceStatusServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	
	/**
	 * @param entityClass
	 */
	public AdviceStatusServiceImpl() {
		super(AdviceStatus.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.AdviceStatus#findById(java.lang.Integer)
	 */
	@Override
	public AdviceStatus findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.AdviceStatus#findByCode(java.lang.String)
	 */
	@Override
	public AdviceStatus findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.AdviceStatus#findByName(java.lang.String)
	 */
	@Override
	public AdviceStatus findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.AdviceStatus#findAll(java.util.Map)
	 */
	@Override
	public List<AdviceStatus> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.AdviceStatus#createAdviceStatus(com.nathanclaire.alantra.advice.rest.request.ServiceRequest)
	 */
	@Override
	public AdviceStatus create(AdviceStatusRequest adviceStatusRequest) {
		return createInstance(adviceStatusRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.AdviceStatus#deleteAdviceStatus(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.AdviceStatus#updateAdviceStatus(com.nathanclaire.alantra.advice.rest.request.ServiceRequest)
	 */
	@Override
	public AdviceStatus update(AdviceStatusRequest adviceStatusRequest) {
		return updateInstance(adviceStatusRequest);
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
    	
		return listItems;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#asListItem()
	 */
	@Override
	public List<ListItemResponse> asListItem() {
		List<ListItemResponse> listItems = new ArrayList<ListItemResponse>();
		queryParameters.clear();
		for(AdviceStatus advicestatus: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(advicestatus.getId(), advicestatus.getCode(), advicestatus.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public AdviceStatus convertRequestToModel(AdviceStatusRequest adviceStatusRequest) 
    {
		AdviceStatus adviceStatus = new AdviceStatus();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(adviceStatusRequest, adviceStatus, allowedEntityFields);
    	//Process many to one relationships
		return adviceStatus;
	}
	
	@Override
	public AdviceStatusResponse convertModelToResponse(AdviceStatus model) {
		if (model == null) return null;
		AdviceStatusResponse adviceStatusResponse = new AdviceStatusResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(model, adviceStatusResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		return adviceStatusResponse;
	}
}
