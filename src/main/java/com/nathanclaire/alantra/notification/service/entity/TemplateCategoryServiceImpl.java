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

import com.nathanclaire.alantra.notification.model.TemplateCategory;
import com.nathanclaire.alantra.notification.request.TemplateCategoryRequest;
import com.nathanclaire.alantra.notification.response.TemplateCategoryResponse;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtil;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class TemplateCategoryServiceImpl 
	extends BaseEntityServiceImpl<TemplateCategory, TemplateCategoryResponse, TemplateCategoryRequest> 
	implements TemplateCategoryService
{
	private static final String ENTITY_NAME = "TemplateCategory";
	private static final String LIST_ACTIVITY_CODE = "LIST_NOTIFICATION_TEMPLATECATEGORY";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_NOTIFICATION_TEMPLATECATEGORY";
	
	private Logger logger = LoggerFactory.getLogger(TemplateCategoryServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	
	/**
	 * @param entityClass
	 */
	public TemplateCategoryServiceImpl() {
		super(TemplateCategory.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.notification.service.TemplateCategory#findById(java.lang.Integer)
	 */
	@Override
	public TemplateCategory findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.notification.service.TemplateCategory#findByCode(java.lang.String)
	 */
	@Override
	public TemplateCategory findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.notification.service.TemplateCategory#findByName(java.lang.String)
	 */
	@Override
	public TemplateCategory findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.notification.service.TemplateCategory#findAll(java.util.Map)
	 */
	@Override
	public List<TemplateCategory> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.notification.service.TemplateCategory#createTemplateCategory(com.nathanclaire.alantra.notification.rest.request.ServiceRequest)
	 */
	@Override
	public TemplateCategory create(TemplateCategoryRequest templateCategoryRequest) throws ApplicationException {
		return createInstance(templateCategoryRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.notification.service.TemplateCategory#deleteTemplateCategory(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.notification.service.TemplateCategory#updateTemplateCategory(com.nathanclaire.alantra.notification.rest.request.ServiceRequest)
	 */
	@Override
	public TemplateCategory update(TemplateCategoryRequest templateCategoryRequest) throws ApplicationException {
		return updateInstance(templateCategoryRequest);
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
		for(TemplateCategory templatecategory: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(templatecategory.getId(), templatecategory.getCode(), templatecategory.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public TemplateCategory convertRequestToModel(TemplateCategoryRequest templateCategoryRequest) 
     throws ApplicationException {
		TemplateCategory templateCategory = new TemplateCategory();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtil.copyProperties(templateCategoryRequest, templateCategory, allowedEntityFields);
    	//Process many to one relationships
		return templateCategory;
	}
	
	@Override
	public TemplateCategoryResponse convertModelToResponse(TemplateCategory model) throws ApplicationException {
		if (model == null) return null;
		TemplateCategoryResponse templateCategoryResponse = new TemplateCategoryResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtil.copyProperties(model, templateCategoryResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		return templateCategoryResponse;
	}
}
