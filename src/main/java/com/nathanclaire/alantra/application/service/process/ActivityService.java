/**
 * 
 */
package com.nathanclaire.alantra.application.service.process;

import java.util.List;

import com.nathanclaire.alantra.application.model.ApplicationActivity;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.businessobject.data.BusinessObjectFieldData;

/**
 * @author Edward Banfa
 *
 */
public interface ActivityService {
	
	public List<BusinessObjectFieldData> getEntityListFields(String entityName) 
			throws ApplicationException;

	public List<BusinessObjectFieldData> getEntityEditFields(String entityName) 
			throws ApplicationException;

	public List<BusinessObjectFieldData> getEntityViewFields(String entityName) 
			throws ApplicationException;
	
	public ApplicationActivity findByActivityURL(String activityURL) throws ApplicationException;

}
