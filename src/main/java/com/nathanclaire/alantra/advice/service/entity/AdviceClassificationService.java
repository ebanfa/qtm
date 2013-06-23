/**
 * 
 */
package com.nathanclaire.alantra.advice.service.entity;

import com.nathanclaire.alantra.base.service.entity.BaseEntityService;
import com.nathanclaire.alantra.advice.model.AdviceClassification;
import com.nathanclaire.alantra.advice.request.AdviceClassificationRequest;
import com.nathanclaire.alantra.advice.response.AdviceClassificationResponse;

/**
 * @author Edward Banfa
 *
 */
public interface AdviceClassificationService extends BaseEntityService<AdviceClassification, AdviceClassificationResponse, AdviceClassificationRequest>
{

	public static final String NON_CYCLIC_ADVICE = "NON_CYCLIC";
	
}
