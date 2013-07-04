/**
 * 
 */
package com.nathanclaire.alantra.customer.service.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.MultivaluedMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.application.model.ApplicationEntityField;

import com.nathanclaire.alantra.customer.model.Account;
import com.nathanclaire.alantra.businessdata.model.Currency;
import com.nathanclaire.alantra.customer.model.AccountType;
import com.nathanclaire.alantra.customer.request.AccountRequest;
import com.nathanclaire.alantra.customer.response.AccountResponse;
import com.nathanclaire.alantra.businessdata.service.entity.CurrencyService;
import com.nathanclaire.alantra.customer.service.entity.AccountTypeService;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtils;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class AccountServiceImpl 
	extends BaseEntityServiceImpl<Account, AccountResponse, AccountRequest> 
	implements AccountService
{
	private static final String LIST_ITEM_CURRENCY = "currency";
	private static final String LIST_ITEM_ACCOUNTTYPE = "accountType";
	private static final String ENTITY_NAME = "Account";
	private static final String LIST_ACTIVITY_CODE = "LIST_CUSTOMER_ACCOUNT";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_CUSTOMER_ACCOUNT";
	
	private Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	@Inject
	CurrencyService  currencyService;
	@Inject
	AccountTypeService  accountTypeService;
	
	/**
	 * @param entityClass
	 */
	public AccountServiceImpl() {
		super(Account.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.Account#findById(java.lang.Integer)
	 */
	@Override
	public Account findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.Account#findByCode(java.lang.String)
	 */
	@Override
	public Account findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.Account#findByName(java.lang.String)
	 */
	@Override
	public Account findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.Account#findAll(java.util.Map)
	 */
	@Override
	public List<Account> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.Account#createAccount(com.nathanclaire.alantra.customer.rest.request.ServiceRequest)
	 */
	@Override
	public Account create(AccountRequest accountRequest) throws ApplicationException {
		return createInstance(accountRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.Account#deleteAccount(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.Account#updateAccount(com.nathanclaire.alantra.customer.rest.request.ServiceRequest)
	 */
	@Override
	public Account update(AccountRequest accountRequest) throws ApplicationException {
		return updateInstance(accountRequest);
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#getListActivityCode()
	 */
	@Override
	public String getListActivityCode() throws ApplicationException {
		return LIST_ACTIVITY_CODE;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#getEditActivityCode()
	 */
	@Override
	public String getEditActivityCode() throws ApplicationException {
		return EDIT_ACTIVITY_CODE;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#getEntityName()
	 */
	@Override
	public String getEntityName() throws ApplicationException {
		return ENTITY_NAME;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#getEntityFields()
	 */
	@Override
	public List<ApplicationEntityField> getEntityFields() throws ApplicationException {
		return applicationEntityService.getFieldsForEntity(ENTITY_NAME);
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#relatedEntitesToListItems()
	 */
	@Override
	public Map<String, List<ListItemResponse>> relatedEntitesToListItems() 
	 throws ApplicationException {
		Map<String, List<ListItemResponse>> listItems = new HashMap<String, List<ListItemResponse>>(); 
		List<ListItemResponse> currencys = currencyService.asListItem();
		List<ListItemResponse> accountTypes = accountTypeService.asListItem();
    	
		listItems.put(LIST_ITEM_CURRENCY, currencys); 
		listItems.put(LIST_ITEM_ACCOUNTTYPE, accountTypes); 
		return listItems;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#asListItem()
	 */
	@Override
	public List<ListItemResponse> asListItem() throws ApplicationException {
		List<ListItemResponse> listItems = new ArrayList<ListItemResponse>();
		queryParameters.clear();
		for(Account account: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(account.getId(), account.getCode(), account.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public Account convertRequestToModel(AccountRequest accountRequest) 
     throws ApplicationException {
		Account account = new Account();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(accountRequest, account, allowedEntityFields);
    	//Process many to one relationships
    	if (accountRequest.getCurrencyId() != null)
    	{
    		Currency currency = getEntityManager().find(Currency.class, accountRequest.getCurrencyId());
    		account.setCurrency(currency);
    	}
    	if (accountRequest.getAccountTypeId() != null)
    	{
    		AccountType accountType = getEntityManager().find(AccountType.class, accountRequest.getAccountTypeId());
    		account.setAccountType(accountType);
    	}
		return account;
	}
	
	@Override
	public AccountResponse convertModelToResponse(Account model) throws ApplicationException {
		if (model == null) return null;
		AccountResponse accountResponse = new AccountResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(model, accountResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		if(model.getCurrency() != null)
			accountResponse.setCurrencyId(model.getCurrency().getId());
			accountResponse.setCurrencyText(model.getCurrency().getName());
		if(model.getAccountType() != null)
			accountResponse.setAccountTypeId(model.getAccountType().getId());
			accountResponse.setAccountTypeText(model.getAccountType().getName());
		return accountResponse;
	}
}
