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

import com.nathanclaire.alantra.channel.model.ServiceMode;
import com.nathanclaire.alantra.channel.request.ServiceModeRequest;
import com.nathanclaire.alantra.channel.response.ServiceModeResponse;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtils;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class ServiceModeServiceImpl 
	extends BaseEntityServiceImpl<ServiceMode, ServiceModeResponse, ServiceModeRequest> 
	implements ServiceModeService
{
	private static final String ENTITY_NAME = "ServiceMode";
	private static final String LIST_ACTIVITY_CODE = "LIST_CHANNEL_SERVICEMODE";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_CHANNEL_SERVICEMODE";
	
	private Logger logger = LoggerFactory.getLogger(ServiceModeServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	
	/**
	 * @param entityClass
	 */
	public ServiceModeServiceImpl() {
		super(ServiceMode.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ServiceMode#findById(java.lang.Integer)
	 */
	@Override
	public ServiceMode findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ServiceMode#findByCode(java.lang.String)
	 */
	@Override
	public ServiceMode findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ServiceMode#findByName(java.lang.String)
	 */
	@Override
	public ServiceMode findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ServiceMode#findAll(java.util.Map)
	 */
	@Override
	public List<ServiceMode> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ServiceMode#createServiceMode(com.nathanclaire.alantra.channel.rest.request.ServiceRequest)
	 */
	@Override
	public ServiceMode create(ServiceModeRequest serviceModeRequest) throws ApplicationException {
		return createInstance(serviceModeRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ServiceMode#deleteServiceMode(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ServiceMode#updateServiceMode(com.nathanclaire.alantra.channel.rest.request.ServiceRequest)
	 */
	@Override
	public ServiceMode update(ServiceModeRequest serviceModeRequest) throws ApplicationException {
		return updateInstance(serviceModeRequest);
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
		for(ServiceMode servicemode: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(servicemode.getId(), servicemode.getCode(), servicemode.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public ServiceMode convertRequestToModel(ServiceModeRequest serviceModeRequest) 
     throws ApplicationException {
		ServiceMode serviceMode = new ServiceMode();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(serviceModeRequest, serviceMode, allowedEntityFields);
    	//Process many to one relationships
		return serviceMode;
	}
	
	@Override
	public ServiceModeResponse convertModelToResponse(ServiceMode model) throws ApplicationException {
		if (model == null) return null;
		ServiceModeResponse serviceModeResponse = new ServiceModeResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(model, serviceModeResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		return serviceModeResponse;
	}
}
