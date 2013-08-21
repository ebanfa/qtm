/**
 * 
 */
package com.nathanclaire.alantra.customer.service.process;

import java.util.List;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.customer.model.Customer;
import com.nathanclaire.alantra.datasource.model.DataChannel;


/**
 * @author Edward Banfa 
 *
 */
public interface CustomerCommsChannelService {
	
	public DataChannel getDefaultCustomerSMSChannel(Customer customer)
			throws ApplicationException;

	public List<DataChannel> getCustomerSMSChannels(Customer customer)
			throws ApplicationException;

	public DataChannel getDefaultCustomerEMAILChannel(Customer customer)
			throws ApplicationException;

	public List<DataChannel> getCustomerEMAILChannels(Customer customer)
			throws ApplicationException;

	public DataChannel getDefaultCustomerIVRChannel(Customer customer)
			throws ApplicationException;

	public List<DataChannel> getCustomerIVRChannels(Customer customer)
			throws ApplicationException;
}
