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

import com.nathanclaire.alantra.datasource.model.DataExtractor;
import com.nathanclaire.alantra.datasource.request.DataExtractorRequest;
import com.nathanclaire.alantra.datasource.response.DataExtractorResponse;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtils;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class DataExtractorServiceImpl 
	extends BaseEntityServiceImpl<DataExtractor, DataExtractorResponse, DataExtractorRequest> 
	implements DataExtractorService
{
	private static final String ENTITY_NAME = "DataExtractor";
	private static final String LIST_ACTIVITY_CODE = "LIST_DATASOURCE_DATAEXTRACTOR";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_DATASOURCE_DATAEXTRACTOR";
	
	private Logger logger = LoggerFactory.getLogger(DataExtractorServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	
	/**
	 * @param entityClass
	 */
	public DataExtractorServiceImpl() {
		super(DataExtractor.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataExtractor#findById(java.lang.Integer)
	 */
	@Override
	public DataExtractor findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataExtractor#findByCode(java.lang.String)
	 */
	@Override
	public DataExtractor findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataExtractor#findByName(java.lang.String)
	 */
	@Override
	public DataExtractor findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataExtractor#findAll(java.util.Map)
	 */
	@Override
	public List<DataExtractor> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataExtractor#createDataExtractor(com.nathanclaire.alantra.datasource.rest.request.ServiceRequest)
	 */
	@Override
	public DataExtractor create(DataExtractorRequest dataExtractorRequest) throws ApplicationException {
		return createInstance(dataExtractorRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataExtractor#deleteDataExtractor(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataExtractor#updateDataExtractor(com.nathanclaire.alantra.datasource.rest.request.ServiceRequest)
	 */
	@Override
	public DataExtractor update(DataExtractorRequest dataExtractorRequest) throws ApplicationException {
		return updateInstance(dataExtractorRequest);
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
		for(DataExtractor dataextractor: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(dataextractor.getId(), dataextractor.getCode(), dataextractor.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public DataExtractor convertRequestToModel(DataExtractorRequest dataExtractorRequest) 
     throws ApplicationException {
		DataExtractor dataExtractor = new DataExtractor();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(dataExtractorRequest, dataExtractor, allowedEntityFields);
    	//Process many to one relationships
		return dataExtractor;
	}
	
	@Override
	public DataExtractorResponse convertModelToResponse(DataExtractor model) throws ApplicationException {
		if (model == null) return null;
		DataExtractorResponse dataExtractorResponse = new DataExtractorResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(model, dataExtractorResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		return dataExtractorResponse;
	}
}
