/**
 * 
 */
package com.nathanclaire.alantra.base.service.process;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.nathanclaire.alantra.base.util.Messages;

/**
 * @author Edward Banfa 
 *
 */
public class BaseTimerService {

	
	@PersistenceContext(unitName = "primary")
    protected EntityManager entityManager;
	
	@Resource(name = "java:jboss/datasources/alantraDS")
	javax.sql.DataSource ds; 
	

	protected String getMessage(String messageKey)
	{
		return Messages.getString(messageKey);
	}

}
