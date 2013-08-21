/**
 * 
 */
package com.nathanclaire.alantra.messaging.service.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.ws.rs.core.MultivaluedMap;

import org.joda.time.DateTime; 

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.messaging.model.MessageAttachment;
import com.nathanclaire.alantra.messaging.request.MessageAttachmentRequest;
import com.nathanclaire.alantra.messaging.response.MessageAttachmentResponse;
import com.nathanclaire.alantra.application.model.ApplicationEntityField;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtils;
import com.nathanclaire.alantra.messaging.model.Message;
import com.nathanclaire.alantra.messaging.service.entity.MessageService;

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
	@Inject MessageService  messageService;
	
	/**
	 * @param entityClass
	 */
	public MessageAttachmentServiceImpl() {
		super(MessageAttachment.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messageAttachment.service.MessageAttachment#findById(java.lang.Integer)
	 */
	@Override
	public MessageAttachment findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messageAttachment.service.MessageAttachment#findByCode(java.lang.String)
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
	 * @see com.nathanclaire.alantra.messaging.service.MessageAttachment#updateMessageAttachment(com.nathanclaire.alantra.messageAttachment.rest.request.ServiceRequest)
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
		for(MessageAttachment messageAttachment: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(messageAttachment.getId(), messageAttachment.getCode(), messageAttachment.getName());
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
		if(model.getMessage() != null)
			messageAttachmentResponse.setMessageId(model.getMessage().getId());
			messageAttachmentResponse.setMessageText(model.getMessage().getName());
		return messageAttachmentResponse;
	}

	/**
	 * @param customerId
	 * @param accountId
	 * @param chequeNo
	 * @param currencyId
	 * @param messageAttachmentTypeId
	 * @param amount
	 */
	private void initializeQueryParameters(Integer messageId, String dataTy, String dataUrl, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) {
		queryParameters.clear();
        if(messageId != null)
            queryParameters.add("message", messageId.toString());
        if(dataTy != null)
            queryParameters.add("dataTy", dataTy.toString());
        if(dataUrl != null)
            queryParameters.add("dataUrl", dataUrl.toString());
        if(id != null)
            queryParameters.add("id", id.toString());
        if(code != null)
            queryParameters.add("code", code.toString());
        if(effectiveDt != null)
            queryParameters.add("effectiveDt", effectiveDt.toString());
        if(recSt != null)
            queryParameters.add("recSt", recSt.toString());
        if(versionNo != null)
            queryParameters.add("versionNo", versionNo.toString());
        if(rowTs != null)
            queryParameters.add("rowTs", rowTs.toString());
        if(createdDt != null)
            queryParameters.add("createdDt", createdDt.toString());
        if(createdByUsr != null)
            queryParameters.add("createdByUsr", createdByUsr.toString());
        if(lastModifiedDt != null)
            queryParameters.add("lastModifiedDt", lastModifiedDt.toString());
        if(lastModifiedUsr != null)
            queryParameters.add("lastModifiedUsr", lastModifiedUsr.toString());
		
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl#extractPredicates(javax.ws.rs.core.MultivaluedMap, javax.persistence.criteria.CriteriaBuilder, javax.persistence.criteria.Root)
	 */
	@Override
	protected Predicate[] extractPredicates(
			MultivaluedMap<String, String> queryParameters,	CriteriaBuilder criteriaBuilder, Root<MessageAttachment> root) 
	{
		
		List<Predicate> predicates = new ArrayList<Predicate>() ;
        if (queryParameters.containsKey("message")) {
            Integer messageId = Integer.valueOf(queryParameters.getFirst("message"));
            predicates.add(criteriaBuilder.equal(root.get("message").get("id"), messageId));
        }
		if (queryParameters.containsKey("dataTy")) {
            String dataTy = queryParameters.getFirst("dataTy");
            predicates.add(criteriaBuilder.equal(root.get("dataTy"), dataTy));
        }
		if (queryParameters.containsKey("dataUrl")) {
            String dataUrl = queryParameters.getFirst("dataUrl");
            predicates.add(criteriaBuilder.equal(root.get("dataUrl"), dataUrl));
        }
		if (queryParameters.containsKey("id")) {
            Integer id = Integer.valueOf(queryParameters.getFirst("id"));
            predicates.add(criteriaBuilder.equal(root.get("id"), id));
        }
		if (queryParameters.containsKey("code")) {
            String code = queryParameters.getFirst("code");
            predicates.add(criteriaBuilder.equal(root.get("code"), code));
        }
		if (queryParameters.containsKey("effectiveDt")) {
			DateTime effectiveDt = new DateTime(queryParameters.getFirst("effectiveDt"));
            predicates.add(criteriaBuilder.equal(root.get("effectiveDt"), effectiveDt.toDate()));
        }
		if (queryParameters.containsKey("recSt")) {
            String recSt = queryParameters.getFirst("recSt");
            predicates.add(criteriaBuilder.equal(root.get("recSt"), recSt));
        }
		if (queryParameters.containsKey("versionNo")) {
            Integer versionNo = Integer.valueOf(queryParameters.getFirst("versionNo"));
            predicates.add(criteriaBuilder.equal(root.get("versionNo"), versionNo));
        }
		if (queryParameters.containsKey("rowTs")) {
			DateTime rowTs = new DateTime(queryParameters.getFirst("rowTs"));
            predicates.add(criteriaBuilder.equal(root.get("rowTs"), rowTs.toDate()));
        }
		if (queryParameters.containsKey("createdDt")) {
			DateTime createdDt = new DateTime(queryParameters.getFirst("createdDt"));
            predicates.add(criteriaBuilder.equal(root.get("createdDt"), createdDt.toDate()));
        }
		if (queryParameters.containsKey("createdByUsr")) {
            String createdByUsr = queryParameters.getFirst("createdByUsr");
            predicates.add(criteriaBuilder.equal(root.get("createdByUsr"), createdByUsr));
        }
		if (queryParameters.containsKey("lastModifiedDt")) {
			DateTime lastModifiedDt = new DateTime(queryParameters.getFirst("lastModifiedDt"));
            predicates.add(criteriaBuilder.equal(root.get("lastModifiedDt"), lastModifiedDt.toDate()));
        }
		if (queryParameters.containsKey("lastModifiedUsr")) {
            String lastModifiedUsr = queryParameters.getFirst("lastModifiedUsr");
            predicates.add(criteriaBuilder.equal(root.get("lastModifiedUsr"), lastModifiedUsr));
        }
        return predicates.toArray(new Predicate[]{});
	}
}
