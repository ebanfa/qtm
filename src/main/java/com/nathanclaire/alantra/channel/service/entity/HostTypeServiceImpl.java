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

import com.nathanclaire.alantra.channel.model.HostType;
import com.nathanclaire.alantra.channel.request.HostTypeRequest;
import com.nathanclaire.alantra.channel.response.HostTypeResponse;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtils;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class HostTypeServiceImpl 
	extends BaseEntityServiceImpl<HostType, HostTypeResponse, HostTypeRequest> 
	implements HostTypeService
{
	private static final String ENTITY_NAME = "HostType";
	private static final String LIST_ACTIVITY_CODE = "LIST_CHANNEL_HOSTTYPE";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_CHANNEL_HOSTTYPE";
	
	private Logger logger = LoggerFactory.getLogger(HostTypeServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	
	/**
	 * @param entityClass
	 */
	public HostTypeServiceImpl() {
		super(HostType.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.HostType#findById(java.lang.Integer)
	 */
	@Override
	public HostType findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.HostType#findByCode(java.lang.String)
	 */
	@Override
	public HostType findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.HostType#findByName(java.lang.String)
	 */
	@Override
	public HostType findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.HostType#findAll(java.util.Map)
	 */
	@Override
	public List<HostType> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.HostType#createHostType(com.nathanclaire.alantra.channel.rest.request.ServiceRequest)
	 */
	@Override
	public HostType create(HostTypeRequest hostTypeRequest) throws ApplicationException {
		return createInstance(hostTypeRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.HostType#deleteHostType(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.HostType#updateHostType(com.nathanclaire.alantra.channel.rest.request.ServiceRequest)
	 */
	@Override
	public HostType update(HostTypeRequest hostTypeRequest) throws ApplicationException {
		return updateInstance(hostTypeRequest);
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
		for(HostType hosttype: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(hosttype.getId(), hosttype.getCode(), hosttype.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public HostType convertRequestToModel(HostTypeRequest hostTypeRequest) 
     throws ApplicationException {
		HostType hostType = new HostType();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(hostTypeRequest, hostType, allowedEntityFields);
    	//Process many to one relationships
		return hostType;
	}
	
	@Override
	public HostTypeResponse convertModelToResponse(HostType model) throws ApplicationException {
		if (model == null) return null;
		HostTypeResponse hostTypeResponse = new HostTypeResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(model, hostTypeResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		return hostTypeResponse;
	}
}
