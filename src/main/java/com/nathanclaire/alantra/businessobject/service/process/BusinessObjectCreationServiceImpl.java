/**
 * 
 */
package com.nathanclaire.alantra.businessobject.service.process;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.model.BaseEntity;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.ErrorCodes;
import com.nathanclaire.alantra.base.util.ExceptionUtil;
import com.nathanclaire.alantra.businessobject.data.BusinessObjectData;
import com.nathanclaire.alantra.businessobject.util.BusinessObjectUtil;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class BusinessObjectCreationServiceImpl implements BusinessObjectCreationService {
	
	@Inject private EntityManager entityManager;
	private Logger logger = LoggerFactory.getLogger(getClass());

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.rule.service.process.BusinessObjectCreationService#create(com.nathanclaire.alantra.rule.engine.BusinessObjectData)
	 */
	public BaseEntity create(BusinessObjectData businessObjectData)	throws ApplicationException {
		BaseEntity businessObject = null;
		try {
			businessObject = createBusinessObjectInstance(businessObjectData);
			logger.debug("Persisting {}", businessObject);
			entityManager.persist(businessObject);
		} catch (Exception e) {
			ExceptionUtil.processException(e, ErrorCodes.BOCS_BUSINESS_OBJECT_CREATION_ERROR_CD);
		}
		return businessObject;
	}
	
	private BaseEntity createBusinessObjectInstance(BusinessObjectData businessObjectData) throws ApplicationException{
		return BusinessObjectUtil.businessObjectToEntityInstance(businessObjectData);
	}

}
