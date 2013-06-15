/**
 * 
 */
package com.nathanclaire.alantra.advice.service.process;

import java.util.Date;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.nathanclaire.alantra.advice.model.AdviceRequestMessageStatus;
import com.nathanclaire.alantra.advice.request.AdviceRequestMessageRequest;
import com.nathanclaire.alantra.advice.service.entity.AdviceRequestMessageService;
import com.nathanclaire.alantra.advice.service.entity.AdviceRequestMessageStatusService;
import com.nathanclaire.alantra.advice.service.entity.AdviceTypeService;
import com.nathanclaire.alantra.base.model.BaseEntity;
import com.nathanclaire.alantra.base.request.BaseRequest;
import com.nathanclaire.alantra.base.service.process.BaseProcessService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtils;
import com.nathanclaire.alantra.base.util.StringUtil;
import com.nathanclaire.alantra.businessdata.service.entity.CurrencyService;
import com.nathanclaire.alantra.customer.model.CustomerAccount;
import com.nathanclaire.alantra.customer.service.entity.CustomerAccountService;
import com.nathanclaire.alantra.datasource.etl.TableData;

/**
 * @author Edward Banfa 
 *
 */
@Stateless
public class AdviceRequestMessageDataInputServiceImpl extends BaseProcessService implements AdviceRequestMessageDataInputService {
	
	@Inject CurrencyService currencyService;
	@Inject AdviceTypeService adviceTypeService;
	@Inject CustomerAccountService accountService;
	@Inject AdviceRequestMessageService adviceRequestMessageService;
	@Inject AdviceRequestMessageStatusService adviceRequestMessageStatusService;

	@Override
	public BaseEntity processDataInput(BaseRequest primaryEntityRequest, BaseRequest secEntityRequest, TableData tableData) throws ApplicationException {
		PropertyUtils.initializeBaseFields(primaryEntityRequest);
		AdviceRequestMessageRequest adviceRequest = (AdviceRequestMessageRequest) primaryEntityRequest;
		adviceRequest.setDataChannelId(getDataImportService(tableData.getSourceServiceCode()).getId());
		adviceRequest.setAdviceRequestMessageStatusId(getAdviceStatus(AdviceRequestMessageStatusService.UNPROCESSED_ADVICE_CODE).getId());
		CustomerAccount account = getAdviceAccount(adviceRequest.getAccountNo());
		if(StringUtil.isValidString(adviceRequest.getChequeNo()))
			adviceRequest.setCode(account.getName() + adviceRequest.getChequeNo());
		if(StringUtil.isValidString(adviceRequest.getCardNo()))
			adviceRequest.setCode(account.getName() + adviceRequest.getCardNo());
		adviceRequest.setSourceAddress(tableData.getSourceChannelText());
		//adviceRequest.setName(account.getName());
		adviceRequest.setCustomerId(account.getId());
		if(adviceRequestMessageService.findByCode(adviceRequest.getCode()) != null)
			throw new ApplicationException(ADVICE_REQUEST_ALREADY_EXIST);
		return adviceRequestMessageService.create(adviceRequest);
	}

	/**
	 * @param unprocessedAdviceCode
	 * @return
	 * @throws ApplicationException
	 */
	private AdviceRequestMessageStatus getAdviceStatus(String unprocessedAdviceCode) throws ApplicationException {
		AdviceRequestMessageStatus status = adviceRequestMessageStatusService.findByCode(unprocessedAdviceCode);
		if(status == null)
			throw new ApplicationException(AdviceRequestMessageDataInputService.ADVICE_STATUS_NOT_FOUND);
		return status;
	}

	/**
	 * @param accountNo
	 * @return
	 * @throws ApplicationException
	 */
	private CustomerAccount getAdviceAccount(String accountNo) throws ApplicationException {
		CustomerAccount account = accountService.findByCode(accountNo);
		if(account == null) 
			throw new ApplicationException(AdviceRequestMessageDataInputService.CUSTOMER_ACCOUNT_DOES_NOT_EXIST);
		return account;
	}

}
