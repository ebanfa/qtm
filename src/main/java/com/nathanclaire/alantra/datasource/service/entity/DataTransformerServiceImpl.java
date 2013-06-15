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

import com.nathanclaire.alantra.datasource.model.DataTransformer;
import com.nathanclaire.alantra.datasource.request.DataTransformerRequest;
import com.nathanclaire.alantra.datasource.response.DataTransformerResponse;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtils;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class DataTransformerServiceImpl 
	extends BaseEntityServiceImpl<DataTransformer, DataTransformerResponse, DataTransformerRequest> 
	implements DataTransformerService
{
	private static final String ENTITY_NAME = "DataTransformer";
	private static final String LIST_ACTIVITY_CODE = "LIST_DATASOURCE_DATATRANSFORMER";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_DATASOURCE_DATATRANSFORMER";
	
	private Logger logger = LoggerFactory.getLogger(DataTransformerServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	
	/**
	 * @param entityClass
	 */
	public DataTransformerServiceImpl() {
		super(DataTransformer.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataTransformer#findById(java.lang.Integer)
	 */
	@Override
	public DataTransformer findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataTransformer#findByCode(java.lang.String)
	 */
	@Override
	public DataTransformer findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataTransformer#findByName(java.lang.String)
	 */
	@Override
	public DataTransformer findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataTransformer#findAll(java.util.Map)
	 */
	@Override
	public List<DataTransformer> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataTransformer#createDataTransformer(com.nathanclaire.alantra.datasource.rest.request.ServiceRequest)
	 */
	@Override
	public DataTransformer create(DataTransformerRequest dataTransformerRequest) throws ApplicationException {
		return createInstance(dataTransformerRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataTransformer#deleteDataTransformer(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataTransformer#updateDataTransformer(com.nathanclaire.alantra.datasource.rest.request.ServiceRequest)
	 */
	@Override
	public DataTransformer update(DataTransformerRequest dataTransformerRequest) throws ApplicationException {
		return updateInstance(dataTransformerRequest);
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
		for(DataTransformer datatransformer: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(datatransformer.getId(), datatransformer.getCode(), datatransformer.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public DataTransformer convertRequestToModel(DataTransformerRequest dataTransformerRequest) 
     throws ApplicationException {
		DataTransformer dataTransformer = new DataTransformer();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(dataTransformerRequest, dataTransformer, allowedEntityFields);
    	//Process many to one relationships
		return dataTransformer;
	}
	
	@Override
	public DataTransformerResponse convertModelToResponse(DataTransformer model) throws ApplicationException {
		if (model == null) return null;
		DataTransformerResponse dataTransformerResponse = new DataTransformerResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(model, dataTransformerResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		return dataTransformerResponse;
	}
}
