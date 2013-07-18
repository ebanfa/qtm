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
import com.nathanclaire.alantra.customer.model.CustomerCategory;
import com.nathanclaire.alantra.customer.model.CustomerCategoryNotificationChannel;
import com.nathanclaire.alantra.customer.model.CustomerNotificationChannel;
import com.nathanclaire.alantra.customer.model.CustomerType;
import com.nathanclaire.alantra.customer.model.CustomerTypeNotificationChannel;
import com.nathanclaire.alantra.datasource.model.DataChannel;
import com.nathanclaire.alantra.datasource.model.DataChannelCategory;
import com.nathanclaire.alantra.datasource.model.DataChannelType;
import com.nathanclaire.alantra.datasource.service.entity.DataChannelService;
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
	 * @see com.nathanclaire.alantra.customer.service.process.CustomerChannelProcessingService#getCustomerChannelCategories(com.nathanclaire.alantra.customer.model.Customer)
	 */
	@Override
	public List<DataChannelCategory> getCustomerChannelCategories(Customer customer)
			throws ApplicationException {
		if(customer == null)
			throw new ApplicationException(INVALID_CUST_SPECIFIED);
		logger.debug("Loading channel categories for customer {}", customer.getName());
		Set<CustomerNotificationChannel> notificationChannels = customer.getCustomerNotificationChannels();
		List<DataChannelCategory> channelCategories = new ArrayList<DataChannelCategory>();
		if(notificationChannels.isEmpty()) 
			return getCustomerTypeChannelCategories(customer.getCustomerType());
		for(CustomerNotificationChannel notificationChannel: notificationChannels)
		{
			channelCategories.add(notificationChannel.getDataChannelCategory());
		}
		return channelCategories;
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.process.CustomerChannelProcessingService#getCustomerTypeChannelCategories(com.nathanclaire.alantra.customer.model.CustomerType)
	 */
	@Override
	public List<DataChannelCategory> getCustomerTypeChannelCategories(CustomerType customerType) throws ApplicationException
	{
		if(customerType == null)
			throw new ApplicationException(INVALID_CUST_TYPE_SPECIFIED);
		logger.debug("Loading communication channels for customer type {}", customerType);
		Set<CustomerTypeNotificationChannel> notificationChannels = customerType.getCustomerTypeNotificationChannels();
		List<DataChannelCategory> channelCategories = new ArrayList<DataChannelCategory>();
		if(notificationChannels.isEmpty()) 
			return getCustomerCategoryChannelCategories(customerType.getCustomerCategory());
		for(CustomerTypeNotificationChannel notificationChannel: notificationChannels)
		{
			channelCategories.add(notificationChannel.getDataChannelCategory());
		}
		return channelCategories;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.process.CustomerChannelProcessingService#getCustomerCategoryChannelCategories(com.nathanclaire.alantra.customer.model.CustomerCategory)
	 */
	@Override
	public List<DataChannelCategory> getCustomerCategoryChannelCategories(CustomerCategory customerCategory) throws ApplicationException
	{
		if(customerCategory == null)
			throw new ApplicationException(INVALID_CUST_CAT_SPECIFIED);
		logger.debug("Loading communication channels for customer category {}", customerCategory);
		Set<CustomerCategoryNotificationChannel> notificationChannels = customerCategory.getCustomerCategoryNotificationChannels();
		List<DataChannelCategory> channelCategories = new ArrayList<DataChannelCategory>();
		if(notificationChannels.isEmpty()) 
			throw new ApplicationException(NO_CHANNELS_CAT_CONFIGURED_FOR_CUSTOMER_CAT);
		for(CustomerCategoryNotificationChannel notificationChannel: notificationChannels)
		{
			channelCategories.add(notificationChannel.getDataChannelCategory());
		}
		return channelCategories;
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.process.CustomerChannelProcessingService#getCustomerChannels(com.nathanclaire.alantra.customer.model.Customer)
	 */
	@Override
	public List<DataChannel> getCustomerChannels(Customer customer) throws ApplicationException {
		if(customer == null)
			throw new ApplicationException(INVALID_CUST_SPECIFIED);
		logger.debug("Loading communication channels for customer {}", customer);
		List<DataChannel> channels = new ArrayList<DataChannel>();
		
		for(DataChannelCategory channelCategory : getCustomerChannelCategories(customer))
		{
			for(DataChannelType channelType : channelCategory.getDataChannelTypes())
			{
				channels.addAll(channelType.getDataChannels());
			}
		}
		logger.debug("Customer {} has {} communucations channels configured", customer, channels.size());
		return channels;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.process.CustomerChannelProcessingService#getCustomerOutboundChannels(com.nathanclaire.alantra.customer.model.Customer)
	 */
	@Override
	public  List<DataChannel> getCustomerOutboundChannels(Customer customer) throws ApplicationException {
		if(customer == null)
			throw new ApplicationException(INVALID_CUST_SPECIFIED);
		logger.debug("Loading outbound communication channels for customer {}", customer);
		List<DataChannel> channels = new ArrayList<DataChannel>();
		
		for(DataChannel channel : getCustomerChannels(customer))
		{
			logger.debug("Checking for outbound channel flag in channel {} with flag {}", channel, channel.getInboundOutboundCd());
			if(channel.getInboundOutboundCd().equals(DataChannelService.OUTBOUND_CHANNEL))
				channels.add(channel);
			else if(channel.getInboundOutboundCd().equals(DataChannelService.BIRDIRECTIONAL_CHANNEL))
				channels.add(channel);
		}
		return channels;
	}
}
