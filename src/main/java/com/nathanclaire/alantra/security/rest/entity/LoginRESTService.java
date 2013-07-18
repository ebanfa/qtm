/**
 * 
 */
package com.nathanclaire.alantra.security.rest.entity;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.security.model.SystemUser;
import com.nathanclaire.alantra.security.service.process.SecurityModuleService;

/**
 * @author Edward Banfa 
 *
 */
@Path("/login")
@Stateless
public class LoginRESTService 
{
	private static final String PASSWORD = "password";
	private static final String USERNAME = "username";
	
	@Inject SecurityModuleService securityService;
	private Logger logger = LoggerFactory.getLogger(LoginRESTService.class);
	
    @POST 
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public boolean login(MultivaluedMap<String, String> form)
	{
    	logger.debug("Using encoded form {} ", form);
    	//
    	String userName = form.getFirst(USERNAME);
    	String password = form.getFirst(PASSWORD);
    	try {
        	logger.debug("Login in user: {} {} {}", userName, password, securityService.encryptPassword(password));
			SystemUser user = securityService.login(userName, password);
			logger.debug("Successfully authenticated user :" + user.getEmail());
			return true;
		} catch (ApplicationException e) {
			logger.error(e.getMessage());
		}
		logger.debug("Athentication failed for user :" + userName);
		return false;
	}
}
