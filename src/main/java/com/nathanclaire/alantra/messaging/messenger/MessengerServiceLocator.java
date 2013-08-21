/**
 * 
 */
package com.nathanclaire.alantra.messaging.messenger;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.datasource.model.DataChannel;

/**
 * @author Edward Banfa
 *
 */
public interface MessengerServiceLocator {
	
	public MessengerService findMessengerService(DataChannel channel) throws ApplicationException;

}
