/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.payment.rest.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.rest.request.BaseRequest;
import com.nathanclaire.alantra.payment.model.PaymentApplication;

/**
 * PaymentApplicationRequest 
 * @author Edward Banfa
 */
public class PaymentApplicationRequest extends BaseRequest {

    private Integer payment;
    private Integer billingAccount;
    private Integer invoiceItem;
    private String description;
    private BigDecimal amountAppl;
    private Integer id;
    private String code;

    public PaymentApplicationRequest() {
    }

    public Integer getPayment() {
        return this.payment;
    }
    
    public void setPayment(Integer payment) {
        this.payment = payment;
    }

    public Integer getBillingAccount() {
        return this.billingAccount;
    }
    
    public void setBillingAccount(Integer billingAccount) {
        this.billingAccount = billingAccount;
    }

    public Integer getInvoiceItem() {
        return this.invoiceItem;
    }
    
    public void setInvoiceItem(Integer invoiceItem) {
        this.invoiceItem = invoiceItem;
    }

    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmountAppl() {
        return this.amountAppl;
    }
    
    public void setAmountAppl(BigDecimal amountAppl) {
        this.amountAppl = amountAppl;
    }

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }


}


