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

import com.nathanclaire.alantra.messaging.model.MessageAttachment;
import com.nathanclaire.alantra.messaging.model.Message;
import com.nathanclaire.alantra.messaging.request.MessageAttachmentRequest;
import com.nathanclaire.alantra.messaging.response.MessageAttachmentResponse;
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
public class MessageAttachmentServiceImpl 
	extends BaseEntityServiceImpl<MessageAttachment, MessageAttachmentResponse, MessageAttachmentRequest> 
	implements MessageAttachmentService
{
	private static final String LIST_ITEM_MESSAGE = "message";
	private static final String ENTITY_NAME = "MessageAttachment";
	private static final String LIST_ACTIVITY_CODE = "LIST_MESSAGING_MESSAGEATTACHMENT";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_MESSAGING_MESSAGEATTACHMENT";
	
	private Logger logger = LoggerFactory.getLogger(MessageAttachmentServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	@Inject
	MessageService  messageService;
	
	/**
	 * @param entityClass
	 */
	public MessageAttachmentServiceImpl() {
		super(MessageAttachment.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.MessageAttachment#findById(java.lang.Integer)
	 */
	@Override
	public MessageAttachment findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.MessageAttachment#findByCode(java.lang.String)
	 */
	@Override
	public MessageAttachment findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.MessageAttachment#findByName(java.lang.String)
	 */
	@Override
	public MessageAttachment findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.MessageAttachment#findAll(java.util.Map)
	 */
	@Override
	public List<MessageAttachment> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.MessageAttachment#createMessageAttachment(com.nathanclaire.alantra.messaging.rest.request.ServiceRequest)
	 */
	@Override
	public MessageAttachment create(MessageAttachmentRequest messageAttachmentRequest) throws ApplicationException {
		return createInstance(messageAttachmentRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.MessageAttachment#deleteMessageAttachment(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.MessageAttachment#updateMessageAttachment(com.nathanclaire.alantra.messaging.rest.request.ServiceRequest)
	 */
	@Override
	public MessageAttachment update(MessageAttachmentRequest messageAttachmentRequest) throws ApplicationException {
		return updateInstance(messageAttachmentRequest);
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
		for(MessageAttachment messageattachment: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(messageattachment.getId(), messageattachment.getCode(), messageattachment.getCode());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public MessageAttachment convertRequestToModel(MessageAttachmentRequest messageAttachmentRequest) 
     throws ApplicationException {
		MessageAttachment messageAttachment = new MessageAttachment();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(messageAttachmentRequest, messageAttachment, allowedEntityFields);
    	//Process many to one relationships
    	if (messageAttachmentRequest.getMessageId() != null)
    	{
    		Message message = getEntityManager().find(Message.class, messageAttachmentRequest.getMessageId());
    		messageAttachment.setMessage(message);
    	}
		return messageAttachment;
	}
	
	@Override
	public MessageAttachmentResponse convertModelToResponse(MessageAttachment model) throws ApplicationException {
		if (model == null) return null;
		MessageAttachmentResponse messageAttachmentResponse = new MessageAttachmentResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(model, messageAttachmentResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		if(model.getMessage() != null)
			messageAttachmentResponse.setMessageId(model.getMessage().getId());
			messageAttachmentResponse.setMessageText(model.getMessage().getCode());
		return messageAttachmentResponse;
	}
}
