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
public class CustCatTxnTypeNotificationOptionsResponse extends BaseResponse {
    private String description;
    private Integer customerCategoryId;
    private String customerCategoryText;
    private Integer transactionTypeId;
    private String transactionTypeText;
    private Character emailFg;
    private BigDecimal emailAmount;
    private Character smsFg;
    private BigDecimal smsAmount;
    private Character ivrFg;
    private BigDecimal ivrAmount;

    public CustCatTxnTypeNotificationOptionsResponse() {
    }
    
	public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
	public Integer getCustomerCategoryId() {
        return this.customerCategoryId;
    }
    
    public void setCustomerCategoryId(Integer customerCategoryId) {
        this.customerCategoryId = customerCategoryId;
    }

    public String getCustomerCategoryText() {
        return this.customerCategoryText;
    }
    
    public void setCustomerCategoryText(String customerCategoryText) {
        this.customerCategoryText = customerCategoryText;
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


