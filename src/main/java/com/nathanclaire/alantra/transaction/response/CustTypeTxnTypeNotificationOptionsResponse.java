/**
 *  Alantra.
 */
package com.nathanclaire.alantra.transaction.response;

import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.response.BaseResponse;

/**
 * AdviceResponse 
 * @author Edward Banfa
 */
public class CustTypeTxnTypeNotificationOptionsResponse extends BaseResponse {
    private String description;
    private Integer customerTypeId;
    private String customerTypeText;
    private Integer transactionTypeId;
    private String transactionTypeText;
    private Character emailFg;
    private BigDecimal emailAmount;
    private Character smsFg;
    private BigDecimal smsAmount;
    private Character ivrFg;
    private BigDecimal ivrAmount;

    public CustTypeTxnTypeNotificationOptionsResponse() {
    }
    
	public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
	public Integer getCustomerTypeId() {
        return this.customerTypeId;
    }
    
    public void setCustomerTypeId(Integer customerTypeId) {
        this.customerTypeId = customerTypeId;
    }

    public String getCustomerTypeText() {
        return this.customerTypeText;
    }
    
    public void setCustomerTypeText(String customerTypeText) {
        this.customerTypeText = customerTypeText;
    }
	public Integer getTransactionTypeId() {
        return this.transactionTypeId;
    }
    
    public void setTransactionTypeId(Integer transactionTypeId) {
        this.transactionTypeId = transactionTypeId;
    }

    public String getTransactionTypeText() {
        return this.transactionTypeText;
    }
    
    public void setTransactionTypeText(String transactionTypeText) {
        this.transactionTypeText = transactionTypeText;
    }
	public Character getEmailFg() {
        return this.emailFg;
    }
    
    public void setEmailFg(Character emailFg) {
        this.emailFg = emailFg;
    }
	public BigDecimal getEmailAmount() {
        return this.emailAmount;
    }
    
    public void setEmailAmount(BigDecimal emailAmount) {
        this.emailAmount = emailAmount;
    }
	public Character getSmsFg() {
        return this.smsFg;
    }
    
    public void setSmsFg(Character smsFg) {
        this.smsFg = smsFg;
    }
	public BigDecimal getSmsAmount() {
        return this.smsAmount;
    }
    
    public void setSmsAmount(BigDecimal smsAmount) {
        this.smsAmount = smsAmount;
    }
	public Character getIvrFg() {
        return this.ivrFg;
    }
    
    public void setIvrFg(Character ivrFg) {
        this.ivrFg = ivrFg;
    }
	public BigDecimal getIvrAmount() {
        return this.ivrAmount;
    }
    
    public void setIvrAmount(BigDecimal ivrAmount) {
        this.ivrAmount = ivrAmount;
    }
}


