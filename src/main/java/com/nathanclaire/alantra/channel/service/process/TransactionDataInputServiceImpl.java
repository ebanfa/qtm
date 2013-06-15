/**
 * 
 */
package com.nathanclaire.alantra.channel.service.process;

import javax.ejb.Stateless;
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
import com.nathanclaire.alantra.channel.model.ServiceTransaction;
import com.nathanclaire.alantra.channel.model.ServiceTransactionStatus;
import com.nathanclaire.alantra.channel.model.ServiceTransactionType;
import com.nathanclaire.alantra.channel.request.ServiceTransactionRequest;
import com.nathanclaire.alantra.channel.service.entity.ServiceTransactionService;
import com.nathanclaire.alantra.channel.service.entity.ServiceTransactionStatusService;
import com.nathanclaire.alantra.channel.service.entity.ServiceTransactionTypeService;
import com.nathanclaire.alantra.customer.model.CustomerAccount;
import com.nathanclaire.alantra.customer.service.entity.CustomerAccountService;
import com.nathanclaire.alantra.datasource.etl.TableData;
import com.nathanclaire.alantra.datasource.model.DataChannel;
import com.nathanclaire.alantra.datasource.service.entity.DataChannelService;

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
	@Inject ServiceTransactionTypeService serviceTransactionTypeService;
	@Inject ServiceTransactionStatusService serviceTransactionStatusService;
	private static final String TRANSACTION_MATCH_PENDING_STATUS_NOT_FOUND = 
			"TransactionDataInputServiceImpl.TRANSACTION_MATCH_PENDING_STATUS_NOT_FOUND";
	private static final String CHQ_TXN_TYPE_NOT_FOUND = "TransactionDataInputServiceImpl.CHQ_TXN_TYPE_NOT_FOUND";
	private static final String CUSTOMER_ACCOUNT_DOES_NOT_EXIST = "TransactionDataInputServiceImpl.CUSTOMER_ACCOUNT_DOES_NOT_EXIST";
	
	private Logger logger = LoggerFactory.getLogger(TransactionDataInputServiceImpl.class);
	

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.process.TransactionDataInputService#processTransactionRequest()
	 */
	@Override
	public BaseEntity processDataInput(BaseRequest primaryEntityRequest, BaseRequest secEntityRequest, TableData tableData) throws ApplicationException {
		PropertyUtils.initializeBaseFields(primaryEntityRequest);
		ServiceTransactionRequest transactionRequest = (ServiceTransactionRequest) primaryEntityRequest;
		transactionRequest.setDataChannelId(getDataImportService(tableData.getSourceServiceCode()).getId());
		transactionRequest.setServiceTransactionStatusId(getTransactionPendingStatus().getId());
		CustomerAccount account = getTransactionAccount(transactionRequest.getCustomerAccountId());
		String transactionCode = processTransactionType(transactionRequest, account);
		transactionRequest.setCode(transactionCode);
		transactionRequest.setName(account.getName());
		transactionRequest.setCustomerAccountId(account.getId());
		ServiceTransaction transaction = serviceTransactionService.findByCode(transactionCode);
		if(transaction != null)
			throw new ApplicationException(TRANSACTION_ALREADY_EXISTS);
		return serviceTransactionService.create(transactionRequest);
	}
	
	private String processTransactionType(ServiceTransactionRequest transactionRequest, 
			CustomerAccount account) throws ApplicationException
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
