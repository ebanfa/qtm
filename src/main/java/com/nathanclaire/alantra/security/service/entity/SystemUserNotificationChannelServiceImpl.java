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

import com.nathanclaire.alantra.security.model.SystemUserNotificationChannel;
import com.nathanclaire.alantra.datasource.model.DataChannelCategory;
import com.nathanclaire.alantra.security.model.SystemUser;
import com.nathanclaire.alantra.security.request.SystemUserNotificationChannelRequest;
import com.nathanclaire.alantra.security.response.SystemUserNotificationChannelResponse;
import com.nathanclaire.alantra.datasource.service.entity.DataChannelCategoryService;
import com.nathanclaire.alantra.security.service.entity.SystemUserService;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtils;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class SystemUserNotificationChannelServiceImpl 
	extends BaseEntityServiceImpl<SystemUserNotificationChannel, SystemUserNotificationChannelResponse, SystemUserNotificationChannelRequest> 
	implements SystemUserNotificationChannelService
{
	private static final String LIST_ITEM_DATACHANNELCATEGORY = "dataChannelCategory";
	private static final String LIST_ITEM_SYSTEMUSER = "systemUser";
	private static final String ENTITY_NAME = "SystemUserNotificationChannel";
	private static final String LIST_ACTIVITY_CODE = "LIST_SECURITY_SYSTEMUSERNOTIFICATIONCHANNEL";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_SECURITY_SYSTEMUSERNOTIFICATIONCHANNEL";
	
	private Logger logger = LoggerFactory.getLogger(SystemUserNotificationChannelServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	@Inject
	DataChannelCategoryService  dataChannelCategoryService;
	@Inject
	SystemUserService  systemUserService;
	
	/**
	 * @param entityClass
	 */
	public SystemUserNotificationChannelServiceImpl() {
		super(SystemUserNotificationChannel.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.security.service.SystemUserNotificationChannel#findById(java.lang.Integer)
	 */
	@Override
	public SystemUserNotificationChannel findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.security.service.SystemUserNotificationChannel#findByCode(java.lang.String)
	 */
	@Override
	public SystemUserNotificationChannel findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.security.service.SystemUserNotificationChannel#findByName(java.lang.String)
	 */
	@Override
	public SystemUserNotificationChannel findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.security.service.SystemUserNotificationChannel#findAll(java.util.Map)
	 */
	@Override
	public List<SystemUserNotificationChannel> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.security.service.SystemUserNotificationChannel#createSystemUserNotificationChannel(com.nathanclaire.alantra.security.rest.request.ServiceRequest)
	 */
	@Override
	public SystemUserNotificationChannel create(SystemUserNotificationChannelRequest systemUserNotificationChannelRequest) throws ApplicationException {
		return createInstance(systemUserNotificationChannelRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.security.service.SystemUserNotificationChannel#deleteSystemUserNotificationChannel(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.security.service.SystemUserNotificationChannel#updateSystemUserNotificationChannel(com.nathanclaire.alantra.security.rest.request.ServiceRequest)
	 */
	@Override
	public SystemUserNotificationChannel update(SystemUserNotificationChannelRequest systemUserNotificationChannelRequest) throws ApplicationException {
		return updateInstance(systemUserNotificationChannelRequest);
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
		List<ListItemResponse> dataChannelCategorys = dataChannelCategoryService.asListItem();
		List<ListItemResponse> systemUsers = systemUserService.asListItem();
    	
		listItems.put(LIST_ITEM_DATACHANNELCATEGORY, dataChannelCategorys); 
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
		for(SystemUserNotificationChannel systemusernotificationchannel: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(systemusernotificationchannel.getId(), systemusernotificationchannel.getCode(), systemusernotificationchannel.getCode());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public SystemUserNotificationChannel convertRequestToModel(SystemUserNotificationChannelRequest systemUserNotificationChannelRequest) 
     throws ApplicationException {
		SystemUserNotificationChannel systemUserNotificationChannel = new SystemUserNotificationChannel();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(systemUserNotificationChannelRequest, systemUserNotificationChannel, allowedEntityFields);
    	//Process many to one relationships
    	if (systemUserNotificationChannelRequest.getDataChannelCategoryId() != null)
    	{
    		DataChannelCategory dataChannelCategory = getEntityManager().find(DataChannelCategory.class, systemUserNotificationChannelRequest.getDataChannelCategoryId());
    		systemUserNotificationChannel.setDataChannelCategory(dataChannelCategory);
    	}
    	if (systemUserNotificationChannelRequest.getSystemUserId() != null)
    	{
    		SystemUser systemUser = getEntityManager().find(SystemUser.class, systemUserNotificationChannelRequest.getSystemUserId());
    		systemUserNotificationChannel.setSystemUser(systemUser);
    	}
		return systemUserNotificationChannel;
	}
	
	@Override
	public SystemUserNotificationChannelResponse convertModelToResponse(SystemUserNotificationChannel model) throws ApplicationException {
		if (model == null) return null;
		SystemUserNotificationChannelResponse systemUserNotificationChannelResponse = new SystemUserNotificationChannelResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(model, systemUserNotificationChannelResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		if(model.getDataChannelCategory() != null)
			systemUserNotificationChannelResponse.setDataChannelCategoryId(model.getDataChannelCategory().getId());
			systemUserNotificationChannelResponse.setDataChannelCategoryText(model.getDataChannelCategory().getName());
		if(model.getSystemUser() != null)
			systemUserNotificationChannelResponse.setSystemUserId(model.getSystemUser().getId());
			systemUserNotificationChannelResponse.setSystemUserText(model.getSystemUser().getName());
		return systemUserNotificationChannelResponse;
	}
}
