/**
 * 
 */
package com.nathanclaire.alantra.transaction.event;

import javax.ejb.Stateless;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import com.nathanclaire.alantra.base.service.process.BaseProcessService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.datasource.model.DataInputJobSummary;
import com.nathanclaire.alantra.datasource.service.entity.DataInputJobService;
import com.nathanclaire.alantra.datasource.service.entity.DataInputJobSummaryService;
import com.nathanclaire.alantra.transaction.annotation.TransactionMatchedEvent;
import com.nathanclaire.alantra.transaction.annotation.TransactionNotMatchedEvent;
import com.nathanclaire.alantra.transaction.service.entity.ServiceTransactionService;

/**
 * @author Edward Banfa 
 *
 */
@Stateless
public class TransactionMatchingEventListenerImpl extends BaseProcessService implements	TransactionMatchingEventListener {

	@Inject DataInputJobService inputJobService;
	@Inject DataInputJobSummaryService summaryService;
	@Inject ServiceTransactionService transactionService;
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.transaction.event.TransactionMatchingEventListener#processTransactionMatchedEvent(com.nathanclaire.alantra.transaction.event.TransactionMatchingEvent)
	 */
	@Override
	public void processTransactionMatchedEvent(@Observes @TransactionMatchedEvent TransactionMatchingEvent event) throws ApplicationException {
		DataInputJobSummary summary = summaryService.findById(event.getJobSummaryId());
		if(summary != null) summary.setTxnsMatched(summary.getTxnsMatched() + 1);
		getEntityManager().merge(summary);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.transaction.event.TransactionMatchingEventListener#processTransactionNotMatchedEvent(com.nathanclaire.alantra.transaction.event.TransactionMatchingEvent)
	 */
	@Override
	public void processTransactionNotMatchedEvent(	@Observes @TransactionNotMatchedEvent TransactionMatchingEvent event) throws ApplicationException {
		DataInputJobSummary summary = summaryService.findById(event.getJobSummaryId());
		if(summary != null) summary.setTxnsUnmatched(summary.getTxnsUnmatched() + 1);
		getEntityManager().merge(summary);
	}

}
