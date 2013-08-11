/**
 * 
 */
package com.nathanclaire.alantra.advice.service.process;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.advice.model.AdviceRequestMessage;
import com.nathanclaire.alantra.advice.model.AdviceType;
import com.nathanclaire.alantra.advice.request.AdviceRequestMessageRequest;
import com.nathanclaire.alantra.base.model.BaseEntity;
import com.nathanclaire.alantra.base.request.BaseRequest;
import com.nathanclaire.alantra.base.service.process.BaseProcessService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtils;
import com.nathanclaire.alantra.businessdata.model.Currency;
import com.nathanclaire.alantra.businessdata.service.process.BusinessDataProcessingService;
import com.nathanclaire.alantra.customer.model.Account;
import com.nathanclaire.alantra.customer.model.Customer;
import com.nathanclaire.alantra.customer.service.process.CustomerProcessingService;
import com.nathanclaire.alantra.datasource.etl.TableDataLite;
import com.nathanclaire.alantra.datasource.model.DataChannel;

/**
 * @author Edward Banfa 
 *
 */
@Stateless
public class AdviceRequestMessageDataInputServiceImpl extends BaseProcessService implements AdviceRequestMessageDataInputService {
	
	//@Inject @AdviceRequestMessageCreatedEvent Event<AdviceRequestMessageCreationEvent> requestMessageCreatedEvent;
	//@Inject @AdviceRequestMessageCreationFailedEvent Event<AdviceRequestMessageCreationEvent> requestMessageCreatedFailedEvent;

	@Inject CustomerProcessingService customerService;
	@Inject AdviceProcessingService adviceProcessingService;
	@Inject BusinessDataProcessingService businessProcessingService;
	private Logger logger = LoggerFactory.getLogger(AdviceRequestMessageDataInputServiceImpl.class);
	

	@Override
	public BaseEntity processDataInput(BaseRequest primaryEntityRequest, BaseRequest secEntityRequest, TableDataLite tableDataLite) 
			throws ApplicationException {
		PropertyUtils.initializeBaseFields(primaryEntityRequest);		
		AdviceRequestMessageRequest adviceRequest = (AdviceRequestMessageRequest) primaryEntityRequest;
		// Verify that the advice request does not exist
		AdviceRequestMessage adviceRequestMessage = adviceProcessingService.findAdviceRequestMessage(adviceRequest.getCode());
		if(adviceRequestMessage != null){
			flagDataInputRejected(tableDataLite);
			//requestMessageCreatedFailedEvent.fire(new AdviceRequestMessageCreationEvent(adviceRequestMessage.getId()));
			return adviceRequestMessage;
		}
		// Currency, customer, account, data channel and friends
		Currency currency = businessProcessingService.getCurrency(adviceRequest.getCurrencyCd());
		Account account = customerService.getAccount(adviceRequest.getAccountNo());
		DataChannel dataChannel = getDataImportService(tableDataLite.getSourceServiceCode());
		Customer customer = customerService.getFirstCustomerWithAccount(account.getAccountNo());
		AdviceType adviceType = adviceProcessingService.getAdviceTypeInAdviceText(adviceRequest.getAdviceTxt());
		// Create the advice request
		try {
			adviceRequestMessage = 
					adviceProcessingService.createAdviceRequest(customer, dataChannel.getIpAddr(), account, currency, 
							adviceRequest.getAmount(), adviceRequest.getChequeNo(), adviceRequest.getCardNo(), 
							adviceType, dataChannel.getId(), adviceRequest.getAdviceTxt());
			// Update job stats
			flagDataInputAccepted(tableDataLite);
		} catch (Exception e) {
			flagDataInputRejected(tableDataLite);
			logger.error(e.getMessage());
		}
		return adviceRequestMessage;
	}
	
}
