/**
 * 
 */
package com.nathanclaire.alantra.security.service.process;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.nathanclaire.alantra.base.service.process.BaseProcessService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.security.model.SystemUser;
import com.nathanclaire.alantra.security.service.entity.SystemUserService;

/**
 * @author Edward Banfa 
 *
 */
@Stateless
public class SecurityServiceImpl extends BaseProcessService implements SecurityService {

	@Inject SystemUserService userService;
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.security.service.process.SecurityService#findUserBySourceAddress(java.lang.String)
	 */
	@Override
	public SystemUser findUserBySourceAddress(String sourceAddress) throws ApplicationException {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.security.service.process.SecurityService#getUserById(java.lang.Integer)
	 */
	@Override
	public SystemUser getUserById(Integer userId) throws ApplicationException {
		SystemUser user = userService.findById(userId);
		if(user == null)
			throw new ApplicationException(USER_NOT_FOUND);
		return user;
	}

}
