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

import com.nathanclaire.alantra.notification.model.TemplateTypeTag;
import com.nathanclaire.alantra.notification.model.TemplateType;
import com.nathanclaire.alantra.notification.request.TemplateTypeTagRequest;
import com.nathanclaire.alantra.notification.response.TemplateTypeTagResponse;
import com.nathanclaire.alantra.notification.service.entity.TemplateTypeService;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtil;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class TemplateTypeTagServiceImpl 
	extends BaseEntityServiceImpl<TemplateTypeTag, TemplateTypeTagResponse, TemplateTypeTagRequest> 
	implements TemplateTypeTagService
{
	private static final String LIST_ITEM_TEMPLATETYPE = "templateType";
	private static final String ENTITY_NAME = "TemplateTypeTag";
	private static final String LIST_ACTIVITY_CODE = "LIST_NOTIFICATION_TEMPLATETYPETAG";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_NOTIFICATION_TEMPLATETYPETAG";
	
	private Logger logger = LoggerFactory.getLogger(TemplateTypeTagServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	@Inject
	TemplateTypeService  templateTypeService;
	
	/**
	 * @param entityClass
	 */
	public TemplateTypeTagServiceImpl() {
		super(TemplateTypeTag.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.notification.service.TemplateTypeTag#findById(java.lang.Integer)
	 */
	@Override
	public TemplateTypeTag findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.notification.service.TemplateTypeTag#findByCode(java.lang.String)
	 */
	@Override
	public TemplateTypeTag findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.notification.service.TemplateTypeTag#findByName(java.lang.String)
	 */
	@Override
	public TemplateTypeTag findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.notification.service.TemplateTypeTag#findAll(java.util.Map)
	 */
	@Override
	public List<TemplateTypeTag> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.notification.service.TemplateTypeTag#createTemplateTypeTag(com.nathanclaire.alantra.notification.rest.request.ServiceRequest)
	 */
	@Override
	public TemplateTypeTag create(TemplateTypeTagRequest templateTypeTagRequest) throws ApplicationException {
		return createInstance(templateTypeTagRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.notification.service.TemplateTypeTag#deleteTemplateTypeTag(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.notification.service.TemplateTypeTag#updateTemplateTypeTag(com.nathanclaire.alantra.notification.rest.request.ServiceRequest)
	 */
	@Override
	public TemplateTypeTag update(TemplateTypeTagRequest templateTypeTagRequest) throws ApplicationException {
		return updateInstance(templateTypeTagRequest);
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
    	
		listItems.put(LIST_ITEM_TEMPLATETYPE, templateTypes); 
		return listItems;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#asListItem()
	 */
	@Override
	public List<ListItemResponse> asListItem() throws ApplicationException {
		List<ListItemResponse> listItems = new ArrayList<ListItemResponse>();
		queryParameters.clear();
		for(TemplateTypeTag templatetypetag: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(templatetypetag.getId(), templatetypetag.getCode(), templatetypetag.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public TemplateTypeTag convertRequestToModel(TemplateTypeTagRequest templateTypeTagRequest) 
     throws ApplicationException {
		TemplateTypeTag templateTypeTag = new TemplateTypeTag();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtil.copyProperties(templateTypeTagRequest, templateTypeTag, allowedEntityFields);
    	//Process many to one relationships
    	if (templateTypeTagRequest.getTemplateTypeId() != null)
    	{
    		TemplateType templateType = getEntityManager().find(TemplateType.class, templateTypeTagRequest.getTemplateTypeId());
    		templateTypeTag.setTemplateType(templateType);
    	}
		return templateTypeTag;
	}
	
	@Override
	public TemplateTypeTagResponse convertModelToResponse(TemplateTypeTag model) throws ApplicationException {
		if (model == null) return null;
		TemplateTypeTagResponse templateTypeTagResponse = new TemplateTypeTagResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtil.copyProperties(model, templateTypeTagResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		if(model.getTemplateType() != null)
			templateTypeTagResponse.setTemplateTypeId(model.getTemplateType().getId());
			templateTypeTagResponse.setTemplateTypeText(model.getTemplateType().getName());
		return templateTypeTagResponse;
	}
}
