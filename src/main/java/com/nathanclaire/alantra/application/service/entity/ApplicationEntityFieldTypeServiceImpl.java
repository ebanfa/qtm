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
import com.nathanclaire.alantra.application.model.ApplicationEntityFieldType;
import com.nathanclaire.alantra.application.request.ApplicationEntityFieldTypeRequest;
import com.nathanclaire.alantra.application.response.ApplicationEntityFieldTypeResponse;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtils;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class ApplicationEntityFieldTypeServiceImpl 
	extends BaseEntityServiceImpl<ApplicationEntityFieldType, ApplicationEntityFieldTypeResponse, ApplicationEntityFieldTypeRequest> 
	implements ApplicationEntityFieldTypeService
{
	
	private static final String ENTITY_NAME = "ApplicationEntityFieldType";
	private static final String LIST_ACTIVITY_CODE = "LIST_APPLICATIONENTITYFIELDTYPE";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_APPLICATIONENTITYFIELDTYPE";
	
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	
	/**
	 * @param entityClass
	 */
	public ApplicationEntityFieldTypeServiceImpl() {
		super(ApplicationEntityFieldType.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.ApplicationEntityFieldType#findById(java.lang.Integer)
	 */
	@Override
	public ApplicationEntityFieldType findById(Integer id)  throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.ApplicationEntityFieldType#findByCode(java.lang.String)
	 */
	@Override
	public ApplicationEntityFieldType findByCode(String code)  throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.ApplicationEntityFieldType#findByName(java.lang.String)
	 */
	@Override
	public ApplicationEntityFieldType findByName(String name)  throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.ApplicationEntityFieldType#findAll(java.util.Map)
	 */
	@Override
	public List<ApplicationEntityFieldType> findAll(MultivaluedMap<String, String> queryParameters)  throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.ApplicationEntityFieldType#createApplicationEntityFieldType(com.nathanclaire.alantra.application.rest.request.ServiceRequest)
	 */
	@Override
	public ApplicationEntityFieldType create(ApplicationEntityFieldTypeRequest applicationEntityFieldTypeRequest)  throws ApplicationException {
		return createInstance(applicationEntityFieldTypeRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.ApplicationEntityFieldType#deleteApplicationEntityFieldType(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id)  throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.ApplicationEntityFieldType#updateApplicationEntityFieldType(com.nathanclaire.alantra.application.rest.request.ServiceRequest)
	 */
	@Override
	public ApplicationEntityFieldType update(ApplicationEntityFieldTypeRequest applicationEntityFieldTypeRequest)  throws ApplicationException {
		return updateInstance(applicationEntityFieldTypeRequest);
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
		for(ApplicationEntityFieldType applicationentityfieldtype: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(applicationentityfieldtype.getId(), applicationentityfieldtype.getCode(), applicationentityfieldtype.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public ApplicationEntityFieldType convertRequestToModel(ApplicationEntityFieldTypeRequest applicationEntityFieldTypeRequest) 
    {
		ApplicationEntityFieldType applicationEntityFieldType = new ApplicationEntityFieldType();
		applicationEntityFieldType = this.loadDefaultFieldsFromRequest(applicationEntityFieldType, applicationEntityFieldTypeRequest);
    	//Process many to one relationships
    	applicationEntityFieldType.setName(applicationEntityFieldTypeRequest.getName()); 
    	applicationEntityFieldType.setDescription(applicationEntityFieldTypeRequest.getDescription()); 
    	applicationEntityFieldType.setCode(applicationEntityFieldTypeRequest.getCode()); 
    	applicationEntityFieldType.setRecSt(applicationEntityFieldTypeRequest.getRecSt()); 
		return applicationEntityFieldType;
	}
	
	@Override
	public ApplicationEntityFieldTypeResponse convertModelToResponse(ApplicationEntityFieldType model)  throws ApplicationException {
		if (model == null) return null;
		ApplicationEntityFieldTypeResponse applicationEntityFieldTypeResponse = new ApplicationEntityFieldTypeResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(model, applicationEntityFieldTypeResponse, allowedEntityFields);
		return applicationEntityFieldTypeResponse;
	}
}
