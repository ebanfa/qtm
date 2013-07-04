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

import com.nathanclaire.alantra.messaging.model.MessageApplication;
import com.nathanclaire.alantra.messaging.request.MessageApplicationRequest;
import com.nathanclaire.alantra.messaging.response.MessageApplicationResponse;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtils;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class MessageApplicationServiceImpl 
	extends BaseEntityServiceImpl<MessageApplication, MessageApplicationResponse, MessageApplicationRequest> 
	implements MessageApplicationService
{
	private static final String ENTITY_NAME = "MessageApplication";
	private static final String LIST_ACTIVITY_CODE = "LIST_MESSAGING_MESSAGEAPPLICATION";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_MESSAGING_MESSAGEAPPLICATION";
	
	private Logger logger = LoggerFactory.getLogger(MessageApplicationServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	
	/**
	 * @param entityClass
	 */
	public MessageApplicationServiceImpl() {
		super(MessageApplication.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.MessageApplication#findById(java.lang.Integer)
	 */
	@Override
	public MessageApplication findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.MessageApplication#findByCode(java.lang.String)
	 */
	@Override
	public MessageApplication findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.MessageApplication#findByName(java.lang.String)
	 */
	@Override
	public MessageApplication findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.MessageApplication#findAll(java.util.Map)
	 */
	@Override
	public List<MessageApplication> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.MessageApplication#createMessageApplication(com.nathanclaire.alantra.messaging.rest.request.ServiceRequest)
	 */
	@Override
	public MessageApplication create(MessageApplicationRequest messageApplicationRequest) throws ApplicationException {
		return createInstance(messageApplicationRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.MessageApplication#deleteMessageApplication(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.MessageApplication#updateMessageApplication(com.nathanclaire.alantra.messaging.rest.request.ServiceRequest)
	 */
	@Override
	public MessageApplication update(MessageApplicationRequest messageApplicationRequest) throws ApplicationException {
		return updateInstance(messageApplicationRequest);
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
		for(MessageApplication messageapplication: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(messageapplication.getId(), messageapplication.getCode(), messageapplication.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public MessageApplication convertRequestToModel(MessageApplicationRequest messageApplicationRequest) 
     throws ApplicationException {
		MessageApplication messageApplication = new MessageApplication();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(messageApplicationRequest, messageApplication, allowedEntityFields);
    	//Process many to one relationships
		return messageApplication;
	}
	
	@Override
	public MessageApplicationResponse convertModelToResponse(MessageApplication model) throws ApplicationException {
		if (model == null) return null;
		MessageApplicationResponse messageApplicationResponse = new MessageApplicationResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(model, messageApplicationResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		return messageApplicationResponse;
	}
}
