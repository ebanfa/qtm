/**
 * 
 */
package com.nathanclaire.alantra.base.rest;

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

}
