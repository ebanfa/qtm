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
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.ws.rs.core.MultivaluedMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.application.model.ApplicationEntityField;

import com.nathanclaire.alantra.messaging.model.Message;
import com.nathanclaire.alantra.messaging.model.MessageType;
import com.nathanclaire.alantra.messaging.model.MessageStatus;
import com.nathanclaire.alantra.messaging.request.MessageRequest;
import com.nathanclaire.alantra.messaging.response.MessageResponse;
import com.nathanclaire.alantra.messaging.service.entity.MessageTypeService;
import com.nathanclaire.alantra.messaging.service.entity.MessageStatusService;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.base.util.PropertyUtils;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class MessageServiceImpl 
	extends BaseEntityServiceImpl<Message, MessageResponse, MessageRequest> 
	implements MessageService
{
	private static final String LIST_ITEM_MESSAGETYPE = "messageType";
	private static final String LIST_ITEM_MESSAGESTATUS = "messageStatus";
	private static final String ENTITY_NAME = "Message";
	private static final String LIST_ACTIVITY_CODE = "LIST_MESSAGING_MESSAGE";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_MESSAGING_MESSAGE";
	
	private Logger logger = LoggerFactory.getLogger(MessageServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	@Inject
	MessageTypeService  messageTypeService;
	@Inject
	MessageStatusService  messageStatusService;
	
	/**
	 * @param entityClass
	 */
	public MessageServiceImpl() {
		super(Message.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.Message#findById(java.lang.Integer)
	 */
	@Override
	public Message findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.Message#findByCode(java.lang.String)
	 */
	@Override
	public Message findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.Message#findByName(java.lang.String)
	 */
	@Override
	public Message findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.Message#findAll(java.util.Map)
	 */
	@Override
	public List<Message> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.Message#createMessage(com.nathanclaire.alantra.messaging.rest.request.ServiceRequest)
	 */
	@Override
	public Message create(MessageRequest messageRequest) {
		return createInstance(messageRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.Message#deleteMessage(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.Message#updateMessage(com.nathanclaire.alantra.messaging.rest.request.ServiceRequest)
	 */
	@Override
	public Message update(MessageRequest messageRequest) {
		return updateInstance(messageRequest);
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#getListActivityCode()
	 */
	@Override
	public String getListActivityCode() {
		return LIST_ACTIVITY_CODE;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#getEditActivityCode()
	 */
	@Override
	public String getEditActivityCode() {
		return EDIT_ACTIVITY_CODE;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#getEntityName()
	 */
	@Override
	public String getEntityName() {
		return ENTITY_NAME;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#getEntityFields()
	 */
	@Override
	public List<ApplicationEntityField> getEntityFields() {
		return applicationEntityService.getFieldsForEntity(ENTITY_NAME);
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#relatedEntitesToListItems()
	 */
	@Override
	public Map<String, List<ListItemResponse>> relatedEntitesToListItems() 
	{
		Map<String, List<ListItemResponse>> listItems = new HashMap<String, List<ListItemResponse>>(); 
		List<ListItemResponse> messageTypes = messageTypeService.asListItem();
		List<ListItemResponse> messageStatuss = messageStatusService.asListItem();
    	
		listItems.put(LIST_ITEM_MESSAGETYPE, messageTypes); 
		listItems.put(LIST_ITEM_MESSAGESTATUS, messageStatuss); 
		return listItems;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#asListItem()
	 */
	@Override
	public List<ListItemResponse> asListItem() {
		List<ListItemResponse> listItems = new ArrayList<ListItemResponse>();
		queryParameters.clear();
		for(Message message: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(message.getId(), message.getCode(), message.getCode());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public Message convertRequestToModel(MessageRequest messageRequest) 
    {
		Message message = new Message();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(messageRequest, message, allowedEntityFields);
    	//Process many to one relationships
    	if (messageRequest.getMessageTypeId() != null)
    	{
    		MessageType messageType = getEntityManager().find(MessageType.class, messageRequest.getMessageTypeId());
    		message.setMessageType(messageType);
    	}
    	if (messageRequest.getMessageStatusId() != null)
    	{
    		MessageStatus messageStatus = getEntityManager().find(MessageStatus.class, messageRequest.getMessageStatusId());
    		message.setMessageStatus(messageStatus);
    	}
		return message;
	}
	
	@Override
	public MessageResponse convertModelToResponse(Message model) {
		if (model == null) return null;
		MessageResponse messageResponse = new MessageResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(model, messageResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		if(model.getMessageType() != null)
			messageResponse.setMessageTypeId(model.getMessageType().getId());
		if(model.getMessageStatus() != null)
			messageResponse.setMessageStatusId(model.getMessageStatus().getId());
		return messageResponse;
	}
}
