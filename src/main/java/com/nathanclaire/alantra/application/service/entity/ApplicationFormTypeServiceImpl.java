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

import com.nathanclaire.alantra.application.model.ApplicationEntityField;
import com.nathanclaire.alantra.application.model.ApplicationFormType;
import com.nathanclaire.alantra.application.request.ApplicationFormTypeRequest;
import com.nathanclaire.alantra.application.response.ApplicationFormTypeResponse;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtils;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class ApplicationFormTypeServiceImpl 
	extends BaseEntityServiceImpl<ApplicationFormType, ApplicationFormTypeResponse, ApplicationFormTypeRequest> 
	implements ApplicationFormTypeService
{
	
	private static final String ENTITY_NAME = "ApplicationFormType";
	private static final String LIST_ACTIVITY_CODE = "LIST_APPLICATIONFORMTYPE";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_APPLICATIONFORMTYPE";
	
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	
	/**
	 * @param entityClass
	 */
	public ApplicationFormTypeServiceImpl(){
		super(ApplicationFormType.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.ApplicationFormType#findById(java.lang.Integer)
	 */
	@Override
	public ApplicationFormType findById(Integer id)  throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.ApplicationFormType#findByCode(java.lang.String)
	 */
	@Override
	public ApplicationFormType findByCode(String code)  throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.ApplicationFormType#findByName(java.lang.String)
	 */
	@Override
	public ApplicationFormType findByName(String name)  throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.ApplicationFormType#findAll(java.util.Map)
	 */
	@Override
	public List<ApplicationFormType> findAll(MultivaluedMap<String, String> queryParameters)  throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.ApplicationFormType#createApplicationFormType(com.nathanclaire.alantra.application.rest.request.ServiceRequest)
	 */
	@Override
	public ApplicationFormType create(ApplicationFormTypeRequest applicationFormTypeRequest)  throws ApplicationException {
		return createInstance(applicationFormTypeRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.ApplicationFormType#deleteApplicationFormType(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id)  throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.ApplicationFormType#updateApplicationFormType(com.nathanclaire.alantra.application.rest.request.ServiceRequest)
	 */
	@Override
	public ApplicationFormType update(ApplicationFormTypeRequest applicationFormTypeRequest)  throws ApplicationException {
		return updateInstance(applicationFormTypeRequest);
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#getListActivityCode()
	 */
	@Override
	public String getListActivityCode()  throws ApplicationException {
		return LIST_ACTIVITY_CODE;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#getEditActivityCode()
	 */
	@Override
	public String getEditActivityCode()  throws ApplicationException {
		return EDIT_ACTIVITY_CODE;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#getEntityName()
	 */
	@Override
	public String getEntityName()  throws ApplicationException {
		return ENTITY_NAME;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#getEntityFields()
	 */
	@Override
	public List<ApplicationEntityField> getEntityFields()  throws ApplicationException {
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
	public List<ListItemResponse> asListItem()  throws ApplicationException {
		List<ListItemResponse> listItems = new ArrayList<ListItemResponse>();
		for(ApplicationFormType applicationformtype: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(applicationformtype.getId(), applicationformtype.getCode(), applicationformtype.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public ApplicationFormType convertRequestToModel(ApplicationFormTypeRequest applicationFormTypeRequest) 
    {
		ApplicationFormType applicationFormType = new ApplicationFormType();
		applicationFormType = this.loadDefaultFieldsFromRequest(applicationFormType, applicationFormTypeRequest);
    	//Process many to one relationships
    	applicationFormType.setName(applicationFormTypeRequest.getName()); 
    	applicationFormType.setDescription(applicationFormTypeRequest.getDescription()); 
    	applicationFormType.setCode(applicationFormTypeRequest.getCode()); 
    	applicationFormType.setRecSt(applicationFormTypeRequest.getRecSt()); 
		return applicationFormType;
	}
	
	@Override
	public ApplicationFormTypeResponse convertModelToResponse(ApplicationFormType model)  throws ApplicationException {
		if (model == null) return null;
		ApplicationFormTypeResponse applicationFormTypeResponse = new ApplicationFormTypeResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(model, applicationFormTypeResponse, allowedEntityFields);
		return applicationFormTypeResponse;
	}
}
