/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.channel.request;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.nathanclaire.alantra.base.request.BaseRequest;

/**
 * ServiceTransactionRequest 
 * @author Edward Banfa
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ServiceTransactionRequest extends BaseRequest {
	// Service Channels
	public static String CHANNEL_EFT = "EFT";
	public static String CHANNEL_ATM = "ATM";
	public static String CHANNEL_MOBILE_BANKING = "MOBILE";
	public static String CHANNEL_ONLINE_BANKING = "ONLINE";
	
	private String bankIdentificationNo;
	private String accountNo;
	private String customerName;
	private String transactionAmount;
	private String transactionDate;
	private String transactionChannel;
	private String transactionType;
	private int returnCode;

    public ServiceTransactionRequest() {
    }

	/**
	 * @param bankIdentificationNo
	 * @param accountNo
	 * @param customerName
	 * @param transactionAmount
	 * @param transactionDate
	 * @param transactionChannel
	 * @param returnCode
	 */
	public ServiceTransactionRequest(String bankIdentificationNo,
			String accountNo, String customerName, String transactionAmount,
			String transactionDate, String transactionChannel, int returnCode) {
		this.bankIdentificationNo = bankIdentificationNo;
		this.accountNo = accountNo;
		this.customerName = customerName;
		this.transactionAmount = transactionAmount;
		this.transactionDate = transactionDate;
		this.transactionChannel = transactionChannel;
		this.returnCode = returnCode;
	}

	/**
	 * @return the bankIdentificationNo
	 */
	public String getBankIdentificationNo() {
		return bankIdentificationNo;
	}

	/**
	 * @param bankIdentificationNo the bankIdentificationNo to set
	 */
	public void setBankIdentificationNo(String bankIdentificationNo) {
		this.bankIdentificationNo = bankIdentificationNo;
	}

	/**
	 * @return the accountNo
	 */
	public String getAccountNo() {
		return accountNo;
	}

	/**
	 * @param accountNo the accountNo to set
	 */
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	/**
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * @param customerName the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
	 * @return the transactionAmount
	 */
	public String getTransactionAmount() {
		return transactionAmount;
	}

	/**
	 * @param transactionAmount the transactionAmount to set
	 */
	public void setTransactionAmount(String transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	/**
	 * @return the transactionDate
	 */
	public String getTransactionDate() {
		return transactionDate;
	}

	/**
	 * @param transactionDate the transactionDate to set
	 */
	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

	/**
	 * @return the transactionChannel
	 */
	public String getTransactionChannel() {
		return transactionChannel;
	}

	/**
	 * @param transactionChannel the transactionChannel to set
	 */
	public void setTransactionChannel(String transactionChannel) {
		this.transactionChannel = transactionChannel;
	}

	/**
	 * @return the returnCode
	 */
	public int getReturnCode() {
		return returnCode;
	}

	/**
	 * @param returnCode the returnCode to set
	 */
	public void setReturnCode(int returnCode) {
		this.returnCode = returnCode;
	}

	/**
	 * @return the transactionType
	 */
	public String getTransactionType() {
		return transactionType;
	}

	/**
	 * @param transactionType the transactionType to set
	 */
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}



}


