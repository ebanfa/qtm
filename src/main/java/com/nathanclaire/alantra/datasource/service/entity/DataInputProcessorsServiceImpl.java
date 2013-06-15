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

import com.nathanclaire.alantra.datasource.model.DataInputProcessors;
import com.nathanclaire.alantra.datasource.model.DataProcessor;
import com.nathanclaire.alantra.datasource.model.DataInput;
import com.nathanclaire.alantra.datasource.request.DataInputProcessorsRequest;
import com.nathanclaire.alantra.datasource.response.DataInputProcessorsResponse;
import com.nathanclaire.alantra.datasource.service.entity.DataProcessorService;
import com.nathanclaire.alantra.datasource.service.entity.DataInputService;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtils;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class DataInputProcessorsServiceImpl 
	extends BaseEntityServiceImpl<DataInputProcessors, DataInputProcessorsResponse, DataInputProcessorsRequest> 
	implements DataInputProcessorsService
{
	private static final String LIST_ITEM_DATAPROCESSOR = "dataProcessor";
	private static final String LIST_ITEM_DATAINPUT = "dataInput";
	private static final String ENTITY_NAME = "DataInputProcessors";
	private static final String LIST_ACTIVITY_CODE = "LIST_DATASOURCE_DATAINPUTPROCESSORS";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_DATASOURCE_DATAINPUTPROCESSORS";
	
	private Logger logger = LoggerFactory.getLogger(DataInputProcessorsServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	@Inject
	DataProcessorService  dataProcessorService;
	@Inject
	DataInputService  dataInputService;
	
	/**
	 * @param entityClass
	 */
	public DataInputProcessorsServiceImpl() {
		super(DataInputProcessors.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataInputProcessors#findById(java.lang.Integer)
	 */
	@Override
	public DataInputProcessors findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataInputProcessors#findByCode(java.lang.String)
	 */
	@Override
	public DataInputProcessors findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataInputProcessors#findByName(java.lang.String)
	 */
	@Override
	public DataInputProcessors findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataInputProcessors#findAll(java.util.Map)
	 */
	@Override
	public List<DataInputProcessors> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataInputProcessors#createDataInputProcessors(com.nathanclaire.alantra.datasource.rest.request.ServiceRequest)
	 */
	@Override
	public DataInputProcessors create(DataInputProcessorsRequest dataInputProcessorsRequest) throws ApplicationException {
		return createInstance(dataInputProcessorsRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataInputProcessors#deleteDataInputProcessors(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataInputProcessors#updateDataInputProcessors(com.nathanclaire.alantra.datasource.rest.request.ServiceRequest)
	 */
	@Override
	public DataInputProcessors update(DataInputProcessorsRequest dataInputProcessorsRequest) throws ApplicationException {
		return updateInstance(dataInputProcessorsRequest);
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
		List<ListItemResponse> dataProcessors = dataProcessorService.asListItem();
		List<ListItemResponse> dataInputs = dataInputService.asListItem();
    	
		listItems.put(LIST_ITEM_DATAPROCESSOR, dataProcessors); 
		listItems.put(LIST_ITEM_DATAINPUT, dataInputs); 
		return listItems;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#asListItem()
	 */
	@Override
	public List<ListItemResponse> asListItem() throws ApplicationException {
		List<ListItemResponse> listItems = new ArrayList<ListItemResponse>();
		queryParameters.clear();
		for(DataInputProcessors datainputprocessors: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(datainputprocessors.getId(), datainputprocessors.getCode(), datainputprocessors.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public DataInputProcessors convertRequestToModel(DataInputProcessorsRequest dataInputProcessorsRequest) 
     throws ApplicationException {
		DataInputProcessors dataInputProcessors = new DataInputProcessors();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(dataInputProcessorsRequest, dataInputProcessors, allowedEntityFields);
    	//Process many to one relationships
    	if (dataInputProcessorsRequest.getDataProcessorId() != null)
    	{
    		DataProcessor dataProcessor = getEntityManager().find(DataProcessor.class, dataInputProcessorsRequest.getDataProcessorId());
    		dataInputProcessors.setDataProcessor(dataProcessor);
    	}
    	if (dataInputProcessorsRequest.getDataInputId() != null)
    	{
    		DataInput dataInput = getEntityManager().find(DataInput.class, dataInputProcessorsRequest.getDataInputId());
    		dataInputProcessors.setDataInput(dataInput);
    	}
		return dataInputProcessors;
	}
	
	@Override
	public DataInputProcessorsResponse convertModelToResponse(DataInputProcessors model) throws ApplicationException {
		if (model == null) return null;
		DataInputProcessorsResponse dataInputProcessorsResponse = new DataInputProcessorsResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(model, dataInputProcessorsResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		if(model.getDataProcessor() != null)
			dataInputProcessorsResponse.setDataProcessorId(model.getDataProcessor().getId());
			dataInputProcessorsResponse.setDataProcessorText(model.getDataProcessor().getName());
		if(model.getDataInput() != null)
			dataInputProcessorsResponse.setDataInputId(model.getDataInput().getId());
			dataInputProcessorsResponse.setDataInputText(model.getDataInput().getName());
		return dataInputProcessorsResponse;
	}
}
