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

import com.nathanclaire.alantra.messaging.model.Message;
import com.nathanclaire.alantra.messaging.model.MessageClassification;
import com.nathanclaire.alantra.messaging.model.MessageType;
import com.nathanclaire.alantra.messaging.model.MessageStatus;
import com.nathanclaire.alantra.datasource.model.DataChannel;
import com.nathanclaire.alantra.messaging.request.MessageRequest;
import com.nathanclaire.alantra.messaging.response.MessageResponse;
import com.nathanclaire.alantra.messaging.service.entity.MessageClassificationService;
import com.nathanclaire.alantra.messaging.service.entity.MessageTypeService;
import com.nathanclaire.alantra.messaging.service.entity.MessageStatusService;
import com.nathanclaire.alantra.datasource.service.entity.DataChannelService;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.util.ApplicationException;
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
	private static final String LIST_ITEM_MESSAGECLASSIFICATION = "messageClassification";
	private static final String LIST_ITEM_MESSAGETYPE = "messageType";
	private static final String LIST_ITEM_MESSAGESTATUS = "messageStatus";
	private static final String LIST_ITEM_DATACHANNEL = "dataChannel";
	private static final String ENTITY_NAME = "Message";
	private static final String LIST_ACTIVITY_CODE = "LIST_MESSAGING_MESSAGE";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_MESSAGING_MESSAGE";
	
	private Logger logger = LoggerFactory.getLogger(MessageServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	@Inject
	MessageClassificationService  messageClassificationService;
	@Inject
	MessageTypeService  messageTypeService;
	@Inject
	MessageStatusService  messageStatusService;
	@Inject
	DataChannelService  dataChannelService;
	
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
	public Message findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.Message#findByCode(java.lang.String)
	 */
	@Override
	public Message findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.Message#findByName(java.lang.String)
	 */
	@Override
	public Message findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.Message#findAll(java.util.Map)
	 */
	@Override
	public List<Message> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.Message#createMessage(com.nathanclaire.alantra.messaging.rest.request.ServiceRequest)
	 */
	@Override
	public Message create(MessageRequest messageRequest) throws ApplicationException {
		return createInstance(messageRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.Message#deleteMessage(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.Message#updateMessage(com.nathanclaire.alantra.messaging.rest.request.ServiceRequest)
	 */
	@Override
	public Message update(MessageRequest messageRequest) throws ApplicationException {
		return updateInstance(messageRequest);
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
		List<ListItemResponse> messageClassifications = messageClassificationService.asListItem();
		List<ListItemResponse> messageTypes = messageTypeService.asListItem();
		List<ListItemResponse> messageStatuss = messageStatusService.asListItem();
		List<ListItemResponse> dataChannels = dataChannelService.asListItem();
    	
		listItems.put(LIST_ITEM_MESSAGECLASSIFICATION, messageClassifications); 
		listItems.put(LIST_ITEM_MESSAGETYPE, messageTypes); 
		listItems.put(LIST_ITEM_MESSAGESTATUS, messageStatuss); 
		listItems.put(LIST_ITEM_DATACHANNEL, dataChannels); 
		return listItems;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#asListItem()
	 */
	@Override
	public List<ListItemResponse> asListItem() throws ApplicationException {
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
     throws ApplicationException {
		Message message = new Message();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(messageRequest, message, allowedEntityFields);
    	//Process many to one relationships
    	if (messageRequest.getMessageClassificationId() != null)
    	{
    		MessageClassification messageClassification = getEntityManager().find(MessageClassification.class, messageRequest.getMessageClassificationId());
    		message.setMessageClassification(messageClassification);
    	}
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
    	if (messageRequest.getDataChannelId() != null)
    	{
    		DataChannel dataChannel = getEntityManager().find(DataChannel.class, messageRequest.getDataChannelId());
    		message.setDataChannel(dataChannel);
    	}
		return message;
	}
	
	@Override
	public MessageResponse convertModelToResponse(Message model) throws ApplicationException {
		if (model == null) return null;
		MessageResponse messageResponse = new MessageResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(model, messageResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		if(model.getMessageClassification() != null)
			messageResponse.setMessageClassificationId(model.getMessageClassification().getId());
			messageResponse.setMessageClassificationText(model.getMessageClassification().getName());
		if(model.getMessageType() != null)
			messageResponse.setMessageTypeId(model.getMessageType().getId());
			messageResponse.setMessageTypeText(model.getMessageType().getName());
		if(model.getMessageStatus() != null)
			messageResponse.setMessageStatusId(model.getMessageStatus().getId());
			messageResponse.setMessageStatusText(model.getMessageStatus().getName());
		if(model.getDataChannel() != null)
			messageResponse.setDataChannelId(model.getDataChannel().getId());
			messageResponse.setDataChannelText(model.getDataChannel().getName());
		return messageResponse;
	}
}
