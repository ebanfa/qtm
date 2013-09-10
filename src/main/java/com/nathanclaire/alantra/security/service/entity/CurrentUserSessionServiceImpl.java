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

import com.nathanclaire.alantra.security.model.CurrentUserSession;
import com.nathanclaire.alantra.security.model.SystemUser;
import com.nathanclaire.alantra.security.request.CurrentUserSessionRequest;
import com.nathanclaire.alantra.security.response.CurrentUserSessionResponse;
import com.nathanclaire.alantra.security.service.entity.SystemUserService;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtil;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class CurrentUserSessionServiceImpl 
	extends BaseEntityServiceImpl<CurrentUserSession, CurrentUserSessionResponse, CurrentUserSessionRequest> 
	implements CurrentUserSessionService
{
	private static final String LIST_ITEM_SYSTEMUSER = "systemUser";
	private static final String ENTITY_NAME = "CurrentUserSession";
	private static final String LIST_ACTIVITY_CODE = "LIST_SECURITY_CURRENTUSERSESSION";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_SECURITY_CURRENTUSERSESSION";
	
	private Logger logger = LoggerFactory.getLogger(CurrentUserSessionServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	@Inject
	SystemUserService  systemUserService;
	
	/**
	 * @param entityClass
	 */
	public CurrentUserSessionServiceImpl() {
		super(CurrentUserSession.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.security.service.CurrentUserSession#findById(java.lang.Integer)
	 */
	@Override
	public CurrentUserSession findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.security.service.CurrentUserSession#findByCode(java.lang.String)
	 */
	@Override
	public CurrentUserSession findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.security.service.CurrentUserSession#findByName(java.lang.String)
	 */
	@Override
	public CurrentUserSession findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.security.service.CurrentUserSession#findAll(java.util.Map)
	 */
	@Override
	public List<CurrentUserSession> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.security.service.CurrentUserSession#createCurrentUserSession(com.nathanclaire.alantra.security.rest.request.ServiceRequest)
	 */
	@Override
	public CurrentUserSession create(CurrentUserSessionRequest currentUserSessionRequest) throws ApplicationException {
		return createInstance(currentUserSessionRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.security.service.CurrentUserSession#deleteCurrentUserSession(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.security.service.CurrentUserSession#updateCurrentUserSession(com.nathanclaire.alantra.security.rest.request.ServiceRequest)
	 */
	@Override
	public CurrentUserSession update(CurrentUserSessionRequest currentUserSessionRequest) throws ApplicationException {
		return updateInstance(currentUserSessionRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.security.service.entity.CurrentUserSessionService#getUserSession(com.nathanclaire.alantra.security.model.SystemUser)
	 */
	@Override
	public CurrentUserSession getUserSession(SystemUser user)	throws ApplicationException {
		return null;
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
    	
		listItems.put(LIST_ITEM_SYSTEMUSER, systemUsers); 
		return listItems;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#asListItem()
	 */
	@Override
	public List<ListItemResponse> asListItem() throws ApplicationException {
		List<ListItemResponse> listItems = new ArrayList<ListItemResponse>();
		queryParameters.clear();
		for(CurrentUserSession currentusersession: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(currentusersession.getId(), currentusersession.getCode(), currentusersession.getCode());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public CurrentUserSession convertRequestToModel(CurrentUserSessionRequest currentUserSessionRequest) 
     throws ApplicationException {
		CurrentUserSession currentUserSession = new CurrentUserSession();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtil.copyProperties(currentUserSessionRequest, currentUserSession, allowedEntityFields);
    	//Process many to one relationships
    	if (currentUserSessionRequest.getSystemUserId() != null)
    	{
    		SystemUser systemUser = getEntityManager().find(SystemUser.class, currentUserSessionRequest.getSystemUserId());
    		currentUserSession.setSystemUser(systemUser);
    	}
		return currentUserSession;
	}
	
	@Override
	public CurrentUserSessionResponse convertModelToResponse(CurrentUserSession model) throws ApplicationException {
		if (model == null) return null;
		CurrentUserSessionResponse currentUserSessionResponse = new CurrentUserSessionResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtil.copyProperties(model, currentUserSessionResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		if(model.getSystemUser() != null)
			currentUserSessionResponse.setSystemUserId(model.getSystemUser().getId());
			currentUserSessionResponse.setSystemUserText(model.getSystemUser().getName());
		return currentUserSessionResponse;
	}

}
