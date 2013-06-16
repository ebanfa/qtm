/**
 * 
 */
package com.nathanclaire.alantra.messaging.util;

import java.util.List;

import com.nathanclaire.alantra.base.util.ApplicationException;

/**
 * @author Edward Banfa 
 *
 */
public interface MessageReader {

	public final String CSV_MIME = "csv";
	public final String MESSAGING_ERROR = "MessageReader.MESSAGING_ERROR";
	public final String CONNECTION_ERROR = "MessageReader.CONNECTION_ERROR";
	public final String UNKNOWN_EMAIL_PROVIDER = "MessageReader.UNKNOWN_MESSAGING_PROVIDER";
	public final String UNKNOWN_MSG_FETCH_ERROR = "MessageReader.UNKNOWN_MSG_FETCH_ERROR";
	
	public List<MessageLite> getMessages(String connectionString, String username, String password) throws ApplicationException;
}
