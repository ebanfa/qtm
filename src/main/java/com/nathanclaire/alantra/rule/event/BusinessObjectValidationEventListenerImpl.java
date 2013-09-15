/**
 * 
 */
package com.nathanclaire.alantra.rule.event;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.model.BaseEntity;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.ErrorCodes;
import com.nathanclaire.alantra.base.util.ExceptionUtil;
import com.nathanclaire.alantra.rule.annotation.BusinessObjectCreationEvent;
import com.nathanclaire.alantra.rule.annotation.BusinessObjectValidationEvent;
import com.nathanclaire.alantra.rule.engine.BusinessObjectData;
import com.nathanclaire.alantra.rule.engine.BusinessObjectDataImpl;
import com.nathanclaire.alantra.rule.service.process.BusinessObjectCreationService;
import com.nathanclaire.alantra.rule.util.BusinessObjectUtil;

/**
 * Listens for {@link BusinessObjectValidationEvent} events and 
 * creates an new {@link BaseEntity} record. The new record will have an entity
 * record of either active or invalid, depending on the outcome of the
 * validation process
 * 
 * @author Edward Banfa
 *
 */
@Stateless
public class BusinessObjectValidationEventListenerImpl implements  BusinessObjectValidationEventListener {

    private Logger logger = LoggerFactory.getLogger(getClass());

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.rule.service.process.BusinessObjectCreator#processBusinessObjectCreactionEvent(com.nathanclaire.alantra.rule.event.BusinessObjectEvent)
	 */
	@Override
	public void processBusinessObjectCreationEvent(
			@Observes @BusinessObjectValidationEvent BusinessObjectEvent event) throws ApplicationException {
		try {
		} catch (Exception e) {
			ExceptionUtil.logException(e);
		}
	}
}
