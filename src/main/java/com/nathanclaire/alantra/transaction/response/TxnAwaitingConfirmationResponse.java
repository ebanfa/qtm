/**
 *  Alantra.
 */
package com.nathanclaire.alantra.transaction.response;


import com.nathanclaire.alantra.base.response.BaseResponse;

/**
 * AdviceResponse 
 * @author Edward Banfa
 */
public class TxnAwaitingConfirmationResponse extends BaseResponse {
    private Integer txnConfirmationStatusId;
    private String txnConfirmationStatusText;
    private Integer serviceTransactionId;
    private String serviceTransactionText;

    public TxnAwaitingConfirmationResponse() {
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


