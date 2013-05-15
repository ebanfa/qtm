/**
 * 
 */
package com.nathanclaire.alantra.application.service.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.application.model.ApplicationEntityField;
import com.nathanclaire.alantra.application.model.ApplicationActivityGroupType;
import com.nathanclaire.alantra.application.request.ApplicationActivityGroupTypeRequest;
import com.nathanclaire.alantra.application.response.ApplicationActivityGroupTypeResponse;

import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.application.response.ApplicationEntityFieldResponse;


import com.nathanclaire.alantra.base.util.PropertyUtils;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class ApplicationActivityGroupTypeServiceImpl 
	extends BaseEntityServiceImpl<ApplicationActivityGroupType, ApplicationActivityGroupTypeResponse, ApplicationActivityGroupTypeRequest> 
	implements ApplicationActivityGroupTypeService
{
	
	private static final String ENTITY_NAME = "ApplicationActivityGroupType";
	private static final String LIST_ACTIVITY_CODE = "LIST_APPLICATIONACTIVITYGROUPTYPE";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_APPLICATIONACTIVITYGROUPTYPE";
	
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	
	/**
	 * @param entityClass
	 */
	public ApplicationActivityGroupTypeServiceImpl() {
		super(ApplicationActivityGroupType.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.ApplicationActivityGroupType#findById(java.lang.Integer)
	 */
	@Override
	public ApplicationActivityGroupType findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.ApplicationActivityGroupType#findByCode(java.lang.String)
	 */
	@Override
	public ApplicationActivityGroupType findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.ApplicationActivityGroupType#findByName(java.lang.String)
	 */
	@Override
	public ApplicationActivityGroupType findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.ApplicationActivityGroupType#findAll(java.util.Map)
	 */
	@Override
	public List<ApplicationActivityGroupType> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.ApplicationActivityGroupType#createApplicationActivityGroupType(com.nathanclaire.alantra.application.rest.request.ServiceRequest)
	 */
	@Override
	public ApplicationActivityGroupType create(ApplicationActivityGroupTypeRequest applicationActivityGroupTypeRequest) {
		return createInstance(applicationActivityGroupTypeRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.ApplicationActivityGroupType#deleteApplicationActivityGroupType(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.ApplicationActivityGroupType#updateApplicationActivityGroupType(com.nathanclaire.alantra.application.rest.request.ServiceRequest)
	 */
	@Override
	public ApplicationActivityGroupType update(ApplicationActivityGroupTypeRequest applicationActivityGroupTypeRequest) {
		return updateInstance(applicationActivityGroupTypeRequest);
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#getListActivityCode()
	 */
	@Override
	public String getListActivityCode() {
		return LIST_ACTIVITY_CODE;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#getEditActivityCode()
	 */
	@Override
	public String getEditActivityCode() {
		return EDIT_ACTIVITY_CODE;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#getEntityName()
	 */
	@Override
	public String getEntityName() {
		return ENTITY_NAME;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#getEntityFields()
	 */
	@Override
	public List<ApplicationEntityField> getEntityFields() {
		return applicationEntityService.getFieldsForEntity(ENTITY_NAME);
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#relatedEntitesToListItems()
	 */
	@Override
	public Map<String, List<ListItemResponse>> relatedEntitesToListItems() 
	{
		Map<String, List<ListItemResponse>> listItems = new HashMap<String, List<ListItemResponse>>(); 
    	
		return listItems;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#asListItem()
	 */
	@Override
	public List<ListItemResponse> asListItem() {
		List<ListItemResponse> listItems = new ArrayList<ListItemResponse>();
		for(ApplicationActivityGroupType applicationactivitygrouptype: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(applicationactivitygrouptype.getId(), applicationactivitygrouptype.getCode(), applicationactivitygrouptype.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public ApplicationActivityGroupType convertRequestToModel(ApplicationActivityGroupTypeRequest applicationActivityGroupTypeRequest) 
    {
		ApplicationActivityGroupType applicationActivityGroupType = new ApplicationActivityGroupType();
		applicationActivityGroupType = this.loadDefaultFieldsFromRequest(applicationActivityGroupType, applicationActivityGroupTypeRequest);
    	//Process many to one relationships
    	applicationActivityGroupType.setName(applicationActivityGroupTypeRequest.getName()); 
    	applicationActivityGroupType.setDescription(applicationActivityGroupTypeRequest.getDescription()); 
    	applicationActivityGroupType.setCode(applicationActivityGroupTypeRequest.getCode()); 
    	applicationActivityGroupType.setRecSt(applicationActivityGroupTypeRequest.getRecSt()); 
		return applicationActivityGroupType;
	}
	
	@Override
	public ApplicationActivityGroupTypeResponse convertModelToResponse(ApplicationActivityGroupType model) {
		if (model == null) return null;
		ApplicationActivityGroupTypeResponse applicationActivityGroupTypeResponse = new ApplicationActivityGroupTypeResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(model, applicationActivityGroupTypeResponse, allowedEntityFields);
		return applicationActivityGroupTypeResponse;
	}
}
