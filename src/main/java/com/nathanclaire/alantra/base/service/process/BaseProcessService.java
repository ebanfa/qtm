/**
 * 
 */
package com.nathanclaire.alantra.base.service.process;

import com.nathanclaire.alantra.base.util.Messages;

/**
 * @author Edward Banfa 
 *
 */
public class BaseProcessService {

	protected String getMessage(String messageKey)
	{
		return Messages.getString(messageKey);
	}
}
