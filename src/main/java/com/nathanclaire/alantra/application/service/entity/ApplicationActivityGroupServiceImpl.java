/**
 * 
 */
package com.nathanclaire.alantra.application.service.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.application.model.ApplicationActivity;
import com.nathanclaire.alantra.application.model.ApplicationActivityGroup;
import com.nathanclaire.alantra.application.model.ApplicationActivityGroupType;
import com.nathanclaire.alantra.application.model.ApplicationEntityField;
import com.nathanclaire.alantra.application.model.ApplicationModule;
import com.nathanclaire.alantra.application.request.ApplicationActivityGroupRequest;
import com.nathanclaire.alantra.application.response.ApplicationActivityGroupResponse;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtil;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class ApplicationActivityGroupServiceImpl 
	extends BaseEntityServiceImpl<ApplicationActivityGroup, ApplicationActivityGroupResponse, ApplicationActivityGroupRequest> 
	implements ApplicationActivityGroupService
{
	
	private static final String ENTITY_NAME = "ApplicationActivityGroup";
	private static final String LIST_ACTIVITY_CODE = "LIST_APPLICATIONACTIVITYGROUP";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_APPLICATIONACTIVITYGROUP";
	
	private static final String LIST_ITEM_APPLICATIONACTIVITYGROUP = "applicationActivityGroup";
	private static final String LIST_ITEM_APPLICATIONACTIVITYGROUPTYPE = "applicationActivityGroupType";
	private static final String LIST_ITEM_APPLICATIONMODULE = "applicationModule";
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	@Inject
	ApplicationActivityGroupTypeService  applicationActivityGroupTypeService;
	@Inject
	ApplicationModuleService  applicationModuleService;
	
	/**
	 * @param entityClass
	 */
	public ApplicationActivityGroupServiceImpl() {
		super(ApplicationActivityGroup.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.ApplicationActivityGroup#findById(java.lang.Integer)
	 */
	@Override
	public ApplicationActivityGroup findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.ApplicationActivityGroup#findByCode(java.lang.String)
	 */
	@Override
	public ApplicationActivityGroup findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.ApplicationActivityGroup#findByName(java.lang.String)
	 */
	@Override
	public ApplicationActivityGroup findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.ApplicationActivityGroup#findAll(java.util.Map)
	 */
	@Override
	public List<ApplicationActivityGroup> findAll(MultivaluedMap<String, String> queryParameters)
	 throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.ApplicationActivityGroup#createApplicationActivityGroup(com.nathanclaire.alantra.application.rest.request.ServiceRequest)
	 */
	@Override
	public ApplicationActivityGroup create(ApplicationActivityGroupRequest applicationActivityGroupRequest)
	throws ApplicationException {
		return createInstance(applicationActivityGroupRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.ApplicationActivityGroup#deleteApplicationActivityGroup(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.ApplicationActivityGroup#updateApplicationActivityGroup(com.nathanclaire.alantra.application.rest.request.ServiceRequest)
	 */
	@Override
	public ApplicationActivityGroup update(ApplicationActivityGroupRequest applicationActivityGroupRequest) throws ApplicationException {
		return updateInstance(applicationActivityGroupRequest);
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#getListActivityCode()
	 */
	@Override
	public String getListActivityCode()  throws ApplicationException{
		return LIST_ACTIVITY_CODE;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#getEditActivityCode()
	 */
	@Override
	public String getEditActivityCode()  throws ApplicationException{
		return EDIT_ACTIVITY_CODE;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#getEntityName()
	 */
	@Override
	public String getEntityName()  throws ApplicationException{
		return ENTITY_NAME;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#getEntityFields()
	 */
	@Override
	public List<ApplicationEntityField> getEntityFields()  throws ApplicationException{
		return applicationEntityService.getFieldsForEntity(ENTITY_NAME);
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#relatedEntitesToListItems()
	 */
	@Override
	public Map<String, List<ListItemResponse>> relatedEntitesToListItems() throws ApplicationException 
	{
		Map<String, List<ListItemResponse>> listItems = new HashMap<String, List<ListItemResponse>>(); 
		List<ListItemResponse> applicationActivityGroups = this.asListItem();
		List<ListItemResponse> applicationActivityGroupTypes = applicationActivityGroupTypeService.asListItem();
		List<ListItemResponse> applicationModules = applicationModuleService.asListItem();
    	
		listItems.put(LIST_ITEM_APPLICATIONACTIVITYGROUP, applicationActivityGroups); 
		listItems.put(LIST_ITEM_APPLICATIONACTIVITYGROUPTYPE, applicationActivityGroupTypes); 
		listItems.put(LIST_ITEM_APPLICATIONMODULE, applicationModules); 
		return listItems;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#asListItem()
	 */
	@Override
	public List<ListItemResponse> asListItem()  throws ApplicationException{
		List<ListItemResponse> listItems = new ArrayList<ListItemResponse>();
		for(ApplicationActivityGroup applicationactivitygroup: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(applicationactivitygroup.getId(), applicationactivitygroup.getCode(), applicationactivitygroup.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public ApplicationActivityGroup convertRequestToModel(ApplicationActivityGroupRequest applicationActivityGroupRequest) 
    		 throws ApplicationException {
		ApplicationActivityGroup applicationActivityGroup = new ApplicationActivityGroup();
		applicationActivityGroup = this.loadDefaultFieldsFromRequest(applicationActivityGroup, applicationActivityGroupRequest);
    	//Process many to one relationships
    	if (applicationActivityGroupRequest.getApplicationActivityGroup() != null)
    	{
    		ApplicationActivityGroup parentApplicationActivityGroup = getEntityManager().find(ApplicationActivityGroup.class, applicationActivityGroupRequest.getApplicationActivityGroup());
    		applicationActivityGroup.setApplicationActivityGroup(parentApplicationActivityGroup);
    	}
    	if (applicationActivityGroupRequest.getApplicationActivityGroupType() != null)
    	{
    		ApplicationActivityGroupType applicationActivityGroupType = getEntityManager().find(ApplicationActivityGroupType.class, applicationActivityGroupRequest.getApplicationActivityGroupType());
    		applicationActivityGroup.setApplicationActivityGroupType(applicationActivityGroupType);
    	}
    	if (applicationActivityGroupRequest.getApplicationModule() != null)
    	{
    		ApplicationModule applicationModule = getEntityManager().find(ApplicationModule.class, applicationActivityGroupRequest.getApplicationModule());
    		applicationActivityGroup.setApplicationModule(applicationModule);
    	}
    	applicationActivityGroup.setName(applicationActivityGroupRequest.getName()); 
    	applicationActivityGroup.setDescription(applicationActivityGroupRequest.getDescription()); 
    	applicationActivityGroup.setGrpUrl(applicationActivityGroupRequest.getGrpUrl()); 
    	applicationActivityGroup.setGrpSeq(applicationActivityGroupRequest.getGrpSeq()); 
    	applicationActivityGroup.setDisplayNm(applicationActivityGroupRequest.getDisplayNm()); 
    	applicationActivityGroup.setDisplayImg(applicationActivityGroupRequest.getDisplayImg()); 
    	applicationActivityGroup.setDisplayFg(applicationActivityGroupRequest.getDisplayFg()); 
    	applicationActivityGroup.setIsParent(applicationActivityGroupRequest.getIsParent()); 
    	applicationActivityGroup.setCode(applicationActivityGroupRequest.getCode()); 
    	applicationActivityGroup.setRecSt(applicationActivityGroupRequest.getRecSt()); 
		return applicationActivityGroup;
	}
	
	@Override
	public ApplicationActivityGroupResponse convertModelToResponse(ApplicationActivityGroup model) throws ApplicationException {
		if (model == null) return null;
		ApplicationActivityGroupResponse applicationActivityGroupResponse = new ApplicationActivityGroupResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtil.copyProperties(model, applicationActivityGroupResponse, allowedEntityFields);
		//
		for (ApplicationActivity activity: model.getApplicationActivities())
		{
			model.setGrpUrl(activity.getActivityUrl());
			break;
		}
		return applicationActivityGroupResponse;
	}

	@Override
	public List<ApplicationActivityGroup> findGroupsInModule(Integer moduleId) throws ApplicationException {
		Set<ApplicationActivityGroup> groups = applicationModuleService.findById(moduleId).getApplicationActivityGroups();
		List<ApplicationActivityGroup> activityGroups = new ArrayList<ApplicationActivityGroup>();
		for(ApplicationActivityGroup group:groups)
		{
			activityGroups.add(group);
		}
		return activityGroups;
	}
}
