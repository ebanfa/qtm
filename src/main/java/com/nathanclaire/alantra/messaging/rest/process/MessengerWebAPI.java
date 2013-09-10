/**
 * 
 */
package com.nathanclaire.alantra.messaging.rest.process;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.DateUtil;
import com.nathanclaire.alantra.base.util.PropertyUtil;
import com.nathanclaire.alantra.base.util.StringUtil;
import com.nathanclaire.alantra.customer.model.Customer;
import com.nathanclaire.alantra.customer.service.process.CustomerService;
import com.nathanclaire.alantra.datasource.model.DataChannel;
import com.nathanclaire.alantra.datasource.service.entity.DataChannelEntityService;
import com.nathanclaire.alantra.messaging.annotation.messenger.SMSHTTPMessenger;
import com.nathanclaire.alantra.messaging.messenger.MessengerService;
import com.nathanclaire.alantra.messaging.request.MessageRequest;
import com.nathanclaire.alantra.messaging.service.entity.MessageStatusService;
import com.nathanclaire.alantra.messaging.util.MessageLite;

/**
 * @author edward
 *
 */
@Path("/sms")
@Stateless
public class MessengerWebAPI {
	
	@Inject @SMSHTTPMessenger MessengerService messengerService;
	@Inject DataChannelEntityService dataChannelEntityService;
	@Inject CustomerService customerService;
	private Logger logger = LoggerFactory.getLogger(MessengerWebAPI.class);
	
	/**
     * <p>
     *     A method for retrieving individual entity instances.
     * </p>
     * @param id entity id
     * @return
     */
    @GET
    @Path("/receive")
    @Produces(MediaType.APPLICATION_JSON)
    public Response receive(@QueryParam("channel") String dataChannelCode, @QueryParam("from") String from, @QueryParam("to") String to, @QueryParam("messageText") String messageText) {
    	try {
    		if(!StringUtil.isValidString(dataChannelCode) | !StringUtil.isValidString(from) | !StringUtil.isValidString(to))
    			return Response.status(Status.BAD_REQUEST).build();
    		// 1. Verify the dataChannel
			DataChannel channel = dataChannelEntityService.findByCode(dataChannelCode);
			if(channel == null){
				logger.debug("Invalid channel specified. Channel not found");
				return Response.status(Status.FORBIDDEN).build();
			}
			// 2. Verify the sender is a valid customer
			Customer customer = null;//customerService.findCustomerByPhone(from);
			if(customer == null){
				logger.debug("Invalid customer specified. Customer with source address {} not found", from);
				return Response.status(Status.FORBIDDEN).build();
			}
			// 3. If the the channel is used for either transactions then try and desern the message type
			/*if(StringUtil.flagToBoolean(channel.getAdviceFg());
			else if(StringUtil.flagToBoolean(channel.getTransactionFg());
			else
				;*/
			// 4. Create an message request and call service to persist
			if(!StringUtil.isValidString(messageText)){
				logger.debug("Invalid message text specified");
				return Response.status(Status.BAD_REQUEST).build();
			}
			//MessageApplication messageApplication = messageTextProcessingService.getMessageApplication(messageText);
			MessageRequest messageRequest = new MessageRequest();
			PropertyUtil.initializeBaseFields(messageRequest);
			//messageRequest.setMessageApplicationId(messageApplication.getId());
			//messageRequest.setMessageClassificationId(classificationService.getMessageClassification(channel).getId());
			//messageRequest.setMessageStatusId(classificationService.getMessageStatus(MessageStatusService.CUSTOMER_MESSAGE_RECEIVED).getId());
			//messageRequest.setMessageTypeId(classificationService.getMessageType(channel).getId());
			messageRequest.setDataChannelId(channel.getId());
			messageRequest.setCode(DateUtil.getCurrentTimeInMilliSeconds().toString());
			messageRequest.setMessageTo(to);
			messageRequest.setMessageFrom(from);
			messageRequest.setMessageTxt(messageText);
			//messagingModuleService.createMessage(messageRequest);
		} catch (ApplicationException e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
    	return Response.status(Status.OK).build();
    }
    /**
     * <p>
     *     A method for retrieving individual entity instances.
     * </p>
     * @param id entity id
     * @return
     */
    @GET
    @Path("/send")
    @Produces(MediaType.APPLICATION_JSON)
    public Response send(@QueryParam("channel") String dataChannelCode, @QueryParam("from") String from, @QueryParam("to") String to, @QueryParam("messageText") String messageText) {
    	try {
    		if(!StringUtil.isValidString(dataChannelCode) | !StringUtil.isValidString(from) | !StringUtil.isValidString(to))
    			return Response.status(Status.BAD_REQUEST).build();
    		// 1. Verify the dataChannel
			DataChannel channel = dataChannelEntityService.findByCode(dataChannelCode);
			if(channel == null){
				logger.debug("Invalid channel specified. Channel not found");
				return Response.status(Status.FORBIDDEN).build();
			}
			// 2. Verify the sender is a valid customer
			Customer customer = null;//customerService.findCustomerByPhone(from);
			if(customer == null){
				logger.debug("Invalid customer specified. Customer with source address {} not found", from);
				return Response.status(Status.FORBIDDEN).build();
			}
			// 3. Call backend to send message
			MessageLite messageLite = new MessageLite();
			messageLite.setMessageTo(to);
			messageLite.setMessageFrom(from);
			messageLite.setMessageBody(messageText);
			messengerService.sendMessage(channel, messageLite);
			
		} catch (ApplicationException e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
    	return Response.status(Status.OK).build();
    }

}
