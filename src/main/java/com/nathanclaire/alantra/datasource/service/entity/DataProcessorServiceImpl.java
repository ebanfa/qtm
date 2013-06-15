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

import com.nathanclaire.alantra.datasource.model.DataProcessor;
import com.nathanclaire.alantra.datasource.request.DataProcessorRequest;
import com.nathanclaire.alantra.datasource.response.DataProcessorResponse;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtils;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class DataProcessorServiceImpl 
	extends BaseEntityServiceImpl<DataProcessor, DataProcessorResponse, DataProcessorRequest> 
	implements DataProcessorService
{
	private static final String ENTITY_NAME = "DataProcessor";
	private static final String LIST_ACTIVITY_CODE = "LIST_DATASOURCE_DATAPROCESSOR";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_DATASOURCE_DATAPROCESSOR";
	
	private Logger logger = LoggerFactory.getLogger(DataProcessorServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	
	/**
	 * @param entityClass
	 */
	public DataProcessorServiceImpl() {
		super(DataProcessor.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataProcessor#findById(java.lang.Integer)
	 */
	@Override
	public DataProcessor findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataProcessor#findByCode(java.lang.String)
	 */
	@Override
	public DataProcessor findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataProcessor#findByName(java.lang.String)
	 */
	@Override
	public DataProcessor findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataProcessor#findAll(java.util.Map)
	 */
	@Override
	public List<DataProcessor> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataProcessor#createDataProcessor(com.nathanclaire.alantra.datasource.rest.request.ServiceRequest)
	 */
	@Override
	public DataProcessor create(DataProcessorRequest dataProcessorRequest) throws ApplicationException {
		return createInstance(dataProcessorRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataProcessor#deleteDataProcessor(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataProcessor#updateDataProcessor(com.nathanclaire.alantra.datasource.rest.request.ServiceRequest)
	 */
	@Override
	public DataProcessor update(DataProcessorRequest dataProcessorRequest) throws ApplicationException {
		return updateInstance(dataProcessorRequest);
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
		for(DataProcessor dataprocessor: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(dataprocessor.getId(), dataprocessor.getCode(), dataprocessor.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public DataProcessor convertRequestToModel(DataProcessorRequest dataProcessorRequest) 
     throws ApplicationException {
		DataProcessor dataProcessor = new DataProcessor();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(dataProcessorRequest, dataProcessor, allowedEntityFields);
    	//Process many to one relationships
		return dataProcessor;
	}
	
	@Override
	public DataProcessorResponse convertModelToResponse(DataProcessor model) throws ApplicationException {
		if (model == null) return null;
		DataProcessorResponse dataProcessorResponse = new DataProcessorResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(model, dataProcessorResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		return dataProcessorResponse;
	}
}
