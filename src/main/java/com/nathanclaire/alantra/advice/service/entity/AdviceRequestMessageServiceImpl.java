/**
 * 
 */
package com.nathanclaire.alantra.advice.service.entity;

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

import com.nathanclaire.alantra.advice.model.AdviceRequestMessage;
import com.nathanclaire.alantra.customer.model.Customer;
import com.nathanclaire.alantra.datasource.model.DataChannel;
import com.nathanclaire.alantra.advice.model.AdviceRequestMessageStatus;
import com.nathanclaire.alantra.advice.request.AdviceRequestMessageRequest;
import com.nathanclaire.alantra.advice.response.AdviceRequestMessageResponse;
import com.nathanclaire.alantra.customer.service.entity.CustomerService;
import com.nathanclaire.alantra.datasource.service.entity.DataChannelService;
import com.nathanclaire.alantra.advice.service.entity.AdviceRequestMessageStatusService;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtils;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class AdviceRequestMessageServiceImpl 
	extends BaseEntityServiceImpl<AdviceRequestMessage, AdviceRequestMessageResponse, AdviceRequestMessageRequest> 
	implements AdviceRequestMessageService
{
	private static final String LIST_ITEM_CUSTOMER = "customer";
	private static final String LIST_ITEM_DATACHANNEL = "dataChannel";
	private static final String LIST_ITEM_ADVICEREQUESTMESSAGESTATUS = "adviceRequestMessageStatus";
	private static final String ENTITY_NAME = "AdviceRequestMessage";
	private static final String LIST_ACTIVITY_CODE = "LIST_ADVICE_ADVICEREQUESTMESSAGE";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_ADVICE_ADVICEREQUESTMESSAGE";
	
	private Logger logger = LoggerFactory.getLogger(AdviceRequestMessageServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	@Inject
	CustomerService  customerService;
	@Inject
	DataChannelService  dataChannelService;
	@Inject
	AdviceRequestMessageStatusService  adviceRequestMessageStatusService;
	
	/**
	 * @param entityClass
	 */
	public AdviceRequestMessageServiceImpl() {
		super(AdviceRequestMessage.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.AdviceRequestMessage#findById(java.lang.Integer)
	 */
	@Override
	public AdviceRequestMessage findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.AdviceRequestMessage#findByCode(java.lang.String)
	 */
	@Override
	public AdviceRequestMessage findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.AdviceRequestMessage#findByName(java.lang.String)
	 */
	@Override
	public AdviceRequestMessage findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.AdviceRequestMessage#findAll(java.util.Map)
	 */
	@Override
	public List<AdviceRequestMessage> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.AdviceRequestMessage#createAdviceRequestMessage(com.nathanclaire.alantra.advice.rest.request.ServiceRequest)
	 */
	@Override
	public AdviceRequestMessage create(AdviceRequestMessageRequest adviceRequestMessageRequest) throws ApplicationException {
		return createInstance(adviceRequestMessageRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.AdviceRequestMessage#deleteAdviceRequestMessage(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.AdviceRequestMessage#updateAdviceRequestMessage(com.nathanclaire.alantra.advice.rest.request.ServiceRequest)
	 */
	@Override
	public AdviceRequestMessage update(AdviceRequestMessageRequest adviceRequestMessageRequest) throws ApplicationException {
		return updateInstance(adviceRequestMessageRequest);
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
		List<ListItemResponse> customers = customerService.asListItem();
		List<ListItemResponse> dataChannels = dataChannelService.asListItem();
		List<ListItemResponse> adviceRequestMessageStatuss = adviceRequestMessageStatusService.asListItem();
    	
		listItems.put(LIST_ITEM_CUSTOMER, customers); 
		listItems.put(LIST_ITEM_DATACHANNEL, dataChannels); 
		listItems.put(LIST_ITEM_ADVICEREQUESTMESSAGESTATUS, adviceRequestMessageStatuss); 
		return listItems;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#asListItem()
	 */
	@Override
	public List<ListItemResponse> asListItem() throws ApplicationException {
		List<ListItemResponse> listItems = new ArrayList<ListItemResponse>();
		queryParameters.clear();
		for(AdviceRequestMessage advicerequestmessage: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(advicerequestmessage.getId(), advicerequestmessage.getCode(), advicerequestmessage.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public AdviceRequestMessage convertRequestToModel(AdviceRequestMessageRequest adviceRequestMessageRequest) 
     throws ApplicationException {
		AdviceRequestMessage adviceRequestMessage = new AdviceRequestMessage();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(adviceRequestMessageRequest, adviceRequestMessage, allowedEntityFields);
    	//Process many to one relationships
    	if (adviceRequestMessageRequest.getCustomerId() != null)
    	{
    		Customer customer = getEntityManager().find(Customer.class, adviceRequestMessageRequest.getCustomerId());
    		adviceRequestMessage.setCustomer(customer);
    	}
    	if (adviceRequestMessageRequest.getDataChannelId() != null)
    	{
    		DataChannel dataChannel = getEntityManager().find(DataChannel.class, adviceRequestMessageRequest.getDataChannelId());
    		adviceRequestMessage.setDataChannel(dataChannel);
    	}
    	if (adviceRequestMessageRequest.getAdviceRequestMessageStatusId() != null)
    	{
    		AdviceRequestMessageStatus adviceRequestMessageStatus = getEntityManager().find(AdviceRequestMessageStatus.class, adviceRequestMessageRequest.getAdviceRequestMessageStatusId());
    		adviceRequestMessage.setAdviceRequestMessageStatus(adviceRequestMessageStatus);
    	}
		return adviceRequestMessage;
	}
	
	@Override
	public AdviceRequestMessageResponse convertModelToResponse(AdviceRequestMessage model) throws ApplicationException {
		if (model == null) return null;
		AdviceRequestMessageResponse adviceRequestMessageResponse = new AdviceRequestMessageResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(model, adviceRequestMessageResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		if(model.getCustomer() != null)
			adviceRequestMessageResponse.setCustomerId(model.getCustomer().getId());
			adviceRequestMessageResponse.setCustomerText(model.getCustomer().getName());
		if(model.getDataChannel() != null)
			adviceRequestMessageResponse.setDataChannelId(model.getDataChannel().getId());
			adviceRequestMessageResponse.setDataChannelText(model.getDataChannel().getName());
		if(model.getAdviceRequestMessageStatus() != null)
			adviceRequestMessageResponse.setAdviceRequestMessageStatusId(model.getAdviceRequestMessageStatus().getId());
			adviceRequestMessageResponse.setAdviceRequestMessageStatusText(model.getAdviceRequestMessageStatus().getName());
		return adviceRequestMessageResponse;
	}
}
