/**
 * 
 */
package com.nathanclaire.alantra.base.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.application.model.ApplicationActivity;
import com.nathanclaire.alantra.application.model.ApplicationActivityGroup;
import com.nathanclaire.alantra.application.model.ApplicationEntity;
import com.nathanclaire.alantra.application.model.ApplicationEntityField;
import com.nathanclaire.alantra.application.model.ApplicationEntityFieldType;
import com.nathanclaire.alantra.application.model.ApplicationModule;
import com.nathanclaire.alantra.application.service.entity.ApplicationActivityGroupService;
import com.nathanclaire.alantra.application.service.entity.ApplicationActivityService;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityFieldService;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityFieldTypeService;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.application.service.entity.ApplicationModuleService;

/**
 * @author Edward Banfa 
 *
 */
@Path("/iderestservice")
@Stateless
public class IDERestService 
{
	
	@Inject
	ApplicationModuleService applicationModuleService;

	@Inject
	ApplicationEntityService applicationEntityService;
	
	@Inject 
	ApplicationActivityService applicationActivityService;
	
	@Inject 
	ApplicationEntityFieldService applicationEntityFieldService;
	
	@Inject 
	ApplicationActivityGroupService applicationActivityGroupService;
	
	@Inject 
	ApplicationEntityFieldTypeService applicationEntityFieldTypeService;
	
	@Inject
	EntityManager entityManager;
	
	private List<ApplicationModule> modules = new ArrayList<ApplicationModule>();
	private List<ApplicationEntity> entities = new ArrayList<ApplicationEntity>();
	private List<ApplicationActivity> activities = new ArrayList<ApplicationActivity>();
	private List<ApplicationEntityField> fields = new ArrayList<ApplicationEntityField>();
	private List<ApplicationActivityGroup> groups = new ArrayList<ApplicationActivityGroup>();
	private List<ApplicationEntityFieldType> fieldTypes = new ArrayList<ApplicationEntityFieldType>();
	

	private Logger logger = LoggerFactory.getLogger(IDERestService.class);

    /**
     * @param uriInfo
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
	public void process(@Context UriInfo uriInfo)
	{
    	logger.debug("Staring build process");
    	this.init(uriInfo.getQueryParameters());
		this.processModules();
    	logger.debug("Finished build process");
	}
    
    private void init(MultivaluedMap<String, String> queryParameters)
    {
		modules = applicationModuleService.findAll(queryParameters);
		entities = applicationEntityService.findAll(queryParameters);
		fields = applicationEntityFieldService.findAll(queryParameters);
		activities = applicationActivityService.findAll(queryParameters);
		groups = applicationActivityGroupService.findAll(queryParameters);
		fieldTypes = applicationEntityFieldTypeService.findAll(queryParameters);
    }
    
    /**
     * 
     */
    private void processModules()
    {
    	for (ApplicationModule module: modules)
		{
        	logger.debug("Processing module {}", module.getCode());
        	//if(module.getCode().equals("CHANNEL"))
        		this.processEntities(module);
        	//logger.debug("Processed module {}", module.getCode());
		}
    }
    
	/**
	 * @param module
	 */
	public void processEntities(ApplicationModule module)
	{
		logger.debug("Processing {} entities for module {}", entities.size(), module.getCode());
		for (ApplicationEntity entity: entities)
		{
			if(this.doesEntityBelongToModule(entity, module))
			{
				this.processEntity(entity, module);
				this.processActivityGroups(entity, module);
			}
			else 
			{
				//logger.debug("Entity {} does not belong to module {}", entity.getCode(), module.getCode());
			}
		}
		logger.debug("Processed entities for module {}", module.getCode());
	}
	
	/**
	 * @param entity
	 * @param module
	 */
	public void processEntity(ApplicationEntity entity, ApplicationModule module)
	{
    	//logger.debug("Processing entity {}", entity.getCode());
		entity.setApplicationModule(module);
		entityManager.merge(entity);
		this.processEntityFields(entity);
    	//logger.debug("Processed entity {}", entity.getCode());
	}
	
	/**
	 * @param entity
	 */
	public void processEntityFields(ApplicationEntity entity)
	{
		for (ApplicationEntityField field : fields)
		{
			if(doesFieldBelongToEntity(field, entity))
			{
	        	logger.debug("Found field {} belonging to entity {}", field.getCode(), entity.getCode());
				this.processEntityField(field, entity);
			}
		}
	}
	
	/**
	 * @param field
	 * @param entity
	 */
	public void processEntityField(ApplicationEntityField field, ApplicationEntity entity)
	{
    	logger.debug("Processing field {}", field.getCode());
		field.setApplicationEntity(entity);
		this.processEntityFieldType(field);
		entityManager.merge(field);
    	logger.debug("Processed entity {}", field.getCode());
	}
	
	public void processEntityFieldType(ApplicationEntityField field)
	{
		for(ApplicationEntityFieldType fieldType: fieldTypes)
		{
			if(doesFieldBelongToFieldType(field, fieldType))
			{
	        	field.setApplicationEntityFieldType(fieldType);
			}
		}
	}
	
	/**
	 * @param entity
	 * @param module
	 */
	public void processActivityGroups(ApplicationEntity entity, ApplicationModule module)
	{
		for(ApplicationActivityGroup group: groups)
		{
			if (doesActivityGroupBelongToEntity(group, entity))
			{
	        	//logger.debug("Found group {} belong to entity {}", group.getCode(), entity.getCode());
				this.processActivityGroup(group, entity);
			}
		}
	}
	
	/**
	 * @param group
	 * @param entity
	 */
	private void processActivityGroup(ApplicationActivityGroup group,	ApplicationEntity entity) 
	{
    	//logger.debug("Processing group {}", group.getCode());
		this.processEntityActivities(entity, group);
    	//logger.debug("Processing group {}", group.getCode());
	}

	/**
	 * @param entity
	 * @param group
	 */
	public void processEntityActivities(ApplicationEntity entity, ApplicationActivityGroup group)
	{
		for(ApplicationActivity activity: activities)
		{
			this.processEntityActivity(activity, entity, group);
		}
	}
	
	/**
	 * @param activity
	 * @param entity
	 * @param group
	 */
	public void processEntityActivity(ApplicationActivity activity, ApplicationEntity entity, ApplicationActivityGroup group)
	{
    	//logger.debug("Processing activity {}", activity.getCode());
		if(this.doesActivityBelongToGroup(activity, group))
		{
			
			activity.setApplicationActivityGroup(group);
		}
		if(this.doesActivityBelongToEntity(activity, entity))
		{
			logger.debug("Processed activity {} for entity {}", activity.getCode(), entity.getCode());
			activity.setApplicationEntity(entity);
			activity.setApplicationModule(entity.getApplicationModule());
		}
    	group.setApplicationModule(entity.getApplicationModule());
    	entityManager.merge(group);
		entityManager.merge(activity);
    	//logger.debug("Processed activity {}", activity.getCode());
	}
	
	/**
	 * @param entity
	 * @param module
	 * @return
	 */
	private boolean doesEntityBelongToModule(ApplicationEntity entity, ApplicationModule module)
	{
		String moduleName = extractModuleNameFromEntityCode(entity.getCode());
		//logger.debug("Extracted the module name {} from entity {} to compare with module name {}", moduleName, entity.getCode(), module.getCode());
	    if (moduleName.equals(module.getCode()))return true;
		return false;
	}

	/**
	 * @param field
	 * @param entity
	 * @return
	 */
	private boolean doesFieldBelongToEntity(ApplicationEntityField field, ApplicationEntity entity)
	{
		String entityName = extractEntityNameFromEntityCode(entity.getCode());
		String entityNameFromFieldCode = this.extractEntityNameFromFieldCode(field.getCode());
	    if (entityName.equals(entityNameFromFieldCode))return true;
		return false;
	}
	
	/**
	 * @param activity
	 * @param group
	 * @return
	 */
	private boolean doesActivityGroupBelongToEntity(ApplicationActivityGroup group, ApplicationEntity entity)
	{
		String entityName = extractEntityNameFromEntityCode(entity.getCode());
		String entityNameFromGroupCode = this.extractEntityNameFromGroupCode(group.getCode());
	    if (entityName.equals(entityNameFromGroupCode))return true;
		return false;
	}


	/**
	 * @param activity
	 * @param group
	 * @return
	 */
	private boolean doesActivityBelongToGroup(ApplicationActivity activity, ApplicationActivityGroup group)
	{
		String entityNameFromGroupCode = this.extractEntityNameFromGroupCode(group.getCode());
		String entityNameFromActivityCode = this.extractEntityNameFromActivityCode(activity.getCode());
	    if (entityNameFromActivityCode.equals(entityNameFromGroupCode))return true;
		return false;
	}
	
	/**
	 * @param activity
	 * @param entity
	 * @return
	 */
	private boolean doesActivityBelongToEntity(ApplicationActivity activity, ApplicationEntity entity)
	{
		String entityNameFromEntityCode = this.extractEntityNameFromEntityCode(entity.getCode());
		String entityNameFromActivityCode = this.extractEntityNameFromActivityCode(activity.getCode());
		logger.debug("Extracted the entity name {} from activity {} to compare with entity name {}", entityNameFromActivityCode, activity.getCode(), entityNameFromEntityCode);
	    if (entityNameFromActivityCode.equals(entityNameFromEntityCode))return true;
		return false;
	}
	
	/**
	 * @param field
	 * @param fieldType
	 * @return
	 */
	private boolean doesFieldBelongToFieldType(ApplicationEntityField field, ApplicationEntityFieldType fieldType)
	{
		String fieldTypeCodeFromFieldCode = this.extractFieldTypeFormFieldCode(field.getCode());
		logger.debug("Extracted the field type code {} from field code {} to compare with field type {}", fieldTypeCodeFromFieldCode, field.getCode(), fieldType.getCode());
	    if (fieldTypeCodeFromFieldCode.equals(fieldType.getCode()))return true;
		return false;
	}
	
	
	/**
	 * @param codeString
	 * @return
	 */
	private String extractModuleNameFromEntityCode(String codeString)
	{
		String[] codeSubstrings = codeString.split("_");
		if(codeSubstrings.length > 1)
		{
			return codeSubstrings[0];
		}
		return "";
	}
	
	/**
	 * @param codeString
	 * @return
	 */
	private String extractEntityNameFromEntityCode(String codeString)
	{
		String[] codeSubstrings = codeString.split("_");
		if(codeSubstrings.length > 1)
		{
			return codeSubstrings[1];
		}
		return "";
	}
	
	/**
	 * @param codeString
	 * @return
	 */
	private String extractEntityNameFromFieldCode(String codeString)
	{
		String[] codeSubstrings = codeString.split("_");
		if(codeSubstrings.length > 1)
		{
			return codeSubstrings[0];
		}
		return "";
	}
	
	/**
	 * @param codeString
	 * @return
	 */
	private String extractEntityNameFromGroupCode(String codeString)
	{
		String[] codeSubstrings = codeString.split("_");
		if(codeSubstrings.length > 1)
		{
			return codeSubstrings[0];
		}
		return "";
	}
	
	/**
	 * @param codeString
	 * @return
	 */
	private String extractEntityNameFromActivityCode(String codeString)
	{
		String[] codeSubstrings = codeString.split("_");
		if(codeSubstrings.length > 2)
		{
			return codeSubstrings[2];
		}
		return "";
	}
	
	/**
	 * @param codeString
	 * @return
	 */
	private String extractFieldTypeFormFieldCode(String codeString)
	{
		String[] codeSubstrings = codeString.split("_");
		if(codeSubstrings.length > 2)
		{
			return codeSubstrings[2];
		}
		return "";
	}

}
