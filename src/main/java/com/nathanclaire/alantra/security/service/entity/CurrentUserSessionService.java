/**
 * 
 */
package com.nathanclaire.alantra.security.service.entity;

import com.nathanclaire.alantra.base.service.entity.BaseEntityService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.security.model.CurrentUserSession;
import com.nathanclaire.alantra.security.model.SystemUser;
import com.nathanclaire.alantra.security.request.CurrentUserSessionRequest;
import com.nathanclaire.alantra.security.response.CurrentUserSessionResponse;

/**
 * @author Edward Banfa
 *
 */
public interface CurrentUserSessionService extends BaseEntityService<CurrentUserSession, CurrentUserSessionResponse, CurrentUserSessionRequest>
{
	public CurrentUserSession getUserSession(SystemUser user) throws ApplicationException;
}
