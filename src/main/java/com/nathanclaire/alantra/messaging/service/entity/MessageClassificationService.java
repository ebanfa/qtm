/**
 * 
 */
package com.nathanclaire.alantra.messaging.service.entity;

import com.nathanclaire.alantra.base.service.entity.BaseEntityService;
import com.nathanclaire.alantra.messaging.model.MessageClassification;
import com.nathanclaire.alantra.messaging.request.MessageClassificationRequest;
import com.nathanclaire.alantra.messaging.response.MessageClassificationResponse;

/**
 * @author Edward Banfa
 *
 */
public interface MessageClassificationService extends BaseEntityService<MessageClassification, MessageClassificationResponse, MessageClassificationRequest>
{
	public final String SMS = "SMS";
	public final String EMAIL = "EMAIL";
}
