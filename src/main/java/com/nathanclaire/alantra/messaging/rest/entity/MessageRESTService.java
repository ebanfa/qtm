/**
 * 
 */
package com.nathanclaire.alantra.messaging.rest.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.application.model.ApplicationEntityField;
import com.nathanclaire.alantra.application.response.ApplicationActivityResponse;
import com.nathanclaire.alantra.application.response.ApplicationEntityFieldResponse;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityFieldService;
import com.nathanclaire.alantra.base.response.EditActivityResponse;
import com.nathanclaire.alantra.base.response.ListActivityResponse;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.rest.BaseActivityRESTService;
import com.nathanclaire.alantra.base.rest.ui.UIListItem;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.StringUtil;
import com.nathanclaire.alantra.customer.model.Customer;
import com.nathanclaire.alantra.customer.model.CustomerCategory;
import com.nathanclaire.alantra.customer.model.CustomerClassification;
import com.nathanclaire.alantra.customer.model.CustomerType;
import com.nathanclaire.alantra.customer.service.entity.CustomerCategoryService;
import com.nathanclaire.alantra.customer.service.entity.CustomerClassificationService;
import com.nathanclaire.alantra.customer.service.entity.CustomerService;
import com.nathanclaire.alantra.customer.service.entity.CustomerTypeService;
import com.nathanclaire.alantra.messaging.model.Message;
import com.nathanclaire.alantra.messaging.request.MessageRequest;
import com.nathanclaire.alantra.messaging.request.SendMessageRequest;
import com.nathanclaire.alantra.messaging.response.MessageResponse;
import com.nathanclaire.alantra.messaging.service.entity.MessageService;
import com.nathanclaire.alantra.messaging.service.process.MessagingModuleService;
import com.nathanclaire.alantra.security.model.SystemUser;
import com.nathanclaire.alantra.security.service.entity.SystemUserService;

/**
 * @author administrator
 *
 */
@Path("/message")
@Stateless
public class MessageRESTService extends BaseActivityRESTService<MessageResponse, MessageRequest> 
{
	private static final String CLASSIFICATION = "CLASSIFICATION";

	private static final String CATEGORY = "CATEGORY";

	private static final String TYPE = "TYPE";

	private static final String ADDRESS = "ADDRESS";

	@Inject
	MessageService messageService;
	
	@Inject MessagingModuleService messagingModuleService;
	@Inject SystemUserService userService;
	@Inject CustomerService customerService;
	@Inject CustomerTypeService typeService;
	@Inject CustomerCategoryService categoryService;
	@Inject CustomerClassificationService classificationService;
	
	@Inject 
	ApplicationEntityFieldService applicationEntityFieldService;
	
	private Logger logger = LoggerFactory.getLogger(MessageRESTService.class);

    @GET
	@Path("/target")
    @Produces(MediaType.APPLICATION_JSON)
	public List<UIListItem> getTargetModeItems(@QueryParam ("targetMode") String targetMode)
	{
		logger.debug("The query param: {}", targetMode);
		if(StringUtil.isValidString(targetMode))
		{
			try {
				if(targetMode.equals(CATEGORY))
					return this.toCategoryUIListItem(categoryService.findAll(null));
				else if (targetMode.equals(TYPE))
					return this.toTypeUIListItem(typeService.findAll(null));
				else if (targetMode.equals(CLASSIFICATION))
					return this.toClassificationUIListItem(classificationService.findAll(null));
			} catch (ApplicationException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
    
    @POST
	@Path("/sendMessage")
    @Consumes(MediaType.APPLICATION_JSON)
	public MessageResponse sendMessage(SendMessageRequest messageRequest)
	{
    	// 1. Get the transport type
		String transport = messageRequest.getTransport();
    	// 2. Get the target mode
		String targetMode = messageRequest.getTargetMode();
    	// 3. Get the CC list
		List<Integer> idOfUsers = Arrays.asList(messageRequest.getCcList());
		//List<Integer> idOfUsers = null;
		try {
			List<SystemUser> usersToReceiveMsg = userService.findByIds(idOfUsers);
			List<Customer> customersToReceiveMsg = null;
			// 4. If we are sending by address then 
			if(targetMode.equals(ADDRESS))
				customersToReceiveMsg = customerService.findByIds(Arrays.asList(messageRequest.getToList()));
			 //customersToReceiveMsg = customerService.findByIds(null);
			else if(targetMode.equals(TYPE))
				customersToReceiveMsg = customerService.findByType(messageRequest.getType());
			else if(targetMode.equals(CATEGORY))
				customersToReceiveMsg = customerService.findByCategory(messageRequest.getCategory());
			else if(targetMode.equals(TYPE))
				customersToReceiveMsg = customerService.findByClassification(messageRequest.getClassification());
			messagingModuleService.sendMessageToCustomers(transport, 
					customersToReceiveMsg, messageRequest.getSubject(), messageRequest.getText());
			messagingModuleService.sendMessageToUsers(transport, 
					usersToReceiveMsg, messageRequest.getSubject(), messageRequest.getText());
		} 
		catch (ApplicationException e) 
		{
			EditActivityResponse<MessageResponse> response = this.getEditActivityResponse(null);
			processActivityResponseException(response, e);
			logger.error(e.getMessage());
		}
    	return null;
	}
    
	private List<UIListItem> toClassificationUIListItem(List<CustomerClassification> entities) {
		List<UIListItem> uiListItems = new ArrayList<UIListItem>();
		for(CustomerClassification entity: entities)
			uiListItems.add(new UIListItem(entity.getCode(), entity.getName()));
		return uiListItems;
	}

	private List<UIListItem> toTypeUIListItem(List<CustomerType> entities) {
		List<UIListItem> uiListItems = new ArrayList<UIListItem>();
		for(CustomerType entity: entities)
			uiListItems.add(new UIListItem(entity.getCode(), entity.getName()));
		return uiListItems;
	}
	/**
	 * @param entities
	 * @return
	 */
	private List<UIListItem> toCategoryUIListItem(List<CustomerCategory> entities) 
	{
		List<UIListItem> uiListItems = new ArrayList<UIListItem>();
		for(CustomerCategory entity: entities)
			uiListItems.add(new UIListItem(entity.getCode(), entity.getName()));
		return uiListItems;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#populateListActivityResponse(com.nathanclaire.alantra.messaging.response.MessageResponse, com.nathanclaire.alantra.base.response.ListActivityResponse, javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected ListActivityResponse<MessageResponse> populateListActivityResponse(
			ApplicationActivityResponse activity, ListActivityResponse<MessageResponse> response,
			MultivaluedMap<String, String> queryParameters) throws ApplicationException 
	{
		// Load the fields for the Message entity
		List<ApplicationEntityFieldResponse> responseFields = new ArrayList<ApplicationEntityFieldResponse>();
		List<ApplicationEntityField> entityFields = messageService.getEntityFields();
		for(ApplicationEntityField entityField:entityFields)
		{
			responseFields.add(applicationEntityFieldService.convertModelToResponse(entityField));
		}
		response.setFields(responseFields);
		// Load the list of Message's
		List<MessageResponse> dataItems = new ArrayList<MessageResponse>();
		for (Message item:messageService.findAll(queryParameters))
		{
			dataItems.add(messageService.convertModelToResponse(item));
		}
		response.setData(dataItems);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getInstance(java.lang.Integer)
	 */
	@Override
	protected EditActivityResponse<MessageResponse> populateEditActivityResponse(
			Integer id,	ApplicationActivityResponse activity, EditActivityResponse<MessageResponse> response) 
					throws ApplicationException {
		// Load the fields for the Message entity
		List<ApplicationEntityField> entityFields = messageService.getEntityFields();
		List<ApplicationEntityFieldResponse> responseFields = new ArrayList<ApplicationEntityFieldResponse>();
		// Convert the entity fields into response fields
		for(ApplicationEntityField entityField:entityFields)
		{
			responseFields.add(applicationEntityFieldService.convertModelToResponse(entityField));
		}
		response.setFields(responseFields);
		response.setRelatedEntitiesListData(messageService.relatedEntitesToListItems());
		if(id != null)
			response.setEntity(messageService.convertModelToResponse(messageService.findById(id)));
		// The response will now have the id of the embedded entity (WHY)
		if(response.getEntity() != null)
			response.setId(response.getEntity().getId());
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#prepareRelatedEntitiesListItems(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected Map<String, List<ListItemResponse>> prepareRelatedEntitiesListItems(MultivaluedMap<String, String> multivaluedMap) 
				   throws ApplicationException {
		return messageService.relatedEntitesToListItems();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#saveEditedEntityInstance(java.lang.Object)
	 */
	@Override
	protected EditActivityResponse<MessageResponse> saveEntityInstance(
			MessageRequest entityInstance) throws ApplicationException {
		Message message = messageService.create(entityInstance);
		return this.getEditActivityResponse(message.getId());
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#saveEditedEntityInstance(java.lang.Object)
	 */
	@Override
	protected EditActivityResponse<MessageResponse> saveEditedEntityInstance(
			MessageRequest entityInstance) throws ApplicationException {
		Message message = messageService.update(entityInstance);
		return this.getEditActivityResponse(message.getId());
	}
	
	@Override
	protected ListActivityResponse<MessageResponse> deleteEntityInstances(
			List<Integer> idsOfEntitiesToDelete) throws ApplicationException {
		for(Integer id: idsOfEntitiesToDelete)
		{
			 messageService.delete(id);
		}
		return this.getListActivityResponse(null);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getListActivityCode()
	 */
	@Override
	protected String getListActivityCode() throws ApplicationException {
		return messageService.getListActivityCode();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseActivityRESTService#getEditActivityCode()
	 */
	@Override
	protected String getEditActivityCode() throws ApplicationException {
		return messageService.getEditActivityCode();
	}

}
