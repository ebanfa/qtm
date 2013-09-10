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

import com.nathanclaire.alantra.datasource.model.DataChannelAdapter;
import com.nathanclaire.alantra.datasource.request.DataChannelAdapterRequest;
import com.nathanclaire.alantra.datasource.response.DataChannelAdapterResponse;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtil;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class DataChannelAdapterServiceImpl 
	extends BaseEntityServiceImpl<DataChannelAdapter, DataChannelAdapterResponse, DataChannelAdapterRequest> 
	implements DataChannelAdapterService
{
	private static final String ENTITY_NAME = "DataChannelAdapter";
	private static final String LIST_ACTIVITY_CODE = "LIST_DATASOURCE_DATACHANNELADAPTER";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_DATASOURCE_DATACHANNELADAPTER";
	
	private Logger logger = LoggerFactory.getLogger(DataChannelAdapterServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	
	/**
	 * @param entityClass
	 */
	public DataChannelAdapterServiceImpl() {
		super(DataChannelAdapter.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataChannelAdapter#findById(java.lang.Integer)
	 */
	@Override
	public DataChannelAdapter findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataChannelAdapter#findByCode(java.lang.String)
	 */
	@Override
	public DataChannelAdapter findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataChannelAdapter#findByName(java.lang.String)
	 */
	@Override
	public DataChannelAdapter findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataChannelAdapter#findAll(java.util.Map)
	 */
	@Override
	public List<DataChannelAdapter> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataChannelAdapter#createDataChannelAdapter(com.nathanclaire.alantra.datasource.rest.request.ServiceRequest)
	 */
	@Override
	public DataChannelAdapter create(DataChannelAdapterRequest dataChannelAdapterRequest) throws ApplicationException {
		return createInstance(dataChannelAdapterRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataChannelAdapter#deleteDataChannelAdapter(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataChannelAdapter#updateDataChannelAdapter(com.nathanclaire.alantra.datasource.rest.request.ServiceRequest)
	 */
	@Override
	public DataChannelAdapter update(DataChannelAdapterRequest dataChannelAdapterRequest) throws ApplicationException {
		return updateInstance(dataChannelAdapterRequest);
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
		for(DataChannelAdapter datachanneladapter: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(datachanneladapter.getId(), datachanneladapter.getCode(), datachanneladapter.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public DataChannelAdapter convertRequestToModel(DataChannelAdapterRequest dataChannelAdapterRequest) 
     throws ApplicationException {
		DataChannelAdapter dataChannelAdapter = new DataChannelAdapter();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtil.copyProperties(dataChannelAdapterRequest, dataChannelAdapter, allowedEntityFields);
    	//Process many to one relationships
		return dataChannelAdapter;
	}
	
	@Override
	public DataChannelAdapterResponse convertModelToResponse(DataChannelAdapter model) throws ApplicationException {
		if (model == null) return null;
		DataChannelAdapterResponse dataChannelAdapterResponse = new DataChannelAdapterResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtil.copyProperties(model, dataChannelAdapterResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		return dataChannelAdapterResponse;
	}
}
