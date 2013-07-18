/**
 * 
 */
package com.nathanclaire.alantra.notification.service.process;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.service.process.BaseProcessService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.StringUtil;
import com.nathanclaire.alantra.notification.model.Template;
import com.nathanclaire.alantra.notification.model.TemplateType;
import com.nathanclaire.alantra.notification.model.TemplateTypeTag;
import com.nathanclaire.alantra.notification.util.FilledTemplate;

/**
 * @author Edward Banfa 
 *
 */
@Stateless
public class TemplateProcessingServiceImpl extends BaseProcessService implements TemplateProcessingService 
{
	private Logger logger = LoggerFactory.getLogger(TemplateProcessingServiceImpl.class);
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.notification.service.process.TemplateProcessingService#fillTemplate(com.nathanclaire.alantra.notification.model.Template, java.util.Map)
	 */
	@Override
	public FilledTemplate fillTemplate(Template template, Map<String, String> tagValues) throws ApplicationException {
		if(template == null)
			throw new ApplicationException(INVALID_TEMPLATE_SPECIFIED);
		if (tagValues == null)
			tagValues = new HashMap<String, String>();
		// Get the template tags associated with the template type
		Set<TemplateTypeTag> tags = getTemplateTags(template);
		logger.debug("Filling template {} with {} tag values {}", template, tagValues.size(), tagValues);
		// Get the template type
		FilledTemplate filledTemplate = new FilledTemplate(
				template.getSubjectTxt(), template.getMessageTxt());
		if(tags.isEmpty())
			return filledTemplate;
		logger.debug("Filling template header {}\n body {}", filledTemplate.getHeader(), filledTemplate.getBody());
		// For each tag search and replace its occurrence in subject 
		// and body text of the template, keeping track of resulting string(s)
		for(TemplateTypeTag tag:tags)
		{
			if(tagValues.containsKey(tag.getCode()))
			{
				// Header
				filledTemplate.setHeader(
						StringUtil.findAndReplace(filledTemplate.getHeader(), tag.getCode(), tagValues.get(tag.getCode()), false));
				// Body
				filledTemplate.setBody(
						StringUtil.findAndReplace(filledTemplate.getBody(), tag.getCode(), tagValues.get(tag.getCode()), false));
			}
		}
		return filledTemplate;
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.notification.service.process.TemplateProcessingService#addToTemplate(java.lang.String, java.lang.String, java.util.Map)
	 */
	@Override
	public Map<String, String> addToTemplate(String tagKey, String tagValue, Map<String, String> templateTagValues){
		if(templateTagValues == null)
			templateTagValues = new HashMap<String, String>();
		templateTagValues.put(tagKey, tagValue);
		return templateTagValues;
	}
	
	/**
	 * @param template
	 * @return
	 */
	private Set<TemplateTypeTag> getTemplateTags(Template template) throws ApplicationException 
	{
		if(template == null)
			throw new ApplicationException(INVALID_TEMPLATE_SPECIFIED_NULL);
		TemplateType templateType = template.getTemplateType();
		if(templateType == null)
			throw new ApplicationException(CONFIG_ERROR_NO_TEMPLATE_TYPE_CONFIGURED);
		return templateType.getTemplateTypeTags();
	}
}
