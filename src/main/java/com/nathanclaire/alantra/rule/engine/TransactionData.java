/**
 * 
 */
package com.nathanclaire.alantra.rule.engine;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.nathanclaire.alantra.channel.config.ChannelConfiguration;

/**
 * An abstract model of a transaction.
 * 
 * @author Edward Banfa
 *
 */
public interface TransactionData {
	
	public String getCardNo();
	
	public String getChequeNo();
	
	public Date getTransactionDate();
	
	public String getAccountNumber();
	
	public BigDecimal getTransactionAmount();
	
	public String getTransactionTypeCode();
	
	public String getCurrencyCode();
	
	public Boolean isDebit();
	
	public Boolean isInboundTransaction();
	
	public Boolean isForwardTransaction();
	
	public Boolean isOutboundTransaction();
	
	public ChannelConfiguration getPrimaryOutboundRoute();
	
	public List<ChannelConfiguration> getSecondaryRoutes();
	
	

}
