/**
 * 
 */
package com.nathanclaire.alantra.datasource.service.process;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.nathanclaire.alantra.channel.service.process.TransactionProcessorService;
import com.nathanclaire.alantra.datasource.etl.TableDataProcessor;
import com.nathanclaire.alantra.datasource.etl.TransactionDataProcessor;

/**
 * @author Edward Banfa 
 *
 */
@Stateless
public class TransactionDataInputJobRunnerImpl extends DataInputJobRunnerImpl 
implements TransactionDataInputJobRunner {
	
	@Inject
	TransactionProcessorService transactionProcessor;
	/**
	 * @param processorClassName
	 * @return
	 */
	protected TableDataProcessor getDataProcessor(String processorClassName) {
		TransactionDataProcessor tableDataProcessor = null;
		try {
			tableDataProcessor = 
					(TransactionDataProcessor) Class.forName(processorClassName).newInstance();
			tableDataProcessor.setTransactionProcessor(transactionProcessor);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return tableDataProcessor;
	}

}
