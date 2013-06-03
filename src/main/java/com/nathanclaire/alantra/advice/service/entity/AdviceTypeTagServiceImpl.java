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

import com.nathanclaire.alantra.advice.model.AdviceTypeTag;
import com.nathanclaire.alantra.advice.model.AdviceType;
import com.nathanclaire.alantra.advice.request.AdviceTypeTagRequest;
import com.nathanclaire.alantra.advice.response.AdviceTypeTagResponse;
import com.nathanclaire.alantra.advice.service.entity.AdviceTypeService;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtils;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class AdviceTypeTagServiceImpl 
	extends BaseEntityServiceImpl<AdviceTypeTag, AdviceTypeTagResponse, AdviceTypeTagRequest> 
	implements AdviceTypeTagService
{
	private static final String LIST_ITEM_ADVICETYPE = "adviceType";
	private static final String ENTITY_NAME = "AdviceTypeTag";
	private static final String LIST_ACTIVITY_CODE = "LIST_ADVICE_ADVICETYPETAG";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_ADVICE_ADVICETYPETAG";
	
	private Logger logger = LoggerFactory.getLogger(AdviceTypeTagServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	@Inject
	AdviceTypeService  adviceTypeService;
	
	/**
	 * @param entityClass
	 */
	public AdviceTypeTagServiceImpl() {
		super(AdviceTypeTag.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.AdviceTypeTag#findById(java.lang.Integer)
	 */
	@Override
	public AdviceTypeTag findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.AdviceTypeTag#findByCode(java.lang.String)
	 */
	@Override
	public AdviceTypeTag findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.AdviceTypeTag#findByName(java.lang.String)
	 */
	@Override
	public AdviceTypeTag findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.AdviceTypeTag#findAll(java.util.Map)
	 */
	@Override
	public List<AdviceTypeTag> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.AdviceTypeTag#createAdviceTypeTag(com.nathanclaire.alantra.advice.rest.request.ServiceRequest)
	 */
	@Override
	public AdviceTypeTag create(AdviceTypeTagRequest adviceTypeTagRequest) throws ApplicationException {
		return createInstance(adviceTypeTagRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.AdviceTypeTag#deleteAdviceTypeTag(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.AdviceTypeTag#updateAdviceTypeTag(com.nathanclaire.alantra.advice.rest.request.ServiceRequest)
	 */
	@Override
	public AdviceTypeTag update(AdviceTypeTagRequest adviceTypeTagRequest) throws ApplicationException {
		return updateInstance(adviceTypeTagRequest);
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
		List<ListItemResponse> adviceTypes = adviceTypeService.asListItem();
    	
		listItems.put(LIST_ITEM_ADVICETYPE, adviceTypes); 
		return listItems;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#asListItem()
	 */
	@Override
	public List<ListItemResponse> asListItem() throws ApplicationException {
		List<ListItemResponse> listItems = new ArrayList<ListItemResponse>();
		queryParameters.clear();
		for(AdviceTypeTag advicetypetag: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(advicetypetag.getId(), advicetypetag.getCode(), advicetypetag.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public AdviceTypeTag convertRequestToModel(AdviceTypeTagRequest adviceTypeTagRequest) 
     throws ApplicationException {
		AdviceTypeTag adviceTypeTag = new AdviceTypeTag();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(adviceTypeTagRequest, adviceTypeTag, allowedEntityFields);
    	//Process many to one relationships
    	if (adviceTypeTagRequest.getAdviceTypeId() != null)
    	{
    		AdviceType adviceType = getEntityManager().find(AdviceType.class, adviceTypeTagRequest.getAdviceTypeId());
    		adviceTypeTag.setAdviceType(adviceType);
    	}
		return adviceTypeTag;
	}
	
	@Override
	public AdviceTypeTagResponse convertModelToResponse(AdviceTypeTag model) throws ApplicationException {
		if (model == null) return null;
		AdviceTypeTagResponse adviceTypeTagResponse = new AdviceTypeTagResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(model, adviceTypeTagResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		if(model.getAdviceType() != null)
			adviceTypeTagResponse.setAdviceTypeId(model.getAdviceType().getId());
			adviceTypeTagResponse.setAdviceTypeText(model.getAdviceType().getName());
		return adviceTypeTagResponse;
	}
}
