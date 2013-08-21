/**
 * 
 */
package com.nathanclaire.alantra.security.service.process;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.security.model.SystemUser;

/**
 * @author Edward Banfa
 *
 */
public interface SystemUserContactService {
	
	
	public String getUserMobileNo(SystemUser user) throws ApplicationException;
	
	public String getUserEmailAddress(SystemUser user) throws ApplicationException;

}
