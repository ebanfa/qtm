/**
 * 
 */
package com.nathanclaire.alantra.channel.service.process;

import java.math.BigDecimal;
import java.util.List;

//import com.nathanclaire.alantra.advice.model.Advice;
import com.nathanclaire.alantra.channel.model.ServiceTransaction;

/**
 * @author Edward Banfa 
 *
 */
public class ServiceTransactionBusinessServiceImpl implements
		ServiceTransactionBusinessService {
	
	//@Inject
	//AdviceService adviceService;

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.process.ServiceTransactionBusinessService#isAuthorized(com.nathanclaire.alantra.channel.model.ServiceTransaction)
	 */
	@Override
	public boolean isAuthorized(ServiceTransaction transaction) {
		// 1. Since the transaction is defined by its type, its amount and 
		// the target account we use these parameters as the most significant 
		// determining criteria here, i.e if an advice is not found
		// then we simple short circuit the entire invocation of this method
		BigDecimal amount = transaction.getAmount();
		String accountNo = transaction.getAccountNo();
		String transactionType = transaction.getServiceTransactionType().getCode();
		// Find the advice
		//List<Advice> advices = findAdvice(transactionType, accountNo, amount);
		//if (advices.isEmpty()) return false;
		return true;
	}
	
	/*private List<Advice> findAdvice(String transactionType, String accountNo, BigDecimal amount)
	{
		return null;//adviceService.findAdvice(transactionType, accountNo, amount);
	}*/
	

}
