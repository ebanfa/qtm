/**
 * 
 */
package com.nathanclaire.alantra.application.service.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.ws.rs.core.MultivaluedMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.application.model.ApplicationEntity;
import com.nathanclaire.alantra.application.model.ApplicationEntityField;
import com.nathanclaire.alantra.application.model.ApplicationModule;
import com.nathanclaire.alantra.application.request.ApplicationEntityRequest;
import com.nathanclaire.alantra.application.response.ApplicationEntityResponse;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.base.util.PropertyUtils;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class ApplicationEntityServiceImpl 
	extends BaseEntityServiceImpl<ApplicationEntity, ApplicationEntityResponse, ApplicationEntityRequest> 
	implements ApplicationEntityService
{
	
	private static final String ENTITY_NAME = "ApplicationEntity";
	private static final String LIST_ACTIVITY_CODE = "LIST_APPLICATIONENTITY";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_APPLICATIONENTITY";
	
	private static final String LIST_ITEM_APPLICATIONMODULE = "applicationModule";

	private Logger logger = LoggerFactory.getLogger(ApplicationEntityServiceImpl.class);
	
	//@Inject
	//ApplicationModuleService  applicationModuleService;
	
	/**
	 * @param entityClass
	 */
	public ApplicationEntityServiceImpl() {
		super(ApplicationEntity.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.ApplicationEntity#findById(java.lang.Integer)
	 */
	@Override
	public ApplicationEntity findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.ApplicationEntity#findByCode(java.lang.String)
	 */
	@Override
	public ApplicationEntity findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.ApplicationEntity#findByName(java.lang.String)
	 */
	@Override
	public ApplicationEntity findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.ApplicationEntity#findAll(java.util.Map)
	 */
	@Override
	public List<ApplicationEntity> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.ApplicationEntity#createApplicationEntity(com.nathanclaire.alantra.application.rest.request.ServiceRequest)
	 */
	@Override
	public ApplicationEntity create(ApplicationEntityRequest applicationEntityRequest) {
		return createInstance(applicationEntityRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.ApplicationEntity#deleteApplicationEntity(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.ApplicationEntity#updateApplicationEntity(com.nathanclaire.alantra.application.rest.request.ServiceRequest)
	 */
	@Override
	public ApplicationEntity update(ApplicationEntityRequest applicationEntityRequest) {
		return updateInstance(applicationEntityRequest);
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
		return this.getFieldsForEntity(ENTITY_NAME);
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#relatedEntitesToListItems()
	 */
	@Override
	public Map<String, List<ListItemResponse>> relatedEntitesToListItems() 
	{
		/*Map<String, List<ListItemResponse>> listItems = new HashMap<String, List<ListItemResponse>>(); 
		List<ListItemResponse> applicationModules = applicationModuleService.asListItem();
    	
		listItems.put(LIST_ITEM_APPLICATIONMODULE, applicationModules); 
		return listItems;*/
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#asListItem()
	 */
	@Override
	public List<ListItemResponse> asListItem() {
		List<ListItemResponse> listItems = new ArrayList<ListItemResponse>();
		for(ApplicationEntity applicationentity: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(applicationentity.getId(), applicationentity.getCode(), applicationentity.getName());
			listItems.add(item);
		}
		return listItems;
	}

/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.entity.ApplicationEntityService#getEntityFields(java.lang.String)
	 */
	@Override
	public List<ApplicationEntityField> getFieldsForEntity(String entityName) {
		//logger.debug("Loading fields for entity {}", entityName);
		ApplicationEntity applicationEntity = this.findByName(entityName);
		List<ApplicationEntityField> fieldResponses = new ArrayList<ApplicationEntityField>();
		if (applicationEntity != null)
		{
			Set<ApplicationEntityField> entityFields = applicationEntity.getApplicationEntityFields();
			for(ApplicationEntityField field:entityFields)
			{
				//logger.debug("Found field {} for applicationEntity {} with name {}", field.getCode(), applicationEntity.getCode(), applicationEntity.getName());
				fieldResponses.add(field);
			}
		//logger.debug("Loaded {} fields for entity {}", entityFields.size(), entityName);
		}
		return fieldResponses;
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl#extractPredicates(javax.ws.rs.core.MultivaluedMap, javax.persistence.criteria.CriteriaBuilder, javax.persistence.criteria.Root)
	 */
	@Override
    protected Predicate[] extractPredicates(MultivaluedMap<String,
            String> queryParameters,
            CriteriaBuilder criteriaBuilder,
            Root<ApplicationEntity> root) {

        List<Predicate> predicates = new ArrayList<Predicate>();
        if (queryParameters.containsKey(NAME_CRITERIA)) {
            String venue = queryParameters.getFirst(NAME_CRITERIA);
            predicates.add(criteriaBuilder.equal(root.get(NAME_CRITERIA), venue));
        }
        return predicates.toArray(new Predicate[]{});
    }

	/**
     * @param request
     * @return
     */
	@Override
    public ApplicationEntity convertRequestToModel(ApplicationEntityRequest applicationEntityRequest) 
    {
		ApplicationEntity applicationEntity = new ApplicationEntity();
		applicationEntity = this.loadDefaultFieldsFromRequest(applicationEntity, applicationEntityRequest);
    	//Process many to one relationships
    	if (applicationEntityRequest.getApplicationModule() != null)
    	{
    		ApplicationModule applicationModule = getEntityManager().find(ApplicationModule.class, applicationEntityRequest.getApplicationModule());
    		applicationEntity.setApplicationModule(applicationModule);
    	}
    	applicationEntity.setName(applicationEntityRequest.getName()); 
    	applicationEntity.setDescription(applicationEntityRequest.getDescription()); 
    	applicationEntity.setDisplayNm(applicationEntityRequest.getDisplayNm()); 
    	applicationEntity.setDisplayNmPlural(applicationEntityRequest.getDisplayNmPlural()); 
    	applicationEntity.setHasTable(applicationEntityRequest.getHasTable()); 
    	applicationEntity.setDbName(applicationEntityRequest.getDbName()); 
    	applicationEntity.setCode(applicationEntityRequest.getCode()); 
    	applicationEntity.setRecSt(applicationEntityRequest.getRecSt()); 
		return applicationEntity;
	}
	
	@Override
	public ApplicationEntityResponse convertModelToResponse(ApplicationEntity model) {
		if (model == null) return null;
		ApplicationEntityResponse applicationEntityResponse = new ApplicationEntityResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(model, applicationEntityResponse, allowedEntityFields);
		return applicationEntityResponse;
	}
}