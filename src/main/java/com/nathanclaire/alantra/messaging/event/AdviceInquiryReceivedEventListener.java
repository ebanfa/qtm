/**
 * 
 */
package com.nathanclaire.alantra.messaging.event;

import javax.enterprise.event.Observes;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.messaging.annotation.AdviceInquiryReceivedEvent;

/**
 * @author Edward Banfa 
 *
 */
public interface AdviceInquiryReceivedEventListener {

	
	public void processAdviceInquiryReceivedEvent(@Observes @AdviceInquiryReceivedEvent MessageEvent event) 
			throws ApplicationException;

}
