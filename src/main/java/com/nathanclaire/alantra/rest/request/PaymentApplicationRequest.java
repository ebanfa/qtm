/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.rest.request;

import java.util.Date;
import java.math.BigDecimal;

/**
 * PaymentApplicationRequest 
 * @author Edward Banfa
 */
public class PaymentApplicationRequest extends BaseRequest {

    private Integer id;
    private Integer payment;
    private Integer billingAccount;
    private Integer invoiceItem;
    private String code;
    private String description;
    private BigDecimal amountAppl;

    public PaymentApplicationRequest() {
    }

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
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

    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
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


}


