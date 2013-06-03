/**
 * 
 */
package com.nathanclaire.alantra.advice.service.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.nathanclaire.alantra.advice.model.Advice;
import com.nathanclaire.alantra.advice.request.AdviceRequest;
import com.nathanclaire.alantra.advice.response.AdviceResponse;
import com.nathanclaire.alantra.base.service.entity.BaseEntityService;
import com.nathanclaire.alantra.base.util.ApplicationException;

/**
 * @author Edward Banfa
 *
 */
public interface AdviceService extends BaseEntityService<Advice, AdviceResponse, AdviceRequest>
{

	/**
	 * @param customerId
	 * @param accountId
	 * @param currencyId
	 * @param adviceTypeId
	 * @param adviceStatus
	 * @param amount
	 * @return
	 */
	public Advice findAdvice(Integer customerId, Integer accountId, 
			Integer currencyId, Integer adviceTypeId, Integer adviceStatus, 
			BigDecimal amount)  throws ApplicationException ;
	/**
	 * @param adviceType
	 * @param accountNo
	 * @param chequeNo
	 * @param amount
	 * @param crncyCode
	 * @param txnDate
	 * @return
	 */
	public Advice createAdvice(String adviceType, String accountNo, 
			String chequeNo, BigDecimal amount, String crncyCode, Date txnDate) throws ApplicationException ;
}
