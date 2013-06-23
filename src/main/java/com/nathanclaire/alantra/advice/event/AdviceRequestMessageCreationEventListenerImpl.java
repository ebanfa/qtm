/**
 * 
 */
package com.nathanclaire.alantra.advice.event;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.advice.annotation.AdviceCreatedEvent;
import com.nathanclaire.alantra.advice.annotation.AdviceCreationFailedEvent;
import com.nathanclaire.alantra.advice.annotation.AdviceRequestMessageCreationFailedEvent;
import com.nathanclaire.alantra.advice.annotation.AdviceRequestMessageCreatedEvent;
import com.nathanclaire.alantra.advice.model.Advice;
import com.nathanclaire.alantra.advice.model.AdviceRequestMessage;
import com.nathanclaire.alantra.advice.service.entity.AdviceRequestMessageService;
import com.nathanclaire.alantra.advice.service.process.AdviceProcessingService;
import com.nathanclaire.alantra.base.util.ApplicationException;


/**
 * @author Edward Banfa 
 *
 */
@Stateless
public class AdviceRequestMessageCreationEventListenerImpl implements AdviceRequestMessageCreationEventListener {

	@Inject AdviceProcessingService adviceProcessingService;
	@Inject AdviceRequestMessageService requestMessageService;
	@Inject @AdviceCreatedEvent Event<AdviceCreationEvent> adviceCreatedEvent;
	@Inject @AdviceCreationFailedEvent Event<AdviceCreationEvent> adviceCreationFailedEvent;

	private Logger logger = LoggerFactory.getLogger(AdviceRequestMessageCreationEventListenerImpl.class);
	
	private static final String ADVICE_REQ_MSG_NOT_FOUND = null;
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.event.AdviceRequestMessageCreationEventListener#processAdviceRequestCreatedEvent(com.nathanclaire.alantra.advice.annotation.AdviceRequestMessageCreationEvent)
	 */
	@Override
	public void processAdviceRequestCreatedEvent(@Observes @AdviceRequestMessageCreatedEvent AdviceRequestMessageCreationEvent event) 
			throws ApplicationException {
		// 1. Get the created advice request using the id from the event object
		AdviceRequestMessage requestMessage = requestMessageService.findById(event.getAdviceRequestId());
		// 2. Validate the advice request
		validateRequestMessage(requestMessage);
		// 3. Create an advice from the advice request
		try {
			Advice advice = adviceProcessingService.createAdvice(requestMessage);
			// 4. If successful then fire advice created event
			if(advice != null)
				adviceCreatedEvent.fire(new AdviceCreationEvent(advice.getId(), requestMessage.getId()));
		} catch (ApplicationException e) {
				// 5. If not fire advice creation failed event
				logger.error(e.getMessage());
				adviceCreationFailedEvent.fire(new AdviceCreationEvent(null, requestMessage.getId()));
		}
		
	}

	/**
	 * @param requestMessage
	 * @throws ApplicationException
	 */
	private void validateRequestMessage(AdviceRequestMessage requestMessage) throws ApplicationException {
		if(requestMessage == null)
			throw new ApplicationException(ADVICE_REQ_MSG_NOT_FOUND);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.event.AdviceRequestMessageCreationEventListener#processAdviceRequestCreateFailedEvent(com.nathanclaire.alantra.advice.annotation.AdviceRequestMessageCreationEvent)
	 */
	@Override
	public void processAdviceRequestCreateFailedEvent(@Observes @AdviceRequestMessageCreationFailedEvent AdviceRequestMessageCreationEvent event) {
		// TODO Auto-generated method stub
		// 1. Get the message id
		// 2. Notify admin
		
	}

	
}
