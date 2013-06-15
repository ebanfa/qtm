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

import com.nathanclaire.alantra.datasource.model.DataChannelType;
import com.nathanclaire.alantra.datasource.model.DataChannelAdapter;
import com.nathanclaire.alantra.datasource.model.DataChannelCategory;
import com.nathanclaire.alantra.datasource.request.DataChannelTypeRequest;
import com.nathanclaire.alantra.datasource.response.DataChannelTypeResponse;
import com.nathanclaire.alantra.datasource.service.entity.DataChannelAdapterService;
import com.nathanclaire.alantra.datasource.service.entity.DataChannelCategoryService;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtils;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class DataChannelTypeServiceImpl 
	extends BaseEntityServiceImpl<DataChannelType, DataChannelTypeResponse, DataChannelTypeRequest> 
	implements DataChannelTypeService
{
	private static final String LIST_ITEM_DATACHANNELADAPTER = "dataChannelAdapter";
	private static final String LIST_ITEM_DATACHANNELCATEGORY = "dataChannelCategory";
	private static final String ENTITY_NAME = "DataChannelType";
	private static final String LIST_ACTIVITY_CODE = "LIST_DATASOURCE_DATACHANNELTYPE";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_DATASOURCE_DATACHANNELTYPE";
	
	private Logger logger = LoggerFactory.getLogger(DataChannelTypeServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	@Inject
	DataChannelAdapterService  dataChannelAdapterService;
	@Inject
	DataChannelCategoryService  dataChannelCategoryService;
	
	/**
	 * @param entityClass
	 */
	public DataChannelTypeServiceImpl() {
		super(DataChannelType.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataChannelType#findById(java.lang.Integer)
	 */
	@Override
	public DataChannelType findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataChannelType#findByCode(java.lang.String)
	 */
	@Override
	public DataChannelType findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataChannelType#findByName(java.lang.String)
	 */
	@Override
	public DataChannelType findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataChannelType#findAll(java.util.Map)
	 */
	@Override
	public List<DataChannelType> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataChannelType#createDataChannelType(com.nathanclaire.alantra.datasource.rest.request.ServiceRequest)
	 */
	@Override
	public DataChannelType create(DataChannelTypeRequest dataChannelTypeRequest) throws ApplicationException {
		return createInstance(dataChannelTypeRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataChannelType#deleteDataChannelType(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataChannelType#updateDataChannelType(com.nathanclaire.alantra.datasource.rest.request.ServiceRequest)
	 */
	@Override
	public DataChannelType update(DataChannelTypeRequest dataChannelTypeRequest) throws ApplicationException {
		return updateInstance(dataChannelTypeRequest);
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
		List<ListItemResponse> dataChannelAdapters = dataChannelAdapterService.asListItem();
		List<ListItemResponse> dataChannelCategorys = dataChannelCategoryService.asListItem();
    	
		listItems.put(LIST_ITEM_DATACHANNELADAPTER, dataChannelAdapters); 
		listItems.put(LIST_ITEM_DATACHANNELCATEGORY, dataChannelCategorys); 
		return listItems;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#asListItem()
	 */
	@Override
	public List<ListItemResponse> asListItem() throws ApplicationException {
		List<ListItemResponse> listItems = new ArrayList<ListItemResponse>();
		queryParameters.clear();
		for(DataChannelType datachanneltype: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(datachanneltype.getId(), datachanneltype.getCode(), datachanneltype.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public DataChannelType convertRequestToModel(DataChannelTypeRequest dataChannelTypeRequest) 
     throws ApplicationException {
		DataChannelType dataChannelType = new DataChannelType();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(dataChannelTypeRequest, dataChannelType, allowedEntityFields);
    	//Process many to one relationships
    	if (dataChannelTypeRequest.getDataChannelAdapterId() != null)
    	{
    		DataChannelAdapter dataChannelAdapter = getEntityManager().find(DataChannelAdapter.class, dataChannelTypeRequest.getDataChannelAdapterId());
    		dataChannelType.setDataChannelAdapter(dataChannelAdapter);
    	}
    	if (dataChannelTypeRequest.getDataChannelCategoryId() != null)
    	{
    		DataChannelCategory dataChannelCategory = getEntityManager().find(DataChannelCategory.class, dataChannelTypeRequest.getDataChannelCategoryId());
    		dataChannelType.setDataChannelCategory(dataChannelCategory);
    	}
		return dataChannelType;
	}
	
	@Override
	public DataChannelTypeResponse convertModelToResponse(DataChannelType model) throws ApplicationException {
		if (model == null) return null;
		DataChannelTypeResponse dataChannelTypeResponse = new DataChannelTypeResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(model, dataChannelTypeResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		if(model.getDataChannelAdapter() != null)
			dataChannelTypeResponse.setDataChannelAdapterId(model.getDataChannelAdapter().getId());
			dataChannelTypeResponse.setDataChannelAdapterText(model.getDataChannelAdapter().getName());
		if(model.getDataChannelCategory() != null)
			dataChannelTypeResponse.setDataChannelCategoryId(model.getDataChannelCategory().getId());
			dataChannelTypeResponse.setDataChannelCategoryText(model.getDataChannelCategory().getName());
		return dataChannelTypeResponse;
	}
}
