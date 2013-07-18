/**
 * 
 */
package com.nathanclaire.alantra.customer.service.process;

import java.util.List;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.customer.model.Customer;
import com.nathanclaire.alantra.customer.model.CustomerCategory;
import com.nathanclaire.alantra.customer.model.CustomerType;
import com.nathanclaire.alantra.datasource.model.DataChannel;
import com.nathanclaire.alantra.datasource.model.DataChannelCategory;

/**
 * @author Edward Banfa 
 *
 */
public interface CustomerChannelProcessingService {

	public static final String NO_CHANNELS_CAT_CONFIGURED_FOR_CUSTOMER_CAT = 
			"CustomerChannelProcessingService.NO_CHANNELS_CAT_CONFIGURED_FOR_CUSTOMER_CAT";
	
	public static final String NO_CHANNELS_CAT_CONFIGURED_FOR_CUSTOMER = 
			"CustomerChannelProcessingService.NO_CHANNELS_CAT_CONFIGURED_FOR_CUSTOMER";
	
	public static final String INVALID_CUST_SPECIFIED = "CustomerChannelProcessingService.INVALID_CUST_SPECIFIED";
	
	public static final String INVALID_CUST_TYPE_SPECIFIED =  "CustomerChannelProcessingService.INVALID_CUST_TYPE_SPECIFIED";

	public static final String INVALID_CUST_CAT_SPECIFIED = "CustomerChannelProcessingService.INVALID_CUST_CAT_SPECIFIED";
	
	public List<DataChannel> getCustomerChannels(Customer customer) throws ApplicationException;
	
	public List<DataChannelCategory> getCustomerChannelCategories(Customer customer) throws ApplicationException;

	public List<DataChannelCategory> getCustomerTypeChannelCategories(CustomerType customerType) throws ApplicationException;

	public List<DataChannelCategory> getCustomerCategoryChannelCategories(CustomerCategory customerCategory) throws ApplicationException;

	public List<DataChannel> getCustomerOutboundChannels(Customer customer) throws ApplicationException;

}
