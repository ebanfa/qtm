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

import com.nathanclaire.alantra.advice.model.AdviceChannelCategory;
import com.nathanclaire.alantra.advice.request.AdviceChannelCategoryRequest;
import com.nathanclaire.alantra.advice.response.AdviceChannelCategoryResponse;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtils;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class AdviceChannelCategoryServiceImpl 
	extends BaseEntityServiceImpl<AdviceChannelCategory, AdviceChannelCategoryResponse, AdviceChannelCategoryRequest> 
	implements AdviceChannelCategoryService
{
	private static final String ENTITY_NAME = "AdviceChannelCategory";
	private static final String LIST_ACTIVITY_CODE = "LIST_ADVICE_ADVICECHANNELCATEGORY";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_ADVICE_ADVICECHANNELCATEGORY";
	
	private Logger logger = LoggerFactory.getLogger(AdviceChannelCategoryServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	
	/**
	 * @param entityClass
	 */
	public AdviceChannelCategoryServiceImpl() {
		super(AdviceChannelCategory.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.AdviceChannelCategory#findById(java.lang.Integer)
	 */
	@Override
	public AdviceChannelCategory findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.AdviceChannelCategory#findByCode(java.lang.String)
	 */
	@Override
	public AdviceChannelCategory findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.AdviceChannelCategory#findByName(java.lang.String)
	 */
	@Override
	public AdviceChannelCategory findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.AdviceChannelCategory#findAll(java.util.Map)
	 */
	@Override
	public List<AdviceChannelCategory> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.AdviceChannelCategory#createAdviceChannelCategory(com.nathanclaire.alantra.advice.rest.request.ServiceRequest)
	 */
	@Override
	public AdviceChannelCategory create(AdviceChannelCategoryRequest adviceChannelCategoryRequest) throws ApplicationException {
		return createInstance(adviceChannelCategoryRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.AdviceChannelCategory#deleteAdviceChannelCategory(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.AdviceChannelCategory#updateAdviceChannelCategory(com.nathanclaire.alantra.advice.rest.request.ServiceRequest)
	 */
	@Override
	public AdviceChannelCategory update(AdviceChannelCategoryRequest adviceChannelCategoryRequest) throws ApplicationException {
		return updateInstance(adviceChannelCategoryRequest);
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
    	
		return listItems;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#asListItem()
	 */
	@Override
	public List<ListItemResponse> asListItem() throws ApplicationException {
		List<ListItemResponse> listItems = new ArrayList<ListItemResponse>();
		queryParameters.clear();
		for(AdviceChannelCategory advicechannelcategory: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(advicechannelcategory.getId(), advicechannelcategory.getCode(), advicechannelcategory.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public AdviceChannelCategory convertRequestToModel(AdviceChannelCategoryRequest adviceChannelCategoryRequest) 
     throws ApplicationException {
		AdviceChannelCategory adviceChannelCategory = new AdviceChannelCategory();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(adviceChannelCategoryRequest, adviceChannelCategory, allowedEntityFields);
    	//Process many to one relationships
		return adviceChannelCategory;
	}
	
	@Override
	public AdviceChannelCategoryResponse convertModelToResponse(AdviceChannelCategory model) throws ApplicationException {
		if (model == null) return null;
		AdviceChannelCategoryResponse adviceChannelCategoryResponse = new AdviceChannelCategoryResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(model, adviceChannelCategoryResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		return adviceChannelCategoryResponse;
	}
}
