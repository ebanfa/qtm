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
import javax.ws.rs.core.MultivaluedMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.application.model.ApplicationEntityField;

import com.nathanclaire.alantra.transaction.model.ServiceTransactionStatus;
import com.nathanclaire.alantra.transaction.request.ServiceTransactionStatusRequest;
import com.nathanclaire.alantra.transaction.response.ServiceTransactionStatusResponse;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtil;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class ServiceTransactionStatusServiceImpl 
	extends BaseEntityServiceImpl<ServiceTransactionStatus, ServiceTransactionStatusResponse, ServiceTransactionStatusRequest> 
	implements ServiceTransactionStatusService
{
	private static final String ENTITY_NAME = "ServiceTransactionStatus";
	private static final String LIST_ACTIVITY_CODE = "LIST_TRANSACTION_SERVICETRANSACTIONSTATUS";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_TRANSACTION_SERVICETRANSACTIONSTATUS";
	
	private Logger logger = LoggerFactory.getLogger(ServiceTransactionStatusServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	
	/**
	 * @param entityClass
	 */
	public ServiceTransactionStatusServiceImpl() {
		super(ServiceTransactionStatus.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ServiceTransactionStatus#findById(java.lang.Integer)
	 */
	@Override
	public ServiceTransactionStatus findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ServiceTransactionStatus#findByCode(java.lang.String)
	 */
	@Override
	public ServiceTransactionStatus findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ServiceTransactionStatus#findByName(java.lang.String)
	 */
	@Override
	public ServiceTransactionStatus findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ServiceTransactionStatus#findAll(java.util.Map)
	 */
	@Override
	public List<ServiceTransactionStatus> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ServiceTransactionStatus#createServiceTransactionStatus(com.nathanclaire.alantra.channel.rest.request.ServiceRequest)
	 */
	@Override
	public ServiceTransactionStatus create(ServiceTransactionStatusRequest serviceTransactionStatusRequest) throws ApplicationException {
		return createInstance(serviceTransactionStatusRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ServiceTransactionStatus#deleteServiceTransactionStatus(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ServiceTransactionStatus#updateServiceTransactionStatus(com.nathanclaire.alantra.channel.rest.request.ServiceRequest)
	 */
	@Override
	public ServiceTransactionStatus update(ServiceTransactionStatusRequest serviceTransactionStatusRequest) throws ApplicationException {
		return updateInstance(serviceTransactionStatusRequest);
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
		for(ServiceTransactionStatus servicetransactionstatus: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(servicetransactionstatus.getId(), servicetransactionstatus.getCode(), servicetransactionstatus.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public ServiceTransactionStatus convertRequestToModel(ServiceTransactionStatusRequest serviceTransactionStatusRequest) 
     throws ApplicationException {
		ServiceTransactionStatus serviceTransactionStatus = new ServiceTransactionStatus();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtil.copyProperties(serviceTransactionStatusRequest, serviceTransactionStatus, allowedEntityFields);
    	//Process many to one relationships
		return serviceTransactionStatus;
	}
	
	@Override
	public ServiceTransactionStatusResponse convertModelToResponse(ServiceTransactionStatus model) throws ApplicationException {
		if (model == null) return null;
		ServiceTransactionStatusResponse serviceTransactionStatusResponse = new ServiceTransactionStatusResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtil.copyProperties(model, serviceTransactionStatusResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		return serviceTransactionStatusResponse;
	}
}
