/**
 * 
 */
package com.nathanclaire.alantra.application.service.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.application.model.ApplicationEntityField;
import com.nathanclaire.alantra.application.model.ApplicationEntityField;
import com.nathanclaire.alantra.application.request.ApplicationEntityFieldRequest;
import com.nathanclaire.alantra.application.response.ApplicationEntityFieldResponse;

import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.application.response.ApplicationEntityFieldResponse;


import com.nathanclaire.alantra.application.model.ApplicationEntityFieldType;
import com.nathanclaire.alantra.application.model.ApplicationEntity;
import com.nathanclaire.alantra.application.model.ApplicationEntity;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityFieldTypeService;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.util.PropertyUtils;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class ApplicationEntityFieldServiceImpl 
	extends BaseEntityServiceImpl<ApplicationEntityField, ApplicationEntityFieldResponse, ApplicationEntityFieldRequest> 
	implements ApplicationEntityFieldService
{
	
	private static final String ENTITY_NAME = "ApplicationEntityField";
	private static final String LIST_ACTIVITY_CODE = "LIST_APPLICATIONENTITYFIELD";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_APPLICATIONENTITYFIELD";
	
	private static final String LIST_ITEM_APPLICATIONENTITYFIELDTYPE = "applicationEntityFieldType";
	private static final String LIST_ITEM_APPLICATIONRELATEDENTITY = "applicationRelatedEntity";
	private static final String LIST_ITEM_APPLICATIONENTITY = "applicationEntity";
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	@Inject
	ApplicationEntityFieldTypeService  applicationEntityFieldTypeService;
	
	/**
	 * @param entityClass
	 */
	public ApplicationEntityFieldServiceImpl() {
		super(ApplicationEntityField.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.ApplicationEntityField#findById(java.lang.Integer)
	 */
	@Override
	public ApplicationEntityField findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.ApplicationEntityField#findByCode(java.lang.String)
	 */
	@Override
	public ApplicationEntityField findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.ApplicationEntityField#findByName(java.lang.String)
	 */
	@Override
	public ApplicationEntityField findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.ApplicationEntityField#findAll(java.util.Map)
	 */
	@Override
	public List<ApplicationEntityField> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.ApplicationEntityField#createApplicationEntityField(com.nathanclaire.alantra.application.rest.request.ServiceRequest)
	 */
	@Override
	public ApplicationEntityField create(ApplicationEntityFieldRequest applicationEntityFieldRequest) {
		return createInstance(applicationEntityFieldRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.ApplicationEntityField#deleteApplicationEntityField(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.ApplicationEntityField#updateApplicationEntityField(com.nathanclaire.alantra.application.rest.request.ServiceRequest)
	 */
	@Override
	public ApplicationEntityField update(ApplicationEntityFieldRequest applicationEntityFieldRequest) {
		return updateInstance(applicationEntityFieldRequest);
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
		List<ListItemResponse> applicationEntityFieldTypes = applicationEntityFieldTypeService.asListItem();
		List<ListItemResponse> applicationRelatedEntitys = applicationEntityService.asListItem();
		List<ListItemResponse> applicationEntitys = applicationEntityService.asListItem();
    	
		listItems.put(LIST_ITEM_APPLICATIONENTITYFIELDTYPE, applicationEntityFieldTypes); 
		listItems.put(LIST_ITEM_APPLICATIONRELATEDENTITY, applicationRelatedEntitys); 
		listItems.put(LIST_ITEM_APPLICATIONENTITY, applicationEntitys); 
		return listItems;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#asListItem()
	 */
	@Override
	public List<ListItemResponse> asListItem() {
		List<ListItemResponse> listItems = new ArrayList<ListItemResponse>();
		for(ApplicationEntityField applicationentityfield: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(applicationentityfield.getId(), applicationentityfield.getCode(), applicationentityfield.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public ApplicationEntityField convertRequestToModel(ApplicationEntityFieldRequest applicationEntityFieldRequest) 
    {
		ApplicationEntityField applicationEntityField = new ApplicationEntityField();
		applicationEntityField = this.loadDefaultFieldsFromRequest(applicationEntityField, applicationEntityFieldRequest);
    	//Process many to one relationships
    	if (applicationEntityFieldRequest.getApplicationEntityFieldType() != null)
    	{
    		ApplicationEntityFieldType applicationEntityFieldType = getEntityManager().find(ApplicationEntityFieldType.class, applicationEntityFieldRequest.getApplicationEntityFieldType());
    		applicationEntityField.setApplicationEntityFieldType(applicationEntityFieldType);
    	}
    	/*if (applicationEntityFieldRequest.getApplicationRelatedEntity() != null)
    	{
    		ApplicationEntity applicationRelatedEntity = getEntityManager().find(ApplicationEntity.class, applicationEntityFieldRequest.getApplicationRelatedEntity());
    		applicationEntityField.setApplicationRelatedEntity(applicationRelatedEntity);
    	}
    	if (applicationEntityFieldRequest.getApplicationEntity() != null)
    	{
    		ApplicationEntity applicationEntity = getEntityManager().find(ApplicationEntity.class, applicationEntityFieldRequest.getApplicationEntity());
    		applicationEntityField.setApplicationEntity(applicationEntity);
    	}*/
    	applicationEntityField.setName(applicationEntityFieldRequest.getName()); 
    	applicationEntityField.setDescription(applicationEntityFieldRequest.getDescription()); 
    	applicationEntityField.setPrimarykeyFg(applicationEntityFieldRequest.getPrimarykeyFg()); 
    	applicationEntityField.setStorage(applicationEntityFieldRequest.getStorage()); 
    	applicationEntityField.setRequiredFg(applicationEntityFieldRequest.getRequiredFg()); 
    	applicationEntityField.setUniqueFg(applicationEntityFieldRequest.getUniqueFg()); 
    	applicationEntityField.setRelatedFg(applicationEntityFieldRequest.getRelatedFg()); 
    	applicationEntityField.setSize(applicationEntityFieldRequest.getSize()); 
    	applicationEntityField.setMaxDigits(applicationEntityFieldRequest.getMaxDigits()); 
    	applicationEntityField.setDecimalPrecision(applicationEntityFieldRequest.getDecimalPrecision()); 
    	applicationEntityField.setSequenceNo(applicationEntityFieldRequest.getSequenceNo()); 
    	applicationEntityField.setCode(applicationEntityFieldRequest.getCode()); 
    	applicationEntityField.setRecSt(applicationEntityFieldRequest.getRecSt()); 
		return applicationEntityField;
	}
	
	@Override
	public ApplicationEntityFieldResponse convertModelToResponse(ApplicationEntityField model) {
		if (model == null) return null;
		ApplicationEntityFieldResponse applicationEntityFieldResponse = new ApplicationEntityFieldResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(model, applicationEntityFieldResponse, allowedEntityFields);
		return applicationEntityFieldResponse;
	}
}
