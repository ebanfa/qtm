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
import com.nathanclaire.alantra.application.model.ApplicationFormFieldType;
import com.nathanclaire.alantra.application.request.ApplicationFormFieldTypeRequest;
import com.nathanclaire.alantra.application.response.ApplicationFormFieldTypeResponse;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtil;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class ApplicationFormFieldTypeServiceImpl 
	extends BaseEntityServiceImpl<ApplicationFormFieldType, ApplicationFormFieldTypeResponse, ApplicationFormFieldTypeRequest> 
	implements ApplicationFormFieldTypeService
{
	
	private static final String ENTITY_NAME = "ApplicationFormFieldType";
	private static final String LIST_ACTIVITY_CODE = "LIST_APPLICATIONFORMFIELDTYPE";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_APPLICATIONFORMFIELDTYPE";
	
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	
	/**
	 * @param entityClass
	 */
	public ApplicationFormFieldTypeServiceImpl() {
		super(ApplicationFormFieldType.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.ApplicationFormFieldType#findById(java.lang.Integer)
	 */
	@Override
	public ApplicationFormFieldType findById(Integer id)  throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.ApplicationFormFieldType#findByCode(java.lang.String)
	 */
	@Override
	public ApplicationFormFieldType findByCode(String code)  throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.ApplicationFormFieldType#findByName(java.lang.String)
	 */
	@Override
	public ApplicationFormFieldType findByName(String name)  throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.ApplicationFormFieldType#findAll(java.util.Map)
	 */
	@Override
	public List<ApplicationFormFieldType> findAll(MultivaluedMap<String, String> queryParameters)  throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.ApplicationFormFieldType#createApplicationFormFieldType(com.nathanclaire.alantra.application.rest.request.ServiceRequest)
	 */
	@Override
	public ApplicationFormFieldType create(ApplicationFormFieldTypeRequest applicationFormFieldTypeRequest)  throws ApplicationException {
		return createInstance(applicationFormFieldTypeRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.ApplicationFormFieldType#deleteApplicationFormFieldType(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id)  throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.ApplicationFormFieldType#updateApplicationFormFieldType(com.nathanclaire.alantra.application.rest.request.ServiceRequest)
	 */
	@Override
	public ApplicationFormFieldType update(ApplicationFormFieldTypeRequest applicationFormFieldTypeRequest)  throws ApplicationException {
		return updateInstance(applicationFormFieldTypeRequest);
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
		for(ApplicationFormFieldType applicationformfieldtype: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(applicationformfieldtype.getId(), applicationformfieldtype.getCode(), applicationformfieldtype.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public ApplicationFormFieldType convertRequestToModel(ApplicationFormFieldTypeRequest applicationFormFieldTypeRequest) 
    {
		ApplicationFormFieldType applicationFormFieldType = new ApplicationFormFieldType();
		applicationFormFieldType = this.loadDefaultFieldsFromRequest(applicationFormFieldType, applicationFormFieldTypeRequest);
    	//Process many to one relationships
    	applicationFormFieldType.setName(applicationFormFieldTypeRequest.getName()); 
    	applicationFormFieldType.setDescription(applicationFormFieldTypeRequest.getDescription()); 
    	applicationFormFieldType.setCode(applicationFormFieldTypeRequest.getCode()); 
    	applicationFormFieldType.setRecSt(applicationFormFieldTypeRequest.getRecSt()); 
		return applicationFormFieldType;
	}
	
	@Override
	public ApplicationFormFieldTypeResponse convertModelToResponse(ApplicationFormFieldType model)  throws ApplicationException {
		if (model == null) return null;
		ApplicationFormFieldTypeResponse applicationFormFieldTypeResponse = new ApplicationFormFieldTypeResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtil.copyProperties(model, applicationFormFieldTypeResponse, allowedEntityFields);
		return applicationFormFieldTypeResponse;
	}
}
