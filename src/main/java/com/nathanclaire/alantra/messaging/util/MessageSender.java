/**
 * 
 */
package com.nathanclaire.alantra.messaging.util;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.datasource.model.DataChannel;

/**
 * @author Edward Banfa 
 *
 */
public interface MessageSender {

	public void sendMessage(DataChannel channel, MessageLite message) throws ApplicationException;
}
