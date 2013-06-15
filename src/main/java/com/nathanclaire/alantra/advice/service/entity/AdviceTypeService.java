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

	String CHQ_WITHDRAWAL = null;
	String CC_TXN_ADVICE = null;
	String ATM_WITHDRAWAL_ADVICE = null;
	
}
