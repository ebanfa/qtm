/**
 * 
 */
package com.nathanclaire.alantra.messaging.service.entity;

import com.nathanclaire.alantra.base.service.entity.BaseEntityService;
import com.nathanclaire.alantra.messaging.model.MessageApplication;
import com.nathanclaire.alantra.messaging.request.MessageApplicationRequest;
import com.nathanclaire.alantra.messaging.response.MessageApplicationResponse;

/**
 * @author Edward Banfa
 *
 */
public interface MessageApplicationService extends BaseEntityService<MessageApplication, MessageApplicationResponse, MessageApplicationRequest>
{

	public String OUT_BOUND_MSG_APPLICATION = "OUT_BOUND_MSG_APPLICATION";
	public String UNKNOWN_MSG_APPLICATION = "UNKNOWN_MSG_APPLICATION";
	
}
