/**
 * 
 */
package com.nathanclaire.alantra.customer.service.process;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.model.BaseEntity;
import com.nathanclaire.alantra.base.request.BaseRequest;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtil;
import com.nathanclaire.alantra.businessdata.model.Currency;
import com.nathanclaire.alantra.businessdata.service.process.BusinessDataService;
import com.nathanclaire.alantra.customer.annotation.CustomerDataInputService;
import com.nathanclaire.alantra.customer.model.AccountType;
import com.nathanclaire.alantra.customer.model.Customer;
import com.nathanclaire.alantra.customer.model.CustomerClassification;
import com.nathanclaire.alantra.customer.model.CustomerType;
import com.nathanclaire.alantra.customer.request.AccountRequest;
import com.nathanclaire.alantra.customer.request.CustomerRequest;
import com.nathanclaire.alantra.datasource.etl.service.BaseEntityDataInputService;
import com.nathanclaire.alantra.datasource.etl.service.EntityDataInputService;
import com.nathanclaire.alantra.datasource.etl.util.TableData;
import com.nathanclaire.alantra.datasource.model.DataChannel;

/**
 * @author Edward Banfa 
 *
 */
@Stateless
@CustomerDataInputService
public class CustomerDataInputServiceImpl extends BaseEntityDataInputService implements EntityDataInputService {

	@Inject CustomerService customerService;
	@Inject CustomerAccountService accountService;
	@Inject BusinessDataService businessDataService;
	private Logger logger = LoggerFactory.getLogger(getClass());
	private static final String CDIS_DATA_INPUT_ERROR = "CustomerDataInputServiceImpl.CDIS_DATA_INPUT_ERROR";
	
	@Override
	public BaseEntity doDataInput(DataChannel channel,
			BaseRequest primaryEntity, BaseRequest secondaryEntity,	TableData tableData) throws ApplicationException {

		try {
			PropertyUtil.initializeBaseFields(primaryEntity);
			CustomerRequest customerRequest = new CustomerRequest();
			Customer customer = customerService.getCustomer(customerRequest.getCode());
			if(customer == null){
				CustomerType customerType = customerService.getCustomerType(customerRequest.getCustomerTypeText());
				CustomerClassification classification = 
						customerService.getCustomerClassification(customerRequest.getCustomerClassificationText());
				customer =  customerService.createCustomer(customerType, classification, customerRequest.getName(), 
						customerService.generateCustomerPin(), customerRequest.getPrimaryEmail(), customerRequest.getPrimaryMobile());
			}
			else{
				PropertyUtil.initializeBaseFields(secondaryEntity);
				AccountRequest accountRequest = (AccountRequest) secondaryEntity;
				if(accountService.getCustomerAccount(accountRequest.getAccountNo()) != null) 
					throw new ApplicationException("");
				else{
					AccountType accountType = accountService.getAccountType(accountRequest.getAccountTypeText());
					Currency currency = businessDataService.getCurrency(accountRequest.getCurrencyText());
					accountService.createCustomerAccount(customer, accountType, 
							currency, accountRequest.getName(), accountRequest.getAccountNo(), 'N', 'Y');
				}
			}
			return customer;
		} catch (Exception e) {
			logger.error("Error processing customer account data input: {}", e.getMessage());
			throw new ApplicationException(CDIS_DATA_INPUT_ERROR, e.getMessage());
		}
	}
	
	
	
}
