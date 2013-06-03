/**
 * 
 */
package com.nathanclaire.alantra.datasource.etl;

import com.nathanclaire.alantra.channel.service.process.TransactionProcessorService;

/**
 * @author Edward Banfa 
 *
 */
public interface TransactionEntityDataProvider extends EntityDataProvider {

	public void setTransactionProcessor(TransactionProcessorService transactionProcessor);
}
