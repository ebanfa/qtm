/**
 * 
 */
package com.nathanclaire.alantra.advice.service.entity;

import java.math.BigDecimal;
import java.util.List;

import com.nathanclaire.alantra.advice.model.Advice;
import com.nathanclaire.alantra.advice.request.AdviceRequest;
import com.nathanclaire.alantra.base.service.entity.BaseEntityService;

/**
 * @author Edward Banfa
 *
 */
public interface AdviceService extends BaseEntityService<Advice, AdviceRequest>
{
	/**
	 * @param accountNo
	 * @return
	 */
	public List<Advice> findByAccountNo(String accountNo);
	/**
	 * @param transactionType
	 * @param accountNo
	 * @param amount
	 * @return
	 */
	public List<Advice> findAdvice(String transactionType, String accountNo, BigDecimal amount);
}
