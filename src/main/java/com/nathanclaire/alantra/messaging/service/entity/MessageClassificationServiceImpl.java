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

import com.nathanclaire.alantra.messaging.model.MessageClassification;
import com.nathanclaire.alantra.messaging.request.MessageClassificationRequest;
import com.nathanclaire.alantra.messaging.response.MessageClassificationResponse;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtils;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class MessageClassificationServiceImpl 
	extends BaseEntityServiceImpl<MessageClassification, MessageClassificationResponse, MessageClassificationRequest> 
	implements MessageClassificationService
{
	private static final String ENTITY_NAME = "MessageClassification";
	private static final String LIST_ACTIVITY_CODE = "LIST_MESSAGING_MESSAGECLASSIFICATION";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_MESSAGING_MESSAGECLASSIFICATION";
	
	private Logger logger = LoggerFactory.getLogger(MessageClassificationServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	
	/**
	 * @param entityClass
	 */
	public MessageClassificationServiceImpl() {
		super(MessageClassification.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.MessageClassification#findById(java.lang.Integer)
	 */
	@Override
	public MessageClassification findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.MessageClassification#findByCode(java.lang.String)
	 */
	@Override
	public MessageClassification findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.MessageClassification#findByName(java.lang.String)
	 */
	@Override
	public MessageClassification findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.MessageClassification#findAll(java.util.Map)
	 */
	@Override
	public List<MessageClassification> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.MessageClassification#createMessageClassification(com.nathanclaire.alantra.messaging.rest.request.ServiceRequest)
	 */
	@Override
	public MessageClassification create(MessageClassificationRequest messageClassificationRequest) throws ApplicationException {
		return createInstance(messageClassificationRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.MessageClassification#deleteMessageClassification(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.MessageClassification#updateMessageClassification(com.nathanclaire.alantra.messaging.rest.request.ServiceRequest)
	 */
	@Override
	public MessageClassification update(MessageClassificationRequest messageClassificationRequest) throws ApplicationException {
		return updateInstance(messageClassificationRequest);
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
    	
		return listItems;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#asListItem()
	 */
	@Override
	public List<ListItemResponse> asListItem() throws ApplicationException {
		List<ListItemResponse> listItems = new ArrayList<ListItemResponse>();
		queryParameters.clear();
		for(MessageClassification messageclassification: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(messageclassification.getId(), messageclassification.getCode(), messageclassification.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public MessageClassification convertRequestToModel(MessageClassificationRequest messageClassificationRequest) 
     throws ApplicationException {
		MessageClassification messageClassification = new MessageClassification();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(messageClassificationRequest, messageClassification, allowedEntityFields);
    	//Process many to one relationships
		return messageClassification;
	}
	
	@Override
	public MessageClassificationResponse convertModelToResponse(MessageClassification model) throws ApplicationException {
		if (model == null) return null;
		MessageClassificationResponse messageClassificationResponse = new MessageClassificationResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(model, messageClassificationResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		return messageClassificationResponse;
	}
}
