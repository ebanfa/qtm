/**
 * 
 */
package com.nathanclaire.alantra.messaging.messenger;

import javax.ejb.Stateless;

import com.nathanclaire.alantra.base.service.process.BaseProcessService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.datasource.model.DataChannel;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class MessengerServiceLocatorImpl extends BaseProcessService implements
		MessengerServiceLocator {

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.messenger.MessengerServiceLocator#findMessengerService(com.nathanclaire.alantra.datasource.model.DataChannel)
	 */
	@Override
	public MessengerService findMessengerService(DataChannel channel)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

}
