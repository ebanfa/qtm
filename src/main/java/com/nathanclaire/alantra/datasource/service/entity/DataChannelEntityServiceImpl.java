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

import com.nathanclaire.alantra.datasource.model.DataChannel;
import com.nathanclaire.alantra.datasource.model.DataChannelCategory;
import com.nathanclaire.alantra.datasource.model.DataChannelStatus;
import com.nathanclaire.alantra.datasource.model.DataChannelType;
import com.nathanclaire.alantra.datasource.request.DataChannelRequest;
import com.nathanclaire.alantra.datasource.response.DataChannelResponse;
import com.nathanclaire.alantra.datasource.service.entity.DataChannelStatusService;
import com.nathanclaire.alantra.datasource.service.entity.DataChannelTypeService;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtil;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class DataChannelEntityServiceImpl 
	extends BaseEntityServiceImpl<DataChannel, DataChannelResponse, DataChannelRequest> 
	implements DataChannelEntityService
{
	private static final String LIST_ITEM_DATACHANNELSTATUS = "dataChannelStatus";
	private static final String LIST_ITEM_DATACHANNELTYPE = "dataChannelType";
	private static final String ENTITY_NAME = "DataChannel";
	private static final String LIST_ACTIVITY_CODE = "LIST_DATASOURCE_DATACHANNEL";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_DATASOURCE_DATACHANNEL";
	
	private Logger logger = LoggerFactory.getLogger(DataChannelEntityServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	@Inject
	DataChannelStatusService  dataChannelStatusService;
	@Inject
	DataChannelTypeService  dataChannelTypeService;
	
	/**
	 * @param entityClass
	 */
	public DataChannelEntityServiceImpl() {
		super(DataChannel.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataChannel#findById(java.lang.Integer)
	 */
	@Override
	public DataChannel findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataChannel#findByCode(java.lang.String)
	 */
	@Override
	public DataChannel findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataChannel#findByName(java.lang.String)
	 */
	@Override
	public DataChannel findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataChannel#findAll(java.util.Map)
	 */
	@Override
	public List<DataChannel> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}
	
	/**
	 * @param channel
	 * @return
	 */
	@Override
	public DataChannelCategory getChannelCategory(DataChannel channel) throws ApplicationException {
		if(channel == null) 	throw new ApplicationException(INVALID_CHANNEL_SPECIFIED);
		DataChannelType channelType = channel.getDataChannelType();
		if(channelType == null) throw new ApplicationException(CONFIG_ERROR_INVALID_NO_PARENT_TYPE_FOUND);
		DataChannelCategory channelCategory = channelType.getDataChannelCategory();
		if(channelCategory == null) throw new ApplicationException(CONFIG_ERROR_INVALID_NO_PARENT_CATEGORY_FOUND);
		return channelCategory;
	}
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataChannel#createDataChannel(com.nathanclaire.alantra.datasource.rest.request.ServiceRequest)
	 */
	@Override
	public DataChannel create(DataChannelRequest dataChannelRequest) throws ApplicationException {
		return createInstance(dataChannelRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataChannel#deleteDataChannel(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataChannel#updateDataChannel(com.nathanclaire.alantra.datasource.rest.request.ServiceRequest)
	 */
	@Override
	public DataChannel update(DataChannelRequest dataChannelRequest) throws ApplicationException {
		return updateInstance(dataChannelRequest);
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
		List<ListItemResponse> dataChannelStatuss = dataChannelStatusService.asListItem();
		List<ListItemResponse> dataChannelTypes = dataChannelTypeService.asListItem();
    	
		listItems.put(LIST_ITEM_DATACHANNELSTATUS, dataChannelStatuss); 
		listItems.put(LIST_ITEM_DATACHANNELTYPE, dataChannelTypes); 
		return listItems;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#asListItem()
	 */
	@Override
	public List<ListItemResponse> asListItem() throws ApplicationException {
		List<ListItemResponse> listItems = new ArrayList<ListItemResponse>();
		queryParameters.clear();
		for(DataChannel datachannel: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(datachannel.getId(), datachannel.getCode(), datachannel.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public DataChannel convertRequestToModel(DataChannelRequest dataChannelRequest) 
     throws ApplicationException {
		DataChannel dataChannel = new DataChannel();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtil.copyProperties(dataChannelRequest, dataChannel, allowedEntityFields);
    	//Process many to one relationships
    	if (dataChannelRequest.getDataChannelStatusId() != null)
    	{
    		DataChannelStatus dataChannelStatus = getEntityManager().find(DataChannelStatus.class, dataChannelRequest.getDataChannelStatusId());
    		dataChannel.setDataChannelStatus(dataChannelStatus);
    	}
    	if (dataChannelRequest.getDataChannelTypeId() != null)
    	{
    		DataChannelType dataChannelType = getEntityManager().find(DataChannelType.class, dataChannelRequest.getDataChannelTypeId());
    		dataChannel.setDataChannelType(dataChannelType);
    	}
		return dataChannel;
	}
	
	@Override
	public DataChannelResponse convertModelToResponse(DataChannel model) throws ApplicationException {
		if (model == null) return null;
		DataChannelResponse dataChannelResponse = new DataChannelResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtil.copyProperties(model, dataChannelResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		if(model.getDataChannelStatus() != null)
			dataChannelResponse.setDataChannelStatusId(model.getDataChannelStatus().getId());
			dataChannelResponse.setDataChannelStatusText(model.getDataChannelStatus().getName());
		if(model.getDataChannelType() != null)
			dataChannelResponse.setDataChannelTypeId(model.getDataChannelType().getId());
			dataChannelResponse.setDataChannelTypeText(model.getDataChannelType().getName());
		return dataChannelResponse;
	}
}
