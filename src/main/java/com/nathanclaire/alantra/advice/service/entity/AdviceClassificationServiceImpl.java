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

import com.nathanclaire.alantra.advice.model.AdviceClassification;
import com.nathanclaire.alantra.advice.request.AdviceClassificationRequest;
import com.nathanclaire.alantra.advice.response.AdviceClassificationResponse;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtil;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class AdviceClassificationServiceImpl 
	extends BaseEntityServiceImpl<AdviceClassification, AdviceClassificationResponse, AdviceClassificationRequest> 
	implements AdviceClassificationService
{
	private static final String ENTITY_NAME = "AdviceClassification";
	private static final String LIST_ACTIVITY_CODE = "LIST_ADVICE_ADVICECLASSIFICATION";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_ADVICE_ADVICECLASSIFICATION";
	
	private Logger logger = LoggerFactory.getLogger(AdviceClassificationServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	
	/**
	 * @param entityClass
	 */
	public AdviceClassificationServiceImpl() {
		super(AdviceClassification.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.AdviceClassification#findById(java.lang.Integer)
	 */
	@Override
	public AdviceClassification findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.AdviceClassification#findByCode(java.lang.String)
	 */
	@Override
	public AdviceClassification findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.AdviceClassification#findByName(java.lang.String)
	 */
	@Override
	public AdviceClassification findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.AdviceClassification#findAll(java.util.Map)
	 */
	@Override
	public List<AdviceClassification> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.AdviceClassification#createAdviceClassification(com.nathanclaire.alantra.advice.rest.request.ServiceRequest)
	 */
	@Override
	public AdviceClassification create(AdviceClassificationRequest adviceClassificationRequest) throws ApplicationException {
		return createInstance(adviceClassificationRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.AdviceClassification#deleteAdviceClassification(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.AdviceClassification#updateAdviceClassification(com.nathanclaire.alantra.advice.rest.request.ServiceRequest)
	 */
	@Override
	public AdviceClassification update(AdviceClassificationRequest adviceClassificationRequest) throws ApplicationException {
		return updateInstance(adviceClassificationRequest);
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
    	
		return listItems;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#asListItem()
	 */
	@Override
	public List<ListItemResponse> asListItem() throws ApplicationException {
		List<ListItemResponse> listItems = new ArrayList<ListItemResponse>();
		queryParameters.clear();
		for(AdviceClassification adviceclassification: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(adviceclassification.getId(), adviceclassification.getCode(), adviceclassification.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public AdviceClassification convertRequestToModel(AdviceClassificationRequest adviceClassificationRequest) 
     throws ApplicationException {
		AdviceClassification adviceClassification = new AdviceClassification();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtil.copyProperties(adviceClassificationRequest, adviceClassification, allowedEntityFields);
    	//Process many to one relationships
		return adviceClassification;
	}
	
	@Override
	public AdviceClassificationResponse convertModelToResponse(AdviceClassification model) throws ApplicationException {
		if (model == null) return null;
		AdviceClassificationResponse adviceClassificationResponse = new AdviceClassificationResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtil.copyProperties(model, adviceClassificationResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		return adviceClassificationResponse;
	}
}
