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

import com.nathanclaire.alantra.application.model.ApplicationEntityField;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtils;
import com.nathanclaire.alantra.messaging.model.MessageType;
import com.nathanclaire.alantra.messaging.model.MessageTypeTemplate;
import com.nathanclaire.alantra.messaging.request.MessageTypeTemplateRequest;
import com.nathanclaire.alantra.messaging.response.MessageTypeTemplateResponse;
import com.nathanclaire.alantra.notification.model.Template;
import com.nathanclaire.alantra.notification.service.entity.TemplateService;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class MessageTypeTemplateServiceImpl 
	extends BaseEntityServiceImpl<MessageTypeTemplate, MessageTypeTemplateResponse, MessageTypeTemplateRequest> 
	implements MessageTypeTemplateService
{
	private static final String LIST_ITEM_TEMPLATE = "template";
	private static final String LIST_ITEM_MESSAGETYPE = "messageType";
	private static final String ENTITY_NAME = "MessageTypeTemplate";
	private static final String LIST_ACTIVITY_CODE = "LIST_MESSAGING_MESSAGETYPETEMPLATE";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_MESSAGING_MESSAGETYPETEMPLATE";
	
	private Logger logger = LoggerFactory.getLogger(MessageTypeTemplateServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	@Inject
	TemplateService  templateService;
	@Inject
	MessageTypeService  messageTypeService;
	
	/**
	 * @param entityClass
	 */
	public MessageTypeTemplateServiceImpl() {
		super(MessageTypeTemplate.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.MessageTypeTemplate#findById(java.lang.Integer)
	 */
	@Override
	public MessageTypeTemplate findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.MessageTypeTemplate#findByCode(java.lang.String)
	 */
	@Override
	public MessageTypeTemplate findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.MessageTypeTemplate#findByName(java.lang.String)
	 */
	@Override
	public MessageTypeTemplate findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.MessageTypeTemplate#findAll(java.util.Map)
	 */
	@Override
	public List<MessageTypeTemplate> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.MessageTypeTemplate#createMessageTypeTemplate(com.nathanclaire.alantra.messaging.rest.request.ServiceRequest)
	 */
	@Override
	public MessageTypeTemplate create(MessageTypeTemplateRequest messageTypeTemplateRequest) throws ApplicationException {
		return createInstance(messageTypeTemplateRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.MessageTypeTemplate#deleteMessageTypeTemplate(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.MessageTypeTemplate#updateMessageTypeTemplate(com.nathanclaire.alantra.messaging.rest.request.ServiceRequest)
	 */
	@Override
	public MessageTypeTemplate update(MessageTypeTemplateRequest messageTypeTemplateRequest) throws ApplicationException {
		return updateInstance(messageTypeTemplateRequest);
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
		List<ListItemResponse> messageTypes = messageTypeService.asListItem();
    	
		listItems.put(LIST_ITEM_TEMPLATE, templates); 
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
		for(MessageTypeTemplate messagetypetemplate: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(messagetypetemplate.getId(), messagetypetemplate.getCode(), messagetypetemplate.getCode());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public MessageTypeTemplate convertRequestToModel(MessageTypeTemplateRequest messageTypeTemplateRequest) 
     throws ApplicationException {
		MessageTypeTemplate messageTypeTemplate = new MessageTypeTemplate();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(messageTypeTemplateRequest, messageTypeTemplate, allowedEntityFields);
    	//Process many to one relationships
    	if (messageTypeTemplateRequest.getTemplateId() != null)
    	{
    		Template template = getEntityManager().find(Template.class, messageTypeTemplateRequest.getTemplateId());
    		messageTypeTemplate.setTemplate(template);
    	}
    	if (messageTypeTemplateRequest.getMessageTypeId() != null)
    	{
    		MessageType messageType = getEntityManager().find(MessageType.class, messageTypeTemplateRequest.getMessageTypeId());
    		messageTypeTemplate.setMessageType(messageType);
    	}
		return messageTypeTemplate;
	}
	
	@Override
	public MessageTypeTemplateResponse convertModelToResponse(MessageTypeTemplate model) throws ApplicationException {
		if (model == null) return null;
		MessageTypeTemplateResponse messageTypeTemplateResponse = new MessageTypeTemplateResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(model, messageTypeTemplateResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		if(model.getTemplate() != null)
			messageTypeTemplateResponse.setTemplateId(model.getTemplate().getId());
			messageTypeTemplateResponse.setTemplateText(model.getTemplate().getName());
		if(model.getMessageType() != null)
			messageTypeTemplateResponse.setMessageTypeId(model.getMessageType().getId());
			messageTypeTemplateResponse.setMessageTypeText(model.getMessageType().getName());
		return messageTypeTemplateResponse;
	}
}
