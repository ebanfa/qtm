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

import com.nathanclaire.alantra.security.model.SystemUserMessage;
import com.nathanclaire.alantra.messaging.model.Message;
import com.nathanclaire.alantra.security.model.SystemUser;
import com.nathanclaire.alantra.security.request.SystemUserMessageRequest;
import com.nathanclaire.alantra.security.response.SystemUserMessageResponse;
import com.nathanclaire.alantra.messaging.service.entity.MessageService;
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
public class SystemUserMessageServiceImpl 
	extends BaseEntityServiceImpl<SystemUserMessage, SystemUserMessageResponse, SystemUserMessageRequest> 
	implements SystemUserMessageService
{
	private static final String LIST_ITEM_MESSAGE = "message";
	private static final String LIST_ITEM_SYSTEMUSER = "systemUser";
	private static final String ENTITY_NAME = "SystemUserMessage";
	private static final String LIST_ACTIVITY_CODE = "LIST_SECURITY_SYSTEMUSERMESSAGE";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_SECURITY_SYSTEMUSERMESSAGE";
	
	private Logger logger = LoggerFactory.getLogger(SystemUserMessageServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	@Inject
	MessageService  messageService;
	@Inject
	SystemUserService  systemUserService;
	
	/**
	 * @param entityClass
	 */
	public SystemUserMessageServiceImpl() {
		super(SystemUserMessage.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.security.service.SystemUserMessage#findById(java.lang.Integer)
	 */
	@Override
	public SystemUserMessage findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.security.service.SystemUserMessage#findByCode(java.lang.String)
	 */
	@Override
	public SystemUserMessage findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.security.service.SystemUserMessage#findByName(java.lang.String)
	 */
	@Override
	public SystemUserMessage findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.security.service.SystemUserMessage#findAll(java.util.Map)
	 */
	@Override
	public List<SystemUserMessage> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.security.service.SystemUserMessage#createSystemUserMessage(com.nathanclaire.alantra.security.rest.request.ServiceRequest)
	 */
	@Override
	public SystemUserMessage create(SystemUserMessageRequest systemUserMessageRequest) throws ApplicationException {
		return createInstance(systemUserMessageRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.security.service.SystemUserMessage#deleteSystemUserMessage(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.security.service.SystemUserMessage#updateSystemUserMessage(com.nathanclaire.alantra.security.rest.request.ServiceRequest)
	 */
	@Override
	public SystemUserMessage update(SystemUserMessageRequest systemUserMessageRequest) throws ApplicationException {
		return updateInstance(systemUserMessageRequest);
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
		List<ListItemResponse> messages = messageService.asListItem();
		List<ListItemResponse> systemUsers = systemUserService.asListItem();
    	
		listItems.put(LIST_ITEM_MESSAGE, messages); 
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
		for(SystemUserMessage systemusermessage: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(systemusermessage.getId(), systemusermessage.getCode(), systemusermessage.getCode());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public SystemUserMessage convertRequestToModel(SystemUserMessageRequest systemUserMessageRequest) 
     throws ApplicationException {
		SystemUserMessage systemUserMessage = new SystemUserMessage();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(systemUserMessageRequest, systemUserMessage, allowedEntityFields);
    	//Process many to one relationships
    	if (systemUserMessageRequest.getMessageId() != null)
    	{
    		Message message = getEntityManager().find(Message.class, systemUserMessageRequest.getMessageId());
    		systemUserMessage.setMessage(message);
    	}
    	if (systemUserMessageRequest.getSystemUserId() != null)
    	{
    		SystemUser systemUser = getEntityManager().find(SystemUser.class, systemUserMessageRequest.getSystemUserId());
    		systemUserMessage.setSystemUser(systemUser);
    	}
		return systemUserMessage;
	}
	
	@Override
	public SystemUserMessageResponse convertModelToResponse(SystemUserMessage model) throws ApplicationException {
		if (model == null) return null;
		SystemUserMessageResponse systemUserMessageResponse = new SystemUserMessageResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(model, systemUserMessageResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		if(model.getMessage() != null)
			systemUserMessageResponse.setMessageId(model.getMessage().getId());
			systemUserMessageResponse.setMessageText(model.getMessage().getCode());
		if(model.getSystemUser() != null)
			systemUserMessageResponse.setSystemUserId(model.getSystemUser().getId());
			systemUserMessageResponse.setSystemUserText(model.getSystemUser().getName());
		return systemUserMessageResponse;
	}
}
