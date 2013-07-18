/**
 * 
 */
package com.nathanclaire.alantra.security.service.process;

import java.util.List;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.datasource.model.DataChannel;
import com.nathanclaire.alantra.security.model.SystemUser;

/**
 * @author Edward Banfa 
 *
 */
public interface SecurityModuleService {

	public static final String USER_DISABLED = "SecurityModuleService.USER_DISABLED";
	public static final String USER_NOT_FOUND = "SecurityModuleService.USER_NOT_FOUND";
	public static final String USER_SESSION_ACTIVE = "SecurityModuleService.USER_SESSION_ACTIVE";
	public static final String PASSWORD_ENCRYPTION_ERROR = "SecurityModuleService.PASSWORD_ENCRYPTION_ERROR";
	public static final String INVALID_USER_NAME_OR_PASSWORD = "SecurityModuleService.INVALID_USER_NAME_OR_PASSWORD";

	public SystemUser findUserBySourceAddress(String sourceAddress) throws ApplicationException;

	public SystemUser getUserById(Integer userId) throws ApplicationException;
	
	public SystemUser login(String userName, String password) throws ApplicationException;

	public boolean isUserSessionActive(SystemUser user) throws ApplicationException;

	public boolean isUserDisabled(SystemUser user) throws ApplicationException;

	public boolean isValidUserPassword(SystemUser user, String password) throws ApplicationException;
	
	public String encryptPassword(String password) throws ApplicationException;
	
	public List<DataChannel> getSystemUserOutboundChannels(SystemUser user) throws ApplicationException;

	public List<SystemUser> findAdminUsers() throws ApplicationException;
}
