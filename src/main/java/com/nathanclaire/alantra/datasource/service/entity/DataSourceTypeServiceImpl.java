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

import com.nathanclaire.alantra.datasource.model.DataSourceType;
import com.nathanclaire.alantra.datasource.model.DataSourceCategory;
import com.nathanclaire.alantra.datasource.request.DataSourceTypeRequest;
import com.nathanclaire.alantra.datasource.response.DataSourceTypeResponse;
import com.nathanclaire.alantra.datasource.service.entity.DataSourceCategoryService;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtils;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class DataSourceTypeServiceImpl 
	extends BaseEntityServiceImpl<DataSourceType, DataSourceTypeResponse, DataSourceTypeRequest> 
	implements DataSourceTypeService
{
	private static final String LIST_ITEM_DATASOURCECATEGORY = "dataSourceCategory";
	private static final String ENTITY_NAME = "DataSourceType";
	private static final String LIST_ACTIVITY_CODE = "LIST_DATASOURCE_DATASOURCETYPE";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_DATASOURCE_DATASOURCETYPE";
	
	private Logger logger = LoggerFactory.getLogger(DataSourceTypeServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	@Inject
	DataSourceCategoryService  dataSourceCategoryService;
	
	/**
	 * @param entityClass
	 */
	public DataSourceTypeServiceImpl() {
		super(DataSourceType.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataSourceType#findById(java.lang.Integer)
	 */
	@Override
	public DataSourceType findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataSourceType#findByCode(java.lang.String)
	 */
	@Override
	public DataSourceType findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataSourceType#findByName(java.lang.String)
	 */
	@Override
	public DataSourceType findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataSourceType#findAll(java.util.Map)
	 */
	@Override
	public List<DataSourceType> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataSourceType#createDataSourceType(com.nathanclaire.alantra.datasource.rest.request.ServiceRequest)
	 */
	@Override
	public DataSourceType create(DataSourceTypeRequest dataSourceTypeRequest) throws ApplicationException {
		return createInstance(dataSourceTypeRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataSourceType#deleteDataSourceType(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataSourceType#updateDataSourceType(com.nathanclaire.alantra.datasource.rest.request.ServiceRequest)
	 */
	@Override
	public DataSourceType update(DataSourceTypeRequest dataSourceTypeRequest) throws ApplicationException {
		return updateInstance(dataSourceTypeRequest);
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
		List<ListItemResponse> dataSourceCategorys = dataSourceCategoryService.asListItem();
    	
		listItems.put(LIST_ITEM_DATASOURCECATEGORY, dataSourceCategorys); 
		return listItems;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#asListItem()
	 */
	@Override
	public List<ListItemResponse> asListItem() throws ApplicationException {
		List<ListItemResponse> listItems = new ArrayList<ListItemResponse>();
		queryParameters.clear();
		for(DataSourceType datasourcetype: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(datasourcetype.getId(), datasourcetype.getCode(), datasourcetype.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public DataSourceType convertRequestToModel(DataSourceTypeRequest dataSourceTypeRequest) 
     throws ApplicationException {
		DataSourceType dataSourceType = new DataSourceType();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(dataSourceTypeRequest, dataSourceType, allowedEntityFields);
    	//Process many to one relationships
    	if (dataSourceTypeRequest.getDataSourceCategoryId() != null)
    	{
    		DataSourceCategory dataSourceCategory = getEntityManager().find(DataSourceCategory.class, dataSourceTypeRequest.getDataSourceCategoryId());
    		dataSourceType.setDataSourceCategory(dataSourceCategory);
    	}
		return dataSourceType;
	}
	
	@Override
	public DataSourceTypeResponse convertModelToResponse(DataSourceType model) throws ApplicationException {
		if (model == null) return null;
		DataSourceTypeResponse dataSourceTypeResponse = new DataSourceTypeResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(model, dataSourceTypeResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		if(model.getDataSourceCategory() != null)
			dataSourceTypeResponse.setDataSourceCategoryId(model.getDataSourceCategory().getId());
			dataSourceTypeResponse.setDataSourceCategoryText(model.getDataSourceCategory().getName());
		return dataSourceTypeResponse;
	}
}
