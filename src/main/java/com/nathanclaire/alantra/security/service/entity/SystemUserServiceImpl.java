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

import com.nathanclaire.alantra.security.model.SystemUser;
import com.nathanclaire.alantra.security.model.SystemGroup;
import com.nathanclaire.alantra.security.request.SystemUserRequest;
import com.nathanclaire.alantra.security.response.SystemUserResponse;
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
public class SystemUserServiceImpl 
	extends BaseEntityServiceImpl<SystemUser, SystemUserResponse, SystemUserRequest> 
	implements SystemUserService
{
	private static final String LIST_ITEM_SYSTEMGROUP = "systemGroup";
	private static final String ENTITY_NAME = "SystemUser";
	private static final String LIST_ACTIVITY_CODE = "LIST_SECURITY_SYSTEMUSER";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_SECURITY_SYSTEMUSER";
	
	private Logger logger = LoggerFactory.getLogger(SystemUserServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	@Inject
	SystemGroupService  systemGroupService;
	
	/**
	 * @param entityClass
	 */
	public SystemUserServiceImpl() {
		super(SystemUser.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.security.service.SystemUser#findById(java.lang.Integer)
	 */
	@Override
	public SystemUser findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.security.service.SystemUser#findByCode(java.lang.String)
	 */
	@Override
	public SystemUser findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.security.service.SystemUser#findByName(java.lang.String)
	 */
	@Override
	public SystemUser findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.security.service.SystemUser#findAll(java.util.Map)
	 */
	@Override
	public List<SystemUser> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.security.service.SystemUser#createSystemUser(com.nathanclaire.alantra.security.rest.request.ServiceRequest)
	 */
	@Override
	public SystemUser create(SystemUserRequest systemUserRequest) throws ApplicationException {
		return createInstance(systemUserRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.security.service.SystemUser#deleteSystemUser(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.security.service.SystemUser#updateSystemUser(com.nathanclaire.alantra.security.rest.request.ServiceRequest)
	 */
	@Override
	public SystemUser update(SystemUserRequest systemUserRequest) throws ApplicationException {
		return updateInstance(systemUserRequest);
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
		List<ListItemResponse> systemGroups = systemGroupService.asListItem();
    	
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
		for(SystemUser systemuser: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(systemuser.getId(), systemuser.getCode(), systemuser.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public SystemUser convertRequestToModel(SystemUserRequest systemUserRequest) 
     throws ApplicationException {
		SystemUser systemUser = new SystemUser();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(systemUserRequest, systemUser, allowedEntityFields);
    	//Process many to one relationships
    	if (systemUserRequest.getSystemGroupId() != null)
    	{
    		SystemGroup systemGroup = getEntityManager().find(SystemGroup.class, systemUserRequest.getSystemGroupId());
    		systemUser.setSystemGroup(systemGroup);
    	}
		return systemUser;
	}
	
	@Override
	public SystemUserResponse convertModelToResponse(SystemUser model) throws ApplicationException {
		if (model == null) return null;
		SystemUserResponse systemUserResponse = new SystemUserResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(model, systemUserResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		if(model.getSystemGroup() != null)
			systemUserResponse.setSystemGroupId(model.getSystemGroup().getId());
			systemUserResponse.setSystemGroupText(model.getSystemGroup().getName());
		return systemUserResponse;
	}
}
