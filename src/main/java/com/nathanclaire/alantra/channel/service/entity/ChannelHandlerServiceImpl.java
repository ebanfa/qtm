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
import com.nathanclaire.alantra.channel.model.ChannelHandler;
import com.nathanclaire.alantra.channel.model.ChannelHandlerType;
import com.nathanclaire.alantra.channel.request.ChannelHandlerRequest;
import com.nathanclaire.alantra.channel.response.ChannelHandlerResponse;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class ChannelHandlerServiceImpl 
	extends BaseEntityServiceImpl<ChannelHandler, ChannelHandlerResponse, ChannelHandlerRequest> 
	implements ChannelHandlerService
{

	private static final String LIST_ITEM_CHANNELHANDLER = "parentChannelHandler";
	private static final String LIST_ITEM_CHANNELHANDLERTYPE = "channelHandlerType";
	private static final String ENTITY_NAME = "ChannelHandler";
	private static final String LIST_ACTIVITY_CODE = "LIST_CHANNEL_CHANNELHANDLER";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_CHANNEL_CHANNELHANDLER";
	
	private Logger logger = LoggerFactory.getLogger(ChannelHandlerServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	@Inject ChannelHandlerTypeService  channelHandlerTypeService;
	
	/**
	 * @param entityClass
	 */
	public ChannelHandlerServiceImpl() {
		super(ChannelHandler.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channelHandler.service.ChannelHandler#findById(java.lang.Integer)
	 */
	@Override
	public ChannelHandler findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channelHandler.service.ChannelHandler#findByCode(java.lang.String)
	 */
	@Override
	public ChannelHandler findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ChannelHandler#findByName(java.lang.String)
	 */
	@Override
	public ChannelHandler findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ChannelHandler#findAll(java.util.Map)
	 */
	@Override
	public List<ChannelHandler> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ChannelHandler#createChannelHandler(com.nathanclaire.alantra.channel.rest.request.ServiceRequest)
	 */
	@Override
	public ChannelHandler create(ChannelHandlerRequest channelHandlerRequest) throws ApplicationException {
		return createInstance(channelHandlerRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ChannelHandler#deleteChannelHandler(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ChannelHandler#updateChannelHandler(com.nathanclaire.alantra.channelHandler.rest.request.ServiceRequest)
	 */
	@Override
	public ChannelHandler update(ChannelHandlerRequest channelHandlerRequest) throws ApplicationException {
		return updateInstance(channelHandlerRequest);
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
        List<ListItemResponse> parentChannelHandlers = asListItem();
        List<ListItemResponse> channelHandlerTypes = channelHandlerTypeService.asListItem();

        listItems.put(LIST_ITEM_CHANNELHANDLER, parentChannelHandlers);
        listItems.put(LIST_ITEM_CHANNELHANDLERTYPE, channelHandlerTypes);
		return listItems;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#asListItem()
	 */
	@Override
	public List<ListItemResponse> asListItem() throws ApplicationException {
		List<ListItemResponse> listItems = new ArrayList<ListItemResponse>();
		queryParameters.clear();
		for(ChannelHandler channelHandler: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(channelHandler.getId(), channelHandler.getCode(), channelHandler.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public ChannelHandler convertRequestToModel(ChannelHandlerRequest channelHandlerRequest) 
     throws ApplicationException {
		ChannelHandler channelHandler = new ChannelHandler();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtil.copyProperties(channelHandlerRequest, channelHandler, allowedEntityFields);
    	//Process many to one relationships
        if (channelHandlerRequest.getParentChannelHandlerId() != null)
    	{
    		ChannelHandler parentChannelHandler = getEntityManager().find(ChannelHandler.class, channelHandlerRequest.getParentChannelHandlerId());
    		channelHandler.setParentChannelHandler(parentChannelHandler);
    	}
        if (channelHandlerRequest.getChannelHandlerTypeId() != null)
    	{
    		ChannelHandlerType channelHandlerType = getEntityManager().find(ChannelHandlerType.class, channelHandlerRequest.getChannelHandlerTypeId());
    		channelHandler.setChannelHandlerType(channelHandlerType);
    	}
		return channelHandler;
	}
	
	@Override
	public ChannelHandlerResponse convertModelToResponse(ChannelHandler model) throws ApplicationException {
		if (model == null) return null;
		ChannelHandlerResponse channelHandlerResponse = new ChannelHandlerResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtil.copyProperties(model, channelHandlerResponse, allowedEntityFields);
		if(model.getParentChannelHandler() != null)
			channelHandlerResponse.setParentChannelHandlerId(model.getParentChannelHandler().getId());
			channelHandlerResponse.setParentChannelHandlerText(model.getParentChannelHandler().getName());
		if(model.getChannelHandlerType() != null)
			channelHandlerResponse.setChannelHandlerTypeId(model.getChannelHandlerType().getId());
			channelHandlerResponse.setChannelHandlerTypeText(model.getChannelHandlerType().getName());
		return channelHandlerResponse;
	}

	/**
	 * @param customerId
	 * @param accountId
	 * @param chequeNo
	 * @param currencyId
	 * @param channelHandlerTypeId
	 * @param amount
	 */
	private void initializeQueryParameters(String name, String description, Integer parentChannelHandlerId, Integer channelHandlerTypeId, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) {
		queryParameters.clear();
        if(name != null)
            queryParameters.add("name", name.toString());
        if(description != null)
            queryParameters.add("description", description.toString());
        if(parentChannelHandlerId != null)
            queryParameters.add("parentChannelHandler", parentChannelHandlerId.toString());
        if(channelHandlerTypeId != null)
            queryParameters.add("channelHandlerType", channelHandlerTypeId.toString());
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
			MultivaluedMap<String, String> queryParameters,	CriteriaBuilder criteriaBuilder, Root<ChannelHandler> root) 
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
        if (queryParameters.containsKey("parentChannelHandler")) {
            Integer parentChannelHandlerId = Integer.valueOf(queryParameters.getFirst("parentChannelHandler"));
            predicates.add(criteriaBuilder.equal(root.get("parentChannelHandler").get("id"), parentChannelHandlerId));
        }
        if (queryParameters.containsKey("channelHandlerType")) {
            Integer channelHandlerTypeId = Integer.valueOf(queryParameters.getFirst("channelHandlerType"));
            predicates.add(criteriaBuilder.equal(root.get("channelHandlerType").get("id"), channelHandlerTypeId));
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
