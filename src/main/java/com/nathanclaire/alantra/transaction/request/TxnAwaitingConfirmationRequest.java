/**
 *  Alantra.
 */
package com.nathanclaire.alantra.transaction.request;


import com.nathanclaire.alantra.base.request.BaseRequest;

/**
 * AdviceRequest 
 * @author Edward Banfa
 */
public class TxnAwaitingConfirmationRequest extends BaseRequest {
    private String channelTypeCode;
    private Integer notificationTypeId;
    private String notificationTypeText;
    private Integer serviceTransactionId;
    private String serviceTransactionText;
    private Integer txnConfirmationStatusId;
    private String txnConfirmationStatusText;

    public TxnAwaitingConfirmationRequest() {
    }
    
	public String getChannelTypeCode() {
        return this.channelTypeCode;
    }
    
    public void setChannelTypeCode(String channelTypeCode) {
        this.channelTypeCode = channelTypeCode;
    }
	public Integer getNotificationTypeId() {
        return this.notificationTypeId;
    }
    
    public void setNotificationTypeId(Integer notificationTypeId) {
        this.notificationTypeId = notificationTypeId;
    }

    public String getNotificationTypeText() {
        return this.notificationTypeText;
    }
    
    public void setNotificationTypeText(String notificationTypeText) {
        this.notificationTypeText = notificationTypeText;
    }
	public Integer getServiceTransactionId() {
        return this.serviceTransactionId;
    }
    
    public void setServiceTransactionId(Integer serviceTransactionId) {
        this.serviceTransactionId = serviceTransactionId;
    }

    public String getServiceTransactionText() {
        return this.serviceTransactionText;
    }
    
    public void setServiceTransactionText(String serviceTransactionText) {
        this.serviceTransactionText = serviceTransactionText;
    }
	public Integer getTxnConfirmationStatusId() {
        return this.txnConfirmationStatusId;
    }
    
    public void setTxnConfirmationStatusId(Integer txnConfirmationStatusId) {
        this.txnConfirmationStatusId = txnConfirmationStatusId;
    }

    public String getTxnConfirmationStatusText() {
        return this.txnConfirmationStatusText;
    }
    
    public void setTxnConfirmationStatusText(String txnConfirmationStatusText) {
        this.txnConfirmationStatusText = txnConfirmationStatusText;
    }
}


