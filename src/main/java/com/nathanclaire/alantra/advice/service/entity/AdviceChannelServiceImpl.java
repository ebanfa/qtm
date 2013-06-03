/**
 * 
 */
package com.nathanclaire.alantra.advice.service.entity;

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

import com.nathanclaire.alantra.advice.model.AdviceChannel;
import com.nathanclaire.alantra.advice.model.AdviceChannelType;
import com.nathanclaire.alantra.advice.request.AdviceChannelRequest;
import com.nathanclaire.alantra.advice.response.AdviceChannelResponse;
import com.nathanclaire.alantra.advice.service.entity.AdviceChannelTypeService;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtils;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class AdviceChannelServiceImpl 
	extends BaseEntityServiceImpl<AdviceChannel, AdviceChannelResponse, AdviceChannelRequest> 
	implements AdviceChannelService
{
	private static final String LIST_ITEM_ADVICECHANNELTYPE = "adviceChannelType";
	private static final String ENTITY_NAME = "AdviceChannel";
	private static final String LIST_ACTIVITY_CODE = "LIST_ADVICE_ADVICECHANNEL";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_ADVICE_ADVICECHANNEL";
	
	private Logger logger = LoggerFactory.getLogger(AdviceChannelServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	@Inject
	AdviceChannelTypeService  adviceChannelTypeService;
	
	/**
	 * @param entityClass
	 */
	public AdviceChannelServiceImpl() {
		super(AdviceChannel.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.AdviceChannel#findById(java.lang.Integer)
	 */
	@Override
	public AdviceChannel findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.AdviceChannel#findByCode(java.lang.String)
	 */
	@Override
	public AdviceChannel findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.AdviceChannel#findByName(java.lang.String)
	 */
	@Override
	public AdviceChannel findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.AdviceChannel#findAll(java.util.Map)
	 */
	@Override
	public List<AdviceChannel> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.AdviceChannel#createAdviceChannel(com.nathanclaire.alantra.advice.rest.request.ServiceRequest)
	 */
	@Override
	public AdviceChannel create(AdviceChannelRequest adviceChannelRequest) throws ApplicationException {
		return createInstance(adviceChannelRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.AdviceChannel#deleteAdviceChannel(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.AdviceChannel#updateAdviceChannel(com.nathanclaire.alantra.advice.rest.request.ServiceRequest)
	 */
	@Override
	public AdviceChannel update(AdviceChannelRequest adviceChannelRequest) throws ApplicationException {
		return updateInstance(adviceChannelRequest);
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
		List<ListItemResponse> adviceChannelTypes = adviceChannelTypeService.asListItem();
    	
		listItems.put(LIST_ITEM_ADVICECHANNELTYPE, adviceChannelTypes); 
		return listItems;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#asListItem()
	 */
	@Override
	public List<ListItemResponse> asListItem() throws ApplicationException {
		List<ListItemResponse> listItems = new ArrayList<ListItemResponse>();
		queryParameters.clear();
		for(AdviceChannel advicechannel: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(advicechannel.getId(), advicechannel.getCode(), advicechannel.getCode());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public AdviceChannel convertRequestToModel(AdviceChannelRequest adviceChannelRequest) 
     throws ApplicationException {
		AdviceChannel adviceChannel = new AdviceChannel();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(adviceChannelRequest, adviceChannel, allowedEntityFields);
    	//Process many to one relationships
    	if (adviceChannelRequest.getAdviceChannelTypeId() != null)
    	{
    		AdviceChannelType adviceChannelType = getEntityManager().find(AdviceChannelType.class, adviceChannelRequest.getAdviceChannelTypeId());
    		adviceChannel.setAdviceChannelType(adviceChannelType);
    	}
		return adviceChannel;
	}
	
	@Override
	public AdviceChannelResponse convertModelToResponse(AdviceChannel model) throws ApplicationException {
		if (model == null) return null;
		AdviceChannelResponse adviceChannelResponse = new AdviceChannelResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(model, adviceChannelResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		if(model.getAdviceChannelType() != null)
			adviceChannelResponse.setAdviceChannelTypeId(model.getAdviceChannelType().getId());
			adviceChannelResponse.setAdviceChannelTypeText(model.getAdviceChannelType().getName());
		return adviceChannelResponse;
	}
}
