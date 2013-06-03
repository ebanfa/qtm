/**
 * 
 */
package com.nathanclaire.alantra.channel.service.process;

import java.math.BigDecimal;
import java.util.Date;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.nathanclaire.alantra.advice.model.Advice;
import com.nathanclaire.alantra.advice.service.process.TransactionMatchingService;
import com.nathanclaire.alantra.base.service.process.BaseProcessService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.businessdata.model.Currency;
import com.nathanclaire.alantra.businessdata.service.entity.CurrencyService;
import com.nathanclaire.alantra.channel.model.Service;
import com.nathanclaire.alantra.channel.model.ServiceTransaction;
import com.nathanclaire.alantra.channel.model.ServiceTransactionStatus;
import com.nathanclaire.alantra.channel.model.ServiceTransactionType;
import com.nathanclaire.alantra.channel.request.ServiceTransactionRequest;
import com.nathanclaire.alantra.channel.service.entity.ServiceService;
import com.nathanclaire.alantra.channel.service.entity.ServiceTransactionService;
import com.nathanclaire.alantra.channel.service.entity.ServiceTransactionStatusService;
import com.nathanclaire.alantra.channel.service.entity.ServiceTransactionTypeService;

/**
 * @author Edward Banfa 
 *
 */
@Stateless
public class TransactionProcessorServiceImpl extends BaseProcessService implements
		TransactionProcessorService {

	@Inject
	CurrencyService currencyService;
	@Inject
	ServiceService serviceEntityService;
	@Inject
	TransactionMatchingService transactionMatchingService;
	@Inject
	ServiceTransactionService serviceTransactionService;
	@Inject
	ServiceTransactionTypeService serviceTransactionTypeService;
	@Inject
	ServiceTransactionStatusService serviceTransactionStatusService;
	
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.process.TransactionProcessorService#processTransactionRequest()
	 */
	@Override
	public ServiceTransaction processChequeTransactionRequest(Integer serviceId, String transactionTypeCode,	
			String accountNo, String chequeNo, BigDecimal amount, String crncyCode, Date txnDate) throws ApplicationException {
		// Validate account number
		if (accountNo == null || accountNo.isEmpty())
			throw new ApplicationException(ACCOUNT_NO_NOT_PROVIDED);
		// Validate cheque number
		if (chequeNo == null || chequeNo.isEmpty())
			throw new ApplicationException(CHEQUE_NO_NOT_PROVIDED);
		// Verify uniqueness of transactions
		String transactionCode = accountNo.concat(chequeNo);
		if (serviceTransactionService.findByCode(transactionCode) != null)
			throw new ApplicationException(TRANSACTION_ALREADY_EXISTS);
		ServiceTransaction transaction = 
				processTransaction(serviceId,transactionTypeCode, 
						accountNo, chequeNo, amount, crncyCode,	txnDate);
		return transaction;
	}

	/**
	 * @param serviceId
	 * @param transactionTypeCode
	 * @param accountNo
	 * @param chequeNo
	 * @param amount
	 * @param crncyCode
	 * @param txnDate
	 * @return
	 */
	private ServiceTransaction processTransaction(Integer serviceId,
			String transactionTypeCode, String accountNo, String chequeNo,
			BigDecimal amount, String crncyCode, Date txnDate) {
		ServiceTransaction transaction = null;
		try {
			// 2. Create a transaction object with the above service
			ServiceTransactionRequest transactionRequest = this.iniatializeChqTxn(accountNo, chequeNo, amount, crncyCode, txnDate);
			// 1. Get the service type with the specified service id
			setService(serviceId, transactionRequest);
			// 3. Get the transaction type with the specified code
			setTransactionType(transactionTypeCode, transactionRequest);
			// 4. Set the status to un processed
			setTransactionStatus(transactionRequest);
			// 5. Set the currency on the transaction
			setCurrency(crncyCode, transactionRequest);
			// 6. Save the transaction.
			transaction = createTransaction(transactionRequest);
			// 7. Return the transaction object to the caller
			return transaction;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return transaction;
	}

	/**
	 * @param crncyCode
	 * @param transactionRequest
	 */
	private void setCurrency(String crncyCode,
			ServiceTransactionRequest transactionRequest) throws ApplicationException {
		Currency currency = currencyService.findByCode(crncyCode);
		transactionRequest.setCurrencyId(currency.getId());
	}

	/**
	 * @param transactionRequest
	 */
	private void setTransactionStatus(
			ServiceTransactionRequest transactionRequest) throws ApplicationException {
		ServiceTransactionStatus transactionMatchingPendingStatus = 
				serviceTransactionStatusService.findByCode(ServiceTransactionStatusService.MATCH_PENDING);
		transactionRequest.setServiceTransactionStatusId(transactionMatchingPendingStatus.getId());
	}

	/**
	 * @param transactionTypeCode
	 * @param transactionRequest
	 */
	private void setTransactionType(String transactionTypeCode,
			ServiceTransactionRequest transactionRequest) throws ApplicationException {
		ServiceTransactionType transactionType = serviceTransactionTypeService.findByCode(transactionTypeCode);
		transactionRequest.setServiceTransactionTypeId(transactionType.getId());
	}

	/**
	 * @param serviceId
	 * @param transactionRequest
	 */
	private void setService(Integer serviceId,
			ServiceTransactionRequest transactionRequest) throws ApplicationException {
		Service dataImportService = serviceEntityService.findById(serviceId);
		transactionRequest.setServiceId(dataImportService.getId());
	}

	/**
	 * @param transactionRequest
	 * @return
	 */
	private ServiceTransaction createTransaction(ServiceTransactionRequest transactionRequest) throws ApplicationException {
		ServiceTransaction transaction = createAndMatchTransaction(transactionRequest);
		transactionRequest.setId(transaction.getId());
		transaction = serviceTransactionService.update(transactionRequest);
		return transaction;
	}

	/**
	 * @param transactionRequest
	 * @return
	 */
	private ServiceTransaction createAndMatchTransaction(
			ServiceTransactionRequest transactionRequest) throws ApplicationException {
		ServiceTransaction transaction = serviceTransactionService.create(transactionRequest);
		// 1. Call advice service to attempt to match the transaction
		Advice advice = transactionMatchingService.match(transaction);
		// 2. If matching succeeds, update the status and save
		if(advice != null)
		{
			ServiceTransactionStatus transactionMatchedStatus = 
					serviceTransactionStatusService.findByCode(ServiceTransactionStatusService.MATCHED);
			transactionRequest.setServiceTransactionStatusId(transactionMatchedStatus.getId());
		}
		// 3. If it fails, then update status to unmatched and save
		else
		{
			ServiceTransactionStatus transactionUnMatchedStatus = 
					serviceTransactionStatusService.findByCode(ServiceTransactionStatusService.NOT_MATCHED);
			transactionRequest.setServiceTransactionStatusId(transactionUnMatchedStatus.getId());
		}
		return transaction;
	}
	
	private ServiceTransactionRequest iniatializeChqTxn(String accountNo, String chequeNo, BigDecimal amount, String crncyCode, Date txnDate)
	{
		ServiceTransactionRequest transaction = new ServiceTransactionRequest();
		transaction.setCode(accountNo.concat(chequeNo));
		transaction.setAccountNo(accountNo);
		transaction.setName(accountNo);
		transaction.setAmount(amount);
		transaction.setChequeNo(chequeNo);
		transaction.setCreatedDt(txnDate);
		transaction.setCreatedByUsr("CTS");
		transaction.setEffectiveDt(txnDate);
		transaction.setTxnDate(txnDate);
		return transaction;
	}

	
}
