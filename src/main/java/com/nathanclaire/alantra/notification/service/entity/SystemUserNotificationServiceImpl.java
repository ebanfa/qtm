/**
 * 
 */
package com.nathanclaire.alantra.notification.service.entity;

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

import com.nathanclaire.alantra.notification.model.SystemUserNotification;
import com.nathanclaire.alantra.security.model.SystemUser;
import com.nathanclaire.alantra.notification.model.NotificationType;
import com.nathanclaire.alantra.notification.request.SystemUserNotificationRequest;
import com.nathanclaire.alantra.notification.response.SystemUserNotificationResponse;
import com.nathanclaire.alantra.security.service.entity.SystemUserService;
import com.nathanclaire.alantra.notification.service.entity.NotificationTypeService;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtils;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class SystemUserNotificationServiceImpl 
	extends BaseEntityServiceImpl<SystemUserNotification, SystemUserNotificationResponse, SystemUserNotificationRequest> 
	implements SystemUserNotificationService
{
	private static final String LIST_ITEM_SYSTEMUSER = "systemUser";
	private static final String LIST_ITEM_NOTIFICATIONTYPE = "notificationType";
	private static final String ENTITY_NAME = "SystemUserNotification";
	private static final String LIST_ACTIVITY_CODE = "LIST_NOTIFICATION_SYSTEMUSERNOTIFICATION";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_NOTIFICATION_SYSTEMUSERNOTIFICATION";
	
	private Logger logger = LoggerFactory.getLogger(SystemUserNotificationServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	@Inject
	SystemUserService  systemUserService;
	@Inject
	NotificationTypeService  notificationTypeService;
	
	/**
	 * @param entityClass
	 */
	public SystemUserNotificationServiceImpl() {
		super(SystemUserNotification.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.notification.service.SystemUserNotification#findById(java.lang.Integer)
	 */
	@Override
	public SystemUserNotification findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.notification.service.SystemUserNotification#findByCode(java.lang.String)
	 */
	@Override
	public SystemUserNotification findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.notification.service.SystemUserNotification#findByName(java.lang.String)
	 */
	@Override
	public SystemUserNotification findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.notification.service.SystemUserNotification#findAll(java.util.Map)
	 */
	@Override
	public List<SystemUserNotification> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.notification.service.SystemUserNotification#createSystemUserNotification(com.nathanclaire.alantra.notification.rest.request.ServiceRequest)
	 */
	@Override
	public SystemUserNotification create(SystemUserNotificationRequest systemUserNotificationRequest) throws ApplicationException {
		return createInstance(systemUserNotificationRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.notification.service.SystemUserNotification#deleteSystemUserNotification(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.notification.service.SystemUserNotification#updateSystemUserNotification(com.nathanclaire.alantra.notification.rest.request.ServiceRequest)
	 */
	@Override
	public SystemUserNotification update(SystemUserNotificationRequest systemUserNotificationRequest) throws ApplicationException {
		return updateInstance(systemUserNotificationRequest);
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
		List<ListItemResponse> notificationTypes = notificationTypeService.asListItem();
    	
		listItems.put(LIST_ITEM_SYSTEMUSER, systemUsers); 
		listItems.put(LIST_ITEM_NOTIFICATIONTYPE, notificationTypes); 
		return listItems;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#asListItem()
	 */
	@Override
	public List<ListItemResponse> asListItem() throws ApplicationException {
		List<ListItemResponse> listItems = new ArrayList<ListItemResponse>();
		queryParameters.clear();
		for(SystemUserNotification systemusernotification: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(systemusernotification.getId(), systemusernotification.getCode(), systemusernotification.getCode());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public SystemUserNotification convertRequestToModel(SystemUserNotificationRequest systemUserNotificationRequest) 
     throws ApplicationException {
		SystemUserNotification systemUserNotification = new SystemUserNotification();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(systemUserNotificationRequest, systemUserNotification, allowedEntityFields);
    	//Process many to one relationships
    	if (systemUserNotificationRequest.getSystemUserId() != null)
    	{
    		SystemUser systemUser = getEntityManager().find(SystemUser.class, systemUserNotificationRequest.getSystemUserId());
    		systemUserNotification.setSystemUser(systemUser);
    	}
    	if (systemUserNotificationRequest.getNotificationTypeId() != null)
    	{
    		NotificationType notificationType = getEntityManager().find(NotificationType.class, systemUserNotificationRequest.getNotificationTypeId());
    		systemUserNotification.setNotificationType(notificationType);
    	}
		return systemUserNotification;
	}
	
	@Override
	public SystemUserNotificationResponse convertModelToResponse(SystemUserNotification model) throws ApplicationException {
		if (model == null) return null;
		SystemUserNotificationResponse systemUserNotificationResponse = new SystemUserNotificationResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(model, systemUserNotificationResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		if(model.getSystemUser() != null)
			systemUserNotificationResponse.setSystemUserId(model.getSystemUser().getId());
			systemUserNotificationResponse.setSystemUserText(model.getSystemUser().getName());
		if(model.getNotificationType() != null)
			systemUserNotificationResponse.setNotificationTypeId(model.getNotificationType().getId());
			systemUserNotificationResponse.setNotificationTypeText(model.getNotificationType().getName());
		return systemUserNotificationResponse;
	}
}
