/**
 *  Alantra.
 */
package com.nathanclaire.alantra.transaction.request;

import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.request.BaseRequest;

/**
 * AdviceRequest 
 * @author Edward Banfa
 */
public class CustTxnTypeConfirmationOptionsRequest extends BaseRequest {
    private String description;
    private Integer customerId;
    private String customerText;
    private Integer transactionTypeId;
    private String transactionTypeText;
    private Character emailFg;
    private BigDecimal emailAmount;
    private Character smsFg;
    private BigDecimal smsAmount;
    private Character ivrFg;
    private BigDecimal ivrAmount;

    public CustTxnTypeConfirmationOptionsRequest() {
    }
    
	public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
	public Integer getCustomerId() {
        return this.customerId;
    }
    
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerText() {
        return this.customerText;
    }
    
    public void setCustomerText(String customerText) {
        this.customerText = customerText;
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


