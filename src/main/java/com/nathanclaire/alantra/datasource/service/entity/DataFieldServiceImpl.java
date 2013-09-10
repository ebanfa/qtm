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

import com.nathanclaire.alantra.datasource.model.DataField;
import com.nathanclaire.alantra.datasource.model.DataTransformer;
import com.nathanclaire.alantra.datasource.model.DataStructure;
import com.nathanclaire.alantra.datasource.model.DataFieldType;
import com.nathanclaire.alantra.datasource.request.DataFieldRequest;
import com.nathanclaire.alantra.datasource.response.DataFieldResponse;
import com.nathanclaire.alantra.datasource.service.entity.DataTransformerService;
import com.nathanclaire.alantra.datasource.service.entity.DataStructureService;
import com.nathanclaire.alantra.datasource.service.entity.DataFieldTypeService;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtil;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class DataFieldServiceImpl 
	extends BaseEntityServiceImpl<DataField, DataFieldResponse, DataFieldRequest> 
	implements DataFieldService
{
	private static final String LIST_ITEM_DATATRANSFORMER = "dataTransformer";
	private static final String LIST_ITEM_DATASTRUCTURE = "dataStructure";
	private static final String LIST_ITEM_DATAFIELDTYPE = "dataFieldType";
	private static final String ENTITY_NAME = "DataField";
	private static final String LIST_ACTIVITY_CODE = "LIST_DATASOURCE_DATAFIELD";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_DATASOURCE_DATAFIELD";
	
	private Logger logger = LoggerFactory.getLogger(DataFieldServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	@Inject
	DataTransformerService  dataTransformerService;
	@Inject
	DataStructureService  dataStructureService;
	@Inject
	DataFieldTypeService  dataFieldTypeService;
	
	/**
	 * @param entityClass
	 */
	public DataFieldServiceImpl() {
		super(DataField.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataField#findById(java.lang.Integer)
	 */
	@Override
	public DataField findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataField#findByCode(java.lang.String)
	 */
	@Override
	public DataField findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataField#findByName(java.lang.String)
	 */
	@Override
	public DataField findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataField#findAll(java.util.Map)
	 */
	@Override
	public List<DataField> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataField#createDataField(com.nathanclaire.alantra.datasource.rest.request.ServiceRequest)
	 */
	@Override
	public DataField create(DataFieldRequest dataFieldRequest) throws ApplicationException {
		return createInstance(dataFieldRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataField#deleteDataField(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataField#updateDataField(com.nathanclaire.alantra.datasource.rest.request.ServiceRequest)
	 */
	@Override
	public DataField update(DataFieldRequest dataFieldRequest) throws ApplicationException {
		return updateInstance(dataFieldRequest);
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
		List<ListItemResponse> dataTransformers = dataTransformerService.asListItem();
		List<ListItemResponse> dataStructures = dataStructureService.asListItem();
		List<ListItemResponse> dataFieldTypes = dataFieldTypeService.asListItem();
    	
		listItems.put(LIST_ITEM_DATATRANSFORMER, dataTransformers); 
		listItems.put(LIST_ITEM_DATASTRUCTURE, dataStructures); 
		listItems.put(LIST_ITEM_DATAFIELDTYPE, dataFieldTypes); 
		return listItems;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#asListItem()
	 */
	@Override
	public List<ListItemResponse> asListItem() throws ApplicationException {
		List<ListItemResponse> listItems = new ArrayList<ListItemResponse>();
		queryParameters.clear();
		for(DataField datafield: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(datafield.getId(), datafield.getCode(), datafield.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public DataField convertRequestToModel(DataFieldRequest dataFieldRequest) 
     throws ApplicationException {
		DataField dataField = new DataField();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtil.copyProperties(dataFieldRequest, dataField, allowedEntityFields);
    	//Process many to one relationships
    	if (dataFieldRequest.getDataTransformerId() != null)
    	{
    		DataTransformer dataTransformer = getEntityManager().find(DataTransformer.class, dataFieldRequest.getDataTransformerId());
    		dataField.setDataTransformer(dataTransformer);
    	}
    	if (dataFieldRequest.getDataStructureId() != null)
    	{
    		DataStructure dataStructure = getEntityManager().find(DataStructure.class, dataFieldRequest.getDataStructureId());
    		dataField.setDataStructure(dataStructure);
    	}
    	if (dataFieldRequest.getDataFieldTypeId() != null)
    	{
    		DataFieldType dataFieldType = getEntityManager().find(DataFieldType.class, dataFieldRequest.getDataFieldTypeId());
    		dataField.setDataFieldType(dataFieldType);
    	}
		return dataField;
	}
	
	@Override
	public DataFieldResponse convertModelToResponse(DataField model) throws ApplicationException {
		if (model == null) return null;
		DataFieldResponse dataFieldResponse = new DataFieldResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtil.copyProperties(model, dataFieldResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		if(model.getDataTransformer() != null)
			dataFieldResponse.setDataTransformerId(model.getDataTransformer().getId());
			dataFieldResponse.setDataTransformerText(model.getDataTransformer().getName());
		if(model.getDataStructure() != null)
			dataFieldResponse.setDataStructureId(model.getDataStructure().getId());
			dataFieldResponse.setDataStructureText(model.getDataStructure().getName());
		if(model.getDataFieldType() != null)
			dataFieldResponse.setDataFieldTypeId(model.getDataFieldType().getId());
			dataFieldResponse.setDataFieldTypeText(model.getDataFieldType().getName());
		return dataFieldResponse;
	}
}
