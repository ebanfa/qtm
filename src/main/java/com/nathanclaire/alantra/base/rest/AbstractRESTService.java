/**
 * 
 */
package com.nathanclaire.alantra.base.rest;

import com.nathanclaire.alantra.application.rest.ActivityResponse;
import com.nathanclaire.alantra.base.util.ExceptionUtil;
import com.nathanclaire.alantra.businessobject.data.BusinessObjectResponse;

/**
 * @author Edward Banfa
 *
 */
public class AbstractRESTService {
	
	
	protected void processRESTException(Exception e, 
			BusinessObjectResponse businessObjectResponse)
	{
		businessObjectResponse.setErrors(true);
		businessObjectResponse.setErrorMessage(e.getMessage());
		ExceptionUtil.logException(e);
	}
	
	protected void processRESTException(Exception e, ActivityResponse activityResponse)
	{
		ExceptionUtil.logException(e);
		activityResponse.setErrors(true);
		activityResponse.setErrorMessage(e.getMessage());
	}

}
