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

import com.nathanclaire.alantra.advice.model.AdviceChannelType;
import com.nathanclaire.alantra.advice.model.AdviceChannelCategory;
import com.nathanclaire.alantra.advice.request.AdviceChannelTypeRequest;
import com.nathanclaire.alantra.advice.response.AdviceChannelTypeResponse;
import com.nathanclaire.alantra.advice.service.entity.AdviceChannelCategoryService;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtils;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class AdviceChannelTypeServiceImpl 
	extends BaseEntityServiceImpl<AdviceChannelType, AdviceChannelTypeResponse, AdviceChannelTypeRequest> 
	implements AdviceChannelTypeService
{
	private static final String LIST_ITEM_ADVICECHANNELCATEGORY = "adviceChannelCategory";
	private static final String ENTITY_NAME = "AdviceChannelType";
	private static final String LIST_ACTIVITY_CODE = "LIST_ADVICE_ADVICECHANNELTYPE";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_ADVICE_ADVICECHANNELTYPE";
	
	private Logger logger = LoggerFactory.getLogger(AdviceChannelTypeServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	@Inject
	AdviceChannelCategoryService  adviceChannelCategoryService;
	
	/**
	 * @param entityClass
	 */
	public AdviceChannelTypeServiceImpl() {
		super(AdviceChannelType.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.AdviceChannelType#findById(java.lang.Integer)
	 */
	@Override
	public AdviceChannelType findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.AdviceChannelType#findByCode(java.lang.String)
	 */
	@Override
	public AdviceChannelType findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.AdviceChannelType#findByName(java.lang.String)
	 */
	@Override
	public AdviceChannelType findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.AdviceChannelType#findAll(java.util.Map)
	 */
	@Override
	public List<AdviceChannelType> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.AdviceChannelType#createAdviceChannelType(com.nathanclaire.alantra.advice.rest.request.ServiceRequest)
	 */
	@Override
	public AdviceChannelType create(AdviceChannelTypeRequest adviceChannelTypeRequest) throws ApplicationException {
		return createInstance(adviceChannelTypeRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.AdviceChannelType#deleteAdviceChannelType(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.AdviceChannelType#updateAdviceChannelType(com.nathanclaire.alantra.advice.rest.request.ServiceRequest)
	 */
	@Override
	public AdviceChannelType update(AdviceChannelTypeRequest adviceChannelTypeRequest) throws ApplicationException {
		return updateInstance(adviceChannelTypeRequest);
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
		List<ListItemResponse> adviceChannelCategorys = adviceChannelCategoryService.asListItem();
    	
		listItems.put(LIST_ITEM_ADVICECHANNELCATEGORY, adviceChannelCategorys); 
		return listItems;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#asListItem()
	 */
	@Override
	public List<ListItemResponse> asListItem() throws ApplicationException {
		List<ListItemResponse> listItems = new ArrayList<ListItemResponse>();
		queryParameters.clear();
		for(AdviceChannelType advicechanneltype: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(advicechanneltype.getId(), advicechanneltype.getCode(), advicechanneltype.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public AdviceChannelType convertRequestToModel(AdviceChannelTypeRequest adviceChannelTypeRequest) 
     throws ApplicationException {
		AdviceChannelType adviceChannelType = new AdviceChannelType();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(adviceChannelTypeRequest, adviceChannelType, allowedEntityFields);
    	//Process many to one relationships
    	if (adviceChannelTypeRequest.getAdviceChannelCategoryId() != null)
    	{
    		AdviceChannelCategory adviceChannelCategory = getEntityManager().find(AdviceChannelCategory.class, adviceChannelTypeRequest.getAdviceChannelCategoryId());
    		adviceChannelType.setAdviceChannelCategory(adviceChannelCategory);
    	}
		return adviceChannelType;
	}
	
	@Override
	public AdviceChannelTypeResponse convertModelToResponse(AdviceChannelType model) throws ApplicationException {
		if (model == null) return null;
		AdviceChannelTypeResponse adviceChannelTypeResponse = new AdviceChannelTypeResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(model, adviceChannelTypeResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		if(model.getAdviceChannelCategory() != null)
			adviceChannelTypeResponse.setAdviceChannelCategoryId(model.getAdviceChannelCategory().getId());
			adviceChannelTypeResponse.setAdviceChannelCategoryText(model.getAdviceChannelCategory().getName());
		return adviceChannelTypeResponse;
	}
}
