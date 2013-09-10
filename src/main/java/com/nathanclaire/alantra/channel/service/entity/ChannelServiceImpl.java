/**
 * 
 */
package com.nathanclaire.alantra.channel.service.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.ws.rs.core.MultivaluedMap;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.application.model.ApplicationEntityField;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtil;
import com.nathanclaire.alantra.channel.model.Channel;
import com.nathanclaire.alantra.channel.model.ChannelPipeline;
import com.nathanclaire.alantra.channel.model.ChannelStatus;
import com.nathanclaire.alantra.channel.model.ChannelType;
import com.nathanclaire.alantra.channel.request.ChannelRequest;
import com.nathanclaire.alantra.channel.response.ChannelResponse;
import com.nathanclaire.alantra.datasource.model.DataChannel;
import com.nathanclaire.alantra.datasource.service.entity.DataChannelEntityService;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class ChannelServiceImpl 
	extends BaseEntityServiceImpl<Channel, ChannelResponse, ChannelRequest> 
	implements ChannelService
{

	private static final String LIST_ITEM_CHANNELTYPE = "channelType";
	private static final String LIST_ITEM_CHANNELSTATUS = "channelStatus";
	private static final String LIST_ITEM_DATACHANNEL = "dataChannel";
	private static final String LIST_ITEM_CHANNELPIPELINE = "channelPipeline";
	private static final String ENTITY_NAME = "Channel";
	private static final String LIST_ACTIVITY_CODE = "LIST_CHANNEL_CHANNEL";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_CHANNEL_CHANNEL";
	
	private Logger logger = LoggerFactory.getLogger(ChannelServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	@Inject ChannelTypeService  channelTypeService;
	@Inject ChannelStatusService  channelStatusService;
	@Inject DataChannelEntityService  dataChannelService;
	@Inject ChannelPipelineService  channelPipelineService;
	
	/**
	 * @param entityClass
	 */
	public ChannelServiceImpl() {
		super(Channel.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.Channel#findById(java.lang.Integer)
	 */
	@Override
	public Channel findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.Channel#findByCode(java.lang.String)
	 */
	@Override
	public Channel findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.Channel#findByName(java.lang.String)
	 */
	@Override
	public Channel findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.Channel#findAll(java.util.Map)
	 */
	@Override
	public List<Channel> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.Channel#createChannel(com.nathanclaire.alantra.channel.rest.request.ServiceRequest)
	 */
	@Override
	public Channel create(ChannelRequest channelRequest) throws ApplicationException {
		return createInstance(channelRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.Channel#deleteChannel(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.Channel#updateChannel(com.nathanclaire.alantra.channel.rest.request.ServiceRequest)
	 */
	@Override
	public Channel update(ChannelRequest channelRequest) throws ApplicationException {
		return updateInstance(channelRequest);
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
        List<ListItemResponse> channelTypes = channelTypeService.asListItem();
        List<ListItemResponse> channelStatuss = channelStatusService.asListItem();
        List<ListItemResponse> dataChannels = dataChannelService.asListItem();
        List<ListItemResponse> channelPipelines = channelPipelineService.asListItem();

        listItems.put(LIST_ITEM_CHANNELTYPE, channelTypes);
        listItems.put(LIST_ITEM_CHANNELSTATUS, channelStatuss);
        listItems.put(LIST_ITEM_DATACHANNEL, dataChannels);
        listItems.put(LIST_ITEM_CHANNELPIPELINE, channelPipelines);
		return listItems;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#asListItem()
	 */
	@Override
	public List<ListItemResponse> asListItem() throws ApplicationException {
		List<ListItemResponse> listItems = new ArrayList<ListItemResponse>();
		queryParameters.clear();
		for(Channel channel: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(channel.getId(), channel.getCode(), channel.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public Channel convertRequestToModel(ChannelRequest channelRequest) 
     throws ApplicationException {
		Channel channel = new Channel();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtil.copyProperties(channelRequest, channel, allowedEntityFields);
    	//Process many to one relationships
        if (channelRequest.getChannelTypeId() != null)
    	{
    		ChannelType channelType = getEntityManager().find(ChannelType.class, channelRequest.getChannelTypeId());
    		channel.setChannelType(channelType);
    	}
        if (channelRequest.getChannelStatusId() != null)
    	{
    		ChannelStatus channelStatus = getEntityManager().find(ChannelStatus.class, channelRequest.getChannelStatusId());
    		channel.setChannelStatus(channelStatus);
    	}
        if (channelRequest.getDataChannelId() != null)
    	{
    		DataChannel dataChannel = getEntityManager().find(DataChannel.class, channelRequest.getDataChannelId());
    		channel.setDataChannel(dataChannel);
    	}
        if (channelRequest.getChannelPipelineId() != null)
    	{
    		ChannelPipeline channelPipeline = getEntityManager().find(ChannelPipeline.class, channelRequest.getChannelPipelineId());
    		channel.setPipeline(channelPipeline);
    	}
		return channel;
	}
	
	@Override
	public ChannelResponse convertModelToResponse(Channel model) throws ApplicationException {
		if (model == null) return null;
		ChannelResponse channelResponse = new ChannelResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtil.copyProperties(model, channelResponse, allowedEntityFields);
		if(model.getChannelType() != null)
			channelResponse.setChannelTypeId(model.getChannelType().getId());
			channelResponse.setChannelTypeText(model.getChannelType().getName());
		if(model.getChannelStatus() != null)
			channelResponse.setChannelStatusId(model.getChannelStatus().getId());
			channelResponse.setChannelStatusText(model.getChannelStatus().getName());
		if(model.getDataChannel() != null)
			channelResponse.setDataChannelId(model.getDataChannel().getId());
			channelResponse.setDataChannelText(model.getDataChannel().getName());
		if(model.getPipeline() != null)
			channelResponse.setChannelPipelineId(model.getPipeline().getId());
			channelResponse.setChannelPipelineText(model.getPipeline().getName());
		return channelResponse;
	}

	/**
	 * @param customerId
	 * @param accountId
	 * @param chequeNo
	 * @param currencyId
	 * @param channelTypeId
	 * @param amount
	 */
	private void initializeQueryParameters(String name, String description, Integer channelTypeId, Integer channelStatusId, Integer dataChannelId, Integer channelPipelineId, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) {
		queryParameters.clear();
        if(name != null)
            queryParameters.add("name", name.toString());
        if(description != null)
            queryParameters.add("description", description.toString());
        if(channelTypeId != null)
            queryParameters.add("channelType", channelTypeId.toString());
        if(channelStatusId != null)
            queryParameters.add("channelStatus", channelStatusId.toString());
        if(dataChannelId != null)
            queryParameters.add("dataChannel", dataChannelId.toString());
        if(channelPipelineId != null)
            queryParameters.add("channelPipeline", channelPipelineId.toString());
        if(id != null)
            queryParameters.add("id", id.toString());
        if(code != null)
            queryParameters.add("code", code.toString());
        if(effectiveDt != null)
            queryParameters.add("effectiveDt", effectiveDt.toString());
        if(recSt != null)
            queryParameters.add("recSt", recSt.toString());
        if(versionNo != null)
            queryParameters.add("versionNo", versionNo.toString());
        if(rowTs != null)
            queryParameters.add("rowTs", rowTs.toString());
        if(createdDt != null)
            queryParameters.add("createdDt", createdDt.toString());
        if(createdByUsr != null)
            queryParameters.add("createdByUsr", createdByUsr.toString());
        if(lastModifiedDt != null)
            queryParameters.add("lastModifiedDt", lastModifiedDt.toString());
        if(lastModifiedUsr != null)
            queryParameters.add("lastModifiedUsr", lastModifiedUsr.toString());
		
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl#extractPredicates(javax.ws.rs.core.MultivaluedMap, javax.persistence.criteria.CriteriaBuilder, javax.persistence.criteria.Root)
	 */
	@Override
	protected Predicate[] extractPredicates(
			MultivaluedMap<String, String> queryParameters,	CriteriaBuilder criteriaBuilder, Root<Channel> root) 
	{
		
		List<Predicate> predicates = new ArrayList<Predicate>() ;
		if (queryParameters.containsKey("name")) {
            String name = queryParameters.getFirst("name");
            predicates.add(criteriaBuilder.equal(root.get("name"), name));
        }
		if (queryParameters.containsKey("description")) {
            String description = queryParameters.getFirst("description");
            predicates.add(criteriaBuilder.equal(root.get("description"), description));
        }
        if (queryParameters.containsKey("channelType")) {
            Integer channelTypeId = Integer.valueOf(queryParameters.getFirst("channelType"));
            predicates.add(criteriaBuilder.equal(root.get("channelType").get("id"), channelTypeId));
        }
        if (queryParameters.containsKey("channelStatus")) {
            Integer channelStatusId = Integer.valueOf(queryParameters.getFirst("channelStatus"));
            predicates.add(criteriaBuilder.equal(root.get("channelStatus").get("id"), channelStatusId));
        }
        if (queryParameters.containsKey("dataChannel")) {
            Integer dataChannelId = Integer.valueOf(queryParameters.getFirst("dataChannel"));
            predicates.add(criteriaBuilder.equal(root.get("dataChannel").get("id"), dataChannelId));
        }
        if (queryParameters.containsKey("channelPipeline")) {
            Integer channelPipelineId = Integer.valueOf(queryParameters.getFirst("channelPipeline"));
            predicates.add(criteriaBuilder.equal(root.get("channelPipeline").get("id"), channelPipelineId));
        }
		if (queryParameters.containsKey("id")) {
            Integer id = Integer.valueOf(queryParameters.getFirst("id"));
            predicates.add(criteriaBuilder.equal(root.get("id"), id));
        }
		if (queryParameters.containsKey("code")) {
            String code = queryParameters.getFirst("code");
            predicates.add(criteriaBuilder.equal(root.get("code"), code));
        }
		if (queryParameters.containsKey("effectiveDt")) {
			DateTime effectiveDt = new DateTime(queryParameters.getFirst("effectiveDt"));
            predicates.add(criteriaBuilder.equal(root.get("effectiveDt"), effectiveDt.toDate()));
        }
		if (queryParameters.containsKey("recSt")) {
            String recSt = queryParameters.getFirst("recSt");
            predicates.add(criteriaBuilder.equal(root.get("recSt"), recSt));
        }
		if (queryParameters.containsKey("versionNo")) {
            Integer versionNo = Integer.valueOf(queryParameters.getFirst("versionNo"));
            predicates.add(criteriaBuilder.equal(root.get("versionNo"), versionNo));
        }
		if (queryParameters.containsKey("rowTs")) {
			DateTime rowTs = new DateTime(queryParameters.getFirst("rowTs"));
            predicates.add(criteriaBuilder.equal(root.get("rowTs"), rowTs.toDate()));
        }
		if (queryParameters.containsKey("createdDt")) {
			DateTime createdDt = new DateTime(queryParameters.getFirst("createdDt"));
            predicates.add(criteriaBuilder.equal(root.get("createdDt"), createdDt.toDate()));
        }
		if (queryParameters.containsKey("createdByUsr")) {
            String createdByUsr = queryParameters.getFirst("createdByUsr");
            predicates.add(criteriaBuilder.equal(root.get("createdByUsr"), createdByUsr));
        }
		if (queryParameters.containsKey("lastModifiedDt")) {
			DateTime lastModifiedDt = new DateTime(queryParameters.getFirst("lastModifiedDt"));
            predicates.add(criteriaBuilder.equal(root.get("lastModifiedDt"), lastModifiedDt.toDate()));
        }
		if (queryParameters.containsKey("lastModifiedUsr")) {
            String lastModifiedUsr = queryParameters.getFirst("lastModifiedUsr");
            predicates.add(criteriaBuilder.equal(root.get("lastModifiedUsr"), lastModifiedUsr));
        }
        return predicates.toArray(new Predicate[]{});
	}
}
