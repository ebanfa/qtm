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
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.ws.rs.core.MultivaluedMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.application.model.ApplicationActivity;
import com.nathanclaire.alantra.application.model.ApplicationActivityGroup;
import com.nathanclaire.alantra.application.model.ApplicationActivityType;
import com.nathanclaire.alantra.application.model.ApplicationEntity;
import com.nathanclaire.alantra.application.model.ApplicationEntityField;
import com.nathanclaire.alantra.application.model.ApplicationForm;
import com.nathanclaire.alantra.application.model.ApplicationModule;
import com.nathanclaire.alantra.application.request.ApplicationActivityRequest;
import com.nathanclaire.alantra.application.response.ApplicationActivityResponse;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtil;

/**
 * @author administrator
 *
 */
@Stateless
public class ApplicationActivityServiceImpl 
	extends BaseEntityServiceImpl<ApplicationActivity, ApplicationActivityResponse, ApplicationActivityRequest> 
	implements ApplicationActivityService
{
	private static final String LIST_ITEM_FORMS = "applicationForm"; 
	private static final String LIST_ITEM_ENTITIES = "applicationEntity"; 
	private static final String LIST_ITEM_MODULE = "applicationModule"; 
	private static final String LIST_ITEM_ACTIVITY_TY = "applicationActivityType";
	private static final String LIST_ITEM_ACTIVITY_GRP = "applicationActivityGroup"; 
	
	private static final String ENTITY_NAME = "ApplicationActivity";
	private static final String LIST_ACTIVITY_CODE = "LIST_APPLICATION_APPLICATIONACTIVITY";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_APPLICATION_APPLICATIONACTIVITY";
	private static final String ACTIVITY_URL_FIELD_NAME = "activityUrl";
	private static final String ACTIVITY_URL_CRITERIA = "activityUrl";

	private Logger logger = LoggerFactory.getLogger(ApplicationActivityServiceImpl.class);

	@Inject
	ApplicationFormService applicationFormService;
	@Inject
	ApplicationEntityService  applicationEntityService;
	@Inject
	ApplicationModuleService applicationModuleService;
	@Inject
	ApplicationActivityTypeService applicationActivityTypeService;
	@Inject
	ApplicationActivityGroupService applicationActivityGroupService;

	
	/**
	 * @param entityClass
	 */
	public ApplicationActivityServiceImpl() {
		super(ApplicationActivity.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.ApplicationActivity#findById(java.lang.Integer)
	 */
	@Override
	public ApplicationActivity findById(Integer id)  throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.ApplicationActivity#findByCode(java.lang.String)
	 */
	@Override
	public ApplicationActivity findByCode(String code)  throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.ApplicationActivity#findByName(java.lang.String)
	 */
	@Override
	public ApplicationActivity findByName(String name)  throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.ApplicationActivity#findByName(java.lang.String)
	 */
	@Override
	public ApplicationActivity findByActivityURL(String name)  throws ApplicationException {
    	queryParameters.clear();
    	queryParameters.add(ACTIVITY_URL_FIELD_NAME, name);
    	List<ApplicationActivity> instances = findAllInstances(queryParameters);
    	if(instances.isEmpty()) return null;
    	return instances.get(0);
	}
	

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.ApplicationActivity#findAll(java.util.Map)
	 */
	@Override
	public List<ApplicationActivity> findAll(MultivaluedMap<String, String> queryParameters)  throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.ApplicationActivity#createApplicationActivity(com.nathanclaire.alantra.application.rest.request.ServiceRequest)
	 */
	@Override
	public ApplicationActivity create(ApplicationActivityRequest applicationActivityRequest)  throws ApplicationException {
		return createInstance(applicationActivityRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.ApplicationActivity#deleteApplicationActivity(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id)  throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.ApplicationActivity#updateApplicationActivity(com.nathanclaire.alantra.application.rest.request.ServiceRequest)
	 */
	@Override
	public ApplicationActivity update(ApplicationActivityRequest applicationActivityRequest)  throws ApplicationException {
		return updateInstance(applicationActivityRequest);
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
	public Map<String, List<ListItemResponse>> relatedEntitesToListItems()  throws ApplicationException 
	{
		Map<String, List<ListItemResponse>> listItems = new HashMap<String, List<ListItemResponse>>(); 
		List<ListItemResponse> applicationForms = applicationFormService.asListItem();
		List<ListItemResponse>  applicationEntitys = applicationEntityService.asListItem();
		List<ListItemResponse> applicationModules = applicationModuleService.asListItem();
		List<ListItemResponse> applicationActivityTypes = applicationActivityTypeService.asListItem();
		List<ListItemResponse> applicationActivityGroups = applicationActivityGroupService.asListItem();
		listItems.put(LIST_ITEM_FORMS, applicationForms); 
		listItems.put(LIST_ITEM_ENTITIES, applicationEntitys); 
		listItems.put(LIST_ITEM_MODULE, applicationModules); 
		listItems.put(LIST_ITEM_ACTIVITY_TY, applicationActivityTypes);
		listItems.put(LIST_ITEM_ACTIVITY_GRP, applicationActivityGroups); 
		return listItems;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#asListItem()
	 */
	@Override
	public List<ListItemResponse> asListItem()  throws ApplicationException {
		List<ListItemResponse> listItems = new ArrayList<ListItemResponse>();
		for(ApplicationActivity activity: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(activity.getId(), activity.getCode(), activity.getName());
			listItems.add(item);
		}
		return listItems;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl#extractPredicates(javax.ws.rs.core.MultivaluedMap, javax.persistence.criteria.CriteriaBuilder, javax.persistence.criteria.Root)
	 */
	@Override
    protected Predicate[] extractPredicates(MultivaluedMap<String,
            String> queryParameters,
            CriteriaBuilder criteriaBuilder,
            Root<ApplicationActivity> root) {

        List<Predicate> predicates = new ArrayList<Predicate>();
        if (queryParameters.containsKey(NAME_CRITERIA)) {
            String name = queryParameters.getFirst(NAME_CRITERIA);
            predicates.add(criteriaBuilder.equal(root.get(NAME_CRITERIA), name));
        }
        if (queryParameters.containsKey(CODE_CRITERIA))   {
            String code = queryParameters.getFirst(CODE_CRITERIA);
            predicates.add(criteriaBuilder.equal(root.get(CODE_CRITERIA), code));
        }
        if (queryParameters.containsKey(ACTIVITY_URL_CRITERIA))   {
            String code = queryParameters.getFirst(ACTIVITY_URL_CRITERIA);
            predicates.add(criteriaBuilder.equal(root.get(ACTIVITY_URL_CRITERIA), code));
        }
        return predicates.toArray(new Predicate[]{});
    }

	@Override
	public ApplicationActivity convertRequestToModel(ApplicationActivityRequest applicationActivityRequest)  throws ApplicationException {
		ApplicationActivity applicationActivity = new ApplicationActivity();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtil.copyProperties(applicationActivityRequest, applicationActivity, allowedEntityFields);
    	//Process many to one relationships
    	if (applicationActivityRequest.getApplicationActivityGroupId() != null)
    	{
    		ApplicationActivityGroup applicationActivityGroup = getEntityManager().find(ApplicationActivityGroup.class, applicationActivityRequest.getApplicationActivityGroupId());
    		applicationActivity.setApplicationActivityGroup(applicationActivityGroup);
    	}
    	if (applicationActivityRequest.getApplicationModuleId() != null)
    	{
    		ApplicationModule applicationModule = getEntityManager().find(ApplicationModule.class, applicationActivityRequest.getApplicationModuleId());
    		applicationActivity.setApplicationModule(applicationModule);
    	}
    	if (applicationActivityRequest.getApplicationEntityId() != null)
    	{
    		ApplicationEntity applicationEntity = getEntityManager().find(ApplicationEntity.class, applicationActivityRequest.getApplicationEntityId());
    		applicationActivity.setApplicationEntity(applicationEntity);
    	}
    	if (applicationActivityRequest.getApplicationFormId() != null)
    	{
    		ApplicationForm applicationForm = getEntityManager().find(ApplicationForm.class, applicationActivityRequest.getApplicationFormId());
    		applicationActivity.setApplicationForm(applicationForm);
    	}
    	if (applicationActivityRequest.getApplicationActivityTypeId() != null)
    	{
    		ApplicationActivityType applicationActivityType = getEntityManager().find(ApplicationActivityType.class, applicationActivityRequest.getApplicationActivityTypeId());
    		applicationActivity.setApplicationActivityType(applicationActivityType);
    	}
		return applicationActivity;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl#convertModelToResponse(java.lang.Object)
	 */
	@Override
	public ApplicationActivityResponse convertModelToResponse(ApplicationActivity model)  throws ApplicationException {
		if (model == null) return null;
		ApplicationActivityResponse applicationActivityResponse = new ApplicationActivityResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		// Set the value of the response to the value of the id of the related Entity
		if(model.getApplicationEntity() != null)
			applicationActivityResponse.setApplicationEntityId(model.getApplicationEntity().getId());
		if(model.getApplicationModule() != null)
			applicationActivityResponse.setApplicationModuleId(model.getApplicationModule().getId());
		if(model.getApplicationActivityGroup() != null)
			applicationActivityResponse.setApplicationActivityGroupId(model.getApplicationActivityGroup().getId());
		if(model.getApplicationActivityType() != null)
			applicationActivityResponse.setApplicationActivityTypeId(model.getApplicationActivityType().getId());
		if(model.getApplicationForm() != null)
			applicationActivityResponse.setApplicationFormId(model.getApplicationForm().getId());
		// Copy properties
		PropertyUtil.copyProperties(model, applicationActivityResponse, allowedEntityFields);
		return applicationActivityResponse;
	}

}
