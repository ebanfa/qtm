/**
 * 
 */
package com.nathanclaire.alantra.advice.service.entity;

import com.nathanclaire.alantra.base.service.entity.BaseEntityService;
import com.nathanclaire.alantra.advice.model.AdviceType;
import com.nathanclaire.alantra.advice.request.AdviceTypeRequest;
import com.nathanclaire.alantra.advice.response.AdviceTypeResponse;

/**
 * @author Edward Banfa
 *
 */
public interface AdviceTypeService extends BaseEntityService<AdviceType, AdviceTypeResponse, AdviceTypeRequest>
{

	public static final String CC_TXN_ADVICE = "CC_TXN_ADVICE";
	public static final String CHQ_WITHDRAWAL = "CHQ_WITHDRAWAL";
	public static final String ATM_WITHDRAWAL_ADVICE = "ATM_WITHDRAWAL_ADVICE";
	
}
