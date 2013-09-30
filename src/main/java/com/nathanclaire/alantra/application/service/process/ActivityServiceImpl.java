/**
 * 
 */
package com.nathanclaire.alantra.application.service.process;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.application.model.ApplicationActivity;
import com.nathanclaire.alantra.application.model.ApplicationEntity;
import com.nathanclaire.alantra.application.model.ApplicationEntityField;
import com.nathanclaire.alantra.application.service.entity.ApplicationActivityService;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.EntityUtil;
import com.nathanclaire.alantra.base.util.ErrorCodes;
import com.nathanclaire.alantra.businessobject.data.BusinessObjectFieldData;
import com.nathanclaire.alantra.businessobject.data.BusinessObjectFieldDataImpl;

/**
 * @author Edward Banfa
 * 
 * TODO: All the methods in actually just do the same
 * thing. Need to implement more specific methods for the
 * each of the use case scenarios (list, view and edit)
 */
@Stateless
public class ActivityServiceImpl implements ActivityService {
	
	@Inject ApplicationEntityService entityService;
	@Inject ApplicationActivityService applicationActivityService;
	private Logger logger = LoggerFactory.getLogger(getClass());

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.process.ActivityService#getEntityListFields(java.lang.String)
	 */
	@Override
	public List<BusinessObjectFieldData> getEntityListFields(String entityName)
			throws ApplicationException 
	{
		EntityUtil.returnOrThrowIfParamsArrayContainsNull(
				new Object[]{entityName}, "ActivityServiceImpl.getEntityListFields");
		
		List<BusinessObjectFieldData> fieldDataList = new ArrayList<BusinessObjectFieldData>();
		ApplicationEntity entity = entityService.findByName(entityName);
		EntityUtil.returnOrThrowIfNull(entity, 
				ErrorCodes.BPS_ENTITY_INSTANCE_NOT_FOUND_ERROR_CD, 
				ErrorCodes.AS_ENTITY_NOT_FOUND_ERROR_MSG);
		// Get all the fields of the the
		for(ApplicationEntityField entityField : entity.getApplicationEntityFields()){
			BusinessObjectFieldDataImpl fieldData = new  BusinessObjectFieldDataImpl();
			fieldData.setFieldName(entityField.getName());
			fieldData.setFieldDataType(entityField.getApplicationEntityFieldType().getCode());
			fieldDataList.add(fieldData);
		}
		logger.debug("Loaded {} fields for entity {}", fieldDataList.size(), entityName);
		return fieldDataList;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.process.ActivityService#getEntityEditFields(java.lang.String)
	 */
	@Override
	public List<BusinessObjectFieldData> getEntityEditFields(String entityName)
			throws ApplicationException 
	{
		return this.getEntityListFields(entityName);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.process.ActivityService#getEntityViewFields(java.lang.String)
	 */
	@Override
	public List<BusinessObjectFieldData> getEntityViewFields(String entityName)
			throws ApplicationException 
	{
		return this.getEntityListFields(entityName);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.process.ActivityService#findByActivityURL(java.lang.String)
	 */
	@Override
	public ApplicationActivity findByActivityURL(String activityURL)
			throws ApplicationException {
		return applicationActivityService.findByActivityURL(activityURL);
	}

}
