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
import com.nathanclaire.alantra.application.model.ApplicationFormField;
import com.nathanclaire.alantra.application.request.ApplicationFormFieldRequest;
import com.nathanclaire.alantra.application.response.ApplicationFormFieldResponse;

import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.application.response.ApplicationEntityFieldResponse;


import com.nathanclaire.alantra.application.model.ApplicationFormFieldType;
import com.nathanclaire.alantra.application.model.ApplicationForm;
import com.nathanclaire.alantra.application.model.ApplicationEntityField;
import com.nathanclaire.alantra.application.service.entity.ApplicationFormFieldTypeService;
import com.nathanclaire.alantra.application.service.entity.ApplicationFormService;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityFieldService;
import com.nathanclaire.alantra.base.util.PropertyUtils;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class ApplicationFormFieldServiceImpl 
	extends BaseEntityServiceImpl<ApplicationFormField, ApplicationFormFieldResponse, ApplicationFormFieldRequest> 
	implements ApplicationFormFieldService
{
	
	private static final String ENTITY_NAME = "ApplicationFormField";
	private static final String LIST_ACTIVITY_CODE = "LIST_APPLICATIONFORMFIELD";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_APPLICATIONFORMFIELD";
	
	private static final String LIST_ITEM_APPLICATIONFORMFIELDTYPE = "applicationFormFieldType";
	private static final String LIST_ITEM_APPLICATIONFORM = "applicationForm";
	private static final String LIST_ITEM_APPLICATIONENTITYFIELD = "applicationEntityField";
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	@Inject
	ApplicationFormFieldTypeService  applicationFormFieldTypeService;
	@Inject
	ApplicationFormService  applicationFormService;
	@Inject
	ApplicationEntityFieldService  applicationEntityFieldService;
	
	/**
	 * @param entityClass
	 */
	public ApplicationFormFieldServiceImpl() {
		super(ApplicationFormField.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.ApplicationFormField#findById(java.lang.Integer)
	 */
	@Override
	public ApplicationFormField findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.ApplicationFormField#findByCode(java.lang.String)
	 */
	@Override
	public ApplicationFormField findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.ApplicationFormField#findByName(java.lang.String)
	 */
	@Override
	public ApplicationFormField findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.ApplicationFormField#findAll(java.util.Map)
	 */
	@Override
	public List<ApplicationFormField> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.ApplicationFormField#createApplicationFormField(com.nathanclaire.alantra.application.rest.request.ServiceRequest)
	 */
	@Override
	public ApplicationFormField create(ApplicationFormFieldRequest applicationFormFieldRequest) {
		return createInstance(applicationFormFieldRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.ApplicationFormField#deleteApplicationFormField(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.ApplicationFormField#updateApplicationFormField(com.nathanclaire.alantra.application.rest.request.ServiceRequest)
	 */
	@Override
	public ApplicationFormField update(ApplicationFormFieldRequest applicationFormFieldRequest) {
		return updateInstance(applicationFormFieldRequest);
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
		List<ListItemResponse> applicationFormFieldTypes = applicationFormFieldTypeService.asListItem();
		List<ListItemResponse> applicationForms = applicationFormService.asListItem();
		List<ListItemResponse> applicationEntityFields = applicationEntityFieldService.asListItem();
    	
		listItems.put(LIST_ITEM_APPLICATIONFORMFIELDTYPE, applicationFormFieldTypes); 
		listItems.put(LIST_ITEM_APPLICATIONFORM, applicationForms); 
		listItems.put(LIST_ITEM_APPLICATIONENTITYFIELD, applicationEntityFields); 
		return listItems;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#asListItem()
	 */
	@Override
	public List<ListItemResponse> asListItem() {
		List<ListItemResponse> listItems = new ArrayList<ListItemResponse>();
		for(ApplicationFormField applicationformfield: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(applicationformfield.getId(), applicationformfield.getCode(), applicationformfield.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public ApplicationFormField convertRequestToModel(ApplicationFormFieldRequest applicationFormFieldRequest) 
    {
		ApplicationFormField applicationFormField = new ApplicationFormField();
		applicationFormField = this.loadDefaultFieldsFromRequest(applicationFormField, applicationFormFieldRequest);
    	//Process many to one relationships
    	if (applicationFormFieldRequest.getApplicationFormFieldType() != null)
    	{
    		ApplicationFormFieldType applicationFormFieldType = getEntityManager().find(ApplicationFormFieldType.class, applicationFormFieldRequest.getApplicationFormFieldType());
    		applicationFormField.setApplicationFormFieldType(applicationFormFieldType);
    	}
    	if (applicationFormFieldRequest.getApplicationForm() != null)
    	{
    		ApplicationForm applicationForm = getEntityManager().find(ApplicationForm.class, applicationFormFieldRequest.getApplicationForm());
    		applicationFormField.setApplicationForm(applicationForm);
    	}
    	if (applicationFormFieldRequest.getApplicationEntityField() != null)
    	{
    		ApplicationEntityField applicationEntityField = getEntityManager().find(ApplicationEntityField.class, applicationFormFieldRequest.getApplicationEntityField());
    		applicationFormField.setApplicationEntityField(applicationEntityField);
    	}
    	applicationFormField.setName(applicationFormFieldRequest.getName()); 
    	applicationFormField.setDescription(applicationFormFieldRequest.getDescription()); 
    	applicationFormField.setPrimarykeyFg(applicationFormFieldRequest.getPrimarykeyFg()); 
    	applicationFormField.setRequiredFg(applicationFormFieldRequest.getRequiredFg()); 
    	applicationFormField.setRelatedFg(applicationFormFieldRequest.getRelatedFg()); 
    	applicationFormField.setSize(applicationFormFieldRequest.getSize()); 
    	applicationFormField.setMaxDigits(applicationFormFieldRequest.getMaxDigits()); 
    	applicationFormField.setDecimalPrecision(applicationFormFieldRequest.getDecimalPrecision()); 
    	applicationFormField.setSequenceNo(applicationFormFieldRequest.getSequenceNo()); 
    	applicationFormField.setCode(applicationFormFieldRequest.getCode()); 
    	applicationFormField.setRecSt(applicationFormFieldRequest.getRecSt()); 
		return applicationFormField;
	}
	
	@Override
	public ApplicationFormFieldResponse convertModelToResponse(ApplicationFormField model) {
		if (model == null) return null;
		ApplicationFormFieldResponse applicationFormFieldResponse = new ApplicationFormFieldResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(model, applicationFormFieldResponse, allowedEntityFields);
		return applicationFormFieldResponse;
	}
}
