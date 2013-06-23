/**
 * 
 */
package com.nathanclaire.alantra.notification.service.entity;

import com.nathanclaire.alantra.base.service.entity.BaseEntityService;
import com.nathanclaire.alantra.notification.model.TemplateTypeTag;
import com.nathanclaire.alantra.notification.request.TemplateTypeTagRequest;
import com.nathanclaire.alantra.notification.response.TemplateTypeTagResponse;

/**
 * @author Edward Banfa
 *
 */
public interface TemplateTypeTagService extends BaseEntityService<TemplateTypeTag, TemplateTypeTagResponse, TemplateTypeTagRequest>
{
	public static final String MESSAGE_CODE = "MESSAGE_CODE";
	public static final String CUSTOMER_NAME = "CUSTOMER_NAME";
	public static final String USER_FULL_NAME = "USER_FULL_NAME";
	public static final String MESSAGE_TY_CODE = "MESSAGE_TY_CODE";
	public static final String MESSAGE_JOB_CODE = "MESSAGE_JOB_CODE";
	public static final String ADVICE_REFERENCE = "ADVICE_REFERENCE";
	public static final String STATUS_INFORMATION = "STATUS_INFORMATION";
	public static final String MESSAGE_SOURCE_ADDR = "MESSAGE_SOURCE_ADDR";
	public static final String TRANSACTION_REFERENCE = "TRANSACTION_REFERENCE";
	public static final String MESSAGE_DESTINATION_ADDR = "MESSAGE_DESTINATION_ADDR";
	
}
