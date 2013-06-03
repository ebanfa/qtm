/**
 * 
 */
package com.nathanclaire.alantra.advice.service.process;

import java.math.BigDecimal;

import javax.inject.Inject;

import com.nathanclaire.alantra.advice.model.Advice;
import com.nathanclaire.alantra.advice.model.AdviceStatus;
import com.nathanclaire.alantra.advice.model.AdviceType;
import com.nathanclaire.alantra.advice.service.entity.AdviceService;
import com.nathanclaire.alantra.advice.service.entity.AdviceStatusService;
import com.nathanclaire.alantra.advice.service.entity.AdviceTypeService;
import com.nathanclaire.alantra.base.service.process.BaseProcessService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.businessdata.model.Currency;
import com.nathanclaire.alantra.channel.model.ServiceTransaction;
import com.nathanclaire.alantra.channel.model.ServiceTransactionType;
import com.nathanclaire.alantra.customer.model.Customer;
import com.nathanclaire.alantra.customer.model.CustomerAccount;
import com.nathanclaire.alantra.customer.service.entity.CustomerAccountService;

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
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.process.TransactionMatchingService#match(com.nathanclaire.alantra.channel.model.ServiceTransaction)
	 */
	@Override
	public Advice match(ServiceTransaction transaction) throws ApplicationException  {
		// TODO Auto-generated method stub
		// 1. Get the account number from the transaction
		String accountNo = transaction.getAccountNo();
		// 2. Locate the account that is referenced by that number
		Integer accountId = null;
		Integer customerId = null;
		CustomerAccount customerAccount = customerAccountService.findByCode(accountNo);
		if(customerAccount != null)
		{
			accountId = customerAccount.getId();
			// 3. Get the customer that has that account
			Customer customer = customerAccount.getCustomer();
			customerId = customer.getId();
		}
		// 4. Get the amount of the transaction
		BigDecimal amount = transaction.getAmount();
		// 5. Get the currency of the transaction
		Integer currencyId = null;
		Currency currency = transaction.getCurrency();
		if(currency != null){
			currencyId = currency.getId();
		}
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
		Advice advice = 
				adviceService.findAdvice(customerId, accountId, 
						currencyId, adviceTypeId, adviceStatusId, amount);
		return advice;
	}
	
	
	
	

}
