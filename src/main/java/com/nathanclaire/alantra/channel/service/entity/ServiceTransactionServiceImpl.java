/**
 * 
 */
package com.nathanclaire.alantra.channel.service.entity;

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

import com.nathanclaire.alantra.channel.model.ServiceTransaction;
import com.nathanclaire.alantra.businessdata.model.Currency;
import com.nathanclaire.alantra.channel.model.Service;
import com.nathanclaire.alantra.channel.model.ServiceTransactionStatus;
import com.nathanclaire.alantra.channel.model.ServiceTransactionType;
import com.nathanclaire.alantra.channel.request.ServiceTransactionRequest;
import com.nathanclaire.alantra.channel.response.ServiceTransactionResponse;
import com.nathanclaire.alantra.businessdata.service.entity.CurrencyService;
import com.nathanclaire.alantra.channel.service.entity.ServiceService;
import com.nathanclaire.alantra.channel.service.entity.ServiceTransactionStatusService;
import com.nathanclaire.alantra.channel.service.entity.ServiceTransactionTypeService;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtils;

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
	private static final String LIST_ITEM_SERVICE = "service";
	private static final String LIST_ITEM_SERVICETRANSACTIONSTATUS = "serviceTransactionStatus";
	private static final String LIST_ITEM_SERVICETRANSACTIONTYPE = "serviceTransactionType";
	private static final String ENTITY_NAME = "ServiceTransaction";
	private static final String LIST_ACTIVITY_CODE = "LIST_CHANNEL_SERVICETRANSACTION";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_CHANNEL_SERVICETRANSACTION";
	
	private Logger logger = LoggerFactory.getLogger(ServiceTransactionServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	@Inject
	CurrencyService  currencyService;
	@Inject
	ServiceService  serviceService;
	@Inject
	ServiceTransactionStatusService  serviceTransactionStatusService;
	@Inject
	ServiceTransactionTypeService  serviceTransactionTypeService;
	
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
		List<ListItemResponse> services = serviceService.asListItem();
		List<ListItemResponse> serviceTransactionStatuss = serviceTransactionStatusService.asListItem();
		List<ListItemResponse> serviceTransactionTypes = serviceTransactionTypeService.asListItem();
    	
		listItems.put(LIST_ITEM_CURRENCY, currencys); 
		listItems.put(LIST_ITEM_SERVICE, services); 
		listItems.put(LIST_ITEM_SERVICETRANSACTIONSTATUS, serviceTransactionStatuss); 
		listItems.put(LIST_ITEM_SERVICETRANSACTIONTYPE, serviceTransactionTypes); 
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
		PropertyUtils.copyProperties(serviceTransactionRequest, serviceTransaction, allowedEntityFields);
    	//Process many to one relationships
    	if (serviceTransactionRequest.getCurrencyId() != null)
    	{
    		Currency currency = getEntityManager().find(Currency.class, serviceTransactionRequest.getCurrencyId());
    		serviceTransaction.setCurrency(currency);
    	}
    	if (serviceTransactionRequest.getServiceId() != null)
    	{
    		Service service = getEntityManager().find(Service.class, serviceTransactionRequest.getServiceId());
    		serviceTransaction.setService(service);
    	}
    	if (serviceTransactionRequest.getServiceTransactionStatusId() != null)
    	{
    		ServiceTransactionStatus serviceTransactionStatus = getEntityManager().find(ServiceTransactionStatus.class, serviceTransactionRequest.getServiceTransactionStatusId());
    		serviceTransaction.setServiceTransactionStatus(serviceTransactionStatus);
    	}
    	if (serviceTransactionRequest.getServiceTransactionTypeId() != null)
    	{
    		ServiceTransactionType serviceTransactionType = getEntityManager().find(ServiceTransactionType.class, serviceTransactionRequest.getServiceTransactionTypeId());
    		serviceTransaction.setServiceTransactionType(serviceTransactionType);
    	}
		return serviceTransaction;
	}
	
	@Override
	public ServiceTransactionResponse convertModelToResponse(ServiceTransaction model) throws ApplicationException {
		if (model == null) return null;
		ServiceTransactionResponse serviceTransactionResponse = new ServiceTransactionResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(model, serviceTransactionResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		if(model.getCurrency() != null)
			serviceTransactionResponse.setCurrencyId(model.getCurrency().getId());
			serviceTransactionResponse.setCurrencyText(model.getCurrency().getName());
		if(model.getService() != null)
			serviceTransactionResponse.setServiceId(model.getService().getId());
			serviceTransactionResponse.setServiceText(model.getService().getName());
		if(model.getServiceTransactionStatus() != null)
			serviceTransactionResponse.setServiceTransactionStatusId(model.getServiceTransactionStatus().getId());
			serviceTransactionResponse.setServiceTransactionStatusText(model.getServiceTransactionStatus().getName());
		if(model.getServiceTransactionType() != null)
			serviceTransactionResponse.setServiceTransactionTypeId(model.getServiceTransactionType().getId());
			serviceTransactionResponse.setServiceTransactionTypeText(model.getServiceTransactionType().getName());
		return serviceTransactionResponse;
	}
}
