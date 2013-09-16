/**
 * 
 */
package com.nathanclaire.alantra.application.service.process;

import java.util.List;

import javax.ejb.Stateless;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.businessobject.util.BusinessObjectFieldData;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class ActivityServiceImpl implements ActivityService {

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.process.ActivityService#getEntityListFields(java.lang.String)
	 */
	@Override
	public List<BusinessObjectFieldData> getEntityListFields(String entityName)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

}
