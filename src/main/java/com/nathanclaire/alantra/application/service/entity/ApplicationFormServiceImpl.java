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
import com.nathanclaire.alantra.application.model.ApplicationForm;
import com.nathanclaire.alantra.application.request.ApplicationFormRequest;
import com.nathanclaire.alantra.application.response.ApplicationFormResponse;

import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.application.response.ApplicationEntityFieldResponse;


import com.nathanclaire.alantra.application.model.ApplicationFormType;
import com.nathanclaire.alantra.application.model.ApplicationModule;
import com.nathanclaire.alantra.application.model.ApplicationEntity;
import com.nathanclaire.alantra.application.service.entity.ApplicationFormTypeService;
import com.nathanclaire.alantra.application.service.entity.ApplicationModuleService;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.util.PropertyUtils;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class ApplicationFormServiceImpl 
	extends BaseEntityServiceImpl<ApplicationForm, ApplicationFormResponse, ApplicationFormRequest> 
	implements ApplicationFormService
{
	
	private static final String ENTITY_NAME = "ApplicationForm";
	private static final String LIST_ACTIVITY_CODE = "LIST_APPLICATIONFORM";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_APPLICATIONFORM";
	
	private static final String LIST_ITEM_APPLICATIONFORMTYPE = "applicationFormType";
	private static final String LIST_ITEM_APPLICATIONMODULE = "applicationModule";
	private static final String LIST_ITEM_APPLICATIONENTITY = "applicationEntity";
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	@Inject
	ApplicationFormTypeService  applicationFormTypeService;
	@Inject
	ApplicationModuleService  applicationModuleService;
	
	/**
	 * @param entityClass
	 */
	public ApplicationFormServiceImpl() {
		super(ApplicationForm.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.ApplicationForm#findById(java.lang.Integer)
	 */
	@Override
	public ApplicationForm findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.ApplicationForm#findByCode(java.lang.String)
	 */
	@Override
	public ApplicationForm findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.ApplicationForm#findByName(java.lang.String)
	 */
	@Override
	public ApplicationForm findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.ApplicationForm#findAll(java.util.Map)
	 */
	@Override
	public List<ApplicationForm> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.ApplicationForm#createApplicationForm(com.nathanclaire.alantra.application.rest.request.ServiceRequest)
	 */
	@Override
	public ApplicationForm create(ApplicationFormRequest applicationFormRequest) {
		return createInstance(applicationFormRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.ApplicationForm#deleteApplicationForm(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.ApplicationForm#updateApplicationForm(com.nathanclaire.alantra.application.rest.request.ServiceRequest)
	 */
	@Override
	public ApplicationForm update(ApplicationFormRequest applicationFormRequest) {
		return updateInstance(applicationFormRequest);
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
		List<ListItemResponse> applicationFormTypes = applicationFormTypeService.asListItem();
		List<ListItemResponse> applicationModules = applicationModuleService.asListItem();
		List<ListItemResponse> applicationEntitys = applicationEntityService.asListItem();
    	
		listItems.put(LIST_ITEM_APPLICATIONFORMTYPE, applicationFormTypes); 
		listItems.put(LIST_ITEM_APPLICATIONMODULE, applicationModules); 
		listItems.put(LIST_ITEM_APPLICATIONENTITY, applicationEntitys); 
		return listItems;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#asListItem()
	 */
	@Override
	public List<ListItemResponse> asListItem() {
		List<ListItemResponse> listItems = new ArrayList<ListItemResponse>();
		for(ApplicationForm applicationform: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(applicationform.getId(), applicationform.getCode(), applicationform.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public ApplicationForm convertRequestToModel(ApplicationFormRequest applicationFormRequest) 
    {
		ApplicationForm applicationForm = new ApplicationForm();
		applicationForm = this.loadDefaultFieldsFromRequest(applicationForm, applicationFormRequest);
    	//Process many to one relationships
    	if (applicationFormRequest.getApplicationFormType() != null)
    	{
    		ApplicationFormType applicationFormType = getEntityManager().find(ApplicationFormType.class, applicationFormRequest.getApplicationFormType());
    		applicationForm.setApplicationFormType(applicationFormType);
    	}
    	if (applicationFormRequest.getApplicationModule() != null)
    	{
    		ApplicationModule applicationModule = getEntityManager().find(ApplicationModule.class, applicationFormRequest.getApplicationModule());
    		applicationForm.setApplicationModule(applicationModule);
    	}
    	if (applicationFormRequest.getApplicationEntity() != null)
    	{
    		ApplicationEntity applicationEntity = getEntityManager().find(ApplicationEntity.class, applicationFormRequest.getApplicationEntity());
    		applicationForm.setApplicationEntity(applicationEntity);
    	}
    	applicationForm.setName(applicationFormRequest.getName()); 
    	applicationForm.setDescription(applicationFormRequest.getDescription()); 
    	applicationForm.setDisplayNm(applicationFormRequest.getDisplayNm()); 
    	applicationForm.setDisplayNmPlural(applicationFormRequest.getDisplayNmPlural()); 
    	applicationForm.setHasTable(applicationFormRequest.getHasTable()); 
    	applicationForm.setDbName(applicationFormRequest.getDbName()); 
    	applicationForm.setCode(applicationFormRequest.getCode()); 
    	applicationForm.setRecSt(applicationFormRequest.getRecSt()); 
		return applicationForm;
	}
	
	@Override
	public ApplicationFormResponse convertModelToResponse(ApplicationForm model) {
		if (model == null) return null;
		ApplicationFormResponse applicationFormResponse = new ApplicationFormResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(model, applicationFormResponse, allowedEntityFields);
		return applicationFormResponse;
	}
}
