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

import com.nathanclaire.alantra.notification.model.NotificationType;
import com.nathanclaire.alantra.notification.model.Template;
import com.nathanclaire.alantra.notification.model.NotificationCategory;
import com.nathanclaire.alantra.notification.request.NotificationTypeRequest;
import com.nathanclaire.alantra.notification.response.NotificationTypeResponse;
import com.nathanclaire.alantra.notification.service.entity.TemplateService;
import com.nathanclaire.alantra.notification.service.entity.NotificationCategoryService;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtils;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class NotificationTypeServiceImpl 
	extends BaseEntityServiceImpl<NotificationType, NotificationTypeResponse, NotificationTypeRequest> 
	implements NotificationTypeService
{
	private static final String LIST_ITEM_TEMPLATE = "template";
	private static final String LIST_ITEM_NOTIFICATIONCATEGORY = "notificationCategory";
	private static final String ENTITY_NAME = "NotificationType";
	private static final String LIST_ACTIVITY_CODE = "LIST_NOTIFICATION_NOTIFICATIONTYPE";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_NOTIFICATION_NOTIFICATIONTYPE";
	
	private Logger logger = LoggerFactory.getLogger(NotificationTypeServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	@Inject
	TemplateService  templateService;
	@Inject
	NotificationCategoryService  notificationCategoryService;
	
	/**
	 * @param entityClass
	 */
	public NotificationTypeServiceImpl() {
		super(NotificationType.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.notification.service.NotificationType#findById(java.lang.Integer)
	 */
	@Override
	public NotificationType findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.notification.service.NotificationType#findByCode(java.lang.String)
	 */
	@Override
	public NotificationType findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.notification.service.NotificationType#findByName(java.lang.String)
	 */
	@Override
	public NotificationType findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.notification.service.NotificationType#findAll(java.util.Map)
	 */
	@Override
	public List<NotificationType> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.notification.service.NotificationType#createNotificationType(com.nathanclaire.alantra.notification.rest.request.ServiceRequest)
	 */
	@Override
	public NotificationType create(NotificationTypeRequest notificationTypeRequest) throws ApplicationException {
		return createInstance(notificationTypeRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.notification.service.NotificationType#deleteNotificationType(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.notification.service.NotificationType#updateNotificationType(com.nathanclaire.alantra.notification.rest.request.ServiceRequest)
	 */
	@Override
	public NotificationType update(NotificationTypeRequest notificationTypeRequest) throws ApplicationException {
		return updateInstance(notificationTypeRequest);
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
		List<ListItemResponse> templates = templateService.asListItem();
		List<ListItemResponse> notificationCategorys = notificationCategoryService.asListItem();
    	
		listItems.put(LIST_ITEM_TEMPLATE, templates); 
		listItems.put(LIST_ITEM_NOTIFICATIONCATEGORY, notificationCategorys); 
		return listItems;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#asListItem()
	 */
	@Override
	public List<ListItemResponse> asListItem() throws ApplicationException {
		List<ListItemResponse> listItems = new ArrayList<ListItemResponse>();
		queryParameters.clear();
		for(NotificationType notificationtype: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(notificationtype.getId(), notificationtype.getCode(), notificationtype.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public NotificationType convertRequestToModel(NotificationTypeRequest notificationTypeRequest) 
     throws ApplicationException {
		NotificationType notificationType = new NotificationType();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(notificationTypeRequest, notificationType, allowedEntityFields);
    	//Process many to one relationships
    	if (notificationTypeRequest.getTemplateId() != null)
    	{
    		Template template = getEntityManager().find(Template.class, notificationTypeRequest.getTemplateId());
    		notificationType.setTemplate(template);
    	}
    	if (notificationTypeRequest.getNotificationCategoryId() != null)
    	{
    		NotificationCategory notificationCategory = getEntityManager().find(NotificationCategory.class, notificationTypeRequest.getNotificationCategoryId());
    		notificationType.setNotificationCategory(notificationCategory);
    	}
		return notificationType;
	}
	
	@Override
	public NotificationTypeResponse convertModelToResponse(NotificationType model) throws ApplicationException {
		if (model == null) return null;
		NotificationTypeResponse notificationTypeResponse = new NotificationTypeResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(model, notificationTypeResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		if(model.getTemplate() != null)
			notificationTypeResponse.setTemplateId(model.getTemplate().getId());
			notificationTypeResponse.setTemplateText(model.getTemplate().getName());
		if(model.getNotificationCategory() != null)
			notificationTypeResponse.setNotificationCategoryId(model.getNotificationCategory().getId());
			notificationTypeResponse.setNotificationCategoryText(model.getNotificationCategory().getName());
		return notificationTypeResponse;
	}
}
