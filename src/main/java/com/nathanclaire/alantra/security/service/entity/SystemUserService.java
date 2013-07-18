/**
 * 
 */
package com.nathanclaire.alantra.security.service.entity;

import java.util.List;

import com.nathanclaire.alantra.base.service.entity.BaseEntityService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.security.model.SystemUser;
import com.nathanclaire.alantra.security.request.SystemUserRequest;
import com.nathanclaire.alantra.security.response.SystemUserResponse;

/**
 * @author Edward Banfa
 *
 */
public interface SystemUserService extends BaseEntityService<SystemUser, SystemUserResponse, SystemUserRequest>
{
	public SystemUser findByUsername(String username) throws ApplicationException;
	
	public List<SystemUser> findAllAdminUsers() throws ApplicationException;

	public List<SystemUser> findByIds(List<Integer> idOfUsers) throws ApplicationException;
}
