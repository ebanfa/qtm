/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.channel.response;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.response.BaseResponse;

/**
 * ServiceTransactionResponse 
 * @author Edward Banfa
 */
public class ServiceTransactionResponse extends BaseResponse {

    private Integer currencyId;
    private String currencyText;
    private Integer serviceId;
    private String serviceText;
    private Integer serviceTransactionStatusId;
    private String serviceTransactionStatusText;
    private Integer serviceTransactionTypeId;
    private String serviceTransactionTypeText;
    private BigDecimal amount;
    private Date txnDate;
    private String accountNo;
    private String chequeNo;
    private String cardNo;
    private String accountNm;

    public ServiceTransactionResponse() {
    }

    public Integer getCurrencyId() {
        return this.currencyId;
    }
    
    public void setCurrencyId(Integer currencyId) {
        this.currencyId = currencyId;
    }

    public String getCurrencyText() {
        return this.currencyText;
    }
    
    public void setCurrencyText(String currencyText) {
        this.currencyText = currencyText;
    }

    public Integer getServiceId() {
        return this.serviceId;
    }
    
    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceText() {
        return this.serviceText;
    }
    
    public void setServiceText(String serviceText) {
        this.serviceText = serviceText;
    }

    public Integer getServiceTransactionStatusId() {
        return this.serviceTransactionStatusId;
    }
    
    public void setServiceTransactionStatusId(Integer serviceTransactionStatusId) {
        this.serviceTransactionStatusId = serviceTransactionStatusId;
    }

    public String getServiceTransactionStatusText() {
        return this.serviceTransactionStatusText;
    }
    
    public void setServiceTransactionStatusText(String serviceTransactionStatusText) {
        this.serviceTransactionStatusText = serviceTransactionStatusText;
    }

    public Integer getServiceTransactionTypeId() {
        return this.serviceTransactionTypeId;
    }
    
    public void setServiceTransactionTypeId(Integer serviceTransactionTypeId) {
        this.serviceTransactionTypeId = serviceTransactionTypeId;
    }

    public String getServiceTransactionTypeText() {
        return this.serviceTransactionTypeText;
    }
    
    public void setServiceTransactionTypeText(String serviceTransactionTypeText) {
        this.serviceTransactionTypeText = serviceTransactionTypeText;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }
    
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getTxnDate() {
        return this.txnDate;
    }
    
    public void setTxnDate(Date txnDate) {
        this.txnDate = txnDate;
    }

    public String getAccountNo() {
        return this.accountNo;
    }
    
    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getChequeNo() {
        return this.chequeNo;
    }
    
    public void setChequeNo(String chequeNo) {
        this.chequeNo = chequeNo;
    }

    public String getCardNo() {
        return this.cardNo;
    }
    
    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getAccountNm() {
        return this.accountNm;
    }
    
    public void setAccountNm(String accountNm) {
        this.accountNm = accountNm;
    }


}


