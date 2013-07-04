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

import com.nathanclaire.alantra.messaging.model.MessageApplicationTag;
import com.nathanclaire.alantra.messaging.model.MessageApplication;
import com.nathanclaire.alantra.messaging.request.MessageApplicationTagRequest;
import com.nathanclaire.alantra.messaging.response.MessageApplicationTagResponse;
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
public class MessageApplicationTagServiceImpl 
	extends BaseEntityServiceImpl<MessageApplicationTag, MessageApplicationTagResponse, MessageApplicationTagRequest> 
	implements MessageApplicationTagService
{
	private static final String LIST_ITEM_MESSAGEAPPLICATION = "messageApplication";
	private static final String ENTITY_NAME = "MessageApplicationTag";
	private static final String LIST_ACTIVITY_CODE = "LIST_MESSAGING_MESSAGEAPPLICATIONTAG";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_MESSAGING_MESSAGEAPPLICATIONTAG";
	
	private Logger logger = LoggerFactory.getLogger(MessageApplicationTagServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	@Inject
	MessageApplicationService  messageApplicationService;
	
	/**
	 * @param entityClass
	 */
	public MessageApplicationTagServiceImpl() {
		super(MessageApplicationTag.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.MessageApplicationTag#findById(java.lang.Integer)
	 */
	@Override
	public MessageApplicationTag findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.MessageApplicationTag#findByCode(java.lang.String)
	 */
	@Override
	public MessageApplicationTag findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.MessageApplicationTag#findByName(java.lang.String)
	 */
	@Override
	public MessageApplicationTag findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.MessageApplicationTag#findAll(java.util.Map)
	 */
	@Override
	public List<MessageApplicationTag> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.MessageApplicationTag#createMessageApplicationTag(com.nathanclaire.alantra.messaging.rest.request.ServiceRequest)
	 */
	@Override
	public MessageApplicationTag create(MessageApplicationTagRequest messageApplicationTagRequest) throws ApplicationException {
		return createInstance(messageApplicationTagRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.MessageApplicationTag#deleteMessageApplicationTag(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.MessageApplicationTag#updateMessageApplicationTag(com.nathanclaire.alantra.messaging.rest.request.ServiceRequest)
	 */
	@Override
	public MessageApplicationTag update(MessageApplicationTagRequest messageApplicationTagRequest) throws ApplicationException {
		return updateInstance(messageApplicationTagRequest);
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
		for(MessageApplicationTag messageapplicationtag: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(messageapplicationtag.getId(), messageapplicationtag.getCode(), messageapplicationtag.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public MessageApplicationTag convertRequestToModel(MessageApplicationTagRequest messageApplicationTagRequest) 
     throws ApplicationException {
		MessageApplicationTag messageApplicationTag = new MessageApplicationTag();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(messageApplicationTagRequest, messageApplicationTag, allowedEntityFields);
    	//Process many to one relationships
    	if (messageApplicationTagRequest.getMessageApplicationId() != null)
    	{
    		MessageApplication messageApplication = getEntityManager().find(MessageApplication.class, messageApplicationTagRequest.getMessageApplicationId());
    		messageApplicationTag.setMessageApplication(messageApplication);
    	}
		return messageApplicationTag;
	}
	
	@Override
	public MessageApplicationTagResponse convertModelToResponse(MessageApplicationTag model) throws ApplicationException {
		if (model == null) return null;
		MessageApplicationTagResponse messageApplicationTagResponse = new MessageApplicationTagResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(model, messageApplicationTagResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		if(model.getMessageApplication() != null)
			messageApplicationTagResponse.setMessageApplicationId(model.getMessageApplication().getId());
			messageApplicationTagResponse.setMessageApplicationText(model.getMessageApplication().getName());
		return messageApplicationTagResponse;
	}
}
