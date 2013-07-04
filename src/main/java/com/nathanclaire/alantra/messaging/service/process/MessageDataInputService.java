/**
 * 
 */
package com.nathanclaire.alantra.messaging.service.process;

import com.nathanclaire.alantra.base.service.process.EntityDataInputService;

/**
 * @author Edward Banfa 
 *
 */
public interface MessageDataInputService extends EntityDataInputService
{
	public static final String CANNOT_PARSE_APPLICATION_ACTION_FOR_MSG = 
			"MessageDataInputService.CANNOT_PARSE_APPLICATION_ACTION_FOR_MSG";
	public static final String INVALID_PRIM_ENTITY_SPECIFIED = "MessageDataInputService.INVALID_PRIM_ENTITY_SPECIFIED";

}
