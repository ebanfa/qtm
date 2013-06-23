/**
 * 
 */
package com.nathanclaire.alantra.advice.service.process;

import java.math.BigDecimal;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.advice.model.Advice;
import com.nathanclaire.alantra.advice.model.AdviceStatus;
import com.nathanclaire.alantra.advice.model.AdviceType;
import com.nathanclaire.alantra.advice.service.entity.AdviceService;
import com.nathanclaire.alantra.advice.service.entity.AdviceServiceImpl;
import com.nathanclaire.alantra.advice.service.entity.AdviceStatusService;
import com.nathanclaire.alantra.advice.service.entity.AdviceTypeService;
import com.nathanclaire.alantra.base.service.process.BaseProcessService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.businessdata.model.Currency;
import com.nathanclaire.alantra.customer.model.Customer;
import com.nathanclaire.alantra.customer.model.CustomerAccount;
import com.nathanclaire.alantra.customer.service.entity.CustomerAccountService;
import com.nathanclaire.alantra.transaction.model.ServiceTransaction;
import com.nathanclaire.alantra.transaction.model.ServiceTransactionType;

/**
 * @author Edward Banfa 
 *
 */
public class TransactionMatchingServiceImpl extends BaseProcessService
		implements TransactionMatchingService {

	@Inject
	AdviceService adviceService;
	@Inject
	AdviceTypeService adviceTypeService;
	@Inject
	AdviceStatusService adviceStatusService;
	@Inject
	CustomerAccountService customerAccountService;
	
	private Logger logger = LoggerFactory.getLogger(AdviceServiceImpl.class);
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.process.TransactionMatchingService#match(com.nathanclaire.alantra.transaction.model.ServiceTransaction)
	 */
	@Override
	public Advice match(ServiceTransaction transaction) throws ApplicationException  {
		// 1. Get the account number from the transaction
		// 2. Locate the account that is referenced by that number
		CustomerAccount account = transaction.getCustomerAccount();
		if(account == null)
			throw new ApplicationException(CUSTOMER_ACCOUNT_NOT_FOUND);
		// 3. Get the customer that has that account
		Customer customer = account.getCustomer();
		if(customer == null)
			throw new ApplicationException(CUSTOMER_NOT_FOUND);
		// 4. Get the amount of the transaction
		BigDecimal amount = transaction.getAmount();
		// 5. Get the currency of the transaction
		Currency currency = transaction.getCurrency();
		if(currency == null)
			throw new ApplicationException(TRANSACTION_CURRENCY_NOT_FOUND);
		// 7. Get the advice type for the transaction type
		Integer adviceTypeId = null;
		String transactionTypeCode = null;
		ServiceTransactionType transactionType = transaction.getServiceTransactionType();
		if(transactionType != null)
		{
			transactionTypeCode = transactionType.getCode();
			AdviceType adviceType = adviceTypeService.findByCode(transactionTypeCode);
			if(adviceType != null){
				adviceTypeId = adviceType.getId();
			}
		}
		// 8. Get the status for unmatched advices
		Integer adviceStatusId = null;
		AdviceStatus adviceStatus = adviceStatusService.findByCode(AdviceStatusService.UNPROCESSED_ADVICE_CODE);
		if (adviceStatus != null){
			adviceStatusId = adviceStatus.getId();
		}
		logger.info("Finding advice with Customer Id: {}, Account Id: {}, Cheque No: {}, " +
				"Currency Id: {}, Advice Type Id: {}, Status Id: {}, Amount: {}", customer.getId(), account.getId(), 
				transaction.getChequeNo(), currency.getId(), adviceTypeId, adviceStatusId, amount);
		/*Advice advice = 
				adviceService.findAdvice(customer.getId(), account.getId(), 
						transaction.getChequeNo(), currency.getId(), adviceTypeId, adviceStatusId, amount);*/
		return null;
	}
	
	
	
	

}
