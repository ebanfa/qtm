/**
 * 
 */
package com.nathanclaire.alantra.advice.service.process;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.advice.model.AdviceType;
import com.nathanclaire.alantra.advice.model.AdviceTypeTag;
import com.nathanclaire.alantra.advice.service.entity.AdviceTypeService;
import com.nathanclaire.alantra.advice.service.entity.AdviceTypeTagService;
import com.nathanclaire.alantra.base.service.process.BaseProcessService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.StringUtil;
import com.nathanclaire.alantra.businessdata.model.Currency;
import com.nathanclaire.alantra.businessdata.service.process.BusinessDataProcessingService;
import com.nathanclaire.alantra.customer.model.Account;
import com.nathanclaire.alantra.customer.model.AccountType;
import com.nathanclaire.alantra.customer.model.Customer;
import com.nathanclaire.alantra.customer.model.CustomerAccount;
import com.nathanclaire.alantra.customer.service.process.CustomerProcessingService;

/**
 * @author Edward Banfa 
 *
 */
@Stateless
public class AdviceTextProcessingServiceImpl extends BaseProcessService
		implements AdviceTextProcessingService {

	private static final String INVALID_ADVICE_TEXT_SPECIFIED_FOR_ACCOUNT_SEARCH = null;
	private static final String INVALID_ACCT_MAP_SPECIFIED_FOR_ACCOUNT_SEARCH = null;
	@Inject CustomerProcessingService customerService;
	@Inject AdviceTypeTagService adviceTypeTagService;
	@Inject BusinessDataProcessingService businessDataProcessingService;
	@Inject AdviceRequestMessageProcessingService adviceRequestMessageProcessingService;
	private Logger logger = LoggerFactory.getLogger(AdviceTextProcessingServiceImpl.class);
	
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.process.AdviceProcessingService#processAdviceText(java.lang.Integer, java.lang.String, java.lang.String, java.lang.Integer)
	 */
	@Override
	public void processAdviceText(Integer customerId, String sourceAddress, 
			String adviceText, Integer dataChannelId) throws ApplicationException 
	{
		logger.debug("Processing advice text {}", adviceText);
		// 1. Get the customer
		Customer customer = customerService.getCustomerById(customerId);
		logger.debug("Verifying customer {}", customer);
		// 2. Verify the sourceAddress belongs to the customer
		if(!customerService.verifyCustomerContact(customer, sourceAddress))
			throw new ApplicationException(INCORRECT_CUSTOMER_SOURCE_ADDRESS);
		logger.debug("Loading customer account mappings");
		// 4. Get all accounts of the customer
		Set<CustomerAccount> accountMappings = customer.getCustomerAccounts();
		if(accountMappings.isEmpty())
			throw new ApplicationException(CUSTOMER_HAS_NO_ACCOUNTS);
		// 5. Find the the advice type based on the advice type tags defined
		AdviceType adviceTypeInAdviceText = getAdviceTypeInAdviceText(adviceText);
		// 6. Look for the account number(s) in the advice text
		List<Account> accountsInAdviceText = findAccountsInAdviceText(accountMappings, adviceText);
		// 9. Get the currency from the advise text or use the currency of the account
		List<Currency> currencysInAdviceText = findCurrencyInAdviceText(adviceText);
		// 10. Extract check number if this is a check transaction (Use advice type to discern this)
		String chequeNoInAdviceText = null;
		if(adviceTypeInAdviceText.getCode().equals(AdviceTypeService.CHQ_WITHDRAWAL))
			chequeNoInAdviceText = findChequeNoInAdviceText(accountsInAdviceText, adviceText);
		// 11. Else is this is a card transaction the get card number (Use advice type to discern this)
		String cardNoInAdviceText = null;
		if(adviceTypeInAdviceText.getCode().equals(AdviceTypeService.ATM_WITHDRAWAL_ADVICE))
			cardNoInAdviceText = findCardNoInAdviceText(accountsInAdviceText, adviceText);
		// 12. Set the advice amount
		BigDecimal amountInAdviceText = getAmountInAdviceText(adviceText);
		Account account =  customerService.getDefaultCustomerAccount(customer, accountsInAdviceText);
		Currency currency = businessDataProcessingService.getDefaultCurrency(accountsInAdviceText, currencysInAdviceText);
		// 13. Create advice AdviceRequestMessageRequestObject
		adviceRequestMessageProcessingService.createAdviceRequest(customer, sourceAddress, account, currency, 
				amountInAdviceText, chequeNoInAdviceText, cardNoInAdviceText, adviceTypeInAdviceText, dataChannelId, adviceText);
	}
	/**
	 * @param adviceText
	 * @return
	 * @throws ApplicationException
	 */
	@Override
	public AdviceType getAdviceTypeInAdviceText(String adviceText) throws ApplicationException {
		logger.debug("Searching for advice type in advice text {}", adviceText);
		// Group 1 is the advice type tag
		String adviceTypeText = StringUtil.extractRegexGroupFromText(adviceText, ADVICE_TEXT_REGEX_PATTERN, 5);
		logger.debug("Extracted advice type {} from advice text {}", adviceTypeText, adviceText);
		// Abort if not found
		if(!StringUtil.isValidString(adviceTypeText))
			throw new ApplicationException(ADVICE_TY_TEXT_NOT_FOUND_ADVICE_TEXT);
		// Loop through all tags and match with the advice type text
		// returning the first match
		AdviceType adviceType = null;
		List<AdviceTypeTag> adviceTypeTags = adviceTypeTagService.findAll(null);
		for(AdviceTypeTag tag : adviceTypeTags)
		{
			boolean isRegexTag = StringUtil.flagToBoolean(tag.getIsRegexFg());
			String matchResult = StringUtil.match(adviceTypeText, tag.getAdviceTyTagVal(), isRegexTag);
			if(StringUtil.isValidString(matchResult))
				adviceType = tag.getAdviceType();
		}
		logger.debug("Found advice type {} in advice text {}", adviceType, adviceText);
		if(adviceType == null)
			throw new ApplicationException(ADVICE_TY_NOT_FOUND_ADVICE_TEXT);
		return adviceType;
	}

	/**
	 * Find the account defined in the advice text.
	 * Since we know the accounts of the customer, we do
	 * a simple search and see if the string contains the account
	 * no of any of the customer's accounts.
	 * 
	 * @param accountMappings
	 * @param adviceText
	 * @return
	 * @throws ApplicationException
	 */
	@Override
	public List<Account> findAccountsInAdviceText(	Set<CustomerAccount> accountMappings, String adviceText)  
			throws ApplicationException 
	{
		if(!StringUtil.isValidString(adviceText))
			throw new ApplicationException(INVALID_ADVICE_TEXT_SPECIFIED_FOR_ACCOUNT_SEARCH);
		if(accountMappings == null)
			throw new ApplicationException(INVALID_ACCT_MAP_SPECIFIED_FOR_ACCOUNT_SEARCH);
		logger.debug("Searching for accounts in advice text {} using customer " +
				"account map containing {} accounts", adviceText, accountMappings.size());
		List<Account> accountsInAdviceText = new ArrayList<Account>();
		for(CustomerAccount mapping : accountMappings)
		{
			Account account = mapping.getAccount();
			if(account == null) throw new ApplicationException(CONFIG_ERROR_INVALID_ACCOUNT_MAPPING);
			String matchResult = StringUtil.match(adviceText, account.getAccountNo(), false);
			if(StringUtil.isValidString(matchResult))
				accountsInAdviceText.add(account);
		}
		return accountsInAdviceText;
	}

	/**
	 * Searches for the currency symbol in the advice text. This needs
	 * to be improved for single letter currency symbols
	 * 
	 * @param adviceText
	 * @return
	 * @throws ApplicationException
	 */
	@Override
	public List<Currency> findCurrencyInAdviceText(String adviceText) throws ApplicationException {
		List<Currency> currencies = new ArrayList<Currency>();
		for(Currency currency : businessDataProcessingService.getAllCurrencies())
		{
			String matchResult = StringUtil.match(adviceText, currency.getCrncySym(), false);
			if(StringUtil.isValidString(matchResult))
				currencies.add(currency);
		}
		return currencies;
	}

	/**
	 * Use the card no format defined on the account type to search
	 * the advice text for the cheque number.
	 * 
	 * @param accounts
	 * @param adviceText
	 * @return
	 * @throws ApplicationException 
	 */
	@Override
	public String findChequeNoInAdviceText(List<Account> accounts, String adviceText) throws ApplicationException {
		for(Account account : accounts)
		{
			AccountType accountType = account.getAccountType();
			if(accountType == null) throw new ApplicationException(CONFIG_ERROR_ACCOUNT_TYPE_NOT_FOUND);
			String matchResult = StringUtil.match(adviceText, accountType.getCode(), true);
			if(StringUtil.isValidString(matchResult))
				return matchResult;
		}
		return null;
	}

	/**
	 * Use the card no format defined on the account type to search
	 * the advice text for the card number.
	 * 
	 * @param accounts
	 * @param adviceText
	 * @return
	 * @throws ApplicationException 
	 */
	@Override
	public String findCardNoInAdviceText(List<Account> accounts, String adviceText) throws ApplicationException {
		for(Account account : accounts)
		{
			AccountType accountType = account.getAccountType();
			if(accountType == null) throw new ApplicationException(CONFIG_ERROR_ACCOUNT_TYPE_NOT_FOUND);
			String matchResult = StringUtil.match(adviceText, accountType.getCode(), true);
			if(StringUtil.isValidString(matchResult))
				return matchResult;
		}
		return null;
	}

	/**
	 * Use the amount format regex to find the amount in the 
	 * advice text.
	 * 
	 * @param adviceText
	 * @return
	 * @throws ApplicationException
	 */
	@Override
	public BigDecimal getAmountInAdviceText(String adviceText) throws ApplicationException {
		logger.debug("Searching for advice amount in advice text {}", adviceText);
		String matchResult = StringUtil.match(adviceText, AMOUNT_GRP_REGEX_PATTERN, true);
		logger.debug("Found advice amount {} in advice text {}", matchResult, adviceText);
		if(!StringUtil.isValidString(matchResult))
			throw new ApplicationException(ADVICE_AMOUNT_NOT_SPECIFIED);
		BigDecimal amountInAdviceText = null;
		try 
		{
			amountInAdviceText = StringUtil.toBigDecimal(matchResult);
		} catch (NumberFormatException e) {
			throw new ApplicationException(INVALID_AMOUNT_STRING_SPECIFIED);
		}
		return amountInAdviceText;
	}

}
