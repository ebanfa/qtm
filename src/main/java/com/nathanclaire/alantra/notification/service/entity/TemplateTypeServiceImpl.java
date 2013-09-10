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

import com.nathanclaire.alantra.notification.model.TemplateType;
import com.nathanclaire.alantra.notification.model.TemplateCategory;
import com.nathanclaire.alantra.notification.request.TemplateTypeRequest;
import com.nathanclaire.alantra.notification.response.TemplateTypeResponse;
import com.nathanclaire.alantra.notification.service.entity.TemplateCategoryService;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtil;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class TemplateTypeServiceImpl 
	extends BaseEntityServiceImpl<TemplateType, TemplateTypeResponse, TemplateTypeRequest> 
	implements TemplateTypeService
{
	private static final String LIST_ITEM_TEMPLATECATEGORY = "templateCategory";
	private static final String ENTITY_NAME = "TemplateType";
	private static final String LIST_ACTIVITY_CODE = "LIST_NOTIFICATION_TEMPLATETYPE";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_NOTIFICATION_TEMPLATETYPE";
	
	private Logger logger = LoggerFactory.getLogger(TemplateTypeServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	@Inject
	TemplateCategoryService  templateCategoryService;
	
	/**
	 * @param entityClass
	 */
	public TemplateTypeServiceImpl() {
		super(TemplateType.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.notification.service.TemplateType#findById(java.lang.Integer)
	 */
	@Override
	public TemplateType findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.notification.service.TemplateType#findByCode(java.lang.String)
	 */
	@Override
	public TemplateType findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.notification.service.TemplateType#findByName(java.lang.String)
	 */
	@Override
	public TemplateType findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.notification.service.TemplateType#findAll(java.util.Map)
	 */
	@Override
	public List<TemplateType> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.notification.service.TemplateType#createTemplateType(com.nathanclaire.alantra.notification.rest.request.ServiceRequest)
	 */
	@Override
	public TemplateType create(TemplateTypeRequest templateTypeRequest) throws ApplicationException {
		return createInstance(templateTypeRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.notification.service.TemplateType#deleteTemplateType(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.notification.service.TemplateType#updateTemplateType(com.nathanclaire.alantra.notification.rest.request.ServiceRequest)
	 */
	@Override
	public TemplateType update(TemplateTypeRequest templateTypeRequest) throws ApplicationException {
		return updateInstance(templateTypeRequest);
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
		List<ListItemResponse> templateCategorys = templateCategoryService.asListItem();
    	
		listItems.put(LIST_ITEM_TEMPLATECATEGORY, templateCategorys); 
		return listItems;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#asListItem()
	 */
	@Override
	public List<ListItemResponse> asListItem() throws ApplicationException {
		List<ListItemResponse> listItems = new ArrayList<ListItemResponse>();
		queryParameters.clear();
		for(TemplateType templatetype: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(templatetype.getId(), templatetype.getCode(), templatetype.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public TemplateType convertRequestToModel(TemplateTypeRequest templateTypeRequest) 
     throws ApplicationException {
		TemplateType templateType = new TemplateType();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtil.copyProperties(templateTypeRequest, templateType, allowedEntityFields);
    	//Process many to one relationships
    	if (templateTypeRequest.getTemplateCategoryId() != null)
    	{
    		TemplateCategory templateCategory = getEntityManager().find(TemplateCategory.class, templateTypeRequest.getTemplateCategoryId());
    		templateType.setTemplateCategory(templateCategory);
    	}
		return templateType;
	}
	
	@Override
	public TemplateTypeResponse convertModelToResponse(TemplateType model) throws ApplicationException {
		if (model == null) return null;
		TemplateTypeResponse templateTypeResponse = new TemplateTypeResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtil.copyProperties(model, templateTypeResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		if(model.getTemplateCategory() != null)
			templateTypeResponse.setTemplateCategoryId(model.getTemplateCategory().getId());
			templateTypeResponse.setTemplateCategoryText(model.getTemplateCategory().getName());
		return templateTypeResponse;
	}
}
