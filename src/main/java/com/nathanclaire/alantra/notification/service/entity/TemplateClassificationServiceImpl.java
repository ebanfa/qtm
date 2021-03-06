/**
 * 
 */
package com.nathanclaire.alantra.notification.service.entity;

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

import com.nathanclaire.alantra.notification.model.TemplateClassification;
import com.nathanclaire.alantra.notification.request.TemplateClassificationRequest;
import com.nathanclaire.alantra.notification.response.TemplateClassificationResponse;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtil;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class TemplateClassificationServiceImpl 
	extends BaseEntityServiceImpl<TemplateClassification, TemplateClassificationResponse, TemplateClassificationRequest> 
	implements TemplateClassificationService
{
	private static final String ENTITY_NAME = "TemplateClassification";
	private static final String LIST_ACTIVITY_CODE = "LIST_NOTIFICATION_TEMPLATECLASSIFICATION";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_NOTIFICATION_TEMPLATECLASSIFICATION";
	
	private Logger logger = LoggerFactory.getLogger(TemplateClassificationServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	
	/**
	 * @param entityClass
	 */
	public TemplateClassificationServiceImpl() {
		super(TemplateClassification.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.notification.service.TemplateClassification#findById(java.lang.Integer)
	 */
	@Override
	public TemplateClassification findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.notification.service.TemplateClassification#findByCode(java.lang.String)
	 */
	@Override
	public TemplateClassification findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.notification.service.TemplateClassification#findByName(java.lang.String)
	 */
	@Override
	public TemplateClassification findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.notification.service.TemplateClassification#findAll(java.util.Map)
	 */
	@Override
	public List<TemplateClassification> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.notification.service.TemplateClassification#createTemplateClassification(com.nathanclaire.alantra.notification.rest.request.ServiceRequest)
	 */
	@Override
	public TemplateClassification create(TemplateClassificationRequest templateClassificationRequest) throws ApplicationException {
		return createInstance(templateClassificationRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.notification.service.TemplateClassification#deleteTemplateClassification(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.notification.service.TemplateClassification#updateTemplateClassification(com.nathanclaire.alantra.notification.rest.request.ServiceRequest)
	 */
	@Override
	public TemplateClassification update(TemplateClassificationRequest templateClassificationRequest) throws ApplicationException {
		return updateInstance(templateClassificationRequest);
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
		for(TemplateClassification templateclassification: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(templateclassification.getId(), templateclassification.getCode(), templateclassification.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public TemplateClassification convertRequestToModel(TemplateClassificationRequest templateClassificationRequest) 
     throws ApplicationException {
		TemplateClassification templateClassification = new TemplateClassification();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtil.copyProperties(templateClassificationRequest, templateClassification, allowedEntityFields);
    	//Process many to one relationships
		return templateClassification;
	}
	
	@Override
	public TemplateClassificationResponse convertModelToResponse(TemplateClassification model) throws ApplicationException {
		if (model == null) return null;
		TemplateClassificationResponse templateClassificationResponse = new TemplateClassificationResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtil.copyProperties(model, templateClassificationResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		return templateClassificationResponse;
	}
}
