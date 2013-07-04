/**
 * 
 */
package com.nathanclaire.alantra.notification.service.process;

import java.util.Map;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.notification.model.Template;
import com.nathanclaire.alantra.notification.util.FilledTemplate;

/**
 * @author Edward Banfa 
 *
 */
public interface TemplateProcessingService {

	public static final String CONFIG_ERROR_NO_TEMPLATE_TYPE_CONFIGURED = 
			"TemplateProcessingService.CONFIG_ERROR_NO_TEMPLATE_TYPE_CONFIGURED";
	public static final String INVALID_TEMPLATE_SPECIFIED_NULL = "TemplateProcessingService.INVALID_TEMPLATE_SPECIFIED_NULL";
	
	public FilledTemplate fillTemplate(Template template, Map<String, String> tagValues) 
			throws ApplicationException;
}