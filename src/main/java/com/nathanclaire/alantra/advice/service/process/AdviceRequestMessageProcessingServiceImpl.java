/**
 * 
 */
package com.nathanclaire.alantra.advice.service.process;

import java.math.BigDecimal;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.nathanclaire.alantra.advice.model.Advice;
import com.nathanclaire.alantra.advice.model.AdviceClassification;
import com.nathanclaire.alantra.advice.model.AdviceRequestMessage;
import com.nathanclaire.alantra.advice.model.AdviceRequestMessageStatus;
import com.nathanclaire.alantra.advice.model.AdviceStatus;
import com.nathanclaire.alantra.advice.model.AdviceType;
import com.nathanclaire.alantra.advice.request.AdviceRequest;
import com.nathanclaire.alantra.advice.request.AdviceRequestMessageRequest;
import com.nathanclaire.alantra.advice.service.entity.AdviceClassificationService;
import com.nathanclaire.alantra.advice.service.entity.AdviceRequestMessageService;
import com.nathanclaire.alantra.advice.service.entity.AdviceRequestMessageStatusService;
import com.nathanclaire.alantra.advice.service.entity.AdviceService;
import com.nathanclaire.alantra.advice.service.entity.AdviceStatusService;
import com.nathanclaire.alantra.advice.service.entity.AdviceTypeService;
import com.nathanclaire.alantra.base.service.process.BaseProcessService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtils;
import com.nathanclaire.alantra.businessdata.model.Currency;
import com.nathanclaire.alantra.businessdata.service.entity.CurrencyService;
import com.nathanclaire.alantra.customer.model.Account;
import com.nathanclaire.alantra.customer.model.Customer;
import com.nathanclaire.alantra.customer.model.CustomerAccount;
import com.nathanclaire.alantra.customer.service.entity.CustomerAccountService;

/**
 * @author Edward Banfa 
 *
 */
@Stateless
public class AdviceRequestMessageProcessingServiceImpl extends
		BaseProcessService implements AdviceRequestMessageProcessingService {

	@Inject AdviceService adviceService;
	@Inject CurrencyService currencyService;
	@Inject AdviceTypeService adviceTypeService;
	@Inject CustomerAccountService accountService;
	@Inject AdviceStatusService adviceStatusService;
	@Inject AdviceClassificationService classificationService;
	@Inject AdviceRequestMessageService requestMessageService;
	@Inject AdviceRequestMessageStatusService adviceRequestMessageStatusService;

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.process.AdviceRequestMessageProcessingService#getAdviceRequestMessageStatus(java.lang.String)
	 */
	@Override
	public AdviceRequestMessageStatus getAdviceRequestMessageStatus(String statusCode) throws ApplicationException {
		AdviceRequestMessageStatus status = adviceRequestMessageStatusService.findByCode(statusCode);
		if(status == null)
			throw new ApplicationException(AdviceRequestMessageDataInputService.ADVICE_STATUS_NOT_FOUND);
		return status;
	}

	@Override
	public AdviceRequestMessage findAdviceRequestMessage(String code) throws ApplicationException {
		return requestMessageService.findByCode(code);
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.process.AdviceProcessingService#createAdviceRequest(com.nathanclaire.alantra.customer.model.Customer, java.lang.String, java.util.List, java.util.List, java.math.BigDecimal, java.lang.String, java.lang.String, com.nathanclaire.alantra.advice.model.AdviceType, java.lang.Integer)
	 */
	@Override
	public AdviceRequestMessage createAdviceRequest(Customer customer, String sourceAddress, Account account, 
			Currency currencysInAdviceText, BigDecimal amount, String chequeNo, String cardNo, AdviceType adviceType, 
			Integer dataChannelId, String adviceText) throws ApplicationException 
	{
		AdviceRequestMessageRequest requestMessage = new AdviceRequestMessageRequest();
		PropertyUtils.initializeBaseFields(requestMessage);
		requestMessage.setAmount(amount);
		requestMessage.setChequeNo(chequeNo);
		requestMessage.setCardNo(cardNo);
		requestMessage.setAdviceTyTxt(adviceType.getCode());
		requestMessage.setAdviceTxt(adviceText);
		requestMessage.setDataChannelId(dataChannelId);
		requestMessage.setCustomerId(customer.getId());
		// Get the customers default account
		requestMessage.setAccountNo(account.getAccountNo());
		// Resolve the currency to use
		requestMessage.setCurrencyCd(currencysInAdviceText.getCode());
		// Set the advice status
		requestMessage.setAdviceRequestMessageStatusId(
				getAdviceRequestMessageStatus(AdviceRequestMessageStatusService.UNPROCESSED_ADVICE_CODE).getId());
		// Create the advice
		AdviceRequestMessage message = requestMessageService.create(requestMessage);
		if(message == null)
			throw new ApplicationException(COULD_NOT_CREATE_ADVICE_REQUEST);
		return message;
	}

	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.process.AdviceProcessingService#createAdvice(com.nathanclaire.alantra.advice.model.AdviceRequestMessage)
	 */
	@Override
	public Advice createAdvice(AdviceRequestMessage requestMessage)  throws ApplicationException 
	{
		// NOTE: You cannot create a cycle advice from an advice request message
		AdviceRequest adviceRequest = new AdviceRequest();
		adviceRequest.setAdviceRequestMessageId(requestMessage.getId());
		adviceRequest.setName(requestMessage.getName());
		PropertyUtils.initializeBaseFields(adviceRequest);
		// 1. Advice account
		CustomerAccount account = accountService.findByCode(requestMessage.getAccountNo());
		if(account == null)
			throw new ApplicationException(CUSTOMER_ACCOUNT_NOT_FOUND);
		adviceRequest.setCustomerAccountId(account.getId());
		adviceRequest.setCode(account.getCode());
		// 2. Advice type
		AdviceType adviceType = adviceTypeService.findByCode(requestMessage.getAdviceTyTxt());
		if(adviceType == null)
			throw new ApplicationException(ADVICE_TYPE_NOT_FOUND);
		adviceRequest.setAdviceTypeId(adviceType.getId());
		// 3. Advice status
		AdviceStatus adviceStatus = adviceStatusService.findByCode(AdviceStatusService.UNPROCESSED_ADVICE_CODE);
		if(adviceStatus == null)
			throw new ApplicationException(ADVICE_STATUS_NOT_FOUND);
		adviceRequest.setAdviceStatusId(adviceStatus.getId());
		// 4. Advice customer
		Customer customer = account.getCustomer();
		if(customer == null)
			throw new ApplicationException(CUSTOMER_NOT_FOUND);
		adviceRequest.setCustomerId(customer.getId());
		// 5. Advice currency
		Currency currency = currencyService.findByCode(requestMessage.getCurrencyCd());
		if(currency == null)
			throw new ApplicationException(ADVICE_CURRENCY_NOT_FOUND);
		adviceRequest.setCurrencyId(currency.getId());
		// 6. Advice classification
		AdviceClassification adviceClassification = classificationService.findByCode(AdviceClassificationService.NON_CYCLIC_ADVICE);
		if(adviceClassification == null)
			throw new ApplicationException(ADVICE_CLASS_NOT_FOUND);
		adviceRequest.setAdviceClassificationId(adviceClassification.getId());
		// 7. Amount
		adviceRequest.setAmount(requestMessage.getAmount());
		// 8. Advice text
		adviceRequest.setAdviceTxt(requestMessage.getAdviceTxt());
		// 9. Card number
		adviceRequest.setCardNo(requestMessage.getCardNo());
		// 10. Cheque number
		adviceRequest.setChequeNo(requestMessage.getChequeNo());
		if(adviceService.findByCode(adviceRequest.getCode()) != null)
			throw new ApplicationException(ADVICE_ALREADY_EXIST);
		return adviceService.create(adviceRequest);
	}
}
