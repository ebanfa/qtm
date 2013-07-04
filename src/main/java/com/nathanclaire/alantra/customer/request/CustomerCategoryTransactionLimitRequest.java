/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.customer.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.request.BaseRequest;

/**
 * CustomerCategoryTransactionLimitRequest 
 * @author Edward Banfa
 */
public class CustomerCategoryTransactionLimitRequest extends BaseRequest {

    private Integer serviceTransactionTypeId;
    private String serviceTransactionTypeText;
    private Integer customerCategoryId;
    private String customerCategoryText;
    private Integer limitTypeId;
    private String limitTypeText;
    private String name;
    private BigDecimal amount;
    private Date startDt;
    private Date endDt;
    private String description;
    private Integer id;
    private String code;

    public CustomerCategoryTransactionLimitRequest() {
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

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
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

    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
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


