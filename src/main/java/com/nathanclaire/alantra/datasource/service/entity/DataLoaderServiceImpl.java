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

import com.nathanclaire.alantra.datasource.model.DataLoader;
import com.nathanclaire.alantra.datasource.request.DataLoaderRequest;
import com.nathanclaire.alantra.datasource.response.DataLoaderResponse;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtils;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class DataLoaderServiceImpl 
	extends BaseEntityServiceImpl<DataLoader, DataLoaderResponse, DataLoaderRequest> 
	implements DataLoaderService
{
	private static final String ENTITY_NAME = "DataLoader";
	private static final String LIST_ACTIVITY_CODE = "LIST_DATASOURCE_DATALOADER";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_DATASOURCE_DATALOADER";
	
	private Logger logger = LoggerFactory.getLogger(DataLoaderServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	
	/**
	 * @param entityClass
	 */
	public DataLoaderServiceImpl() {
		super(DataLoader.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataLoader#findById(java.lang.Integer)
	 */
	@Override
	public DataLoader findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataLoader#findByCode(java.lang.String)
	 */
	@Override
	public DataLoader findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataLoader#findByName(java.lang.String)
	 */
	@Override
	public DataLoader findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataLoader#findAll(java.util.Map)
	 */
	@Override
	public List<DataLoader> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataLoader#createDataLoader(com.nathanclaire.alantra.datasource.rest.request.ServiceRequest)
	 */
	@Override
	public DataLoader create(DataLoaderRequest dataLoaderRequest) throws ApplicationException {
		return createInstance(dataLoaderRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataLoader#deleteDataLoader(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataLoader#updateDataLoader(com.nathanclaire.alantra.datasource.rest.request.ServiceRequest)
	 */
	@Override
	public DataLoader update(DataLoaderRequest dataLoaderRequest) throws ApplicationException {
		return updateInstance(dataLoaderRequest);
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
		for(DataLoader dataloader: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(dataloader.getId(), dataloader.getCode(), dataloader.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public DataLoader convertRequestToModel(DataLoaderRequest dataLoaderRequest) 
     throws ApplicationException {
		DataLoader dataLoader = new DataLoader();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(dataLoaderRequest, dataLoader, allowedEntityFields);
    	//Process many to one relationships
		return dataLoader;
	}
	
	@Override
	public DataLoaderResponse convertModelToResponse(DataLoader model) throws ApplicationException {
		if (model == null) return null;
		DataLoaderResponse dataLoaderResponse = new DataLoaderResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(model, dataLoaderResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		return dataLoaderResponse;
	}
}
