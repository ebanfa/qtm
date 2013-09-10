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

import com.nathanclaire.alantra.datasource.model.DataFieldType;
import com.nathanclaire.alantra.datasource.request.DataFieldTypeRequest;
import com.nathanclaire.alantra.datasource.response.DataFieldTypeResponse;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtil;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class DataFieldTypeServiceImpl 
	extends BaseEntityServiceImpl<DataFieldType, DataFieldTypeResponse, DataFieldTypeRequest> 
	implements DataFieldTypeService
{
	private static final String ENTITY_NAME = "DataFieldType";
	private static final String LIST_ACTIVITY_CODE = "LIST_DATASOURCE_DATAFIELDTYPE";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_DATASOURCE_DATAFIELDTYPE";
	
	private Logger logger = LoggerFactory.getLogger(DataFieldTypeServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	
	/**
	 * @param entityClass
	 */
	public DataFieldTypeServiceImpl() {
		super(DataFieldType.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataFieldType#findById(java.lang.Integer)
	 */
	@Override
	public DataFieldType findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataFieldType#findByCode(java.lang.String)
	 */
	@Override
	public DataFieldType findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataFieldType#findByName(java.lang.String)
	 */
	@Override
	public DataFieldType findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataFieldType#findAll(java.util.Map)
	 */
	@Override
	public List<DataFieldType> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataFieldType#createDataFieldType(com.nathanclaire.alantra.datasource.rest.request.ServiceRequest)
	 */
	@Override
	public DataFieldType create(DataFieldTypeRequest dataFieldTypeRequest) throws ApplicationException {
		return createInstance(dataFieldTypeRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataFieldType#deleteDataFieldType(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataFieldType#updateDataFieldType(com.nathanclaire.alantra.datasource.rest.request.ServiceRequest)
	 */
	@Override
	public DataFieldType update(DataFieldTypeRequest dataFieldTypeRequest) throws ApplicationException {
		return updateInstance(dataFieldTypeRequest);
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
		for(DataFieldType datafieldtype: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(datafieldtype.getId(), datafieldtype.getCode(), datafieldtype.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public DataFieldType convertRequestToModel(DataFieldTypeRequest dataFieldTypeRequest) 
     throws ApplicationException {
		DataFieldType dataFieldType = new DataFieldType();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtil.copyProperties(dataFieldTypeRequest, dataFieldType, allowedEntityFields);
    	//Process many to one relationships
		return dataFieldType;
	}
	
	@Override
	public DataFieldTypeResponse convertModelToResponse(DataFieldType model) throws ApplicationException {
		if (model == null) return null;
		DataFieldTypeResponse dataFieldTypeResponse = new DataFieldTypeResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtil.copyProperties(model, dataFieldTypeResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		return dataFieldTypeResponse;
	}
}
