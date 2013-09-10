/**
 * 
 */
package com.nathanclaire.alantra.datasource.service.entity;

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

import com.nathanclaire.alantra.datasource.model.DataChannelStatus;
import com.nathanclaire.alantra.datasource.request.DataChannelStatusRequest;
import com.nathanclaire.alantra.datasource.response.DataChannelStatusResponse;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtil;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class DataChannelStatusServiceImpl 
	extends BaseEntityServiceImpl<DataChannelStatus, DataChannelStatusResponse, DataChannelStatusRequest> 
	implements DataChannelStatusService
{
	private static final String ENTITY_NAME = "DataChannelStatus";
	private static final String LIST_ACTIVITY_CODE = "LIST_DATASOURCE_DATACHANNELSTATUS";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_DATASOURCE_DATACHANNELSTATUS";
	
	private Logger logger = LoggerFactory.getLogger(DataChannelStatusServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	
	/**
	 * @param entityClass
	 */
	public DataChannelStatusServiceImpl() {
		super(DataChannelStatus.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataChannelStatus#findById(java.lang.Integer)
	 */
	@Override
	public DataChannelStatus findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataChannelStatus#findByCode(java.lang.String)
	 */
	@Override
	public DataChannelStatus findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataChannelStatus#findByName(java.lang.String)
	 */
	@Override
	public DataChannelStatus findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataChannelStatus#findAll(java.util.Map)
	 */
	@Override
	public List<DataChannelStatus> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataChannelStatus#createDataChannelStatus(com.nathanclaire.alantra.datasource.rest.request.ServiceRequest)
	 */
	@Override
	public DataChannelStatus create(DataChannelStatusRequest dataChannelStatusRequest) throws ApplicationException {
		return createInstance(dataChannelStatusRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataChannelStatus#deleteDataChannelStatus(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataChannelStatus#updateDataChannelStatus(com.nathanclaire.alantra.datasource.rest.request.ServiceRequest)
	 */
	@Override
	public DataChannelStatus update(DataChannelStatusRequest dataChannelStatusRequest) throws ApplicationException {
		return updateInstance(dataChannelStatusRequest);
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
		for(DataChannelStatus datachannelstatus: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(datachannelstatus.getId(), datachannelstatus.getCode(), datachannelstatus.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public DataChannelStatus convertRequestToModel(DataChannelStatusRequest dataChannelStatusRequest) 
     throws ApplicationException {
		DataChannelStatus dataChannelStatus = new DataChannelStatus();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtil.copyProperties(dataChannelStatusRequest, dataChannelStatus, allowedEntityFields);
    	//Process many to one relationships
		return dataChannelStatus;
	}
	
	@Override
	public DataChannelStatusResponse convertModelToResponse(DataChannelStatus model) throws ApplicationException {
		if (model == null) return null;
		DataChannelStatusResponse dataChannelStatusResponse = new DataChannelStatusResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtil.copyProperties(model, dataChannelStatusResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		return dataChannelStatusResponse;
	}
}
