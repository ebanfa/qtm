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

import com.nathanclaire.alantra.notification.model.Template;
import com.nathanclaire.alantra.notification.model.TemplateType;
import com.nathanclaire.alantra.notification.model.TemplateClassification;
import com.nathanclaire.alantra.notification.request.TemplateRequest;
import com.nathanclaire.alantra.notification.response.TemplateResponse;
import com.nathanclaire.alantra.notification.service.entity.TemplateTypeService;
import com.nathanclaire.alantra.notification.service.entity.TemplateClassificationService;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtils;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class TemplateServiceImpl 
	extends BaseEntityServiceImpl<Template, TemplateResponse, TemplateRequest> 
	implements TemplateService
{
	private static final String LIST_ITEM_TEMPLATETYPE = "templateType";
	private static final String LIST_ITEM_TEMPLATECLASSIFICATION = "templateClassification";
	private static final String ENTITY_NAME = "Template";
	private static final String LIST_ACTIVITY_CODE = "LIST_NOTIFICATION_TEMPLATE";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_NOTIFICATION_TEMPLATE";
	
	private Logger logger = LoggerFactory.getLogger(TemplateServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	@Inject
	TemplateTypeService  templateTypeService;
	@Inject
	TemplateClassificationService  templateClassificationService;
	
	/**
	 * @param entityClass
	 */
	public TemplateServiceImpl() {
		super(Template.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.notification.service.Template#findById(java.lang.Integer)
	 */
	@Override
	public Template findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.notification.service.Template#findByCode(java.lang.String)
	 */
	@Override
	public Template findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.notification.service.Template#findByName(java.lang.String)
	 */
	@Override
	public Template findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.notification.service.Template#findAll(java.util.Map)
	 */
	@Override
	public List<Template> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.notification.service.Template#createTemplate(com.nathanclaire.alantra.notification.rest.request.ServiceRequest)
	 */
	@Override
	public Template create(TemplateRequest templateRequest) throws ApplicationException {
		return createInstance(templateRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.notification.service.Template#deleteTemplate(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.notification.service.Template#updateTemplate(com.nathanclaire.alantra.notification.rest.request.ServiceRequest)
	 */
	@Override
	public Template update(TemplateRequest templateRequest) throws ApplicationException {
		return updateInstance(templateRequest);
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
		List<ListItemResponse> templateTypes = templateTypeService.asListItem();
		List<ListItemResponse> templateClassifications = templateClassificationService.asListItem();
    	
		listItems.put(LIST_ITEM_TEMPLATETYPE, templateTypes); 
		listItems.put(LIST_ITEM_TEMPLATECLASSIFICATION, templateClassifications); 
		return listItems;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#asListItem()
	 */
	@Override
	public List<ListItemResponse> asListItem() throws ApplicationException {
		List<ListItemResponse> listItems = new ArrayList<ListItemResponse>();
		queryParameters.clear();
		for(Template template: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(template.getId(), template.getCode(), template.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public Template convertRequestToModel(TemplateRequest templateRequest) 
     throws ApplicationException {
		Template template = new Template();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(templateRequest, template, allowedEntityFields);
    	//Process many to one relationships
    	if (templateRequest.getTemplateTypeId() != null)
    	{
    		TemplateType templateType = getEntityManager().find(TemplateType.class, templateRequest.getTemplateTypeId());
    		template.setTemplateType(templateType);
    	}
    	if (templateRequest.getTemplateClassificationId() != null)
    	{
    		TemplateClassification templateClassification = getEntityManager().find(TemplateClassification.class, templateRequest.getTemplateClassificationId());
    		template.setTemplateClassification(templateClassification);
    	}
		return template;
	}
	
	@Override
	public TemplateResponse convertModelToResponse(Template model) throws ApplicationException {
		if (model == null) return null;
		TemplateResponse templateResponse = new TemplateResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(model, templateResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		if(model.getTemplateType() != null)
			templateResponse.setTemplateTypeId(model.getTemplateType().getId());
			templateResponse.setTemplateTypeText(model.getTemplateType().getName());
		if(model.getTemplateClassification() != null)
			templateResponse.setTemplateClassificationId(model.getTemplateClassification().getId());
			templateResponse.setTemplateClassificationText(model.getTemplateClassification().getName());
		return templateResponse;
	}
}
