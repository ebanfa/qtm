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

import com.nathanclaire.alantra.messaging.model.MessageAction;
import com.nathanclaire.alantra.messaging.model.MessageApplicationAction;
import com.nathanclaire.alantra.messaging.model.Message;
import com.nathanclaire.alantra.messaging.request.MessageActionRequest;
import com.nathanclaire.alantra.messaging.response.MessageActionResponse;
import com.nathanclaire.alantra.messaging.service.entity.MessageApplicationActionService;
import com.nathanclaire.alantra.messaging.service.entity.MessageService;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtils;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class MessageActionServiceImpl 
	extends BaseEntityServiceImpl<MessageAction, MessageActionResponse, MessageActionRequest> 
	implements MessageActionService
{
	private static final String LIST_ITEM_MESSAGEAPPLICATIONACTION = "messageApplicationAction";
	private static final String LIST_ITEM_MESSAGE = "message";
	private static final String ENTITY_NAME = "MessageAction";
	private static final String LIST_ACTIVITY_CODE = "LIST_MESSAGING_MESSAGEACTION";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_MESSAGING_MESSAGEACTION";
	
	private Logger logger = LoggerFactory.getLogger(MessageActionServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	@Inject
	MessageApplicationActionService  messageApplicationActionService;
	@Inject
	MessageService  messageService;
	
	/**
	 * @param entityClass
	 */
	public MessageActionServiceImpl() {
		super(MessageAction.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.MessageAction#findById(java.lang.Integer)
	 */
	@Override
	public MessageAction findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.MessageAction#findByCode(java.lang.String)
	 */
	@Override
	public MessageAction findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.MessageAction#findByName(java.lang.String)
	 */
	@Override
	public MessageAction findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.MessageAction#findAll(java.util.Map)
	 */
	@Override
	public List<MessageAction> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.MessageAction#createMessageAction(com.nathanclaire.alantra.messaging.rest.request.ServiceRequest)
	 */
	@Override
	public MessageAction create(MessageActionRequest messageActionRequest) throws ApplicationException {
		return createInstance(messageActionRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.MessageAction#deleteMessageAction(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.MessageAction#updateMessageAction(com.nathanclaire.alantra.messaging.rest.request.ServiceRequest)
	 */
	@Override
	public MessageAction update(MessageActionRequest messageActionRequest) throws ApplicationException {
		return updateInstance(messageActionRequest);
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
		List<ListItemResponse> messages = messageService.asListItem();
    	
		listItems.put(LIST_ITEM_MESSAGEAPPLICATIONACTION, messageApplicationActions); 
		listItems.put(LIST_ITEM_MESSAGE, messages); 
		return listItems;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#asListItem()
	 */
	@Override
	public List<ListItemResponse> asListItem() throws ApplicationException {
		List<ListItemResponse> listItems = new ArrayList<ListItemResponse>();
		queryParameters.clear();
		for(MessageAction messageaction: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(messageaction.getId(), messageaction.getCode(), messageaction.getCode());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public MessageAction convertRequestToModel(MessageActionRequest messageActionRequest) 
     throws ApplicationException {
		MessageAction messageAction = new MessageAction();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(messageActionRequest, messageAction, allowedEntityFields);
    	//Process many to one relationships
    	if (messageActionRequest.getMessageApplicationActionId() != null)
    	{
    		MessageApplicationAction messageApplicationAction = getEntityManager().find(MessageApplicationAction.class, messageActionRequest.getMessageApplicationActionId());
    		messageAction.setMessageApplicationAction(messageApplicationAction);
    	}
    	if (messageActionRequest.getMessageId() != null)
    	{
    		Message message = getEntityManager().find(Message.class, messageActionRequest.getMessageId());
    		messageAction.setMessage(message);
    	}
		return messageAction;
	}
	
	@Override
	public MessageActionResponse convertModelToResponse(MessageAction model) throws ApplicationException {
		if (model == null) return null;
		MessageActionResponse messageActionResponse = new MessageActionResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(model, messageActionResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		if(model.getMessageApplicationAction() != null)
			messageActionResponse.setMessageApplicationActionId(model.getMessageApplicationAction().getId());
			messageActionResponse.setMessageApplicationActionText(model.getMessageApplicationAction().getName());
		if(model.getMessage() != null)
			messageActionResponse.setMessageId(model.getMessage().getId());
			messageActionResponse.setMessageText(model.getMessage().getCode());
		return messageActionResponse;
	}
}
