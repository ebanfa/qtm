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

import com.nathanclaire.alantra.messaging.model.MessageTypeTag;
import com.nathanclaire.alantra.messaging.model.MessageType;
import com.nathanclaire.alantra.messaging.request.MessageTypeTagRequest;
import com.nathanclaire.alantra.messaging.response.MessageTypeTagResponse;
import com.nathanclaire.alantra.messaging.service.entity.MessageTypeService;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtils;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class MessageTypeTagServiceImpl 
	extends BaseEntityServiceImpl<MessageTypeTag, MessageTypeTagResponse, MessageTypeTagRequest> 
	implements MessageTypeTagService
{
	private static final String LIST_ITEM_MESSAGETYPE = "messageType";
	private static final String ENTITY_NAME = "MessageTypeTag";
	private static final String LIST_ACTIVITY_CODE = "LIST_MESSAGING_MESSAGETYPETAG";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_MESSAGING_MESSAGETYPETAG";
	
	private Logger logger = LoggerFactory.getLogger(MessageTypeTagServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	@Inject
	MessageTypeService  messageTypeService;
	
	/**
	 * @param entityClass
	 */
	public MessageTypeTagServiceImpl() {
		super(MessageTypeTag.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.MessageTypeTag#findById(java.lang.Integer)
	 */
	@Override
	public MessageTypeTag findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.MessageTypeTag#findByCode(java.lang.String)
	 */
	@Override
	public MessageTypeTag findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.MessageTypeTag#findByName(java.lang.String)
	 */
	@Override
	public MessageTypeTag findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.MessageTypeTag#findAll(java.util.Map)
	 */
	@Override
	public List<MessageTypeTag> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.MessageTypeTag#createMessageTypeTag(com.nathanclaire.alantra.messaging.rest.request.ServiceRequest)
	 */
	@Override
	public MessageTypeTag create(MessageTypeTagRequest messageTypeTagRequest) throws ApplicationException {
		return createInstance(messageTypeTagRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.MessageTypeTag#deleteMessageTypeTag(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.MessageTypeTag#updateMessageTypeTag(com.nathanclaire.alantra.messaging.rest.request.ServiceRequest)
	 */
	@Override
	public MessageTypeTag update(MessageTypeTagRequest messageTypeTagRequest) throws ApplicationException {
		return updateInstance(messageTypeTagRequest);
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
		List<ListItemResponse> messageTypes = messageTypeService.asListItem();
    	
		listItems.put(LIST_ITEM_MESSAGETYPE, messageTypes); 
		return listItems;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#asListItem()
	 */
	@Override
	public List<ListItemResponse> asListItem() throws ApplicationException {
		List<ListItemResponse> listItems = new ArrayList<ListItemResponse>();
		queryParameters.clear();
		for(MessageTypeTag messagetypetag: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(messagetypetag.getId(), messagetypetag.getCode(), messagetypetag.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public MessageTypeTag convertRequestToModel(MessageTypeTagRequest messageTypeTagRequest) 
     throws ApplicationException {
		MessageTypeTag messageTypeTag = new MessageTypeTag();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(messageTypeTagRequest, messageTypeTag, allowedEntityFields);
    	//Process many to one relationships
    	if (messageTypeTagRequest.getMessageTypeId() != null)
    	{
    		MessageType messageType = getEntityManager().find(MessageType.class, messageTypeTagRequest.getMessageTypeId());
    		messageTypeTag.setMessageType(messageType);
    	}
		return messageTypeTag;
	}
	
	@Override
	public MessageTypeTagResponse convertModelToResponse(MessageTypeTag model) throws ApplicationException {
		if (model == null) return null;
		MessageTypeTagResponse messageTypeTagResponse = new MessageTypeTagResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(model, messageTypeTagResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		if(model.getMessageType() != null)
			messageTypeTagResponse.setMessageTypeId(model.getMessageType().getId());
			messageTypeTagResponse.setMessageTypeText(model.getMessageType().getName());
		return messageTypeTagResponse;
	}
}
