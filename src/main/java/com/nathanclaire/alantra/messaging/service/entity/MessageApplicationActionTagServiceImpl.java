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

import com.nathanclaire.alantra.messaging.model.MessageApplicationActionTag;
import com.nathanclaire.alantra.messaging.model.MessageApplicationAction;
import com.nathanclaire.alantra.messaging.request.MessageApplicationActionTagRequest;
import com.nathanclaire.alantra.messaging.response.MessageApplicationActionTagResponse;
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
public class MessageApplicationActionTagServiceImpl 
	extends BaseEntityServiceImpl<MessageApplicationActionTag, MessageApplicationActionTagResponse, MessageApplicationActionTagRequest> 
	implements MessageApplicationActionTagService
{
	private static final String LIST_ITEM_MESSAGEAPPLICATIONACTION = "messageApplicationAction";
	private static final String ENTITY_NAME = "MessageApplicationActionTag";
	private static final String LIST_ACTIVITY_CODE = "LIST_MESSAGING_MESSAGEAPPLICATIONACTIONTAG";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_MESSAGING_MESSAGEAPPLICATIONACTIONTAG";
	
	private Logger logger = LoggerFactory.getLogger(MessageApplicationActionTagServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	@Inject
	MessageApplicationActionService  messageApplicationActionService;
	
	/**
	 * @param entityClass
	 */
	public MessageApplicationActionTagServiceImpl() {
		super(MessageApplicationActionTag.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.MessageApplicationActionTag#findById(java.lang.Integer)
	 */
	@Override
	public MessageApplicationActionTag findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.MessageApplicationActionTag#findByCode(java.lang.String)
	 */
	@Override
	public MessageApplicationActionTag findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.MessageApplicationActionTag#findByName(java.lang.String)
	 */
	@Override
	public MessageApplicationActionTag findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.MessageApplicationActionTag#findAll(java.util.Map)
	 */
	@Override
	public List<MessageApplicationActionTag> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.MessageApplicationActionTag#createMessageApplicationActionTag(com.nathanclaire.alantra.messaging.rest.request.ServiceRequest)
	 */
	@Override
	public MessageApplicationActionTag create(MessageApplicationActionTagRequest messageApplicationActionTagRequest) throws ApplicationException {
		return createInstance(messageApplicationActionTagRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.MessageApplicationActionTag#deleteMessageApplicationActionTag(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.MessageApplicationActionTag#updateMessageApplicationActionTag(com.nathanclaire.alantra.messaging.rest.request.ServiceRequest)
	 */
	@Override
	public MessageApplicationActionTag update(MessageApplicationActionTagRequest messageApplicationActionTagRequest) throws ApplicationException {
		return updateInstance(messageApplicationActionTagRequest);
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
		for(MessageApplicationActionTag messageapplicationactiontag: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(messageapplicationactiontag.getId(), messageapplicationactiontag.getCode(), messageapplicationactiontag.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public MessageApplicationActionTag convertRequestToModel(MessageApplicationActionTagRequest messageApplicationActionTagRequest) 
     throws ApplicationException {
		MessageApplicationActionTag messageApplicationActionTag = new MessageApplicationActionTag();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(messageApplicationActionTagRequest, messageApplicationActionTag, allowedEntityFields);
    	//Process many to one relationships
    	if (messageApplicationActionTagRequest.getMessageApplicationActionId() != null)
    	{
    		MessageApplicationAction messageApplicationAction = getEntityManager().find(MessageApplicationAction.class, messageApplicationActionTagRequest.getMessageApplicationActionId());
    		messageApplicationActionTag.setMessageApplicationAction(messageApplicationAction);
    	}
		return messageApplicationActionTag;
	}
	
	@Override
	public MessageApplicationActionTagResponse convertModelToResponse(MessageApplicationActionTag model) throws ApplicationException {
		if (model == null) return null;
		MessageApplicationActionTagResponse messageApplicationActionTagResponse = new MessageApplicationActionTagResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(model, messageApplicationActionTagResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		if(model.getMessageApplicationAction() != null)
			messageApplicationActionTagResponse.setMessageApplicationActionId(model.getMessageApplicationAction().getId());
			messageApplicationActionTagResponse.setMessageApplicationActionText(model.getMessageApplicationAction().getName());
		return messageApplicationActionTagResponse;
	}
}
