/**
 * 
 */
package com.nathanclaire.alantra.security.service.process;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.jasypt.util.password.BasicPasswordEncryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.service.process.BaseProcessService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.datasource.model.DataChannel;
import com.nathanclaire.alantra.security.model.SystemUser;
import com.nathanclaire.alantra.security.service.entity.CurrentUserSessionService;
import com.nathanclaire.alantra.security.service.entity.SystemUserService;

/**
 * @author Edward Banfa 
 *
 */
@Stateless
public class SecurityModuleServiceImpl extends BaseProcessService implements SecurityModuleService {

	public static final String EMAIL = "email";
	public static final String MOBILE = "mobile";

	public static final String LOCKED_FG = "Y";
	
	@Inject SystemUserService userService;
	@Inject CurrentUserSessionService userSessionService;
	@Inject SystemUserChannelService channelService;
	
	private Logger logger = LoggerFactory.getLogger(SecurityModuleServiceImpl.class);
	
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.security.service.process.SecurityModuleService#findUserBySourceAddress(java.lang.String)
	 */
	@Override
	public SystemUser findUserBySourceAddress(String sourceAddress) throws ApplicationException {
		Map<String, String> criteria = new HashMap<String, String>();
		if(sourceAddress.contains("@"))
			criteria.put(EMAIL, sourceAddress);
		else
			criteria.put(MOBILE, sourceAddress);
		List<SystemUser> users = userService.findByCriteria(criteria);
		if(!users.isEmpty())
			return users.get(0);
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.security.service.process.SecurityModuleService#getUserById(java.lang.Integer)
	 */
	@Override
	public SystemUser getUserById(Integer userId) throws ApplicationException {
		SystemUser user = userService.findById(userId);
		if(user == null)
			throw new ApplicationException(USER_NOT_FOUND);
		return user;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.security.service.process.SecurityModuleService#login(java.lang.String, java.lang.String)
	 */
	@Override
	public SystemUser login(String userName, String password) throws ApplicationException {
		logger.debug("Searching for user with username {}", userName);
		// Lookup the user with the specified 
		SystemUser user = userService.findByUsername(userName);
		logger.debug("Found user {}", user);
		if(user == null)
			throw new ApplicationException(INVALID_USER_NAME_OR_PASSWORD);
		// Check that the user is allowed to login
		logger.debug("Checking if user account is disabled.");
		if(isUserDisabled(user)){
			logger.debug("User account is disabled.");
			throw new ApplicationException(USER_DISABLED);
		}
		logger.debug("User account is not disabled");
		// Verify the provided password
		if(!isValidUserPassword(user, password)){
			logger.debug("Invalid user password.");
			throw new ApplicationException(INVALID_USER_NAME_OR_PASSWORD);
		}
		logger.debug("User password has be verified.");
		// Check that the user session is not active.
		if(!isUserSessionActive(user)){
			logger.debug("User session is already active.");
			throw new ApplicationException(USER_SESSION_ACTIVE);
		}
		logger.debug("Login process completed with no error.");
		return user;
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.security.service.process.SecurityModuleService#isUserDisabled(com.nathanclaire.alantra.security.model.SystemUser)
	 */
	@Override
	public boolean isUserDisabled(SystemUser user) throws ApplicationException 
	{
		if(user.getLockedFg() == LOCKED_FG)
			return true;
		return false;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.security.service.process.SecurityModuleService#isUserSessionActive(com.nathanclaire.alantra.security.model.SystemUser)
	 */
	@Override
	public boolean isUserSessionActive(SystemUser user) throws ApplicationException {
		if(userSessionService.getUserSession(user) == null)
			return true;
		return false;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.security.service.process.SecurityModuleService#isValidUserPassword(com.nathanclaire.alantra.security.model.SystemUser, java.lang.String)
	 */
	@Override
	public boolean isValidUserPassword(SystemUser user, String password) throws ApplicationException 
	{
		BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
		if(passwordEncryptor.checkPassword(password, user.getPassword())){
			return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.security.service.process.SecurityModuleService#encryptPassword(java.lang.String)
	 */
	@Override
	public String encryptPassword(String password) throws ApplicationException {
		try {
			BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
			return passwordEncryptor.encryptPassword(password);
		} 
		catch (Exception e) 
		{
			throw new ApplicationException(PASSWORD_ENCRYPTION_ERROR, e.getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.security.service.process.SecurityModuleService#getSystemUserOutboundChannels(com.nathanclaire.alantra.security.model.SystemUser)
	 */
	@Override
	public List<DataChannel> getSystemUserOutboundChannels(SystemUser user)
			throws ApplicationException {
		return channelService.getSystemUserOutboundChannels(user);
	}

	@Override
	public List<SystemUser> findAdminUsers() throws ApplicationException {
		return userService.findAllAdminUsers();
	}

}
