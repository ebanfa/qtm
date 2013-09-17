/**
 * 
 */
package com.nathanclaire.alantra.application.service.process;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.nathanclaire.alantra.application.model.ApplicationEntity;
import com.nathanclaire.alantra.application.model.ApplicationEntityField;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.businessobject.data.BusinessObjectFieldData;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class ActivityServiceImpl implements ActivityService {
	
	@Inject ApplicationEntityService entityService;

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.process.ActivityService#getEntityListFields(java.lang.String)
	 */
	@Override
	public List<BusinessObjectFieldData> getEntityListFields(String entityName)
			throws ApplicationException 
	{
		List<BusinessObjectFieldData> fieldDataList = new ArrayList<BusinessObjectFieldData>();
		ApplicationEntity entity = entityService.findByName(entityName);
		for(ApplicationEntityField entityField : entity.getApplicationEntityFields()){
			BusinessObjectFieldData fieldData = new  BusinessObjectFieldData();
			fieldData.setFieldName(entityField.getName());
			fieldData.setFieldDataType(entityField.getApplicationEntityFieldType().getCode());
			fieldDataList.add(fieldData);
		}
		return fieldDataList;
	}

}
