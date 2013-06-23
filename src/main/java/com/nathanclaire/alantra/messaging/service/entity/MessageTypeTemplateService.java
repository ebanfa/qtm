/**
 * 
 */
package com.nathanclaire.alantra.messaging.service.entity;

import com.nathanclaire.alantra.base.service.entity.BaseEntityService;
import com.nathanclaire.alantra.messaging.model.MessageTypeTemplate;
import com.nathanclaire.alantra.messaging.request.MessageTypeTemplateRequest;
import com.nathanclaire.alantra.messaging.response.MessageTypeTemplateResponse;

/**
 * @author Edward Banfa
 *
 */
public interface MessageTypeTemplateService extends BaseEntityService<MessageTypeTemplate, MessageTypeTemplateResponse, MessageTypeTemplateRequest>
{

	public static final String CODE = "CODE";
	public static final String JOB_CODE = "JOB_CODE";
	public static final String STATUS_TAG = "STATUS";
	
}
