/**
 * 
 */
package com.nathanclaire.alantra.datasource.etl;

import java.math.BigDecimal;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.channel.model.ServiceTransaction;
import com.nathanclaire.alantra.channel.model.ServiceTransactionStatus;
import com.nathanclaire.alantra.channel.service.entity.ServiceTransactionStatusService;
import com.nathanclaire.alantra.channel.service.entity.ServiceTransactionTypeService;
import com.nathanclaire.alantra.channel.service.process.TransactionProcessorService;

/**
 * @author Edward Banfa 
 *
 */
public class TransactionEntityDataProviderImpl extends BaseEntityDataProviderImpl implements TransactionEntityDataProvider {

	private static final String TRANSACTION_NOT_CREATED = null;
	private String amountString = null;
	private String txnDateString = null;
	private String chequeNoString = null;
	private String accountNoString = null;
	private String crncyCodeString = null;
	private TransactionProcessorService transactionProcessor;
	
	/**
	 * @return the transactionProcessor
	 */
	public TransactionProcessorService getTransactionProcessor() {
		return transactionProcessor;
	}

	/**
	 * @param transactionProcessor the transactionProcessor to set
	 */
	public void setTransactionProcessor(TransactionProcessorService transactionProcessor) {
		this.transactionProcessor = transactionProcessor;
	}


	/**
	 * @param rowData
	 * @return
	 */
	protected void processRow(RowData rowData) throws ApplicationException {
		loadRowData(rowData);
		ServiceTransaction transaction = transactionProcessor.processChequeTransactionRequest(1, ServiceTransactionTypeService.CHEQUE_WITHDRAWAL,
				accountNoString, chequeNoString, new BigDecimal(amountString), crncyCodeString, convertStringToJavaDate(txnDateString));
		if(transaction == null) throw new ApplicationException(TRANSACTION_NOT_CREATED);
		ServiceTransactionStatus status = transaction.getServiceTransactionStatus();
		if(status.getCode().equals(ServiceTransactionStatusService.MATCHED))
			getTableData().setTxnMatched(getTableData().getTxnMatched() + 1);
		if(status.getCode().equals(ServiceTransactionStatusService.NOT_MATCHED))
			getTableData().setTxnUnMatched(getTableData().getTxnUnMatched() + 1);
		if(status.getCode().equals(ServiceTransactionStatusService.MATCH_PENDING));
	}

	/**
	 * @param rowData
	 */
	private void loadRowData(RowData rowData) {
		int columnCount = 1;
		for(CellData cellData:rowData.getColumns())
		{
			if(columnCount == 1)
				accountNoString = cellData.getData();
			if(columnCount == 2)
				amountString = cellData.getData();
			if(columnCount == 3)
				crncyCodeString = cellData.getData();
			if(columnCount == 4)
				txnDateString = cellData.getData();
			if(columnCount == 5)
				chequeNoString = cellData.getData();
			columnCount ++;
		}
	}

}
