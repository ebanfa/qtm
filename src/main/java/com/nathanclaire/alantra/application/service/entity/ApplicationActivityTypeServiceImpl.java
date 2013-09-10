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

import com.nathanclaire.alantra.application.model.ApplicationActivityType;
import com.nathanclaire.alantra.application.model.ApplicationEntityField;
import com.nathanclaire.alantra.application.request.ApplicationActivityTypeRequest;
import com.nathanclaire.alantra.application.response.ApplicationActivityTypeResponse;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtil;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class ApplicationActivityTypeServiceImpl 
	extends BaseEntityServiceImpl<ApplicationActivityType, ApplicationActivityTypeResponse, ApplicationActivityTypeRequest> 
	implements ApplicationActivityTypeService
{
	
	private static final String ENTITY_NAME = "ApplicationActivityType";
	private static final String LIST_ACTIVITY_CODE = "LIST_APPLICATIONACTIVITYTYPE";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_APPLICATIONACTIVITYTYPE";
	
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	
	/**
	 * @param entityClass
	 */
	public ApplicationActivityTypeServiceImpl() {
		super(ApplicationActivityType.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.ApplicationActivityType#findById(java.lang.Integer)
	 */
	@Override
	public ApplicationActivityType findById(Integer id)  throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.ApplicationActivityType#findByCode(java.lang.String)
	 */
	@Override
	public ApplicationActivityType findByCode(String code)  throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.ApplicationActivityType#findByName(java.lang.String)
	 */
	@Override
	public ApplicationActivityType findByName(String name)  throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.ApplicationActivityType#findAll(java.util.Map)
	 */
	@Override
	public List<ApplicationActivityType> findAll(MultivaluedMap<String, String> queryParameters)  throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.ApplicationActivityType#createApplicationActivityType(com.nathanclaire.alantra.application.rest.request.ServiceRequest)
	 */
	@Override
	public ApplicationActivityType create(ApplicationActivityTypeRequest applicationActivityTypeRequest)  throws ApplicationException {
		return createInstance(applicationActivityTypeRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.ApplicationActivityType#deleteApplicationActivityType(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id)  throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.ApplicationActivityType#updateApplicationActivityType(com.nathanclaire.alantra.application.rest.request.ServiceRequest)
	 */
	@Override
	public ApplicationActivityType update(ApplicationActivityTypeRequest applicationActivityTypeRequest)  throws ApplicationException {
		return updateInstance(applicationActivityTypeRequest);
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
		for(ApplicationActivityType applicationactivitytype: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(applicationactivitytype.getId(), applicationactivitytype.getCode(), applicationactivitytype.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public ApplicationActivityType convertRequestToModel(ApplicationActivityTypeRequest applicationActivityTypeRequest) 
    {
		ApplicationActivityType applicationActivityType = new ApplicationActivityType();
		applicationActivityType = this.loadDefaultFieldsFromRequest(applicationActivityType, applicationActivityTypeRequest);
    	//Process many to one relationships
    	applicationActivityType.setName(applicationActivityTypeRequest.getName()); 
    	applicationActivityType.setDescription(applicationActivityTypeRequest.getDescription()); 
    	applicationActivityType.setCode(applicationActivityTypeRequest.getCode()); 
    	applicationActivityType.setRecSt(applicationActivityTypeRequest.getRecSt()); 
		return applicationActivityType;
	}
	
	@Override
	public ApplicationActivityTypeResponse convertModelToResponse(ApplicationActivityType model)  throws ApplicationException {
		if (model == null) return null;
		ApplicationActivityTypeResponse applicationActivityTypeResponse = new ApplicationActivityTypeResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtil.copyProperties(model, applicationActivityTypeResponse, allowedEntityFields);
		return applicationActivityTypeResponse;
	}
}
