/**
 * 
 */
package com.nathanclaire.alantra.customer.service.process;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.service.process.BaseProcessService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.customer.model.Customer;
import com.nathanclaire.alantra.customer.model.CustomerNotificationChannel;
import com.nathanclaire.alantra.datasource.model.DataChannel;
import com.nathanclaire.alantra.datasource.model.DataChannelCategory;
import com.nathanclaire.alantra.notification.service.entity.CustomerNotificationService;

/**
 * @author Edward Banfa 
 *
 */
@Stateless
public class CustomerChannelProcessingServiceImpl extends
		BaseProcessService implements CustomerChannelProcessingService 
{
	@Inject CustomerNotificationService customerNotificationService;
	private Logger logger = LoggerFactory.getLogger(CustomerChannelProcessingServiceImpl.class);
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.process.CustomerChannelProcessingService#getCustomerChannelType(com.nathanclaire.alantra.customer.model.Customer)
	 */
	@Override
	public List<DataChannelCategory> getCustomerChannelType(Customer customer)
			throws ApplicationException {
		if(customer == null)
			throw new ApplicationException(INVALID_CUST_SPECIFIED);
		logger.debug("Loading channel categories for customer {}", customer.getName());
		Set<CustomerNotificationChannel> notificationChannels = customer.getCustomerNotificationChannels();
		List<DataChannelCategory> channelCategories = new ArrayList<DataChannelCategory>();
		if(channelCategories.isEmpty()) 
			throw new ApplicationException(NO_CHANNELS_CAT_CONFIGURED_FOR_CUSTOMER);
		for(CustomerNotificationChannel notificationChannel: notificationChannels)
		{
			channelCategories.add(notificationChannel.getDataChannelCategory());
		}
		return channelCategories;
	}
	@Override
	public List<DataChannel> getCustomerChannels(Customer customer) {
		// TODO Auto-generated method stub
		return null;
	}

}
