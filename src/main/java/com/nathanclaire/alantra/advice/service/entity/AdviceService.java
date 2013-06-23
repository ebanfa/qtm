/**
 * 
 */
package com.nathanclaire.alantra.advice.service.entity;

import java.math.BigDecimal;

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
	 * @param chequeNo
	 * @param cardNo
	 * @param currencyId
	 * @param adviceTypeId
	 * @param adviceStatus
	 * @param amount
	 * @return
	 * @throws ApplicationException
	 */
	public Advice findAdvice(Integer customerId, Integer accountId, String chequeNo, String cardNo, 
			Integer currencyId, Integer adviceTypeId, Integer adviceStatus, BigDecimal amount)  throws ApplicationException ;
}
