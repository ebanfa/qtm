/**
 * 
 */
package com.nathanclaire.alantra.transaction.service.process;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.model.BaseEntity;
import com.nathanclaire.alantra.base.request.BaseRequest;
import com.nathanclaire.alantra.base.service.process.BaseProcessService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtils;
import com.nathanclaire.alantra.base.util.StringUtil;
import com.nathanclaire.alantra.businessdata.service.entity.CurrencyService;
import com.nathanclaire.alantra.customer.model.CustomerAccount;
import com.nathanclaire.alantra.customer.service.entity.CustomerAccountService;
import com.nathanclaire.alantra.datasource.etl.TableDataLite;
import com.nathanclaire.alantra.datasource.service.entity.DataChannelService;
import com.nathanclaire.alantra.datasource.service.entity.DataInputJobSummaryService;
import com.nathanclaire.alantra.transaction.annotation.TransactionCreatedEvent;
import com.nathanclaire.alantra.transaction.annotation.TransactionNotCreatedEvent;
import com.nathanclaire.alantra.transaction.event.TransactionEvent;
import com.nathanclaire.alantra.transaction.model.ServiceTransaction;
import com.nathanclaire.alantra.transaction.model.ServiceTransactionStatus;
import com.nathanclaire.alantra.transaction.model.ServiceTransactionType;
import com.nathanclaire.alantra.transaction.request.ServiceTransactionRequest;
import com.nathanclaire.alantra.transaction.service.entity.ServiceTransactionService;
import com.nathanclaire.alantra.transaction.service.entity.ServiceTransactionStatusService;
import com.nathanclaire.alantra.transaction.service.entity.ServiceTransactionTypeService;

/**
 * @author Edward Banfa 
 *
 */
@Stateless
public class TransactionDataInputServiceImpl extends BaseProcessService implements TransactionDataInputService {
	
	@Inject DataChannelService dataChannelService;
	@Inject CurrencyService currencyService;
	@Inject CustomerAccountService accountService;
	@Inject ServiceTransactionService serviceTransactionService;
	@Inject DataInputJobSummaryService summaryService;
	@Inject ServiceTransactionTypeService serviceTransactionTypeService;
	@Inject ServiceTransactionStatusService serviceTransactionStatusService;
	private static final String TRANSACTION_MATCH_PENDING_STATUS_NOT_FOUND = 
			"TransactionDataInputServiceImpl.TRANSACTION_MATCH_PENDING_STATUS_NOT_FOUND";
	private static final String CHQ_TXN_TYPE_NOT_FOUND = "TransactionDataInputServiceImpl.CHQ_TXN_TYPE_NOT_FOUND";
	private static final String CUSTOMER_ACCOUNT_DOES_NOT_EXIST = "TransactionDataInputServiceImpl.CUSTOMER_ACCOUNT_DOES_NOT_EXIST";

	@Inject @TransactionCreatedEvent Event<TransactionEvent> transactionCreatedEvent;
	@Inject @TransactionNotCreatedEvent Event<TransactionEvent> transactionNotCreatedEvent;
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.transaction.service.process.TransactionDataInputService#processTransactionRequest()
	 */
	@Override
	public BaseEntity processDataInput(BaseRequest primaryEntityRequest, BaseRequest secEntityRequest, TableDataLite tableDataLite) throws ApplicationException {
		PropertyUtils.initializeBaseFields(primaryEntityRequest);
		ServiceTransactionRequest transactionRequest = (ServiceTransactionRequest) primaryEntityRequest;
		// Process relationships
		transactionRequest.setDataChannelId(getDataImportService(tableDataLite.getSourceServiceCode()).getId());
		transactionRequest.setServiceTransactionStatusId(getTransactionPendingStatus().getId());
		CustomerAccount account = getTransactionAccount(transactionRequest.getCustomerAccountId());
		String transactionCode = getTransactionCode(transactionRequest, account);
		// Process other fields
		transactionRequest.setCode(transactionCode);
		transactionRequest.setName(account.getName());
		transactionRequest.setCustomerAccountId(account.getId());
		// Ensure uniqueness of data
		ServiceTransaction transaction = serviceTransactionService.findByCode(transactionCode);		
		if(transaction != null) 
		{
			flagDataInputRejected(tableDataLite);
			String transactionType = transaction.getServiceTransactionType().getCode();
			// Fire event and throw exception
			transactionNotCreatedEvent.fire(initializeEvent(tableDataLite, transaction, transactionType));
			return transaction;
		}
		flagDataInputAccepted(tableDataLite);
		transaction = serviceTransactionService.create(transactionRequest);
		transactionCreatedEvent.fire(initializeEvent(tableDataLite, transaction, transaction.getServiceTransactionType().getCode()));
		return transaction;
	}
	
	/**
	 * @param tableDataLite
	 * @return
	 */
	private TransactionEvent initializeEvent(TableDataLite tableDataLite, ServiceTransaction transaction, String transactionTypeCode)
	{
		TransactionEvent event = new TransactionEvent();
		event.setJobId(tableDataLite.getJobId());
		if(transaction != null) event.setTransactionId(transaction.getId());
		event.setChannelId(tableDataLite.getChannelId());
		event.setJobSummaryId(tableDataLite.getJobSummaryId());
		event.setTransactionTypeCode(transactionTypeCode);
		return event;
	}
	
	/**
	 * @param transactionRequest
	 * @param account
	 * @return
	 * @throws ApplicationException
	 */
	private String getTransactionCode(ServiceTransactionRequest transactionRequest, CustomerAccount account) 
			throws ApplicationException
	{
		String transactionCode = null;
		if(StringUtil.isValidString(transactionRequest.getChequeNo())){
				transactionRequest.setServiceTransactionTypeId(getChequeWidthrawalTransactionType().getId());
				transactionCode = account.getCode().concat(StringUtil.UNDERSCORE.concat(transactionRequest.getChequeNo()));
		}
		return transactionCode;
	}

	/**
	 * @param accountNo
	 * @return
	 * @throws ApplicationException
	 */
	private CustomerAccount getTransactionAccount(Integer id) throws ApplicationException {
		CustomerAccount account = accountService.findById(id);
		if(account == null) 
			throw new ApplicationException(CUSTOMER_ACCOUNT_DOES_NOT_EXIST);
		return account;
	}
	
	/**
	 * @return
	 * @throws ApplicationException
	 */
	private ServiceTransactionStatus getTransactionPendingStatus() throws ApplicationException {
		// Get the service
		ServiceTransactionStatus status = serviceTransactionStatusService.findByCode(ServiceTransactionStatusService.MATCH_PENDING);
		if(status == null)
			throw new ApplicationException(TRANSACTION_MATCH_PENDING_STATUS_NOT_FOUND);
		return status;
	}
	
	/**
	 * @return
	 * @throws ApplicationException
	 */
	private ServiceTransactionType getChequeWidthrawalTransactionType() throws ApplicationException {
		// Get the service
		ServiceTransactionType transactionType = serviceTransactionTypeService.findByCode(ServiceTransactionTypeService.CHEQUE_WITHDRAWAL);
		if(transactionType == null)
			throw new ApplicationException(CHQ_TXN_TYPE_NOT_FOUND);
		return transactionType;
	}
	
}
