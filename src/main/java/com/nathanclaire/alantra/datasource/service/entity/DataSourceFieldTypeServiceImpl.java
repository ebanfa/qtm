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

import com.nathanclaire.alantra.datasource.model.DataSourceFieldType;
import com.nathanclaire.alantra.datasource.request.DataSourceFieldTypeRequest;
import com.nathanclaire.alantra.datasource.response.DataSourceFieldTypeResponse;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtils;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class DataSourceFieldTypeServiceImpl 
	extends BaseEntityServiceImpl<DataSourceFieldType, DataSourceFieldTypeResponse, DataSourceFieldTypeRequest> 
	implements DataSourceFieldTypeService
{
	private static final String ENTITY_NAME = "DataSourceFieldType";
	private static final String LIST_ACTIVITY_CODE = "LIST_DATASOURCE_DATASOURCEFIELDTYPE";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_DATASOURCE_DATASOURCEFIELDTYPE";
	
	private Logger logger = LoggerFactory.getLogger(DataSourceFieldTypeServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	
	/**
	 * @param entityClass
	 */
	public DataSourceFieldTypeServiceImpl() {
		super(DataSourceFieldType.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataSourceFieldType#findById(java.lang.Integer)
	 */
	@Override
	public DataSourceFieldType findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataSourceFieldType#findByCode(java.lang.String)
	 */
	@Override
	public DataSourceFieldType findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataSourceFieldType#findByName(java.lang.String)
	 */
	@Override
	public DataSourceFieldType findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataSourceFieldType#findAll(java.util.Map)
	 */
	@Override
	public List<DataSourceFieldType> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataSourceFieldType#createDataSourceFieldType(com.nathanclaire.alantra.datasource.rest.request.ServiceRequest)
	 */
	@Override
	public DataSourceFieldType create(DataSourceFieldTypeRequest dataSourceFieldTypeRequest) throws ApplicationException {
		return createInstance(dataSourceFieldTypeRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataSourceFieldType#deleteDataSourceFieldType(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataSourceFieldType#updateDataSourceFieldType(com.nathanclaire.alantra.datasource.rest.request.ServiceRequest)
	 */
	@Override
	public DataSourceFieldType update(DataSourceFieldTypeRequest dataSourceFieldTypeRequest) throws ApplicationException {
		return updateInstance(dataSourceFieldTypeRequest);
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
		for(DataSourceFieldType datasourcefieldtype: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(datasourcefieldtype.getId(), datasourcefieldtype.getCode(), datasourcefieldtype.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public DataSourceFieldType convertRequestToModel(DataSourceFieldTypeRequest dataSourceFieldTypeRequest) 
     throws ApplicationException {
		DataSourceFieldType dataSourceFieldType = new DataSourceFieldType();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(dataSourceFieldTypeRequest, dataSourceFieldType, allowedEntityFields);
    	//Process many to one relationships
		return dataSourceFieldType;
	}
	
	@Override
	public DataSourceFieldTypeResponse convertModelToResponse(DataSourceFieldType model) throws ApplicationException {
		if (model == null) return null;
		DataSourceFieldTypeResponse dataSourceFieldTypeResponse = new DataSourceFieldTypeResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(model, dataSourceFieldTypeResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		return dataSourceFieldTypeResponse;
	}
}
