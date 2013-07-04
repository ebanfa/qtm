/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.customer.response;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.response.BaseResponse;

/**
 * CustomerCategoryTransactionLimitResponse 
 * @author Edward Banfa
 */
public class CustomerCategoryTransactionLimitResponse extends BaseResponse {

    private Integer serviceTransactionTypeId;
    private String serviceTransactionTypeText;
    private Integer customerCategoryId;
    private String customerCategoryText;
    private Integer limitTypeId;
    private String limitTypeText;
    private BigDecimal amount;
    private Date startDt;
    private Date endDt;

    public CustomerCategoryTransactionLimitResponse() {
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

    public Integer getLimitTypeId() {
        return this.limitTypeId;
    }
    
    public void setLimitTypeId(Integer limitTypeId) {
        this.limitTypeId = limitTypeId;
    }

    public String getLimitTypeText() {
        return this.limitTypeText;
    }
    
    public void setLimitTypeText(String limitTypeText) {
        this.limitTypeText = limitTypeText;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }
    
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getStartDt() {
        return this.startDt;
    }
    
    public void setStartDt(Date startDt) {
        this.startDt = startDt;
    }

    public Date getEndDt() {
        return this.endDt;
    }
    
    public void setEndDt(Date endDt) {
        this.endDt = endDt;
    }


}


