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

import com.nathanclaire.alantra.security.model.SystemGroup;
import com.nathanclaire.alantra.security.request.SystemGroupRequest;
import com.nathanclaire.alantra.security.response.SystemGroupResponse;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtil;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class SystemGroupServiceImpl 
	extends BaseEntityServiceImpl<SystemGroup, SystemGroupResponse, SystemGroupRequest> 
	implements SystemGroupService
{
	private static final String ENTITY_NAME = "SystemGroup";
	private static final String LIST_ACTIVITY_CODE = "LIST_SECURITY_SYSTEMGROUP";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_SECURITY_SYSTEMGROUP";
	
	private Logger logger = LoggerFactory.getLogger(SystemGroupServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	
	/**
	 * @param entityClass
	 */
	public SystemGroupServiceImpl() {
		super(SystemGroup.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.security.service.SystemGroup#findById(java.lang.Integer)
	 */
	@Override
	public SystemGroup findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.security.service.SystemGroup#findByCode(java.lang.String)
	 */
	@Override
	public SystemGroup findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.security.service.SystemGroup#findByName(java.lang.String)
	 */
	@Override
	public SystemGroup findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.security.service.SystemGroup#findAll(java.util.Map)
	 */
	@Override
	public List<SystemGroup> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.security.service.SystemGroup#createSystemGroup(com.nathanclaire.alantra.security.rest.request.ServiceRequest)
	 */
	@Override
	public SystemGroup create(SystemGroupRequest systemGroupRequest) throws ApplicationException {
		return createInstance(systemGroupRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.security.service.SystemGroup#deleteSystemGroup(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.security.service.SystemGroup#updateSystemGroup(com.nathanclaire.alantra.security.rest.request.ServiceRequest)
	 */
	@Override
	public SystemGroup update(SystemGroupRequest systemGroupRequest) throws ApplicationException {
		return updateInstance(systemGroupRequest);
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
		for(SystemGroup systemgroup: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(systemgroup.getId(), systemgroup.getCode(), systemgroup.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public SystemGroup convertRequestToModel(SystemGroupRequest systemGroupRequest) 
     throws ApplicationException {
		SystemGroup systemGroup = new SystemGroup();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtil.copyProperties(systemGroupRequest, systemGroup, allowedEntityFields);
    	//Process many to one relationships
		return systemGroup;
	}
	
	@Override
	public SystemGroupResponse convertModelToResponse(SystemGroup model) throws ApplicationException {
		if (model == null) return null;
		SystemGroupResponse systemGroupResponse = new SystemGroupResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtil.copyProperties(model, systemGroupResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		return systemGroupResponse;
	}
}
