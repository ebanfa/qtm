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

import com.nathanclaire.alantra.messaging.model.Message;
import com.nathanclaire.alantra.messaging.request.MessageRequest;
import com.nathanclaire.alantra.messaging.response.MessageResponse;
import com.nathanclaire.alantra.application.model.ApplicationEntityField;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtil;
import com.nathanclaire.alantra.messaging.model.MessageClassification;
import com.nathanclaire.alantra.messaging.service.entity.MessageClassificationService;
import com.nathanclaire.alantra.messaging.model.MessageType;
import com.nathanclaire.alantra.messaging.service.entity.MessageTypeService;
import com.nathanclaire.alantra.messaging.model.MessageStatus;
import com.nathanclaire.alantra.messaging.service.entity.MessageStatusService;
import com.nathanclaire.alantra.datasource.model.DataChannel;
import com.nathanclaire.alantra.datasource.service.entity.DataChannelEntityService;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class MessageServiceImpl 
	extends BaseEntityServiceImpl<Message, MessageResponse, MessageRequest> 
	implements MessageService
{

private static final String LIST_ITEM_MESSAGECLASSIFICATION = "messageClassification";
private static final String LIST_ITEM_MESSAGETYPE = "messageType";
private static final String LIST_ITEM_MESSAGESTATUS = "messageStatus";
private static final String LIST_ITEM_DATACHANNEL = "dataChannel";
	private static final String ENTITY_NAME = "Message";
	private static final String LIST_ACTIVITY_CODE = "LIST_MESSAGING_MESSAGE";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_MESSAGING_MESSAGE";
	
	private Logger logger = LoggerFactory.getLogger(MessageServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	@Inject MessageClassificationService  messageClassificationService;
	@Inject MessageTypeService  messageTypeService;
	@Inject MessageStatusService  messageStatusService;
	@Inject DataChannelEntityService  dataChannelEntityService;
	
	/**
	 * @param entityClass
	 */
	public MessageServiceImpl() {
		super(Message.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.message.service.Message#findById(java.lang.Integer)
	 */
	@Override
	public Message findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.message.service.Message#findByCode(java.lang.String)
	 */
	@Override
	public Message findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.Message#findByName(java.lang.String)
	 */
	@Override
	public Message findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.Message#findAll(java.util.Map)
	 */
	@Override
	public List<Message> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.Message#createMessage(com.nathanclaire.alantra.messaging.rest.request.ServiceRequest)
	 */
	@Override
	public Message create(MessageRequest messageRequest) throws ApplicationException {
		return createInstance(messageRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.Message#deleteMessage(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.Message#updateMessage(com.nathanclaire.alantra.message.rest.request.ServiceRequest)
	 */
	@Override
	public Message update(MessageRequest messageRequest) throws ApplicationException {
		return updateInstance(messageRequest);
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
        List<ListItemResponse> messageClassifications = messageClassificationService.asListItem();
        List<ListItemResponse> messageTypes = messageTypeService.asListItem();
        List<ListItemResponse> messageStatuss = messageStatusService.asListItem();
        List<ListItemResponse> dataChannels = dataChannelEntityService.asListItem();

        listItems.put(LIST_ITEM_MESSAGECLASSIFICATION, messageClassifications);
        listItems.put(LIST_ITEM_MESSAGETYPE, messageTypes);
        listItems.put(LIST_ITEM_MESSAGESTATUS, messageStatuss);
        listItems.put(LIST_ITEM_DATACHANNEL, dataChannels);
		return listItems;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#asListItem()
	 */
	@Override
	public List<ListItemResponse> asListItem() throws ApplicationException {
		List<ListItemResponse> listItems = new ArrayList<ListItemResponse>();
		queryParameters.clear();
		for(Message message: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(message.getId(), message.getCode(), message.getCode());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public Message convertRequestToModel(MessageRequest messageRequest) 
     throws ApplicationException {
		Message message = new Message();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtil.copyProperties(messageRequest, message, allowedEntityFields);
    	//Process many to one relationships
        if (messageRequest.getMessageClassificationId() != null)
    	{
    		MessageClassification messageClassification = getEntityManager().find(MessageClassification.class, messageRequest.getMessageClassificationId());
    		message.setMessageClassification(messageClassification);
    	}
        if (messageRequest.getMessageTypeId() != null)
    	{
    		MessageType messageType = getEntityManager().find(MessageType.class, messageRequest.getMessageTypeId());
    		message.setMessageType(messageType);
    	}
        if (messageRequest.getMessageStatusId() != null)
    	{
    		MessageStatus messageStatus = getEntityManager().find(MessageStatus.class, messageRequest.getMessageStatusId());
    		message.setMessageStatus(messageStatus);
    	}
        if (messageRequest.getDataChannelId() != null)
    	{
    		DataChannel dataChannel = getEntityManager().find(DataChannel.class, messageRequest.getDataChannelId());
    		message.setDataChannel(dataChannel);
    	}
		return message;
	}
	
	@Override
	public MessageResponse convertModelToResponse(Message model) throws ApplicationException {
		if (model == null) return null;
		MessageResponse messageResponse = new MessageResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtil.copyProperties(model, messageResponse, allowedEntityFields);
		if(model.getMessageClassification() != null)
			messageResponse.setMessageClassificationId(model.getMessageClassification().getId());
			messageResponse.setMessageClassificationText(model.getMessageClassification().getName());
		if(model.getMessageType() != null)
			messageResponse.setMessageTypeId(model.getMessageType().getId());
			messageResponse.setMessageTypeText(model.getMessageType().getName());
		if(model.getMessageStatus() != null)
			messageResponse.setMessageStatusId(model.getMessageStatus().getId());
			messageResponse.setMessageStatusText(model.getMessageStatus().getName());
		if(model.getDataChannel() != null)
			messageResponse.setDataChannelId(model.getDataChannel().getId());
			messageResponse.setDataChannelText(model.getDataChannel().getName());
		return messageResponse;
	}

	/**
	 * @param customerId
	 * @param accountId
	 * @param chequeNo
	 * @param currencyId
	 * @param messageTypeId
	 * @param amount
	 */
	private void initializeQueryParameters(Integer messageClassificationId, Integer messageTypeId, Integer messageStatusId, Integer dataChannelId, String messageFrom, String messageTo, String messageSubject, String messageTxt, String msgAttachmentTy, String msgAttachmentFile, Character inOutFg, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) {
		queryParameters.clear();
        if(messageClassificationId != null)
            queryParameters.add("messageClassification", messageClassificationId.toString());
        if(messageTypeId != null)
            queryParameters.add("messageType", messageTypeId.toString());
        if(messageStatusId != null)
            queryParameters.add("messageStatus", messageStatusId.toString());
        if(dataChannelId != null)
            queryParameters.add("dataChannel", dataChannelId.toString());
        if(messageFrom != null)
            queryParameters.add("messageFrom", messageFrom.toString());
        if(messageTo != null)
            queryParameters.add("messageTo", messageTo.toString());
        if(messageSubject != null)
            queryParameters.add("messageSubject", messageSubject.toString());
        if(messageTxt != null)
            queryParameters.add("messageTxt", messageTxt.toString());
        if(msgAttachmentTy != null)
            queryParameters.add("msgAttachmentTy", msgAttachmentTy.toString());
        if(msgAttachmentFile != null)
            queryParameters.add("msgAttachmentFile", msgAttachmentFile.toString());
        if(inOutFg != null)
            queryParameters.add("inOutFg", inOutFg.toString());
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
			MultivaluedMap<String, String> queryParameters,	CriteriaBuilder criteriaBuilder, Root<Message> root) 
	{
		
		List<Predicate> predicates = new ArrayList<Predicate>() ;
        if (queryParameters.containsKey("messageClassification")) {
            Integer messageClassificationId = Integer.valueOf(queryParameters.getFirst("messageClassification"));
            predicates.add(criteriaBuilder.equal(root.get("messageClassification").get("id"), messageClassificationId));
        }
        if (queryParameters.containsKey("messageType")) {
            Integer messageTypeId = Integer.valueOf(queryParameters.getFirst("messageType"));
            predicates.add(criteriaBuilder.equal(root.get("messageType").get("id"), messageTypeId));
        }
        if (queryParameters.containsKey("messageStatus")) {
            Integer messageStatusId = Integer.valueOf(queryParameters.getFirst("messageStatus"));
            predicates.add(criteriaBuilder.equal(root.get("messageStatus").get("id"), messageStatusId));
        }
        if (queryParameters.containsKey("dataChannel")) {
            Integer dataChannelId = Integer.valueOf(queryParameters.getFirst("dataChannel"));
            predicates.add(criteriaBuilder.equal(root.get("dataChannel").get("id"), dataChannelId));
        }
		if (queryParameters.containsKey("messageFrom")) {
            String messageFrom = queryParameters.getFirst("messageFrom");
            predicates.add(criteriaBuilder.equal(root.get("messageFrom"), messageFrom));
        }
		if (queryParameters.containsKey("messageTo")) {
            String messageTo = queryParameters.getFirst("messageTo");
            predicates.add(criteriaBuilder.equal(root.get("messageTo"), messageTo));
        }
		if (queryParameters.containsKey("messageSubject")) {
            String messageSubject = queryParameters.getFirst("messageSubject");
            predicates.add(criteriaBuilder.equal(root.get("messageSubject"), messageSubject));
        }
		if (queryParameters.containsKey("messageTxt")) {
            String messageTxt = queryParameters.getFirst("messageTxt");
            predicates.add(criteriaBuilder.equal(root.get("messageTxt"), messageTxt));
        }
		if (queryParameters.containsKey("msgAttachmentTy")) {
            String msgAttachmentTy = queryParameters.getFirst("msgAttachmentTy");
            predicates.add(criteriaBuilder.equal(root.get("msgAttachmentTy"), msgAttachmentTy));
        }
		if (queryParameters.containsKey("msgAttachmentFile")) {
            String msgAttachmentFile = queryParameters.getFirst("msgAttachmentFile");
            predicates.add(criteriaBuilder.equal(root.get("msgAttachmentFile"), msgAttachmentFile));
        }
		if (queryParameters.containsKey("inOutFg")) {
            String inOutFg = queryParameters.getFirst("inOutFg");
            predicates.add(criteriaBuilder.equal(root.get("inOutFg"), inOutFg));
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
