/**
 * 
 */
package com.nathanclaire.alantra.customer.service.process;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.service.process.BaseProcessService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.StringUtil;
import com.nathanclaire.alantra.customer.model.Customer;
import com.nathanclaire.alantra.customer.model.CustomerCategory;
import com.nathanclaire.alantra.customer.model.CustomerCategoryCommsChannel;
import com.nathanclaire.alantra.customer.model.CustomerClassification;
import com.nathanclaire.alantra.customer.model.CustomerClassificationCommsChannel;
import com.nathanclaire.alantra.customer.model.CustomerCommsChannel;
import com.nathanclaire.alantra.customer.model.CustomerType;
import com.nathanclaire.alantra.customer.model.CustomerTypeCommsChannel;
import com.nathanclaire.alantra.datasource.model.DataChannel;
import com.nathanclaire.alantra.datasource.service.entity.DataChannelCategoryService;

/**
 * @author Edward Banfa 
 *
 */
@Stateless
public class CustomerCommsChannelServiceImpl extends
		BaseProcessService implements CustomerCommsChannelService 
{
	private static final String CUSTOMER_IVR_COMMS_CHANNEL = "Customer IVR communications channel";
	private static final String CUSTOMER_SMS_COMMS_CHANNEL = "Customer SMS communications channel";
	private static final String CUSTOMER_EMAIL_COMMS_CHANNEL = "Customer EMAIL communications channel";
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.process.CustomerCommsChannelService#getDefaultCustomerSMSChannel(com.nathanclaire.alantra.customer.model.Customer)
	 */
	@Override
	public DataChannel getDefaultCustomerSMSChannel(Customer customer)
			throws ApplicationException 
	{
		List<DataChannel> customerSMSChannels = this.getCustomerSMSChannels(customer);
		for(DataChannel channel : customerSMSChannels){
			// 1. Get the comms channels at the customer level
			for(CustomerCommsChannel commsChannel: customer.getCustomerCommsChannels()){
				if(commsChannel.getDataChannel().equals(channel) && StringUtil.flagToBoolean(commsChannel.getDefaultChannelFg())){
					return commsChannel.getDataChannel();				
				}
			}
			// 2. Get the comms channels at the customer type level
			CustomerType customerType = customer.getCustomerType();
			for(CustomerTypeCommsChannel commsChannel: customerType.getCustomerTypeCommsChannels()){
				if(commsChannel.getDataChannel().equals(channel) && StringUtil.flagToBoolean(commsChannel.getDefaultChannelFg())){
					return commsChannel.getDataChannel();				
				}
			}
			// 3. Get the comms channels at the customer category level
			CustomerCategory customerCategory = customerType.getCustomerCategory();
			for(CustomerCategoryCommsChannel commsChannel: customerCategory.getCustomerCategoryCommsChannels()){
				if(commsChannel.getDataChannel().equals(channel) && StringUtil.flagToBoolean(commsChannel.getDefaultChannelFg())){
					return commsChannel.getDataChannel();				
				}
			}
			// 4. Get the comms channels at the customer classification level
			CustomerClassification customerClassification = customer.getCustomerClassification();
			for(CustomerClassificationCommsChannel commsChannel: customerClassification.getCustomerClassificationCommsChannels()){
				if(commsChannel.getDataChannel().equals(channel) && StringUtil.flagToBoolean(commsChannel.getDefaultChannelFg())){
					return commsChannel.getDataChannel();				
				}
			}
		}
		if(!customerSMSChannels.isEmpty())
			return customerSMSChannels.get(0);
		else
			throw new ApplicationException("ENTITY_INSTANCE_NOT_FOUND", CUSTOMER_SMS_COMMS_CHANNEL);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.process.CustomerCommsChannelService#getCustomerSMSChannels(com.nathanclaire.alantra.customer.model.Customer)
	 */
	@Override
	public List<DataChannel> getCustomerSMSChannels(Customer customer)
			throws ApplicationException 
	{
		List<DataChannel> customerSMSChannels = new ArrayList<DataChannel>();
		// 1. Get the comms channels at the customer level
		for(CustomerCommsChannel commsChannel: customer.getCustomerCommsChannels()){
			if(this.isSMSChannel(commsChannel.getDataChannel()))
				customerSMSChannels.add(commsChannel.getDataChannel());
		}
		// 2. Get the comms channels at the customer type level
		CustomerType customerType = customer.getCustomerType();
		for(CustomerTypeCommsChannel commsChannel: customerType.getCustomerTypeCommsChannels()){
			if(this.isSMSChannel(commsChannel.getDataChannel()))
				customerSMSChannels.add(commsChannel.getDataChannel());
		}
		// 3. Get the comms channels at the customer category level
		CustomerCategory customerCategory = customerType.getCustomerCategory();
		for(CustomerCategoryCommsChannel commsChannel: customerCategory.getCustomerCategoryCommsChannels()){
			if(this.isSMSChannel(commsChannel.getDataChannel()))
				customerSMSChannels.add(commsChannel.getDataChannel());
		}
		// 4. Get the comms channels at the customer classification level
		CustomerClassification customerClassification = customer.getCustomerClassification();
		for(CustomerClassificationCommsChannel commsChannel: customerClassification.getCustomerClassificationCommsChannels()){
			if(this.isSMSChannel(commsChannel.getDataChannel()))
				customerSMSChannels.add(commsChannel.getDataChannel());
		}
		return customerSMSChannels;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.process.CustomerCommsChannelService#getDefaultCustomerEMAILChannel(com.nathanclaire.alantra.customer.model.Customer)
	 */
	@Override
	public DataChannel getDefaultCustomerEMAILChannel(Customer customer)
			throws ApplicationException {
		List<DataChannel> customerSMSChannels = this.getCustomerEMAILChannels(customer);
		for(DataChannel channel : customerSMSChannels){
			// 1. Get the comms channels at the customer level
			for(CustomerCommsChannel commsChannel: customer.getCustomerCommsChannels()){
				if(commsChannel.getDataChannel().equals(channel) && StringUtil.flagToBoolean(commsChannel.getDefaultChannelFg())){
					return commsChannel.getDataChannel();				
				}
			}
			// 2. Get the comms channels at the customer type level
			CustomerType customerType = customer.getCustomerType();
			for(CustomerTypeCommsChannel commsChannel: customerType.getCustomerTypeCommsChannels()){
				if(commsChannel.getDataChannel().equals(channel) && StringUtil.flagToBoolean(commsChannel.getDefaultChannelFg())){
					return commsChannel.getDataChannel();				
				}
			}
			// 3. Get the comms channels at the customer category level
			CustomerCategory customerCategory = customerType.getCustomerCategory();
			for(CustomerCategoryCommsChannel commsChannel: customerCategory.getCustomerCategoryCommsChannels()){
				if(commsChannel.getDataChannel().equals(channel) && StringUtil.flagToBoolean(commsChannel.getDefaultChannelFg())){
					return commsChannel.getDataChannel();				
				}
			}
			// 4. Get the comms channels at the customer classification level
			CustomerClassification customerClassification = customer.getCustomerClassification();
			for(CustomerClassificationCommsChannel commsChannel: customerClassification.getCustomerClassificationCommsChannels()){
				if(commsChannel.getDataChannel().equals(channel) && StringUtil.flagToBoolean(commsChannel.getDefaultChannelFg())){
					return commsChannel.getDataChannel();				
				}
			}
		}
		if(!customerSMSChannels.isEmpty())
			return customerSMSChannels.get(0);
		else
			throw new ApplicationException("ENTITY_INSTANCE_NOT_FOUND", CUSTOMER_EMAIL_COMMS_CHANNEL);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.process.CustomerCommsChannelService#getCustomerEMAILChannels(com.nathanclaire.alantra.customer.model.Customer)
	 */
	@Override
	public List<DataChannel> getCustomerEMAILChannels(Customer customer)
			throws ApplicationException {
		List<DataChannel> customerEMAILhannels = new ArrayList<DataChannel>();
		// 1. Get the comms channels at the customer level
		for(CustomerCommsChannel commsChannel: customer.getCustomerCommsChannels()){
			if(this.isEMAILChannel(commsChannel.getDataChannel()))
				customerEMAILhannels.add(commsChannel.getDataChannel());
		}
		// 2. Get the comms channels at the customer type level
		CustomerType customerType = customer.getCustomerType();
		for(CustomerTypeCommsChannel commsChannel: customerType.getCustomerTypeCommsChannels()){
			if(this.isEMAILChannel(commsChannel.getDataChannel()))
				customerEMAILhannels.add(commsChannel.getDataChannel());
		}
		// 3. Get the comms channels at the customer category level
		CustomerCategory customerCategory = customerType.getCustomerCategory();
		for(CustomerCategoryCommsChannel commsChannel: customerCategory.getCustomerCategoryCommsChannels()){
			if(this.isEMAILChannel(commsChannel.getDataChannel()))
				customerEMAILhannels.add(commsChannel.getDataChannel());
		}
		// 4. Get the comms channels at the customer classification level
		CustomerClassification customerClassification = customer.getCustomerClassification();
		for(CustomerClassificationCommsChannel commsChannel: customerClassification.getCustomerClassificationCommsChannels()){
			if(this.isEMAILChannel(commsChannel.getDataChannel()))
				customerEMAILhannels.add(commsChannel.getDataChannel());
		}
		return customerEMAILhannels;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.process.CustomerCommsChannelService#getDefaultCustomerIVRChannel(com.nathanclaire.alantra.customer.model.Customer)
	 */
	@Override
	public DataChannel getDefaultCustomerIVRChannel(Customer customer)
			throws ApplicationException {
		List<DataChannel> customerSMSChannels = this.getCustomerIVRChannels(customer);
		for(DataChannel channel : customerSMSChannels){
			// 1. Get the comms channels at the customer level
			for(CustomerCommsChannel commsChannel: customer.getCustomerCommsChannels()){
				if(commsChannel.getDataChannel().equals(channel) && StringUtil.flagToBoolean(commsChannel.getDefaultChannelFg())){
					return commsChannel.getDataChannel();				
				}
			}
			// 2. Get the comms channels at the customer type level
			CustomerType customerType = customer.getCustomerType();
			for(CustomerTypeCommsChannel commsChannel: customerType.getCustomerTypeCommsChannels()){
				if(commsChannel.getDataChannel().equals(channel) && StringUtil.flagToBoolean(commsChannel.getDefaultChannelFg())){
					return commsChannel.getDataChannel();				
				}
			}
			// 3. Get the comms channels at the customer category level
			CustomerCategory customerCategory = customerType.getCustomerCategory();
			for(CustomerCategoryCommsChannel commsChannel: customerCategory.getCustomerCategoryCommsChannels()){
				if(commsChannel.getDataChannel().equals(channel) && StringUtil.flagToBoolean(commsChannel.getDefaultChannelFg())){
					return commsChannel.getDataChannel();				
				}
			}
			// 4. Get the comms channels at the customer classification level
			CustomerClassification customerClassification = customer.getCustomerClassification();
			for(CustomerClassificationCommsChannel commsChannel: customerClassification.getCustomerClassificationCommsChannels()){
				if(commsChannel.getDataChannel().equals(channel) && StringUtil.flagToBoolean(commsChannel.getDefaultChannelFg())){
					return commsChannel.getDataChannel();				
				}
			}
		}
		if(!customerSMSChannels.isEmpty())
			return customerSMSChannels.get(0);
		else
			throw new ApplicationException("ENTITY_INSTANCE_NOT_FOUND", CUSTOMER_IVR_COMMS_CHANNEL);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.process.CustomerCommsChannelService#getCustomerIVRChannels(com.nathanclaire.alantra.customer.model.Customer)
	 */
	@Override
	public List<DataChannel> getCustomerIVRChannels(Customer customer)
			throws ApplicationException {
		List<DataChannel> customerIVRChannels = new ArrayList<DataChannel>();
		// 1. Get the comms channels at the customer level
		for(CustomerCommsChannel commsChannel: customer.getCustomerCommsChannels()){
			if(this.isIVRChannel(commsChannel.getDataChannel()))
				customerIVRChannels.add(commsChannel.getDataChannel());
		}
		// 2. Get the comms channels at the customer type level
		CustomerType customerType = customer.getCustomerType();
		for(CustomerTypeCommsChannel commsChannel: customerType.getCustomerTypeCommsChannels()){
			if(this.isIVRChannel(commsChannel.getDataChannel()))
				customerIVRChannels.add(commsChannel.getDataChannel());
		}
		// 3. Get the comms channels at the customer category level
		CustomerCategory customerCategory = customerType.getCustomerCategory();
		for(CustomerCategoryCommsChannel commsChannel: customerCategory.getCustomerCategoryCommsChannels()){
			if(this.isIVRChannel(commsChannel.getDataChannel()))
				customerIVRChannels.add(commsChannel.getDataChannel());
		}
		// 4. Get the comms channels at the customer classification level
		CustomerClassification customerClassification = customer.getCustomerClassification();
		for(CustomerClassificationCommsChannel commsChannel: customerClassification.getCustomerClassificationCommsChannels()){
			if(this.isIVRChannel(commsChannel.getDataChannel()))
				customerIVRChannels.add(commsChannel.getDataChannel());
		}
		return customerIVRChannels;
	}
	
	private boolean isSMSChannel(DataChannel dataChannel) {
		if(getDataChannelCategory(dataChannel).getCode().equals(DataChannelCategoryService.SMS_DATA_CHANNEL))
			return true;
		return false;
	}

	private boolean isEMAILChannel(DataChannel dataChannel) {
		if(getDataChannelCategory(dataChannel).getCode().equals(DataChannelCategoryService.EMAIL_DATA_CHANNEL))
			return true;
		return false;
	}

	private boolean isIVRChannel(DataChannel dataChannel) {
		if(getDataChannelCategory(dataChannel).getCode().equals(DataChannelCategoryService.IVR_DATA_CHANNEL))
			return true;
		return false;
	}

}
