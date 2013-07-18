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

import com.nathanclaire.alantra.application.model.ApplicationEntity;
import com.nathanclaire.alantra.application.model.ApplicationEntityField;
import com.nathanclaire.alantra.application.model.ApplicationEntityFieldType;
import com.nathanclaire.alantra.application.request.ApplicationEntityFieldRequest;
import com.nathanclaire.alantra.application.response.ApplicationEntityFieldResponse;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.base.util.ApplicationException;
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
	public ApplicationEntityField findById(Integer id)  throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.ApplicationEntityField#findByCode(java.lang.String)
	 */
	@Override
	public ApplicationEntityField findByCode(String code)  throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.ApplicationEntityField#findByName(java.lang.String)
	 */
	@Override
	public ApplicationEntityField findByName(String name)  throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.ApplicationEntityField#findAll(java.util.Map)
	 */
	@Override
	public List<ApplicationEntityField> findAll(MultivaluedMap<String, String> queryParameters)  throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.ApplicationEntityField#createApplicationEntityField(com.nathanclaire.alantra.application.rest.request.ServiceRequest)
	 */
	@Override
	public ApplicationEntityField create(ApplicationEntityFieldRequest applicationEntityFieldRequest)  throws ApplicationException {
		return createInstance(applicationEntityFieldRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.ApplicationEntityField#deleteApplicationEntityField(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id)  throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.ApplicationEntityField#updateApplicationEntityField(com.nathanclaire.alantra.application.rest.request.ServiceRequest)
	 */
	@Override
	public ApplicationEntityField update(ApplicationEntityFieldRequest applicationEntityFieldRequest)  throws ApplicationException {
		return updateInstance(applicationEntityFieldRequest);
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#getListActivityCode()
	 */
	@Override
	public String getListActivityCode()  throws ApplicationException {
		return LIST_ACTIVITY_CODE;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#getEditActivityCode()
	 */
	@Override
	public String getEditActivityCode()  throws ApplicationException {
		return EDIT_ACTIVITY_CODE;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#getEntityName()
	 */
	@Override
	public String getEntityName()  throws ApplicationException {
		return ENTITY_NAME;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#getEntityFields()
	 */
	@Override
	public List<ApplicationEntityField> getEntityFields()  throws ApplicationException {
		return applicationEntityService.getFieldsForEntity(ENTITY_NAME);
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#relatedEntitesToListItems()
	 */
	@Override
	public Map<String, List<ListItemResponse>> relatedEntitesToListItems()  throws ApplicationException
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
	public List<ListItemResponse> asListItem()  throws ApplicationException {
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
	     throws ApplicationException {
			ApplicationEntityField applicationEntityField = new ApplicationEntityField();
			// Copy properties
			List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
			PropertyUtils.copyProperties(applicationEntityFieldRequest, applicationEntityField, allowedEntityFields);
	    	//Process many to one relationships
	    	if (applicationEntityFieldRequest.getApplicationEntityFieldTypeId() != null)
	    	{
	    		ApplicationEntityFieldType applicationEntityFieldType = getEntityManager().find(ApplicationEntityFieldType.class, applicationEntityFieldRequest.getApplicationEntityFieldTypeId());
	    		applicationEntityField.setApplicationEntityFieldType(applicationEntityFieldType);
	    	}
	    	if (applicationEntityFieldRequest.getApplicationRelatedEntityId() != null)
	    	{
	    		ApplicationEntity applicationRelatedEntity = getEntityManager().find(ApplicationEntity.class, applicationEntityFieldRequest.getApplicationRelatedEntityId());
	    		applicationEntityField.setApplicationRelatedEntity(applicationRelatedEntity);
	    	}
	    	if (applicationEntityFieldRequest.getApplicationEntityId() != null)
	    	{
	    		ApplicationEntity applicationEntity = getEntityManager().find(ApplicationEntity.class, applicationEntityFieldRequest.getApplicationEntityId());
	    		applicationEntityField.setApplicationEntity(applicationEntity);
	    	}
			return applicationEntityField;
		}
    		/*
	@Override
    public ApplicationEntityField convertRequestToModel(ApplicationEntityFieldRequest applicationEntityFieldRequest) 
    {
		ApplicationEntityField applicationEntityField = new ApplicationEntityField();
		applicationEntityField = this.loadDefaultFieldsFromRequest(applicationEntityField, applicationEntityFieldRequest);
    	//Process many to one relationships
    	if (applicationEntityFieldRequest.getApplicationEntityFieldTypeId() != null)
    	{
    		ApplicationEntityFieldType applicationEntityFieldType = getEntityManager().find(ApplicationEntityFieldType.class, applicationEntityFieldRequest.getApplicationEntityFieldTypeId());
    		applicationEntityField.setApplicationEntityFieldType(applicationEntityFieldType);
    	}
    	if (applicationEntityFieldRequest.getApplicationRelatedEntity() != null)
    	{
    		ApplicationEntity applicationRelatedEntity = getEntityManager().find(ApplicationEntity.class, applicationEntityFieldRequest.getApplicationRelatedEntity());
    		applicationEntityField.setApplicationRelatedEntity(applicationRelatedEntity);
    	}
    	if (applicationEntityFieldRequest.getApplicationEntity() != null)
    	{
    		ApplicationEntity applicationEntity = getEntityManager().find(ApplicationEntity.class, applicationEntityFieldRequest.getApplicationEntity());
    		applicationEntityField.setApplicationEntity(applicationEntity);
    	}
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
	}*/
	
	@Override
	public ApplicationEntityFieldResponse convertModelToResponse(ApplicationEntityField model)  throws ApplicationException {
		if (model == null) return null;
		/*ApplicationEntityFieldResponse applicationEntityFieldResponse = new ApplicationEntityFieldResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(model, applicationEntityFieldResponse, allowedEntityFields);
		ApplicationEntityFieldType fieldType = model.getApplicationEntityFieldType();
		applicationEntityFieldResponse.setApplicationEntityFieldTypeText(this.getFieldTypeCode(fieldType));
		return applicationEntityFieldResponse;*/
		ApplicationEntityFieldResponse applicationEntityFieldResponse = new ApplicationEntityFieldResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(model, applicationEntityFieldResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		if(model.getApplicationEntityFieldType() != null){
			applicationEntityFieldResponse.setApplicationEntityFieldTypeId(model.getApplicationEntityFieldType().getId());
			ApplicationEntityFieldType fieldType = model.getApplicationEntityFieldType();
			applicationEntityFieldResponse.setApplicationEntityFieldTypeText(this.getFieldTypeCode(fieldType));
		}
		if(model.getApplicationRelatedEntity() != null){
			applicationEntityFieldResponse.setApplicationRelatedEntityId(model.getApplicationRelatedEntity().getId());
			applicationEntityFieldResponse.setApplicationRelatedEntityText(model.getApplicationRelatedEntity().getName());
		}
		if(model.getApplicationEntity() != null){
			applicationEntityFieldResponse.setApplicationEntityId(model.getApplicationEntity().getId());
			applicationEntityFieldResponse.setApplicationEntityText(model.getApplicationEntity().getName());
		}
		return applicationEntityFieldResponse;
	}
	
	/**
	 * Converts the field type into a code that is suitable for use with the ApplicationEntityFieldResponse
	 * @param fieldType
	 * @return
	 */
	private String getFieldTypeCode(ApplicationEntityFieldType fieldType)
	{
		return fieldType.getCode();
	}
}