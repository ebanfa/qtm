/**
 * 
 */
package com.nathanclaire.alantra.advice.service.entity;

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

import com.nathanclaire.alantra.advice.model.Advice;
import com.nathanclaire.alantra.customer.model.Customer;
import com.nathanclaire.alantra.businessdata.model.Currency;
import com.nathanclaire.alantra.advice.model.AdviceRequestMessage;
import com.nathanclaire.alantra.advice.model.AdviceStatus;
import com.nathanclaire.alantra.advice.model.AdviceClassification;
import com.nathanclaire.alantra.customer.model.CustomerAccount;
import com.nathanclaire.alantra.advice.model.AdviceType;
import com.nathanclaire.alantra.advice.request.AdviceRequest;
import com.nathanclaire.alantra.advice.response.AdviceResponse;
import com.nathanclaire.alantra.customer.service.entity.CustomerService;
import com.nathanclaire.alantra.businessdata.service.entity.CurrencyService;
import com.nathanclaire.alantra.advice.service.entity.AdviceRequestMessageService;
import com.nathanclaire.alantra.advice.service.entity.AdviceStatusService;
import com.nathanclaire.alantra.advice.service.entity.AdviceClassificationService;
import com.nathanclaire.alantra.customer.service.entity.CustomerAccountService;
import com.nathanclaire.alantra.advice.service.entity.AdviceTypeService;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtils;

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
	private static final String LIST_ITEM_CURRENCY = "currency";
	private static final String LIST_ITEM_ADVICEREQUESTMESSAGE = "adviceRequestMessage";
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
	CurrencyService  currencyService;
	@Inject
	AdviceRequestMessageService  adviceRequestMessageService;
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
		List<ListItemResponse> currencys = currencyService.asListItem();
		List<ListItemResponse> adviceRequestMessages = adviceRequestMessageService.asListItem();
		List<ListItemResponse> adviceStatuss = adviceStatusService.asListItem();
		List<ListItemResponse> adviceClassifications = adviceClassificationService.asListItem();
		List<ListItemResponse> customerAccounts = customerAccountService.asListItem();
		List<ListItemResponse> adviceTypes = adviceTypeService.asListItem();
    	
		listItems.put(LIST_ITEM_CUSTOMER, customers); 
		listItems.put(LIST_ITEM_CURRENCY, currencys); 
		listItems.put(LIST_ITEM_ADVICEREQUESTMESSAGE, adviceRequestMessages); 
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
    	if (adviceRequest.getCurrencyId() != null)
    	{
    		Currency currency = getEntityManager().find(Currency.class, adviceRequest.getCurrencyId());
    		advice.setCurrency(currency);
    	}
    	if (adviceRequest.getAdviceRequestMessageId() != null)
    	{
    		AdviceRequestMessage adviceRequestMessage = getEntityManager().find(AdviceRequestMessage.class, adviceRequest.getAdviceRequestMessageId());
    		advice.setAdviceRequestMessage(adviceRequestMessage);
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
		if(model.getCurrency() != null)
			adviceResponse.setCurrencyId(model.getCurrency().getId());
			adviceResponse.setCurrencyText(model.getCurrency().getName());
		if(model.getAdviceRequestMessage() != null)
			adviceResponse.setAdviceRequestMessageId(model.getAdviceRequestMessage().getId());
			adviceResponse.setAdviceRequestMessageText(model.getAdviceRequestMessage().getName());
		if(model.getAdviceStatus() != null)
			adviceResponse.setAdviceStatusId(model.getAdviceStatus().getId());
			adviceResponse.setAdviceStatusText(model.getAdviceStatus().getName());
		if(model.getAdviceClassification() != null)
			adviceResponse.setAdviceClassificationId(model.getAdviceClassification().getId());
			adviceResponse.setAdviceClassificationText(model.getAdviceClassification().getName());
		if(model.getAdviceType() != null)
			adviceResponse.setAdviceTypeId(model.getAdviceType().getId());
			adviceResponse.setAdviceTypeText(model.getAdviceType().getName());
		return adviceResponse;
	}
}
