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

import com.nathanclaire.alantra.messaging.model.MessageCategory;
import com.nathanclaire.alantra.messaging.request.MessageCategoryRequest;
import com.nathanclaire.alantra.messaging.response.MessageCategoryResponse;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtils;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class MessageCategoryServiceImpl 
	extends BaseEntityServiceImpl<MessageCategory, MessageCategoryResponse, MessageCategoryRequest> 
	implements MessageCategoryService
{
	private static final String ENTITY_NAME = "MessageCategory";
	private static final String LIST_ACTIVITY_CODE = "LIST_MESSAGING_MESSAGECATEGORY";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_MESSAGING_MESSAGECATEGORY";
	
	private Logger logger = LoggerFactory.getLogger(MessageCategoryServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	
	/**
	 * @param entityClass
	 */
	public MessageCategoryServiceImpl() {
		super(MessageCategory.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.MessageCategory#findById(java.lang.Integer)
	 */
	@Override
	public MessageCategory findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.MessageCategory#findByCode(java.lang.String)
	 */
	@Override
	public MessageCategory findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.MessageCategory#findByName(java.lang.String)
	 */
	@Override
	public MessageCategory findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.MessageCategory#findAll(java.util.Map)
	 */
	@Override
	public List<MessageCategory> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.MessageCategory#createMessageCategory(com.nathanclaire.alantra.messaging.rest.request.ServiceRequest)
	 */
	@Override
	public MessageCategory create(MessageCategoryRequest messageCategoryRequest) throws ApplicationException {
		return createInstance(messageCategoryRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.MessageCategory#deleteMessageCategory(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.MessageCategory#updateMessageCategory(com.nathanclaire.alantra.messaging.rest.request.ServiceRequest)
	 */
	@Override
	public MessageCategory update(MessageCategoryRequest messageCategoryRequest) throws ApplicationException {
		return updateInstance(messageCategoryRequest);
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
		for(MessageCategory messagecategory: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(messagecategory.getId(), messagecategory.getCode(), messagecategory.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public MessageCategory convertRequestToModel(MessageCategoryRequest messageCategoryRequest) 
     throws ApplicationException {
		MessageCategory messageCategory = new MessageCategory();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(messageCategoryRequest, messageCategory, allowedEntityFields);
    	//Process many to one relationships
		return messageCategory;
	}
	
	@Override
	public MessageCategoryResponse convertModelToResponse(MessageCategory model) throws ApplicationException {
		if (model == null) return null;
		MessageCategoryResponse messageCategoryResponse = new MessageCategoryResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(model, messageCategoryResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		return messageCategoryResponse;
	}
}
