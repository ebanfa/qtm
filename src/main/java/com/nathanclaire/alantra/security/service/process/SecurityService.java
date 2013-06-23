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
public interface SecurityService {

	public static final String USER_NOT_FOUND = "SecurityService.USER_NOT_FOUND";

	public SystemUser findUserBySourceAddress(String sourceAddress) throws ApplicationException;

	public SystemUser getUserById(Integer userId) throws ApplicationException;
}
