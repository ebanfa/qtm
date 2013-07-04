/**
 * 
 */
package com.nathanclaire.alantra.customer.service.process;

import java.util.List;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.customer.model.Customer;
import com.nathanclaire.alantra.datasource.model.DataChannel;
import com.nathanclaire.alantra.datasource.model.DataChannelCategory;

/**
 * @author Edward Banfa 
 *
 */
public interface CustomerChannelProcessingService {

	public static final String NO_CHANNELS_CAT_CONFIGURED_FOR_CUSTOMER = 
			"CustomerChannelProcessingService.NO_CHANNELS_CAT_CONFIGURED_FOR_CUSTOMER";
	public static final String INVALID_CUST_SPECIFIED = "CustomerChannelProcessingService.INVALID_CUST_SPECIFIED";
	
	public List<DataChannelCategory> getCustomerChannelType(Customer customer) throws ApplicationException;

	public List<DataChannel> getCustomerChannels(Customer customer);

}
