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

import com.nathanclaire.alantra.messaging.model.MessageAttachements;
import com.nathanclaire.alantra.messaging.model.Message;
import com.nathanclaire.alantra.messaging.request.MessageAttachementsRequest;
import com.nathanclaire.alantra.messaging.response.MessageAttachementsResponse;
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
public class MessageAttachementsServiceImpl 
	extends BaseEntityServiceImpl<MessageAttachements, MessageAttachementsResponse, MessageAttachementsRequest> 
	implements MessageAttachementsService
{
	private static final String LIST_ITEM_MESSAGE = "message";
	private static final String ENTITY_NAME = "MessageAttachements";
	private static final String LIST_ACTIVITY_CODE = "LIST_MESSAGING_MESSAGEATTACHEMENTS";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_MESSAGING_MESSAGEATTACHEMENTS";
	
	private Logger logger = LoggerFactory.getLogger(MessageAttachementsServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	@Inject
	MessageService  messageService;
	
	/**
	 * @param entityClass
	 */
	public MessageAttachementsServiceImpl() {
		super(MessageAttachements.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.MessageAttachements#findById(java.lang.Integer)
	 */
	@Override
	public MessageAttachements findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.MessageAttachements#findByCode(java.lang.String)
	 */
	@Override
	public MessageAttachements findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.MessageAttachements#findByName(java.lang.String)
	 */
	@Override
	public MessageAttachements findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.MessageAttachements#findAll(java.util.Map)
	 */
	@Override
	public List<MessageAttachements> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.MessageAttachements#createMessageAttachements(com.nathanclaire.alantra.messaging.rest.request.ServiceRequest)
	 */
	@Override
	public MessageAttachements create(MessageAttachementsRequest messageAttachementsRequest) throws ApplicationException {
		return createInstance(messageAttachementsRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.MessageAttachements#deleteMessageAttachements(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.MessageAttachements#updateMessageAttachements(com.nathanclaire.alantra.messaging.rest.request.ServiceRequest)
	 */
	@Override
	public MessageAttachements update(MessageAttachementsRequest messageAttachementsRequest) throws ApplicationException {
		return updateInstance(messageAttachementsRequest);
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
		for(MessageAttachements messageattachements: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(messageattachements.getId(), messageattachements.getCode(), messageattachements.getCode());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public MessageAttachements convertRequestToModel(MessageAttachementsRequest messageAttachementsRequest) 
     throws ApplicationException {
		MessageAttachements messageAttachements = new MessageAttachements();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(messageAttachementsRequest, messageAttachements, allowedEntityFields);
    	//Process many to one relationships
    	if (messageAttachementsRequest.getMessageId() != null)
    	{
    		Message message = getEntityManager().find(Message.class, messageAttachementsRequest.getMessageId());
    		messageAttachements.setMessage(message);
    	}
		return messageAttachements;
	}
	
	@Override
	public MessageAttachementsResponse convertModelToResponse(MessageAttachements model) throws ApplicationException {
		if (model == null) return null;
		MessageAttachementsResponse messageAttachementsResponse = new MessageAttachementsResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(model, messageAttachementsResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		if(model.getMessage() != null)
			messageAttachementsResponse.setMessageId(model.getMessage().getId());
			messageAttachementsResponse.setMessageText(model.getMessage().getCode());
		return messageAttachementsResponse;
	}
}
