/**
 * 
 */
package com.nathanclaire.alantra.transaction.service.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.ws.rs.core.MultivaluedMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.application.model.ApplicationEntityField;

import com.nathanclaire.alantra.businessdata.model.Currency;
import com.nathanclaire.alantra.datasource.model.DataChannel;
import com.nathanclaire.alantra.customer.model.CustomerAccount;
import com.nathanclaire.alantra.businessdata.service.entity.CurrencyService;
import com.nathanclaire.alantra.datasource.service.entity.DataChannelEntityService;
import com.nathanclaire.alantra.transaction.model.ServiceTransaction;
import com.nathanclaire.alantra.transaction.model.ServiceTransactionStatus;
import com.nathanclaire.alantra.transaction.model.ServiceTransactionType;
import com.nathanclaire.alantra.transaction.request.ServiceTransactionRequest;
import com.nathanclaire.alantra.transaction.response.ServiceTransactionResponse;
import com.nathanclaire.alantra.transaction.service.entity.ServiceTransactionStatusService;
import com.nathanclaire.alantra.transaction.service.entity.ServiceTransactionTypeService;
import com.nathanclaire.alantra.customer.service.entity.CustomerAccountService;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtil;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class ServiceTransactionServiceImpl 
	extends BaseEntityServiceImpl<ServiceTransaction, ServiceTransactionResponse, ServiceTransactionRequest> 
	implements ServiceTransactionService
{
	private static final String LIST_ITEM_CURRENCY = "currency";
	private static final String LIST_ITEM_SERVICETRANSACTIONSTATUS = "serviceTransactionStatus";
	private static final String LIST_ITEM_DATACHANNEL = "dataChannel";
	private static final String LIST_ITEM_SERVICETRANSACTIONTYPE = "serviceTransactionType";
	private static final String LIST_ITEM_CUSTOMERACCOUNT = "customerAccount";
	private static final String ENTITY_NAME = "ServiceTransaction";
	private static final String LIST_ACTIVITY_CODE = "LIST_TRANSACTION_SERVICETRANSACTION";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_TRANSACTION_SERVICETRANSACTION";
	
	private Logger logger = LoggerFactory.getLogger(ServiceTransactionServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	@Inject
	CurrencyService  currencyService;
	@Inject
	ServiceTransactionStatusService  serviceTransactionStatusService;
	@Inject
	DataChannelEntityService  dataChannelEntityService;
	@Inject
	ServiceTransactionTypeService  serviceTransactionTypeService;
	@Inject
	CustomerAccountService  customerAccountService;
	
	/**
	 * @param entityClass
	 */
	public ServiceTransactionServiceImpl() {
		super(ServiceTransaction.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ServiceTransaction#findById(java.lang.Integer)
	 */
	@Override
	public ServiceTransaction findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ServiceTransaction#findByCode(java.lang.String)
	 */
	@Override
	public ServiceTransaction findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ServiceTransaction#findByName(java.lang.String)
	 */
	@Override
	public ServiceTransaction findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ServiceTransaction#findAll(java.util.Map)
	 */
	@Override
	public List<ServiceTransaction> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ServiceTransaction#createServiceTransaction(com.nathanclaire.alantra.channel.rest.request.ServiceRequest)
	 */
	@Override
	public ServiceTransaction create(ServiceTransactionRequest serviceTransactionRequest) throws ApplicationException {
		return createInstance(serviceTransactionRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ServiceTransaction#deleteServiceTransaction(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ServiceTransaction#updateServiceTransaction(com.nathanclaire.alantra.channel.rest.request.ServiceRequest)
	 */
	@Override
	public ServiceTransaction update(ServiceTransactionRequest serviceTransactionRequest) throws ApplicationException {
		return updateInstance(serviceTransactionRequest);
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
		List<ListItemResponse> serviceTransactionStatuss = serviceTransactionStatusService.asListItem();
		List<ListItemResponse> dataChannels = dataChannelEntityService.asListItem();
		List<ListItemResponse> serviceTransactionTypes = serviceTransactionTypeService.asListItem();
		List<ListItemResponse> customerAccounts = customerAccountService.asListItem();
    	
		listItems.put(LIST_ITEM_CURRENCY, currencys); 
		listItems.put(LIST_ITEM_SERVICETRANSACTIONSTATUS, serviceTransactionStatuss); 
		listItems.put(LIST_ITEM_DATACHANNEL, dataChannels); 
		listItems.put(LIST_ITEM_SERVICETRANSACTIONTYPE, serviceTransactionTypes); 
		listItems.put(LIST_ITEM_CUSTOMERACCOUNT, customerAccounts); 
		return listItems;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#asListItem()
	 */
	@Override
	public List<ListItemResponse> asListItem() throws ApplicationException {
		List<ListItemResponse> listItems = new ArrayList<ListItemResponse>();
		queryParameters.clear();
		for(ServiceTransaction servicetransaction: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(servicetransaction.getId(), servicetransaction.getCode(), servicetransaction.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public ServiceTransaction convertRequestToModel(ServiceTransactionRequest serviceTransactionRequest) 
     throws ApplicationException {
		ServiceTransaction serviceTransaction = new ServiceTransaction();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtil.copyProperties(serviceTransactionRequest, serviceTransaction, allowedEntityFields);
    	//Process many to one relationships
    	if (serviceTransactionRequest.getCurrencyId() != null)
    	{
    		Currency currency = getEntityManager().find(Currency.class, serviceTransactionRequest.getCurrencyId());
    		serviceTransaction.setCurrency(currency);
    	}
    	if (serviceTransactionRequest.getServiceTransactionStatusId() != null)
    	{
    		ServiceTransactionStatus serviceTransactionStatus = getEntityManager().find(ServiceTransactionStatus.class, serviceTransactionRequest.getServiceTransactionStatusId());
    		serviceTransaction.setServiceTransactionStatus(serviceTransactionStatus);
    	}
    	if (serviceTransactionRequest.getDataChannelId() != null)
    	{
    		DataChannel dataChannel = getEntityManager().find(DataChannel.class, serviceTransactionRequest.getDataChannelId());
    		serviceTransaction.setDataChannel(dataChannel);
    	}
    	if (serviceTransactionRequest.getServiceTransactionTypeId() != null)
    	{
    		ServiceTransactionType serviceTransactionType = getEntityManager().find(ServiceTransactionType.class, serviceTransactionRequest.getServiceTransactionTypeId());
    		serviceTransaction.setServiceTransactionType(serviceTransactionType);
    	}
    	if (serviceTransactionRequest.getCustomerAccountId() != null)
    	{
    		CustomerAccount customerAccount = getEntityManager().find(CustomerAccount.class, serviceTransactionRequest.getCustomerAccountId());
    		serviceTransaction.setCustomerAccount(customerAccount);
    	}
		return serviceTransaction;
	}
	
	@Override
	public ServiceTransactionResponse convertModelToResponse(ServiceTransaction model) throws ApplicationException {
		if (model == null) return null;
		ServiceTransactionResponse serviceTransactionResponse = new ServiceTransactionResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtil.copyProperties(model, serviceTransactionResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		if(model.getCurrency() != null)
			serviceTransactionResponse.setCurrencyId(model.getCurrency().getId());
			serviceTransactionResponse.setCurrencyText(model.getCurrency().getName());
		if(model.getServiceTransactionStatus() != null)
			serviceTransactionResponse.setServiceTransactionStatusId(model.getServiceTransactionStatus().getId());
			serviceTransactionResponse.setServiceTransactionStatusText(model.getServiceTransactionStatus().getName());
		if(model.getDataChannel() != null)
			serviceTransactionResponse.setDataChannelId(model.getDataChannel().getId());
			serviceTransactionResponse.setDataChannelText(model.getDataChannel().getName());
		if(model.getServiceTransactionType() != null)
			serviceTransactionResponse.setServiceTransactionTypeId(model.getServiceTransactionType().getId());
			serviceTransactionResponse.setServiceTransactionTypeText(model.getServiceTransactionType().getName());
		if(model.getCustomerAccount() != null)
			serviceTransactionResponse.setCustomerAccountId(model.getCustomerAccount().getId());
			serviceTransactionResponse.setCustomerAccountText(model.getCustomerAccount().getName());
		return serviceTransactionResponse;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl#extractPredicates(javax.ws.rs.core.MultivaluedMap, javax.persistence.criteria.CriteriaBuilder, javax.persistence.criteria.Root)
	 */
	@Override
	protected Predicate[] extractPredicates(
			MultivaluedMap<String, String> queryParameters,
			CriteriaBuilder criteriaBuilder, Root<ServiceTransaction> root) {
		List<Predicate> predicates = new ArrayList<Predicate>() ;
        if (queryParameters.containsKey("serviceTransactionStatus.code")) {
            String statusCode = queryParameters.getFirst("serviceTransactionStatus.code");
            predicates.add(criteriaBuilder.equal(root.get("serviceTransactionStatus").get("code"), statusCode));
        }
        for(Predicate predicate : super.extractPredicates(queryParameters, criteriaBuilder, root))
        	predicates.add(predicate);
        return predicates.toArray(new Predicate[]{});
	}
	
	
}
