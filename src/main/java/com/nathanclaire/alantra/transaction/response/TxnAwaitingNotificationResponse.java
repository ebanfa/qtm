/**
 *  Alantra.
 */
package com.nathanclaire.alantra.transaction.response;


import com.nathanclaire.alantra.base.response.BaseResponse;

/**
 * AdviceResponse 
 * @author Edward Banfa
 */
public class TxnAwaitingNotificationResponse extends BaseResponse {
    private Integer txnNotificationStatusId;
    private String txnNotificationStatusText;
    private Integer serviceTransactionId;
    private String serviceTransactionText;

    public TxnAwaitingNotificationResponse() {
    }
    
	public Integer getTxnNotificationStatusId() {
        return this.txnNotificationStatusId;
    }
    
    public void setTxnNotificationStatusId(Integer txnNotificationStatusId) {
        this.txnNotificationStatusId = txnNotificationStatusId;
    }

    public String getTxnNotificationStatusText() {
        return this.txnNotificationStatusText;
    }
    
    public void setTxnNotificationStatusText(String txnNotificationStatusText) {
        this.txnNotificationStatusText = txnNotificationStatusText;
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
}


