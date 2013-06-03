/**
 * 
 */
package com.nathanclaire.alantra.datasource.etl;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.channel.service.process.TransactionProcessorService;


/**
 * @author Edward Banfa 
 *
 */
public class TransactionDataProcessor implements TableDataProcessor {

   private TransactionProcessorService transactionProcessor;
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.TableDataProcessor#processData(com.nathanclaire.alantra.datasource.etl.TableData)
	 */
	@Override
	public void processData(TableData data) throws ApplicationException{
		TransactionEntityDataProvider txnEntityDataProvider = new TransactionEntityDataProviderImpl();
		txnEntityDataProvider.setTransactionProcessor(transactionProcessor);
		txnEntityDataProvider.loadEntityData(data);
	}
	/**
	 * @return the transactionProcessor
	 */
	public TransactionProcessorService getTransactionProcessor() {
		return transactionProcessor;
	}
	/**
	 * @param transactionProcessor the transactionProcessor to set
	 */
	public void setTransactionProcessor(
			TransactionProcessorService transactionProcessor) {
		this.transactionProcessor = transactionProcessor;
	}

}
