/**
 * 
 */
package com.nathanclaire.alantra.transaction.service.process;

import java.util.Set;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.advice.model.Advice;
import com.nathanclaire.alantra.advice.model.AdviceStatus;
import com.nathanclaire.alantra.advice.model.AdviceType;
import com.nathanclaire.alantra.advice.service.entity.AdviceServiceImpl;
import com.nathanclaire.alantra.advice.service.entity.AdviceStatusService;
import com.nathanclaire.alantra.advice.service.entity.AdviceTypeService;
import com.nathanclaire.alantra.advice.service.process.AdviceProcessingService;
import com.nathanclaire.alantra.base.service.process.BaseProcessService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.businessdata.model.Currency;
import com.nathanclaire.alantra.customer.model.Customer;
import com.nathanclaire.alantra.customer.model.CustomerAccount;
import com.nathanclaire.alantra.transaction.model.ServiceTransaction;
import com.nathanclaire.alantra.transaction.model.ServiceTransactionType;

/**
 * @author Edward Banfa 
 *
 */
@Stateless
public class TransactionMatchingServiceImpl extends BaseProcessService implements TransactionMatchingService {
	

	@Inject	AdviceProcessingService adviceProcessingService;
	@Inject	AdviceTypeService adviceTypeService;
	@Inject	AdviceStatusService adviceStatusService;
	private Logger logger = LoggerFactory.getLogger(TransactionMatchingServiceImpl.class);
	

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.transaction.service.process.TransactionMatchingService#match(com.nathanclaire.alantra.transaction.model.ServiceTransaction)
	 */
	@Override
	public Advice match(ServiceTransaction transaction) throws ApplicationException {
		validateTransaction(transaction);
		// 7. Get the advice type for the transaction type
		ServiceTransactionType transactionType = 
				transaction.getServiceTransactionType();
		Set<AdviceType> adviceTypes = transactionType.getAdviceTypes();
		AdviceType adviceType = null;
		// If we don't find a advice type means one has not been configured 
		// in that case we don't throw an exception, we just return null
		if(adviceTypes.isEmpty())
			return null;
		// Use the first entry we find.
		for (AdviceType entry : adviceTypes)
		{
			adviceType = entry;
			break;
		}
		// 8. Get the status for unmatched advices
		AdviceStatus adviceStatus = adviceStatusService.findByCode(AdviceStatusService.UNPROCESSED_ADVICE_CODE);
		if (adviceStatus == null)
			throw new ApplicationException(CONFIG_ERROR_ADVICE_STATUS_NOT_FOUND, AdviceStatusService.UNPROCESSED_ADVICE_CODE);
		// The customer account
		CustomerAccount account = transaction.getCustomerAccount();

		logger.info("Finding advice with Customer Id: {}, Account Id: {}, Cheque No: {}, cardNo: {}" +
				"Currency Id: {}, Advice Type Id: {}, Status Id: {}, Amount: {}", account.getCustomer().getId(),
				account.getId(), transaction.getChequeNo(), transaction.getCardNo(), 
				transaction.getCurrency().getId(), adviceType.getId(), adviceStatus.getId(), transaction.getAmount());
		// Find the advice
		return adviceProcessingService.findAdvice(account.getCustomer().getId(),
				account.getId(), transaction.getChequeNo(), transaction.getCardNo(), 
				transaction.getCurrency().getId(), adviceType.getId(), adviceStatus.getId(), transaction.getAmount());
	}

	/**
	 * @param transaction
	 * @throws ApplicationException
	 */
	private void validateTransaction(ServiceTransaction transaction) throws ApplicationException {
		// 1. Confirm the account
		CustomerAccount account = transaction.getCustomerAccount();
		if(account == null)
			throw new ApplicationException(CUSTOMER_ACCOUNT_NOT_FOUND);
		// 2. Get the customer that has that account
		Customer customer = account.getCustomer();
		if(customer == null)
			throw new ApplicationException(CUSTOMER_NOT_FOUND);
		// 3. Get the currency of the transaction
		Currency currency = transaction.getCurrency();
		if(currency == null)
			throw new ApplicationException(TRANSACTION_CURRENCY_NOT_FOUND);
	}


}
