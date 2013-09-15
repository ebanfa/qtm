/**
 * 
 */
package com.nathanclaire.alantra.rule.service.process;

import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.service.process.BaseProcessService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.EntityUtil;
import com.nathanclaire.alantra.base.util.ErrorCodes;
import com.nathanclaire.alantra.rule.engine.BusinessObjectData;

/**
 * 
 * @author Edward Banfa
 *
 */
@Stateless
public class TransactionAttributeValueServiceImpl extends BaseProcessService
		implements TransactionAttributeValueService {
	private Logger logger = LoggerFactory.getLogger(getClass());
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.rule.service.process.TransactionAttributeValueService#getAttributeValue(java.lang.String, com.nathanclaire.alantra.rule.engine.BusinessObjectData)
	 */
	@Override
	public Object getAttributeValue(String attributeName,
			BusinessObjectData businessObjectData) throws ApplicationException {
		EntityUtil.returnOrThrowIfParamsArrayContainsNull(
				new Object[]{attributeName, businessObjectData}, "TransactionAttributeValueService.getAttributeValue");
		logger.debug("Resolving value for business object attribute: {} in business object: {}", attributeName, businessObjectData);
		if(attributeName.equals(DATE_ATTR_NM))
			return businessObjectData.getDataValue(DATE_ATTR_NM);
		else if(attributeName.equals(ACCOUNT_NO_ATTR_NM))
			return businessObjectData.getDataValue(ACCOUNT_NO_ATTR_NM);
		else if(attributeName.equals(TRANSACTION_TY_ATTR_NM))
			return businessObjectData.getDataValue(TRANSACTION_TY_ATTR_NM);
		else
			throw new ApplicationException(ErrorCodes.TAVS_ATTR_RESOLUTION_ERROR_CD, ErrorCodes.TAVS_ATTR_NOT_FOUND_ERROR_MSG);
	}

}
