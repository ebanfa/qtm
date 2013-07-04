/**
 * 
 */
package com.nathanclaire.alantra.messaging.service.entity;

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

import com.nathanclaire.alantra.messaging.model.MessageApplicationNotificationMap;
import com.nathanclaire.alantra.messaging.model.MessageApplicationAction;
import com.nathanclaire.alantra.messaging.request.MessageApplicationNotificationMapRequest;
import com.nathanclaire.alantra.messaging.response.MessageApplicationNotificationMapResponse;
import com.nathanclaire.alantra.messaging.service.entity.MessageApplicationActionService;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtils;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class MessageApplicationNotificationMapServiceImpl 
	extends BaseEntityServiceImpl<MessageApplicationNotificationMap, MessageApplicationNotificationMapResponse, MessageApplicationNotificationMapRequest> 
	implements MessageApplicationNotificationMapService
{
	private static final String LIST_ITEM_MESSAGEAPPLICATIONACTION = "messageApplicationAction";
	private static final String ENTITY_NAME = "MessageApplicationNotificationMap";
	private static final String LIST_ACTIVITY_CODE = "LIST_MESSAGING_MESSAGEAPPLICATIONNOTIFICATIONMAP";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_MESSAGING_MESSAGEAPPLICATIONNOTIFICATIONMAP";
	
	private Logger logger = LoggerFactory.getLogger(MessageApplicationNotificationMapServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	@Inject
	MessageApplicationActionService  messageApplicationActionService;
	
	/**
	 * @param entityClass
	 */
	public MessageApplicationNotificationMapServiceImpl() {
		super(MessageApplicationNotificationMap.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.MessageApplicationNotificationMap#findById(java.lang.Integer)
	 */
	@Override
	public MessageApplicationNotificationMap findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.MessageApplicationNotificationMap#findByCode(java.lang.String)
	 */
	@Override
	public MessageApplicationNotificationMap findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.MessageApplicationNotificationMap#findByName(java.lang.String)
	 */
	@Override
	public MessageApplicationNotificationMap findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.MessageApplicationNotificationMap#findAll(java.util.Map)
	 */
	@Override
	public List<MessageApplicationNotificationMap> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.MessageApplicationNotificationMap#createMessageApplicationNotificationMap(com.nathanclaire.alantra.messaging.rest.request.ServiceRequest)
	 */
	@Override
	public MessageApplicationNotificationMap create(MessageApplicationNotificationMapRequest messageApplicationNotificationMapRequest) throws ApplicationException {
		return createInstance(messageApplicationNotificationMapRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.MessageApplicationNotificationMap#deleteMessageApplicationNotificationMap(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.MessageApplicationNotificationMap#updateMessageApplicationNotificationMap(com.nathanclaire.alantra.messaging.rest.request.ServiceRequest)
	 */
	@Override
	public MessageApplicationNotificationMap update(MessageApplicationNotificationMapRequest messageApplicationNotificationMapRequest) throws ApplicationException {
		return updateInstance(messageApplicationNotificationMapRequest);
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
		List<ListItemResponse> messageApplicationActions = messageApplicationActionService.asListItem();
    	
		listItems.put(LIST_ITEM_MESSAGEAPPLICATIONACTION, messageApplicationActions); 
		return listItems;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#asListItem()
	 */
	@Override
	public List<ListItemResponse> asListItem() throws ApplicationException {
		List<ListItemResponse> listItems = new ArrayList<ListItemResponse>();
		queryParameters.clear();
		for(MessageApplicationNotificationMap messageapplicationnotificationmap: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(messageapplicationnotificationmap.getId(), messageapplicationnotificationmap.getCode(), messageapplicationnotificationmap.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public MessageApplicationNotificationMap convertRequestToModel(MessageApplicationNotificationMapRequest messageApplicationNotificationMapRequest) 
     throws ApplicationException {
		MessageApplicationNotificationMap messageApplicationNotificationMap = new MessageApplicationNotificationMap();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(messageApplicationNotificationMapRequest, messageApplicationNotificationMap, allowedEntityFields);
    	//Process many to one relationships
    	if (messageApplicationNotificationMapRequest.getMessageApplicationActionId() != null)
    	{
    		MessageApplicationAction messageApplicationAction = getEntityManager().find(MessageApplicationAction.class, messageApplicationNotificationMapRequest.getMessageApplicationActionId());
    		messageApplicationNotificationMap.setMessageApplicationAction(messageApplicationAction);
    	}
		return messageApplicationNotificationMap;
	}
	
	@Override
	public MessageApplicationNotificationMapResponse convertModelToResponse(MessageApplicationNotificationMap model) throws ApplicationException {
		if (model == null) return null;
		MessageApplicationNotificationMapResponse messageApplicationNotificationMapResponse = new MessageApplicationNotificationMapResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(model, messageApplicationNotificationMapResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		if(model.getMessageApplicationAction() != null)
			messageApplicationNotificationMapResponse.setMessageApplicationActionId(model.getMessageApplicationAction().getId());
			messageApplicationNotificationMapResponse.setMessageApplicationActionText(model.getMessageApplicationAction().getName());
		return messageApplicationNotificationMapResponse;
	}
}
