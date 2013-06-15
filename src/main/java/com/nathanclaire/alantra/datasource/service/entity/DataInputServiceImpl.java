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

import com.nathanclaire.alantra.datasource.model.DataInput;
import com.nathanclaire.alantra.datasource.model.DataLoader;
import com.nathanclaire.alantra.datasource.model.Data;
import com.nathanclaire.alantra.datasource.request.DataInputRequest;
import com.nathanclaire.alantra.datasource.response.DataInputResponse;
import com.nathanclaire.alantra.datasource.service.entity.DataLoaderService;
import com.nathanclaire.alantra.datasource.service.entity.DataService;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtils;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class DataInputServiceImpl 
	extends BaseEntityServiceImpl<DataInput, DataInputResponse, DataInputRequest> 
	implements DataInputService
{
	private static final String LIST_ITEM_DATALOADER = "dataLoader";
	private static final String LIST_ITEM_DATA = "data";
	private static final String ENTITY_NAME = "DataInput";
	private static final String LIST_ACTIVITY_CODE = "LIST_DATASOURCE_DATAINPUT";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_DATASOURCE_DATAINPUT";
	
	private Logger logger = LoggerFactory.getLogger(DataInputServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	@Inject
	DataLoaderService  dataLoaderService;
	@Inject
	DataService  dataService;
	
	/**
	 * @param entityClass
	 */
	public DataInputServiceImpl() {
		super(DataInput.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataInput#findById(java.lang.Integer)
	 */
	@Override
	public DataInput findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataInput#findByCode(java.lang.String)
	 */
	@Override
	public DataInput findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataInput#findByName(java.lang.String)
	 */
	@Override
	public DataInput findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataInput#findAll(java.util.Map)
	 */
	@Override
	public List<DataInput> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataInput#createDataInput(com.nathanclaire.alantra.datasource.rest.request.ServiceRequest)
	 */
	@Override
	public DataInput create(DataInputRequest dataInputRequest) throws ApplicationException {
		return createInstance(dataInputRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataInput#deleteDataInput(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataInput#updateDataInput(com.nathanclaire.alantra.datasource.rest.request.ServiceRequest)
	 */
	@Override
	public DataInput update(DataInputRequest dataInputRequest) throws ApplicationException {
		return updateInstance(dataInputRequest);
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
		List<ListItemResponse> dataLoaders = dataLoaderService.asListItem();
		List<ListItemResponse> datas = dataService.asListItem();
    	
		listItems.put(LIST_ITEM_DATALOADER, dataLoaders); 
		listItems.put(LIST_ITEM_DATA, datas); 
		return listItems;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#asListItem()
	 */
	@Override
	public List<ListItemResponse> asListItem() throws ApplicationException {
		List<ListItemResponse> listItems = new ArrayList<ListItemResponse>();
		queryParameters.clear();
		for(DataInput datainput: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(datainput.getId(), datainput.getCode(), datainput.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public DataInput convertRequestToModel(DataInputRequest dataInputRequest) 
     throws ApplicationException {
		DataInput dataInput = new DataInput();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(dataInputRequest, dataInput, allowedEntityFields);
    	//Process many to one relationships
    	if (dataInputRequest.getDataLoaderId() != null)
    	{
    		DataLoader dataLoader = getEntityManager().find(DataLoader.class, dataInputRequest.getDataLoaderId());
    		dataInput.setDataLoader(dataLoader);
    	}
    	if (dataInputRequest.getDataId() != null)
    	{
    		Data data = getEntityManager().find(Data.class, dataInputRequest.getDataId());
    		dataInput.setData(data);
    	}
		return dataInput;
	}
	
	@Override
	public DataInputResponse convertModelToResponse(DataInput model) throws ApplicationException {
		if (model == null) return null;
		DataInputResponse dataInputResponse = new DataInputResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(model, dataInputResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		if(model.getDataLoader() != null)
			dataInputResponse.setDataLoaderId(model.getDataLoader().getId());
			dataInputResponse.setDataLoaderText(model.getDataLoader().getName());
		if(model.getData() != null)
			dataInputResponse.setDataId(model.getData().getId());
			dataInputResponse.setDataText(model.getData().getName());
		return dataInputResponse;
	}
}
