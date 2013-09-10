/**
 * 
 */
package com.nathanclaire.alantra.channel.service.entity;

import java.math.BigDecimal;
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

import com.nathanclaire.alantra.channel.model.ChannelPipelineHandler;
import com.nathanclaire.alantra.channel.request.ChannelPipelineHandlerRequest;
import com.nathanclaire.alantra.channel.response.ChannelPipelineHandlerResponse;
import com.nathanclaire.alantra.application.model.ApplicationEntityField;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtil;
import com.nathanclaire.alantra.channel.model.ChannelHandler;
import com.nathanclaire.alantra.channel.service.entity.ChannelHandlerService;
import com.nathanclaire.alantra.channel.model.ChannelPipeline;
import com.nathanclaire.alantra.channel.service.entity.ChannelPipelineService;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class ChannelPipelineHandlerServiceImpl 
	extends BaseEntityServiceImpl<ChannelPipelineHandler, ChannelPipelineHandlerResponse, ChannelPipelineHandlerRequest> 
	implements ChannelPipelineHandlerService
{

private static final String LIST_ITEM_CHANNELHANDLER = "channelHandler";
private static final String LIST_ITEM_CHANNELPIPELINE = "channelPipeline";
	private static final String ENTITY_NAME = "ChannelPipelineHandler";
	private static final String LIST_ACTIVITY_CODE = "LIST_CHANNEL_CHANNELPIPELINEHANDLER";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_CHANNEL_CHANNELPIPELINEHANDLER";
	
	private Logger logger = LoggerFactory.getLogger(ChannelPipelineHandlerServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	@Inject ChannelHandlerService  channelHandlerService;
	@Inject ChannelPipelineService  channelPipelineService;
	
	/**
	 * @param entityClass
	 */
	public ChannelPipelineHandlerServiceImpl() {
		super(ChannelPipelineHandler.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channelPipelineHandler.service.ChannelPipelineHandler#findById(java.lang.Integer)
	 */
	@Override
	public ChannelPipelineHandler findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channelPipelineHandler.service.ChannelPipelineHandler#findByCode(java.lang.String)
	 */
	@Override
	public ChannelPipelineHandler findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ChannelPipelineHandler#findByName(java.lang.String)
	 */
	@Override
	public ChannelPipelineHandler findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ChannelPipelineHandler#findAll(java.util.Map)
	 */
	@Override
	public List<ChannelPipelineHandler> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ChannelPipelineHandler#createChannelPipelineHandler(com.nathanclaire.alantra.channel.rest.request.ServiceRequest)
	 */
	@Override
	public ChannelPipelineHandler create(ChannelPipelineHandlerRequest channelPipelineHandlerRequest) throws ApplicationException {
		return createInstance(channelPipelineHandlerRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ChannelPipelineHandler#deleteChannelPipelineHandler(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ChannelPipelineHandler#updateChannelPipelineHandler(com.nathanclaire.alantra.channelPipelineHandler.rest.request.ServiceRequest)
	 */
	@Override
	public ChannelPipelineHandler update(ChannelPipelineHandlerRequest channelPipelineHandlerRequest) throws ApplicationException {
		return updateInstance(channelPipelineHandlerRequest);
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
        List<ListItemResponse> channelHandlers = channelHandlerService.asListItem();
        List<ListItemResponse> channelPipelines = channelPipelineService.asListItem();

        listItems.put(LIST_ITEM_CHANNELHANDLER, channelHandlers);
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
		for(ChannelPipelineHandler channelPipelineHandler: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(channelPipelineHandler.getId(), channelPipelineHandler.getCode(), channelPipelineHandler.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public ChannelPipelineHandler convertRequestToModel(ChannelPipelineHandlerRequest channelPipelineHandlerRequest) 
     throws ApplicationException {
		ChannelPipelineHandler channelPipelineHandler = new ChannelPipelineHandler();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtil.copyProperties(channelPipelineHandlerRequest, channelPipelineHandler, allowedEntityFields);
    	//Process many to one relationships
        if (channelPipelineHandlerRequest.getChannelHandlerId() != null)
    	{
    		ChannelHandler channelHandler = getEntityManager().find(ChannelHandler.class, channelPipelineHandlerRequest.getChannelHandlerId());
    		channelPipelineHandler.setChannelHandler(channelHandler);
    	}
        if (channelPipelineHandlerRequest.getChannelPipelineId() != null)
    	{
    		ChannelPipeline channelPipeline = getEntityManager().find(ChannelPipeline.class, channelPipelineHandlerRequest.getChannelPipelineId());
    		channelPipelineHandler.setChannelPipeline(channelPipeline);
    	}
		return channelPipelineHandler;
	}
	
	@Override
	public ChannelPipelineHandlerResponse convertModelToResponse(ChannelPipelineHandler model) throws ApplicationException {
		if (model == null) return null;
		ChannelPipelineHandlerResponse channelPipelineHandlerResponse = new ChannelPipelineHandlerResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtil.copyProperties(model, channelPipelineHandlerResponse, allowedEntityFields);
		if(model.getChannelHandler() != null)
			channelPipelineHandlerResponse.setChannelHandlerId(model.getChannelHandler().getId());
			channelPipelineHandlerResponse.setChannelHandlerText(model.getChannelHandler().getName());
		if(model.getChannelPipeline() != null)
			channelPipelineHandlerResponse.setChannelPipelineId(model.getChannelPipeline().getId());
			channelPipelineHandlerResponse.setChannelPipelineText(model.getChannelPipeline().getName());
		return channelPipelineHandlerResponse;
	}

	/**
	 * @param customerId
	 * @param accountId
	 * @param chequeNo
	 * @param currencyId
	 * @param channelPipelineHandlerTypeId
	 * @param amount
	 */
	private void initializeQueryParameters(String name, String description, Integer channelHandlerId, Integer channelPipelineId, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) {
		queryParameters.clear();
        if(name != null)
            queryParameters.add("name", name.toString());
        if(description != null)
            queryParameters.add("description", description.toString());
        if(channelHandlerId != null)
            queryParameters.add("channelHandler", channelHandlerId.toString());
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
			MultivaluedMap<String, String> queryParameters,	CriteriaBuilder criteriaBuilder, Root<ChannelPipelineHandler> root) 
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
        if (queryParameters.containsKey("channelHandler")) {
            Integer channelHandlerId = Integer.valueOf(queryParameters.getFirst("channelHandler"));
            predicates.add(criteriaBuilder.equal(root.get("channelHandler").get("id"), channelHandlerId));
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
