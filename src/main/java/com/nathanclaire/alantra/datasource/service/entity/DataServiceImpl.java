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

import com.nathanclaire.alantra.datasource.model.Data;
import com.nathanclaire.alantra.datasource.model.DataType;
import com.nathanclaire.alantra.datasource.model.DataStructure;
import com.nathanclaire.alantra.datasource.model.DataChannel;
import com.nathanclaire.alantra.datasource.request.DataRequest;
import com.nathanclaire.alantra.datasource.response.DataResponse;
import com.nathanclaire.alantra.datasource.service.entity.DataTypeService;
import com.nathanclaire.alantra.datasource.service.entity.DataStructureService;
import com.nathanclaire.alantra.datasource.service.entity.DataChannelService;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtils;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class DataServiceImpl 
	extends BaseEntityServiceImpl<Data, DataResponse, DataRequest> 
	implements DataService
{
	private static final String LIST_ITEM_DATATYPE = "dataType";
	private static final String LIST_ITEM_DATASTRUCTURE = "dataStructure";
	private static final String LIST_ITEM_DATACHANNEL = "dataChannel";
	private static final String ENTITY_NAME = "Data";
	private static final String LIST_ACTIVITY_CODE = "LIST_DATASOURCE_DATA";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_DATASOURCE_DATA";
	
	private Logger logger = LoggerFactory.getLogger(DataServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	@Inject
	DataTypeService  dataTypeService;
	@Inject
	DataStructureService  dataStructureService;
	@Inject
	DataChannelService  dataChannelService;
	
	/**
	 * @param entityClass
	 */
	public DataServiceImpl() {
		super(Data.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.Data#findById(java.lang.Integer)
	 */
	@Override
	public Data findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.Data#findByCode(java.lang.String)
	 */
	@Override
	public Data findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.Data#findByName(java.lang.String)
	 */
	@Override
	public Data findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.Data#findAll(java.util.Map)
	 */
	@Override
	public List<Data> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.Data#createData(com.nathanclaire.alantra.datasource.rest.request.ServiceRequest)
	 */
	@Override
	public Data create(DataRequest dataRequest) throws ApplicationException {
		return createInstance(dataRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.Data#deleteData(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.Data#updateData(com.nathanclaire.alantra.datasource.rest.request.ServiceRequest)
	 */
	@Override
	public Data update(DataRequest dataRequest) throws ApplicationException {
		return updateInstance(dataRequest);
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
		List<ListItemResponse> dataTypes = dataTypeService.asListItem();
		List<ListItemResponse> dataStructures = dataStructureService.asListItem();
		List<ListItemResponse> dataChannels = dataChannelService.asListItem();
    	
		listItems.put(LIST_ITEM_DATATYPE, dataTypes); 
		listItems.put(LIST_ITEM_DATASTRUCTURE, dataStructures); 
		listItems.put(LIST_ITEM_DATACHANNEL, dataChannels); 
		return listItems;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#asListItem()
	 */
	@Override
	public List<ListItemResponse> asListItem() throws ApplicationException {
		List<ListItemResponse> listItems = new ArrayList<ListItemResponse>();
		queryParameters.clear();
		for(Data data: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(data.getId(), data.getCode(), data.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public Data convertRequestToModel(DataRequest dataRequest) 
     throws ApplicationException {
		Data data = new Data();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(dataRequest, data, allowedEntityFields);
    	//Process many to one relationships
    	if (dataRequest.getDataTypeId() != null)
    	{
    		DataType dataType = getEntityManager().find(DataType.class, dataRequest.getDataTypeId());
    		data.setDataType(dataType);
    	}
    	if (dataRequest.getDataStructureId() != null)
    	{
    		DataStructure dataStructure = getEntityManager().find(DataStructure.class, dataRequest.getDataStructureId());
    		data.setDataStructure(dataStructure);
    	}
    	if (dataRequest.getDataChannelId() != null)
    	{
    		DataChannel dataChannel = getEntityManager().find(DataChannel.class, dataRequest.getDataChannelId());
    		data.setDataChannel(dataChannel);
    	}
		return data;
	}
	
	@Override
	public DataResponse convertModelToResponse(Data model) throws ApplicationException {
		if (model == null) return null;
		DataResponse dataResponse = new DataResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(model, dataResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		if(model.getDataType() != null)
			dataResponse.setDataTypeId(model.getDataType().getId());
			dataResponse.setDataTypeText(model.getDataType().getName());
		if(model.getDataStructure() != null)
			dataResponse.setDataStructureId(model.getDataStructure().getId());
			dataResponse.setDataStructureText(model.getDataStructure().getName());
		if(model.getDataChannel() != null)
			dataResponse.setDataChannelId(model.getDataChannel().getId());
			dataResponse.setDataChannelText(model.getDataChannel().getName());
		return dataResponse;
	}
}
