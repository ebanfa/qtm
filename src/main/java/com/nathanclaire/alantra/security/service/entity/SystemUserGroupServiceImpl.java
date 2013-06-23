/**
 * 
 */
package com.nathanclaire.alantra.security.service.entity;

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

import com.nathanclaire.alantra.security.model.SystemUserGroup;
import com.nathanclaire.alantra.security.model.SystemUser;
import com.nathanclaire.alantra.security.model.SystemGroup;
import com.nathanclaire.alantra.security.request.SystemUserGroupRequest;
import com.nathanclaire.alantra.security.response.SystemUserGroupResponse;
import com.nathanclaire.alantra.security.service.entity.SystemUserService;
import com.nathanclaire.alantra.security.service.entity.SystemGroupService;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtils;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class SystemUserGroupServiceImpl 
	extends BaseEntityServiceImpl<SystemUserGroup, SystemUserGroupResponse, SystemUserGroupRequest> 
	implements SystemUserGroupService
{
	private static final String LIST_ITEM_SYSTEMUSER = "systemUser";
	private static final String LIST_ITEM_SYSTEMGROUP = "systemGroup";
	private static final String ENTITY_NAME = "SystemUserGroup";
	private static final String LIST_ACTIVITY_CODE = "LIST_SECURITY_SYSTEMUSERGROUP";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_SECURITY_SYSTEMUSERGROUP";
	
	private Logger logger = LoggerFactory.getLogger(SystemUserGroupServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	@Inject
	SystemUserService  systemUserService;
	@Inject
	SystemGroupService  systemGroupService;
	
	/**
	 * @param entityClass
	 */
	public SystemUserGroupServiceImpl() {
		super(SystemUserGroup.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.security.service.SystemUserGroup#findById(java.lang.Integer)
	 */
	@Override
	public SystemUserGroup findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.security.service.SystemUserGroup#findByCode(java.lang.String)
	 */
	@Override
	public SystemUserGroup findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.security.service.SystemUserGroup#findByName(java.lang.String)
	 */
	@Override
	public SystemUserGroup findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.security.service.SystemUserGroup#findAll(java.util.Map)
	 */
	@Override
	public List<SystemUserGroup> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.security.service.SystemUserGroup#createSystemUserGroup(com.nathanclaire.alantra.security.rest.request.ServiceRequest)
	 */
	@Override
	public SystemUserGroup create(SystemUserGroupRequest systemUserGroupRequest) throws ApplicationException {
		return createInstance(systemUserGroupRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.security.service.SystemUserGroup#deleteSystemUserGroup(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.security.service.SystemUserGroup#updateSystemUserGroup(com.nathanclaire.alantra.security.rest.request.ServiceRequest)
	 */
	@Override
	public SystemUserGroup update(SystemUserGroupRequest systemUserGroupRequest) throws ApplicationException {
		return updateInstance(systemUserGroupRequest);
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
		List<ListItemResponse> systemUsers = systemUserService.asListItem();
		List<ListItemResponse> systemGroups = systemGroupService.asListItem();
    	
		listItems.put(LIST_ITEM_SYSTEMUSER, systemUsers); 
		listItems.put(LIST_ITEM_SYSTEMGROUP, systemGroups); 
		return listItems;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#asListItem()
	 */
	@Override
	public List<ListItemResponse> asListItem() throws ApplicationException {
		List<ListItemResponse> listItems = new ArrayList<ListItemResponse>();
		queryParameters.clear();
		for(SystemUserGroup systemusergroup: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(systemusergroup.getId(), systemusergroup.getCode(), systemusergroup.getCode());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public SystemUserGroup convertRequestToModel(SystemUserGroupRequest systemUserGroupRequest) 
     throws ApplicationException {
		SystemUserGroup systemUserGroup = new SystemUserGroup();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(systemUserGroupRequest, systemUserGroup, allowedEntityFields);
    	//Process many to one relationships
    	if (systemUserGroupRequest.getSystemUserId() != null)
    	{
    		SystemUser systemUser = getEntityManager().find(SystemUser.class, systemUserGroupRequest.getSystemUserId());
    		systemUserGroup.setSystemUser(systemUser);
    	}
    	if (systemUserGroupRequest.getSystemGroupId() != null)
    	{
    		SystemGroup systemGroup = getEntityManager().find(SystemGroup.class, systemUserGroupRequest.getSystemGroupId());
    		systemUserGroup.setSystemGroup(systemGroup);
    	}
		return systemUserGroup;
	}
	
	@Override
	public SystemUserGroupResponse convertModelToResponse(SystemUserGroup model) throws ApplicationException {
		if (model == null) return null;
		SystemUserGroupResponse systemUserGroupResponse = new SystemUserGroupResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(model, systemUserGroupResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		if(model.getSystemUser() != null)
			systemUserGroupResponse.setSystemUserId(model.getSystemUser().getId());
			systemUserGroupResponse.setSystemUserText(model.getSystemUser().getName());
		if(model.getSystemGroup() != null)
			systemUserGroupResponse.setSystemGroupId(model.getSystemGroup().getId());
			systemUserGroupResponse.setSystemGroupText(model.getSystemGroup().getName());
		return systemUserGroupResponse;
	}
}
