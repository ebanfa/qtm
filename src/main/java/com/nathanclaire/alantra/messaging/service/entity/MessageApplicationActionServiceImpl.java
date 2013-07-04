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

import com.nathanclaire.alantra.messaging.model.MessageApplicationAction;
import com.nathanclaire.alantra.messaging.model.MessageApplication;
import com.nathanclaire.alantra.messaging.request.MessageApplicationActionRequest;
import com.nathanclaire.alantra.messaging.response.MessageApplicationActionResponse;
import com.nathanclaire.alantra.messaging.service.entity.MessageApplicationService;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtils;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class MessageApplicationActionServiceImpl 
	extends BaseEntityServiceImpl<MessageApplicationAction, MessageApplicationActionResponse, MessageApplicationActionRequest> 
	implements MessageApplicationActionService
{
	private static final String LIST_ITEM_MESSAGEAPPLICATION = "messageApplication";
	private static final String ENTITY_NAME = "MessageApplicationAction";
	private static final String LIST_ACTIVITY_CODE = "LIST_MESSAGING_MESSAGEAPPLICATIONACTION";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_MESSAGING_MESSAGEAPPLICATIONACTION";
	
	private Logger logger = LoggerFactory.getLogger(MessageApplicationActionServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	@Inject
	MessageApplicationService  messageApplicationService;
	
	/**
	 * @param entityClass
	 */
	public MessageApplicationActionServiceImpl() {
		super(MessageApplicationAction.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.MessageApplicationAction#findById(java.lang.Integer)
	 */
	@Override
	public MessageApplicationAction findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.MessageApplicationAction#findByCode(java.lang.String)
	 */
	@Override
	public MessageApplicationAction findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.MessageApplicationAction#findByName(java.lang.String)
	 */
	@Override
	public MessageApplicationAction findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.MessageApplicationAction#findAll(java.util.Map)
	 */
	@Override
	public List<MessageApplicationAction> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.MessageApplicationAction#createMessageApplicationAction(com.nathanclaire.alantra.messaging.rest.request.ServiceRequest)
	 */
	@Override
	public MessageApplicationAction create(MessageApplicationActionRequest messageApplicationActionRequest) throws ApplicationException {
		return createInstance(messageApplicationActionRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.MessageApplicationAction#deleteMessageApplicationAction(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.MessageApplicationAction#updateMessageApplicationAction(com.nathanclaire.alantra.messaging.rest.request.ServiceRequest)
	 */
	@Override
	public MessageApplicationAction update(MessageApplicationActionRequest messageApplicationActionRequest) throws ApplicationException {
		return updateInstance(messageApplicationActionRequest);
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
		List<ListItemResponse> messageApplications = messageApplicationService.asListItem();
    	
		listItems.put(LIST_ITEM_MESSAGEAPPLICATION, messageApplications); 
		return listItems;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#asListItem()
	 */
	@Override
	public List<ListItemResponse> asListItem() throws ApplicationException {
		List<ListItemResponse> listItems = new ArrayList<ListItemResponse>();
		queryParameters.clear();
		for(MessageApplicationAction messageapplicationaction: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(messageapplicationaction.getId(), messageapplicationaction.getCode(), messageapplicationaction.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public MessageApplicationAction convertRequestToModel(MessageApplicationActionRequest messageApplicationActionRequest) 
     throws ApplicationException {
		MessageApplicationAction messageApplicationAction = new MessageApplicationAction();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(messageApplicationActionRequest, messageApplicationAction, allowedEntityFields);
    	//Process many to one relationships
    	if (messageApplicationActionRequest.getMessageApplicationId() != null)
    	{
    		MessageApplication messageApplication = getEntityManager().find(MessageApplication.class, messageApplicationActionRequest.getMessageApplicationId());
    		messageApplicationAction.setMessageApplication(messageApplication);
    	}
		return messageApplicationAction;
	}
	
	@Override
	public MessageApplicationActionResponse convertModelToResponse(MessageApplicationAction model) throws ApplicationException {
		if (model == null) return null;
		MessageApplicationActionResponse messageApplicationActionResponse = new MessageApplicationActionResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(model, messageApplicationActionResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		if(model.getMessageApplication() != null)
			messageApplicationActionResponse.setMessageApplicationId(model.getMessageApplication().getId());
			messageApplicationActionResponse.setMessageApplicationText(model.getMessageApplication().getName());
		return messageApplicationActionResponse;
	}
}
