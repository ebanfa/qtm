/**
 * 
 */
package com.nathanclaire.alantra.advice.service.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.ws.rs.core.MultivaluedMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.advice.model.Advice;
import com.nathanclaire.alantra.advice.model.AdviceClassification;
import com.nathanclaire.alantra.advice.model.AdviceStatus;
import com.nathanclaire.alantra.advice.model.AdviceType;
import com.nathanclaire.alantra.advice.request.AdviceRequest;
import com.nathanclaire.alantra.advice.response.AdviceResponse;
import com.nathanclaire.alantra.application.model.ApplicationEntityField;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtils;
import com.nathanclaire.alantra.businessdata.model.Currency;
import com.nathanclaire.alantra.businessdata.service.entity.CurrencyService;
import com.nathanclaire.alantra.customer.model.Customer;
import com.nathanclaire.alantra.customer.model.CustomerAccount;
import com.nathanclaire.alantra.customer.service.entity.CustomerAccountService;
import com.nathanclaire.alantra.customer.service.entity.CustomerService;
import com.nathanclaire.alantra.messaging.model.Message;
import com.nathanclaire.alantra.messaging.service.entity.MessageService;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class AdviceServiceImpl 
	extends BaseEntityServiceImpl<Advice, AdviceResponse, AdviceRequest> 
	implements AdviceService
{
	private static final String LIST_ITEM_CUSTOMER = "customer";
	private static final String LIST_ITEM_MESSAGE = "message";
	private static final String LIST_ITEM_CURRENCY = "currency";
	private static final String LIST_ITEM_ADVICESTATUS = "adviceStatus";
	private static final String LIST_ITEM_ADVICECLASSIFICATION = "adviceClassification";
	private static final String LIST_ITEM_CUSTOMERACCOUNT = "customerAccount";
	private static final String LIST_ITEM_ADVICETYPE = "adviceType";
	private static final String ENTITY_NAME = "Advice";
	private static final String LIST_ACTIVITY_CODE = "LIST_ADVICE_ADVICE";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_ADVICE_ADVICE";
	
	private Logger logger = LoggerFactory.getLogger(AdviceServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	@Inject
	CustomerService  customerService;
	@Inject
	MessageService  messageService;
	@Inject
	CurrencyService  currencyService;
	@Inject
	AdviceStatusService  adviceStatusService;
	@Inject
	AdviceClassificationService  adviceClassificationService;
	@Inject
	CustomerAccountService  customerAccountService;
	@Inject
	AdviceTypeService  adviceTypeService;
	
	/**
	 * @param entityClass
	 */
	public AdviceServiceImpl() {
		super(Advice.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.Advice#findById(java.lang.Integer)
	 */
	@Override
	public Advice findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.Advice#findByCode(java.lang.String)
	 */
	@Override
	public Advice findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.Advice#findByName(java.lang.String)
	 */
	@Override
	public Advice findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.Advice#findAll(java.util.Map)
	 */
	@Override
	public List<Advice> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.Advice#createAdvice(com.nathanclaire.alantra.advice.rest.request.ServiceRequest)
	 */
	@Override
	public Advice create(AdviceRequest adviceRequest) throws ApplicationException {
		return createInstance(adviceRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.Advice#deleteAdvice(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.Advice#updateAdvice(com.nathanclaire.alantra.advice.rest.request.ServiceRequest)
	 */
	@Override
	public Advice update(AdviceRequest adviceRequest) throws ApplicationException {
		return updateInstance(adviceRequest);
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
		List<ListItemResponse> customers = customerService.asListItem();
		List<ListItemResponse> messages = messageService.asListItem();
		List<ListItemResponse> currencys = currencyService.asListItem();
		List<ListItemResponse> adviceStatuss = adviceStatusService.asListItem();
		List<ListItemResponse> adviceClassifications = adviceClassificationService.asListItem();
		List<ListItemResponse> customerAccounts = customerAccountService.asListItem();
		List<ListItemResponse> adviceTypes = adviceTypeService.asListItem();
    	
		listItems.put(LIST_ITEM_CUSTOMER, customers); 
		listItems.put(LIST_ITEM_MESSAGE, messages); 
		listItems.put(LIST_ITEM_CURRENCY, currencys); 
		listItems.put(LIST_ITEM_ADVICESTATUS, adviceStatuss); 
		listItems.put(LIST_ITEM_ADVICECLASSIFICATION, adviceClassifications); 
		listItems.put(LIST_ITEM_CUSTOMERACCOUNT, customerAccounts); 
		listItems.put(LIST_ITEM_ADVICETYPE, adviceTypes); 
		return listItems;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#asListItem()
	 */
	@Override
	public List<ListItemResponse> asListItem() throws ApplicationException {
		List<ListItemResponse> listItems = new ArrayList<ListItemResponse>();
		queryParameters.clear();
		for(Advice advice: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(advice.getId(), advice.getCode(), advice.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public Advice convertRequestToModel(AdviceRequest adviceRequest) 
     throws ApplicationException {
		Advice advice = new Advice();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(adviceRequest, advice, allowedEntityFields);
    	//Process many to one relationships
    	if (adviceRequest.getCustomerId() != null)
    	{
    		Customer customer = getEntityManager().find(Customer.class, adviceRequest.getCustomerId());
    		advice.setCustomer(customer);
    	}
    	if (adviceRequest.getMessageId() != null)
    	{
    		Message message = getEntityManager().find(Message.class, adviceRequest.getMessageId());
    		advice.setMessage(message);
    	}
    	if (adviceRequest.getCurrencyId() != null)
    	{
    		Currency currency = getEntityManager().find(Currency.class, adviceRequest.getCurrencyId());
    		advice.setCurrency(currency);
    	}
    	if (adviceRequest.getAdviceStatusId() != null)
    	{
    		AdviceStatus adviceStatus = getEntityManager().find(AdviceStatus.class, adviceRequest.getAdviceStatusId());
    		advice.setAdviceStatus(adviceStatus);
    	}
    	if (adviceRequest.getAdviceClassificationId() != null)
    	{
    		AdviceClassification adviceClassification = getEntityManager().find(AdviceClassification.class, adviceRequest.getAdviceClassificationId());
    		advice.setAdviceClassification(adviceClassification);
    	}
    	if (adviceRequest.getCustomerAccountId() != null)
    	{
    		CustomerAccount customerAccount = getEntityManager().find(CustomerAccount.class, adviceRequest.getCustomerAccountId());
    		advice.setCustomerAccount(customerAccount);
    	}
    	if (adviceRequest.getAdviceTypeId() != null)
    	{
    		AdviceType adviceType = getEntityManager().find(AdviceType.class, adviceRequest.getAdviceTypeId());
    		advice.setAdviceType(adviceType);
    	}
		return advice;
	}
	
	@Override
	public AdviceResponse convertModelToResponse(Advice model) throws ApplicationException {
		if (model == null) return null;
		AdviceResponse adviceResponse = new AdviceResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(model, adviceResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		if(model.getCustomer() != null)
			adviceResponse.setCustomerId(model.getCustomer().getId());
			adviceResponse.setCustomerText(model.getCustomer().getName());
		if(model.getMessage() != null)
			adviceResponse.setMessageId(model.getMessage().getId());
			adviceResponse.setMessageText(model.getMessage().getCode());
		if(model.getCurrency() != null)
			adviceResponse.setCurrencyId(model.getCurrency().getId());
			adviceResponse.setCurrencyText(model.getCurrency().getName());
		if(model.getAdviceStatus() != null)
			adviceResponse.setAdviceStatusId(model.getAdviceStatus().getId());
			adviceResponse.setAdviceStatusText(model.getAdviceStatus().getName());
		if(model.getAdviceClassification() != null)
			adviceResponse.setAdviceClassificationId(model.getAdviceClassification().getId());
			adviceResponse.setAdviceClassificationText(model.getAdviceClassification().getName());
		if(model.getCustomerAccount() != null)
			adviceResponse.setCustomerAccountId(model.getCustomerAccount().getId());
			adviceResponse.setCustomerAccountText(model.getCustomerAccount().getName());
		if(model.getAdviceType() != null)
			adviceResponse.setAdviceTypeId(model.getAdviceType().getId());
			adviceResponse.setAdviceTypeText(model.getAdviceType().getName());
		return adviceResponse;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.entity.AdviceService#createAdvice(java.lang.String, java.lang.String, java.math.BigDecimal, java.lang.String, java.util.Date)
	 */
	@Override
	public Advice createAdvice(String adviceTypeCode, String accountNo, String chequeNo,
			BigDecimal amount, String crncyCode, Date txnDate) throws ApplicationException {
		Customer customer = this.getCustomerByAcctNo(accountNo);
		if (customer == null)return null;
		try {
			AdviceType adviceType = adviceTypeService.findByCode(adviceTypeCode);
			AdviceStatus unprocessedAdvice = 
					adviceStatusService.findByCode(AdviceStatusService.UNPROCESSED_ADVICE_CODE);
			Advice advice = new Advice();
			advice.setAdviceType(adviceType);
			advice.setAdviceStatus(unprocessedAdvice);
			advice.setCustomer(customer);
			advice.setName(customer.getName());
			advice.setAccountNm(accountNo);
			//advice.setAccountNo(accountNo);
			advice.setChequeNo(chequeNo);
			advice.setCode(customer.getName() + "_" + new Date().getTime());
			advice.setAmount(amount);
			advice.setEffectiveDt(txnDate);
			advice.setDescription(crncyCode);
			advice.setRecSt(BaseEntityServiceImpl.ENTITY_STATUS_ACTIVE);
			advice.setCreatedByUsr("CTS_USER");
			advice.setCreatedDt(new Date());
			this.getEntityManager().persist(advice);
			return advice;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * @param accountNo
	 * @return
	 */
	private Customer getCustomerByAcctNo(String accountNo) throws ApplicationException
	{
		CustomerAccount account = customerAccountService.findByCode(accountNo);
		if(account!=null)
		{
			return account.getCustomer();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.entity.AdviceService#findAdvice(java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.math.BigDecimal)
	 */
	@Override
	public Advice findAdvice(Integer customerId, Integer accountId,
			Integer currencyId, Integer adviceTypeId, Integer adviceStatusId, BigDecimal amount) {
		// Return the single  instance that meets the criteria
		Advice advice = null;
		// Start crazy query builder
		String queryString = "SELECT a FROM Advice a";
		String whereString = " where ";
		String queryParameters = "";
		// Check for parameters
		if(customerId != null)
			queryParameters = this.addParameter(queryParameters, " a.customer.id = :customerId");
		if(accountId != null)
			queryParameters = this.addParameter(queryParameters, " a.customerAccount.id = :accountId");
		if(currencyId != null)
			queryParameters = this.addParameter(queryParameters, " a.currency.id = :currencyId");
		if(adviceTypeId != null)
			queryParameters = this.addParameter(queryParameters, " a.adviceType.id = :adviceTypeId");
		if(adviceStatusId != null)
			queryParameters = this.addParameter(queryParameters, " a.adviceStatus.id = :adviceStatusId");
		if(amount != null)
			queryParameters = this.addParameter(queryParameters, " a.amount = :amount");
		// If we have query parameters then add them
		if(!queryParameters.isEmpty()){
			whereString = whereString.concat(queryParameters);
			queryString =  queryString.concat(whereString);
		}
		// Build the query object and add parameters if necessary
		if(!queryParameters.isEmpty()){

			Query query = this.getEntityManager().createQuery(queryString);
			if(customerId != null)
				query.setParameter("customerId", customerId);
			if(accountId != null)
				query.setParameter("accountId", accountId);
			if(currencyId != null)
				query.setParameter("currencyId", currencyId);
			if(adviceTypeId != null)
				query.setParameter("adviceTypeId", adviceTypeId);
			if(adviceStatusId != null)
				query.setParameter("adviceStatusId", adviceStatusId);
			if(amount != null)
				query.setParameter("amount", amount);

			try {
				advice = (Advice) query.getSingleResult();
			} catch (NoResultException e) {
				logger.error("Advice not found for transaction");
				//e.printStackTrace();
			}
		}
		return advice;
	}
	
	private String addParameter(String queryParameters, String parameter)
	{
		if(!queryParameters.isEmpty()){
			queryParameters = queryParameters.concat(" AND" + parameter);
		}
		else
		{
			queryParameters = queryParameters.concat(parameter);
		}
		return queryParameters;
	}
}
