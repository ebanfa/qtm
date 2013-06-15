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

import com.nathanclaire.alantra.datasource.model.DataStructure;
import com.nathanclaire.alantra.datasource.request.DataStructureRequest;
import com.nathanclaire.alantra.datasource.response.DataStructureResponse;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtils;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class DataStructureServiceImpl 
	extends BaseEntityServiceImpl<DataStructure, DataStructureResponse, DataStructureRequest> 
	implements DataStructureService
{
	private static final String ENTITY_NAME = "DataStructure";
	private static final String LIST_ACTIVITY_CODE = "LIST_DATASOURCE_DATASTRUCTURE";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_DATASOURCE_DATASTRUCTURE";
	
	private Logger logger = LoggerFactory.getLogger(DataStructureServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	
	/**
	 * @param entityClass
	 */
	public DataStructureServiceImpl() {
		super(DataStructure.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataStructure#findById(java.lang.Integer)
	 */
	@Override
	public DataStructure findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataStructure#findByCode(java.lang.String)
	 */
	@Override
	public DataStructure findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataStructure#findByName(java.lang.String)
	 */
	@Override
	public DataStructure findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataStructure#findAll(java.util.Map)
	 */
	@Override
	public List<DataStructure> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataStructure#createDataStructure(com.nathanclaire.alantra.datasource.rest.request.ServiceRequest)
	 */
	@Override
	public DataStructure create(DataStructureRequest dataStructureRequest) throws ApplicationException {
		return createInstance(dataStructureRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataStructure#deleteDataStructure(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataStructure#updateDataStructure(com.nathanclaire.alantra.datasource.rest.request.ServiceRequest)
	 */
	@Override
	public DataStructure update(DataStructureRequest dataStructureRequest) throws ApplicationException {
		return updateInstance(dataStructureRequest);
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
		for(DataStructure datastructure: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(datastructure.getId(), datastructure.getCode(), datastructure.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public DataStructure convertRequestToModel(DataStructureRequest dataStructureRequest) 
     throws ApplicationException {
		DataStructure dataStructure = new DataStructure();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(dataStructureRequest, dataStructure, allowedEntityFields);
    	//Process many to one relationships
		return dataStructure;
	}
	
	@Override
	public DataStructureResponse convertModelToResponse(DataStructure model) throws ApplicationException {
		if (model == null) return null;
		DataStructureResponse dataStructureResponse = new DataStructureResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(model, dataStructureResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		return dataStructureResponse;
	}
}
