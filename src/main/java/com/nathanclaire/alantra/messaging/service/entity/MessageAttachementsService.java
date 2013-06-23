/**
 * 
 */
package com.nathanclaire.alantra.messaging.service.entity;

import com.nathanclaire.alantra.base.service.entity.BaseEntityService;
import com.nathanclaire.alantra.messaging.model.MessageAttachements;
import com.nathanclaire.alantra.messaging.request.MessageAttachementsRequest;
import com.nathanclaire.alantra.messaging.response.MessageAttachementsResponse;

/**
 * @author Edward Banfa
 *
 */
public interface MessageAttachementsService extends BaseEntityService<MessageAttachements, MessageAttachementsResponse, MessageAttachementsRequest>
{
	public static final String CSV_MIME_TYPE = "CSV";
	
	public static final String EXCEL_MIME_TYPE = "EXCEL";
}
