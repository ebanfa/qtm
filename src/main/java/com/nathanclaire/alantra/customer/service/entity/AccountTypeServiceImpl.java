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

import com.nathanclaire.alantra.customer.model.AccountType;
import com.nathanclaire.alantra.customer.request.AccountTypeRequest;
import com.nathanclaire.alantra.customer.response.AccountTypeResponse;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtil;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class AccountTypeServiceImpl 
	extends BaseEntityServiceImpl<AccountType, AccountTypeResponse, AccountTypeRequest> 
	implements AccountTypeService
{
	private static final String ENTITY_NAME = "AccountType";
	private static final String LIST_ACTIVITY_CODE = "LIST_CUSTOMER_ACCOUNTTYPE";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_CUSTOMER_ACCOUNTTYPE";
	
	private Logger logger = LoggerFactory.getLogger(AccountTypeServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	
	/**
	 * @param entityClass
	 */
	public AccountTypeServiceImpl() {
		super(AccountType.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.AccountType#findById(java.lang.Integer)
	 */
	@Override
	public AccountType findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.AccountType#findByCode(java.lang.String)
	 */
	@Override
	public AccountType findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.AccountType#findByName(java.lang.String)
	 */
	@Override
	public AccountType findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.AccountType#findAll(java.util.Map)
	 */
	@Override
	public List<AccountType> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.AccountType#createAccountType(com.nathanclaire.alantra.customer.rest.request.ServiceRequest)
	 */
	@Override
	public AccountType create(AccountTypeRequest accountTypeRequest) throws ApplicationException {
		return createInstance(accountTypeRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.AccountType#deleteAccountType(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.AccountType#updateAccountType(com.nathanclaire.alantra.customer.rest.request.ServiceRequest)
	 */
	@Override
	public AccountType update(AccountTypeRequest accountTypeRequest) throws ApplicationException {
		return updateInstance(accountTypeRequest);
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
    	
		return listItems;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#asListItem()
	 */
	@Override
	public List<ListItemResponse> asListItem() throws ApplicationException {
		List<ListItemResponse> listItems = new ArrayList<ListItemResponse>();
		queryParameters.clear();
		for(AccountType accounttype: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(accounttype.getId(), accounttype.getCode(), accounttype.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public AccountType convertRequestToModel(AccountTypeRequest accountTypeRequest) 
     throws ApplicationException {
		AccountType accountType = new AccountType();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtil.copyProperties(accountTypeRequest, accountType, allowedEntityFields);
    	//Process many to one relationships
		return accountType;
	}
	
	@Override
	public AccountTypeResponse convertModelToResponse(AccountType model) throws ApplicationException {
		if (model == null) return null;
		AccountTypeResponse accountTypeResponse = new AccountTypeResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtil.copyProperties(model, accountTypeResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		return accountTypeResponse;
	}
}
