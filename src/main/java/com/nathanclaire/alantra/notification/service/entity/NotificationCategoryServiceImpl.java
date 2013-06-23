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

import com.nathanclaire.alantra.notification.model.NotificationCategory;
import com.nathanclaire.alantra.notification.request.NotificationCategoryRequest;
import com.nathanclaire.alantra.notification.response.NotificationCategoryResponse;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtils;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class NotificationCategoryServiceImpl 
	extends BaseEntityServiceImpl<NotificationCategory, NotificationCategoryResponse, NotificationCategoryRequest> 
	implements NotificationCategoryService
{
	private static final String ENTITY_NAME = "NotificationCategory";
	private static final String LIST_ACTIVITY_CODE = "LIST_NOTIFICATION_NOTIFICATIONCATEGORY";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_NOTIFICATION_NOTIFICATIONCATEGORY";
	
	private Logger logger = LoggerFactory.getLogger(NotificationCategoryServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	
	/**
	 * @param entityClass
	 */
	public NotificationCategoryServiceImpl() {
		super(NotificationCategory.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.notification.service.NotificationCategory#findById(java.lang.Integer)
	 */
	@Override
	public NotificationCategory findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.notification.service.NotificationCategory#findByCode(java.lang.String)
	 */
	@Override
	public NotificationCategory findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.notification.service.NotificationCategory#findByName(java.lang.String)
	 */
	@Override
	public NotificationCategory findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.notification.service.NotificationCategory#findAll(java.util.Map)
	 */
	@Override
	public List<NotificationCategory> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.notification.service.NotificationCategory#createNotificationCategory(com.nathanclaire.alantra.notification.rest.request.ServiceRequest)
	 */
	@Override
	public NotificationCategory create(NotificationCategoryRequest notificationCategoryRequest) throws ApplicationException {
		return createInstance(notificationCategoryRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.notification.service.NotificationCategory#deleteNotificationCategory(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.notification.service.NotificationCategory#updateNotificationCategory(com.nathanclaire.alantra.notification.rest.request.ServiceRequest)
	 */
	@Override
	public NotificationCategory update(NotificationCategoryRequest notificationCategoryRequest) throws ApplicationException {
		return updateInstance(notificationCategoryRequest);
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
		for(NotificationCategory notificationcategory: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(notificationcategory.getId(), notificationcategory.getCode(), notificationcategory.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public NotificationCategory convertRequestToModel(NotificationCategoryRequest notificationCategoryRequest) 
     throws ApplicationException {
		NotificationCategory notificationCategory = new NotificationCategory();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(notificationCategoryRequest, notificationCategory, allowedEntityFields);
    	//Process many to one relationships
		return notificationCategory;
	}
	
	@Override
	public NotificationCategoryResponse convertModelToResponse(NotificationCategory model) throws ApplicationException {
		if (model == null) return null;
		NotificationCategoryResponse notificationCategoryResponse = new NotificationCategoryResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(model, notificationCategoryResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		return notificationCategoryResponse;
	}
}
