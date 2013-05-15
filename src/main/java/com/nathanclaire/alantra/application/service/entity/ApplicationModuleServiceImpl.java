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

import com.nathanclaire.alantra.application.model.ApplicationEntityField;
import com.nathanclaire.alantra.application.model.ApplicationModule;
import com.nathanclaire.alantra.application.request.ApplicationModuleRequest;
import com.nathanclaire.alantra.application.response.ApplicationModuleResponse;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.base.util.PropertyUtils;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class ApplicationModuleServiceImpl 
	extends BaseEntityServiceImpl<ApplicationModule, ApplicationModuleResponse, ApplicationModuleRequest> 
	implements ApplicationModuleService
{
	
	private static final String ENTITY_NAME = "ApplicationModule";
	private static final String LIST_ACTIVITY_CODE = "LIST_APPLICATIONMODULE";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_APPLICATIONMODULE";
	
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	
	/**
	 * @param entityClass
	 */
	public ApplicationModuleServiceImpl() {
		super(ApplicationModule.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.ApplicationModule#findById(java.lang.Integer)
	 */
	@Override
	public ApplicationModule findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.ApplicationModule#findByCode(java.lang.String)
	 */
	@Override
	public ApplicationModule findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.ApplicationModule#findByName(java.lang.String)
	 */
	@Override
	public ApplicationModule findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.ApplicationModule#findAll(java.util.Map)
	 */
	@Override
	public List<ApplicationModule> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.ApplicationModule#createApplicationModule(com.nathanclaire.alantra.application.rest.request.ServiceRequest)
	 */
	@Override
	public ApplicationModule create(ApplicationModuleRequest applicationModuleRequest) {
		return createInstance(applicationModuleRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.ApplicationModule#deleteApplicationModule(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.ApplicationModule#updateApplicationModule(com.nathanclaire.alantra.application.rest.request.ServiceRequest)
	 */
	@Override
	public ApplicationModule update(ApplicationModuleRequest applicationModuleRequest) {
		return updateInstance(applicationModuleRequest);
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
    	
		return listItems;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#asListItem()
	 */
	@Override
	public List<ListItemResponse> asListItem() {
		List<ListItemResponse> listItems = new ArrayList<ListItemResponse>();
		for(ApplicationModule applicationmodule: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(applicationmodule.getId(), applicationmodule.getCode(), applicationmodule.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public ApplicationModule convertRequestToModel(ApplicationModuleRequest applicationModuleRequest) 
    {
		ApplicationModule applicationModule = new ApplicationModule();
		applicationModule = this.loadDefaultFieldsFromRequest(applicationModule, applicationModuleRequest);
    	//Process many to one relationships
    	applicationModule.setName(applicationModuleRequest.getName()); 
    	applicationModule.setDescription(applicationModuleRequest.getDescription()); 
    	applicationModule.setSequenceNo(applicationModuleRequest.getSequenceNo()); 
    	applicationModule.setDisplayNm(applicationModuleRequest.getDisplayNm()); 
    	applicationModule.setDisplayImg(applicationModuleRequest.getDisplayImg()); 
    	applicationModule.setDisplayFg(applicationModuleRequest.getDisplayFg()); 
    	applicationModule.setCode(applicationModuleRequest.getCode()); 
    	applicationModule.setRecSt(applicationModuleRequest.getRecSt()); 
		return applicationModule;
	}
	
	@Override
	public ApplicationModuleResponse convertModelToResponse(ApplicationModule model) {
		if (model == null) return null;
		ApplicationModuleResponse applicationModuleResponse = new ApplicationModuleResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(model, applicationModuleResponse, allowedEntityFields);
		return applicationModuleResponse;
	}
}
