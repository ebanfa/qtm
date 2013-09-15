/**
 * 
 */
package com.nathanclaire.alantra.datasource.etl.service;

import javax.inject.Inject;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.ErrorCodes;
import com.nathanclaire.alantra.base.util.ExceptionUtil;
import com.nathanclaire.alantra.rule.engine.BusinessObjectData;
import com.nathanclaire.alantra.rule.service.process.BusinessObjectCreationService;
import com.nathanclaire.alantra.rule.service.process.TransactionRuleProcessingService;
import com.nathanclaire.alantra.rule.service.process.TransactionRuleRoutingService;
import com.nathanclaire.alantra.rule.service.process.TransactionRuleValidationService;

/**
 * @author Edward Banfa
 *
 */
public class BusinessObjectInputServiceImpl implements BusinessObjectInputService {

	@Inject TransactionRuleRoutingService routingService;
	@Inject TransactionRuleValidationService validationService;
	@Inject TransactionRuleProcessingService processingService;
    @Inject BusinessObjectCreationService objectCreationService;

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.service.BusinessObjectInputService#loadBusinessObject(com.nathanclaire.alantra.rule.engine.BusinessObjectData)
	 */
	@Override
	public void loadBusinessObject(BusinessObjectData businessObjectData)	throws ApplicationException {
		try {
			validationService.validate(businessObjectData);
			objectCreationService.create(businessObjectData);
			processingService.process(businessObjectData);
			routingService.route(businessObjectData);
		} catch (Exception e) {
			ExceptionUtil.processException(e, ErrorCodes.BOIS_LOAD_BO_ERROR_CD);
		}
	}

}
