/**
 * 
 */
package com.nathanclaire.alantra.advice.service.process;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.advice.annotation.AdviceDataInputService;
import com.nathanclaire.alantra.advice.model.Advice;
import com.nathanclaire.alantra.advice.model.AdviceClassification;
import com.nathanclaire.alantra.advice.model.AdviceStatus;
import com.nathanclaire.alantra.advice.model.AdviceType;
import com.nathanclaire.alantra.advice.request.AdviceRequest;
import com.nathanclaire.alantra.advice.service.entity.AdviceClassificationService;
import com.nathanclaire.alantra.advice.service.entity.AdviceStatusService;
import com.nathanclaire.alantra.base.model.BaseEntity;
import com.nathanclaire.alantra.base.request.BaseRequest;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.ErrorCodes;
import com.nathanclaire.alantra.base.util.PropertyUtil;
import com.nathanclaire.alantra.businessdata.model.Currency;
import com.nathanclaire.alantra.businessdata.service.process.BusinessDataService;
import com.nathanclaire.alantra.customer.model.Customer;
import com.nathanclaire.alantra.customer.model.CustomerAccount;
import com.nathanclaire.alantra.customer.service.process.CustomerAccountService;
import com.nathanclaire.alantra.customer.service.process.CustomerService;
import com.nathanclaire.alantra.datasource.etl.service.BaseEntityDataInputService;
import com.nathanclaire.alantra.datasource.etl.service.EntityDataInputService;
import com.nathanclaire.alantra.datasource.etl.util.TableData;
import com.nathanclaire.alantra.datasource.model.DataChannel;

/**
 * @author Edward Banfa
 *
 */
@Stateless
@AdviceDataInputService
public class AdviceDataInputServiceImpl extends BaseEntityDataInputService implements
		EntityDataInputService {
	
	@Inject AdviceService adviceService;
	@Inject CustomerService customerService;
	@Inject CustomerAccountService accountService;
	@Inject BusinessDataService businessDataService;
	private Logger logger = LoggerFactory.getLogger(getClass());

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.service.BaseDataInputService#doDataInput(com.nathanclaire.alantra.datasource.model.DataChannel, com.nathanclaire.alantra.base.request.BaseRequest, com.nathanclaire.alantra.base.request.BaseRequest, com.nathanclaire.alantra.datasource.etl.TableDataLite)
	 */
	@Override
	public BaseEntity doDataInput(DataChannel channel,	BaseRequest primaryEntity, 
			BaseRequest secondaryEntity, TableData tableData) throws ApplicationException 
	{
		logger.debug("Processing data input request");
		try {
			PropertyUtil.initializeBaseFields(primaryEntity);
			AdviceRequest adviceRequest = (AdviceRequest) primaryEntity;
			// Resolve dependencies
			Customer customer = customerService.getCustomer(tableData.getCustomerId());
			Currency currency = businessDataService.getCurrency(adviceRequest.getCurrencyText());
			AdviceStatus adviceStatus = adviceService.getAdviceStatus(AdviceStatusService.ACTIVE);
			AdviceType adviceType = adviceService.getAdviceType(adviceRequest.getAdviceTypeText());
			CustomerAccount account = accountService.getCustomerAccount(customer, adviceRequest.getCustomerAccountText());
			AdviceClassification adviceClassification = adviceService.getAdviceClassification(AdviceClassificationService.NON_CYCLIC_ADVICE);
			// Create the advice
			Advice advice = adviceService.createAdvice(adviceType, adviceClassification, adviceStatus, channel, 
					customer, account, currency, adviceRequest.getAmount(), adviceRequest.getChequeNo(), adviceRequest.getCardNo());
			return advice;
		} catch (Exception e) {
			logger.error("Error processing message data input: {}", e.getMessage());
			throw new ApplicationException(ErrorCodes.ADIS_DATA_INPUT_ERROR, e.getMessage());
		}
	}

}
