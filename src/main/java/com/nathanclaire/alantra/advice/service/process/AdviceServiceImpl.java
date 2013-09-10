/**
 * 
 */
package com.nathanclaire.alantra.advice.service.process;

import java.math.BigDecimal;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.advice.model.Advice;
import com.nathanclaire.alantra.advice.model.AdviceClassification;
import com.nathanclaire.alantra.advice.model.AdviceStatus;
import com.nathanclaire.alantra.advice.model.AdviceType;
import com.nathanclaire.alantra.advice.request.AdviceRequest;
import com.nathanclaire.alantra.advice.service.entity.AdviceClassificationService;
import com.nathanclaire.alantra.advice.service.entity.AdviceStatusService;
import com.nathanclaire.alantra.advice.service.entity.AdviceTypeService;
import com.nathanclaire.alantra.base.service.process.BaseProcessService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.EntityUtil;
import com.nathanclaire.alantra.base.util.ErrorCodes;
import com.nathanclaire.alantra.base.util.ExceptionUtil;
import com.nathanclaire.alantra.base.util.PropertyUtil;
import com.nathanclaire.alantra.businessdata.model.Currency;
import com.nathanclaire.alantra.customer.model.Customer;
import com.nathanclaire.alantra.customer.model.CustomerAccount;
import com.nathanclaire.alantra.datasource.model.DataChannel;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class AdviceServiceImpl extends BaseProcessService implements AdviceService {

	@Inject AdviceTypeService adviceTypeService;
	@Inject AdviceStatusService adviceStatusService;
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Inject AdviceClassificationService adviceClassificationService;
	@Inject com.nathanclaire.alantra.advice.service.entity.AdviceService adviceEntityService;
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.process.AdviceService#createAdvice(com.nathanclaire.alantra.advice.model.AdviceType, com.nathanclaire.alantra.advice.model.AdviceStatus, com.nathanclaire.alantra.datasource.model.DataChannel, com.nathanclaire.alantra.customer.model.Customer, com.nathanclaire.alantra.customer.model.CustomerAccount, com.nathanclaire.alantra.businessdata.model.Currency, java.math.BigDecimal, java.lang.String, java.lang.String)
	 */
	@Override
	public Advice createAdvice(AdviceType adviceType, AdviceClassification adviceClassification, 
			AdviceStatus status, DataChannel channel, Customer customer, CustomerAccount account,
			Currency currency, BigDecimal amount, String chequeNo, String cardNo) throws ApplicationException 
	{
		// Debug and validate the provided parameters
		logger.debug("Creating advice: Type: {}, classification: {}, status: " +
				"{}, channel: {}, customer: {}, account: {}, currency: {} amount: {}, chequeNo: {}, cardNo: {} ", 
				adviceType, adviceClassification, status, channel, customer, account, currency, amount, chequeNo, cardNo);
		
		EntityUtil.returnOrThrowIfParamsArrayContainsNull( new Object[] {adviceType, 
				adviceClassification, status, channel, customer, account, currency, amount, chequeNo, cardNo});
		try {
			AdviceRequest adviceRequest = 
					new AdviceRequest(adviceType.getId(), adviceClassification.getId(), status.getId(), customer.getId(), 
							account.getId(), currency.getId(), EntityUtil.generateDefaultEntityCode(), cardNo, chequeNo, amount);
			PropertyUtil.initializeBaseFields(adviceRequest);
			return adviceEntityService.create(adviceRequest);
		} catch (Exception e) {
			ExceptionUtil.processException(e, ErrorCodes.BPS_ENTITY_CREATION_ERROR_CD);
		}
		return null;
	}


	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.process.AdviceService#getAdviceType(java.lang.String)
	 */
	@Override
	public AdviceType getAdviceType(String adviceTypeCode) throws ApplicationException {
		return (AdviceType) EntityUtil.returnOrThrowIfNull(
				adviceTypeService.findByCode(adviceTypeCode), ErrorCodes.BPS_ENTITY_INSTANCE_NOT_FOUND_ERROR_CD, "AdviceType");
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.process.AdviceService#getAdviceStatus(java.lang.String)
	 */
	@Override
	public AdviceStatus getAdviceStatus(String adviceStatusCode) throws ApplicationException {
		return (AdviceStatus) EntityUtil.returnOrThrowIfNull(
				adviceStatusService.findByCode(adviceStatusCode), ErrorCodes.BPS_ENTITY_INSTANCE_NOT_FOUND_ERROR_CD, "AdviceStatus");
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.process.AdviceService#getAdviceClassification(java.lang.String)
	 */
	@Override
	public AdviceClassification getAdviceClassification(String adviceClassificationCode) throws ApplicationException {
		return (AdviceClassification) 
				EntityUtil.returnOrThrowIfNull(adviceClassificationService.findByCode(adviceClassificationCode), 
						ErrorCodes.BPS_ENTITY_INSTANCE_NOT_FOUND_ERROR_CD, "AdviceClassification");
	}
	

}
