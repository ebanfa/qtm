/**
 * 
 */
package com.nathanclaire.alantra.transaction.service.process;

import java.math.BigDecimal;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.nathanclaire.alantra.base.service.entity.BaseEntityService;
import com.nathanclaire.alantra.base.service.process.BaseProcessService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.customer.model.Customer;
import com.nathanclaire.alantra.transaction.model.CustCatTxnTypeConfirmationOptions;
import com.nathanclaire.alantra.transaction.model.CustCatTxnTypeNotificationOptions;
import com.nathanclaire.alantra.transaction.model.CustTxnTypeConfirmationOptions;
import com.nathanclaire.alantra.transaction.model.CustTxnTypeNotificationOptions;
import com.nathanclaire.alantra.transaction.model.CustTypeTxnTypeConfirmationOptions;
import com.nathanclaire.alantra.transaction.model.CustTypeTxnTypeNotificationOptions;
import com.nathanclaire.alantra.transaction.model.ServiceTransactionType;
import com.nathanclaire.alantra.transaction.service.entity.CustCatTxnTypeConfirmationOptionsService;
import com.nathanclaire.alantra.transaction.service.entity.CustCatTxnTypeNotificationOptionsService;
import com.nathanclaire.alantra.transaction.service.entity.CustTxnTypeConfirmationOptionsService;
import com.nathanclaire.alantra.transaction.service.entity.CustTxnTypeNotificationOptionsService;
import com.nathanclaire.alantra.transaction.service.entity.CustTypeTxnTypeConfirmationOptionsService;
import com.nathanclaire.alantra.transaction.service.entity.CustTypeTxnTypeNotificationOptionsService;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class CustomerTxnNotificationConfigurationServiceImpl extends
		BaseProcessService implements CustomerTxnNotificationConfigurationService {
	
	@Inject CustTxnTypeNotificationOptionsService custTxnTypeNotificationOptionsService;
	@Inject CustCatTxnTypeNotificationOptionsService custCatTxnTypeNotificationOptionsService;
	@Inject CustTypeTxnTypeNotificationOptionsService custTypeTxnTypeNotificationOptionsService;
	
	@Inject CustTxnTypeConfirmationOptionsService custTxnTypeConfirmationOptionsService;
	@Inject CustCatTxnTypeConfirmationOptionsService custCatTxnTypeConfirmationOptionsService;
	@Inject CustTypeTxnTypeConfirmationOptionsService custTypeTxnTypeConfirmationOptionsService;

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.transaction.service.process.CustomerTxnNotificationConfigurationService#getSMSNotificationAmount(com.nathanclaire.alantra.customer.model.Customer, com.nathanclaire.alantra.transaction.model.ServiceTransactionType)
	 */
	@Override
	public BigDecimal getSMSNotificationAmount(Customer customer,
			ServiceTransactionType serviceTransactionType)
			throws ApplicationException 
	{
		CustTxnTypeNotificationOptions  options = 
				custTxnTypeNotificationOptionsService.findCustomerOption(customer, serviceTransactionType);
		if(options != null)
			return options.getSmsAmount();
		else {
			CustTypeTxnTypeNotificationOptions customerTypeOptions = 
					custTypeTxnTypeNotificationOptionsService.findCustomerTypeOption(customer.getCustomerType(), serviceTransactionType);
			if(customerTypeOptions != null)
				return customerTypeOptions.getSmsAmount();
			else {
				CustCatTxnTypeNotificationOptions customerCategoryOptions = 
						custCatTxnTypeNotificationOptionsService.findCustomerCategoryOption(
								customer.getCustomerType().getCustomerCategory(), serviceTransactionType);
				if(customerCategoryOptions != null)
					return customerCategoryOptions.getSmsAmount();
				else
					throw new ApplicationException(BaseEntityService.ENTITY_INSTANCE_NOT_FOUND, "CustTxnTypeNotificationOptions");
			}
		}
		
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.transaction.service.process.CustomerTxnNotificationConfigurationService#getEMAILNotificationAmount(com.nathanclaire.alantra.customer.model.Customer, com.nathanclaire.alantra.transaction.model.ServiceTransactionType)
	 */
	@Override
	public BigDecimal getEMAILNotificationAmount(Customer customer,
			ServiceTransactionType serviceTransactionType)
			throws ApplicationException {
		CustTxnTypeNotificationOptions  options = 
				custTxnTypeNotificationOptionsService.findCustomerOption(customer, serviceTransactionType);
		if(options != null)
			return options.getEmailAmount();
		else {
			CustTypeTxnTypeNotificationOptions customerTypeOptions = 
					custTypeTxnTypeNotificationOptionsService.findCustomerTypeOption(customer.getCustomerType(), serviceTransactionType);
			if(customerTypeOptions != null)
				return customerTypeOptions.getEmailAmount();
			else {
				CustCatTxnTypeNotificationOptions customerCategoryOptions = 
						custCatTxnTypeNotificationOptionsService.findCustomerCategoryOption(
								customer.getCustomerType().getCustomerCategory(), serviceTransactionType);
				if(customerCategoryOptions != null)
					return customerCategoryOptions.getEmailAmount();
				else
					throw new ApplicationException(BaseEntityService.ENTITY_INSTANCE_NOT_FOUND, "CustTxnTypeNotificationOptions");
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.transaction.service.process.CustomerTxnNotificationConfigurationService#getIVRNotificationAmount(com.nathanclaire.alantra.customer.model.Customer, com.nathanclaire.alantra.transaction.model.ServiceTransactionType)
	 */
	@Override
	public BigDecimal getIVRNotificationAmount(Customer customer,
			ServiceTransactionType serviceTransactionType)
			throws ApplicationException 
	{
		CustTxnTypeNotificationOptions  options = 
				custTxnTypeNotificationOptionsService.findCustomerOption(customer, serviceTransactionType);
		if(options != null)
			return options.getIvrAmount();
		else {
			CustTypeTxnTypeNotificationOptions customerTypeOptions = 
					custTypeTxnTypeNotificationOptionsService.findCustomerTypeOption(customer.getCustomerType(), serviceTransactionType);
			if(customerTypeOptions != null)
				return customerTypeOptions.getIvrAmount();
			else {
				CustCatTxnTypeNotificationOptions customerCategoryOptions = 
						custCatTxnTypeNotificationOptionsService.findCustomerCategoryOption(
								customer.getCustomerType().getCustomerCategory(), serviceTransactionType);
				if(customerCategoryOptions != null)
					return customerCategoryOptions.getIvrAmount();
				else
					throw new ApplicationException(BaseEntityService.ENTITY_INSTANCE_NOT_FOUND, "CustTxnTypeNotificationOptions");
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.transaction.service.process.CustomerTxnNotificationConfigurationService#getSMSConfirmationAmount(com.nathanclaire.alantra.customer.model.Customer, com.nathanclaire.alantra.transaction.model.ServiceTransactionType)
	 */
	@Override
	public BigDecimal getSMSConfirmationAmount(Customer customer,
			ServiceTransactionType serviceTransactionType)
			throws ApplicationException {
		CustTxnTypeConfirmationOptions  options = 
				custTxnTypeConfirmationOptionsService.findCustomerOption(customer, serviceTransactionType);
		if(options != null)
			return options.getSmsAmount();
		else {
			CustTypeTxnTypeConfirmationOptions customerTypeOptions = 
					custTypeTxnTypeConfirmationOptionsService.findCustomerTypeOption(customer.getCustomerType(), serviceTransactionType);
			if(customerTypeOptions != null)
				return customerTypeOptions.getSmsAmount();
			else {
				CustCatTxnTypeConfirmationOptions customerCategoryOptions = 
						custCatTxnTypeConfirmationOptionsService.findCustomerCategoryOption(
								customer.getCustomerType().getCustomerCategory(), serviceTransactionType);
				if(customerCategoryOptions != null)
					return customerCategoryOptions.getSmsAmount();
				else
					throw new ApplicationException(BaseEntityService.ENTITY_INSTANCE_NOT_FOUND, "CustTxnTypeConfirmationOptions");
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.transaction.service.process.CustomerTxnNotificationConfigurationService#getEMAILConfirmationAmount(com.nathanclaire.alantra.customer.model.Customer, com.nathanclaire.alantra.transaction.model.ServiceTransactionType)
	 */
	@Override
	public BigDecimal getEMAILConfirmationAmount(Customer customer,
			ServiceTransactionType serviceTransactionType)
			throws ApplicationException {
		CustTxnTypeConfirmationOptions  options = 
				custTxnTypeConfirmationOptionsService.findCustomerOption(customer, serviceTransactionType);
		if(options != null)
			return options.getEmailAmount();
		else {
			CustTypeTxnTypeConfirmationOptions customerTypeOptions = 
					custTypeTxnTypeConfirmationOptionsService.findCustomerTypeOption(customer.getCustomerType(), serviceTransactionType);
			if(customerTypeOptions != null)
				return customerTypeOptions.getEmailAmount();
			else {
				CustCatTxnTypeConfirmationOptions customerCategoryOptions = 
						custCatTxnTypeConfirmationOptionsService.findCustomerCategoryOption(
								customer.getCustomerType().getCustomerCategory(), serviceTransactionType);
				if(customerCategoryOptions != null)
					return customerCategoryOptions.getEmailAmount();
				else
					throw new ApplicationException(BaseEntityService.ENTITY_INSTANCE_NOT_FOUND, "CustTxnTypeConfirmationOptions");
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.transaction.service.process.CustomerTxnNotificationConfigurationService#getIVRConfirmationAmount(com.nathanclaire.alantra.customer.model.Customer, com.nathanclaire.alantra.transaction.model.ServiceTransactionType)
	 */
	@Override
	public BigDecimal getIVRConfirmationAmount(Customer customer,
			ServiceTransactionType serviceTransactionType)
			throws ApplicationException 
	{
		CustTxnTypeConfirmationOptions  options = 
				custTxnTypeConfirmationOptionsService.findCustomerOption(customer, serviceTransactionType);
		if(options != null)
			return options.getIvrAmount();
		else {
			CustTypeTxnTypeConfirmationOptions customerTypeOptions = 
					custTypeTxnTypeConfirmationOptionsService.findCustomerTypeOption(customer.getCustomerType(), serviceTransactionType);
			if(customerTypeOptions != null)
				return customerTypeOptions.getIvrAmount();
			else {
				CustCatTxnTypeConfirmationOptions customerCategoryOptions = 
						custCatTxnTypeConfirmationOptionsService.findCustomerCategoryOption(
								customer.getCustomerType().getCustomerCategory(), serviceTransactionType);
				if(customerCategoryOptions != null)
					return customerCategoryOptions.getIvrAmount();
				else
					throw new ApplicationException(BaseEntityService.ENTITY_INSTANCE_NOT_FOUND, "CustTxnTypeConfirmationOptions");
			}
		}
	}

	
}
